package hcmute.edu.vn.foodyapp_04.activities;

import static hcmute.edu.vn.foodyapp_04.R.layout.activity_sign_in;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import hcmute.edu.vn.foodyapp_04.R;
import hcmute.edu.vn.foodyapp_04.databinding.ActivitySignInBinding;
import hcmute.edu.vn.foodyapp_04.utilities.Constants;
import hcmute.edu.vn.foodyapp_04.utilities.PreferenceManager;

public class SignInActivity extends AppCompatActivity {
    private ActivitySignInBinding binding;
    private PreferenceManager preferenceManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferenceManager = new PreferenceManager(getApplicationContext());
        if(preferenceManager.getBoolean(Constants.KEY_IS_SIGN_IN)){
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
            finish();
        }
        binding = ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setOnclick();
    }
    private void setOnclick(){
        binding.txtSignUp.setOnClickListener(v ->{
            startActivity(new Intent(this, SignUpActivity.class));
        });
        binding.btnLogin.setOnClickListener(v->{
            if (isValidSignInDetail()){
                signIn();
            }
        });
    }
    private void showToast(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
    private void signIn(){
        FirebaseFirestore database = FirebaseFirestore.getInstance();
        database.collection(Constants.KEY_COLLECTION_USERS)
                .whereEqualTo(Constants.KEY_PHONE_NUMBER, binding.inputPhoneNum.getText().toString())
                .whereEqualTo(Constants.KEY_PASSWORD, binding.inputPassword.getText().toString())
                .get()
                .addOnCompleteListener(task -> {
                            if (task.isSuccessful() && task.getResult() != null
                                    && task.getResult().getDocuments().size()>0){
                                DocumentSnapshot documentSnapshot = task.getResult().getDocuments().get(0);
                                preferenceManager.putBoolean(Constants.KEY_IS_SIGN_IN,true);
                                preferenceManager.putString(Constants.KEY_USER_ID, documentSnapshot.getId());
                                preferenceManager.putString(Constants.KEY_NAME, documentSnapshot.getString(Constants.KEY_NAME));
                                preferenceManager.putString(Constants.KEY_PHONE_NUMBER, documentSnapshot.getString(Constants.KEY_PHONE_NUMBER));
                                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                            }
                            else{
                                showToast("Wrong Phone Number Or Password");
                            }
                        }
                );
    }
    private  Boolean isValidSignInDetail(){
        if (binding.inputPhoneNum.getText().toString().trim().isEmpty()){
            showToast("Enter phone number");
            return false;

        }else if(binding.inputPassword.getText().toString().trim().isEmpty()){
            showToast("Enter Password");
            return false;
        }else {
            return true;
        }
    }
}