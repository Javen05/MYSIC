package sg.edu.tp.mysicmysic;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import sg.edu.tp.mysicmysic.navigation.AccountFragment;
import sg.edu.tp.mysicmysic.navigation.HomeFragment;
import sg.edu.tp.mysicmysic.navigation.OfflineFragment;
import sg.edu.tp.mysicmysic.navigation.SearchFragment;
import sg.edu.tp.mysicmysic.navigation.SettingsFragment;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    SettingsFragment settingsFragment = new SettingsFragment();
    AccountFragment accountFragment = new AccountFragment();
    HomeFragment homeFragment = new HomeFragment();
    OfflineFragment offlineFragment = new OfflineFragment();
    SearchFragment searchFragment = new SearchFragment();

    boolean noInternet;
    boolean nightMode;
    Switch switcher;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**Internet Check**/
        ConnectivityManager conMgr = (ConnectivityManager) getSystemService (Context.CONNECTIVITY_SERVICE);

        if(conMgr.getActiveNetworkInfo() != null
                && conMgr.getActiveNetworkInfo().isAvailable()
                && conMgr.getActiveNetworkInfo().isConnected()) {

            noInternet = false; } else {
            noInternet = true;

        }

        if(noInternet == true) {
            Toast.makeText(this, "Status: " +
                    "Offline", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Status: " +
                    "Online", Toast.LENGTH_SHORT).show();
        }


        /**Dark Mode**/
        switcher = findViewById(R.id.switchDarkMode);

        //save Mode - even if App is closed and reopened
        sharedPreferences = getSharedPreferences("MODE", Context.MODE_PRIVATE);
        nightMode = sharedPreferences.getBoolean("night", false); //Default: Light

        if(nightMode) {

            switcher.setChecked(true);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }

        switcher.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (nightMode) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    editor = sharedPreferences.edit();
                    editor.putBoolean("night", false);

                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    editor = sharedPreferences.edit();
                    editor.putBoolean("night", true);

                }

                editor.apply();

            }
        });


        /**NavBar**/
        bottomNavigationView  = findViewById(R.id.bottom_navigation);

        getSupportFragmentManager().beginTransaction().replace(R.id.container,homeFragment).commit();

        //Notification on Offline icon to indicate no internet connection
        BadgeDrawable badgeDrawable = bottomNavigationView.getOrCreateBadge(R.id.offline);
        badgeDrawable.setNumber(1);

        if(noInternet == true) {
        badgeDrawable.setVisible(true);

        } else {
            badgeDrawable.setVisible(false);
        }


        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item)
            {
                switch (item.getItemId())
                {
                    case R.id.home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,homeFragment).commit();
                        return true;

                    case R.id.offline:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,offlineFragment).commit();
                        return true;

                    case R.id.search:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,searchFragment).commit();
                        return true;

                    case R.id.account:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,accountFragment).commit();
                        return true;

                    case R.id.settings:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,settingsFragment).commit();
                        return true;
                }

                return false;
            }
        });


    }
}
