package es.ulpgc.eite.da.templatedemo.registerTask;

import android.content.Context;
import es.ulpgc.eite.da.templatedemo.database.AppDataBase;
import es.ulpgc.eite.da.templatedemo.database.ProjectEntity;
import es.ulpgc.eite.da.templatedemo.database.TaskEntity;

public class registerTaskModel implements registerTaskContract.Model {

    private AppDataBase db;

    // Necesitamos el contexto para abrir la base de datos
    public registerTaskModel(Context context) {
        db = AppDataBase.getInstance(context);
    }

    @Override
    public boolean registerNewTask(String project, String taskName, String responsible) {
        if (taskName == null || taskName.trim().isEmpty()) {
            return false;
        }

        ProjectEntity parentProject = db.projectDao().getProjectByName(project);

        if (parentProject == null) {
            return false;
        }

        TaskEntity newTask = new TaskEntity();
        newTask.name = taskName;
        newTask.responsible = responsible;

        newTask.parentProjectId = parentProject.projectId;

        db.taskDao().insertTask(newTask);

        return true;
    }
}