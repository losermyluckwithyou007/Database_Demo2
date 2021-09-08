package com.example.databasedemo2;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {

            SQLiteDatabase myDatabase = this.openOrCreateDatabase("events", MODE_PRIVATE, null);

            myDatabase.execSQL("CREATE TABLE IF NOT EXISTS events (name VARCHAR, year INT(4))");
            myDatabase.execSQL("INSERT INTO events (name, year) VALUES ('Milenieum', 2000)");
            myDatabase.execSQL("INSERT INTO events (name, year) VALUES ('Pawan started learning', 2020)");

            Cursor c = myDatabase.rawQuery("SELECT * FROM events",null);
            int eventIndex = c.getColumnIndex("name");
            int eventDate = c.getColumnIndex("year");

            c.moveToFirst();

            while(!c.isAfterLast()) {
                Log.i("Results - event", c.getString(eventIndex));
                Log.i("Results - year", Integer.toString(c.getInt(eventDate)));
                c.moveToNext();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}