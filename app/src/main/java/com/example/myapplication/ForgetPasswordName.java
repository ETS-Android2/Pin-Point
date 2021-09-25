package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.amplifyframework.core.Amplify;

public class ForgetPasswordName extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password_name);

        EditText enterusername=findViewById(R.id.forgetUserNameName);
        Button confimuserName=findViewById(R.id.confirmforgetusername);
        confimuserName.setOnClickListener(view -> {
            forgetpassword(enterusername.getText().toString());
            Intent createnewPassWord=new Intent(ForgetPasswordName.this,confirmNewPassword.class);
            startActivity(createnewPassWord);
        });

    }


    void forgetpassword(String userName){
        Amplify.Auth.resetPassword(
                userName,
                result -> Log.i("AuthQuickstart", result.toString()),
                error -> Log.e("AuthQuickstart", error.toString())
        );
    }
}