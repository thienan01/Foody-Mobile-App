package hcmute.edu.vn.foodyapp_04.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import hcmute.edu.vn.foodyapp_04.R;
import hcmute.edu.vn.foodyapp_04.adapter.CartAdapter;
import hcmute.edu.vn.foodyapp_04.database.DAO;
import hcmute.edu.vn.foodyapp_04.database.Database;
import hcmute.edu.vn.foodyapp_04.databinding.ActivityCartBinding;
import hcmute.edu.vn.foodyapp_04.models.CartLine;
import hcmute.edu.vn.foodyapp_04.models.Food;
import hcmute.edu.vn.foodyapp_04.utilities.Constants;
import hcmute.edu.vn.foodyapp_04.utilities.PreferenceManager;

public class CartActivity extends AppCompatActivity {

    ActivityCartBinding binding;
    CartAdapter cartAdapter;
    Food food;
    DAO dao = new DAO();
    Database database;
    private PreferenceManager preferenceManager;
    List<CartLine> cartLineList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        database = new Database(getApplicationContext(),"FoodAppDB", null ,1 );
        preferenceManager = new PreferenceManager(getApplicationContext());
        loadCartItem();
        setListener();
    }

    private void setListener(){
        binding.btnBack.setOnClickListener(v->{
            onBackPressed();
        });
    }

    private void loadCartItem(){
        cartLineList = dao.getListCartLineByUserId(database,preferenceManager.getString(Constants.KEY_USER_ID));
        if (cartLineList.size()<=0){
            binding.layoutCartEmpty.setVisibility(View.VISIBLE);
        }
        else {
            binding.rcvCart.setLayoutManager(new LinearLayoutManager(this));
            cartAdapter = new CartAdapter(cartLineList);
            binding.rcvCart.setAdapter(cartAdapter);
        }
    }

    private List<CartLine> getCartData(){
        List<CartLine> cartLineList = new ArrayList<>();
//        cartLineList.add(new CartLine(null,"Hamburger","20000","2"));
//        cartLineList.add(new CartLine(null,"Ga","20000","1"));
//        cartLineList.add(new CartLine(null,"Tra sua","20000","2"));
        return cartLineList ;
    }
}