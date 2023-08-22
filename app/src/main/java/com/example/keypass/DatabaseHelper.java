package com.example.keypass;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "credentials.db";
    private static final int DATABASE_VERSION = 1;

    // Define table and column names
    public static final String TABLE_CREDENTIALS = "credentials";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_WEBSITE = "website";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASSWORD = "password";

    // Create table query
    private static final String DATABASE_CREATE =
            "CREATE TABLE " + TABLE_CREDENTIALS + "(" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_WEBSITE + " TEXT NOT NULL, " +
                    COLUMN_USERNAME + " TEXT NOT NULL, " +
                    COLUMN_PASSWORD + " TEXT NOT NULL);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Handle database upgrades if needed
    }

    public long insertCredential(String website, String username, String password) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_WEBSITE, website);
        values.put(DatabaseHelper.COLUMN_USERNAME, username);
        values.put(DatabaseHelper.COLUMN_PASSWORD, password);

        SQLiteDatabase db = this.getWritableDatabase();
        return db.insert(DatabaseHelper.TABLE_CREDENTIALS, null, values);
    }

    public List<Credential> getAllCredentials() {
        List<Credential> credentialsList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(DatabaseHelper.TABLE_CREDENTIALS,
                null, null, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Credential credential = new Credential();
                credential.setId(cursor.getLong(cursor.getColumnIndex(DatabaseHelper.COLUMN_ID)));
                credential.setWebsite(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_WEBSITE)));
                credential.setUsername(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_USERNAME)));
                credential.setPassword(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_PASSWORD)));

                credentialsList.add(credential);
                cursor.moveToNext();
            }
            cursor.close();
        }

        return credentialsList;
    }

}
