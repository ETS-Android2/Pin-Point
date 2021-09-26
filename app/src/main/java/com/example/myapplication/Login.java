package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.api.aws.GsonVariablesSerializer;
import com.amplifyframework.api.graphql.GraphQLRequest;
import com.amplifyframework.api.graphql.SimpleGraphQLRequest;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Pin;
import com.amplifyframework.datastore.generated.model.User;
import com.amplifyframework.storage.s3.AWSS3StoragePlugin;

import java.util.Collections;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        configure();
        Button signIn=findViewById(R.id.signIn_button);
        EditText username=findViewById(R.id.username_signIn);
        EditText password=findViewById(R.id.password_signIn);
        Button signUp=findViewById(R.id.createNewUser);

        signIn.setOnClickListener(view -> signInfunc(username.getText().toString(),password.getText().toString()));

        signUp.setOnClickListener(view ->
        { Intent intent=new Intent(Login.this, com.example.myapplication.signup.class);
            startActivity(intent);
        });

        Button fogetpassword=findViewById(R.id.forgetPassword);
        fogetpassword.setOnClickListener(view -> {
            Intent newIntent=new Intent(Login.this,ForgetPasswordName.class);
            startActivity(newIntent);
        });
//        fogetpassword.setOnClickListener(view -> forgetpassword(username.getText().toString()));
    }


    void signInfunc(String userName, String password){
        Amplify.Auth.signIn(
                userName,
                password,
                success ->{
                    Log.i("signIn", "signIn successful: " + success.toString());
                    Intent goToMain = new Intent(Login.this, Dashboard.class);
                    goToMain.putExtra("userName",userName);
                    startActivity(goToMain);
                },
                error ->{
                    Log.e("signIn", "signIn failed: " + error.toString());
                }
        );
    }
    void configure(){
        try {
            Amplify.addPlugin(new AWSApiPlugin());
            Amplify.addPlugin(new AWSCognitoAuthPlugin());
            Amplify.addPlugin(new AWSS3StoragePlugin());
            Amplify.configure(getApplicationContext());

            Log.i("MyAmplifyApp", "Initialized Amplify");
        } catch (AmplifyException error) {
            Log.e("MyAmplifyApp", "Could not initialize Amplify", error);
        }
        Amplify.Auth.fetchAuthSession(
                result -> Log.i("AmplifyQuickstart", result.toString()),
                error -> Log.e("AmplifyQuickstart", error.toString())
        );
    }


}

//        Amplify.API.query(
//                ModelQuery.(Pin.class, User.USER_NAME: "Fsdds" ),
//                response -> {
//                    for (Pin todo : response.getData()) {
//                        Log.i("MyAmplifyApp", Pin.getName());
//                    }
//                },
//                error -> Log.e("MyAmplifyApp", "Query failure", error)
//        );

//        private GraphQLRequest<Pin> getPinRequest(String id) {
//            String document = "query getPin($id: ID!) { "
//                    + "getTodo(id: $id) { "
//                    + "id "
//                    + "name "
//                    + "}"
//                    + "}";
//            return new SimpleGraphQLRequest<>(
//                    document,
//                    Collections.singletonMap("id", id),
//                    Pin.class,
//                    new GsonVariablesSerializer());
//        }

//
//Amplify.API.query(getTodoRequest("[TODO_ID]"),
//        response -> Log.d("MyAmplifyApp", "response" + response),
//        error -> Log.e("MyAmplifyApp", "error" + error)
//        );