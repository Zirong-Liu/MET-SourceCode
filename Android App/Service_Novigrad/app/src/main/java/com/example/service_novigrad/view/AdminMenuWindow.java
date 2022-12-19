package com.example.service_novigrad.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.service_novigrad.MainActivity;
import com.example.service_novigrad.R;

public class AdminMenuWindow extends AppCompatActivity
{
    Button bt2Create, bt2Edit, bt2DeleteService, bt2DeleteEmp, bt2DeleteCus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_menu);

        bt2Create=findViewById(R.id.bt2Create);
        bt2Edit=findViewById(R.id.bt2Edit);
        bt2DeleteService=findViewById(R.id.bt2DeleteService);
        bt2DeleteEmp=findViewById(R.id.bt2DeleteEmp);
        bt2DeleteCus=findViewById(R.id.bt2DeleteCus);

        bt2DeleteEmp.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminMenuWindow.this, DeleteEmpWindow.class);
                startActivity(intent);
            }
        });
        bt2DeleteCus.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminMenuWindow.this, DeleteCusWindow.class);
                startActivity(intent);
            }
        });
        bt2DeleteService.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminMenuWindow.this, DeleteServiceWindow.class);
                startActivity(intent);
            }
        });
        bt2Create.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminMenuWindow.this, CreateServiceWindow.class);
                startActivity(intent);
            }
        });
        bt2Edit.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminMenuWindow.this, EditServiceWindow.class);
                startActivity(intent);
            }
        });
    }
}
