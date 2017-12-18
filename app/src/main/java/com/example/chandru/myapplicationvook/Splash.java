package com.example.chandru.myapplicationvook;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.chandru.myapplicationvook.IntroScreen.MainActivity;
import com.example.chandru.myapplicationvook.Landing.FirstScreen;
import com.example.chandru.myapplicationvook.Model.PrefManager;
import com.example.chandru.myapplicationvook.Onboard.WelcomeActivity;

public class Splash extends AppCompatActivity {
   public static SharedPreferences prefs = null;
   public static SharedPreferences u_login = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {



            super.onCreate(savedInstanceState);
        prefs = getSharedPreferences("firstRun", MODE_PRIVATE);
        u_login= getSharedPreferences("userStatus",MODE_PRIVATE);

        PrefManager p = new PrefManager(Splash.this);
        if(u_login.getBoolean("userStatus",true)){
            Intent intent = new Intent(Splash.this, FirstScreen.class);
            startActivity(intent);
            finish();
        }
        else {

        if (prefs.getBoolean("firstRun", true)) {
            Intent intent = new Intent(Splash.this, WelcomeActivity.class);
            startActivity(intent);
            finish();

        }
        else
        {
            //do nothing
            Intent intent = new Intent(Splash.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        }



    }

}
