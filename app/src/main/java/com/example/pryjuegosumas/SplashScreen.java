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
        // Crear un nuevo MediaPlayer para el sonido del botón
        MediaPlayer buttonSound = MediaPlayer.create(SplashScreen.this, R.raw.pkboton);

        // Iniciar la reproducción del sonido del botón
        buttonSound.start();

        // Liberar el recurso una vez que el sonido ha terminado de reproducirse
        buttonSound.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.release();
            }
        });

        // Cambiar PantallaCarga.class a Menu.class
        startActivity(new Intent(SplashScreen.this, Menu.class));
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