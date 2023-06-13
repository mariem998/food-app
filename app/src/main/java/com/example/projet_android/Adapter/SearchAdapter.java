package com.example.projet_android.Adapter;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projet_android.Model.Food;
import com.example.projet_android.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {

    private List<Food> foodList;
    private List<Food> filteredList;

    public SearchAdapter(List<Food> foodList) {
        this.foodList = foodList;
        this.filteredList = new ArrayList<>(foodList);
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_food, parent, false);
        return new SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        Food food = filteredList.get(position);
        holder.bind(food);
    }

    @Override
    public int getItemCount() {
        return filteredList.size();
    }

    public void filter(String searchText) {
        filteredList.clear();

        if (TextUtils.isEmpty(searchText)) {
            filteredList.addAll(foodList);
        } else {
            String query = searchText.toLowerCase().trim();
            for (Food food : foodList) {
                if (food.getFoodName().toLowerCase().contains(query)) {
                    filteredList.add(food);
                }
            }
        }

        notifyDataSetChanged();
    }

    public class SearchViewHolder extends RecyclerView.ViewHolder {

        private TextView foodNameTextView;
        private TextView descriptionTextView;
        private ImageView foodImageView;

        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);

            foodNameTextView = itemView.findViewById(R.id.foodNameTextView);
            descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
            foodImageView = itemView.findViewById(R.id.foodImageView);
        }

        public void bind(Food food) {
            foodNameTextView.setText(food.getFoodName());
            descriptionTextView.setText(food.getDescription());

            Picasso.get().load(food.getImageUrl()).into(foodImageView);
        }
    }
}
