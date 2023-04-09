package com.example.habitplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ListView taskList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        taskList = findViewById(R.id.listView);
        DBHelper dbHelper = new DBHelper(this);
        CustomAdapter adapter = dbHelper.getAllTasks();
        taskList.setAdapter(adapter);

        Button
        //listView = findViewById(R.id.listView);
        //CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(), taskList, tasksImages);
        //listView.setAdapter(customAdapter);
    }
}