package com.example.laggin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.laggin.databinding.ActivityMainMenuBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;


public class MainMenu extends AppCompatActivity {

    private ActivityMainMenuBinding binding;
    Database data;
    Button logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_screen);
        data = new Database(this);
        Boolean checkSession = data.checkSession("ada");
        if(checkSession == false){
            Intent login = new Intent(MainMenu.this, LoginActivity.class);
            startActivity(login);
            finish();
        }
        logout = (Button)findViewById(R.id.button4);
        logout.setOnClickListener(view -> {
            Boolean updtSession = data.upgradeSession("No DATA", 1);
            if(updtSession == true ){
                Toast.makeText(getApplicationContext(), "Logout", Toast.LENGTH_LONG).show();
                Intent login = new Intent(MainMenu.this, LoginActivity.class);
                startActivity(login);
                finish();
            }
        });


        /*binding = ActivityMainMenuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main_menu);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
        //login*/

        }
}