package com.holamundo.app.holamundo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main23);
    }

    public void mostrarStudent(View view) {

        Intent in= new Intent(Main2Activity.this,StudentActivity.class);
        startActivity(in);


    }
    public void mostrarTeacher(View view) {

        Intent in= new Intent(Main2Activity.this,TeacherActivity.class);
        startActivity(in);


    }

    public void mostrarCourse(View view) {

        Intent in= new Intent(Main2Activity.this, CourseActivity.class);
        startActivity(in);


    }
    public  void mostrarSpinner(View view){

       Intent in = new Intent(Main2Activity.this, SpinnerTestActivity.class);
        startActivity(in);
    }


}
