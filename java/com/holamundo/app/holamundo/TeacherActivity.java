package com.holamundo.app.holamundo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class TeacherActivity extends AppCompatActivity {
    Button btnCrearTeacher;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        btnCrearTeacher = (Button)findViewById(R.id.buttonCreateTeacher);
        btnCrearTeacher.setOnClickListener(new OnClickListenerCreateTeacher());
        countRecords();
        readRecords();

    }
    public void countRecords() {
        int recordCount = new TableControllerTeacher(this).count();
        TextView textViewRecordCount = (TextView) findViewById(R.id.textViewRecordCount);
        textViewRecordCount.setText(recordCount + " records found.");
    }

    public void readRecords() {
        LinearLayout linearLayoutRecords = (LinearLayout) findViewById(R.id.linearLayoutRecords);
        linearLayoutRecords.removeAllViews();
        List<ObjectTeacher> teachers = new TableControllerTeacher(this).read();
        if (teachers.size() > 0) {
            for (ObjectTeacher obj : teachers) {
                int id = obj.id;
                String teacherFirstname = obj.firstname;
                String teacherEmail = obj.email;
                String teacherLastname = obj.lastname;
                String teacherPhone = obj.phone;
                String teacherCity=obj.city;
                String textViewContents = teacherFirstname + " - " + teacherLastname + " - "+ teacherEmail  +" - "+ teacherPhone+" - "+teacherCity;
                TextView textViewTeacherItem= new TextView(this);
                textViewTeacherItem.setPadding(0, 10, 0, 10);
                textViewTeacherItem.setText(textViewContents);
                textViewTeacherItem.setTag(Integer.toString(id));
                textViewTeacherItem.setOnLongClickListener(new OnLongClickListenerTeacherRecord());
                linearLayoutRecords.addView(textViewTeacherItem);
            }

        }
        else {
            TextView locationItem = new TextView(this);
            locationItem.setPadding(8, 8, 8, 8);
            locationItem.setText("No records yet.");

            linearLayoutRecords.addView(locationItem);
        }

    }



}

