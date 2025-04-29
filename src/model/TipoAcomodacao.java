package model;

import java.io.Serializable;

public class TipoAcomodacao implements Serializable{

    private static final long serialVersionUID = -8358558335L;

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    private final String nome;
    private double tarifaDiaria;
    private double adicionalAcompanhante;

    public TipoAcomodacao(String nome, double tarifaDiaria, double adicionalAcompanhante){
        if(tarifaDiaria <= 0){
            throw exception.Excecoes.tarifaDiaria;
        }
        if(adicionalAcompanhante < 0){
            throw exception.Excecoes.adcAcomp;
        }
        this.nome = nome;
        this.tarifaDiaria = tarifaDiaria;
        this.adicionalAcompanhante = adicionalAcompanhante;
    }

    public String getNome() {
        return this.nome;
    }
    
    public double getTarifaDiaria(){
        return this.tarifaDiaria;
    }

    public void setTarifaDiaria(double tarifaDiaria){
        if(tarifaDiaria <= 0){
            throw exception.Excecoes.tarifaDiaria;
        }
        this.tarifaDiaria = tarifaDiaria;
    }

    public double getAdicionalAcompanhante(){
        return this.adicionalAcompanhante;
    }

    public void setAdicionalAcompanhate(double adicionalAcompanhante){
        if(adicionalAcompanhante <= 0){
            throw exception.Excecoes.adcAcomp;
        }
        this.adicionalAcompanhante = adicionalAcompanhante;
    }

}
