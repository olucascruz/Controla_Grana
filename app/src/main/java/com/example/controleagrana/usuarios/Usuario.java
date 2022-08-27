package com.example.controleagrana.usuarios;
import com.example.controleagrana.contas.Conta;
import java.util.ArrayList;

public class Usuario {
    private String nome;
    private String cpf;
    private float salario;
    private ArrayList<Conta> contas;

    public Usuario(String _nome, String _cpf, float _salario){
        this.nome = _nome;
        this.cpf = _cpf;
        this.salario = _salario;

        contas = new ArrayList<Conta>();
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }
}
