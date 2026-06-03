package es.ulpgc.eite.da.templatedemo.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import java.util.List;

@Dao
public interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertFavorite(FavoriteEntity favorite);

    @Query("DELETE FROM favorites WHERE userEmail = :email AND projectId = :projectId")
    void removeFavorite(String email, int projectId);

    @Query("SELECT EXISTS(SELECT 1 FROM favorites WHERE userEmail = :email AND projectId = :projectId)")
    boolean isFavorite(String email, int projectId);

    @Query("SELECT p.* FROM projects p INNER JOIN favorites f ON p.projectId = f.projectId WHERE f.userEmail = :email")
    List<ProjectEntity> getFavoriteProjectsForUser(String email);

    @Query("SELECT * FROM favorites WHERE userEmail = :email")
    List<FavoriteEntity> getFavoritesByUser(String email);
}