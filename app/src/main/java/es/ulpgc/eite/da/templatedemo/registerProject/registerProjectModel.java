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
        // 1. Validamos que no esté vacío
        if (name == null || name.trim().isEmpty()) {
            return false;
        }

        // 2. Rescatamos quién es el usuario que está usando la app ahora mismo
        UserEntity currentUser = AppMediator.getInstance().getLoggedUser();
        if (currentUser == null) {
            return false; // Seguridad extra por si se coló un invitado
        }

        // 3. Creamos el objeto de la base de datos
        ProjectEntity newProject = new ProjectEntity();
        newProject.name = name;
        newProject.description = description;

        // 4. ¡LA CLAVE DEL AISLAMIENTO! Le asignamos el dominio del usuario
        newProject.companyDomain = currentUser.companyDomain;
        newProject.creatorEmail = currentUser.email;

        // 5. Lo guardamos en Room
        db.projectDao().insertProject(newProject);

        return true;
    }
}
