package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.amplifyframework.auth.AuthUserAttributeKey;
import com.amplifyframework.auth.options.AuthSignUpOptions;
import com.amplifyframework.core.Amplify;

public class signup extends AppCompatActivity {
   private LoadingDialog loadingDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadingDialog = new LoadingDialog(signup.this);
        setContentView(R.layout.activity_signup);
        Button signUp = findViewById(R.id.signup_button);
        EditText username = findViewById(R.id.username_signup_text);
        EditText email = findViewById(R.id.email_signup_text);
        EditText password = findViewById(R.id.password_signup_text);
        Button signIn_From_SignUp=findViewById(R.id.signIn_from_SignUp);
        signIn_From_SignUp.setOnClickListener(view -> {
            Intent signIn_from_signUp_Intent=new Intent(signup.this,Login.class);
            startActivity(signIn_from_signUp_Intent);
        });
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadingDialog.startLoading();
                signUp(username.getText().toString(), email.getText().toString(), password.getText().toString());

            }
        });
    }

    // Sign Up Function
    void signUp(String username, String email, String password) {
        Amplify.Auth.signUp(
                username,
                password,
                AuthSignUpOptions.builder()
                        .userAttribute(AuthUserAttributeKey.email(), email)
                        .build(),
                success -> {
                    Log.i("signUp", "signUp successful: " + success.toString());
                    Intent goToVerification = new Intent(signup.this, Verfication.class);
                    goToVerification.putExtra("username", username);
//                    goToVerification.putExtra("password", password);
                    loadingDialog.dismissLoading();
                    startActivity(goToVerification);
                },
                error -> {
                    Log.e("signUp", "signUp failed: " + error.toString());
                });
    }

    // Press Back fuction
    @Override
    public void onBackPressed() {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }
}
