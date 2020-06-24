package com.project.bushop.bushop;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    public static final String PREFS_NAME = "MyPrefsFile";
    Intent intent,intentSlider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        boolean IS_FIRST_TIME_LAUNCH = settings.getBoolean("IS_FIRST_TIME_LAUNCH",true);

        if(IS_FIRST_TIME_LAUNCH == true)
        {
            startActivity(new Intent(MainActivity.this, WelcomeActivity.class));
            finish();
        }
        else
        {
            //Get "hasLoggedIn" value. If the value doesn't exist yet, false is returned
            boolean hasLoggedIn = settings.getBoolean("hasLoggedIn", false);

            if (hasLoggedIn == false)
                intent = new Intent(this, Login.class);
            else
                intent = new Intent(this, Temp.class);

            startActivity(intent);
            finish();
        }
    }
}
