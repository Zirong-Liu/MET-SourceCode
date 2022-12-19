package com.example.service_novigrad.view.recyclerView.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.service_novigrad.R;
import com.example.service_novigrad.entity.Branch;
import com.example.service_novigrad.view.BranchUpdateDialog;
import com.example.service_novigrad.view.recyclerView.view_holder.BranchViewHolder;

import java.util.ArrayList;
import java.util.List;

public class ViewBranchesRecyclerViewAdapter extends RecyclerView.Adapter<BranchViewHolder> implements Filterable {

    private List<Branch> branches;
    private final List<Branch> allbranches;
    private Context context;
    private CardView cardView;

    public ViewBranchesRecyclerViewAdapter(List<Branch> branches, Context context) {
        this.context = context;
        this.branches = branches;
        this.allbranches = new ArrayList<>(branches);
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
        branchViewHolder.update.setVisibility(View.INVISIBLE);
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

    private Filter branchFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            List<Branch> filteredBranches = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredBranches.addAll(allbranches);
            } else {
                String pattern = constraint.toString().toLowerCase().trim();
                for(Branch branch : allbranches) {
                    if(String.valueOf(branch.getBranchCode()).contains(pattern) || branch.getOpeningHours().toLowerCase().contains(pattern.toLowerCase()) ||
                    branch.getCity().toLowerCase().contains(pattern.toLowerCase()) || branch.getAddress().toLowerCase().contains(pattern.toLowerCase())
                    ) {
                        filteredBranches.add(branch);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredBranches;
            return results;
        }
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            branches.clear();
            branches.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

    @Override
    public Filter getFilter() {
        return this.branchFilter;
    }
}



