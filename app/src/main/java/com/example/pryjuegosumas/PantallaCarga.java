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

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // Asegurarse de liberar el MediaPlayer cuando la actividad se destruye
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Detener la música cuando la aplicación se ejecuta en segundo plano
        if (mediaPlayer != null) {
            mediaPlayer.pause();
        }
    }@Override
    protected void onResume() {
        super.onResume();

        // Reanudar la música cuando la aplicación vuelve a primer plano
        if (mediaPlayer != null) {
            mediaPlayer.start();
        }
    }
}