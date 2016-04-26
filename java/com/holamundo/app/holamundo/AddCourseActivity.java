package com.holamundo.app.holamundo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AddCourseActivity extends AppCompatActivity {

     EditText editTextCourseName;
     EditText editTextCourseHourly;
     EditText editTextCourseClassroom;
     Spinner spinnerteacher;
     Button btnAgregar;
     Spinner spinner;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_input_form);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        spinner=(Spinner)findViewById(R.id.spinner2);
        listTeacher(this);


        editTextCourseName = (EditText) findViewById(R.id.editTextCourseName);
        editTextCourseHourly = (EditText) findViewById(R.id.editTextCourseHourly);
        editTextCourseClassroom = (EditText) findViewById(R.id.editTextCourseClassroom);
        spinnerteacher = (Spinner) findViewById(R.id.spinner2);
        btnAgregar = (Button) findViewById(R.id.button15);
        btnAgregar.setOnClickListener(new OnClickAgregar());



    }
    private class OnClickAgregar implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            final Context context = v.getContext();

            String courseName = editTextCourseName.getText().toString();
            String courseHourly = editTextCourseHourly.getText().toString();
            String courseClassroom = editTextCourseClassroom.getText().toString();
            String courseTeacher=spinnerteacher.getSelectedItem().toString();


            ObjectCourse objectCourse = new ObjectCourse();


            objectCourse.name = courseName;
            objectCourse.hourly = courseHourly;
            objectCourse.classroom = courseClassroom;
            objectCourse.teacher=courseTeacher;



            boolean createSuccessful = new TableControllerCourse(context).create(objectCourse);
            if (createSuccessful) {
                Toast.makeText(context, "Teacher information was saved.",
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Unable to save teacher information.",
                        Toast.LENGTH_SHORT).show();
            }
            Intent in= new Intent(context.getApplicationContext() ,CourseActivity.class);
            context.startActivity(in);
        }

    }
    private void listTeacher(Context context) {

        List<ObjectTeacher> teachers = new TableControllerTeacher(this).read();
        ArrayList<String>valores = new ArrayList<>();
        if (teachers.size() > 0) {
            for (ObjectTeacher obj : teachers) {
                String teacherFirstname = obj.firstname;
                String teacherLastname = obj.lastname;

                valores.add(teacherFirstname+teacherLastname);
            }

        }
        ArrayAdapter<String>adaptador=new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item,valores);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adaptador);

    }

}