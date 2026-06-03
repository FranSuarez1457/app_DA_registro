package es.ulpgc.eite.da.templatedemo;

import android.content.Context;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import es.ulpgc.eite.da.templatedemo.database.AppDataBase;
import es.ulpgc.eite.da.templatedemo.database.UserEntity;
import es.ulpgc.eite.da.templatedemo.database.ProjectEntity;
import es.ulpgc.eite.da.templatedemo.database.TaskEntity;
import es.ulpgc.eite.da.templatedemo.database.FavoriteEntity;

@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    private AppDataBase db;

    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, AppDataBase.class)
                .allowMainThreadQueries()
                .build();
    }

    @After
    public void closeDb() {
        db.close();
    }

    @Test
    public void fullSystemIntegration_isolationAndFavorites_worksPerfectly() {
        insertUser("u1@ulpgc.es", "ulpgc.es");
        insertUser("u2@ulpgc.es", "ulpgc.es");
        insertUser("u1@upv.es", "upv.es");
        insertUser("u2@upv.es", "upv.es");
        insertUser("u1@ull.es", "ull.es");
        insertUser("u2@ull.es", "ull.es");
        insertUser("indep1@gmail.com", "indep1.com");
        insertUser("indep2@yahoo.com", "indep2.com");
        insertUser("indep3@hotmail.com", "indep3.com");

        String[] emails = {
                "u1@ulpgc.es", "u2@ulpgc.es", "u1@upv.es", "u2@upv.es",
                "u1@ull.es", "u2@ull.es", "indep1@gmail.com", "indep2@yahoo.com", "indep3@hotmail.com"
        };

        String[] domains = {
                "ulpgc.es", "ulpgc.es", "upv.es", "upv.es",
                "ull.es", "ull.es", "indep1.com", "indep2.com", "indep3.com"
        };

        int globalProjectIdCounter = 1;

        for (int i = 0; i < emails.length; i++) {
            for (int p = 1; p <= 2; p++) {
                String projectName = "Proyecto " + p + " de " + emails[i];
                insertProjectAndTasks(globalProjectIdCounter, projectName, emails[i], domains[i]);
                globalProjectIdCounter++;
            }
        }

        List<ProjectEntity> ulpgcProjects = db.projectDao().getProjectsByCompany("ulpgc.es");

        assertEquals(4, ulpgcProjects.size());

        for (ProjectEntity project : ulpgcProjects) {
            assertEquals("ulpgc.es", project.companyDomain);
            assertNotEquals("upv.es", project.companyDomain);
            assertNotEquals("ull.es", project.companyDomain);
        }

        int targetProjectId = -1;
        for(ProjectEntity p : ulpgcProjects) {
            if(p.name.equals("Proyecto 1 de u2@ulpgc.es")) {
                targetProjectId = p.projectId;
                break;
            }
        }

        FavoriteEntity newFav = new FavoriteEntity();
        newFav.userEmail = "u1@ulpgc.es";
        newFav.projectId = targetProjectId;
        db.favoriteDao().insertFavorite(newFav);

        List<ProjectEntity> user1Favorites = db.favoriteDao().getFavoriteProjectsForUser("u1@ulpgc.es");

        assertNotNull(user1Favorites);
        assertEquals(1, user1Favorites.size());
        assertEquals("Proyecto 1 de u2@ulpgc.es", user1Favorites.get(0).name);
    }

    private void insertUser(String email, String domain) {
        UserEntity u = new UserEntity();
        u.email = email;
        u.companyDomain = domain;
        u.password = "1234";
        db.userDao().insertUser(u);
    }

    private void insertProjectAndTasks(int id, String name, String creatorEmail, String companyDomain) {
        ProjectEntity p = new ProjectEntity();
        p.projectId = id;
        p.name = name;
        p.creatorEmail = creatorEmail;
        p.companyDomain = companyDomain;
        db.projectDao().insertProject(p);

        TaskEntity t1 = new TaskEntity();
        t1.name = "Tarea 1 para " + name;
        t1.parentProjectId = id;
        db.taskDao().insertTask(t1);

        TaskEntity t2 = new TaskEntity();
        t2.name = "Tarea 2 para " + name;
        t2.parentProjectId = id;
        db.taskDao().insertTask(t2);
    }

    @Test
    public void test_emptyDatabase_returnsEmptyListsWithoutCrashing() {
        List<ProjectEntity> projects = db.projectDao().getProjectsByCompany("ulpgc.es");
        List<ProjectEntity> favorites = db.favoriteDao().getFavoriteProjectsForUser("fantasma@correo.com");

        assertNotNull(projects);
        assertEquals(0, projects.size());

        assertNotNull(favorites);
        assertEquals(0, favorites.size());
    }

    @Test
    public void test_singleUser_canCreateAndRetrievePerfectly() {
        insertUser("solitario@empresa.com", "empresa.com");
        insertProjectAndTasks(99, "Proyecto Secreto", "solitario@empresa.com", "empresa.com");

        List<ProjectEntity> myProjects = db.projectDao().getProjectsByCompany("empresa.com");
        assertEquals(1, myProjects.size());
        assertEquals("Proyecto Secreto", myProjects.get(0).name);
    }

    @Test
    public void test_threeUsersSameCompany_canSeeAllCompanyProjects() {
        insertUser("a@startup.com", "startup.com");
        insertUser("b@startup.com", "startup.com");
        insertUser("c@startup.com", "startup.com");

        insertProjectAndTasks(101, "App Android", "a@startup.com", "startup.com");
        insertProjectAndTasks(102, "Base de Datos", "b@startup.com", "startup.com");
        insertProjectAndTasks(103, "Diseño UI", "c@startup.com", "startup.com");

        List<ProjectEntity> companyProjects = db.projectDao().getProjectsByCompany("startup.com");
        assertEquals(3, companyProjects.size());
    }

    @Test
    public void test_duplicateFavorite_isIgnoredByDatabase() {
        insertUser("spammer@ulpgc.es", "ulpgc.es");
        insertProjectAndTasks(500, "Proyecto Interesante", "otro@ulpgc.es", "ulpgc.es");

        FavoriteEntity fav1 = new FavoriteEntity();
        fav1.userEmail = "spammer@ulpgc.es";
        fav1.projectId = 500;
        db.favoriteDao().insertFavorite(fav1);

        FavoriteEntity fav2 = new FavoriteEntity();
        fav2.userEmail = "spammer@ulpgc.es";
        fav2.projectId = 500;
        db.favoriteDao().insertFavorite(fav2);

        FavoriteEntity fav3 = new FavoriteEntity();
        fav3.userEmail = "spammer@ulpgc.es";
        fav3.projectId = 500;
        db.favoriteDao().insertFavorite(fav3);

        List<ProjectEntity> favorites = db.favoriteDao().getFavoriteProjectsForUser("spammer@ulpgc.es");
        assertEquals(1, favorites.size());
    }

    @Test
    public void test_removeFavorite_updatesListCorrectly() {
        insertUser("indeciso@ull.es", "ull.es");
        insertProjectAndTasks(600, "Proyecto Temporal", "creador@ull.es", "ull.es");

        FavoriteEntity fav = new FavoriteEntity();
        fav.userEmail = "indeciso@ull.es";
        fav.projectId = 600;
        db.favoriteDao().insertFavorite(fav);

        assertEquals(1, db.favoriteDao().getFavoriteProjectsForUser("indeciso@ull.es").size());

        db.favoriteDao().removeFavorite("indeciso@ull.es", 600);

        List<ProjectEntity> favoritesAfterDelete = db.favoriteDao().getFavoriteProjectsForUser("indeciso@ull.es");
        assertEquals(0, favoritesAfterDelete.size());
    }

    @Test
    public void test_guestMode_seesOnlyInitialJSONData() {
        es.ulpgc.eite.da.templatedemo.app.AppMediator.getInstance().setLoggedUser(null);

        es.ulpgc.eite.da.templatedemo.projectList.projectListModel listModel =
                new es.ulpgc.eite.da.templatedemo.projectList.projectListModel(ApplicationProvider.getApplicationContext());

        List<ProjectEntity> guestProjects = listModel.getProjectList();

        assertNotNull(guestProjects);
        assertEquals(1, guestProjects.size());
        assertEquals("Proyecto de Demostración", guestProjects.get(0).name);
    }

    @Test
    public void test_guestMode_cannotAddFavorite() {
        es.ulpgc.eite.da.templatedemo.app.AppMediator.getInstance().setLoggedUser(null);

        es.ulpgc.eite.da.templatedemo.projectDetail.projectDetailModel detailModel =
                new es.ulpgc.eite.da.templatedemo.projectDetail.projectDetailModel(ApplicationProvider.getApplicationContext());

        boolean isFavoriteAdded = detailModel.addToFavorites();

        assertEquals(false, isFavoriteAdded);

        List<FavoriteEntity> allFavsInDb = db.favoriteDao().getFavoritesByUser("");
        assertEquals(0, allFavsInDb.size());
    }

    @Test
    public void test_alternatingSessions_maintainAbsoluteIsolation() {
        es.ulpgc.eite.da.templatedemo.app.AppMediator.getInstance().setLoggedUser(null);
        UserEntity currentGuest = es.ulpgc.eite.da.templatedemo.app.AppMediator.getInstance().getLoggedUser();
        assertEquals(null, currentGuest);

        insertUser("jefe@empresaA.com", "empresaA.com");
        insertProjectAndTasks(701, "Secreto A1", "jefe@empresaA.com", "empresaA.com");
        insertProjectAndTasks(702, "Secreto A2", "jefe@empresaA.com", "empresaA.com");

        UserEntity userA = new UserEntity();
        userA.email = "jefe@empresaA.com";
        userA.companyDomain = "empresaA.com";

        es.ulpgc.eite.da.templatedemo.app.AppMediator.getInstance().setLoggedUser(userA);

        String domainA = es.ulpgc.eite.da.templatedemo.app.AppMediator.getInstance().getLoggedUser().companyDomain;
        List<ProjectEntity> projectsA = db.projectDao().getProjectsByCompany(domainA);
        assertEquals(2, projectsA.size());

        insertUser("novato@empresaB.com", "empresaB.com");

        UserEntity userB = new UserEntity();
        userB.email = "novato@empresaB.com";
        userB.companyDomain = "empresaB.com";

        es.ulpgc.eite.da.templatedemo.app.AppMediator.getInstance().setLoggedUser(userB);

        String domainB = es.ulpgc.eite.da.templatedemo.app.AppMediator.getInstance().getLoggedUser().companyDomain;
        List<ProjectEntity> projectsB = db.projectDao().getProjectsByCompany(domainB);
        assertEquals(0, projectsB.size());

        es.ulpgc.eite.da.templatedemo.app.AppMediator.getInstance().setLoggedUser(null);
        UserEntity finalGuest = es.ulpgc.eite.da.templatedemo.app.AppMediator.getInstance().getLoggedUser();
        assertEquals(null, finalGuest);
    }

    @Test
    public void test_massiveDataLoad_doesNotBreakIsolation() {
        insertUser("victima@corporacion.com", "corporacion.com");
        insertUser("atacante@hacker.com", "hacker.com");

        for(int i = 1; i <= 100; i++) {
            insertProjectAndTasks(1000 + i, "Proyecto Carga " + i, "victima@corporacion.com", "corporacion.com");
        }

        List<ProjectEntity> victimaProjects = db.projectDao().getProjectsByCompany("corporacion.com");
        assertEquals(100, victimaProjects.size());

        List<ProjectEntity> atacanteProjects = db.projectDao().getProjectsByCompany("hacker.com");
        assertEquals(0, atacanteProjects.size());
    }
}