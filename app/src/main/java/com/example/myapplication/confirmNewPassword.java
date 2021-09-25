package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.amplifyframework.core.Amplify;

public class confirmNewPassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_new_password);

        EditText newCode=findViewById(R.id.resrtPasswordCode);
        EditText newPassword=findViewById(R.id.newPassword);
        Button confirmnewPassword=findViewById(R.id.confirmnewpassword);
        confirmnewPassword.setOnClickListener(view -> {
            changePassword(newCode.getText().toString(),newPassword.getText().toString());
            Intent gotologinPage=new Intent(confirmNewPassword.this,Login.class);
            startActivity(gotologinPage);
        });
    }

    void changePassword(String code,String password){
        Amplify.Auth.confirmResetPassword(
                password,
                code,
                () -> Log.i("AuthQuickstart", "New password confirmed"),
                error -> Log.e("AuthQuickstart", error.toString())
        );
    }
}