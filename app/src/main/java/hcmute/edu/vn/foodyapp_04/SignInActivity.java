package hcmute.edu.vn.foodyapp_04;

import static hcmute.edu.vn.foodyapp_04.R.layout.activity_sign_in;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class SignInActivity extends AppCompatActivity {

    Button btnSignUp;
    TextView txtSignUp;
    Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_sign_in);
        txtSignUp = findViewById(R.id.txtSignUp);
        btnLogin = findViewById(R.id.btnLogin);
        setOnclick();
    }
    private void setOnclick(){
        txtSignUp.setOnClickListener(v ->{
            startActivity(new Intent(this, SignUpActivity.class));
        });
        btnLogin.setOnClickListener(v->{
            startActivity(new Intent(this, MainActivity.class));
        });
    }
}