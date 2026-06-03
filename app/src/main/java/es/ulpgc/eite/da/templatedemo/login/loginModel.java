package es.ulpgc.eite.da.templatedemo.login;

import android.content.Context;
import es.ulpgc.eite.da.templatedemo.app.AppMediator;
import es.ulpgc.eite.da.templatedemo.database.AppDataBase;
import es.ulpgc.eite.da.templatedemo.database.UserEntity;

public class loginModel implements loginContract.Model {

    private AppDataBase db;
    private Context context;

    public loginModel(Context context) {
        this.context = context;
        this.db = AppDataBase.getInstance(context);
    }

    @Override
    public boolean loginUser(String email, String password) {
        if (email == null || password == null) return false;

        UserEntity user = db.userDao().login(email, password);

        if (user != null) {
            AppMediator.getInstance().setLoggedUser(user);
            AppMediator.getInstance().saveSession(context, user);
            return true;
        }

        return false;
    }

    @Override
    public void loginGuest() {
        AppMediator.getInstance().setLoggedUser(null);
        AppMediator.getInstance().saveSession(context, null);
    }

    @Override
    public boolean userExists(String email) {
        return db.userDao().getUserByEmail(email) != null;
    }
}