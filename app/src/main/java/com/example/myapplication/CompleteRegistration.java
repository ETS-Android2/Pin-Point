package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.User;
import com.amplifyframework.storage.s3.AWSS3StoragePlugin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

public class CompleteRegistration extends AppCompatActivity {

    String fileName;
    String userName;
    List<User> followers;
    List<User> followings;
    Uri dataUri;
    TextView firstNameInput;
    TextView lastNameInput;
    TextView bioInput;
    TextView imgName;
    Button selectImgBtn;
    Button saveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_registration);
//        configure();
        Intent intent = getIntent();

        userName = intent.getExtras().getString("userName");
        firstNameInput = findViewById(R.id.firstNameInput);
        lastNameInput = findViewById(R.id.lastNameInput);
        bioInput = findViewById(R.id.bioInput);
        imgName = findViewById(R.id.imgName);
        selectImgBtn = findViewById(R.id.selectImgBtn);
        saveBtn = findViewById(R.id.saveBtn);

        selectImgBtn.setOnClickListener(view -> pickFile());

        saveBtn.setOnClickListener(view -> handelRegistration());
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        assert data != null;
        dataUri = data.getData();
        File file = new File(dataUri.getPath());
        fileName = file.getName();
        Log.i("0000000000", fileName);
        super.onActivityResult(requestCode, resultCode, data);
    }

    @SuppressLint("IntentReset")
    private void pickFile() {
        @SuppressLint("IntentReset") Intent selectedFile = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//        selectedFile.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        selectedFile.setType(("*/*"));
        selectedFile = Intent.createChooser(selectedFile, "Select File");
        startActivityForResult(selectedFile, 1234);
    }

    private void uploadImage() {
        try {
            if (dataUri != null) {
                InputStream inputStream = getContentResolver().openInputStream(dataUri);
                Amplify.Storage.uploadInputStream(
                        fileName,
                        inputStream,
                        result -> Log.i("MyAmplifyApp", "Successfully uploaded: " + result.getKey()),
                        storageFailure -> Log.e("MyAmplifyApp", "Upload failed", storageFailure)
                );
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    void handelRegistration() {
        User newUser = User.builder().userName(userName).firstName(firstNameInput.getText().toString()).lastName(lastNameInput.getText().toString()).bio(bioInput.getText().toString()).img(fileName).build();

        Amplify.API.mutate(ModelMutation.create(newUser),
                response -> {
                    Log.i("CompleteRegistration", "Todo with id: " + response.toString());
                    uploadImage();
//                    Intent goToMain = new Intent(CompleteRegistration.this, Dashboard.class);

                },
                error -> Log.e("CompleteRegistration", "Create failed", error)
        );
    }

    void configure() {
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