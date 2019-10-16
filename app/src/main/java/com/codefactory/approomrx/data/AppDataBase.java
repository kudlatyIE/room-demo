package com.codefactory.approomrx.data;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.codefactory.approomrx.dao.CrimeDao;
import com.codefactory.approomrx.dao.JudgmentDao;
import com.codefactory.approomrx.dao.PersonDao;

@Database(entities = {Crime.class, Person.class, Judgment.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {

    public abstract CrimeDao crimeDAO();
    public abstract PersonDao personDAO();
    public abstract JudgmentDao judgmentDao();

    private static volatile  AppDataBase INSTANCE;

    static AppDataBase getDataBase(Context context){
        if (INSTANCE==null){
            synchronized (AppDataBase.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDataBase.class, "crime_db")
//                            .addMigrations(MIGRATION_1_2, MIGRATION_2_3, MIGRATION_3_4)
                            .allowMainThreadQueries()// don't allow queries on the main thread - just temporary
                            .build();
                }
            }
        }
        return INSTANCE;
    }
    @NonNull
    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }

    @NonNull
    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }

    @Override
    public void clearAllTables() {

    }

    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE table_badGuy "
                    + " ADD COLUMN last_update INTEGER");
        }
    };
    //TODO: migration with copy data
    static final Migration MIGRATION_1_4 = new Migration(1, 4) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            // Create the new table
            database.execSQL(
                    "CREATE TABLE table_temp (someId INTEGER, name TEXT, last_update INTEGER, PRIMARY KEY(someId))");

            // Copy the data
            database.execSQL(
                    "INSERT INTO table_temp (someId, name, last_update) SELECT someId, name, last_update FROM table_old");
            // Remove the old table
            database.execSQL("DROP TABLE table_old");
            // Change the table name to the correct one
            database.execSQL("ALTER TABLE table_temp RENAME TO table_old");
        }
    };
}
