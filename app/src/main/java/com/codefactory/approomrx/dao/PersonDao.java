package com.codefactory.approomrx.dao;

import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.codefactory.approomrx.data.Person;

import java.util.List;

@Dao
public interface PersonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Person badGuy);

    @Update(onConflict = OnConflictStrategy.IGNORE)
    int update(Person badGuy);

    @Query("DELETE FROM table_person_bad_guy")
    void deleteAll();

    @Query("DELETE FROM table_person_bad_guy WHERE id = :code")
    void deleteBadGuyByCode(int code);

    @Delete
    void deleteBadGuys( Person... badGuys);

    @Query("SELECT * FROM table_person_bad_guy")
    MutableLiveData<List<Person>> getBadGuyAll();

//    @Query("SELECT * FROM table_person_bad_guy "
//            + "INNER JOIN table_crimeType_join "
//            +" ON table_badGuy.crime_area_id = table_crimeType_join.crimeId ")
//    MutableLiveData<List<BadGuy>> getBadGuyAllByCrime();

    @Query("SELECT * FROM table_person_bad_guy WHERE id = :code")
    MutableLiveData<Person> getBadGuyByCode(int code);

}
