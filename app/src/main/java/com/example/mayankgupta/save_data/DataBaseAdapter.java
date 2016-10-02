package com.example.mayankgupta.save_data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.sql.ResultSet;

/**
 * Created by mayankgupta on 30/09/2016.
 */
public class DataBaseAdapter {
    static final String DATABASE_NAME = "SaveData.db";
    static final int DATABASE_VERSION = 1;
    public static final int NAME_COLUMN = 1;
    static final String DATABASE_CREATE = "create table " + "UserData" + "( "
            + "Rollno" + " text primary key ,"
            + "Name  text,Email text); ";
    public SQLiteDatabase db;
    private final Context context;
    private DataBaseHelper dbHelper;
    public DataBaseAdapter(Context _context) {
        context = _context;
        dbHelper = new DataBaseHelper(context, DATABASE_NAME, null,
                DATABASE_VERSION);
    }
    public DataBaseAdapter open() throws SQLException {
        db = dbHelper.getWritableDatabase();
        return this;
    }
    public void close() {
        db.close();
    }
    public SQLiteDatabase getDatabaseInstance() {
        return db;
    }

    public void insertEntry(String Name, String email,String rollno) {
        ContentValues newValues = new ContentValues();
        newValues.put("Rollno",rollno);
        newValues.put("Name", Name);
        newValues.put("Email", email);

        db.insert("UserData", null, newValues);

    }
    public int deleteEntry(String Rollno) {

        String where = "Rollno=?";
        int numberOFEntriesDeleted = db.delete("UserData", where,
                new String[] { Rollno });

        return numberOFEntriesDeleted;
    }
    public Cursor getData(String Rollno)
    {
        Cursor cursor = db.query("UserData", null, " Rollno=?",
                new String[] { Rollno }, null, null, null);
        cursor.moveToFirst();
        return cursor;
    }
    public void updateEntry(String Name, String email,String rollno) {
        ContentValues updatedValues = new ContentValues();
        updatedValues.put("Name", Name);
        updatedValues.put("Email", email);

        String where = "Rollno = ?";
        db.update("UserData", updatedValues, where, new String[] { rollno });
    }
}
