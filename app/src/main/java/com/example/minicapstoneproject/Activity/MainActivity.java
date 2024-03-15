package com.example.minicapstoneproject.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentTransaction;
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
import android.widget.TextView;
import android.widget.Toast;

import com.example.minicapstoneproject.Adapter.ProductAdapter;
import com.example.minicapstoneproject.Model.Product;
import com.example.minicapstoneproject.R;
import com.example.minicapstoneproject.databinding.ActivityMainBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText searchEditText;
    private RecyclerView recyclerView;
    private ProductAdapter adapter;
    private List<Product> productList;
    private List<Product> filteredList;
    private ImageView cartButton1, locationButton1;
    private TextView cartButton2, locationButton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        statusBarColor();
        searchEditText = findViewById(R.id.editTextText);
        recyclerView = findViewById(R.id.PopularView);
        cartButton1 = findViewById(R.id.cartImg); // Initialize cart button
        cartButton2 = findViewById(R.id.cartTxt);
        locationButton1 = findViewById(R.id.locationImg);
        locationButton2 = findViewById(R.id.locationTxt);
        productList = new ArrayList<>();
        filteredList = new ArrayList<>();


        productList.add(new Product("T-shirt 1", "item_1","Cheap, affordable product", 9, 5.99, 3.3, 0));
        productList.add(new Product("T-shirt 2", "item_1","High end clothing with fine fabric", 7, 29.99, 4.5, 0));
        productList.add(new Product("Watch 2", "item_2","Affordable watch", 6, 54.99, 5, 0));
        productList.add(new Product("Watch 2", "item_2","If you're stuck trying to find the perfect words for that special someone, take a look at some of our favorite suggestions", 7, 4599.99, 3.5, 0));
        productList.add(new Product("Phone 1", "item_3","Amazing phone! Great value", 8, 599.99, 2.2, 0));
        productList.add(new Product("Phone 2", "item_3","Cheap, easy to use, easy to buy", 8, 199.99, 4.5, 0));

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
        cartButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open ActivityCart
                Intent intent = new Intent(MainActivity.this, CartActivity.class);
                startActivity(intent);
            }
        });
        cartButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open ActivityCart
                Intent intent = new Intent(MainActivity.this, CartActivity.class);
                startActivity(intent);
            }
        });
        locationButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Launch the location_store activity
                Intent intent = new Intent(MainActivity.this, LocationActivity.class);
                startActivity(intent);
            }
        });
        locationButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LocationActivity.class);
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
    private void statusBarColor() {
        Window window = MainActivity.this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(MainActivity.this, R.color.purple_Dark));
    }
}