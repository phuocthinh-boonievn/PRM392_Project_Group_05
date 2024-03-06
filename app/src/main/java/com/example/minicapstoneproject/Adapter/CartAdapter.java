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
import com.example.minicapstoneproject.Utils.CartManagement;
import com.example.minicapstoneproject.Utils.ChangeNumberItemsListener;
import com.example.minicapstoneproject.databinding.ViewholderCartBinding;
import com.example.minicapstoneproject.databinding.ViewholderPupListBinding;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    ArrayList<Product> items;
    Context context;
    ViewholderCartBinding binding;
    ChangeNumberItemsListener changeNumberItemsListener;
    CartManagement cartManagement;

    public CartAdapter(ArrayList<Product> items, ChangeNumberItemsListener changeNumberItemsListener) {
        this.items = items;
        this.changeNumberItemsListener = changeNumberItemsListener;
    }

    @NonNull
    @Override
    public CartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ViewholderCartBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        context = parent.getContext();
        cartManagement = new CartManagement(context);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.ViewHolder holder, int position) {
        binding.titleTxt.setText(items.get(position).getTitle());
        binding.feeEachItem.setText("$"+items.get(position).getPrice());
        binding.totalEachItem.setText("$"+Math.round(items.get(position).getNumberInCart()*items.get(position).getPrice()));
        binding.numberItemTxt.setText(String.valueOf(items.get(position).getNumberInCart()));

        int drawableResourceID = holder.itemView.getResources().getIdentifier(items.get(position).getImageURL()
        , "drawable", holder.itemView.getContext().getPackageName());

        //Load image to display using Glide framework
        Glide.with(context)
                .load(drawableResourceID)
                .transform(new GranularRoundedCorners(30,30,0,30))
                .into(binding.pic);

        binding.plusCartBtn.setOnClickListener(v -> cartManagement.plusNumberItem(items, position, () -> {
            notifyDataSetChanged();
            changeNumberItemsListener.change();
        }));

        binding.minusCartItem.setOnClickListener(v -> cartManagement.minusNumberItem(items, position, () -> {
            notifyDataSetChanged();
            changeNumberItemsListener.change();
        }));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ViewHolder(ViewholderCartBinding binding){
            super(binding.getRoot());
        }
    }
}
