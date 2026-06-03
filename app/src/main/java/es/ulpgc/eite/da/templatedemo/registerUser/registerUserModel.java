package es.ulpgc.eite.da.templatedemo.registerUser;

import android.content.Context;
import es.ulpgc.eite.da.templatedemo.database.AppDataBase;
import es.ulpgc.eite.da.templatedemo.database.UserEntity;

public class registerUserModel implements registerUserContract.Model {

    private AppDataBase db;

    public registerUserModel(Context context) {
        db = AppDataBase.getInstance(context);
    }

    @Override
    public boolean registerNewUser(String email, String password) {
        if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
            return false;
        }

        UserEntity existente = db.userDao().getUserByEmail(email);

        if (existente != null) {
            return false;
        }

        UserEntity newUser = new UserEntity();
        newUser.email = email;
        newUser.password = password;
        newUser.companyDomain = email.contains("@") ? email.split("@")[1] : "independiente";

        db.userDao().insertUser(newUser);

        return true;
    }
}