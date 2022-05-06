package hcmute.edu.vn.foodyapp_04.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import hcmute.edu.vn.foodyapp_04.R;


public class MainActivity extends AppCompatActivity {
    private
    BottomNavigationView bottomNavigationView;
    FloatingActionButton btnCart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottomNAVView);
        btnCart = findViewById(R.id.btnCart);
        configureNAVBar();
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentLayout, new HomeFragment()).commit();

        setListener();
    }

    private void setListener(){
        btnCart.setOnClickListener(v ->{
            startActivity(new Intent(this, CartActivity.class));
        });
    }

    private void configureNAVBar(){
        bottomNavigationView.setBackground(null);
    }
    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new
            BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;
                    switch (item.getItemId()){
                        case  R.id.miHome:
                            selectedFragment = new HomeFragment();
                            break;
                        case R.id.miHistory:
                            selectedFragment = new HistoryFragment();
                            break;
                        case R.id.miNotification:
                            selectedFragment = new NotificationFragment();
                            break;
                        case R.id.miProfile:
                            selectedFragment = new ProfileFragment();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragmentLayout
                            ,selectedFragment).commit();
                    return true;
                }
            };


}