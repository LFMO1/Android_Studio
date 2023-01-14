package com.example.projetocadastro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private TextView txtTelaCadastro;
    private EditText etEmail, etSenha;
    private Button btnEntrar;
    private ProgressBar progressBar;

    String[] mensagens = {"Preencha todos os campos", "Login realizado com sucesso"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        iniciarComponentes();


        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = etEmail.getText().toString();
                String senha = etSenha.getText().toString();

                if(email.isEmpty() || senha.isEmpty()){
                    Snackbar snackbar = Snackbar.make(view, mensagens[0], Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();
                }else{
                    autenticarUsuario(view);
                }
            }
        });





        txtTelaCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,CadastroActivity.class );
                startActivity(intent);
            }
        });


    }


    public void autenticarUsuario(View view){
        String email = etEmail.getText().toString();
        String senha = etSenha.getText().toString();

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    progressBar.setVisibility(View.VISIBLE);

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(LoginActivity.this, PerfilActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }, 3000);
                }else{
                    String erro;
                    try {
                        throw task.getException();
                    }catch(Exception e){
                        erro = "Não foi possivel fazer login, tente novamente";
                    }
                    Snackbar snackbar = Snackbar.make(view, erro, Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();
                }
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser usuarioAtual = FirebaseAuth.getInstance().getCurrentUser(); //assim faz uma variavel receber o usuario atual
        
        if(usuarioAtual != null){
            Intent intent = new Intent(LoginActivity.this, PerfilActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public void iniciarComponentes(){
        txtTelaCadastro = findViewById(R.id.txtTelaCadastro);
        etEmail = findViewById(R.id.etEmail);
        etSenha = findViewById(R.id.etSenha);
        btnEntrar = findViewById(R.id.btnEntrar);
        progressBar = findViewById(R.id.progressBar);

    }
}