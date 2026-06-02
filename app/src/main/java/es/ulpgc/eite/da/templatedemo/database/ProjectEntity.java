package es.ulpgc.eite.da.templatedemo.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "projects")
public class ProjectEntity {

    @PrimaryKey(autoGenerate = true)
    public int projectId;

    public String name;
    public String description;
    public String startDate;

    public String companyDomain;

    public String creatorEmail;
}