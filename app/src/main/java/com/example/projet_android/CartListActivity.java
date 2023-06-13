package com.example.projet_android;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projet_android.Model.Food;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CartListActivity extends AppCompatActivity {


    private TextView foodNameTextView;
    private ImageView foodImageView;
    private TextView prix;
    private ImageView minusButton;
    private ImageView plusButton;
    private TextView numberOrderTitle;
    private TextView totalEachitem;

    private TextView totalFeeTxt;
    private TextView totalTxt;
    private int numberOrder = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_list);

        foodNameTextView = findViewById(R.id.titleTxt);
        foodImageView = findViewById(R.id.picCart);
        prix = findViewById(R.id.feeEachitem);
        minusButton = findViewById(R.id.minusCartBtn);
        plusButton = findViewById(R.id.plusCardBtn);
        numberOrderTitle = findViewById(R.id.numberItemTxt);
        totalEachitem = findViewById(R.id.totalEachitem);
        totalFeeTxt = findViewById(R.id.totalFeeTxt);
        totalTxt = findViewById(R.id.totalTxt);


        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberOrder++;
                numberOrderTitle.setText(String.valueOf(numberOrder));
                calculateTotal();

            }
        });
        minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (numberOrder > 1) {
                    numberOrder--;
                    numberOrderTitle.setText(String.valueOf(numberOrder));
                    calculateTotal();
                } else {
                    Toast.makeText(CartListActivity.this, "Quantity cannot be less than 1", Toast.LENGTH_SHORT).show();
                }
            }
        });


        Intent intent = getIntent();
        if (intent != null) {
            String foodName = intent.getStringExtra("foodName");
            String foodImage = intent.getStringExtra("foodImage");
            String foodPrice = intent.getStringExtra("foodPrice");

            // Faire ce que vous souhaitez avec les données de l'Intent
            foodNameTextView.setText(foodName);
            Picasso.get().load(foodImage).into(foodImageView);
            prix.setText(foodPrice);
            calculateTotal();
        }
    }

    private void calculateTotal() {
        double feeEachitem = Double.parseDouble(prix.getText().toString());
        int numberItemTxt = Integer.parseInt(numberOrderTitle.getText().toString());
        double total = feeEachitem * numberItemTxt;
        totalEachitem.setText(String.valueOf(total));
        totalFeeTxt.setText(String.valueOf(total) + " dt");

        double finalTotal = total + 3.5;
        totalTxt.setText(String.valueOf(finalTotal) + " dt");
    }
}