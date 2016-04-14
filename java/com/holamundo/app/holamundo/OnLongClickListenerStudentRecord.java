package com.holamundo.app.holamundo;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Iehoshia on 28/03/2016.
 */
public class OnLongClickListenerStudentRecord implements View.OnLongClickListener {
    Context context;
    String id;

    @Override
    public boolean onLongClick(View view) {
        context = view.getContext();
        id = view.getTag().toString();
        final CharSequence[] items = { "Edit", "Delete" };
        new AlertDialog.Builder(context).setTitle("Student Record")
                .setItems(items, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        if (item == 0) {
                            editRecord(Integer.parseInt(id));
                        }
                        else if (item == 1) {

                            boolean deleteSuccessful = new TableControllerStudent(context).delete(Integer.parseInt(id));

                            if (deleteSuccessful){
                                Toast.makeText(context, "Student record was deleted.", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(context, "Unable to delete student record.", Toast.LENGTH_SHORT).show();
                            }
                            ((StudentActivity) context).countRecords();
                            ((StudentActivity) context).readRecords();
                        }
                        dialog.dismiss();
                    }
                }).show();
        return false;
    }

    public void editRecord(final int studentId) {
        final TableControllerStudent tableControllerStudent = new TableControllerStudent(context);
        ObjectStudent objectStudent = tableControllerStudent.readSingleRecord(studentId);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View formElementsView = inflater.inflate(R.layout.student_input_form, null, false);
        final EditText editTextStudentFirstname = (EditText) formElementsView.findViewById(R.id.editTextStudentFirstname);
        final EditText editTextStudentEmail = (EditText) formElementsView.findViewById(R.id.editTextStudentEmail);
        final EditText editTextStudentLastname= (EditText) formElementsView.findViewById(R.id.editTextStudentLastname);
        final EditText editTextStudentPhone= (EditText) formElementsView.findViewById(R.id.editTextStudentPhone);

        final EditText editTextStudentCity=(EditText) formElementsView.findViewById(R.id.editTextStudentCity);

        editTextStudentFirstname.setText(objectStudent.firstname);
        editTextStudentEmail.setText(objectStudent.email);
        editTextStudentLastname.setText(objectStudent.lastname);
        editTextStudentPhone.setText(objectStudent.phone);
        editTextStudentCity.setText(objectStudent.city);


        new AlertDialog.Builder(context)
                .setView(formElementsView)
                .setTitle("Edit Record")
                .setPositiveButton("Save Changes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                ObjectStudent objectStudent = new ObjectStudent();
                                objectStudent.id = studentId;
                                objectStudent.firstname = editTextStudentFirstname.getText().toString();
                                objectStudent.email = editTextStudentEmail.getText().toString();
                                objectStudent.city=editTextStudentCity.getText().toString();
                                boolean updateSuccessful = tableControllerStudent.update(objectStudent);

                                if(updateSuccessful){
                                    Toast.makeText(context, "Student record was updated.", Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(context, "Unable to update student record.", Toast.LENGTH_SHORT).show();
                                }
                                ((StudentActivity) context).countRecords();
                                ((StudentActivity) context).readRecords();

                                dialog.cancel();
                            }
                        }).show();
    }
}


