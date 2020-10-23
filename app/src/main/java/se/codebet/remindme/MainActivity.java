package se.codebet.remindme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import se.codebet.remindme.ui.auth.AuthActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boolean isAuthenticated = false;

        SharedPreferences sharedPref = MainActivity.this.getSharedPreferences("CB", Context.MODE_PRIVATE);
        String token = sharedPref.getString("token", "false");

        if(token != "false") {
            isAuthenticated = true;
        }

        if (!isAuthenticated) {
            Intent intent = new Intent(this, AuthActivity.class);
            startActivity(intent);
        } else {
            Log.i("cb", "har en token");
        }
    }
}