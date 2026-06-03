package es.ulpgc.eite.da.templatedemo.projectDetail;

import android.content.Context;
import es.ulpgc.eite.da.templatedemo.app.AppMediator;
import es.ulpgc.eite.da.templatedemo.database.AppDataBase;
import es.ulpgc.eite.da.templatedemo.database.FavoriteEntity;
import es.ulpgc.eite.da.templatedemo.database.UserEntity;

public class projectDetailModel implements projectDetailContract.Model {

    private AppDataBase db;

    public projectDetailModel(Context context) {
        db = AppDataBase.getInstance(context);
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