package com.example.androidapp_practice7;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.squareup.picasso.Picasso;

import java.io.File;

public class Userinfo_activity extends AppCompatActivity {

    private TextView resultDataName;
    private TextView resultDataAge;
    private TextView resultDataCity;
    private ImageView imageView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_userinfo);

        resultDataName = findViewById(R.id.tvResultName);
        resultDataAge = findViewById(R.id.tvResultAge);
        resultDataCity = findViewById(R.id.tvResultCity);

        imageView = findViewById(R.id.imaveView);

        Button exitButton = findViewById(R.id.exit);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteCache(getApplicationContext());

                finishAffinity();
                System.exit(0);
            }
        });

        loadUsername();
        loadUserage();
        loadUsercity();
        loadUserURL();
    }

    protected void loadUsername() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyUsername", MODE_PRIVATE);

        String username = sharedPreferences.getString("username", null);

        if(username!=null) {
            resultDataName.setText("Welcome back, " + username);
        }

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("username");
        editor.commit();
    }

    private void loadUserage() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyUserage", MODE_PRIVATE);

        String userage = sharedPreferences.getString("userage", null);

        if(userage!=null) {
            resultDataAge.setText("at the age of " + userage);
        }

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("userage");
        editor.commit();
    }

    private void loadUsercity() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyUsercity", MODE_PRIVATE);

        String usercity = sharedPreferences.getString("usercity", null);

        if(usercity!=null) {
            resultDataCity.setText("from - " + usercity);
        }

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("usercity");
        editor.commit();
    }

    private void loadUserURL() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyURL", MODE_PRIVATE);

        String userURL = sharedPreferences.getString("url", null);

        if(userURL!=null) {
            Picasso.get().load(userURL).into(imageView);
            Log.d("img", "imagesaved");
        }

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("url");
        editor.commit();
    }

    public void deleteCache(Context context) {
        try {
            File dir = context.getCacheDir();
            if (dir != null && dir.isDirectory()) {
                deleteDir(dir);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (String child : children) {
                boolean success = deleteDir(new File(dir, child));
                if (!success) {
                    return false;
                }
            }
            return dir.delete();
        } else if (dir != null && dir.isFile()) {
            return dir.delete();
        } else {
            return false;
        }
    }
}