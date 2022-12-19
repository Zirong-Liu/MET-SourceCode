package com.example.service_novigrad.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.service_novigrad.entity.Employee;
import com.example.service_novigrad.entity.User;

import java.util.ArrayList;
import java.util.List;

public class EmployeeDao implements IEmployeeDao
{
    public static final String EMPLOYEE = "employee";
    public static final String COL_FIRSTNAME = "firstname";
    public static final String COL_LASTNAME = "lastname";
    public static final String COL_EMAIL = "email";
    public static final String COL_PASSWORD = "password";

    private DatabaseHelper dbHelper;
    public EmployeeDao(@Nullable Context context)
    {
        dbHelper=new DatabaseHelper(context);
    }

    @Override
    public boolean addEmployee(Employee emp)
    {
        SQLiteDatabase db=dbHelper.getWritableDatabase();

        ContentValues cv=new ContentValues();
        cv.put(COL_FIRSTNAME, emp.getFirstName());
        cv.put(COL_LASTNAME, emp.getLastName());
        cv.put(COL_EMAIL, emp.getEmail());
        cv.put(COL_PASSWORD, emp.getPassword());

        long insert=db.insert(EMPLOYEE, null, cv);
        if(insert==-1)
        {
            db.close();
            return false;
        }
        else
        {
            db.close();
            return true;
        }
    }

    @Override
    public User selectEmployee(String emailIn, String pwdIn)
    {
        User emp=new Employee("default", "default", "default", "default");
        SQLiteDatabase db=dbHelper.getReadableDatabase();
        try
        {
            String select="SELECT * FROM employee WHERE email='"+emailIn+"' AND password='"+pwdIn+"';";
            Cursor cursor=db.rawQuery(select, null);
            if(cursor.moveToFirst())
            {
                String email=cursor.getString(0);
                String firstName=cursor.getString(1);
                String lastName=cursor.getString(2);
                String password=cursor.getString(3);

                emp.setEmail(email);
                emp.setFirstName(firstName);
                emp.setLastName(lastName);
                emp.setPassword(password);
            }
            cursor.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        db.close();
        return emp;
    }

    @Override
    public List<User> getAll()
    {
        List<User> returnList=new ArrayList<User>();
        String query="select * from "+EMPLOYEE;
        SQLiteDatabase db=dbHelper.getReadableDatabase();
        Cursor cursor=db.rawQuery(query, null);
        if(cursor.moveToFirst())
        {
            do
            {
                String email=cursor.getString(0);
                String firstName=cursor.getString(1);
                String lastName=cursor.getString(2);
                String password=cursor.getString(3);

                User emp=new Employee(firstName, lastName, email, password);
                returnList.add(emp);
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return returnList;
    }
    @Override
    public boolean deleteEmp(String email)
    {
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        String query="delete from "+EMPLOYEE+" where "+COL_EMAIL+" = '"+email+"'";
        Cursor cursor=db.rawQuery(query, null);
        if(cursor.moveToFirst())
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
