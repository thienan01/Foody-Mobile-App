package hcmute.edu.vn.foodyapp_04.activities;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import hcmute.edu.vn.foodyapp_04.R;
import hcmute.edu.vn.foodyapp_04.adapter.FoodAdapter;
import hcmute.edu.vn.foodyapp_04.adapter.FoodAdapter2;
import hcmute.edu.vn.foodyapp_04.databinding.FragmentHomeBinding;
import hcmute.edu.vn.foodyapp_04.models.Food;
import hcmute.edu.vn.foodyapp_04.utilities.Constants;
import hcmute.edu.vn.foodyapp_04.utilities.PreferenceManager;

public class HomeFragment extends Fragment {

    FirebaseFirestore database;
    private PreferenceManager preferenceManager;
    private FragmentHomeBinding binding;
    private FoodAdapter foodAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.rcvFoods.setLayoutManager(new GridLayoutManager(getActivity().getApplicationContext(), 2));
        getFoods();
    }

    private void isLoading(boolean loading){
        if (loading)
            binding.progressBar.setVisibility(View.VISIBLE);
        else
            binding.progressBar.setVisibility(View.INVISIBLE);
    }

    private void getFoods(){
        isLoading(true);
        FirebaseFirestore database = FirebaseFirestore.getInstance();
        database.collection(Constants.KEY_COLLECTION_FOODS)
                .get()
                .addOnCompleteListener(task -> {
                    isLoading(false);
                    if (task.isSuccessful() && task.getResult() != null){
                        List<Food> foods = new ArrayList<>();
                        for (QueryDocumentSnapshot queryDocumentSnapshot : task.getResult()){
                            Food food   = new Food();
                            food.name = queryDocumentSnapshot.getString(Constants.KEY_FOOD_NAME);
                            food.price = queryDocumentSnapshot.getString(Constants.KEY_FOOD_PRICE);
                            foods.add(food);
                        }
                        if (foods.size() > 0){
                            foodAdapter = new FoodAdapter(foods);
                            binding.rcvFoods.setAdapter(foodAdapter);
                            binding.rcvFoods.setVisibility(View.VISIBLE);
                        }
                        else{
                            Toast.makeText(getContext(), "fail", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(getContext(), "fail", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}