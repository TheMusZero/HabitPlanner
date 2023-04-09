package com.example.habitplanner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {

    Context context;
    ArrayList<String> tasksNames;
    ArrayList<String> tasksDueDates;
    LayoutInflater layoutInflater;

    public CustomAdapter(Context newContext, ArrayList<String> newTasks, ArrayList<String> dueData, ArrayList<Boolean> isHabit){
        tasksNames = newTasks;
        tasksDueDates = dueData;
        addContext(newContext);
    }

    public CustomAdapter(ArrayList<String> newTasks, ArrayList<String> dueData, ArrayList<Boolean> isHabit){
        tasksNames = newTasks;
        tasksDueDates = dueData;
    }

    public void addContext(Context newContext){
        context = newContext;
        layoutInflater = LayoutInflater.from(newContext);
    }

    @Override
    public int getCount() {
        return tasksNames.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    /*TODO
    -Add variation for tasks (Regular tasks/Habits)
    -Differentiate the completed tasks from uncompleted
    -Make the corresponding element_listview.xml
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = layoutInflater.inflate(R.layout.element_listview, null);
        TextView taskName = view.findViewById(R.id.taskName);
        TextView taskDueDate = view.findViewById(R.id.taskDueDate);
        taskName.setText(tasksNames.get(position));
        taskDueDate.setText(tasksDueDates.get(position));
        return view;
    }
}
