package com.example.service_novigrad.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.service_novigrad.entity.Branch;
import com.example.service_novigrad.entity.Customer;
import com.example.service_novigrad.entity.User;

import java.util.ArrayList;
import java.util.List;

public class BranchDao implements IBranchDao{

    private DatabaseHelper dbHelper;
    public BranchDao(@Nullable Context context)
    {
        dbHelper = new DatabaseHelper(context);
    }

    @Override
    public List<Branch> getAll() {
        List<Branch> returnList = new ArrayList();
        String query="select * from " + DatabaseHelper.BRANCH;
        SQLiteDatabase db= dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst())
        {
            do
            {
                int code =cursor.getInt(0);
                String address = cursor.getString(1);
                String city = cursor.getString(2);
                String opening_hours = cursor.getString(3);
                returnList.add(new Branch(code,address,city,opening_hours));
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return returnList;
    }

    @Override
    public boolean updateOpeningHours(int code, String opening_hours) {
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        String query="update " + DatabaseHelper.BRANCH + " set " + DatabaseHelper.COL_BRANCH_OPENING_HOURS + "='" + opening_hours + "' where " + DatabaseHelper.COL_BRANCH_CODE + "=" + code +";";
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
