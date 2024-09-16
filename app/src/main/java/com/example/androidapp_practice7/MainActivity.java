package com.example.androidapp_practice7;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText username;
    private EditText userage;
    private EditText usercity;
    private EditText textURL;
    private Button saveButton;
    private TextView resultData;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.editTextUserName);
        userage = findViewById(R.id.editTextUserAge);
        usercity = findViewById(R.id.editTextUserCity);
        textURL = findViewById(R.id.editTextURL);
        saveButton = findViewById(R.id.buttonSaveData);
        resultData = findViewById(R.id.tvResult);

        loadUsername();

        saveButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                saveUsername();
                saveUserage();
                saveUsercity();
                saveUserURL();
                Intent intent = new Intent(MainActivity.this, Userinfo_activity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void saveUsername() {
        String usernameVal = username.getText().toString();
        if (!usernameVal.isEmpty()) {
            SharedPreferences sharedPreferences = getSharedPreferences("MyUsername", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("username", usernameVal);
            editor.apply();

            resultData.setText("Hello, " + usernameVal);
        }
    }

    private void saveUserage() {
        String userageVal = userage.getText().toString();
        if (!userageVal.isEmpty()) {
            SharedPreferences sharedPreferences = getSharedPreferences("MyUserage", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("userage", userageVal);
            editor.apply();
        }
    }

    private void saveUsercity() {
        String usercityVal = usercity.getText().toString();
        if (!usercityVal.isEmpty()) {
            SharedPreferences sharedPreferences = getSharedPreferences("MyUsercity", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("usercity", usercityVal);
            editor.apply();
        }
    }

    private void saveUserURL() {
        String userURL = textURL.getText().toString();
        if (!userURL.isEmpty()) {
            SharedPreferences sharedPreferences = getSharedPreferences("MyURL", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("url", userURL);
            editor.apply();
        }
    }

    private void loadUsername() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyUsername", MODE_PRIVATE);

        String username = sharedPreferences.getString("username", null);

        if(username!=null) {
            resultData.setText("Welcome back, " + username);
        }

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("username");
        editor.commit();
    }
}