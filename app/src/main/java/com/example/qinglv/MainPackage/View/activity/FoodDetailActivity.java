package com.example.qinglv.MainPackage.View.activity;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.qinglv.MainPackage.Entity.Food;
import com.example.qinglv.R;

import java.util.Objects;

public class FoodDetailActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_food);
        Food food = (Food) Objects.requireNonNull(getIntent().getExtras()).getSerializable("food");

        Toolbar toolbar = findViewById(R.id.toolbar_article_food);
        assert food != null;
        toolbar.setTitle(food.getTitle());
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setHomeButtonEnabled(true);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        TextView textViewTittle = findViewById(R.id.textView_food_detail_tittle);
        TextView textViewContent = findViewById(R.id.textView_food_detail_content);
        TextView textViewTime = findViewById(R.id.textView_food_detail_time);
        ImageView imageView = findViewById(R.id.imageView_food_detail_);

        textViewContent.setText(food.getContent());
        textViewTime.setText(food.getDepositTime());
        textViewTittle.setText(food.getTitle());
        Glide.with(this).load(food.getPreview()).into(imageView);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}
