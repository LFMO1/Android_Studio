package com.example.crud.model;

import com.example.crud.FormularioProdutos;

import java.io.Serializable;

public class Produtos implements Serializable {

    private Long id;
    private String produto;
    private String descricao;
    private int quantidade;


    public String toString(){
        return  produto.toString();
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }



}
