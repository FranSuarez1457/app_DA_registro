package es.ulpgc.eite.da.templatedemo.registerUser;

import android.content.Context;
import es.ulpgc.eite.da.templatedemo.database.AppDatabase;
import es.ulpgc.eite.da.templatedemo.database.UserEntity;

public class registerUserModel implements registerUserContract.Model {

    private AppDatabase db;

    public registerUserModel(Context context) {
        // Inicializamos la conexión a la base de datos
        db = AppDatabase.getInstance(context);
    }

    @Override
    public boolean registerNewUser(String email, String password) {
        // Comprobación de seguridad básica
        if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
            return false;
        }

        UserEntity newUser = new UserEntity();
        newUser.email = email;
        newUser.password = password;

        // Extraemos el dominio para saber de qué empresa es (ej: "ulpgc.es")
        if (email.contains("@")) {
            newUser.companyDomain = email.split("@")[1];
        } else {
            newUser.companyDomain = "independiente";
        }

        // Insertamos el usuario en la tabla de Room
        db.userDao().insertUser(newUser);

        return true;
    }
}