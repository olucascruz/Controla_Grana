package com.example.controleagrana.contas;

import com.example.controleagrana.usuarios.Usuario;

import java.util.Date;

public class Conta {
    private int codigo;
    private float valor;
    private String descricao;
    private Date validade;
    private Usuario usuario;

    public Conta(int _codigo, float _valor, String _descricao, Date _validade, Usuario _usuario){
        this.codigo = _codigo;
        this.valor = _valor;
        this.descricao = _descricao;
        this.validade = _validade;
        this.usuario = _usuario;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getValidade() {
        return validade;
    }

    public void setValidade(Date validade) {
        this.validade = validade;
    }


}
