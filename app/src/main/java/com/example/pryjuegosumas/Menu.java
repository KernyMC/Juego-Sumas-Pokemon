package com.example.pryjuegosumas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Menu extends AppCompatActivity {

    ImageView juegoVeinte, juegoSumas;
    MediaPlayer botonSound; // Sonido del botón

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // Crear MediaPlayer para el sonido del botón
        botonSound = MediaPlayer.create(this, R.raw.pkboton);

        juegoVeinte = findViewById(R.id.iv_smVeinte);
        juegoVeinte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Reproducir el sonido del botón
                botonSound.start();

                Intent intent = new Intent(Menu.this, PantallaCasino.class);
                startActivity(intent);
            }
        });

        juegoSumas = findViewById(R.id.iv_sm);
        juegoSumas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Reproducir el sonido del botón
                botonSound.start();

                Intent intent = new Intent(Menu.this, PantallaCarga.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (botonSound != null) {
            botonSound.release();
            botonSound = null;
        }
    }
}