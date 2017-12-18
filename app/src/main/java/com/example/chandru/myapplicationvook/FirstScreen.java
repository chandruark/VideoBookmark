package com.example.chandru.myapplicationvook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import static com.example.chandru.myapplicationvook.Splash.prefs;
import static com.example.chandru.myapplicationvook.Splash.u_login;

public class FirstScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_screen);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(FirstScreen.this,WelcomeActivity.class));
            finish();
            return true;
        }
        if (id == R.id.logout) {
            u_login.edit().putBoolean("userStatus", false).commit();
            startActivity(new Intent(FirstScreen.this,Splash.class));
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}