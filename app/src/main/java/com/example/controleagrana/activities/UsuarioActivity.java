package com.example.controleagrana.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.example.controleagrana.R;
import com.example.controleagrana.contas.Conta;
import com.example.controleagrana.modal.ModalAddConta;
import com.example.controleagrana.modal.ModalBuscarConta;
import com.example.controleagrana.modal.ModalRelatorio;
import com.example.controleagrana.usuarios.Usuario;

public class UsuarioActivity extends AppCompatActivity {

    private Usuario user;
    private Conta editConta = null;
    @SuppressWarnings("FieldCanBeLocal")
    private TextView salario;
    private TextView despesas;
    private TextView restante;
    private TextView aviso;
    private float despesasValue;
    private String DeleteOrEdit;
    private String CadOrEdit = "Cadastrar";


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);
        user = MainActivity.getUsusarioSelecionado();
        Log.i("User", user.getNome());
        salario = findViewById(R.id.textSalario);

        salario.setText("Salario: R$" + user.getSalario());

        Button btCadastrarConta = findViewById(R.id.opencadastroconta);
        btCadastrarConta.setOnClickListener(view -> openModalAddConta());

        Button btDeletarConta = findViewById(R.id.opendelconta);
        btDeletarConta.setOnClickListener(view -> {
            DeleteOrEdit = "Delete";
            ModalBuscarConta modalDelete = new ModalBuscarConta();
            modalDelete.show(getSupportFragmentManager(), "myModal");
        });

        Button btEditConta = findViewById(R.id.openeditconta);
        btEditConta.setOnClickListener(view -> {
            DeleteOrEdit = "Edit";
            ModalBuscarConta modalBuscar = new ModalBuscarConta();
            modalBuscar.show(getSupportFragmentManager(), "myModal");
        });

        Button btVerRelatorio = findViewById(R.id.openrelatorio);
        btVerRelatorio.setOnClickListener(view -> {
            ModalRelatorio modalRelatorio = new ModalRelatorio();
            modalRelatorio.show(getSupportFragmentManager(), "myModal");
        });

        despesas = findViewById(R.id.textDespesas);
        restante = findViewById(R.id.textRestante);
        aviso = findViewById(R.id.aviso);




        despesas.setText("Despesas: R$0");
        restante.setText("Restante: R$" + user.getSalario());
    }

    public void openModalAddConta(){
        ModalAddConta modalCadastro = new ModalAddConta();
        modalCadastro.show(getSupportFragmentManager(), "myModal");
    }

    @SuppressLint("SetTextI18n")
    public void setDespesas(float d){
        despesasValue = d;
        despesas.setText("Despesas: R$"+ d);
    }

    @SuppressLint("SetTextI18n")
    public void setRestante(float restanteValue){
        if(restanteValue < 0){
            restante.setText("Divida: R$" + restanteValue);
        }else{
            restante.setText("Restante: R$" + restanteValue);
        }
    }

    @SuppressLint("SetTextI18n")
    public void setAviso(Boolean b){
        if(b){
            aviso.setText("Você está endividado!");

        }else{
            aviso.setText("");
        }
    }

    public float getDespesasValue(){
        return despesasValue;
    }


    public Usuario getUser(){
        return user;
    }

    public String DeleteOrEdit(){
        return DeleteOrEdit;
    }

    public void setCadOrEdit(String Cadastro_or_Edit){
        CadOrEdit = Cadastro_or_Edit;
    }

    public String getCadOrEdit(){
        return CadOrEdit;
    }

    public void setEditUser(Conta selected){
        editConta = selected;
    }
    public Conta getEditUser(){
        return editConta;
    }
}