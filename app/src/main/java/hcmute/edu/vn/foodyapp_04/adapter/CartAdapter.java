package hcmute.edu.vn.foodyapp_04.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import hcmute.edu.vn.foodyapp_04.databinding.ItemCartContainerBinding;
import hcmute.edu.vn.foodyapp_04.models.CartLine;
import hcmute.edu.vn.foodyapp_04.models.Food;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder>{
    private  final List<CartLine> cartLineList;

    public CartAdapter(List<CartLine> cartLineList) {
        this.cartLineList = cartLineList;
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
            binding.btnPlus.setOnClickListener(v->{
                binding.count.setText(String.valueOf(Integer.parseInt(binding.count.getText().toString())+1));
            });
            binding.btnMinus.setOnClickListener(v->{
                binding.count.setText(String.valueOf(Integer.parseInt(binding.count.getText().toString())-1));
                if (Integer.parseInt(binding.count.getText().toString())<0){
                    binding.count.setText("0");
                }
            });
        }
    }

}
