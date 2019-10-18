package com.codefactory.approomrx.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.TypeConverters;

import com.codefactory.approomrx.data.Judgment;
import com.codefactory.approomrx.data.JudgmentCrimeAndPerson;
import com.codefactory.approomrx.utils.DateConverter;

import java.util.Date;
import java.util.List;

@Dao
@TypeConverters(DateConverter.class)
public interface JudgmentDao {

    @Query("SELECT * From table_judgment")
    LiveData<List<Judgment>> findAllJudgment(); //TODO: od MutableLiveData?

    @Query("SELECT table_judgment.id, table_person.person_name, table_crime.crime_name, table_judgment.startTime, table_judgment.endTime " +
            "From table_judgment " +
            "INNER JOIN table_crime ON table_judgment.crime_id = table_crime.id " +
            "INNER JOIN table_person ON table_judgment.person_id = table_person.id ")
    LiveData<List<JudgmentCrimeAndPerson>> findAllWithPersonAndCrime();

    @Query("SELECT table_judgment.id, table_person.person_name as crimeName, table_crime.crime_name as personName, table_judgment.startTime, table_judgment.endTime " +
            "FROM table_crime " +
            "INNER JOIN table_judgment ON table_judgment.crime_id = table_crime.id " +
            "INNER JOIN table_person on table_person.id = table_judgment.person_id " +
            "WHERE table_person.person_name LIKE :userName " +
                "AND table_judgment.endTime > :after "
    )
    LiveData<List<JudgmentCrimeAndPerson>> findJudgmentByNameAfter(String userName, Date after);

    @Insert()
    long insertJudgment(Judgment judgment);

    @Query("DELETE FROM table_judgment")
    void deleteAll();
}
