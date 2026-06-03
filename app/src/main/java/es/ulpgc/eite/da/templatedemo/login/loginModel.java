package es.ulpgc.eite.da.templatedemo.login;

import android.content.Context;
import es.ulpgc.eite.da.templatedemo.app.AppMediator;
import es.ulpgc.eite.da.templatedemo.database.AppDataBase;
import es.ulpgc.eite.da.templatedemo.database.UserEntity;

public class loginModel implements loginContract.Model {

    private AppDataBase db;

    public loginModel(Context context) {
        db = AppDataBase.getInstance(context);
    }

    @Override
    public boolean loginUser(String email, String password) {
        if (email == null || password == null) return false;

        UserEntity user = db.userDao().login(email, password);

        if (user != null) {
            AppMediator.getInstance().setLoggedUser(user);
            return true;
        }

        return false;
    }

    @Override
    public void loginGuest() {
        AppMediator.getInstance().setLoggedUser(null);
    }
}