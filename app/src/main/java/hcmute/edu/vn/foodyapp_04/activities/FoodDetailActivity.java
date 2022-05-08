package hcmute.edu.vn.foodyapp_04.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.widget.Toast;

import hcmute.edu.vn.foodyapp_04.database.DAO;
import hcmute.edu.vn.foodyapp_04.database.Database;
import hcmute.edu.vn.foodyapp_04.databinding.ActivityFoodDetailBinding;
import hcmute.edu.vn.foodyapp_04.models.Food;
import hcmute.edu.vn.foodyapp_04.models.User;
import hcmute.edu.vn.foodyapp_04.utilities.Constants;

public class FoodDetailActivity extends AppCompatActivity {
    private ActivityFoodDetailBinding binding;
    private Food food;
    Database database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFoodDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        database = new Database(getApplicationContext(),"FoodAppDB", null ,1 );
        loadFoodDetail();
        setListener();
    }

    private void setListener(){
        binding.btnBack.setOnClickListener(v->onBackPressed());
    }

    private  void loadFoodDetail(){
        food = (Food) getIntent().getSerializableExtra(Constants.KEY_FOOD);
        DAO dao = new DAO();
        User restaurant = dao.getRestaurantById(database,String.valueOf(food.getId()));

        binding.foodName.setText(food.getName());
        binding.ivFoodImage.setImageBitmap(getUserImage(food.getImage()));
        binding.tvFoodDescription.setText(food.getDescription());
        binding.tvRestaurantName.setText(restaurant.getName());
        binding.tvAddress.setText(restaurant.getAddress());
        binding.tvPrice.setText(food.getPrice() + " vnd");
        binding.totalPrice.setText(food.getPrice() + " vnd");
    }
    private Bitmap getUserImage(String encodedImage){
        byte[] bytes = Base64.decode(encodedImage,Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(bytes,0,bytes.length);
    }
}