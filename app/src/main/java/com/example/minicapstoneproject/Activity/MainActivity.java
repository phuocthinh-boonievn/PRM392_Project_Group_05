package com.example.minicapstoneproject.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.example.minicapstoneproject.Adapter.ProductAdapter;
import com.example.minicapstoneproject.Model.Product;
import com.example.minicapstoneproject.R;
import com.example.minicapstoneproject.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        statusBarColor();
        initRecyclerView();
        bottomNavigation();
    }

    private void bottomNavigation() {
        binding.cartBtn.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, CartActivity.class)));
    }

    private void statusBarColor() {
        Window window = MainActivity.this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(MainActivity.this, R.color.purple_Dark));
    }

    private void initRecyclerView() {
        ArrayList<Product> items = new ArrayList<>();
        items.add(new  Product("Product 1", "item_1","D1", 4, 19.99, 4.5, 0));
        items.add(new  Product("Product 2", "item_2","D1", 4, 19.99, 4.5, 0));
        items.add(new  Product("Product 3", "item_3","D1", 4, 19.99, 4.5, 0));

        binding.PopularView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        binding.PopularView.setAdapter(new ProductAdapter(items));
    }

    public void profile(View view) {
        startActivity(new Intent(MainActivity.this,LoginActivity.class));
    }
}