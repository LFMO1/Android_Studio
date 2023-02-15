package com.example.crud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.crud.dao.ProdutoDao;
import com.example.crud.model.Produtos;

public class FormularioProdutos extends AppCompatActivity {

    EditText etNome, etDesc, etQtd;
    Button btnModificar;
    Produtos editarProduto, produtos;
    ProdutoDao produtoDao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_produtos);
        valores();

        produtos = new Produtos();
        produtoDao = new ProdutoDao(FormularioProdutos.this);
        Intent intent = getIntent();
        editarProduto = (Produtos) intent.getSerializableExtra("produto-escolhido");

        if(editarProduto != null){
            btnModificar.setText("modificar");

            etNome.setText(editarProduto.getProduto());
            etDesc.setText(editarProduto.getDescricao());
            etQtd.setText(editarProduto.getQuantidade()+"");
            produtos.setId(editarProduto.getId());
        }else{
            btnModificar.setText("cadastrar");
        }
        
        btnModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                produtos.setProduto(etNome.getText().toString());
                produtos.setQuantidade(Integer.parseInt(etQtd.getText().toString()));
                produtos.setDescricao(etDesc.getText().toString());

                if(btnModificar.getText().toString().equals("cadastrar")){
                    produtoDao.salvarProduto(produtos);
                    produtoDao.close();
                }else{
                    produtoDao.alterarProduto(produtos);
                    produtoDao.close();
                }
            }
        });

    }



    public void valores(){
        etNome = findViewById(R.id.etNome);
        etDesc = findViewById(R.id.etDescProd);
        etQtd = findViewById(R.id.etQtd);
        btnModificar = findViewById(R.id.btnModificar);
    }
}