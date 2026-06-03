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

        if (password.length() < 2) {
            return false;
        }

        if (!email.contains("@") || email.contains("<") || email.contains(">")) {
            return false;
        }

        if (db.userDao().getUserByEmail(email) != null) {
            return false;
        }

        UserEntity newUser = new UserEntity();
        newUser.email = email;
        newUser.password = password;
        newUser.companyDomain = email.split("@")[1];
        db.userDao().insertUser(newUser);

        return true;
    }
}