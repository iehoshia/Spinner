package com.holamundo.app.holamundo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Iehoshia on 28/03/2016.
 */
public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    protected static final String DATABASE_NAME = "StudentDatabase";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "DROP TABLE IF EXISTS students";
        db.execSQL(sql);

        sql = "CREATE TABLE students " +
                "( id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "firstname TEXT, " +
                "lastname TEXT, " +
                "phone TEXT, " +
                "email TEXT, " +


                "city TEXT ) ";


        db.execSQL(sql);


        String sql2 = "DROP TABLE IF EXISTS teacher";
        db.execSQL(sql2);

        sql2 = "CREATE TABLE teacher " +
                "( id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "firstname TEXT, " +
                "lastname TEXT, " +
                "phone TEXT, " +
                "email TEXT, " +
                "city TEXT ) ";


        db.execSQL(sql2);

        String sql3 = "DROP TABLE IF EXISTS course";
        db.execSQL(sql3);

        sql3 = "CREATE TABLE course " +
                "( id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT, " +
                "hourly TEXT, " +
                "classroom TEXT," +
                "teacher TEXT ) ";



        db.execSQL(sql3);

 /*       String sql4 = "DROP TABLE IF EXISTS student_course";
        db.execSQL(sql4);

        sql4 = "CREATE TABLE student_course " +
                "( id INTEGER PRIMARY KEY AUTOINCREMENT, " +

                "FOREIGN_KEY(students__id)REFERENCES students(id),"+
                "FOREIGN_KEY(course_id)REFERENCES course(id)";



        db.execSQL(sql4);
*/

    }






    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS students";
        db.execSQL(sql);
        onCreate(db);

        String sql2 = "DROP TABLE IF EXISTS teacher";
        db.execSQL(sql2);
        onCreate(db);

        String sql3 = "DROP TABLE IF EXISTS course";
        db.execSQL(sql3);
        onCreate(db);

 /*       String sql4 = "DROP TABLE IF EXISTS student_course";
        db.execSQL(sql4);
      onCreate(db);
*/



    }
}
