package com.example.chandru.myapplicationvook;

import android.content.Intent;
import android.support.design.widget.Snackbar;
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

import static com.example.chandru.myapplicationvook.Splash.u_login;


public class Signin_activity extends AppCompatActivity {
    public int setup_signin;
    EditText pass_signin,email_signin;
    TextView txt_signin,Signup_txtbtn;
    PrefManager p = new PrefManager(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin_activity);

        setup_signin =1;
        Signup_txtbtn=(TextView)findViewById(R.id.Signup_txtbtn);
        email_signin =(EditText)findViewById(R.id.email_signin);
        pass_signin =(EditText)findViewById(R.id.password_signin);
        txt_signin =(TextView) findViewById(R.id.textEye_signin);
        txt_signin.setBackgroundResource(R.drawable.hide_pwd);

        txt_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(setup_signin ==1){
                    setup_signin =0;
                    pass_signin.setTransformationMethod(null);
                    if(pass_signin.getText().length() > 0)
                        pass_signin.setSelection(pass_signin.getText().length());

                    txt_signin.setBackgroundResource(R.drawable.view_pwd);
                }
                else {
                    setup_signin =1;
                    pass_signin.setTransformationMethod(new PasswordTransformationMethod());
                    if(pass_signin.getText().length()>0)
                        pass_signin.setSelection(pass_signin.getText().length());
                    txt_signin.setBackgroundResource(R.drawable.hide_pwd);



                }
            }
        });
        findViewById(R.id.button_signin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!signinValidate()){
                        Log.d("Signup", "onClick: fail");
                        onSigninFail();
                        return;

                }
                else {
                    onSigninSuccess();

                }
            }
        });

        Signup_txtbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(Signin_activity.this, "mov to signup"+pass_signin.getText(), Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Signin_activity.this,Signup_activity.class));
                finish();

            }
        });


        findViewById(R.id.mov_forgpass).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Signin_activity.this,Forgotpass_activity.class));
                finish();
            }
        });

        findViewById(R.id.close_signin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Signin_activity.this, MainActivity.class));
                finish();
            }
        });
    }
    public void onSigninSuccess() {
        if(email_signin.getText().toString().equals(p.getEmail())&& pass_signin.getText().toString().equals(p.getPassword())) {
            findViewById(R.id.button_signin).setEnabled(true);
            setResult(RESULT_OK, null);
            u_login.edit().putBoolean("userStatus", true).commit();
            startActivity(new Intent(Signin_activity.this, FirstScreen.class));
            finish();
        }
        else {
            Snackbar mySnackbar = Snackbar.make(findViewById(android.R.id.content),
                    "Invalid", Snackbar.LENGTH_SHORT);
            mySnackbar.setAction("Ok", null);
            mySnackbar.show();

        }
    }

    private void onSigninFail() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();
        findViewById(R.id.button_signin).setEnabled(true);
    }

    private boolean signinValidate() {
        boolean valid = true;
        Pattern pattern;
        Matcher matcher,matcher1;
        String email = email_signin.getText().toString();
        String password = pass_signin.getText().toString();

        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);


        if(TextUtils.isEmpty(email)){
            email_signin.setError("Enter a email address");
            valid = false;
        }
        else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            email_signin.setError("Enter a valid email address");
            valid = false;
        }
        else {
            email_signin.setError(null);
        }


        if (TextUtils.isEmpty(password)){
            pass_signin.setError("Enter password");
            valid = false;
        }
        else if (!matcher.matches()){
            pass_signin.setError("contains at least 1 Upper case, 1 lower case,1 number and 1 special character ");
            valid = false;
        }
        else {
            pass_signin.setError(null);
        }

        return valid;


    }

}
