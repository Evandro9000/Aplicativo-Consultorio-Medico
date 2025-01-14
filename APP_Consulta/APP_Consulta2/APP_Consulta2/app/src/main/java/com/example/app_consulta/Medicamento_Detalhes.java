package com.example.app_consulta;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Medicamento_Detalhes extends AppCompatActivity {

    TextView tvPackageName, tvTotalCost;
    EditText edDetails;
    NumberPicker numberPickerQuantidade;
    Button btnVoltar, btnAdd;
    double precoUnitario; // Armazena o preço unitário do medicamento

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicamento_detalhes);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvPackageName = findViewById(R.id.textViewBMDPackageName);
        edDetails = findViewById(R.id.editTextTextBMDMultiline);
        tvTotalCost = findViewById(R.id.textViewBMDTotalCost);
        btnVoltar = findViewById(R.id.buttonBMDVoltarCarrinho);
        btnAdd = findViewById(R.id.buttonBMDAddCarrinho);
        numberPickerQuantidade = findViewById(R.id.numberPickerQuantidade);

        edDetails.setKeyListener(null);

        // Configurando o NumberPicker para selecionar quantidades entre 1 e 20
        numberPickerQuantidade.setMinValue(1);
        numberPickerQuantidade.setMaxValue(20);

        Intent intent = getIntent();
        tvPackageName.setText(intent.getStringExtra("text1"));
        edDetails.setText(intent.getStringExtra("text2"));

        // Obtendo o preço unitário e exibindo o custo inicial
        precoUnitario = Double.parseDouble(intent.getStringExtra("text3"));
        atualizarCustoTotal(numberPickerQuantidade.getValue());

        // Listener para atualizar o preço total ao mudar a quantidade
        numberPickerQuantidade.setOnValueChangedListener((picker, oldVal, newVal) -> atualizarCustoTotal(newVal));

        btnVoltar.setOnClickListener(view -> finish());

        btnAdd.setOnClickListener(view -> {
            int quantidade = numberPickerQuantidade.getValue();
            double total = precoUnitario * quantidade;

            // Retorna o preço total para a atividade Medicamentos
            Intent resultIntent = new Intent();
            resultIntent.putExtra("precoProduto", total);
            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }

    // Atualiza o custo total no TextView com base na quantidade
    private void atualizarCustoTotal(int quantidade) {
        double total = precoUnitario * quantidade;
        tvTotalCost.setText("Custo Total: " + String.format("%.2f", total) + "/-");
    }
}
