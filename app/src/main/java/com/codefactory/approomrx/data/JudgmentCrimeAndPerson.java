package com.codefactory.approomrx.data;

import androidx.room.ColumnInfo;
import androidx.room.TypeConverters;

import com.codefactory.approomrx.utils.DateConverter;

import java.util.Date;

public class JudgmentCrimeAndPerson {

    private int id;

    @ColumnInfo(name="crimeName")
    private String crimeName;

    @ColumnInfo(name="personName")
    private String personName;

    @TypeConverters(DateConverter.class)
    private Date startTime;

    @TypeConverters(DateConverter.class)
    private Date endTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCrimeName() {
        return crimeName;
    }

    public void setCrimeName(String crimeName) {
        this.crimeName = crimeName;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
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
}
