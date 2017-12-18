package com.example.chandru.myapplicationvook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Signup_activity extends AppCompatActivity {

    public int setup_signup;
    EditText pass_signup,name_signup,email_signup;
    TextView txt_signup,Signin_txtbtn;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final PrefManager p = new PrefManager(this);

        setContentView(R.layout.activity_signup_activity);
        setup_signup = 1;
//        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        pass_signup = (EditText) findViewById(R.id.password);
        name_signup = (EditText) findViewById(R.id.name);
        email_signup = (EditText) findViewById(R.id.email);
        txt_signup=findViewById(R.id.textEye);
        Signin_txtbtn = (TextView) findViewById(R.id.Signin_txtbtn);
        txt_signup.setBackgroundResource(R.drawable.hide_pwd);


        findViewById(R.id.close_signup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Signup_activity.this, MainActivity.class));
            }
        });

        findViewById(R.id.textEye).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (setup_signup == 1) {
                    setup_signup = 0;
                    pass_signup.setTransformationMethod(null);
                    if (pass_signup.getText().length() > 0)
                        pass_signup.setSelection(pass_signup.getText().length());

                    txt_signup.setBackgroundResource(R.drawable.view_pwd);
                } else {
                    setup_signup = 1;
                    pass_signup.setTransformationMethod(new PasswordTransformationMethod());
                    if (pass_signup.getText().length() > 0)
                        pass_signup.setSelection(pass_signup.getText().length());
                    txt_signup.setBackgroundResource(R.drawable.hide_pwd);


                }
            }
        });

        findViewById(R.id.signupbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!signupValidate()){
                    Log.d("Signup", "onClick: fail");
                    onSignupFail();
                    return;
                }
                else {
                    p.saveLoginDetails(email_signup.getText().toString(),pass_signup.getText().toString());
                    findViewById(R.id.signupbtn).setEnabled(false);
                    onSignupSuccess();
                }
            }
        });

        Signin_txtbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(Signup_activity.this, "mov to signin  " + pass_signup.getText(), Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Signup_activity.this, Signin_activity.class));
                finish();

            }
        });
    }

    public void onSignupSuccess() {
        findViewById(R.id.signupbtn).setEnabled(true);
        setResult(RESULT_OK, null);
        startActivity(new Intent(Signup_activity.this,FirstScreen.class));
        finish();
    }

    private void onSignupFail() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();
        findViewById(R.id.signupbtn).setEnabled(true);
    }


    private boolean signupValidate() {
        boolean valid = true;
        Pattern pattern;
        Matcher matcher,matcher1;
        String name = name_signup.getText().toString();
        String email = email_signup.getText().toString();
        String password = pass_signup.getText().toString();

        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        if (TextUtils.isEmpty(name)) {
            name_signup.setError("Enter your name");
            valid = false;
        }
        else if (name.length() < 3) {
            name_signup.setError("At least 3 characters");
            valid = false;
        }
        else {
            name_signup.setError(null);
        }

        if(TextUtils.isEmpty(email)){
            email_signup.setError("Enter a email address");
            valid = false;
        }
        else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            email_signup.setError("Enter a valid email address");
            valid = false;
        }
        else {
            email_signup.setError(null);
        }

        if (TextUtils.isEmpty(password)){
            pass_signup.setError("Enter password");
            valid = false;
        }
        else if (!matcher.matches()){
            pass_signup.setError("contains at least 1 Upper case, 1 lower case,1 number and 1 special character ");
            valid = false;
        }
        else {
            pass_signup.setError(null);
        }

        return valid;

    }
    }

