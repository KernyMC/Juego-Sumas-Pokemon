package com.example.pryjuegosumas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class PantallaFinal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_final);

        // Obtener el tiempo total desde el Intent
        String tiempoTotal = getIntent().getStringExtra("Tiempo Final");

        // Mostrar el tiempo total en tv_tiempo
        TextView tvTiempo = findViewById(R.id.tv_tiempo);
        tvTiempo.setText(tiempoTotal);

        Button btMenu = findViewById(R.id.bt_menu);
        btMenu.setOnClickListener(v -> {
            Intent intent = new Intent(PantallaFinal.this, Menu.class);
            startActivity(intent);
        });
    }
}