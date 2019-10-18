package com.codefactory.approomrx.utils;

import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;

import com.codefactory.approomrx.data.AppDataBase;
import com.codefactory.approomrx.data.Crime;
import com.codefactory.approomrx.data.Judgment;
import com.codefactory.approomrx.data.Person;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class DataInit {

    private static final String TAG = DataInit.class.getSimpleName();

    private static final int DELAY_MILLIS = 500;

    public static void populateAsync(final AppDataBase db) {

        PopulateDbAsync task = new PopulateDbAsync(db);
        task.execute();
    }

    public static void populateSync(@NonNull final AppDataBase db) {
        populateWithTestData(db);
    }

    private static void populateWithTestData(AppDataBase db) {
        db.crimeDAO().deleteAll();
        db.personDAO().deleteAll();
        db.judgmentDao().deleteAll();
        ArrayList<Judgment> list = new ArrayList<>();


        try {
            //TODO: create crime, persons and jugd objects
            Crime crime1 = addCrime(db, "bad stuff", "very, very bad stuff");
            Crime crime2 = addCrime(db, "murder", "the worst stuff ever");
            Crime crime3 = addCrime(db, "corruption", "very bad, almost as bad as murder");
            Crime crime4 = addCrime(db, "vandalism", "medium level of crime");
            Crime crime5 = addCrime(db, "communism", "the worst stuff ever, even worse than murder");

            Person person1 = addPerson(db, "Donald", crime3);
            Person person2 = addPerson(db,"Julius", crime1);
            Person person3 = addPerson(db, "Ann", crime1);
            Person person4 = addPerson(db, "Janusz", crime2);

//            db.crimeDAO().insert(new Crime());
//            db.personDAO().insert(new Person());
            long inserted;
            Thread.sleep(DELAY_MILLIS);
            inserted = addJudgment(db, person1, crime3, getTodayPlusDays(-670), getTodayPlusDays(-227));
            Log.d(TAG, "inserted judgment >> "+inserted+", Person: "+person1.getName()+" crime: "+crime3.getCrimeTypeName());

            Thread.sleep(DELAY_MILLIS);
            inserted = addJudgment(db, person2, crime1, getTodayPlusDays(-10), getTodayPlusDays(127));
            Log.d(TAG, "inserted judgment >> "+inserted+", Person: "+person2.getName()+" crime: "+crime1.getCrimeTypeName());

            Thread.sleep(DELAY_MILLIS);
            inserted = addJudgment(db, person3, crime4, getTodayPlusDays(-30), getTodayPlusDays(-7));
            Log.d(TAG, "inserted judgment >> "+inserted+", Person: "+person3.getName()+" crime: "+crime4.getCrimeTypeName());

            Thread.sleep(DELAY_MILLIS);
            inserted = addJudgment(db, person4, crime1, getTodayPlusDays(-5), getTodayPlusDays(150));
            Log.d(TAG, "inserted judgment >> "+inserted+", Person: "+person4.getName()+" crime: "+crime1.getCrimeTypeName());

            Thread.sleep(DELAY_MILLIS);
            inserted = addJudgment(db, person1, crime5, getTodayPlusDays(-200), getTodayPlusDays(327));
            Log.d(TAG, "inserted judgment >> "+inserted+", Person: "+person1.getName()+" crime: "+crime5.getCrimeTypeName());

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private static long addJudgment(AppDataBase db, Person person, Crime crime, Date from, Date to){
        Judgment judgment = new Judgment();
        judgment.setPersonId(person.getId());
        judgment.setCrimeId(crime.getId());
        judgment.setStartTime(from);
        judgment.setEndTime(to);
        return db.judgmentDao().insertJudgment(judgment);
    }

    private static Crime addCrime(AppDataBase db, String name, String description){
        Crime crime = new Crime();
        crime.setCrimeTypeName(name);
        crime.setCrimeTypeDescription(description);
        db.crimeDAO().insert(crime);
        return crime;
    }

    private static Person addPerson(AppDataBase db, String name, Crime crime){
        Person person = new Person();
        person.setName(name);
        person.setCrimeTypeId(crime.getId());
        db.personDAO().insert(person);
        Log.d(TAG, "inserted person >> name: "+name+", ID: "+person.getId());
        return person;
    }

    private static Date getTodayPlusDays(int daysAgo) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, daysAgo);
        return calendar.getTime();
    }

//    https://codelabs.developers.google.com/codelabs/android-persistence/index.html?index=..%2F..%2Findex#3


    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final AppDataBase mDb;

        PopulateDbAsync(AppDataBase db) {
            mDb = db;
        }

        @Override
        protected Void doInBackground(final Void... params) {
            populateWithTestData(mDb);
            return null;
        }

    }
}
