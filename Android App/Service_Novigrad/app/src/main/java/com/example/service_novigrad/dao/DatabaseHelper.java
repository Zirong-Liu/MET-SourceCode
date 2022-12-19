package com.example.service_novigrad.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.service_novigrad.entity.Admin;
import com.example.service_novigrad.entity.Customer;
import com.example.service_novigrad.entity.Employee;
import com.example.service_novigrad.entity.User;

public class DatabaseHelper extends SQLiteOpenHelper
{
    public static final String ADMIN = "admin";
    public static final String CUSTOMER = "customer";
    public static final String EMPLOYEE = "employee";
    public static final String BRANCH = "branch";
    public static final String COL_FIRSTNAME = "firstname";
    public static final String COL_LASTNAME = "lastname";
    public static final String COL_EMAIL = "email";
    public static final String COL_PASSWORD = "password";
    public static final String COL_BRANCH_CODE = "code";
    public static final String COL_BRANCH_ADDRESS = "address";
    public static final String COL_BRANCH_CITY = "city";
    public static final String COL_BRANCH_OPENING_HOURS = "opening_hours";

    public DatabaseHelper(@Nullable Context context)
    {
        super(context, "serviceNov.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
{
    String createTableEmp= "create table " + EMPLOYEE + " (" + COL_EMAIL + " text primary key, " +COL_FIRSTNAME + " text, "+COL_LASTNAME+" text, " + COL_PASSWORD + " text)";
    db.execSQL(createTableEmp);
    String createTableCus= "create table " + CUSTOMER + " (" + COL_EMAIL + " text primary key, " +COL_FIRSTNAME + " text, "+COL_LASTNAME+" text, " + COL_PASSWORD + " text)";
    db.execSQL(createTableCus);
    String createTableAdmin= "create table " + ADMIN + " (" + COL_EMAIL + " text primary key, " +COL_FIRSTNAME + " text, "+COL_LASTNAME+" text, " + COL_PASSWORD + " text)";
    db.execSQL(createTableAdmin);
    String createBranchTable = "create table " + BRANCH + " (" + COL_BRANCH_CODE + " INTEGER primary key, " + COL_BRANCH_ADDRESS + " text, " + COL_BRANCH_CITY + " text, " + COL_BRANCH_OPENING_HOURS + " text)";
    db.execSQL(createBranchTable);
    String insertAdmin="INSERT INTO admin (email, firstname, lastname, password) VALUES('admin@adm.com', 'Administrator', '1', 'admin12345');";
    db.execSQL(insertAdmin);
    db.execSQL("INSERT INTO branch VALUES(100, '2035 33rd Ave Sw', 'Calgary', '08:00 am - 17:30 pm');");
    db.execSQL("INSERT INTO branch VALUES(120, '75 Crowfoot Way Nw', 'Calgary', '08:00 am - 17:30 pm');");
    db.execSQL("INSERT INTO branch VALUES(220, '1068 Communications Bldg', 'Cross Lake', '08:00 am - 17:30 pm');");
}

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

    }
}
