package sg.edu.tp.mysicmysic.Login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import sg.edu.tp.mysicmysic.MainActivity;
import sg.edu.tp.mysicmysic.R;

public class RegisterActivity extends AppCompatActivity {

    boolean validEmail       = false;
    boolean matchingPassword = false;
    boolean validUser        = false;

    //REGEX
    private static final String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        EditText username        = findViewById(R.id.etUsername);
        EditText email           = findViewById(R.id.etEmail);
        EditText password        = findViewById(R.id.etPassword);
        EditText confirmPassword = findViewById(R.id.etConfirmPassword);

        ImageButton btnBack      = findViewById(R.id.btnBack);
        Button btnCreate         = findViewById(R.id.btnCreateAccount);


        btnBack.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick (View view) {

                Intent goBack = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(goBack);
                finish();
            }

        });


        btnCreate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick (View view) {

                String Username        = username.getText().toString().toLowerCase();
                String Password        = password.getText().toString();
                String ConfirmPassword = confirmPassword.getText().toString();
                String Email           = email.getText().toString();


                //Username must be longer than 3
                if(Username.length() > 2) {

                    validUser = true;
                } else {
                    //these else statements will revert the boolean back
                    //if the conditions are no longer met
                    validUser = false;
                }

                //Email Check
                if(Email.matches(EMAIL_REGEX)) {
                    validEmail = true;
                } else {

                    validEmail = false;
                }


                //Password quality && match check
                if(!Password.isEmpty() && Password.length() >= 8
                        && Password.matches(ConfirmPassword)) {

                    matchingPassword = true;
                } else {

                    matchingPassword = false;
                }

                if(validUser && validEmail && matchingPassword) {

                    Intent login = new Intent(RegisterActivity.this, MainActivity.class);
                    Toast.makeText(RegisterActivity.this, "Successful Created: " + username.getText() , Toast.LENGTH_SHORT).show();
                    login.putExtra("username", username.getText().toString());

                    startActivity(login);
                    finish();

                    //empty fields
                } else if(Username.isEmpty() || Password.isEmpty() || ConfirmPassword.isEmpty() || Email.isEmpty()) {

                    Toast.makeText(RegisterActivity.this, " Incomplete Form", Toast.LENGTH_SHORT).show();

                    //username too short
                } else if(Username.length() < 3) {

                    Toast.makeText(RegisterActivity.this, "Username must be atleast 3 char", Toast.LENGTH_SHORT).show();

                    //invalid email
                } else if(validEmail == false) {

                    Toast.makeText(RegisterActivity.this, "Invalid Email", Toast.LENGTH_SHORT).show();

                    //password != confirmPassword
                } else if(!Password.matches(ConfirmPassword)) {

                    Toast.makeText(RegisterActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();

                    //password too short
                } else if(Password.length() < 8) {

                    Toast.makeText(RegisterActivity.this, "Password must be atleast 8 char", Toast.LENGTH_SHORT).show();

                    //for any unfound loophole
                } else {

                    Toast.makeText(RegisterActivity.this, "Please Recheck All fields", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
