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

    @Override
    public List<ProjectEntity> getFavoriteProjects() {
        UserEntity currentUser = AppMediator.getInstance().getLoggedUser();
        if (currentUser == null) return new ArrayList<>();

        return db.favoriteDao().getFavoriteProjectsForUser(currentUser.email);
    }
}