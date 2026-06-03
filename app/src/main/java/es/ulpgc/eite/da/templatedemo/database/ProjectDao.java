package es.ulpgc.eite.da.templatedemo.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface ProjectDao {

    // Al insertar, devuelve el ID numérico que se acaba de generar
    @Insert
    long insertProject(ProjectEntity project);

    // EL FILTRO MÁGICO: Aísla los proyectos por empresa
    @Query("SELECT * FROM projects WHERE companyDomain = :domain")
    List<ProjectEntity> getProjectsByCompany(String domain);

    // Para cuando queramos ver el detalle de un proyecto concreto
    @Query("SELECT * FROM projects WHERE projectId = :id LIMIT 1")
    ProjectEntity getProjectById(int id);
}