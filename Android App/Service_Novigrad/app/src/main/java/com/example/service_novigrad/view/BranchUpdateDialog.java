package com.example.service_novigrad.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.service_novigrad.R;
import com.example.service_novigrad.dao.BranchDao;

public class BranchUpdateDialog extends AppCompatDialogFragment {

    private Boolean validate = false;
    private UpdateListener listener;
    private TextView txt_opening_hours;
    private BranchDao dao;
    private int code,index;
    private String opening_hours;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        validate = false;
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.activity_branch_dialog, null);
        this.dao = new BranchDao(this.getContext());
        this.code = getArguments().getInt("CODE");
        this.index = getArguments().getInt("INDEX");
        this.opening_hours = getArguments().getString("OPENING_HOURS");
        this.txt_opening_hours = view.findViewById(R.id.txt_edit_opening_hours);
        this.txt_opening_hours.setText(opening_hours);
        builder.setView(view)
                .setCancelable(false)
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Paid", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        final AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().setLayout(600,250);
        alertDialog.show();

        Button theButton = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
        theButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate = true;
                if(txt_opening_hours.getText().toString().isEmpty()){
                    validate = false;
                    txt_opening_hours.setError("Opening Hours is required!");
                }
                if(validate){
                    alertDialog.dismiss();
                    alertMessage(code,index,txt_opening_hours.getText().toString());
                }

            }
        });

        return alertDialog;
    }

    private void alertMessage(final int code, final int index,final String opening_hours) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Confirm");
        builder.setMessage("Are you sure?")
                .setPositiveButton("CANCEL", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("YES", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                        listener.sendData(code,index,opening_hours);
                    }
                });
        builder.create().show();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (UpdateListener) context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString() + " must implement UpdateListener ");
        }

    }

    public interface UpdateListener{
        void sendData(int code,int index,String updatedHours);
    }

}
