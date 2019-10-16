package com.codefactory.approomrx.data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "table_crime")
public class Crime {

    @PrimaryKey
    @NonNull
    private String id;

    @NonNull
    @ColumnInfo(name = "crime_name")
    private String crimeTypeName;

    @NonNull
    @ColumnInfo(name = "crime_tdescription")
    private String crimeTypeDescription;


    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
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
