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
public class OnLongClickListenerTeacherRecord implements View.OnLongClickListener {
    Context context;
    String id;

    @Override
    public boolean onLongClick(View view) {
        context = view.getContext();
        id = view.getTag().toString();
        final CharSequence[] items = { "Edit", "Delete" };
        new AlertDialog.Builder(context).setTitle("Teacher Record")
                .setItems(items, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        if (item == 0) {
                            editRecord(Integer.parseInt(id));
                        }
                        else if (item == 1) {

                            boolean deleteSuccessful = new TableControllerTeacher((AddCourseActivity) context).delete(Integer.parseInt(id));

                            if (deleteSuccessful){
                                Toast.makeText(context, "Teacher record was deleted.", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(context, "Unable to delete teacher record.", Toast.LENGTH_SHORT).show();
                            }
                            ((TeacherActivity) context).countRecords();
                            ((TeacherActivity) context).readRecords();
                        }
                        dialog.dismiss();
                    }
                }).show();
        return false;
    }

    public void editRecord(final int teacherId) {
        final TableControllerTeacher tableControllerTeacher = new TableControllerTeacher((AddCourseActivity) context);
        ObjectTeacher objectTeacher = tableControllerTeacher.readSingleRecord(teacherId);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View formElementsView = inflater.inflate(R.layout.teacher_input_form, null, false);
        final EditText editTextTeacherFirstname = (EditText) formElementsView.findViewById(R.id.editTextTeacherFirstname);
        final EditText editTextTeacherEmail = (EditText) formElementsView.findViewById(R.id.editTextTeacherEmail);
        final EditText editTextTeacherLastname= (EditText) formElementsView.findViewById(R.id.editTextTeacherLastname);
        final EditText editTextTeacherPhone= (EditText) formElementsView.findViewById(R.id.editTextTeacherPhone);

        final EditText editTextTeacherCity=(EditText) formElementsView.findViewById(R.id.editTextTeacherCity);

        editTextTeacherFirstname.setText(objectTeacher.firstname);
        editTextTeacherEmail.setText(objectTeacher.email);
        editTextTeacherLastname.setText(objectTeacher.lastname);
        editTextTeacherPhone.setText(objectTeacher.phone);
        editTextTeacherCity.setText(objectTeacher.city);


        new AlertDialog.Builder(context)
                .setView(formElementsView)
                .setTitle("Edit Record")
                .setPositiveButton("Save Changes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                ObjectTeacher objectTeacher = new ObjectTeacher();
                                objectTeacher.id = teacherId;
                                objectTeacher.firstname = editTextTeacherFirstname.getText().toString();
                                objectTeacher.email = editTextTeacherEmail.getText().toString();
                                objectTeacher.city=editTextTeacherCity.getText().toString();
                                objectTeacher.lastname=editTextTeacherLastname.getText().toString();
                                objectTeacher.phone=editTextTeacherPhone.getText().toString();
                                boolean updateSuccessful = tableControllerTeacher.update(objectTeacher);

                                if(updateSuccessful){
                                    Toast.makeText(context, "Teacher record was updated.", Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(context, "Unable to update teacher record.", Toast.LENGTH_SHORT).show();
                                }
                                ((TeacherActivity) context).countRecords();
                                ((TeacherActivity) context).readRecords();

                                dialog.cancel();
                            }
                        }).show();
    }
}


