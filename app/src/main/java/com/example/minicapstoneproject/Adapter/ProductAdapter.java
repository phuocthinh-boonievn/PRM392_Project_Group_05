package com.example.minicapstoneproject.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners;
import com.example.minicapstoneproject.Activity.DetailActivity;
import com.example.minicapstoneproject.Model.Product;
import com.example.minicapstoneproject.databinding.ViewholderPupListBinding;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    ArrayList<Product> items;
    Context context;
    ViewholderPupListBinding binding;

    public ProductAdapter(ArrayList<Product> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ViewholderPupListBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        context = parent.getContext();
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ViewHolder holder, int position) {
        ViewholderPupListBinding binding = holder.binding; // Lấy ra binding của ViewHolder hiện tại

        binding.titleTxt.setText(items.get(position).getTitle());
        binding.priceTxt.setText(items.get(position).getPrice() + "$");
        binding.scoreTxt.setText(items.get(position).getScore() + "");

        int drawableResourceID = holder.itemView.getResources().getIdentifier(items.get(position).getImageURL()
                , "drawable", holder.itemView.getContext().getPackageName());

        //Load image to display using Glide framework
        Glide.with(context)
                .load(drawableResourceID)
                .transform(new GranularRoundedCorners(30, 30, 0, 30))
                .into(binding.pic);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("detail", items.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void filterList(List<Product> filteredList) {
        items = (ArrayList<Product>) filteredList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ViewholderPupListBinding binding;

        public ViewHolder(ViewholderPupListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}