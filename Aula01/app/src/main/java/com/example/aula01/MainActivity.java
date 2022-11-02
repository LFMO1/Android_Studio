package com.example.aula01;


import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;



public class MainActivity extends AppCompatActivity {
    double  num1, num2, res;
    EditText numero1;
    EditText numero2;


    TextView tv_resultado;
    @Override
    protected void onCreate(Bundle savedInstanceState){ //ao criar a tela
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // ele chama esse arquivo. Que é o layout da aplicação
        //tudo do aplicativo tem uma referencia na classe R, ela é muito importante no android studio



        Button somar=(Button)findViewById(R.id.btn_somar);
        Button subtrair=(Button)findViewById(R.id.btn_subtrair);
        Button multiplicar=(Button)findViewById(R.id.btn_multiplicar);
        Button dividir=(Button)findViewById(R.id.btn_dividir);

        numero1=(EditText)findViewById(R.id.numero1);
        numero2=(EditText)findViewById(R.id.numero2);


        tv_resultado=(TextView)findViewById(R.id.tv_resultado);



        /*
        somar.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                    num1=Double.parseDouble(numero1.getText().toString()); //getText tranforma o valor em um valor editavel, dai você usa o toString para transfomar em um string e como o metodo parseDouble só aceita string essa é a maneira de se fazer isso
                    num2=Double.parseDouble(numero2.getText().toString());
                    res=num1+num2;
                    tv_resultado.setText(String.valueOf(res)); //transformando um double em uma string
            }
        });
        subtrair.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                num1=Double.parseDouble(numero1.getText().toString());
                num2=Double.parseDouble(numero2.getText().toString());
                res=num1-num2;
                tv_resultado.setText(String.valueOf(res));

            }
        });
        multiplicar.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                num1=Double.parseDouble(numero1.getText().toString());
                num2=Double.parseDouble(numero2.getText().toString());
                res=num1*num2;
                tv_resultado.setText(String.valueOf(res));
            }
        });
        dividir.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                num1=Double.parseDouble(numero1.getText().toString());
                num2=Double.parseDouble(numero2.getText().toString());
                res=num1/num2;
                tv_resultado.setText(String.valueOf(res));

            }
        });
        */







        /*
        //cast fazendo o java reconhecer que devolver realmente o tipo do atributo
        Button btn=(Button)findViewById(R.id.button); //fazendo uma variavel do tipo Button receber os valores do botão criado no layout
        TextView tv_nome=(TextView)findViewById(R.id.tv_nome);
        EditText et_nome=(EditText)findViewById(R.id.et_nome);
        //atributos recebendo os valores do layout
        //findViewById é o metoddo para localizar o id
        //Usando a classe o atributo id na classe R que tem refencia ao button;

        btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                tv_nome.setText(et_nome.getText()); //pegando o valor do et_nome e alocando no tv_nome
            }
        });*/
    }

    public void somar(View v){
        num1=Double.parseDouble(numero1.getText().toString()); //getText tranforma o valor em um valor editavel, dai você usa o toString para transfomar em um string e como o metodo parseDouble só aceita string essa é a maneira de se fazer isso
        num2=Double.parseDouble(numero2.getText().toString());
        res=num1+num2;
        tv_resultado.setText(String.valueOf(res)); //transformando um double em uma string
    }

    public void subtrair(View v){
        num1=Double.parseDouble(numero1.getText().toString());
        num2=Double.parseDouble(numero2.getText().toString());
        res=num1-num2;
        tv_resultado.setText(String.valueOf(res));
    }
    public void multiplicar(View v){
        num1=Double.parseDouble(numero1.getText().toString());
        num2=Double.parseDouble(numero2.getText().toString());
        res=num1*num2;
        tv_resultado.setText(String.valueOf(res));
    }
    public void dividir(View v){
        num1=Double.parseDouble(numero1.getText().toString());
        num2=Double.parseDouble(numero2.getText().toString());
        res=num1/num2;
        tv_resultado.setText(String.valueOf(res));
    }




}