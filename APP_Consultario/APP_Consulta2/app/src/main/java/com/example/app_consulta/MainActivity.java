package com.example.app_consulta;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private Button tela_cadastro;
    private EditText emailLog, senhaLog;

    private Button bt_log;

    String[] msgs={"Preencha todosos campo","Login Realizado"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        IniciarComponentes();
        tela_cadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, tela_cadastro.class);
                startActivity(intent);
            }
        });
        bt_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = emailLog.getText().toString();
                String senha = senhaLog.getText().toString();

                if(email.isEmpty() || senha.isEmpty()){
                    Snackbar snackbar = Snackbar.make(view,msgs[0],Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();
                }
                else{
                    Autenticar();
                }
            }
        });
    }

       private void Autenticar(){
           String email = emailLog.getText().toString();
           String senha = senhaLog.getText().toString();


           FirebaseAuth.getInstance().signInWithEmailAndPassword(email,senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
               @Override
               public void onComplete(@NonNull Task<AuthResult> task) {

                   if(task.isSuccessful()){
                       new Handler().postDelayed(new Runnable() {
                           @Override
                           public void run() {
                               TelaPrincipal();
                           }
                       }, 3000);
                   }
               }
           });
        }

        private void TelaPrincipal(){
            Intent intent = new Intent(MainActivity.this, TelaPrincipal.class);
            startActivity(intent);
        }
    private void IniciarComponentes()
    {
        tela_cadastro = findViewById(R.id.btnCadastro1);
        emailLog = findViewById(R.id.EmailLogin);
        senhaLog = findViewById(R.id.senhaLogin);
        bt_log = findViewById(R.id.btnLogin);
    }
}