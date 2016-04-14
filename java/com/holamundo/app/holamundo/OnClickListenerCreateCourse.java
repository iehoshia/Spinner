package com.holamundo.app.holamundo;

import android.content.Context;
import android.content.DialogInterface;
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
    Spinner editTextCourseTeacher;

    @Override
    public void onClick(View v) {
        final Context context = v.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View formElementsView = inflater.inflate(R.layout.course_input_form, null, false);
        final EditText editTextCourseName = (EditText) formElementsView.findViewById(R.id.editTextCourseName);
        final EditText editTextCourseHourly = (EditText) formElementsView.findViewById(R.id.editTextCourseHourly);
        final EditText editTextCourseClassroom = (EditText) formElementsView.findViewById(R.id.editTextCourseClassroom);
        editTextCourseTeacher =(Spinner) formElementsView.findViewById(R.id.spinner2);
        listTeacher();
        new AlertDialog.Builder(context)
                .setView(formElementsView)
                .setTitle("Create Course")
                .setPositiveButton("Add",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                String courseName = editTextCourseName.getText().toString();
                                String courseHourly = editTextCourseHourly.getText().toString();
                                String courseClassroom = editTextCourseClassroom.getText().toString();
                                String courseTeacher=editTextCourseTeacher.getSelectedItem().toString();

                                ObjectCourse objectCourse= new ObjectCourse();
                                objectCourse.teacher=courseTeacher;
                                objectCourse.name = courseName;
                                objectCourse.hourly = courseHourly;
                                objectCourse.classroom = courseClassroom;
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
    }

    private void listTeacher() {
        List<ObjectTeacher> teachers = new TableControllerTeacher(this).read();
        ArrayList<String> valores = new ArrayList<>();
        if (teachers.size() > 0) {
            for (ObjectTeacher obj : teachers) {
                String teacherFirstname = obj.firstname;
                String teacherLastname = obj.lastname;
                valores.add(teacherFirstname+teacherLastname);
            }
        }
        ArrayAdapter<String> adaptador= new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, valores);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        editTextCourseTeacher.setAdapter(adaptador);

        // +++++++++++++
        List<ObjectTeacher> recordsList = new ArrayList<ObjectTeacher>();

        String sql = "SELECT * FROM teacher ORDER BY id DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                int id = Integer.parseInt(cursor.getString(cursor.getColumnIndex("id")));

                String teacherFirstname = cursor.getString(cursor.getColumnIndex("firstname"));
                String teacherEmail = cursor.getString(cursor.getColumnIndex("email"));
                String teacherLastname= cursor.getString(cursor.getColumnIndex("lastname"));
                String teacherPhone= cursor.getString(cursor.getColumnIndex("phone"));
                String teacherCity= cursor.getString(cursor.getColumnIndex("city"));

                ObjectTeacher objectTeacher=new ObjectTeacher();
                objectTeacher.id = id;
                objectTeacher.firstname = teacherFirstname;
                objectTeacher.email = teacherEmail;
                objectTeacher.lastname = teacherLastname;
                objectTeacher.phone = teacherPhone;
                objectTeacher.city=teacherCity;

                recordsList.add(objectTeacher);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

    }
}
