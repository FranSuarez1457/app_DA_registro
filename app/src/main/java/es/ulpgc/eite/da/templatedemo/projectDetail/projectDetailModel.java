package es.ulpgc.eite.da.templatedemo.projectDetail;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import es.ulpgc.eite.da.templatedemo.app.AppMediator;
import es.ulpgc.eite.da.templatedemo.database.AppDataBase;
import es.ulpgc.eite.da.templatedemo.database.FavoriteEntity;
import es.ulpgc.eite.da.templatedemo.database.ProjectEntity;
import es.ulpgc.eite.da.templatedemo.database.TaskEntity;
import es.ulpgc.eite.da.templatedemo.database.UserEntity;

public class projectDetailModel implements projectDetailContract.Model {

    private AppDataBase db;

    public projectDetailModel(Context context) {
        db = AppDataBase.getInstance(context);
    }

    @Override
    public ProjectEntity getProjectByName(String name) { // Esto ya no dará error
        return db.projectDao().getProjectByName(name);
    }

    @Override
    public List<String> getTaskNamesByProjectId(int projectId) {
        // Consultamos las tareas en la BBDD filtrando por el ID del proyecto
        List<TaskEntity> tasks = db.taskDao().getTasksByProjectId(projectId);
        List<String> names = new ArrayList<>();
        for (TaskEntity task : tasks) {
            names.add(task.name);
        }
        return names;
    }

    @Override
    public boolean addToFavorites() {
        UserEntity currentUser = AppMediator.getInstance().getLoggedUser();

        if (currentUser == null) return false;

        FavoriteEntity newFavorite = new FavoriteEntity();
        newFavorite.userEmail = currentUser.email;
        newFavorite.projectId = 1;

        db.favoriteDao().insertFavorite(newFavorite);

        return true;
    }
}