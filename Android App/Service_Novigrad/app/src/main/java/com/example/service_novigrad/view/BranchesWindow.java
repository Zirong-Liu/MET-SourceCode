package com.example.service_novigrad.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.service_novigrad.R;
import com.example.service_novigrad.dao.BranchDao;
import com.example.service_novigrad.entity.Branch;
import com.example.service_novigrad.view.recyclerView.adapter.BranchesRecyclerVIewAdapter;

import java.util.List;

public class BranchesWindow extends AppCompatActivity implements BranchUpdateDialog.UpdateListener {

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private BranchesRecyclerVIewAdapter adapter;
    private List<Branch> branches;
    private BranchDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_branches_window);
        toolbar = (Toolbar) findViewById(R.id.toolbar_branches);
        toolbar.setTitle("Branches");
        this.toolbar.setTitleTextAppearance(this,R.style.ActionBarTitle);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recyclerView = findViewById(R.id.recycler_employee_branches);
        dao = new BranchDao(this);
        branches = dao.getAll();
        loadRecyclerView();
    }

    void loadRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new BranchesRecyclerVIewAdapter(branches,this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void sendData(int code, int index, String updatedHours) {
        boolean flag = dao.updateOpeningHours(code,updatedHours);
            adapter.notifyItemChanged(index);
        Toast.makeText(this,"Updated Opening Hours!",Toast.LENGTH_LONG).show();
    }
}