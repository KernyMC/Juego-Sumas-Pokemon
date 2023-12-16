package com.example.pryjuegosumas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Menu extends AppCompatActivity {

    ImageView juegoVeinte, juegoSumas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        juegoVeinte = findViewById(R.id.iv_smVeinte);
        juegoVeinte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, PantallaCasino.class);
                startActivity(intent);
            }
        });

        juegoSumas = findViewById(R.id.iv_sm);
        juegoSumas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, PantallaCarga.class);
                startActivity(intent);
            }
        });
    }
}