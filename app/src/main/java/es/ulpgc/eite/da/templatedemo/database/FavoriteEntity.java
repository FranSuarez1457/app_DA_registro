package es.ulpgc.eite.da.templatedemo.database;

import androidx.annotation.NonNull;
import androidx.room.Entity;

@Entity(tableName = "favorites", primaryKeys = {"userEmail", "projectId"})
public class FavoriteEntity {

    @NonNull
    public String userEmail = "";

    public int projectId;
}
