package com.example.clicker;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.provider.Settings;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private long lastClickTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final Button button = findViewById(R.id.simulateTapButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long currentTime = System.currentTimeMillis();
                if (currentTime - lastClickTime < 1000) {
                    Toast.makeText(MainActivity.this, "Za szybko!", Toast.LENGTH_SHORT).show();
                    return;
                }
                lastClickTime = currentTime;

                if (MyAccessibilityService.getInstance() != null) {
                    MyAccessibilityService.getInstance().simulateTap(600,1430);
                    Log.d("here", "tutaj");
                } else {
                    Toast.makeText(MainActivity.this, "Enable Accessibility Service first!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}