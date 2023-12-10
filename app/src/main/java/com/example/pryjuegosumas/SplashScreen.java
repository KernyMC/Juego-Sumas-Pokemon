package com.example.pryjuegosumas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.media.MediaPlayer;
import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {

    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        // Iniciar la reproducción de la música
        mediaPlayer = MediaPlayer.create(this, R.raw.intro);
        mediaPlayer.start();

        TextView btnIniciar = findViewById(R.id.tv_iniciar);
        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SplashScreen.this, MainActivity.class));
                finish();

                // Detener la música cuando se inicia la MainActivity
                if (mediaPlayer != null) {
                    mediaPlayer.stop();
                    mediaPlayer.release();
                    mediaPlayer = null;
                }
            }
        });
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
}