package com.example.service_novigrad.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.service_novigrad.entity.Customer;
import com.example.service_novigrad.entity.User;

import java.util.List;

public interface ICustomerDao
{
    boolean addCustomer(Customer cus);
    User selectCustomer(String emailIn, String pwdIn);
    List<User> getAll();
    boolean deleteCus(String email);
}
