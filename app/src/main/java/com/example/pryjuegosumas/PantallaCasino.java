package com.example.pryjuegosumas;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class PantallaCasino extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private Handler handler;
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_casino);

        VideoView videoView = findViewById(R.id.videoView);
        Uri video = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.pikacasin);
        videoView.setVideoURI(video);

        // Crear MediaPlayer para el sonido de fondo
        mediaPlayer = MediaPlayer.create(this, R.raw.pikainicio);
        mediaPlayer.setLooping(false); // Configurar para que se repita
        mediaPlayer.start(); // Iniciar la reproducción del sonido de fondo

        videoView.setOnPreparedListener(mp -> {
            mp.setLooping(false);
            videoView.start();

            // Detener la reproducción después de 6 segundos y navegar a SumasVeinte
            handler = new Handler();
            runnable = () -> {
                videoView.pause();
                Intent intent = new Intent(PantallaCasino.this, SumasVeinte.class);
                startActivity(intent);
            };
            handler.postDelayed(runnable, 6000);
        });

        // Configurar el botón bt_saltar para navegar a SumasVeinte cuando se presiona
        ImageView btSaltar = findViewById(R.id.bt_saltar);
        btSaltar.setOnClickListener(v -> {
            if (mediaPlayer != null) {
                mediaPlayer.stop();
                mediaPlayer.release();
                mediaPlayer = null;
            }
            videoView.stopPlayback();
            handler.removeCallbacks(runnable);
            Intent intent = new Intent(PantallaCasino.this, SumasVeinte.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}