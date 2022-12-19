package com.example.service_novigrad.dao;

import com.example.service_novigrad.entity.Branch;

import java.util.List;

public interface IBranchDao {
    List<Branch> getAll();
    boolean updateOpeningHours(int code,String opening_hours);
}
