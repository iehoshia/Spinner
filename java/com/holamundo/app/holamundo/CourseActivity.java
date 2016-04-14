package com.holamundo.app.holamundo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class CourseActivity extends AppCompatActivity {
    Button btnCrearCourse;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        btnCrearCourse = (Button)findViewById(R.id.buttonCreateCourse);
        btnCrearCourse.setOnClickListener(new OnClickListenerCreateCourse());
        countRecords();
        readRecords();

    }
    public void countRecords() {
        int recordCount = new TableControllerCourse(this).count();
        TextView textViewRecordCount = (TextView) findViewById(R.id.textViewRecordCount);
        textViewRecordCount.setText(recordCount + " records found.");
    }

    public void readRecords() {
        LinearLayout linearLayoutRecords = (LinearLayout) findViewById(R.id.linearLayoutRecords);
        linearLayoutRecords.removeAllViews();
        List<ObjectCourse> courses = new TableControllerCourse(this).read();
        if (courses.size() > 0) {
            for (ObjectCourse obj : courses) {
                int id = obj.id;
                String courseName = obj.name;
                String courseHourly = obj.hourly;
                String courseClassroom = obj.classroom;
                String courseTeacher=obj.teacher;
                String textViewContents = courseName + " - " + courseHourly + " - "+ courseClassroom + " - " + courseTeacher;
                TextView textViewCourseItem= new TextView(this);
                textViewCourseItem.setPadding(0, 10, 0, 10);
                textViewCourseItem.setText(textViewContents);
                textViewCourseItem.setTag(Integer.toString(id));
                textViewCourseItem.setOnLongClickListener(new OnLongClickListenerCourseRecord());
                linearLayoutRecords.addView(textViewCourseItem);
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

