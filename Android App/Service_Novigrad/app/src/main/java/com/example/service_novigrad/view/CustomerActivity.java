package com.example.service_novigrad.view;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.service_novigrad.R;
import com.example.service_novigrad.dao.BranchDao;
import com.example.service_novigrad.entity.Branch;
import com.example.service_novigrad.view.recyclerView.adapter.ViewBranchesRecyclerViewAdapter;

import java.util.List;

public class CustomerActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private ViewBranchesRecyclerViewAdapter adapter;
    private List<Branch> branches;
    private BranchDao dao;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);
        toolbar = (Toolbar) findViewById(R.id.toolbar_branches_view);
        toolbar.setTitle("Branches");
        this.toolbar.setTitleTextAppearance(this,R.style.ActionBarTitle);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recyclerView = findViewById(R.id.recycler_customer_branches);
        dao = new BranchDao(this);

        searchView = (SearchView) findViewById(R.id.search_view);
        searchView.setMaxWidth(Integer.MAX_VALUE);

        this.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchView.setIconified(true);
                finish();
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });


        branches = dao.getAll();
        loadRecyclerView();
    }

    void loadRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ViewBranchesRecyclerViewAdapter(branches,this);
        recyclerView.setAdapter(adapter);
    }
}