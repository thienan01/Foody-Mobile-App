package hcmute.edu.vn.foodyapp_04.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import hcmute.edu.vn.foodyapp_04.databinding.ActivityFoodDetailBinding;

public class FoodDetailActivity extends AppCompatActivity {
    private ActivityFoodDetailBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFoodDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}