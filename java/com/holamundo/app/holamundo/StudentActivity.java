package com.holamundo.app.holamundo;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class StudentActivity extends AppCompatActivity {
    Button btnCrearAlumno;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estudents);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        btnCrearAlumno = (Button)findViewById(R.id.buttonCreateStudent);
        btnCrearAlumno.setOnClickListener(new OnClickListenerCreateStudent());
        countRecords();
        readRecords();

    }
    public void countRecords() {
        int recordCount = new TableControllerStudent(this).count();
        TextView textViewRecordCount = (TextView) findViewById(R.id.textViewRecordCount);
        textViewRecordCount.setText(recordCount + " records found.");
    }

    public void readRecords() {
        LinearLayout linearLayoutRecords = (LinearLayout) findViewById(R.id.linearLayoutRecords);
        linearLayoutRecords.removeAllViews();
        List<ObjectStudent> students = new TableControllerStudent(this).read();
        if (students.size() > 0) {
            for (ObjectStudent obj : students) {
                int id = obj.id;
                String studentFirstname = obj.firstname;
                String studentEmail = obj.email;
                String studentLastname = obj.lastname;
                String studentPhone = obj.phone;
                String studentCity=obj.city;
                String textViewContents = studentFirstname + " - " + studentLastname + " - "+ studentEmail  +" - "+ studentPhone+" - "+studentCity;
                TextView textViewStudentItem= new TextView(this);
                textViewStudentItem.setPadding(0, 10, 0, 10);
                textViewStudentItem.setText(textViewContents);
                textViewStudentItem.setTag(Integer.toString(id));
                textViewStudentItem.setOnLongClickListener(new OnLongClickListenerStudentRecord());
                linearLayoutRecords.addView(textViewStudentItem);
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
