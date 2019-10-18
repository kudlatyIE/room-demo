package com.codefactory.approomrx.data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.codefactory.approomrx.utils.DateConverter;

import java.util.Date;

@Entity(tableName = "table_judgment",
        foreignKeys = {
        @ForeignKey(entity = Person.class,
                parentColumns = "id",
                childColumns = "person_id"),

        @ForeignKey(entity = Crime.class,
                parentColumns = "id",
                childColumns = "crime_id")})
@TypeConverters(DateConverter.class)
public class Judgment {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;

    private Date startTime;

    private Date endTime;

    @ColumnInfo(name="person_id")
    private long personId;

    @ColumnInfo(name="crime_id")
    private int crimeId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public long getPersonId() {
        return personId;
    }

    public void setPersonId(long personId) {
        this.personId = personId;
    }

    public int getCrimeId() {
        return crimeId;
    }

    public void setCrimeId(int crimeId) {
        this.crimeId = crimeId;
    }
}
