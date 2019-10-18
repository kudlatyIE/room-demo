package com.codefactory.approomrx.data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "table_person")
public class Person {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;

    @NonNull
    @ColumnInfo(name = "person_name")
    private String name;

    @NonNull
    @ColumnInfo(name = "crime_type_id")
    long crimeTypeId;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public long getCrimeTypeId() {
        return crimeTypeId;
    }

    public void setCrimeTypeId(long crimeTypeId) {
        this.crimeTypeId = crimeTypeId;
    }
}
