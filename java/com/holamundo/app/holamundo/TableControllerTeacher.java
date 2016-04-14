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

public class TableControllerTeacher extends DatabaseHandler {

    public TableControllerTeacher(Context context) {
        super(context);
    }

    public boolean create(ObjectTeacher objectTeacher) {

        ContentValues values = new ContentValues();

        values.put("firstname", objectTeacher.firstname);
        values.put("lastname", objectTeacher.lastname);
        values.put("email", objectTeacher.email);
        values.put("phone", objectTeacher.phone);
        values.put("city",objectTeacher.city);

        SQLiteDatabase db = this.getWritableDatabase();

        boolean createSuccessful = db.insert("teacher", null, values) > 0;
        db.close();

        return createSuccessful;
    }

    public int count() {

        SQLiteDatabase db = this.getWritableDatabase();

        String sql = "SELECT * FROM teacher";
        int recordCount = db.rawQuery(sql, null).getCount();
        db.close();

        return recordCount;

    }

    public List<ObjectTeacher> read() {
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

        return recordsList;
    }

    public ObjectTeacher readSingleRecord(int teacherId) {
        ObjectTeacher objectTeacher = null;
        String sql = "SELECT * FROM teacher WHERE id = " + teacherId;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            int id = Integer.parseInt(cursor.getString(cursor.getColumnIndex("id")));
            String firstname = cursor.getString(cursor.getColumnIndex("firstname"));
            String email = cursor.getString(cursor.getColumnIndex("email"));
            String lastname = cursor.getString(cursor.getColumnIndex("lastname"));
            String phone = cursor.getString(cursor.getColumnIndex("phone"));
            String city=cursor.getString(cursor.getColumnIndex("city"));
            objectTeacher = new ObjectTeacher();
            objectTeacher.id = id;
            objectTeacher.firstname = firstname;
            objectTeacher.email = email;
            objectTeacher.lastname = lastname;
            objectTeacher.phone = phone;
            objectTeacher.city=city;
        }
        cursor.close();
        db.close();
        return objectTeacher;
    }

    public boolean update(ObjectTeacher objectTeacher) {

        ContentValues values = new ContentValues();
        values.put("firstname", objectTeacher.firstname);
        values.put("email", objectTeacher.email);
        values.put("lastname", objectTeacher.lastname);
        values.put("phone", objectTeacher.phone);
        values.put("city", objectTeacher.city);
        String where = "id = ?";
        String[] whereArgs = { Integer.toString(objectTeacher.id) };
        SQLiteDatabase db = this.getWritableDatabase();
        boolean updateSuccessful = db.update("teacher", values, where, whereArgs) > 0;
        db.close();
        return updateSuccessful;
    }

    public boolean delete(int id) {
        boolean deleteSuccessful = false;

        SQLiteDatabase db = this.getWritableDatabase();
        deleteSuccessful = db.delete("teacher", "id ='" + id + "'", null) > 0;
        db.close();

        return deleteSuccessful;

    }

}
