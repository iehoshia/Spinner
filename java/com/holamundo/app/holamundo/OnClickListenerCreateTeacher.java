package com.holamundo.app.holamundo;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Iehoshia on 28/03/2016.
 */
public class OnClickListenerCreateTeacher implements View.OnClickListener {
    @Override
    public void onClick(View v) {

        final Context context = v.getContext();

        Intent in= new Intent(context.getApplicationContext() ,AddTeacherActivity.class);
        context.startActivity(in);


        /*LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View formElementsView = inflater.inflate(R.layout.teacher_input_form, null, false);
        final EditText editTextTeacherFirstname = (EditText) formElementsView.findViewById(R.id.editTextTeacherFirstname);

        new AlertDialog.Builder(context)
                .setView(formElementsView)
                .setTitle("Create Teacher")
                .setPositiveButton("Add",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                String teacherFirstname = editTextTeacherFirstname.getText().toString();
                                String teacherLastname = editTextTeacherLastname.getText().toString();
                                String teacherPhone = editTextTeacherPhone.getText().toString();
                                String teacherCity= editTextTeacherCity.getText().toString();
                                ObjectTeacher objectTeacher = new ObjectTeacher();
                                objectTeacher.firstname = teacherFirstname;
                                objectTeacher.lastname = teacherLastname;
                                objectTeacher.phone = teacherPhone;
                                objectTeacher.city=teacherCity;
                                boolean createSuccessful = new TableControllerTeacher(context)
                                        .create(objectTeacher);
                                if (createSuccessful) {
                                    Toast.makeText(context, "Teacher information was saved.",
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(context, "Unable to save teacher information.",
                                            Toast.LENGTH_SHORT).show();
                                }
                                ((TeacherActivity) context).countRecords();
                                ((TeacherActivity) context).readRecords();
                                dialog.cancel();
                            }

                        }).show();*/

    }
}
