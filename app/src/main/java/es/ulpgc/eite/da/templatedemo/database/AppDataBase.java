package es.ulpgc.eite.da.templatedemo.database;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = {UserEntity.class, ProjectEntity.class, TaskEntity.class, FavoriteEntity.class}, version = 1, exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {


    public abstract UserDao userDao();
    public abstract ProjectDao projectDao();
    public abstract TaskDao taskDao();
    public abstract FavoriteDao favoriteDao();

    private static volatile AppDataBase INSTANCE;

    public static AppDataBase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDataBase.class) {
                if (INSTANCE == null) {

                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDataBase.class, "empresa_database")
                            .fallbackToDestructiveMigration()
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
