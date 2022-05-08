package hcmute.edu.vn.foodyapp_04.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import hcmute.edu.vn.foodyapp_04.databinding.ItemFoodContainerBinding;
import hcmute.edu.vn.foodyapp_04.listener.IFoodListener;
import hcmute.edu.vn.foodyapp_04.models.Food;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder>{
    private  final List<Food> foodList;
    private final IFoodListener iFoodListener;
    public FoodAdapter(List<Food> foodList, IFoodListener iFoodListener) {
        this.foodList = foodList;
        this.iFoodListener = iFoodListener;
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
            binding.tvFoodName.setText(food.getName());
            binding.tvFoodPrice.setText(food.getPrice() + "vnd");
            binding.foodImage.setImageBitmap(getUserImage(food.getImage()));
            binding.getRoot().setOnClickListener(v-> iFoodListener.onFoodClicked(food));
        }
    }
    private Bitmap getUserImage(String encodedImage){
        byte[] bytes = Base64.decode(encodedImage,Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(bytes,0,bytes.length);
    }
}
