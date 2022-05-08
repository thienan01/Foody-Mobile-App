package hcmute.edu.vn.foodyapp_04.activities;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import hcmute.edu.vn.foodyapp_04.R;
import hcmute.edu.vn.foodyapp_04.database.DAO;
import hcmute.edu.vn.foodyapp_04.database.Database;
import hcmute.edu.vn.foodyapp_04.databinding.ActivityUploadFoodBinding;
import hcmute.edu.vn.foodyapp_04.models.Food;
import hcmute.edu.vn.foodyapp_04.utilities.Constants;
import hcmute.edu.vn.foodyapp_04.utilities.PreferenceManager;

public class UploadFoodActivity extends AppCompatActivity {
    ActivityUploadFoodBinding binding;
    private  String encodedImage;
    Database database;
    private PreferenceManager preferenceManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUploadFoodBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        preferenceManager = new PreferenceManager(getApplicationContext());
        database = new Database(this,"FoodAppDB", null ,1 );

        database.QueryData("CREATE TABLE IF NOT EXISTS Restaurant(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " "+Constants.KEY_RESTAURANT_IMAGE + " TEXT, "+ Constants.KEY_RESTAURANT_NAME+" " +
                "VARCHAR(200), "+Constants.KEY_RESTAURANT_PHONE_NUM+" VARCHAR(12), "+ Constants.KEY_RESTAURANT_PASSWORD+" " +
                "VARCHAR(20), "+ Constants.KEY_TYPE+" VARCHAR(50), "+Constants.KEY_RESTAURANT_ADDRESS+" " +
                "VARCHAR(200))" );

//        database.QueryData("INSERT INTO "+ Constants.KEY_RESTAURANT+" VALUES(null,null,'Anlesport Restaurant', '0383865402', '123456', " +
//                "'restaurant', '186 Le Van Viet Quan 9 HCM')");

        Cursor restaurant =  database.GetData("SELECT * FROM "+Constants.KEY_RESTAURANT);

        while (restaurant.moveToNext()){
            binding.tvUpdateFood.setText(restaurant.getString(2));
        }

//        database.QueryData("CREATE TABLE IF NOT EXISTS Food(id INTEGER PRIMARY KEY AUTOINCREMENT," +
//                " "+ Constants.KEY_FOOD_NAME +" VARCHAR(200)," +
//                " "+ Constants.KEY_FOOD_PRICE )");

        setListener();
    }


    private void setListener(){
        binding.layoutImage.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            pickImage.launch(intent);
        });
        binding.btnBack.setOnClickListener(v->{
            onBackPressed();
        });
        binding.btnUpload.setOnClickListener(v->{
            uploadFood();
        });
    }

    private  void uploadFood(){
        DAO dao = new DAO();
        database = new Database(this,Constants.KEY_DATABASE, null ,1 );
        Food food = new Food();
        food.setImage(encodedImage);
        food.setName(binding.edFoodName.getText().toString());
        food.setPrice(binding.edFoodPrice.getText().toString());
        food.setDescription(binding.edFoodDescription.getText().toString());
        food.setRestaurantId(preferenceManager.getString(Constants.KEY_USER_ID));
        dao.insertFood(database,food);
        Cursor data = dao.selectAll(database,Constants.KEY_FOOD);
        Toast.makeText(this, "Upload successful", Toast.LENGTH_SHORT).show();
        onBackPressed();
    }

    private  String encodeImage(Bitmap bitmap){
        int previewWidth = 150;
        int previewHeight = bitmap.getHeight()*previewWidth/bitmap.getWidth();
        Bitmap previewBitmap = Bitmap.createScaledBitmap(bitmap,previewWidth,previewHeight,false);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        previewBitmap.compress(Bitmap.CompressFormat.JPEG,50,byteArrayOutputStream);
        byte[] bytes =  byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(bytes,Base64.DEFAULT);
    }

    private final ActivityResultLauncher<Intent> pickImage = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK){
                    if (result.getData() != null){
                        Uri imageUri = result.getData().getData();
                        try {
                            InputStream inputStream = getContentResolver().openInputStream(imageUri);
                            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                            binding.imageFood.setImageBitmap(bitmap);
                            binding.txtAddImg.setVisibility(View.GONE);
                            encodedImage   = encodeImage(bitmap);
                        }catch (FileNotFoundException e){
                            e.printStackTrace();
                        }
                    }
                }
            }
    );
}