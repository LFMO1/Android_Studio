package com.example.projetocadastro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class CadastroActivity extends AppCompatActivity {

    EditText etNome;
    EditText etEmail;
    EditText etSenha;
    Button btnConcluir;
    String[] mensagens = {"preencha todos os campos", "cadastro realizado com sucesso"};
    String usuarioID;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        getSupportActionBar().hide();
        valores();

        btnConcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nome = etNome.getText().toString();
                String email = etEmail.getText().toString();
                String senha = etSenha.getText().toString();

                if(nome.isEmpty() || email.isEmpty() || senha.isEmpty()){
                    Snackbar snackbar = Snackbar.make(view, mensagens[0], Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();
                }else{
                    cadastroConcluido(view);
                }

                /*if(etNome == null || etEmail == null || etSenha == null){
                    AlertDialog.Builder alert = new AlertDialog.Builder(CadastroActivity.this);
                    alert.setMessage("cadastre todos os campos2");
                    alert.show();
                }*/
            }
        });
    }

    private void cadastroConcluido(View view){
        String email = etEmail.getText().toString();
        String senha = etSenha.getText().toString();

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) { //responsavel pelo cadastro e autenticação dos usuarios. Nesse caso ele tem a função de cadastro
                if(task.isSuccessful()){
                    salvarDadosUsuarios();

                    Snackbar snackbar = Snackbar.make(view, mensagens[1], Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();
                }else{
                    String erro;
                    try {
                        throw task.getException();
                    }catch (FirebaseAuthWeakPasswordException e){ //excessão se o usuario digitar uma senha com poucos caracteres
                        erro = "Digite uma senha com no minimo 6 caracteres";
                    }catch (FirebaseAuthUserCollisionException e){ //excessão que trata emails iguais/repetidos
                        erro = "Esse email ja esta cadastrado";
                    }catch (FirebaseAuthInvalidCredentialsException e){ //
                        erro = "Email invalido";
                    }catch (Exception e){
                        erro = "Erro ao cadastrar";
                    }
                    Snackbar snackbar = Snackbar.make(view, erro, Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();
                }
            }
        });
    }

    public void salvarDadosUsuarios(){
        String nome = etNome.getText().toString();
        FirebaseFirestore db = FirebaseFirestore.getInstance(); // receeu a instancia do banco de dados

        Map<String,Object> usuarios = new HashMap<>();
        usuarios.put("nome", nome);

        usuarioID = FirebaseAuth.getInstance().getCurrentUser().getUid();

        DocumentReference documentReference = db.collection("Usuarios").document(usuarioID);
        documentReference.set(usuarios).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Log.d("db","Sucesso ao salvar os dados");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("db_error", "Erro ao salvar os dados " + e.toString());
            }
        });
    }


    public void valores(){
        etNome = findViewById(R.id.etNomeCadastro);
        etEmail = findViewById(R.id.etEmailCadastro);
        etSenha = findViewById(R.id.etSenhaCadastro);
        btnConcluir = findViewById(R.id.btnConcluirCadastro);
    }
}