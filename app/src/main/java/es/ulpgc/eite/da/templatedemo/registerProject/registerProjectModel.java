package es.ulpgc.eite.da.templatedemo.registerProject;

import android.content.Context;
import es.ulpgc.eite.da.templatedemo.app.AppMediator;
import es.ulpgc.eite.da.templatedemo.database.AppDataBase;
import es.ulpgc.eite.da.templatedemo.database.ProjectEntity;
import es.ulpgc.eite.da.templatedemo.database.UserEntity;

public class registerProjectModel implements registerProjectContract.Model {

    private AppDataBase db;

    public registerProjectModel(Context context) {
        db = AppDataBase.getInstance(context);
    }

    @Override
    public boolean saveProject(String name, String description) {
        if (name == null || name.trim().isEmpty()) {
            return false;
        }

        UserEntity currentUser = AppMediator.getInstance().getLoggedUser();
        if (currentUser == null) {
            return false;
        }

        ProjectEntity newProject = new ProjectEntity();
        newProject.name = name;
        newProject.description = description;

        newProject.companyDomain = currentUser.companyDomain;
        newProject.creatorEmail = currentUser.email;

        db.projectDao().insertProject(newProject);

        return true;
    }
}