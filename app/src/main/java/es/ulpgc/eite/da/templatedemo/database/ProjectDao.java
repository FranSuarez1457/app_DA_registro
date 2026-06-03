package es.ulpgc.eite.da.templatedemo.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface ProjectDao {

    @Insert
    long insertProject(ProjectEntity project);

    @Query("SELECT * FROM projects WHERE companyDomain = :domain")
    List<ProjectEntity> getProjectsByCompany(String domain);

    @Query("SELECT * FROM projects WHERE projectId = :id LIMIT 1")
    ProjectEntity getProjectById(int id);
}