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
import hcmute.edu.vn.foodyapp_04.databinding.FragmentProfileBinding;
import hcmute.edu.vn.foodyapp_04.utilities.Constants;
import hcmute.edu.vn.foodyapp_04.utilities.PreferenceManager;

public class ProfileFragment extends Fragment {
    private FragmentProfileBinding binding;
    private PreferenceManager preferenceManager;
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
    }
    private void setListener(){
        binding.txtLogout.setOnClickListener(v-> signOut());
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
}