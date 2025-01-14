package com.example.app_consulta;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.HashMap;

public class DetalhesDoutor extends AppCompatActivity {
    private String[][] detalhes_doutores1 =
            {
                    {"Marinho", "Hospital: Santa Mônica", "01", "Telefone: 190", "600"},
                    {"Gal Costa", "Hospital: São Marcos", "02", "Telefone: 190", "900"},
                    {"Gilberto Gil", "Hospital: CDHU", "03", "Telefone: 190", "300"},
                    {"Evandro", "Hospital: Mario Gatti", "04", "Telefone: 190", "500"},
                    {"Caetano Veloso", "Hospital: Unisal", "05", "Telefone: 190", "800"}
            };

    private String[][] detalhes_doutores2 =
            {
                    {"Biel", "Hospital: Oziel", "06", "Telefone: 190", "600"},
                    {"Tizzei", "Hospital: Aeroporto", "07", "Telefone: 190", "900"},
                    {"Adriana", "Hospital: Albert einstein", "08", "Telefone: 190", "3000"},
                    {"Djavan", "Hospital: Complexo da Penha", "09", "Telefone: 190", "500"},
                    {"Donald Trump", "Hospital: Afeganistão", "10", "Telefone: 190", "800"}
            };

    private String[][] detalhes_doutores3 =
            {
                    {"Alcione", "Hospital: Taquaral", "11", "Telefone: 190", "600"},
                    {"Justin Timberlake", "Hospital: São Paulo", "12", "Telefone: 190", "900"},
                    {"Luan Gameplays", "Hospital: São Marcos", "13", "Telefone: 190", "300"},
                    {"Michael Jackson", "Hospital: Mario Gatti", "14", "Telefone: 190", "500"},
                    {"André", "Hospital: CDHU", "15", "Telefone: 190", "800"}
            };

    private String[][] detalhes_doutores4 =
            {
                    {"MC Daleste", "Hospital: Unisal", "16", "Telefone: 190", "600"},
                    {"Carlos", "Hospital: Complexo do Alemão", "17", "Telefone: 190", "900"},
                    {"Luan Santana", "Hospital: Unicamp", "18", "Telefone: 190", "300"},
                    {"Elis Regina", "Hospital: USP", "19", "Telefone: 190", "500"},
                    {"Wilson Simonal", "Hospital: PUC", "20", "Telefone: 190", "800"}
            };

    private String[][] detalhes_doutores5 =
            {
                    {"Martin Luther King", "Hospital: Pimpri", "21", "Telefone: 190", "600"},
                    {"Mike Tyson", "Hospital: PUC", "22", "Telefone: 190", "900"},
                    {"Marília Mendonça", "Hospital: Unicamp", "23", "Telefone: 190", "300"},
                    {"Malcom X", "Hospital: USP", "24", "Telefone: 190", "500"},
                    {"Ayrton Senna", "Hospital: Unicamp", "25", "Telefone: 190", "800"}
            };
    TextView tv;
    String[][] detalhes_doutores = {};
    HashMap<String, String> item;
    ArrayList list;
    SimpleAdapter sa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detalhes_doutor);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tv = findViewById(R.id.textViewDDTitle);
        Intent it = getIntent();
        String title = it.getStringExtra("title");
        tv.setText(title);

        if (title.compareTo("Dentista") == 0)
            detalhes_doutores = detalhes_doutores1;
        else if (title.compareTo("Psicologo") == 0)
            detalhes_doutores = detalhes_doutores2;
        else if (title.compareTo("Cirurgião") == 0)
            detalhes_doutores = detalhes_doutores3;
        else if (title.compareTo("Cardiologista") == 0)
            detalhes_doutores = detalhes_doutores4;
        else
            detalhes_doutores = detalhes_doutores5;


        Button button = findViewById(R.id.buttonBack);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(DetalhesDoutor.this, EncontreUmMedico.class);
                startActivity(intent);
            }
        });

        list = new ArrayList();
        for (int i = 0; i < detalhes_doutores.length; i++) {
            item = new HashMap<String, String>();
            item.put("linha1", detalhes_doutores[i][0]);
            item.put("linha2", detalhes_doutores[i][1]);
            item.put("linha3", detalhes_doutores[i][2]);
            item.put("linha4", detalhes_doutores[i][3]);
            item.put("linha5", "Preço da Consulta: " + detalhes_doutores[i][4] + " R$");
            list.add(item);
        }
        sa = new SimpleAdapter(this, list,
                R.layout.multi_linhas,
                new String[]{"linha1", "linha2", "linha3", "linha4", "linha5"},
                new int[]{R.id.linha_a, R.id.linha_b, R.id.linha_c, R.id.linha_d, R.id.linha_e}
        );
        ListView lst = findViewById(R.id.listView);
        lst.setAdapter(sa);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it = new Intent(DetalhesDoutor.this, Agendamento.class);
                it.putExtra("text1", title);
                it.putExtra("text2", detalhes_doutores[i][0]);
                it.putExtra("text3", detalhes_doutores[i][1]);
                it.putExtra("text4", detalhes_doutores[i][2]);
                it.putExtra("text5", detalhes_doutores[i][4]);
                startActivity(it);
            }
        });

    }
}