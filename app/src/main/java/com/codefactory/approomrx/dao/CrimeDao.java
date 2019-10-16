package com.codefactory.approomrx.dao;

import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.codefactory.approomrx.data.Crime;

import java.util.List;

@Dao
public interface CrimeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Crime crime);

    @Update(onConflict = OnConflictStrategy.IGNORE)//@Query("UPDATE table_crimeType SET crime_typeName = :crime")
    int update(Crime crime);

    @Query("DELETE FROM table_crime")
    void deleteAll();

    @Query("SELECT * FROM table_crime WHERE id = :code")
    MutableLiveData<Crime> getCrimesById(int code);

    @Query("SELECT * FROM table_crime WHERE crime_typeName LIKE :crimeString OR crime_typeDescription LIKE :crimeString")
    MutableLiveData<List<Crime>> getCrimesByText(String crimeString);

    @Query("SELECT * FROM table_crime")
    MutableLiveData<List<Crime>> getCrimesAll();


}
