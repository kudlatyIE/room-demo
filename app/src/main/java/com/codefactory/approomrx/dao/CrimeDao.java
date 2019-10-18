package com.codefactory.approomrx.dao;

import androidx.lifecycle.LiveData;
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
    long insert(Crime crime);

    @Update(onConflict = OnConflictStrategy.IGNORE)//@Query("UPDATE table_crimeType SET crime_typeName = :crime")
    int update(Crime crime);

    @Query("DELETE FROM table_crime")
    void deleteAll();

    @Query("SELECT * FROM table_crime WHERE id = :code")
    LiveData<Crime> getCrimesById(int code);

    @Query("SELECT * FROM table_crime WHERE crime_name LIKE :crimeString OR crime_description LIKE :crimeString")
    LiveData<List<Crime>> getCrimesByText(String crimeString);

    @Query("SELECT * FROM table_crime")
    LiveData<List<Crime>> getCrimesAll();


}
