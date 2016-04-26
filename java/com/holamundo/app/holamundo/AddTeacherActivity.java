package com.holamundo.app.holamundo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddTeacherActivity extends AppCompatActivity {
    EditText editTextTeacherFirstname;
    EditText editTextTeacherEmail;
    EditText editTextTeacherLastname;
    EditText editTextTeacherPhone;
    EditText editTextTeacherCity;
    Button btnAgregar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacher_input_form);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        editTextTeacherFirstname = (EditText) findViewById(R.id.editTextTeacherFirstname);
        editTextTeacherEmail= (EditText) findViewById(R.id.editTextTeacherEmail);
        editTextTeacherLastname = (EditText) findViewById(R.id.editTextTeacherLastname);
        editTextTeacherPhone= (EditText) findViewById(R.id.editTextTeacherPhone);
        editTextTeacherCity= (EditText) findViewById(R.id.editTextTeacherCity);


        btnAgregar = (Button) findViewById(R.id.button14);
        btnAgregar.setOnClickListener(new OnClickAgregar());
    }


    private class OnClickAgregar implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            final Context context = v.getContext();
            String teacherFirstname = editTextTeacherFirstname.getText().toString();
            String teacherEmail = editTextTeacherEmail.getText().toString();
            String teacherLastname = editTextTeacherLastname.getText().toString();
            String teacherPhone = editTextTeacherPhone.getText().toString();
            String teacherCity= editTextTeacherCity.getText().toString();


            ObjectTeacher objectTeacher = new ObjectTeacher();
            objectTeacher.firstname = teacherFirstname;
            objectTeacher.lastname=teacherLastname;
            objectTeacher.email = teacherEmail;
            objectTeacher.phone = teacherPhone;
            objectTeacher.city=teacherCity;


            boolean createSuccessful = new TableControllerTeacher(context).create(objectTeacher);
            if (createSuccessful) {
                Toast.makeText(context, "Teacher information was saved.",
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Unable to save teacher information.",
                        Toast.LENGTH_SHORT).show();
            }
            Intent in= new Intent(context.getApplicationContext() ,TeacherActivity.class);
            context.startActivity(in);
        }
    }
}
