package hcmute.edu.vn.foodyapp_04.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import hcmute.edu.vn.foodyapp_04.activities.CartActivity;
import hcmute.edu.vn.foodyapp_04.database.DAO;
import hcmute.edu.vn.foodyapp_04.database.Database;
import hcmute.edu.vn.foodyapp_04.databinding.ItemCartContainerBinding;
import hcmute.edu.vn.foodyapp_04.models.CartLine;
import hcmute.edu.vn.foodyapp_04.models.Food;
import hcmute.edu.vn.foodyapp_04.utilities.Constants;
import hcmute.edu.vn.foodyapp_04.utilities.PreferenceManager;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder>{
    private  final List<CartLine> cartLineList;
    private DAO dao;
    Database database;
    Context context;
    private PreferenceManager preferenceManager;
    public CartAdapter(List<CartLine> cartLineList, Database database, Context context) {
        this.cartLineList = cartLineList;
        this.context = context;
        dao = new DAO();
        this.database = database;
        preferenceManager = new PreferenceManager(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCartContainerBinding itemCartContainerBinding = ItemCartContainerBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false);

        return new ViewHolder(itemCartContainerBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setCartData(cartLineList.get(position));
    }

    @Override
    public int getItemCount() {
        return cartLineList.size();
    }

    public class  ViewHolder extends RecyclerView.ViewHolder{
        ItemCartContainerBinding binding;
        public ViewHolder(ItemCartContainerBinding itemCartContainerBinding){
            super(itemCartContainerBinding.getRoot());
            binding = itemCartContainerBinding;
        }
        void setCartData(CartLine cartLine){
            binding.foodName.setText(cartLine.getName());
            binding.foodPrice.setText(cartLine.getPrice());
            binding.count.setText(cartLine.getQuantity());
            binding.foodImg.setImageBitmap(getFoodImage(cartLine.getImage()));
            binding.btnPlus.setOnClickListener(v->{
                binding.count.setText(String.valueOf(Integer.parseInt(binding.count.getText().toString())+1));
            });
            binding.btnMinus.setOnClickListener(v->{
                binding.count.setText(String.valueOf(Integer.parseInt(binding.count.getText().toString())-1));
                if (Integer.parseInt(binding.count.getText().toString())<0){
                    binding.count.setText("0");
                }
            });
            binding.btnDelete.setOnClickListener(v->{
                dao.deleteCartLineById(database, String.valueOf(cartLine.getId()));

            });
        }
    }
    private Bitmap getFoodImage(String encodedImage){
        byte[] bytes = Base64.decode(encodedImage,Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(bytes,0,bytes.length);
    }

}
