package com.example.lab10_2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class DbHandler extends SQLiteOpenHelper {

    private static final int DB_VERSION = 2;
    private static final String DB_NAME = "bookdb";
    private static final String TABLE_Users = "bookdetails";
    private static final String KEY_ID = "id";
    private static final String B_NAME = "bname";
    private static final String B_AUTHOR = "bauthor";
    private static final String B_DATE = "bdate";
    private Context context;

    public DbHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Drop the existing table if it exists

        // Create a new table
        String CREATE_TABLE = "CREATE TABLE " + TABLE_Users + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + B_NAME + " TEXT, "
                + B_AUTHOR + " TEXT, "
                + B_DATE + " TEXT" + ")";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Users);
        onCreate(db);
    }

    void insertBookDetails(String bname, String bauthor, String bdate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cValues = new ContentValues();
        cValues.put(B_NAME, bname);
        cValues.put(B_AUTHOR, bauthor);
        cValues.put(B_DATE, bdate);
        long newRowId = db.insert(TABLE_Users, null, cValues);
        db.close();
    }

    public ArrayList<HashMap<String, String>> GetUsers() {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> userList = new ArrayList<>();
        String query = "SELECT id, bname, bauthor, bdate FROM " + TABLE_Users;
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            HashMap<String, String> user = new HashMap<>();
            user.put("id", cursor.getString(cursor.getColumnIndexOrThrow(KEY_ID)));
            user.put("bname", cursor.getString(cursor.getColumnIndexOrThrow(B_NAME)));
            user.put("bauthor", cursor.getString(cursor.getColumnIndexOrThrow(B_AUTHOR)));
            user.put("bdate", cursor.getString(cursor.getColumnIndexOrThrow(B_DATE)));
            userList.add(user);
        }
        cursor.close();

        // Log retrieved user list
        for (HashMap<String, String> user : userList) {
            Log.d("DbHandler", "ID: " + user.get("id") + ", Book: " + user.get("bname") + ", Author: " + user.get("bauthor") + ", Date: " + user.get("bdate"));
        }

        return userList;
    }



    public ArrayList<HashMap<String, String>> GetUserByUserId(int userid) {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> userList = new ArrayList<>();
        String query = "SELECT bname, bauthor, bdate FROM " + TABLE_Users;
        Cursor cursor = db.query(TABLE_Users, new String[]{B_NAME, B_AUTHOR, B_DATE}, KEY_ID + "=?", new String[]{String.valueOf(userid)}, null, null, null, null);
        if (cursor.moveToNext()) {
            HashMap<String, String> user = new HashMap<>();
            user.put("bname", cursor.getString(cursor.getColumnIndexOrThrow(B_NAME)));
            user.put("bauthor", cursor.getString(cursor.getColumnIndexOrThrow(B_AUTHOR)));
            user.put("bdate", cursor.getString(cursor.getColumnIndexOrThrow(B_DATE)));
            userList.add(user);
        }
        cursor.close();
        return userList;
    }

    public void deleteBook(String bookName) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            Log.d("DeleteBook", "Deleting book with Name: " + bookName);

            String deleteQuery = "DELETE FROM " + TABLE_Users + " WHERE " + B_NAME + "=?";
            Log.d("DeleteBook", "Delete Query: " + deleteQuery + " | Args: " + bookName);

            int rowsDeleted = db.delete(TABLE_Users, B_NAME + "=?", new String[]{bookName});
            Log.d("DeleteBook", "Deleted " + rowsDeleted + " rows");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
    }

    public void updateBook(String bookName, String newAuthor, String newDate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        try {
            Log.d("UpdateBook", "Updating book with Name: " + bookName);

            values.put(B_AUTHOR, newAuthor);
            values.put(B_DATE, newDate);

            String updateQuery = "UPDATE " + TABLE_Users + " SET "
                    + B_AUTHOR + "='" + newAuthor + "', "
                    + B_DATE + "='" + newDate + "' "
                    + "WHERE " + B_NAME + "=?";
            Log.d("UpdateBook", "Update Query: " + updateQuery + " | Args: " + bookName);

            int rowsUpdated = db.update(TABLE_Users, values, B_NAME + "=?", new String[]{bookName});
            Log.d("UpdateBook", "Updated " + rowsUpdated + " rows");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
    }

}
