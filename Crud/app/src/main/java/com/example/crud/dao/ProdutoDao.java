package com.example.crud.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.crud.model.Produtos;

import java.util.ArrayList;
import java.util.List;

public class ProdutoDao extends SQLiteOpenHelper {

    private static final String DATABASE = "dbProdutos";
    private static final int VERSION = 1;

    public ProdutoDao( Context context) {
        super(context, DATABASE, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String produtos = "CREATE TABLE produtos(" +
                " id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, \n" +
                "nomeproduto TEXT NOT NULL, \n" +
                " descricao TEXT NOT NULL, \n" +
                " quantidade INTEGER NOT NULL);";
        db.execSQL(produtos);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String produtos = "DROP TABLE IF EXISTS produtos";
        db.execSQL(produtos);
    }
    //salva dados
    public void salvarProduto(Produtos produtos){
        ContentValues values = new ContentValues();

        values.put("nomeproduto", produtos.getProduto());
        values.put("descricao", produtos.getDescricao());
        values.put("quantidade", produtos.getQuantidade());

        getWritableDatabase().insert("produtos", null, values);
    }

    public void alterarProduto(Produtos produtos){
        ContentValues values = new ContentValues();

        values.put("nomeproduto", produtos.getProduto());
        values.put("descricao", produtos.getDescricao());
        values.put("quantidade", produtos.getQuantidade());

        String[] argumentos = {String.valueOf(produtos.getId())};
        getWritableDatabase().update("produtos", values, "id=?", argumentos);
    }

    public void delete(Produtos produtos){
        String[] argumentos = {String.valueOf(produtos.getId())};
        getWritableDatabase().delete("produtos", "id=?", argumentos);
    }

    public List<Produtos> getLista(){
        String[] columns ={ "id", "nomeproduto", "descricao","quantidade"};
        Cursor cursor = getWritableDatabase().query("produtos", columns, null,null,null,null,null);
        List<Produtos> listaProdutos = new ArrayList<>();

        while (cursor.moveToNext()){
            Produtos produtos = new Produtos();
            produtos.setId(cursor.getLong(0));
            produtos.setProduto(cursor.getString(1));
            produtos.setDescricao(cursor.getString(1));
            produtos.setQuantidade(cursor.getInt(1));
            listaProdutos.add(produtos);
        }
        return listaProdutos;
    }
}
