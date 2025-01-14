package com.example.app_consulta;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Medicamentos extends AppCompatActivity {

    private double totalCompra = 0.0; // Variável para armazenar o total da compra
    private TextView textViewTotal; // Referência para o TextView do total

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicamentos);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inicializando o TextView do total
        textViewTotal = findViewById(R.id.textView_title);
        atualizarTotalCompra();

        ListView listView = findViewById(R.id.listView);
        String[] items = {
                "Dipirona Sódica",
                "Paracetamol",
                "Amoxicilina",
                "Ibuprofeno",
                "Omeprazol",
                "Simeticona",
                "Losartana",
                "Dorflex",
                "AAS Infantil",
                "Neosoro"
        };

        String[] details = {
                "Analgésico e antitérmico usado para febre e dor.",
                "Analgésico e antitérmico, indicado para dores leves.",
                "Antibiótico utilizado em infecções bacterianas.",
                "Anti-inflamatório indicado para febre e dores musculares.",
                "Reduz acidez estomacal, indicado para gastrite e refluxo.",
                "Reduz gases e desconfortos abdominais.",
                "Antihipertensivo usado para controle da pressão arterial.",
                "Analgésico combinado usado para dores musculares.",
                "Antiplaquetário usado para prevenção de tromboses.",
                "Descongestionante nasal para alívio de coriza."
        };

        String[] prices = {
                "5.00",
                "4.00",
                "15.00",
                "10.00",
                "12.00",
                "8.00",
                "20.00",
                "7.00",
                "3.00",
                "6.00"
        };


        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(Medicamentos.this, Medicamento_Detalhes.class);
            intent.putExtra("text1", items[position]);
            intent.putExtra("text2", details[position]);
            intent.putExtra("text3", prices[position]);
            startActivityForResult(intent, 1); // Inicia a atividade para resultado
        });

        Button button = findViewById(R.id.buttonBack);
        button.setOnClickListener(v -> {
            Intent intent = new Intent(Medicamentos.this, TelaPrincipal.class);
            startActivity(intent);
        });

        Button button2 = findViewById(R.id.buttonGoToCart);
        button2.setOnClickListener(v -> {
            // Criar a Intent para ir para a tela Carrinho
            Intent intent = new Intent(Medicamentos.this, Carrinho.class);

            // Passar o preço total como extra para a Intent
            intent.putExtra("precoTotal", totalCompra);  // Enviando o totalCompra

            // Iniciar a tela Carrinho
            startActivity(intent);
        });
    }

    // Método chamado ao retornar da atividade Medicamento_Detalhes
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            double preco = data.getDoubleExtra("precoProduto", 0.0);
            totalCompra += preco;
            atualizarTotalCompra();
        }
        // Verifique se existe um valor de totalCompra que foi passado ao retornar de Carrinho
        if (data != null && data.hasExtra("precoTotal")) {
            totalCompra = data.getDoubleExtra("precoTotal", 0.0);
            atualizarTotalCompra();
        }
    }

    // Atualiza o total no TextView
    private void atualizarTotalCompra() {
        textViewTotal.setText("Total (R$): " + String.format("%.2f", totalCompra));
    }
}
