package com.codefactory.approomrx.data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "table_crime")
public class Crime {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @NonNull
    @ColumnInfo(name = "crime_name")
    private String crimeTypeName;

    @NonNull
    @ColumnInfo(name = "crime_description")
    private String crimeTypeDescription;


    @NonNull
    public int getId() {
        return id;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }

    @NonNull
    public String getCrimeTypeName() {
        return crimeTypeName;
    }

    public void setCrimeTypeName(@NonNull String crimeTypeName) {
        this.crimeTypeName = crimeTypeName;
    }

    @NonNull
    public String getCrimeTypeDescription() {
        return crimeTypeDescription;
    }

    public void setCrimeTypeDescription(@NonNull String crimeTypeDescription) {
        this.crimeTypeDescription = crimeTypeDescription;
    }
}
