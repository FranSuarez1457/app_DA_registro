package es.ulpgc.eite.da.templatedemo.projectList;

import android.content.Context;
import java.util.ArrayList;
import java.util.List;

import es.ulpgc.eite.da.templatedemo.app.AppMediator;
import es.ulpgc.eite.da.templatedemo.database.AppDataBase;
import es.ulpgc.eite.da.templatedemo.database.ProjectEntity;
import es.ulpgc.eite.da.templatedemo.database.UserEntity;
import es.ulpgc.eite.da.templatedemo.database.FavoriteEntity; // IMPORTANTE

public class projectListModel implements projectListContract.Model {

    private AppDataBase db;

    public projectListModel(Context context) {
        db = AppDataBase.getInstance(context);
    }

    @Override
    public List<ProjectEntity> getProjectList() {
        UserEntity currentUser = AppMediator.getInstance().getLoggedUser();

        if (currentUser == null) {
            List<ProjectEntity> sampleList = new ArrayList<>();
            ProjectEntity p1 = new ProjectEntity();
            p1.name = "Proyecto de Demostración";
            sampleList.add(p1);
            return sampleList;
        } else {
            return db.projectDao().getProjectsByCompany(currentUser.companyDomain);
        }
    }

    // --- EL CEREBRO DEL FILTRO ---
    @Override
    public List<ProjectEntity> getFavoriteProjects() {
        UserEntity currentUser = AppMediator.getInstance().getLoggedUser();
        if (currentUser == null) return new ArrayList<>();

        // Buscamos los favoritos reales
        List<FavoriteEntity> favorites = db.favoriteDao().getFavoritesByUser(currentUser.email);
        List<ProjectEntity> filteredProjects = new ArrayList<>();

        // Extraemos los nombres y los empaquetamos como proyectos para no romper la pantalla
        for(FavoriteEntity fav : favorites) {
            ProjectEntity project = new ProjectEntity();
            project.name = fav.projectName;
            filteredProjects.add(project);
        }

        return filteredProjects;
    }
}