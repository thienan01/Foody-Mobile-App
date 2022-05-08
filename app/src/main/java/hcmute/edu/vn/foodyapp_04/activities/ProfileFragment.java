package hcmute.edu.vn.foodyapp_04.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

import hcmute.edu.vn.foodyapp_04.R;
import hcmute.edu.vn.foodyapp_04.database.DAO;
import hcmute.edu.vn.foodyapp_04.database.Database;
import hcmute.edu.vn.foodyapp_04.databinding.FragmentProfileBinding;
import hcmute.edu.vn.foodyapp_04.utilities.Constants;
import hcmute.edu.vn.foodyapp_04.utilities.PreferenceManager;

public class ProfileFragment extends Fragment {
    private FragmentProfileBinding binding;
    private PreferenceManager preferenceManager;
    Database database;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setListener();
        preferenceManager = new PreferenceManager(getActivity().getApplicationContext());
        if (preferenceManager.getString(Constants.KEY_USER_NAME) != null){
            getInfo();
        }
        else{
            binding.txtName.setText("Login to continue");
            int url = R.drawable.avatar_null;
            binding.ivProfileImg.setImageResource(url);
        }

    }
    private void setListener(){
        binding.txtLogout.setOnClickListener(v-> signOut());
        binding.txtUploadFood.setOnClickListener(v->{
            startActivity(new Intent(getActivity().getApplicationContext(), UploadFoodActivity.class));
        });
    }

    private void showToast(String message){
        Toast.makeText(getActivity().getApplicationContext(),message,Toast.LENGTH_SHORT).show();
    }

    private void signOut(){
        showToast("Signing out...");
        preferenceManager.clear();
        startActivity(new Intent(getActivity().getApplicationContext(), SignInActivity.class));
        getActivity().finish();
    }

    private void getInfo(){
        binding.txtName.setText(preferenceManager.getString(Constants.KEY_USER_NAME));
        binding.phoneNumber.setText(preferenceManager.getString(Constants.KEY_USER_PhoneNumber));
        binding.txtAddress.setText(preferenceManager.getString(Constants.KEY_USER_ADDRESS));
        if (preferenceManager.getString(Constants.KEY_TYPE).equals("restaurant")){
            binding.uploadLayout.setVisibility(View.VISIBLE);
        }
    }
}