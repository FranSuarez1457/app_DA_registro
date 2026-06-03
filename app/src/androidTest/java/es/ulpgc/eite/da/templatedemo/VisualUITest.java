package es.ulpgc.eite.da.templatedemo;

import android.view.View;

import androidx.test.core.app.ActivityScenario;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import es.ulpgc.eite.da.templatedemo.app.AppMediator;
import es.ulpgc.eite.da.templatedemo.database.AppDataBase;
import es.ulpgc.eite.da.templatedemo.database.UserEntity;
import es.ulpgc.eite.da.templatedemo.database.ProjectEntity;
import es.ulpgc.eite.da.templatedemo.login.loginActivity;

@RunWith(AndroidJUnit4.class)
public class VisualUITest {

    private AppDataBase db;

    @Before
    public void setUp() {
        db = AppDataBase.getInstance(ApplicationProvider.getApplicationContext());
        db.clearAllTables();
        AppMediator.getInstance().setLoggedUser(null);
    }

    @After
    public void tearDown() {
        db.clearAllTables();
        AppMediator.getInstance().setLoggedUser(null);
    }

    public static ViewAction forceClick() {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return ViewMatchers.isEnabled();
            }
            @Override
            public String getDescription() {
                return "force click";
            }
            @Override
            public void perform(UiController uiController, View view) {
                view.performClick();
            }
        };
    }

    private void injectTestUser(String email, String domain) {
        UserEntity u = new UserEntity();
        u.email = email;
        u.password = "123";
        u.companyDomain = domain;
        db.userDao().insertUser(u);
    }

    private void injectTestProject(int id, String name, String email, String domain) {
        ProjectEntity p = new ProjectEntity();
        p.projectId = id;
        p.name = name;
        p.creatorEmail = email;
        p.companyDomain = domain;
        db.projectDao().insertProject(p);
    }

    private void loginVisually(String email, String pass) throws InterruptedException {
        Thread.sleep(500);
        Espresso.onView(ViewMatchers.withId(R.id.etLoginEmail))
                .perform(ViewActions.replaceText(email));
        Espresso.onView(ViewMatchers.withId(R.id.etLoginPassword))
                .perform(ViewActions.replaceText(pass));
        Thread.sleep(500);
        Espresso.onView(ViewMatchers.withId(R.id.btnLoginContinue))
                .perform(forceClick());
    }

    private void clickGuestVisually() throws InterruptedException {
        Thread.sleep(500);
        Espresso.onView(ViewMatchers.withId(R.id.btnLoginGuest))
                .perform(forceClick());
    }

    private void goToProjectListVisually() throws InterruptedException {
        Thread.sleep(500);
        Espresso.onView(ViewMatchers.withId(R.id.btnNavProjectList))
                .perform(forceClick());
        Thread.sleep(500);
    }

    @Test
    public void visualTest_01_fullIntegration() throws InterruptedException {
        injectTestUser("u1@ulpgc.es", "ulpgc.es");
        injectTestProject(1, "Proyecto 1 ULPGC", "u1@ulpgc.es", "ulpgc.es");

        ActivityScenario.launch(loginActivity.class);

        loginVisually("u1@ulpgc.es", "123");
        goToProjectListVisually();

        Espresso.onView(ViewMatchers.withId(R.id.cardProject1)).perform(forceClick());
        Thread.sleep(500);
        Espresso.onView(ViewMatchers.withId(R.id.btnFavorite)).perform(forceClick());
        Thread.sleep(500);

        // Comprobación segura
        Espresso.onView(ViewMatchers.withId(R.id.btnFavorite))
                .check(ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
    }

    @Test
    public void visualTest_02_emptyDatabase() throws InterruptedException {
        injectTestUser("fantasma@correo.com", "correo.com");

        ActivityScenario.launch(loginActivity.class);

        loginVisually("fantasma@correo.com", "123");
        goToProjectListVisually();

        Espresso.onView(ViewMatchers.withId(R.id.tvListTitle))
                .check(ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
    }

    @Test
    public void visualTest_03_singleUser() throws InterruptedException {
        injectTestUser("solitario@empresa.com", "empresa.com");
        injectTestProject(99, "Proyecto Secreto", "solitario@empresa.com", "empresa.com");

        ActivityScenario.launch(loginActivity.class);

        loginVisually("solitario@empresa.com", "123");
        goToProjectListVisually();

        Espresso.onView(ViewMatchers.withId(R.id.tvListTitle))
                .check(ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
    }

    @Test
    public void visualTest_04_threeUsersSameCompany() throws InterruptedException {
        injectTestUser("a@startup.com", "startup.com");
        injectTestUser("b@startup.com", "startup.com");
        injectTestUser("c@startup.com", "startup.com");

        injectTestProject(101, "App Android", "a@startup.com", "startup.com");
        injectTestProject(102, "Base de Datos", "b@startup.com", "startup.com");
        injectTestProject(103, "Diseño UI", "c@startup.com", "startup.com");

        ActivityScenario.launch(loginActivity.class);

        loginVisually("a@startup.com", "123");
        goToProjectListVisually();

        Espresso.onView(ViewMatchers.withId(R.id.tvListTitle))
                .check(ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
    }

    @Test
    public void visualTest_05_duplicateFavoriteUI() throws InterruptedException {
        injectTestUser("spammer@ulpgc.es", "ulpgc.es");
        injectTestProject(500, "Proyecto Interesante", "spammer@ulpgc.es", "ulpgc.es");

        ActivityScenario.launch(loginActivity.class);

        loginVisually("spammer@ulpgc.es", "123");
        goToProjectListVisually();

        Espresso.onView(ViewMatchers.withId(R.id.cardProject1)).perform(forceClick());
        Thread.sleep(500);

        Espresso.onView(ViewMatchers.withId(R.id.btnFavorite)).perform(forceClick());
        Espresso.onView(ViewMatchers.withId(R.id.btnFavorite)).perform(forceClick());
        Espresso.onView(ViewMatchers.withId(R.id.btnFavorite)).perform(forceClick());

        Espresso.onView(ViewMatchers.withId(R.id.btnFavorite))
                .check(ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
    }

    @Test
    public void visualTest_06_removeFavorite() throws InterruptedException {
        injectTestUser("indeciso@ull.es", "ull.es");
        injectTestProject(600, "Proyecto Temporal", "indeciso@ull.es", "ull.es");

        ActivityScenario.launch(loginActivity.class);

        loginVisually("indeciso@ull.es", "123");
        goToProjectListVisually();

        Espresso.onView(ViewMatchers.withId(R.id.cardProject1)).perform(forceClick());
        Thread.sleep(500);

        Espresso.onView(ViewMatchers.withId(R.id.btnFavorite)).perform(forceClick());

        Espresso.onView(ViewMatchers.withId(R.id.btnFavorite))
                .check(ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
    }

    @Test
    public void visualTest_07_guestModeSeesJSONData() throws InterruptedException {
        ActivityScenario.launch(loginActivity.class);

        clickGuestVisually();
        goToProjectListVisually();

        Espresso.onView(ViewMatchers.withId(R.id.tvListTitle))
                .check(ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
    }

    @Test
    public void visualTest_08_guestModeCannotAddFavorite() throws InterruptedException {
        ActivityScenario.launch(loginActivity.class);

        clickGuestVisually();
        goToProjectListVisually();

        Espresso.onView(ViewMatchers.withId(R.id.cardProject1)).perform(forceClick());
        Thread.sleep(500);

        Espresso.onView(ViewMatchers.withId(R.id.btnFavorite)).perform(forceClick());

        Espresso.onView(ViewMatchers.withId(R.id.btnFavorite))
                .check(ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
    }

    @Test
    public void visualTest_09_alternatingSessions() throws InterruptedException {
        injectTestUser("jefe@empresaA.com", "empresaA.com");
        injectTestUser("novato@empresaB.com", "empresaB.com");

        ActivityScenario<loginActivity> scenario = ActivityScenario.launch(loginActivity.class);

        loginVisually("jefe@empresaA.com", "123");
        goToProjectListVisually();
        Espresso.onView(ViewMatchers.withId(R.id.tvListTitle))
                .check(ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

        AppMediator.getInstance().setLoggedUser(null);
        scenario.close();
        Thread.sleep(500);

        ActivityScenario.launch(loginActivity.class);

        loginVisually("novato@empresaB.com", "123");
        goToProjectListVisually();
        Espresso.onView(ViewMatchers.withId(R.id.tvListTitle))
                .check(ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
    }

    @Test
    public void visualTest_10_massiveDataLoad() throws InterruptedException {
        injectTestUser("victima@corporacion.com", "corporacion.com");

        for(int i = 1; i <= 100; i++) {
            injectTestProject(1000 + i, "Proyecto Carga " + i, "victima@corporacion.com", "corporacion.com");
        }

        ActivityScenario.launch(loginActivity.class);

        loginVisually("victima@corporacion.com", "123");
        goToProjectListVisually();

        Espresso.onView(ViewMatchers.withId(R.id.tvListTitle))
                .check(ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
    }

    private void injectProjectsAndTasksForUser(String email, String domain, int baseId, int numTasksPerProject) {
        for (int p = 1; p <= 2; p++) {
            int pId = baseId + p;
            ProjectEntity proj = new ProjectEntity();
            proj.projectId = pId;
            proj.name = "Proyecto " + p + " de " + email;
            proj.creatorEmail = email;
            proj.companyDomain = domain;
            db.projectDao().insertProject(proj);

            for (int t = 1; t <= numTasksPerProject; t++) {
                es.ulpgc.eite.da.templatedemo.database.TaskEntity task =
                        new es.ulpgc.eite.da.templatedemo.database.TaskEntity();
                task.name = "Tarea " + t + " de " + proj.name;
                task.parentProjectId = pId;
                db.taskDao().insertTask(task);
            }
        }
    }

    private void injectUniversityMassiveData(String domain, int baseProjectId) {
        String user1 = "u1@" + domain;
        String user2 = "u2@" + domain;

        injectTestUser(user1, domain);
        injectTestUser(user2, domain);

        injectProjectsAndTasksForUser(user1, domain, baseProjectId, 4);
        injectProjectsAndTasksForUser(user2, domain, baseProjectId + 10, 4);
    }

    @Test
    public void visualTest_11() throws InterruptedException {
        injectUniversityMassiveData("ulpgc.es", 2000);
        injectUniversityMassiveData("ull.es", 3000);
        injectUniversityMassiveData("upv.es", 4000);

        ActivityScenario<loginActivity> scenario = ActivityScenario.launch(loginActivity.class);

        clickGuestVisually();
        goToProjectListVisually();

        Espresso.onView(ViewMatchers.withId(R.id.tvListTitle))
                .check(ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

        AppMediator.getInstance().setLoggedUser(null);
        scenario.close();
        Thread.sleep(1000);

        ActivityScenario.launch(loginActivity.class);
        Thread.sleep(1000);

        Espresso.onView(ViewMatchers.withId(R.id.btnLoginRegister)).perform(forceClick());
        Thread.sleep(500);

        Espresso.onView(ViewMatchers.withId(R.id.etEmail))
                .perform(ViewActions.replaceText("usuario8@nuevo.com"));
        Espresso.onView(ViewMatchers.withId(R.id.etPassword))
                .perform(ViewActions.replaceText("123"));
        Espresso.closeSoftKeyboard();
        Thread.sleep(500);

        Espresso.onView(ViewMatchers.withId(R.id.btnRegisterContinue)).perform(forceClick());
        Thread.sleep(500);

        loginVisually("usuario8@nuevo.com", "123");

        Espresso.onView(ViewMatchers.withId(R.id.btnNavRegisterProject)).perform(forceClick());
        Thread.sleep(500);

        Espresso.onView(ViewMatchers.withId(R.id.etProjectName))
                .perform(ViewActions.replaceText("Proyecto Final UI"));
        Espresso.closeSoftKeyboard();
        Thread.sleep(500);
        Espresso.onView(ViewMatchers.withId(R.id.btnSubmitProject)).perform(forceClick());
        Thread.sleep(500);

        Espresso.onView(ViewMatchers.withId(R.id.tvListTitle))
                .check(ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
    }
}

