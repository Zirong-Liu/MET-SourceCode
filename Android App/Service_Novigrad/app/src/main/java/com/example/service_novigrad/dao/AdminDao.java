package com.example.service_novigrad.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.service_novigrad.entity.Admin;
import com.example.service_novigrad.entity.Customer;
import com.example.service_novigrad.entity.User;

public class AdminDao implements IAdminDao
{
    private DatabaseHelper dbHelper;
    public AdminDao(@Nullable Context context)
    {
        dbHelper=new DatabaseHelper(context);
    }

    @Override
    public User selectAdmin(String emailIn, String pwdIn)
    {
        User adm=new Admin("default", "default", "default", "default");
        SQLiteDatabase db=dbHelper.getReadableDatabase();
        try
        {
            String select="SELECT * FROM admin WHERE email='"+emailIn+"' AND password='"+pwdIn+"';";
            Cursor cursor=db.rawQuery(select, null);
            if(cursor.moveToFirst())
            {
                String email=cursor.getString(0);
                String firstName=cursor.getString(1);
                String lastName=cursor.getString(2);
                String password=cursor.getString(3);

                adm.setEmail(email);
                adm.setFirstName(firstName);
                adm.setLastName(lastName);
                adm.setPassword(password);
            }
            cursor.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        db.close();
        return adm;
    }
}
