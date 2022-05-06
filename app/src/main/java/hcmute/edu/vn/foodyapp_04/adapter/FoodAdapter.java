package hcmute.edu.vn.foodyapp_04.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import hcmute.edu.vn.foodyapp_04.databinding.ItemFoodContainerBinding;
import hcmute.edu.vn.foodyapp_04.models.Food;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder>{
    List<Food> foodList;
    public FoodAdapter(List<Food> foodList) {
        this.foodList = foodList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemFoodContainerBinding itemFoodContainerBinding = ItemFoodContainerBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false);
        return new ViewHolder((itemFoodContainerBinding));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setFoodData(foodList.get(position));
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    public class  ViewHolder extends RecyclerView.ViewHolder{
        ItemFoodContainerBinding binding;
        public ViewHolder(ItemFoodContainerBinding itemFoodContainerBinding){
            super(itemFoodContainerBinding.getRoot());
            binding = itemFoodContainerBinding;
        }
        void setFoodData(Food food){
            binding.tvFoodName.setText(food.name);
            binding.tvFoodPrice.setText(food.price + "vnd");
        }
    }
}
