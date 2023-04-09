package com.example.habitplanner;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    /*Will need to create another database for habits
     this one will hold only the tasks (and task related habits)
     if task == true then accessing another db (Gotta think how!) */

    public static final String DATABASE_NAME = "Tasks.db";
    public static final String TASKS_TABLE_NAME = "tasks";
    public static final String TASKS_COLUMN_ID = "id";
    public static final String TASKS_COLUMN_NAME = "name";
    public static final String TASKS_COLUMN_DUE_DATE = "dueDate";
    public static final String TASKS_COLUMN_IS_HABIT= "isHabit";

    public DBHelper(Context context){
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE tasks " +
                "(id INTEGER PRIMARY KEY, name TEXT, dueDate DATETIME, isHabit BIT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS tasks");
        onCreate(db);
    }

    public boolean insertTask(Integer id, String name, String dueDate, boolean isHabit){
        SQLiteDatabase db = this.getWritableDatabase(); //Hello? where do we hold the database, that was created
        ContentValues cv = new ContentValues();         //What is there are two of them??
        cv.put(TASKS_COLUMN_ID, id);
        cv.put(TASKS_COLUMN_NAME, name);
        cv.put(TASKS_COLUMN_DUE_DATE, dueDate);
        cv.put(TASKS_COLUMN_IS_HABIT, isHabit);
        db.insert(TASKS_TABLE_NAME, null, cv);
        return true;
    }

    public Cursor getData(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cur = db.rawQuery("SELECT * FROM CONTACTS WHERE id = "+ id+"", null);
        return cur;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, TASKS_TABLE_NAME);
        return numRows;
    }

    public boolean updateTask(Integer id, String name, String date, boolean habit){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TASKS_COLUMN_ID, id);
        cv.put(TASKS_COLUMN_NAME, name);
        cv.put(TASKS_COLUMN_DUE_DATE, date);
        cv.put(TASKS_COLUMN_IS_HABIT, habit);
        db.update(TASKS_TABLE_NAME, cv, "id = ? ", new String[]{Integer.toString(id)});
        return true;
    }

    public Integer deleteTask (Integer id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TASKS_TABLE_NAME, "id = ? ", new String[]{ Integer.toString(id)});
    }

    public CustomAdapter getAllTasks(){
        ArrayList<String> names_list = new ArrayList<String>();
        ArrayList<String> dueDates_list = new ArrayList<String>();
        ArrayList<Boolean> isHabits_list = new ArrayList<Boolean>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor query = db.rawQuery("SELECT * FROM " + TASKS_COLUMN_NAME, null);
        query.moveToFirst();
        while(query.isAfterLast() == false){
            int NameColumnIndex = query.getColumnIndex(TASKS_COLUMN_NAME);
            int DueDateColumnIndex = query.getColumnIndex(TASKS_COLUMN_DUE_DATE);
            int isHabitColumnIndex = query.getColumnIndex(TASKS_COLUMN_IS_HABIT);
            names_list.add(query.getString(NameColumnIndex));
            dueDates_list.add(query.getString(DueDateColumnIndex));
            dueDates_list.add(query.getString(isHabitColumnIndex));
            query.moveToNext();
        }
        CustomAdapter adapter = new CustomAdapter(names_list, dueDates_list, isHabits_list);
        return adapter;
    }
}
