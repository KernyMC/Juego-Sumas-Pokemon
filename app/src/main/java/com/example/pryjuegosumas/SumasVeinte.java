package com.example.pryjuegosumas;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class SumasVeinte extends AppCompatActivity {

    ImageView[] imagenes = new ImageView[12];
    int[] sumas = {20, 16, 19, 18, 15, 15, 17, 17, 19, 16, 18, 20}; // Los valores de las sumas
    ImageView ultimoSeleccionado = null; // La última ImageView seleccionada
    int ultimoValor = -1; // El último valor seleccionado
    MediaPlayer flipSound; // Sonido de voltear la carta

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sumas_veinte);

        // Crear MediaPlayer para el sonido de voltear la carta
        flipSound = MediaPlayer.create(this, R.raw.flipcard);

        // Asignar los valores a las ImageViews en el orden especificado
        int[] ids = {R.id.iv_1, R.id.iv_2, R.id.iv_3, R.id.iv_4, R.id.iv_5, R.id.iv_6, R.id.iv_7, R.id.iv_8, R.id.iv_9, R.id.iv_10, R.id.iv_11, R.id.iv_12};
        for (int i = 0; i < 12; i++) {
            imagenes[i] = findViewById(ids[i]);
            imagenes[i].setTag(sumas[i]); // Asignar el valor de la suma como una etiqueta a la ImageView

            // Establecer la imagen inicial a "detrascard.png"
            imagenes[i].setImageResource(R.drawable.detrascard);

            imagenes[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        // Reproducir el sonido de voltear la carta
                        flipSound.start();

                        ImageView seleccionado = (ImageView) v;
                        int valorSeleccionado = (int) seleccionado.getTag();

                        if (ultimoSeleccionado == null) { // Si es la primera selección
                            ultimoSeleccionado = seleccionado;
                            ultimoValor = valorSeleccionado;
                            // Cambiar la imagen a la imagen correspondiente a su valor de suma
                            seleccionado.setImageResource(R.drawable.suma1 + valorSeleccionado - 15);
                        } else {
                            // Si es la segunda selección
                            seleccionado.setImageResource(R.drawable.suma1 + valorSeleccionado - 15);
                            if (ultimoValor == valorSeleccionado) { // Si los valores son iguales
                                ultimoSeleccionado.setVisibility(View.INVISIBLE); // Hacerlas invisibles
                                seleccionado.setVisibility(View.INVISIBLE);
                            } else { // Si los valores no son iguales
                                // Cambiar las imágenes de nuevo a "detrascard.png" después de un corto retraso
                                seleccionado.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        ultimoSeleccionado.setImageResource(R.drawable.detrascard);
                                        seleccionado.setImageResource(R.drawable.detrascard);
                                        ultimoSeleccionado = null; // Restablecer para la próxima pareja
                                    }
                                }, 800); // 1000 milisegundos de retraso
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (flipSound != null) {
            flipSound.release();
            flipSound = null;
        }
    }
}