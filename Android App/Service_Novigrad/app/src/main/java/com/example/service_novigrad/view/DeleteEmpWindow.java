package com.example.service_novigrad.view;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.service_novigrad.R;
import com.example.service_novigrad.dao.DatabaseHelper;
import com.example.service_novigrad.dao.EmployeeDao;
import com.example.service_novigrad.dao.IEmployeeDao;
import com.example.service_novigrad.entity.User;

import java.util.List;

public class DeleteEmpWindow extends AppCompatActivity
{
    ListView empList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete_emp_account_window);

        empList=findViewById(R.id.empList);

        showEmpList();

        empList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                User emp= (User) parent.getItemAtPosition(position);
                showDeleteDialog(emp.getEmail());
                return false;
            }
        });
    }
    public void showDeleteDialog(final String email)
    {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.delete_dialog, null);
        dialogBuilder.setView(dialogView);

        Button btDelete = (Button) dialogView.findViewById(R.id.btDelete);
        dialogBuilder.setTitle("Are you sure to delete "+email+" ?");
        final AlertDialog b = dialogBuilder.create();
        b.show();

        btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteEmp(email);
                b.dismiss();
                showEmpList();
                Toast.makeText(DeleteEmpWindow.this, "Delete "+email+" successfully", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void deleteEmp(String email)
    {
        IEmployeeDao empDao=new EmployeeDao(DeleteEmpWindow.this);
        empDao.deleteEmp(email);
    }
    public void showEmpList()
    {
        IEmployeeDao empDao=new EmployeeDao(DeleteEmpWindow.this);
        final List<User> allEmp=empDao.getAll();
        //Toast.makeText(DeleteEmpWindow.this, allEmp.toString(), Toast.LENGTH_SHORT).show();
        ArrayAdapter adp=new ArrayAdapter<User>(DeleteEmpWindow.this, android.R.layout.simple_list_item_1, allEmp);
        empList.setAdapter(adp);
    }
}
