package model;

import java.io.Serializable;
import util.Util;

public class Hospede implements IHospede, Serializable {
    
    private static final long serialVersionUID = -9938019161L;
    
    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    private final long cpf;
    private String nome;
    private String email;
    private long telefone;

    public Hospede(long cpf, String nome, long telefone){

        if(Util.validaNome(nome) == false){
            throw exception.Excecoes.nomeInv;
        }
        this.nome = nome;
        this.cpf = cpf; // Validado apenas no Controller por conta da transformação de string pra long
        this.telefone = telefone; // Validado apenas no Controller por conta da transformação de string pra long
    }

    public Hospede(long cpf, String nome, long telefone, String email){
        this(cpf, nome, telefone);

        if(Util.validaEmail(email) == false){
            throw exception.Excecoes.emailInv;
        }

        this.email = email;
    }

    @Override
    public long getCpf() {
        return this.cpf;
    }

    @Override
    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome){
        if(Util.validaNome(nome) == false){
            throw exception.Excecoes.nomeInv;
        }
        this.nome = nome;
    }

    @Override
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    @Override
    public long getTelefone() {
        return this.telefone;
    }

    public void setTelefone(long telefone){
        this.telefone = telefone;
    }    
    
}
