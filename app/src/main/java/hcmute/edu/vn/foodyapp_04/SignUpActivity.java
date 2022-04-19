package hcmute.edu.vn.foodyapp_04;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SignUpActivity extends AppCompatActivity {

    TextView txtBackLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        txtBackLogin = findViewById(R.id.txtBackToLogin);
        setOnclick();

    }
    private void setOnclick(){
        txtBackLogin.setOnClickListener(v ->{
            startActivity(new Intent(this, SignInActivity.class));
        });
    }
}