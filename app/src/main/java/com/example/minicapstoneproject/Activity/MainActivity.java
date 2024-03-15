package com.example.minicapstoneproject.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.text.Editable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.minicapstoneproject.Adapter.ProductAdapter;
import com.example.minicapstoneproject.Model.Product;
import com.example.minicapstoneproject.R;
import com.example.minicapstoneproject.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText searchEditText;
    private RecyclerView recyclerView;
    private ProductAdapter adapter;
    private List<Product> productList;
    private List<Product> filteredList;
    private ImageView cartButton; // Add this

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchEditText = findViewById(R.id.editTextText);
        recyclerView = findViewById(R.id.PopularView);
        cartButton = findViewById(R.id.imageView29); // Initialize cart button

        productList = new ArrayList<>();
        filteredList = new ArrayList<>();

        productList.add(new Product("Product 1", "item_1","D1", 4, 19.99, 4.5, 0));
        productList.add(new Product("Product 2", "item_2","D1", 4, 19.99, 4.5, 0));
        productList.add(new Product("Product 3", "item_3","D1", 4, 19.99, 4.5, 0));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ProductAdapter((ArrayList<Product>) productList);
        recyclerView.setAdapter(adapter);

        filteredList.addAll(productList);

        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filter(s.toString());
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });
        cartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open ActivityCart
                Intent intent = new Intent(MainActivity.this, CartActivity.class);
                startActivity(intent);
            }
        });
    }
    private void filter(String text) {
        filteredList.clear();
        if (text.isEmpty()) {
            filteredList.addAll(productList);
        } else {
            for (Product product : productList) {
                if (product.getTitle().toLowerCase().contains(text.toLowerCase())) {
                    filteredList.add(product);
                }
            }
        }
        adapter.filterList(filteredList);
    }
}