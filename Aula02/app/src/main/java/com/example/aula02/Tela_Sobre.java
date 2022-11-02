package com.example.aula02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Tela_Sobre extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_sobre);
        Intent it=getIntent();
        String nome = it.getStringExtra("p_nome");
        Double valor =it.getDoubleExtra("p_vtotal" ,0); //quando é relacionado a numero, ele pede um valor padrão
        TextView tv = findViewById(R.id.textView2);
        tv.setText(nome +"|"+valor); //quando eu clicar no sobre ele aparece-ra o nome da pessoa e o valor calculado
    }

    public void voltar(View v){
        //maneira correta de voltar a tela anterior
        this.finish(); //finaliza a tela

        //Intent it_voltar = new Intent(this, MainActivity.class);
        //startActivity(it_voltar);
    }
}