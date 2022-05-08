package hcmute.edu.vn.foodyapp_04.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import hcmute.edu.vn.foodyapp_04.R;
import hcmute.edu.vn.foodyapp_04.adapter.CartAdapter;
import hcmute.edu.vn.foodyapp_04.databinding.ActivityCartBinding;
import hcmute.edu.vn.foodyapp_04.models.CartLine;

public class CartActivity extends AppCompatActivity {

    ActivityCartBinding binding;
    CartAdapter cartAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.rcvCart.setLayoutManager(new LinearLayoutManager(this));
        cartAdapter = new CartAdapter(getCartData());
        binding.rcvCart.setAdapter(cartAdapter);
    }

    private void setListener(){
    }

    private List<CartLine> getCartData(){
        List<CartLine> cartLineList = new ArrayList<>();
        cartLineList.add(new CartLine(null,"Hamburger","20000","2"));
        cartLineList.add(new CartLine(null,"Ga","20000","1"));
        cartLineList.add(new CartLine(null,"Tra sua","20000","2"));
        return cartLineList ;
    }
}