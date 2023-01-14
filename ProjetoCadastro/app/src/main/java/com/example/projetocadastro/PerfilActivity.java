package com.example.projetocadastro;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class PerfilActivity extends AppCompatActivity {

    private TextView txtNome, txtEmail;
    private Button btnDeslogar;
    String usuarioID;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        getSupportActionBar().hide();
        valores();

        btnDeslogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseAuth.getInstance().signOut(); //sair da sessão (deslogar)
                Intent intent = new Intent(PerfilActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void onStart() { //ciclo de vida da activity
        super.onStart();

        usuarioID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        String email = FirebaseAuth.getInstance().getCurrentUser().getEmail();

        DocumentReference documentReference = db.collection("Usuarios").document(usuarioID);
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                if(documentSnapshot != null){
                    txtNome.setText(documentSnapshot.getString("nome")); //pegando a chave que foi criada no "cadastroActivty"
                    txtEmail.setText(email);
                }
            }
        });
    }

    public void valores(){
        txtNome = findViewById(R.id.txtUser);
        txtEmail = findViewById(R.id.txtEmailPerfil);
        btnDeslogar = findViewById(R.id.btnDeslogar);
    }
}