package com.example.service_novigrad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.service_novigrad.view.LoginWindow;
import com.example.service_novigrad.view.RegisterWindow;

public class MainActivity extends AppCompatActivity {
    Button bt2Register, bt2Login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt2Register=(Button)findViewById(R.id.bt2Register);
        bt2Login=(Button)findViewById(R.id.bt2Login);

        bt2Register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterWindow.class);
                startActivity(intent);
                //MainActivity.this.finish();
            }
        });
        bt2Login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginWindow.class);
                startActivity(intent);
                //MainActivity.this.finish();
            }
        });
    }
}