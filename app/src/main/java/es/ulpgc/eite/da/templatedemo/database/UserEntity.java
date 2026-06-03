package es.ulpgc.eite.da.templatedemo.database;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "users")
public class UserEntity {

    @PrimaryKey
    @NonNull
    public String email = "";

    public String password;

    public String companyDomain;
}