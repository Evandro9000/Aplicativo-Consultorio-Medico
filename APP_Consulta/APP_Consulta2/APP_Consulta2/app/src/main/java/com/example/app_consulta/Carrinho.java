package com.example.app_consulta;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Carrinho extends AppCompatActivity {

    private TextView textViewPrecoTotal;
    private Button buttonCancelarCompra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrinho);

        // Referências para os elementos da tela
        textViewPrecoTotal = findViewById(R.id.textView_precoTotal);
        buttonCancelarCompra = findViewById(R.id.buttonCancelarCompra);

        // Obtendo o valor total da compra passado pela Intent
        double precoTotal = getIntent().getDoubleExtra("precoTotal", 0.0);
        textViewPrecoTotal.setText("Preço Total: R$ " + String.format("%.2f", precoTotal));

        // Ação para o botão Cancelar Compra
        buttonCancelarCompra.setOnClickListener(v -> {
            // Zera o valor total de compra na tela de medicamentos
            Intent intent = new Intent(Carrinho.this, Medicamentos.class);
            // Envia o valor zerado para a tela de medicamentos
            intent.putExtra("precoTotal", 0.0);
            startActivity(intent);  // Inicia a tela Medicamentos
            finish();  // Finaliza a tela Carrinho
        });

        Button button = findViewById(R.id.buttonBoleto);
        button.setOnClickListener(v -> {
            Intent intent = new Intent(Carrinho.this, Boleto.class);
            startActivity(intent);
        });

        Button button2 = findViewById(R.id.buttonPix);
        button2.setOnClickListener(v -> {
            Intent intent = new Intent(Carrinho.this, Pix.class);
            startActivity(intent);
        });
    }
}
