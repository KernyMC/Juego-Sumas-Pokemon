package com.example.pryjuegosumas;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashSet;
import java.util.Random;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import androidx.core.app.NotificationCompat;

public class MainActivity extends AppCompatActivity {

    private TextView tvNum1, tvNum2, tvGanar, tvSuma, tvTiempo;
    private ImageView imgDos, imgTres, imgCuatro, imgCinco, imgSeis, imgSiete, imgOcho, imgNueve, imgDiez;
    private int resultadoCorrecto;
    private int intentos = 0;
    private MediaPlayer mediaPlayer, popSound; // Sonido de pop
    private HashSet<String> sumasGeneradas = new HashSet<>();
    private boolean[] imagenSeleccionada = new boolean[9];
    private int[] musicArray = {R.raw.themepk, R.raw.rgpokemon}; // Array con los dos archivos de música

    private CountDownTimer countDownTimer;
    private long timeElapsed = 0;
    private String finalTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Random random = new Random();
        int randomMusic = musicArray[random.nextInt(musicArray.length)];
        mediaPlayer = MediaPlayer.create(this, randomMusic);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();

        // Crear MediaPlayer para el sonido de pop
        popSound = MediaPlayer.create(this, R.raw.pkpop);

        tvNum1 = findViewById(R.id.tv_num1);
        tvNum2 = findViewById(R.id.tv_num2);
        tvSuma = findViewById(R.id.tv_suma);
        tvGanar = findViewById(R.id.tv_ganar);
        tvTiempo = findViewById(R.id.tv_tiempo);
        imgDos = findViewById(R.id.img_dos);
        imgTres = findViewById(R.id.img_tres);
        imgCuatro = findViewById(R.id.img_cuatro);
        imgCinco = findViewById(R.id.img_cinco);
        imgSeis = findViewById(R.id.img_seis);
        imgSiete = findViewById(R.id.img_siete);
        imgOcho = findViewById(R.id.img_ocho);
        imgNueve = findViewById(R.id.img_nueve);
        imgDiez = findViewById(R.id.img_diez);

        imgDos.setTag("2");
        imgTres.setTag("3");
        imgCuatro.setTag("4");
        imgCinco.setTag("5");
        imgSeis.setTag("6");
        imgSiete.setTag("7");
        imgOcho.setTag("8");
        imgNueve.setTag("9");
        imgDiez.setTag("10");

        generarSuma();
        imgClic();

        countDownTimer = new CountDownTimer(Long.MAX_VALUE, 1000) {
            public void onTick(long millisUntilFinished) {
                timeElapsed += 1000; // Incrementa el tiempo transcurrido en 1 segundo
                int seconds = (int) (timeElapsed / 1000) % 60;
                int minutes = (int) ((timeElapsed / (1000 * 60)) % 60);
                int hours = (int) ((timeElapsed / (1000 * 60 * 60)) % 24);
                tvTiempo.setText(String.format("%02d:%02d:%02d", hours, minutes, seconds));
            }

            public void onFinish() {
                // No necesitamos hacer nada aquí
            }
        }.start();

        if(sumasGeneradas.size() == 9) {
            tvNum1.setVisibility(View.GONE);
            tvNum2.setVisibility(View.GONE);
            tvSuma.setVisibility(View.GONE);
            tvGanar.setText("¡Has Ganado!");
            countDownTimer.cancel(); // Detén el temporizador
            return;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }

        if (popSound != null) {
            popSound.release();
            popSound = null;
        }
    }

    private void showNotification() {
        String CHANNEL_ID = "channel_id";
        String CHANNEL_NAME = "channel_name";

        NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
        ((NotificationManager) getSystemService(NOTIFICATION_SERVICE)).createNotificationChannel(notificationChannel);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("¡Felicidades!")
                .setContentText("Has Ganado, eres todo un campeón")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        ((NotificationManager) getSystemService(NOTIFICATION_SERVICE)).notify(0, builder.build());

    }

    private void generarSuma() {
        if(sumasGeneradas.size() == 9) {
            tvNum1.setVisibility(View.GONE);
            tvNum2.setVisibility(View.GONE);
            tvSuma.setVisibility(View.GONE);
            tvGanar.setText("¡Has Ganado!");
            countDownTimer.cancel();
            finalTime = tvTiempo.getText().toString(); // Almacenar el tiempo final

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(MainActivity.this, PantallaFinal.class);
                    intent.putExtra("Tiempo Final", finalTime); // Pasar el tiempo final a PantallaFinal
                    startActivity(intent);
                    finish();
                }
            }, 500); // Retraso de 5 segundos

            return;
        }

        Random r = new Random();
        int num1 = r.nextInt(9) + 1;
        int num2 = 1;
        String suma = num1 + "+" + num2;

        while(sumasGeneradas.contains(suma)) {
            num1 = r.nextInt(9) + 1;
            num2 = 1;
            suma = num1 + "+" + num2;
        }

        sumasGeneradas.add(suma);
        resultadoCorrecto = num1 + num2;
        tvNum1.setText(String.valueOf(num1));
        tvNum2.setText(String.valueOf(num2));
    }

    private void imgClic() {
        View.OnClickListener clicImagen = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Reproducir el sonido de pop
                popSound.start();

                int tag = Integer.parseInt(v.getTag().toString());
                if(imagenSeleccionada[tag-2]) {
                    return;
                }
                if(tag == resultadoCorrecto) {
                    ((ImageView)v).setImageResource(R.drawable.pkball);
                    imagenSeleccionada[tag-2] = true;
                    generarSuma(); // Generar una nueva suma después de seleccionar la respuesta correcta
                } else {
                    Toast.makeText(MainActivity.this, "Respuesta incorrecta", Toast.LENGTH_SHORT).show();
                }
            }
        };

        imgDos.setOnClickListener(clicImagen);
        imgTres.setOnClickListener(clicImagen);
        imgCuatro.setOnClickListener(clicImagen);
        imgCinco.setOnClickListener(clicImagen);
        imgSeis.setOnClickListener(clicImagen);
        imgSiete.setOnClickListener(clicImagen);
        imgOcho.setOnClickListener(clicImagen);
        imgNueve.setOnClickListener(clicImagen);
        imgDiez.setOnClickListener(clicImagen);
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Detener la música cuando la aplicación se ejecuta en segundo plano
        if (mediaPlayer != null) {
            mediaPlayer.pause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Reanudar la música cuando la aplicación vuelve a primer plano
        if (mediaPlayer != null) {
            mediaPlayer.start();
        }
    }
}