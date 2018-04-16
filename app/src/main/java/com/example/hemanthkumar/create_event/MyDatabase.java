package com.example.hemanthkumar.create_event;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by hemanthkumar on 22/3/18.
 */

public class MyDatabase extends SQLiteOpenHelper {
    //1.Database version
    private static int DATABASE_VERSION = 1;
    //2.Database Name
    private static String Database_Name = "Events";
    //3.Table name
    private static String Table_Name = "EventDetails";


    private static String Event_title = "title";
    private static String Event_Details = "details";
    private static String Event_Date = "date";
    private static String Event_time = "time";
    public Context context;





    public MyDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_EVENTS_TABLE = "CREATE TABLE "
                + Table_Name + "("
                + Event_title + " TEXT,"
                + Event_Details + " TEXT,"
                + Event_Date + " TEXT,"
                + Event_time + " TEXT" +")";
        db.execSQL(CREATE_EVENTS_TABLE);

    }

    public void insert(String title, String details, String date,  String time) {
        SQLiteDatabase db = this.getReadableDatabase();
        String Insert = "Insert into " + Table_Name + "(" + Event_title + "," + Event_Details + "," + Event_Date + "," + Event_time +
        ") values('" + title + "','" + details + "','" + date + "','" + time + "');";

        db.execSQL(Insert);
        db.close();

    }
    public void delete(String title, String details, String date, String time) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from " + Table_Name + " where " + Event_title + " = '" + title + "'AND " + Event_Details +"= '"+ details +"'AND "+ Event_Date +"= '"+ date +"'AND "+Event_time +"= '"+time+"'");
        db.close();

    }
    public void update(String title,String details,String date,String time) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Event_title, title);
        contentValues.put(Event_Details,details);
        contentValues.put(Event_Date,date);
        contentValues.put(Event_time,time);
        db.update(MyDatabase.Table_Name, contentValues,
                null, null);
        db.close();
    }


    public ArrayList<Data> getData() {
        ArrayList<Data> tabledata;
        tabledata = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String RETRIEVE_STUDENTS_INFO = "SELECT * from " + Table_Name;
        Cursor cursor = db.rawQuery(RETRIEVE_STUDENTS_INFO, null);
        if (cursor.moveToFirst()) {
            do {
                String title = cursor.getString(cursor.getColumnIndex(Event_title));
                String details = cursor.getString(cursor.getColumnIndex(Event_Details));
                String date = cursor.getString(cursor.getColumnIndex(Event_Date));
                String time = cursor.getString(cursor.getColumnIndex(Event_time));
                Data data = new Data(title, details, date, time);
                tabledata.add(data);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return tabledata;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
