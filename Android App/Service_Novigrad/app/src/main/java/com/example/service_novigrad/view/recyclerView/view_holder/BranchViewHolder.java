package com.example.service_novigrad.view.recyclerView.view_holder;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.service_novigrad.R;

public class BranchViewHolder extends RecyclerView.ViewHolder {

    public TextView code,address,city,opening_hours;
    public Button update;
    public BranchViewHolder(@NonNull final View itemView, final Context context) {
        super(itemView);
        this.code = itemView.findViewById(R.id.txt_card_view_branch_code);
        this.address = itemView.findViewById(R.id.txt_card_view_address);
        this.city = itemView.findViewById(R.id.txt_card_view_city);
        this.opening_hours = itemView.findViewById(R.id.txt_card_view_opening_hours);
        this.update = itemView.findViewById(R.id.btn_card_view_update);
    }

}
