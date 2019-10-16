package com.codefactory.approomrx.data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "table_person_bad_guy")
public class Person {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    private int id;

    @NonNull
    @ColumnInfo(name = "person_name")
    private String name;

    @NonNull
    @ColumnInfo(name = "crime_type_id")
    int crimeTypeId;
}
