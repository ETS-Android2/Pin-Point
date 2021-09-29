package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.amplifyframework.core.Amplify;

public class Verfication extends AppCompatActivity {

    private LoadingDialog loadingDialog;



    private String username;
    private String password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadingDialog = new LoadingDialog(Verfication.this);


        setContentView(R.layout.activity_verfication);
        Button verfiy=findViewById(R.id.verficationButton);
        EditText code=findViewById(R.id.vervicationTextInput);

        Intent intent=getIntent();
        username=intent.getExtras().getString("username","");
//        username=intent.getExtras().getString("username");
//        password=intent.getExtras().getString("password","");

        verfiy.setOnClickListener(view -> {
            loadingDialog.startLoading();


            verification(username, code.getText().toString());
        });
    }

    void verification (String username,String verficationCode){
        Amplify.Auth.confirmSignUp(
                username,
                verficationCode,
                success -> {
                    Log.i("verification", "verification successful: " + success.toString());
                    Intent goToSignIn = new Intent(Verfication.this, Login.class);
                    goToSignIn.putExtra("username", username);
                    loadingDialog.dismissLoading();
                    startActivity(goToSignIn);
                },
                error -> {
                    Log.e("verification", "verification failed: " + error.toString());
                });
    }

    @Override
    public void onBackPressed() {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }
}
