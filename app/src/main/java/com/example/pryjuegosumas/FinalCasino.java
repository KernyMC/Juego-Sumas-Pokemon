package com.example.pryjuegosumas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class FinalCasino extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_casino);

        // Obtener el tiempo total desde el Intent
        long tiempoTotal = getIntent().getLongExtra("tiempoTotal", 0);

        // Mostrar el tiempo total en tv_tiempo
        TextView tvTiempo = findViewById(R.id.tv_tiempo);
        tvTiempo.setText(String.valueOf(tiempoTotal));

        Button btMenu = findViewById(R.id.bt_menu);
        btMenu.setOnClickListener(v -> {
            Intent intent = new Intent(FinalCasino.this, Menu.class);
            startActivity(intent);
        });
    }
}