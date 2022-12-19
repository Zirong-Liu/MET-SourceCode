package com.example.service_novigrad.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.service_novigrad.MainActivity;
import com.example.service_novigrad.R;

public class WelcomeMainWindow extends AppCompatActivity {
    //welcome
    TextView viewWelcome, viewLoginAs;
    Button bt2Menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_main_window);

        viewWelcome=findViewById(R.id.welcome);
        viewLoginAs=findViewById(R.id.loginAs);
        bt2Menu=findViewById(R.id.bt2Menu);

        Intent intent=getIntent();
        String firstName=intent.getStringExtra("firstName");
        final String userType=intent.getStringExtra("userType");

        viewWelcome.setText("Welcome "+firstName);
        viewLoginAs.setText("You are logged in as "+userType);

        if(userType.equals("Employee")) {
            bt2Menu.setText("All Branches");
        } else if(userType.equals("Customer")) {
            bt2Menu.setText("All Branches");
        }

        bt2Menu.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if(userType.equals("Administrator"))
                {
                    Intent intent = new Intent(WelcomeMainWindow.this, AdminMenuWindow.class);
                    startActivity(intent);
                }
                if(userType.equals("Employee"))
                {
                    Intent intent1 = new Intent(WelcomeMainWindow.this,BranchesWindow.class);
                    startActivity(intent1);
                }
                if(userType.equals("Customer"))
                {
                    Intent intent2 = new Intent(WelcomeMainWindow.this,CustomerActivity.class);
                    startActivity(intent2);
                }
            }
        });
    }
}

