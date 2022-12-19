package com.example.service_novigrad.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.service_novigrad.entity.Employee;
import com.example.service_novigrad.entity.User;

import java.util.List;

public interface IEmployeeDao
{
    boolean addEmployee(Employee emp);
    User selectEmployee(String emailIn, String pwdIn);
    List<User> getAll();
    boolean deleteEmp(String email);
}
