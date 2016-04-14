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

public class TableControllerCourse extends DatabaseHandler {

    public TableControllerCourse(Context context) {
        super(context);
    }

    public boolean create(ObjectCourse objectCourse) {

        ContentValues values = new ContentValues();

        values.put("name", objectCourse.name);
        values.put("hourly", objectCourse.hourly);
        values.put("classroom", objectCourse.classroom);
        values.put("teacher",objectCourse.teacher);

        SQLiteDatabase db = this.getWritableDatabase();

        boolean createSuccessful = db.insert("course", null, values) > 0;
        db.close();

        return createSuccessful;
    }

    public int count() {

        SQLiteDatabase db = this.getWritableDatabase();

        String sql = "SELECT * FROM course";
        int recordCount = db.rawQuery(sql, null).getCount();
        db.close();

        return recordCount;

    }

    public List<ObjectCourse> read() {
        List<ObjectCourse> recordsList = new ArrayList<ObjectCourse>();

        String sql = "SELECT * FROM course ORDER BY id DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                int id = Integer.parseInt(cursor.getString(cursor.getColumnIndex("id")));

                String courseName = cursor.getString(cursor.getColumnIndex("name"));
                String courseHourly = cursor.getString(cursor.getColumnIndex("hourly"));
                String courseClassroom= cursor.getString(cursor.getColumnIndex("classroom"));
                String courseTeacher=cursor.getString(cursor.getColumnIndex("teacher"));

                ObjectCourse objectCourse=new ObjectCourse();

                objectCourse.id = id;
                objectCourse.name = courseName;
                objectCourse.hourly = courseHourly;
                objectCourse.classroom = courseClassroom;
                objectCourse.teacher= courseTeacher;

                recordsList.add(objectCourse);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return recordsList;
    }

    public ObjectCourse readSingleRecord(int courseId) {
        ObjectCourse objectCourse = null;
        String sql = "SELECT * FROM course WHERE id = " + courseId;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            int id = Integer.parseInt(cursor.getString(cursor.getColumnIndex("id")));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String hourly = cursor.getString(cursor.getColumnIndex("hourly"));
            String classroom = cursor.getString(cursor.getColumnIndex("classroom"));
            String teacher=cursor.getString(cursor.getColumnIndex("teacher"));
            objectCourse = new ObjectCourse();
            objectCourse.id = id;
            objectCourse.name = name;
            objectCourse.hourly = hourly;
            objectCourse.classroom = classroom;
            objectCourse.teacher=teacher;
            }
        cursor.close();
        db.close();
        return objectCourse;
    }

    public boolean update(ObjectCourse objectCourse) {

        ContentValues values = new ContentValues();
        values.put("name", objectCourse.name);
        values.put("hourly", objectCourse.hourly);
        values.put("classroom", objectCourse.classroom);
        values.put("teacher",objectCourse.teacher);
        String where = "id = ?";
        String[] whereArgs = { Integer.toString(objectCourse.id) };
        SQLiteDatabase db = this.getWritableDatabase();
        boolean updateSuccessful = db.update("course", values, where, whereArgs) > 0;
        db.close();
        return updateSuccessful;
    }

    public boolean delete(int id) {
        boolean deleteSuccessful = false;

        SQLiteDatabase db = this.getWritableDatabase();
        deleteSuccessful = db.delete("course", "id ='" + id + "'", null) > 0;
        db.close();

        return deleteSuccessful;

    }

}
