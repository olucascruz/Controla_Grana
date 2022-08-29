package com.example.controleagrana.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.controleagrana.R;
import com.example.controleagrana.modal.ModalAddConta;
import com.example.controleagrana.modal.ModalCadastro;
import com.example.controleagrana.modal.ModalDelConta;
import com.example.controleagrana.modal.ModalRelatorio;
import com.example.controleagrana.usuarios.Usuario;

public class UsuarioActivity extends AppCompatActivity {

    private Usuario user;
    private TextView salario;
    private TextView despesas;
    private TextView restante;
    private float despesasValue;
    private float restanteValue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);
        user = MainActivity.getUsusarioSelecionado();
        Log.i("User", user.getNome());
        salario = findViewById(R.id.textSalario);

        salario.setText("Salario: R$" +Float.toString(user.getSalario()));

        Button btCadastrarConta = findViewById(R.id.opencadastroconta);
        btCadastrarConta.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                ModalAddConta modalCadastro = new ModalAddConta();
                modalCadastro.show(getSupportFragmentManager(), "myModal");
            };
        });

        Button btDeletarConta = findViewById(R.id.opendelconta);
        btDeletarConta.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                ModalDelConta modalDelete = new ModalDelConta();
                modalDelete.show(getSupportFragmentManager(), "myModal");
            };
        });

        Button btVerRelatorio = findViewById(R.id.openrelatorio);
        btVerRelatorio.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                ModalRelatorio modalRelatorio = new ModalRelatorio();
                modalRelatorio.show(getSupportFragmentManager(), "myModal");
            };
        });

        despesas = findViewById(R.id.textDespesas);
        restante = findViewById(R.id.textRestante);




        despesas.setText("Despesas: R$0");
        restante.setText("Restante: R$" + user.getSalario());
    }


    public void setDespesas(float d){
        despesasValue = d;
        despesas.setText("Despesas: R$"+ despesasValue);
    }

    public  void setRestante(float r){
        restanteValue = r;
        restante.setText("Restante: R$" + restanteValue);
    }

    public float getDespesasValue(){
        return despesasValue;
    }


    public Usuario getUser(){
        return user;
    }
}