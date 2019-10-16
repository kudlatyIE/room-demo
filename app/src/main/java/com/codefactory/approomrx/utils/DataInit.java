package com.codefactory.approomrx.utils;

import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;

import com.codefactory.approomrx.data.AppDataBase;
import com.codefactory.approomrx.data.Crime;
import com.codefactory.approomrx.data.Judgment;
import com.codefactory.approomrx.data.Person;

import java.util.Date;

public class DataInit {

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


        try {
            //TODO: create crime, persons and jugd objects
            db.crimeDAO().insert(new Crime());
            db.personDAO().insert(new Person());
            Thread.sleep(DELAY_MILLIS);
            db.judgmentDao().insertJudgment(new Judgment());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    https://codelabs.developers.google.com/codelabs/android-persistence/index.html?index=..%2F..%2Findex#3


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
