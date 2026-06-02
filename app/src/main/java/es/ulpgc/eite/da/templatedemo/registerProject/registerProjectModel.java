package es.ulpgc.eite.da.templatedemo.registerProject;

public class registerProjectModel implements registerProjectContract.Model {

    @Override
    public boolean registerNewProject(String name, String description, String date) {
        // Validación súper básica de simulacro: Si el nombre está vacío, falla. Si tiene texto, acierta.
        if (name == null || name.trim().isEmpty()) {
            return false;
        }
        return true;
    }
}
