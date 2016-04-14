package com.holamundo.app.holamundo;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.concurrent.RunnableScheduledFuture;

/**
 * Created by Iehoshia on 28/03/2016.
 */
public class OnClickListenerCreateStudent implements View.OnClickListener {
    @Override
    public void onClick(View v) {
        final Context context = v.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View formElementsView = inflater.inflate(R.layout.student_input_form, null, false);
        final EditText editTextStudentFirstname = (EditText) formElementsView.findViewById(R.id.editTextStudentFirstname);
        final EditText editTextStudentEmail = (EditText) formElementsView.findViewById(R.id.editTextStudentEmail);
        final EditText editTextStudentLastname = (EditText) formElementsView.findViewById(R.id.editTextStudentLastname);
        final EditText editTextStudentPhone = (EditText) formElementsView.findViewById(R.id.editTextStudentPhone);
        final EditText editTextStudentCity=(EditText) formElementsView.findViewById(R.id.editTextStudentCity);
        new AlertDialog.Builder(context)
                .setView(formElementsView)
                .setTitle("Create Student")
                .setPositiveButton("Add",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                String studentFirstname = editTextStudentFirstname.getText().toString();
                                String studentEmail = editTextStudentEmail.getText().toString();
                                String studentLastname = editTextStudentLastname.getText().toString();
                                String studentPhone = editTextStudentPhone.getText().toString();
                                String studentCity= editTextStudentCity.getText().toString();
                                ObjectStudent objectStudent = new ObjectStudent();
                                objectStudent.firstname = studentFirstname;
                                objectStudent.email = studentEmail;
                                objectStudent.lastname = studentLastname;
                                objectStudent.phone = studentPhone;
                                objectStudent.city=studentCity;
                                boolean createSuccessful = new TableControllerStudent(context)
                                        .create(objectStudent);
                                if (createSuccessful) {
                                    Toast.makeText(context, "Student information was saved.",
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(context, "Unable to save student information.",
                                            Toast.LENGTH_SHORT).show();
                                }
                                ((StudentActivity) context).countRecords();
                                ((StudentActivity) context).readRecords();
                                dialog.cancel();
                            }

                        }).show();

    }
}
