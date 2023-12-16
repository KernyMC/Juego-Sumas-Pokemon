package com.example.pryjuegosumas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class PantallaFinal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_final);

        TextView tvTiempo = findViewById(R.id.tv_tiempo);
        String finalTime = getIntent().getStringExtra("Tiempo Final"); // Recibir el tiempo final
        tvTiempo.setText(finalTime); // Mostrar el tiempo final
    }
}