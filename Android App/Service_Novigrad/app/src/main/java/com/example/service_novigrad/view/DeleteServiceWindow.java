package com.example.service_novigrad.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.service_novigrad.R;
import com.example.service_novigrad.dao.EmployeeDao;
import com.example.service_novigrad.dao.IEmployeeDao;
import com.example.service_novigrad.entity.Service;
import com.example.service_novigrad.entity.User;

import java.util.ArrayList;
import java.util.List;

public class DeleteServiceWindow extends AppCompatActivity
{
    ListView serviceList;
    final List allService=new ArrayList();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete_service_window);

        serviceList =findViewById(R.id.serviceList);

        allService.add("Driver License");
        allService.add("Health Card");
        allService.add("Photo ID");
        showServiceList();

        serviceList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                String s= (String) parent.getItemAtPosition(position);
                showDeleteDialog(s);
                return false;
            }
        });
    }
    public void showDeleteDialog(final String service)
    {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.delete_dialog, null);
        dialogBuilder.setView(dialogView);

        Button btDelete = (Button) dialogView.findViewById(R.id.btDelete);
        dialogBuilder.setTitle("Are you sure to delete "+service+" ?");
        final AlertDialog b = dialogBuilder.create();
        b.show();

        btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteService(service);
                b.dismiss();
                showServiceList();
                Toast.makeText(DeleteServiceWindow.this, "Delete "+service+" successfully", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void deleteService(String service)
    {
        allService.remove(service);
    }
    public void showServiceList()
    {
        ArrayAdapter adp=new ArrayAdapter<User>(DeleteServiceWindow.this, android.R.layout.simple_list_item_1, allService);
        serviceList.setAdapter(adp);
    }
}
