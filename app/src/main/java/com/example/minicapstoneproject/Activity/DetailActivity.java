package com.example.minicapstoneproject.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners;
import com.example.minicapstoneproject.Model.Product;
import com.example.minicapstoneproject.R;
import com.example.minicapstoneproject.Utils.CartManagement;
import com.example.minicapstoneproject.databinding.ActivityDetailBinding;
import com.example.minicapstoneproject.databinding.ActivityMainBinding;

public class DetailActivity extends AppCompatActivity {
    private ActivityDetailBinding binding;
    private Product detail;
    private int numOrder=1;
    private CartManagement cartManagement;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        statusBarColor();
        getProductDetail();
        cartManagement = new CartManagement(this);
    }

    private void statusBarColor() {
        Window window = DetailActivity.this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(DetailActivity.this, R.color.purple_Dark));
    }
    private void getProductDetail() {
        detail = (Product) getIntent().getSerializableExtra("detail");
        int drawableResourceID = this.getResources().getIdentifier(detail.getImageURL()
                , "drawable", this.getPackageName());

        Glide.with(this)
                .load(drawableResourceID)
                .into(binding.productImage);

        binding.titleTxt.setText(detail.getTitle());
        binding.priceTxt.setText(detail.getPrice() + "$");
        binding.descriptionTxt.setText(detail.getDescription());
        binding.reviewTxt.setText(detail.getReview() + "");
        binding.ratingTxt.setText(detail.getScore() + "");

        binding.buyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detail.setNumberInCart(numOrder);
                cartManagement.insertProduct(detail);
            }
        });

        binding.backBtn.setOnClickListener(v -> finish());
    }
}