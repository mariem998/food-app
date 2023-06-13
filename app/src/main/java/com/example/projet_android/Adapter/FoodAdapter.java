package com.example.projet_android.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projet_android.FoodDetailActivity;
import com.example.projet_android.Model.Food;
import com.example.projet_android.R;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.jar.Attributes;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> {

    private List<Food> foodList;
    private Context context;

    public FoodAdapter(Context context, List<Food> foodList) {
        this.context = context;
        this.foodList = foodList;
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_food, parent, false);
        return new FoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        Food food = foodList.get(position);
        holder.bind(food);
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    public class FoodViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView foodNameTextView;
        private TextView descriptionTextView;
        private TextView prixTextView;
        private ImageView foodImageView;

        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);

            foodNameTextView = itemView.findViewById(R.id.foodNameTextView);
            descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
            prixTextView = itemView.findViewById(R.id.prixTextView);
            foodImageView = itemView.findViewById(R.id.foodImageView);
            Button btnGoToDetail = itemView.findViewById(R.id.btnGoToDetail);
            btnGoToDetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        Food food = foodList.get(position);

                        Intent intent = new Intent(context, FoodDetailActivity.class);
                        intent.putExtra("name", food.getFoodName());
                        intent.putExtra("description", food.getDescription());
                        intent.putExtra("prix", food.getPrix());
                        intent.putExtra("image", food.getImageUrl());


                        context.startActivity(intent);
                }}
            });

            itemView.setOnClickListener(this);
        }

        public void bind(Food food) {
            foodNameTextView.setText(food.getFoodName());
            descriptionTextView.setText(food.getDescription());
            prixTextView.setText(food.getPrix());


            Picasso.get().load(food.getImageUrl()).into(foodImageView);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                Food food = foodList.get(position);

                Intent intent = new Intent(context, FoodDetailActivity.class);
                intent.putExtra("food", food);
                context.startActivity(intent);
            }
        }
    }
}