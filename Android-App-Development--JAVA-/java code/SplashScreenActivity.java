package sg.edu.tp.mysicmysic;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import sg.edu.tp.mysicmysic.Login.LoginActivity;

public class SplashScreenActivity extends AppCompatActivity {

    private static int SPLASH_SCREEN_TIME_OUT = 1500;

     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);

         getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                 WindowManager.LayoutParams.FLAG_FULLSCREEN);

         setContentView(R.layout.activity_splash_screen);

         new Handler().postDelayed(new Runnable() {

             @Override
             public void run() {
                 Intent intent = new Intent(SplashScreenActivity.this, LoginActivity.class);
                 startActivity(intent);
                 finish();
             }

         }, SPLASH_SCREEN_TIME_OUT);
     }
   }
