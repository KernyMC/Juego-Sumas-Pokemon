package com.example.pryjuegosumas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class FinalCasino extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_casino);

        Button btMenu = findViewById(R.id.bt_menu);
        btMenu.setOnClickListener(v -> {
            Intent intent = new Intent(FinalCasino.this, Menu.class);
            startActivity(intent);
        });
    }
}