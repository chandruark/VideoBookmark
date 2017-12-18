package com.example.chandru.myapplicationvook.Login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.example.chandru.myapplicationvook.Landing.FirstScreen;
import com.example.chandru.myapplicationvook.R;

public class Forgotpass_activity extends AppCompatActivity {
    EditText email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpass_activity);
        email = findViewById(R.id.email_forgpass);

        findViewById(R.id.close_forgot).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Forgotpass_activity.this, Signin_activity.class));
                finish();
            }
        });

        findViewById(R.id.button_reset).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validate()){
                 startActivity(new Intent(Forgotpass_activity.this,FirstScreen.class));
                 finish();
                }
            }
        });

    }
    boolean validate(){
        boolean valid = true;
        String s= email.getText().toString();
        if(TextUtils.isEmpty(s)){
            email.setError("Enter a email address");
            valid = false;
        }
        else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(s).matches()) {
            email.setError("Enter a valid email address");
            valid = false;
        }
        else {
            email.setError(null);
        }
        return valid;
    }
}
