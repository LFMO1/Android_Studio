package com.example.aula02;


import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.app.AlertDialog;

public class MainActivity extends AppCompatActivity {

    double vtaca, vprato, vgarfo, vfaca, vtotal;
    EditText et_taca, et_prato, et_garfo, et_faca;
    CheckBox cb_taca, cb_prato, cb_garfo, cb_faca;
    Button btn_cacular;
    TextView tv_resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_taca=findViewById(R.id.et_tacas);
        et_prato=findViewById(R.id.et_pratos);
        et_garfo=findViewById(R.id.et_garfos);
        et_faca=findViewById(R.id.et_facas);

        cb_taca=findViewById(R.id.cb_tacas);
        cb_prato=findViewById(R.id.cb_pratos);
        cb_garfo=findViewById(R.id.cb_garfos);
        cb_faca=findViewById(R.id.cb_facas);

        btn_cacular=findViewById(R.id.btn_calcular);

        tv_resultado=findViewById(R.id.tv_resultado);

        vtaca=0.25;
        vprato=0.20;
        vgarfo=0.15;
        vfaca=0.15;
        vtotal=0.0;

        cb_taca.setText(cb_taca.getText().toString() +vtaca); //pegando o texto/valor.
        cb_prato.setText(cb_prato.getText().toString() +vprato); //assim mostrara o valor dos protudos após o tipo do produto no layout
        cb_garfo.setText(cb_garfo.getText().toString() +vgarfo);
        cb_faca.setText(cb_faca.getText().toString() +vfaca);

    }

    public void Calcular(View v){ // assim o botão consegue acessar o metodo
        vtotal=0.0;
        if(cb_taca.isChecked()){ //metodo para verificar se a checkbox esta marcada
            vtotal +=vtaca* Double.parseDouble(et_taca.getText().toString()); //transformando uma string em double
        }
        if(cb_prato.isChecked()){
            vtotal +=vprato* Double.parseDouble(et_prato.getText().toString());
        }
        if(cb_garfo.isChecked()){
            vtotal +=vgarfo* Double.parseDouble(et_garfo.getText().toString());
        }
        if(cb_faca.isChecked()){
            vtotal +=vfaca* Double.parseDouble(et_faca.getText().toString());
        }
        tv_resultado.setText("Valor total R$: "+vtotal); //setText muda o valor do TextView
        Toast.makeText(this, "Valor da locação calculado", Toast.LENGTH_SHORT).show(); //this este toast, toast.length_short tempo mostrado e show metodo que mostra o thoast
        AlertDialog.Builder cxMsg = new AlertDialog.Builder(this);
        cxMsg.setNeutralButton("OK", null);
        cxMsg.setMessage("Valor da locação calculado"); //menssagem a ser mostrada
        cxMsg.show();
    }

    public void abrirTelaSobre(View v){
        Intent it_telaSobre= new Intent(this, Tela_Sobre.class); //instanciando um objeto, criando uma inteção
        it_telaSobre.putExtra("p_nome","Luís"); // criando parametros no intent. Dando um nome para aparecer na tela sobre
        it_telaSobre.putExtra("p_vtotal", vtotal); //usando o total que deu na conta
        startActivity(it_telaSobre); //chamando a intenção
        setContentView(R.layout.activity_tela_sobre); //troca o arquivo xml que esta associado ao mainActiviy
        //com o Intent você abre uma nova janela, assim, você vai escalando o na aplicação. Ja com o startActivity você apenas muda o layout
    }


}