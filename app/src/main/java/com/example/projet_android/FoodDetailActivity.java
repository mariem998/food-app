package com.example.projet_android;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projet_android.Model.Food;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.squareup.picasso.Picasso;

public class FoodDetailActivity extends AppCompatActivity {

    private TextView foodNameTextView;
    private ImageView foodImageView;
    private TextView descriptionTextView;
    private TextView prixTextView;
    private ImageView plusBtn;
    private ImageView minusBtn;

    private TextView numberOrderTitle;
    private Button button;


    private FirebaseAuth firebaseAuth;
    private DatabaseReference cartRef;
    private Food food;

    private int numberOrder = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);

        foodNameTextView = findViewById(R.id.foodNameTextView);
        foodImageView = findViewById(R.id.foodImageView);
        descriptionTextView = findViewById(R.id.descriptionTextView);
        prixTextView = findViewById(R.id.priceTxt);
        numberOrderTitle = findViewById(R.id.numberOrderTitle);
        button = findViewById(R.id.addToCartBtn);

        Intent j = getIntent();

        String name = j.getStringExtra("name");
        if (name == null) { name = "";}


        String description = j.getStringExtra("description");
        if (description == null) { description = "";}

        String image = j.getStringExtra("image");
        if (image == null) { image = "";}

        String prix = j.getStringExtra("prix");
        if (prix == null) {prix = "";}

        foodNameTextView.setText(name);
        descriptionTextView.setText(description);
        prixTextView.setText(prix+"dt");
        Picasso.get().load(image).into(foodImageView);

        plusBtn = findViewById(R.id.plusBtn);
        minusBtn = findViewById(R.id.minusBtn);


        plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberOrder++;
                numberOrderTitle.setText(String.valueOf(numberOrder));
            }
        });
        minusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (numberOrder > 1) {
                    numberOrder--;
                    numberOrderTitle.setText(String.valueOf(numberOrder));
                } else {
                    Toast.makeText(FoodDetailActivity.this, "Quantity cannot be less than 1", Toast.LENGTH_SHORT).show();
                }
            }
        });


        final String foodName = name;
        final String foodPrice = prix;
        final String foodImage = image;


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FoodDetailActivity.this, CartListActivity.class);
                intent.putExtra("foodName", foodName);
                intent.putExtra("foodPrice", foodPrice);
                intent.putExtra("foodImage", foodImage);
                startActivity(intent);
            }
        });
}}