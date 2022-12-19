package com.example.service_novigrad.dao;

import com.example.service_novigrad.entity.User;

import java.util.List;

public interface IAdminDao
{
    User selectAdmin(String emailIn, String pwdIn);
}
