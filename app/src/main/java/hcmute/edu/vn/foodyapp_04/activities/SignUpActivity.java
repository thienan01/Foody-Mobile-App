package hcmute.edu.vn.foodyapp_04.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

import hcmute.edu.vn.foodyapp_04.R;
import hcmute.edu.vn.foodyapp_04.database.DAO;
import hcmute.edu.vn.foodyapp_04.database.Database;
import hcmute.edu.vn.foodyapp_04.databinding.ActivitySignUpBinding;
import hcmute.edu.vn.foodyapp_04.models.User;
import hcmute.edu.vn.foodyapp_04.utilities.Constants;
import hcmute.edu.vn.foodyapp_04.utilities.PreferenceManager;

public class SignUpActivity extends AppCompatActivity {
    private ActivitySignUpBinding binding;
    TextView txtBackLogin;
    private PreferenceManager preferenceManager;
    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        preferenceManager = new PreferenceManager(getApplicationContext());
        setOnclick();


    }
    private void setOnclick(){
        binding.txtBackToLogin.setOnClickListener(v ->{
            startActivity(new Intent(this, SignInActivity.class));
        });
        binding.btnLogin.setOnClickListener(v-> {
            if (isValidSignUpDetails()){
                signUpSQLite();
            }
        });
    }

    private void signUpSQLite(){
        database = new Database(this,"FoodAppDB", null ,1 );
        database.QueryData("CREATE TABLE IF NOT EXISTS "+ Constants.KEY_USERS+"(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " "+Constants.KEY_USER_IMAGE + " TEXT, "+ Constants.KEY_USER_NAME+" " +
                "VARCHAR(200), "+Constants.KEY_USER_PhoneNumber+" VARCHAR(12), "+ Constants.KEY_USER_PASSWORD+" " +
                "VARCHAR(20), "+ Constants.KEY_TYPE+" VARCHAR(50), "+Constants.KEY_USER_ADDRESS+" " +
                "VARCHAR(200))" );
        DAO dao = new DAO();
        User user = new User();
        user.setName(binding.etName.getText().toString());
        user.setPassword(binding.etPassword.getText().toString());
        user.setPhoneNumber(binding.etPhoneNum.getText().toString());
        dao.insertUser(database,user);
        preferenceManager.putBoolean(Constants.KEY_IS_SIGN_IN, true);
        preferenceManager.putString(Constants.KEY_USER_NAME, binding.etName.getText().toString());
        preferenceManager.putString(Constants.KEY_USER_PhoneNumber, binding.etPhoneNum.getText().toString());
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

//    private void signUp(){
//        FirebaseFirestore database = FirebaseFirestore.getInstance();
//        HashMap<String,Object> user = new HashMap<>();
//        user.put(Constants.KEY_NAME, binding.etName.getText().toString());
//        user.put(Constants.KEY_PHONE_NUMBER, binding.etPhoneNum.getText().toString());
//        user.put(Constants.KEY_PASSWORD, binding.etPassword.getText().toString());
//
//        database.collection(Constants.KEY_COLLECTION_USERS)
//                .add(user)
//                .addOnSuccessListener(documentReference -> {
//                    preferenceManager.putBoolean(Constants.KEY_IS_SIGN_IN, true);
//                    preferenceManager.putString(Constants.KEY_USER_ID, documentReference.getId());
//                    preferenceManager.putString(Constants.KEY_NAME, binding.etName.getText().toString());
//                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
//                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                    startActivity(intent);
//                })
//                .addOnFailureListener(exception -> {
//                });
//    }

    private void showToast(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    private  boolean isValidSignUpDetails(){
        if(binding.etName.getText().toString().trim().isEmpty()){
            showToast("Enter name");
            return  false;
        }else  if(binding.etPhoneNum.getText().toString().trim().isEmpty()){
            showToast("Enter phone number");
            return  false;
        }else  if(binding.etPassword.getText().toString().trim().isEmpty()){
            showToast("Enter password");
            return  false;
        }else {
            return true;
        }
    }
}