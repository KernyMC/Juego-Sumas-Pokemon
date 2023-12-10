package com.example.pryjuegosumas;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashSet;
import java.util.Random;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.media.MediaPlayer;

public class MainActivity extends AppCompatActivity {

    private TextView tvNum1, tvNum2, tvGanar, tvSuma;
    private ImageView imgDos, imgTres, imgCuatro, imgCinco, imgSeis, imgSiete, imgOcho, imgNueve, imgDiez;
    private int resultadoCorrecto;
    private int intentos = 0;
    private MediaPlayer mediaPlayer;
    private HashSet<String> sumasGeneradas = new HashSet<>();
    private boolean[] imagenSeleccionada = new boolean[9];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mediaPlayer = MediaPlayer.create(this, R.raw.themepk);
        mediaPlayer.start();

        tvNum1 = findViewById(R.id.tv_num1);
        tvNum2 = findViewById(R.id.tv_num2);
        tvSuma = findViewById(R.id.tv_suma);
        tvGanar = findViewById(R.id.tv_ganar);
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
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    private void generarSuma() {
        if(sumasGeneradas.size() == 9) {
            tvNum1.setVisibility(View.GONE);
            tvNum2.setVisibility(View.GONE);
            tvSuma.setVisibility(View.GONE);
            tvGanar.setText("¡Has Ganado!");
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
}