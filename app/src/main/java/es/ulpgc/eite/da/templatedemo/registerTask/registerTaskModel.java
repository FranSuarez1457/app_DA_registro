package es.ulpgc.eite.da.templatedemo.registerTask;

public class registerTaskModel implements registerTaskContract.Model {

    @Override
    public boolean registerNewTask(String project, String taskName, String responsible) {
        if (taskName == null || taskName.trim().isEmpty()) {
            return false;
        }
        return true;
    }
}