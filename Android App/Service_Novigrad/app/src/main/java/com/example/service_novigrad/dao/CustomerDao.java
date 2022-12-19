package com.example.service_novigrad.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.service_novigrad.entity.Customer;
import com.example.service_novigrad.entity.User;

import java.util.ArrayList;
import java.util.List;

public class CustomerDao implements ICustomerDao
{
    public static final String CUSTOMER = "customer";
    public static final String COL_FIRSTNAME = "firstname";
    public static final String COL_LASTNAME = "lastname";
    public static final String COL_EMAIL = "email";
    public static final String COL_PASSWORD = "password";

    private DatabaseHelper dbHelper;
    public CustomerDao(@Nullable Context context)
    {
        dbHelper=new DatabaseHelper(context);
    }

    @Override
    public boolean addCustomer(Customer cus)
    {
        SQLiteDatabase db=dbHelper.getWritableDatabase();

        ContentValues cv=new ContentValues();
        cv.put(COL_FIRSTNAME, cus.getFirstName());
        cv.put(COL_LASTNAME, cus.getLastName());
        cv.put(COL_EMAIL, cus.getEmail());
        cv.put(COL_PASSWORD, cus.getPassword());

        long insert=db.insert(CUSTOMER, null, cv);
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
    public User selectCustomer(String emailIn, String pwdIn)
    {
        User cus=new Customer("default", "default", "default", "default");
        SQLiteDatabase db=dbHelper.getReadableDatabase();
        try
        {
            String select="SELECT * FROM customer WHERE email='"+emailIn+"' AND password='"+pwdIn+"';";
            Cursor cursor=db.rawQuery(select, null);
            if(cursor.moveToFirst())
            {
                String email=cursor.getString(0);
                String firstName=cursor.getString(1);
                String lastName=cursor.getString(2);
                String password=cursor.getString(3);

                cus.setEmail(email);
                cus.setFirstName(firstName);
                cus.setLastName(lastName);
                cus.setPassword(password);
            }
            cursor.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        db.close();
        return cus;
    }

    @Override
    public List<User> getAll()
    {
        List<User> returnList=new ArrayList<User>();
        String query="select * from "+CUSTOMER;
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

                User emp=new Customer(firstName, lastName, email, password);
                returnList.add(emp);
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return returnList;
    }
    @Override
    public boolean deleteCus(String email)
    {
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        String query="delete from "+CUSTOMER+" where "+COL_EMAIL+" = '"+email+"'";
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
