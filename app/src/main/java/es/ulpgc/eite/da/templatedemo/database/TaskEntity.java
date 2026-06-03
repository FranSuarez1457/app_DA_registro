package es.ulpgc.eite.da.templatedemo.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tasks")
public class TaskEntity {

    @PrimaryKey(autoGenerate = true)
    public int taskId;

    public int parentProjectId;

    public String name;
    public String responsible;
}