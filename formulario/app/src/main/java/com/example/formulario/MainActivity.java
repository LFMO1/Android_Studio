package com.example.formulario;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText preco,hora,cep,data, cpf, telefone, resultado;
    //mascara telefone
    String ultimoCaracterDigitado = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preco=findViewById(R.id.et_preco);
        hora=findViewById(R.id.et_hora);
        cep=findViewById(R.id.et_cep);
        data=findViewById(R.id.et_data);
        cpf=findViewById(R.id.et_cpf);
        telefone=findViewById(R.id.et_telefone);
        resultado=findViewById(R.id.et_resultado);



        telefone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Integer digitos =telefone.getText().toString().length();
                if(digitos>1){
                    ultimoCaracterDigitado = telefone.getText().toString().substring(digitos-1);
                }
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Integer digitos =telefone.getText().toString().length();
                if(digitos==2){
                    if(!ultimoCaracterDigitado.equals(" ")){
                        telefone.append(" ");
                    }else{
                        telefone.getText().delete(digitos-1, digitos);
                    }
                }else if(digitos==8){
                    if(!ultimoCaracterDigitado.equals("-")){
                        telefone.append(" ");
                    }else{
                        telefone.getText().delete(digitos-1, digitos);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    public void calcular(){

    }
}