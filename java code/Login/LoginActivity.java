package sg.edu.tp.mysicmysic.Login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

import sg.edu.tp.mysicmysic.MainActivity;
import sg.edu.tp.mysicmysic.R;

public class LoginActivity extends AppCompatActivity {

    //Account
    String Username = "user";
    String Password = "Abc12345";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText etUsername = findViewById(R.id.etUsername);
        EditText etPassword = findViewById(R.id.etPassword);

        MaterialButton btnLogin  = findViewById(R.id.btnLogin);
        MaterialButton btnSignup = findViewById(R.id.btnSignup);
        MaterialButton btnGuest  = findViewById(R.id.btnGuestLogin);


        //login
        btnLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                String username = etUsername.getText().toString().toLowerCase();
                String password = etPassword.getText().toString();


                if(username.matches(Username)
                        && password.matches(Password)) {

                    Intent login = new Intent(LoginActivity.this, MainActivity.class);
                    login.putExtra("username", etUsername.getText().toString());
                    startActivity(login);
                    finish();

                } else if(username.isEmpty()) {

                    Toast.makeText(LoginActivity.this, "Invalid User", Toast.LENGTH_SHORT).show();

                } else {

                    Toast.makeText(LoginActivity.this, "Invalid Password", Toast.LENGTH_SHORT).show();

                }

            }
        });


        //create account
        btnSignup.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent register = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(register);
                finish();

            }
        });


        //guest login
        btnGuest.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent login = new Intent(LoginActivity.this, MainActivity.class);
                login.putExtra("username", "GUEST");
                startActivity(login);
                finish();

            }
        });

    }

}