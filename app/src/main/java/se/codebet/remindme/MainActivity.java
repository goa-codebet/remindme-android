package se.codebet.remindme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import se.codebet.remindme.AuthActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boolean isAuthenticated = false;

        if (!isAuthenticated) {
            Intent intent = new Intent(this, AuthActivity.class);
            startActivity(intent);
        }
    }
}