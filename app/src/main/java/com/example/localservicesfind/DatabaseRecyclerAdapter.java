package com.example.localservicesfind;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DatabaseRecyclerAdapter extends RecyclerView.Adapter<DatabaseRecyclerAdapter.DatabaseViewHolder> {

    ArrayList<ModelClass> modelClassArrayList;

    public DatabaseRecyclerAdapter(ArrayList<ModelClass> modelClassArrayList) {
        this.modelClassArrayList = modelClassArrayList;
    }


    @NonNull
    @Override
    public DatabaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View singleRow = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.single_row, viewGroup, false);
        return new DatabaseViewHolder(singleRow);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull DatabaseViewHolder databaseViewHolder, int i) {
        ModelClass modelClass = modelClassArrayList.get(i);
        databaseViewHolder.name.setText(modelClass.getName());
        databaseViewHolder.mobile.setText(Integer.toString(modelClass.getMobile()));
        databaseViewHolder.street.setText(modelClass.getStreet());
        databaseViewHolder.address.setText(modelClass.getAddress());
        databaseViewHolder.pin.setText(Integer.toString(modelClass.getPin()));
        databaseViewHolder.category.setText(modelClass.getCategory());
    }

    @Override
    public int getItemCount() {
        return modelClassArrayList.size();
    }

    public static class DatabaseViewHolder extends RecyclerView.ViewHolder {
        TextView name, mobile, street, address, pin, category;
        public DatabaseViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            mobile = itemView.findViewById(R.id.mobile);
            street = itemView.findViewById(R.id.street);
            address = itemView.findViewById(R.id.address);
            pin = itemView.findViewById(R.id.pin);
            category = itemView.findViewById(R.id.category);

        }
    }

}
