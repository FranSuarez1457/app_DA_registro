package es.ulpgc.eite.da.templatedemo.registerTask;

public class registerTaskModel implements registerTaskContract.Model {

    @Override
    public boolean registerNewTask(String project, String taskName, String responsible) {
        // Validación simulada: El nombre de la tarea no puede estar vacío
        if (taskName == null || taskName.trim().isEmpty()) {
            return false;
        }
        return true;
    }
}
