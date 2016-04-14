package com.holamundo.app.holamundo;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by Iehoshia on 28/03/2016.
 */

public class OnLongClickListenerCourseRecord implements View.OnLongClickListener {
    Context context;
    String id;

    @Override
    public boolean onLongClick(View view) {
        context = view.getContext();
        id = view.getTag().toString();
        final CharSequence[] items = { "Edit", "Delete" };
        new AlertDialog.Builder(context).setTitle("Course Record")
                .setItems(items, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        if (item == 0) {
                            editRecord(Integer.parseInt(id));
                        }
                        else if (item == 1) {

                            boolean deleteSuccessful = new TableControllerCourse(context).delete(Integer.parseInt(id));

                            if (deleteSuccessful){
                                Toast.makeText(context, "Course record was deleted.", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(context, "Unable to delete course record.", Toast.LENGTH_SHORT).show();
                            }
                            ((CourseActivity) context).countRecords();
                            ((CourseActivity) context).readRecords();
                        }
                        dialog.dismiss();
                    }
                }).show();
        return false;
    }

    public void editRecord(final int courseId) {
        final TableControllerCourse tableControllerCourse = new TableControllerCourse(context);
        ObjectCourse objectCourse = tableControllerCourse.readSingleRecord(courseId);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View formElementsView = inflater.inflate(R.layout.course_input_form, null, false);


        final Spinner editTextCourseTeacher=(Spinner) formElementsView.findViewById(R.id.spinner);

        final EditText editTextCourseName = (EditText) formElementsView.findViewById(R.id.editTextCourseName);
        final EditText editTextCourseHourly = (EditText) formElementsView.findViewById(R.id.editTextCourseHourly);
        final EditText editTextCourseClassroom= (EditText) formElementsView.findViewById(R.id.editTextCourseClassroom);

        editTextCourseName.setText(objectCourse.name);
        editTextCourseHourly.setText(objectCourse.hourly);
        editTextCourseClassroom.setText(objectCourse.classroom);
        //editTextCourseTeacher.setText(objectCourse.teacher);
        setSpinText(editTextCourseTeacher, objectCourse.teacher);


        new AlertDialog.Builder(context)
                .setView(formElementsView)
                .setTitle("Edit Record")
                .setPositiveButton("Save Changes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                ObjectCourse objectCourse = new ObjectCourse();
                                objectCourse.id = courseId;
                                objectCourse.hourly = editTextCourseHourly.getText().toString();
                                objectCourse.classroom=editTextCourseClassroom.getText().toString();
                                objectCourse.teacher=editTextCourseTeacher.getSelectedItem().toString();
                                boolean updateSuccessful = tableControllerCourse.update(objectCourse);

                                if(updateSuccessful){
                                    Toast.makeText(context, "Course record was updated.", Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(context, "Unable to update course record.", Toast.LENGTH_SHORT).show();
                                }
                                ((CourseActivity) context).countRecords();
                                ((CourseActivity) context).readRecords();

                                dialog.cancel();
                            }
                        }).show();
    }

    public void setSpinText(Spinner spin, String text)
    {
        for(int i= 0; i < spin.getAdapter().getCount(); i++)
        {
            if(spin.getAdapter().getItem(i).toString().contains(text))
            {
                spin.setSelection(i);
            }
        }

    }
}


