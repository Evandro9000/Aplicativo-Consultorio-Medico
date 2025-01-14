package com.example.app_consulta;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class EncontreUmMedico extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_encontre_um_medico);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        CardView exit = findViewById(R.id.cardVoltar);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EncontreUmMedico.this, TelaPrincipal.class);
                startActivity(intent);
            }
        });

        CardView exit1 = findViewById(R.id.cardDentista);
        exit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EncontreUmMedico.this, DetalhesDoutor.class);
                intent.putExtra("title", "Dentista");
                startActivity(intent);
            }
        });

        CardView exit2 = findViewById(R.id.cardPsicologo);
        exit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EncontreUmMedico.this, DetalhesDoutor.class);
                intent.putExtra("title", "Psicologo");
                startActivity(intent);
            }
        });

        CardView exit3 = findViewById(R.id.cardCirugiao);
        exit3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EncontreUmMedico.this, DetalhesDoutor.class);
                intent.putExtra("title", "Cirurgião");
                startActivity(intent);
            }
        });

        CardView exit4 = findViewById(R.id.cardCardioLogista);
        exit4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EncontreUmMedico.this, DetalhesDoutor.class);
                intent.putExtra("title", "Cardiologista");
                startActivity(intent);
            }
        });

        CardView exit5 = findViewById(R.id.cardNutricionista);
        exit5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EncontreUmMedico.this, DetalhesDoutor.class);
                intent.putExtra("title", "Nutricionista");
                startActivity(intent);
            }
        });


    }
}