package com.codefactory.approomrx.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.codefactory.approomrx.data.Judgment;
import com.codefactory.approomrx.data.JudgmentCrimeAndPerson;

import java.util.Date;
import java.util.List;

@Dao
public interface JudgmentDao {

    @Query("SELECT * From table_judgment")
    LiveData<List<Judgment>> findAllLoans(); //TODO: od MutableLiveData?

    @Query("SELECT table_judgment.id, table_person_bad_guy.person_name, table_crime.crime_name, table_judgment.startTime, table_judgment.endTime " +
            "From table_judgment " +
            "INNER JOIN table_crime ON table_judgment.crime_id = table_crime.id " +
            "INNER JOIN table_person_bad_guy ON table_judgment.person_id = table_person_bad_guy.id ")
    LiveData<List<JudgmentCrimeAndPerson>> findAllWithUserAndBook();

    @Query("SELECT table_judgment.id, table_person_bad_guy.person_name as title, table_crime.crime_name as name, table_judgment.startTime, table_judgment.endTime " +
            "FROM table_crime " +
            "INNER JOIN table_judgment ON table_judgment.crime_id = table_crime.id " +
            "INNER JOIN table_person_bad_guy on table_person_bad_guy.id = table_judgment.person_id " +
            "WHERE table_person_bad_guy.person_name LIKE :userName " +
                "AND table_judgment.endTime > :after "
    )
    LiveData<List<JudgmentCrimeAndPerson>> findLoansByNameAfter(String userName, Date after);

    @Insert()
    void insertJudgment(Judgment judgment);

    @Query("DELETE FROM table_judgment")
    void deleteAll();
}
