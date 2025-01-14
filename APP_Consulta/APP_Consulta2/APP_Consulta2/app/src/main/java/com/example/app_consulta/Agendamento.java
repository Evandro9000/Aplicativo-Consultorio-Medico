package com.example.app_consulta;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Agendamento extends AppCompatActivity {

    EditText ed1, ed2, ed3, ed4;
    TextView tv;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    private Button dateButton, timeButton;

    FirebaseFirestore db;

    // Variáveis para armazenar data e horário
    private String data = "";
    private String horario = "";
    private String nome_doutor = "";
    private String cod_doutor = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_agendamento);

        // Inicializando o Firestore aqui
        db = FirebaseFirestore.getInstance();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Associando as variáveis aos componentes de layout
        tv = findViewById(R.id.textViewAppTitle);
        ed1 = findViewById(R.id.editTextAppFullName);
        ed2 = findViewById(R.id.editTextAppAddress);
        ed3 = findViewById(R.id.editTextAppContactNumber);
        ed4 = findViewById(R.id.editTextAppFees);
        dateButton = findViewById(R.id.buttonData);
        timeButton = findViewById(R.id.buttonHorario);

        // Tornando os campos somente leitura
        ed1.setKeyListener(null);
        ed2.setKeyListener(null);
        ed3.setKeyListener(null);
        ed4.setKeyListener(null);

        // Recuperando dados da Intent
        Intent it = getIntent();
        String title = it.getStringExtra("text1");
        String fullname = it.getStringExtra("text2");
        String address = it.getStringExtra("text3");
        String contact = it.getStringExtra("text4");
        String fees = it.getStringExtra("text5");

        // Populando os campos com os dados recebidos
        tv.setText(title);
        ed1.setText("Doutor(a): "+fullname);
        ed2.setText(address);
        ed3.setText("Código - Doutor: "+contact);
        ed4.setText("Preço: " + fees + " R$");

        // Inicializando os DatePicker e TimePicker
        initDatePicker();
        dateButton.setOnClickListener(view -> datePickerDialog.show());

        initTimePicker();
        timeButton.setOnClickListener(view -> timePickerDialog.show());

        nome_doutor = it.getStringExtra("text2");
        cod_doutor = it.getStringExtra("text4");

        Button button = findViewById(R.id.buttonRegistrar);
        button.setOnClickListener(v -> {
            // Antes de registrar o agendamento, verifica se já existe um agendamento no mesmo horário
            checkAgendamentoDisponivel();
        });
    }

    private void checkAgendamentoDisponivel() {
        // Consultando se já existe um agendamento para o mesmo doutor e horário
        db.collection("agenda")
                .whereEqualTo("Nome_Doutor", nome_doutor)
                .whereEqualTo("Horario", horario)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful() && !task.getResult().isEmpty()) {
                        // Se já existe um agendamento com o mesmo doutor e horário
                        Toast.makeText(Agendamento.this, "Já existe um agendamento nesse horário", Toast.LENGTH_SHORT).show();
                    } else {
                        // Caso contrário, cria o agendamento
                        registrarAgendamento();
                    }
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(Agendamento.this, "Erro ao verificar agendamento.", Toast.LENGTH_SHORT).show();
                });
    }

    private void registrarAgendamento() {
        // Criando o mapa de dados
        Map<String, Object> agendaMap = new HashMap<>();
        agendaMap.put("Nome_Doutor", nome_doutor);
        agendaMap.put("Horario", horario);
        agendaMap.put("Data", data);
        agendaMap.put("Cod_Doutor", cod_doutor);

        // Enviando os dados para o Firestore
        db.collection("agenda").document(nome_doutor + "_" + horario)
                .set(agendaMap)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(Agendamento.this, "Consulta agendada com sucesso!", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(Agendamento.this, "Falha ao agendar consulta.", Toast.LENGTH_SHORT).show();
                });
    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = (datePicker, year, month, day) -> {
            month = month + 1; // Ajuste do mês (0-indexado)
            data = day + "/" + month + "/" + year; // Atualiza a variável "data"
            dateButton.setText(data);
        };

        Calendar cal = Calendar.getInstance();
        int ano = cal.get(Calendar.YEAR);
        int mes = cal.get(Calendar.MONTH);
        int dia = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_DARK;
        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, ano, mes, dia);
        datePickerDialog.getDatePicker().setMinDate(cal.getTimeInMillis() + 86400000);
    }

    private void initTimePicker() {
        TimePickerDialog.OnTimeSetListener timeSetListener = (timePicker, hourOfDay, minute) -> {
            // Ajustar minutos para o intervalo de 30 minutos
            int adjustedMinute = (minute < 30) ? 0 : 30;

            // Atualiza a variável "horario"
            horario = String.format("%02d:%02d", hourOfDay, adjustedMinute);
            timeButton.setText(horario);
        };

        Calendar cal = Calendar.getInstance();
        int hrs = cal.get(Calendar.HOUR_OF_DAY);
        int mins = cal.get(Calendar.MINUTE);

        int style = AlertDialog.THEME_HOLO_DARK;
        timePickerDialog = new TimePickerDialog(this, style, timeSetListener, hrs, mins, true);
    }
}
