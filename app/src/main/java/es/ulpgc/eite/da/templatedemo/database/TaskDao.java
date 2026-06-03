package es.ulpgc.eite.da.templatedemo.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface TaskDao {

    @Insert
    void insertTask(TaskEntity task);

    // Saca solo las tareas que correspondan al proyecto que estamos viendo
    @Query("SELECT * FROM tasks WHERE parentProjectId = :projectId")
    List<TaskEntity> getTasksForProject(int projectId);
}