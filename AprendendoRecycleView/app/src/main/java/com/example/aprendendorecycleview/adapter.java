package com.example.aprendendorecycleview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class adapter extends RecyclerView.Adapter<adapter.MyViewHoler> {

    private List<filme> filmeList;
    public adapter(List<filme> lista){
        filmeList = lista;
    }

    @NonNull
    @Override
    public MyViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) { //cria a view holder, conectando o layout ao codigo
        View itemLista = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter, parent, false);
        return new MyViewHoler(itemLista);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHoler holder, int position) { //fazendo a vizualização do holder
        filme filme = filmeList.get(position);

        holder.titulo.setText(filme.getTitulo());
        holder.ano.setText(filme.getAno());
        holder.genero.setText(filme.getGenero());

    }

    @Override
    public int getItemCount() {
        return filmeList.size();
    }

    public class MyViewHoler extends RecyclerView.ViewHolder {
        public MyViewHoler(@NonNull View itemView) {
            super(itemView);

            titulo  = itemView.findViewById(R.id.titulo);
            ano = itemView.findViewById(R.id.ano);
            genero = itemView.findViewById(R.id.genero);
        }



        TextView titulo;
        TextView ano;
        TextView genero;
    }
}
