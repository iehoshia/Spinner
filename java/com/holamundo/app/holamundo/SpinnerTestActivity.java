package com.holamundo.app.holamundo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SpinnerTestActivity extends AppCompatActivity {
    Spinner spinner;
    Button button;
    EditText editText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner_test);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        spinner=(Spinner)findViewById(R.id.spinner);
        button=(Button)findViewById(R.id.button);
        editText=(EditText) findViewById(R.id.editText);

        //listTeacher();


    }
    /*

    private void listTeacher() {
        List<ObjectTeacher> teachers = new TableControllerTeacher(this).read();
        ArrayList<String>valores = new ArrayList<>();
        if (teachers.size() > 0) {
            for (ObjectTeacher obj : teachers) {
                String teacherFirstname = obj.firstname;
                String teacherLastname = obj.lastname;
                valores.add(teacherFirstname+teacherLastname);
            }

        }
        ArrayAdapter<String>adaptador=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,valores);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adaptador);

    }
*/




}
