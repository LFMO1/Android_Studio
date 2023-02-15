package com.example.aprendendorecycleview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rv;
    private List<filme> listaFilmes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv = findViewById(R.id.recycle);

        //lista filmes
        criarFilmes();

        //configurar adapter
        adapter adapter = new adapter(listaFilmes);

        //configurar o recycler view
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        rv.setLayoutManager(layoutManager);
        rv.setHasFixedSize(true); //fazer tamanho fixo
        rv.setAdapter(adapter);

    }


    public void criarFilmes(){
        filme filme = new filme("titulo", "genero", "ano");
        listaFilmes.add(filme);//adicionando filmes a uma lista
    }
}