package es.ulpgc.eite.da.templatedemo.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import java.util.List;
import es.ulpgc.eite.da.templatedemo.database.*;

@Dao
public interface FavoriteDao {

    // Guarda un favorito (Si ya estaba, lo ignora)
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertFavorite(FavoriteEntity favorite);

    // Elimina un favorito
    @Query("DELETE FROM favorites WHERE userEmail = :email AND projectId = :projectId")
    void removeFavorite(String email, int projectId);

    // Comprueba si un proyecto ya es favorito para pintar la estrella encendida o apagada
    @Query("SELECT EXISTS(SELECT 1 FROM favorites WHERE userEmail = :email AND projectId = :projectId)")
    boolean isFavorite(String email, int projectId);

    // ¡Para la futura pantalla de Favoritos! Cruza las tablas para sacar los datos del proyecto
    @Query("SELECT p.* FROM projects p INNER JOIN favorites f ON p.projectId = f.projectId WHERE f.userEmail = :email")
    List<ProjectEntity> getFavoriteProjectsForUser(String email);
    @Query("SELECT * FROM favorites WHERE userEmail = :email")
    List<FavoriteEntity> getFavoritesByUser(String email);
}
