package hcmute.edu.vn.foodyapp_04.activities;

import static hcmute.edu.vn.foodyapp_04.R.layout.activity_sign_in;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import hcmute.edu.vn.foodyapp_04.R;
import hcmute.edu.vn.foodyapp_04.database.DAO;
import hcmute.edu.vn.foodyapp_04.database.Database;
import hcmute.edu.vn.foodyapp_04.databinding.ActivitySignInBinding;
import hcmute.edu.vn.foodyapp_04.utilities.Constants;
import hcmute.edu.vn.foodyapp_04.utilities.PreferenceManager;

public class SignInActivity extends AppCompatActivity {
    private ActivitySignInBinding binding;
    private PreferenceManager preferenceManager;
    Database database;
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
                signInSQLite();
            }
        });
        binding.cbUser.setOnClickListener(v->{
            if (binding.cbRestaurant.isChecked()){
                binding.cbRestaurant.setChecked(false);
            }
        });
        binding.cbRestaurant.setOnClickListener(v->{
            if (binding.cbUser.isChecked()){
                binding.cbUser.setChecked(false);
            }
        });
        binding.checkFood.setOnClickListener(v->{
            startActivity(new Intent(this,MainActivity.class));
            finish();
        });
    }
    private void showToast(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
    private  void signInSQLite(){
        database = new Database(this,Constants.KEY_DATABASE, null ,1 );
//        Cursor data =  database.GetData("SELECT * FROM "+Constants.KEY_RESTAURANT);
        DAO dao = new DAO();
        Cursor data = null;
        if (binding.cbUser.isChecked()){
            data = dao.selectAll(database,Constants.KEY_USERS);
        }
        if (binding.cbRestaurant.isChecked()){
            data = dao.selectAll(database,Constants.KEY_RESTAURANT);
        }
        while (data != null && data.moveToNext() == true){
            if (binding.inputPhoneNum.getText().toString().equals(data.getString(3)) && binding.inputPassword.getText().toString().equals(data.getString(4))){
                preferenceManager.putBoolean(Constants.KEY_IS_SIGN_IN,true);
                preferenceManager.putString(Constants.KEY_USER_ID, data.getString(0));
                preferenceManager.putString(Constants.KEY_USER_NAME, data.getString(2));
                preferenceManager.putString(Constants.KEY_USER_PhoneNumber, data.getString(3));
                preferenceManager.putString(Constants.KEY_TYPE, data.getString(5));
                preferenceManager.putString(Constants.KEY_USER_ADDRESS, data.getString(6));

                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        }
        if (preferenceManager.getString(Constants.KEY_USER_ID) == null){
            showToast("Wrong Phone Number Or Password");
        }
    }

//    private void signIn(){
//        FirebaseFirestore database = FirebaseFirestore.getInstance();
//        database.collection(Constants.KEY_COLLECTION_USERS)
//                .whereEqualTo(Constants.KEY_PHONE_NUMBER, binding.inputPhoneNum.getText().toString())
//                .whereEqualTo(Constants.KEY_PASSWORD, binding.inputPassword.getText().toString())
//                .get()
//                .addOnCompleteListener(task -> {
//                            if (task.isSuccessful() && task.getResult() != null
//                                    && task.getResult().getDocuments().size()>0){
//                                DocumentSnapshot documentSnapshot = task.getResult().getDocuments().get(0);
//                                preferenceManager.putBoolean(Constants.KEY_IS_SIGN_IN,true);
//                                preferenceManager.putString(Constants.KEY_USER_ID, documentSnapshot.getId());
//                                preferenceManager.putString(Constants.KEY_NAME, documentSnapshot.getString(Constants.KEY_NAME));
//                                preferenceManager.putString(Constants.KEY_PHONE_NUMBER, documentSnapshot.getString(Constants.KEY_PHONE_NUMBER));
//                                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
//                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                                startActivity(intent);
//                            }
//                            else{
//                                showToast("Wrong Phone Number Or Password");
//                            }
//                        }
//                );
//    }

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