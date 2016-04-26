package com.holamundo.app.holamundo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Iehoshia on 28/03/2016.
 */

public class TableControllerStudentCourse extends DatabaseHandler {

    public TableControllerStudentCourse(Context context) {
        super(context);
    }

    public boolean create(ObjectStudentCourse objectStudentCourse) {

        ContentValues values = new ContentValues();



        values.put("startDate",objectStudentCourse.startDate);
        values.put("endDate",objectStudentCourse.endDate);
        values.put("cost",objectStudentCourse.cost);
       values.put("student",objectStudentCourse.student);
        values.put("course",objectStudentCourse.course);


        SQLiteDatabase db = this.getWritableDatabase();

        boolean createSuccessful = db.insert("student_course", null, values) > 0;
        db.close();

        return createSuccessful;
    }

    public int count() {

        SQLiteDatabase db = this.getWritableDatabase();

        String sql = "SELECT * FROM student_course";
        int recordCount = db.rawQuery(sql, null).getCount();
        db.close();

        return recordCount;

    }

    public List<ObjectStudentCourse> read() {
        List<ObjectStudentCourse> recordsList = new ArrayList<ObjectStudentCourse>();

        String sql = "SELECT * FROM student_course ORDER BY id DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                int id = Integer.parseInt(cursor.getString(cursor.getColumnIndex("id")));

                String startDate=cursor.getString(cursor.getColumnIndex("startDate"));
                String endDate=cursor.getString(cursor.getColumnIndex("endDate"));
                String cost=cursor.getString(cursor.getColumnIndex("cost"));
                String student=cursor.getString(cursor.getColumnIndex("student"));
                String course=cursor.getString(cursor.getColumnIndex("course"));


                ObjectStudentCourse objectStudentCourse=new ObjectStudentCourse();

                objectStudentCourse.id=id;
                objectStudentCourse.startDate=startDate;
                objectStudentCourse.endDate=endDate;
                objectStudentCourse.cost=cost;
                objectStudentCourse.student=student;
                objectStudentCourse.course=course;

                recordsList.add(objectStudentCourse);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return recordsList;
    }

    public ObjectStudentCourse readSingleRecord(int StudentcourseId) {
        ObjectStudentCourse objectStudentCourse = null;
        String sql = "SELECT * FROM student_course WHERE id = " + StudentcourseId;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            int id = Integer.parseInt(cursor.getString(cursor.getColumnIndex("id")));
            String startDate = cursor.getString(cursor.getColumnIndex("startDate"));
            String endDate = cursor.getString(cursor.getColumnIndex("endDate"));
            String cost = cursor.getString(cursor.getColumnIndex("cost"));
            String student= cursor.getString(cursor.getColumnIndex("student"));
            String course= cursor.getString(cursor.getColumnIndex("course"));
            objectStudentCourse = new ObjectStudentCourse();
            objectStudentCourse.id = id;
            objectStudentCourse.startDate = startDate;
            objectStudentCourse.endDate = endDate;
            objectStudentCourse.cost = cost;
            objectStudentCourse.student=student;
            objectStudentCourse.course=course;

        }
        cursor.close();
        db.close();
        return objectStudentCourse;
    }

    public boolean update(ObjectStudentCourse objectStudentCourse) {

        ContentValues values = new ContentValues();

        values.put("startDate",objectStudentCourse.startDate);
        values.put("endDate",objectStudentCourse.endDate);
        values.put("cost",objectStudentCourse.cost);
        values.put("student",objectStudentCourse.student);
        values.put("course",objectStudentCourse.course);

        String where = "id = ?";
        String[] whereArgs = { Integer.toString(objectStudentCourse.id) };
        SQLiteDatabase db = this.getWritableDatabase();
        boolean updateSuccessful = db.update("student_course", values, where, whereArgs) > 0;
        db.close();
        return updateSuccessful;
    }

    public boolean delete(int id) {
        boolean deleteSuccessful = false;

        SQLiteDatabase db = this.getWritableDatabase();
        deleteSuccessful = db.delete("student_course", "id ='" + id + "'", null) > 0;
        db.close();

        return deleteSuccessful;

    }

}
