package com.example.service_novigrad.view.recyclerView.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.service_novigrad.R;
import com.example.service_novigrad.entity.Branch;
import com.example.service_novigrad.view.BranchUpdateDialog;
import com.example.service_novigrad.view.recyclerView.view_holder.BranchViewHolder;

import java.util.List;

public class BranchesRecyclerVIewAdapter extends RecyclerView.Adapter<BranchViewHolder> {

    private List<Branch> branches;
    private Context context;
    private CardView cardView;

    public BranchesRecyclerVIewAdapter(List<Branch> branches, Context context) {
        this.context = context;
        this.branches = branches;
    }

    @NonNull
    @Override
    public BranchViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.branch_card_view, viewGroup, false);
        cardView = (CardView) view;
        return new BranchViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull final BranchViewHolder branchViewHolder, final int i) {
        final Branch branch = branches.get(i);
        branchViewHolder.code.setText("Branch Code : " + String.valueOf(branch.getBranchCode()));
        if(branch.getAddress() != null) {
            branchViewHolder.address.setText("Address : " + branch.getAddress());
        }
        if(branch.getOpeningHours() != null) {
            branchViewHolder.opening_hours.setText("Opening Hours : " + branch.getOpeningHours());
        }
        if(branch.getCity() != null) {
            branchViewHolder.city.setText("City : " + branch.getCity());
        }

        branchViewHolder.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(branch.getBranchCode(),i,branch.getOpeningHours());
            }
        });
    }

    void openDialog(int code,int index,String opening_hours){
        BranchUpdateDialog dialog = new BranchUpdateDialog();
        Bundle args = new Bundle();
        args.putInt("CODE",code);
        args.putInt("INDEX",index);
        args.putString("OPENING_HOURS",opening_hours);
        dialog.setArguments(args);
        FragmentManager manager = ((AppCompatActivity)context).getSupportFragmentManager();
        dialog.show(manager,"");
    }


    @Override
    public int getItemCount()
    {
        return branches == null ? 0 : branches.size();
    }
}


