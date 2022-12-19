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
import com.example.service_novigrad.dao.CustomerDao;
import com.example.service_novigrad.dao.EmployeeDao;
import com.example.service_novigrad.dao.ICustomerDao;
import com.example.service_novigrad.dao.IEmployeeDao;
import com.example.service_novigrad.entity.Customer;
import com.example.service_novigrad.entity.Employee;

public class RegisterWindow extends AppCompatActivity {
    Button btReg;
    EditText edFirstName, edLastName, edEmail, edPwd, edCfPwd;
    RadioButton rdEmp, rdCus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_window);

        btReg=findViewById(R.id.btReg);
        edFirstName=findViewById(R.id.tfFirstName);
        edLastName=findViewById(R.id.tfLastName);
        edEmail=findViewById(R.id.tfEmail);
        edPwd=findViewById(R.id.tfPwd);
        edCfPwd=findViewById(R.id.tfCfPwd);
        rdEmp=findViewById(R.id.rdEmp);
        rdCus=findViewById(R.id.rdCus);

        btReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rdEmp.isChecked())
                {
                    Employee emp;
                    switch(isValid(edFirstName.getText().toString(), edLastName.getText().toString(),
                            edEmail.getText().toString(), edPwd.getText().toString(), edCfPwd.getText().toString(), rdEmp, rdCus))
                    {
                        case 0:
                            emp=new Employee(edFirstName.getText().toString(), edLastName.getText().toString(),
                                    edEmail.getText().toString(), edPwd.getText().toString());
                            Toast.makeText(RegisterWindow.this, emp.toString(), Toast.LENGTH_SHORT).show();
                            //insert to database
                            IEmployeeDao empDao=new EmployeeDao(RegisterWindow.this);
                            boolean bool=empDao.addEmployee(emp);
                            if(bool)
                            {
                                //jump to Welcome
                                Intent intent = new Intent(RegisterWindow.this, WelcomeMainWindow.class);
                                intent.putExtra("firstName", edFirstName.getText().toString());
                                intent.putExtra("userType", "Employee");
                                startActivity(intent);
                                RegisterWindow.this.finish();
                            }
                            else
                            {
                                Toast.makeText(RegisterWindow.this, "The Email address has been registered for use.", Toast.LENGTH_SHORT).show();
                            }
                            break;
                        case -1:
                            Toast.makeText(RegisterWindow.this, "First name cannot be empty.", Toast.LENGTH_SHORT).show();
                            break;
                        case -2:
                            Toast.makeText(RegisterWindow.this, "Last name cannot be empty.", Toast.LENGTH_SHORT).show();
                            break;
                        case -3:
                            Toast.makeText(RegisterWindow.this, "Email cannot be empty.", Toast.LENGTH_SHORT).show();
                            break;
                        case -4:
                            Toast.makeText(RegisterWindow.this, "Password cannot be empty.", Toast.LENGTH_SHORT).show();
                            break;
                        case -5:
                            Toast.makeText(RegisterWindow.this, "Confirm Password cannot be empty.", Toast.LENGTH_SHORT).show();
                            break;
                        case -6:
                            Toast.makeText(RegisterWindow.this, "Invalid Email format.", Toast.LENGTH_SHORT).show();
                            break;
                        case -7:
                            Toast.makeText(RegisterWindow.this, "Password and Confirm Password are different.", Toast.LENGTH_SHORT).show();
                            edPwd.setText("");
                            edCfPwd.setText("");
                            break;
                        case -8:
                            Toast.makeText(RegisterWindow.this, "You must select one of those, Employee or Customer.", Toast.LENGTH_SHORT).show();
                            break;
                        case -9:
                            Toast.makeText(RegisterWindow.this, "The length of the password must be at least 6.", Toast.LENGTH_SHORT).show();
                            break;
                        case -10:
                            Toast.makeText(RegisterWindow.this, "Invalid Email Address.", Toast.LENGTH_SHORT).show();
                            break;
                        default:
                            break;
                    }
                }
                if(rdCus.isChecked())
                {
                    Customer cus;
                    switch(isValid(edFirstName.getText().toString(), edLastName.getText().toString(),
                            edEmail.getText().toString(), edPwd.getText().toString(), edCfPwd.getText().toString(), rdEmp, rdCus))
                    {
                        case 0:
                            cus=new Customer(edFirstName.getText().toString(), edLastName.getText().toString(),
                                    edEmail.getText().toString(), edPwd.getText().toString());
                            Toast.makeText(RegisterWindow.this, cus.toString(), Toast.LENGTH_SHORT).show();
                            //insert to database
                            ICustomerDao cusDao=new CustomerDao(RegisterWindow.this);
                            boolean bool=cusDao.addCustomer(cus);
                            if(bool)
                            {
                                //jump to main Welcome
                                Intent intent = new Intent(RegisterWindow.this, WelcomeMainWindow.class);
                                intent.putExtra("firstName", edFirstName.getText().toString());
                                intent.putExtra("userType", "Customer");
                                startActivity(intent);
                                RegisterWindow.this.finish();
                            }
                            else
                            {
                                Toast.makeText(RegisterWindow.this, "The Email address has been registered for use.", Toast.LENGTH_SHORT).show();
                            }
                            break;
                        case -1:
                            Toast.makeText(RegisterWindow.this, "First name cannot be empty.", Toast.LENGTH_SHORT).show();
                            break;
                        case -2:
                            Toast.makeText(RegisterWindow.this, "Last name cannot be empty.", Toast.LENGTH_SHORT).show();
                            break;
                        case -3:
                            Toast.makeText(RegisterWindow.this, "Email cannot be empty.", Toast.LENGTH_SHORT).show();
                            break;
                        case -4:
                            Toast.makeText(RegisterWindow.this, "Password cannot be empty.", Toast.LENGTH_SHORT).show();
                            break;
                        case -5:
                            Toast.makeText(RegisterWindow.this, "Confirm Password cannot be empty.", Toast.LENGTH_SHORT).show();
                            break;
                        case -6:
                            Toast.makeText(RegisterWindow.this, "Invalid Email format.", Toast.LENGTH_SHORT).show();
                            break;
                        case -7:
                            Toast.makeText(RegisterWindow.this, "Password and Confirm Password are different.", Toast.LENGTH_SHORT).show();
                            edPwd.setText("");
                            edCfPwd.setText("");
                            break;
                        case -8:
                            Toast.makeText(RegisterWindow.this, "You must select one of those, Employee or Customer.", Toast.LENGTH_SHORT).show();
                            break;
                        case -9:
                            Toast.makeText(RegisterWindow.this, "The length of the password must be at least 6.", Toast.LENGTH_SHORT).show();
                            break;
                        case -10:
                            Toast.makeText(RegisterWindow.this, "Invalid Email Address.", Toast.LENGTH_SHORT).show();
                            break;
                        default:
                            break;
                    }
                }
            }

        });
    }
    
    public int isValid(String firstName, String lastName, String email, String pwd, String cfPwd, RadioButton rdEmp, RadioButton rdCus)
    {
        if(firstName==null || firstName.equals(""))
        {
            return -1;
        }
        if(lastName==null || lastName.equals(""))
        {
            return -2;
        }
        if(email==null || email.equals(""))
        {
            return -3;
        }
        if(pwd==null || pwd.equals(""))
        {
            return -4;
        }
        if(cfPwd==null || cfPwd.equals(""))
        {
            return -5;
        }
        if(!isEmail(email))
        {
            return -6;
        }
        if(!pwd.equals(cfPwd))
        {
            return -7;
        }
        if(!rdEmp.isChecked() && !rdCus.isChecked())
        {
            return -8;
        }
        if(pwd.length()<6)
        {
            return -9;
        }
        if(email.equals("admin@adm.com"))
        {
            return -10;
        }
        return 0;
    }
    public static Boolean isEmail(String str)
    {
        Boolean isEmail = false;
        String expr = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})$";

        if (str.matches(expr)) {
            isEmail = true;
        }
        return isEmail;
    }

}
