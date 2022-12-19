package com.example.service_novigrad.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.service_novigrad.R;
import com.example.service_novigrad.dao.AdminDao;
import com.example.service_novigrad.dao.CustomerDao;
import com.example.service_novigrad.dao.EmployeeDao;
import com.example.service_novigrad.dao.IAdminDao;
import com.example.service_novigrad.dao.ICustomerDao;
import com.example.service_novigrad.dao.IEmployeeDao;
import com.example.service_novigrad.entity.User;

public class LoginWindow extends AppCompatActivity {

    EditText edEmail, edPwd;
    Button btLogin;
    RadioButton rdEmp, rdCus, rdAdm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_window);

        edEmail=findViewById(R.id.tfEmail);
        edPwd=findViewById(R.id.tfPwd);
        btLogin=findViewById(R.id.btLogin);
        rdEmp=findViewById(R.id.rdEmp);
        rdCus=findViewById(R.id.rdCus);
        rdAdm=findViewById(R.id.rdAdm);

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=edEmail.getText().toString();
                String password=edPwd.getText().toString();
                if(!email.equals("") && !password.equals(""))
                {
                    if(rdEmp.isChecked() || rdCus.isChecked() || rdAdm.isChecked())
                    {
                        if(rdEmp.isChecked())
                        {
                            IEmployeeDao empDao=new EmployeeDao(LoginWindow.this);
                            User emp=empDao.selectEmployee(email, password);
                            if(!emp.getEmail().equals("default"))
                            {
                                //jump to welcome
                                Intent intent = new Intent(LoginWindow.this, WelcomeMainWindow.class);
                                intent.putExtra("firstName", emp.getFirstName());
                                intent.putExtra("userType", "Employee");
                                startActivity(intent);
                                LoginWindow.this.finish();
                            }
                            else
                            {
                                Toast.makeText(LoginWindow.this, "Email or Password is wrong.", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else if(rdCus.isChecked())
                        {
                            ICustomerDao cusDao=new CustomerDao(LoginWindow.this);
                            User cus=cusDao.selectCustomer(email, password);
                            if(!cus.getEmail().equals("default"))
                            {
                                //jump
                                Intent intent = new Intent(LoginWindow.this, WelcomeMainWindow.class);
                                intent.putExtra("firstName", cus.getFirstName());
                                intent.putExtra("userType", "Customer");
                                startActivity(intent);
                                LoginWindow.this.finish();
                            }
                            else
                            {
                                Toast.makeText(LoginWindow.this, "Email or Password is wrong.", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else if(rdAdm.isChecked())
                        {
                            IAdminDao admDao=new AdminDao(LoginWindow.this);
                            User adm=admDao.selectAdmin(email, password);
                            if(!adm.getEmail().equals("default"))
                            {
                                //jump
                                Intent intent = new Intent(LoginWindow.this, WelcomeMainWindow.class);
                                intent.putExtra("firstName", adm.getFirstName());
                                intent.putExtra("userType", "Administrator");
                                startActivity(intent);
                                LoginWindow.this.finish();
                            }
                            else
                            {
                                Toast.makeText(LoginWindow.this, "Email or Password is wrong.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                    else
                    {
                        Toast.makeText(LoginWindow.this, "You must choose to log in as an employee or a customer.", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(LoginWindow.this, "Email and Password cannot be empty.", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}
