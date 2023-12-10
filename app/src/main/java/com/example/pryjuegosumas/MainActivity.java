package com.example.pryjuegosumas;

import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.media.MediaPlayer;

public class MainActivity extends AppCompatActivity {

    private TextView tvNum1, tvNum2;
    private ImageView imgDos, imgTres, imgCuatro, imgCinco, imgSeis, imgSiete, imgOcho, imgNueve, imgDiez;
    private int resultadoCorrecto;
    private int intentos = 0;
    private MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mediaPlayer = MediaPlayer.create(this, R.raw.themepk);
        mediaPlayer.start();
        // Inicializar vistas
        tvNum1 = findViewById(R.id.tv_num1);
        tvNum2 = findViewById(R.id.tv_num2);
        imgDos = findViewById(R.id.img_dos);
        imgTres = findViewById(R.id.img_tres);
        imgCuatro = findViewById(R.id.img_cuatro);
        imgCinco = findViewById(R.id.img_cinco);
        imgSeis = findViewById(R.id.img_seis);
        imgSiete = findViewById(R.id.img_siete);
        imgOcho = findViewById(R.id.img_ocho);
        imgNueve = findViewById(R.id.img_nueve);
        imgDiez = findViewById(R.id.img_diez);

        // Asignar valores a las imágenes
        imgDos.setContentDescription("2");
        imgTres.setContentDescription("3");
        imgCuatro.setContentDescription("4");
        imgCinco.setContentDescription("5");
        imgSeis.setContentDescription("6");
        imgSiete.setContentDescription("7");
        imgOcho.setContentDescription("8");
        imgNueve.setContentDescription("9");
        imgDiez.setContentDescription("10");

        // Generar la primera suma
        generarSuma();

        // Configurar clics en las imágenes
        imgClic();
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

    private void generarSuma() {
        Random random = new Random();
        int num1 = random.nextInt(9) + 1;  // Números del 2 al 10
        int num2 = 1;  // Números del 1 al 9
        resultadoCorrecto = num1 + num2;

        // Mostrar los términos de la suma
        tvNum1.setText(String.valueOf(num1));
        tvNum2.setText(String.valueOf(num2));
    }


    private void imgClic() {
        View.OnClickListener clicImagen = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView imageView = (ImageView) v;
                CharSequence contentDescription = imageView.getContentDescription();

                if (contentDescription != null) {
                    int respuestaUsuario = Integer.parseInt(contentDescription.toString());

                    // Verificar si la respuesta es correcta
                    if (respuestaUsuario == resultadoCorrecto) {
                        // Respuesta correcta, generar la siguiente suma
                        generarSuma();
                        intentos++;
                    } else {
                        // Respuesta incorrecta, puedes manejar esto de acuerdo a tus necesidades
                        // Por ejemplo, mostrar un mensaje al usuario
                        Toast.makeText(MainActivity.this, "Respuesta incorrecta", Toast.LENGTH_SHORT).show();
                    }

                    // Si se han realizado los 9 intentos, puedes manejarlo según tus requerimientos
                    if (intentos == 9) {
                        // Por ejemplo, mostrar un mensaje de éxito o redirigir a otra actividad
                    }
                } else {
                    Toast.makeText(MainActivity.this, "La imagen no tiene una descripción de contenido", Toast.LENGTH_SHORT).show();
                }
            }
        };

        // Asignar el mismo clicListener a todas las imágenes
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