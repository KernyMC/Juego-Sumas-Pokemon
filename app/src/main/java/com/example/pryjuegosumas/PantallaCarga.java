package com.example.pryjuegosumas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.media.MediaPlayer;
import android.os.Handler;

public class PantallaCarga extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_carga);
        mediaPlayer = MediaPlayer.create(this, R.raw.james);
        mediaPlayer.start();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(PantallaCarga.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        },9000);
    }
}