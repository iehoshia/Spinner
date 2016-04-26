package com.holamundo.app.holamundo;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Iehoshia on 28/03/2016.
 */
public class OnClickListenerCreateCourse implements View.OnClickListener {
    //Spinner editTextCourseTeacher;

    @Override
    public void onClick(View v) {
        final Context context = v.getContext();

        Intent in= new Intent(context.getApplicationContext() ,AddCourseActivity.class);
        context.startActivity(in);


       /* LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View formElementsView = inflater.inflate(R.layout.course_input_form, null, false);
        //listTeacher();
        new AlertDialog.Builder(context)
                .setView(formElementsView)
                .setTitle("Create Course")
                .setPositiveButton("Add",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {


                                boolean createSuccessful = new TableControllerCourse(context)
                                        .create(objectCourse);
                                if (createSuccessful) {
                                    Toast.makeText(context, "Course information was saved.",
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(context, "Unable to save course information.",
                                            Toast.LENGTH_SHORT).show();
                                }
                                ((CourseActivity) context).countRecords();
                                ((CourseActivity) context).readRecords();
                                dialog.cancel();
                            }


                        }).show();

                        */
    }
}

