package com.example.crud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.crud.dao.ProdutoDao;
import com.example.crud.model.Produtos;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button btnCadastrar;
    ListView lista;
    ProdutoDao produtosDao;
    List<Produtos> listview_produtos = new ArrayList<>();
    Produtos produtos;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        lista = (ListView) findViewById(R.id.ListProdutos);
        registerForContextMenu(lista);

        btnCadastrar = findViewById(R.id.btnCadastrar);
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FormularioProdutos.class);
                startActivity(intent);
            }
        });

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Produtos produtosEscolhidos = (Produtos) adapterView.getItemAtPosition(i);
                Intent intent = new Intent(MainActivity.this, FormularioProdutos.class);
                intent.putExtra("produtoEscolhido", produtosEscolhidos);

            }
        });

        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
               produtos = (Produtos) adapterView.getItemAtPosition(i);
                return false;
            }
        });

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem menuDelete = menu.add("Deletar este produto");
        menuDelete.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                produtosDao = new ProdutoDao(MainActivity.this);
                produtosDao.delete(produtos);
                produtosDao.close();
                carregarProduto();
                return true;
            }
        });
    }

    protected void onResume(){
        super.onResume();
        carregarProduto();
    }

    public void carregarProduto(){
        produtosDao = new ProdutoDao(MainActivity.this);
        listview_produtos = produtosDao.getLista();
        produtosDao.close();

        if(listview_produtos != null){
            adapter = new ArrayAdapter<Produtos>(MainActivity.this, android.R.layout.simple_list_item_1, listview_produtos);
            lista.setAdapter(adapter);
        }
    }
}