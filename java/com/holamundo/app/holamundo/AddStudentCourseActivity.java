package com.holamundo.app.holamundo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AddStudentCourseActivity extends AppCompatActivity {

     EditText editTextStudentCourseStartDate;
     EditText editTextStudentCourseEndDate;
     EditText editTextStudentCouseCost;
     Button btnAgregar;
     Spinner spinnerStudent;
    Spinner spinnerCourse;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__student_course);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        spinnerStudent=(Spinner)findViewById(R.id.spinner3);
        spinnerCourse=(Spinner)findViewById(R.id.spinner4);

        listStudent(this);
        listCourse(this);

        editTextStudentCourseStartDate= (EditText) findViewById(R.id.editText14);
        editTextStudentCourseEndDate= (EditText) findViewById(R.id.editText15);
        editTextStudentCouseCost= (EditText) findViewById(R.id.editText16);
        btnAgregar = (Button) findViewById(R.id.button16);
        btnAgregar.setOnClickListener(new OnClickAgregar());



    }
    private class OnClickAgregar implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            final Context context = v.getContext();

            String startDate=editTextStudentCourseStartDate.getText().toString();
            String endDate=editTextStudentCourseEndDate.getText().toString();
            String cost= editTextStudentCouseCost.getText().toString();
            String student_id=spinnerStudent.getSelectedItem().toString();
            String course_id=spinnerCourse.getSelectedItem().toString();

            ObjectStudentCourse objectStudentCourse=new ObjectStudentCourse();

            objectStudentCourse.startDate=startDate;
            objectStudentCourse.endDate=endDate;

            objectStudentCourse.cost=cost;
            objectStudentCourse.student=student_id;
            objectStudentCourse.course=course_id;



            boolean createSuccessful = new TableControllerStudentCourse(context).create(objectStudentCourse);
            if (createSuccessful) {
                Toast.makeText(context, "Teacher information was saved.",
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Unable to save teacher information.",
                        Toast.LENGTH_SHORT).show();
            }
            Intent in= new Intent(context.getApplicationContext() ,StudentCourseActivity.class);
            context.startActivity(in);
        }

    }
    private void listStudent(Context context) {

        List<ObjectStudent> students = new TableControllerStudent(this).read();
        ArrayList<String>valores = new ArrayList<>();
        if (students.size() > 0) {
            for (ObjectStudent obj : students) {

                String studentFirstname = obj.firstname;
                String studentLastname = obj.lastname;

                valores.add(studentFirstname+studentLastname);
            }

        }
        ArrayAdapter<String>adaptador=new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item,valores);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerStudent.setAdapter(adaptador);

    }
    private void listCourse(Context context) {

        List<ObjectCourse> courses = new TableControllerCourse(this).read();
        ArrayList<String>valores = new ArrayList<>();
        if (courses.size() > 0) {
            for (ObjectCourse obj : courses) {

                String courseName = obj.name;
                String courseHourly = obj.hourly;

                valores.add(courseName+courseHourly);
            }

        }
        ArrayAdapter<String>adaptador=new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item,valores);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCourse.setAdapter(adaptador);

    }
}