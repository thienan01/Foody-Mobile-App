package hcmute.edu.vn.foodyapp_04.activities;

import android.content.Intent;
import android.database.Cursor;
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

import hcmute.edu.vn.foodyapp_04.adapter.FoodAdapter;
import hcmute.edu.vn.foodyapp_04.database.DAO;
import hcmute.edu.vn.foodyapp_04.database.Database;
import hcmute.edu.vn.foodyapp_04.databinding.FragmentHomeBinding;
import hcmute.edu.vn.foodyapp_04.listener.IFoodListener;
import hcmute.edu.vn.foodyapp_04.models.Food;
import hcmute.edu.vn.foodyapp_04.utilities.Constants;
import hcmute.edu.vn.foodyapp_04.utilities.PreferenceManager;

public class HomeFragment extends Fragment implements IFoodListener {

    Database database;
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
        database = new Database(getActivity().getApplicationContext(),"FoodAppDB", null ,1 );
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
        DAO dao = new DAO();
        Cursor data = dao.selectAll(database,Constants.KEY_FOOD);
        List<Food> foodList = new ArrayList<>();
        int index = 0;
        while (data.moveToNext()){
            foodList.add(new Food(data.getInt(0),data.getString(1),data.getString(2),data.getString(3),
                    data.getString(4),String.valueOf(data.getInt(5))));
        }
        if (foodList.size()>0){
            foodAdapter = new FoodAdapter(foodList,this);
            binding.rcvFoods.setAdapter(foodAdapter);
            binding.rcvFoods.setVisibility(View.VISIBLE);
            isLoading(false);
        }
    }
    public void onFoodClicked(Food food){
        Intent intent = new Intent(getActivity().getApplicationContext(), FoodDetailActivity.class);
        intent.putExtra(Constants.KEY_FOOD, food);
        startActivity(intent);
    }
}