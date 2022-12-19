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
import com.example.service_novigrad.dao.CustomerDao;
import com.example.service_novigrad.dao.DatabaseHelper;
import com.example.service_novigrad.dao.EmployeeDao;
import com.example.service_novigrad.dao.ICustomerDao;
import com.example.service_novigrad.dao.IEmployeeDao;
import com.example.service_novigrad.entity.User;

import java.util.List;

public class DeleteCusWindow extends AppCompatActivity
{
    ListView cusList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete_cus_account_window);

        cusList=findViewById(R.id.cusList);

        showCusList();

        cusList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                User cus= (User) parent.getItemAtPosition(position);
                showDeleteDialog(cus.getEmail());
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
                deleteCus(email);
                b.dismiss();
                showCusList();
                Toast.makeText(DeleteCusWindow.this, "Delete "+email+" successfully", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void deleteCus(String email)
    {
        ICustomerDao cusDao=new CustomerDao(DeleteCusWindow.this);
        cusDao.deleteCus(email);
    }
    public void showCusList()
    {
        ICustomerDao cusDao=new CustomerDao(DeleteCusWindow.this);
        final List<User> allCus=cusDao.getAll();
        ArrayAdapter adp=new ArrayAdapter<User>(DeleteCusWindow.this, android.R.layout.simple_list_item_1, allCus);
        cusList.setAdapter(adp);
    }
}
