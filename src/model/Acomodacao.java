package model;

import java.io.Serializable;

public class Acomodacao implements IAcomodacao, Serializable {
    
    private static final long serialVersionUID = -9488945461L;

    public enum EEstadoOcupacao {

        OCUPADO("Ocupado"),
        DISPONIVEL("Disponível"),
        MANUTENCAO("Manutenção");

        private final String descricao;

        EEstadoOcupacao(String descricao){
            this.descricao = descricao;
        }

        public String getDescricao(){
            return this.descricao;
        }
    }
    
    private final int numero;
    private final int ocupacaoMax;
    private EEstadoOcupacao estadoOcupacao;

    private final TipoAcomodacao tipo;

    public Acomodacao(int numero, int ocupacaoMax, TipoAcomodacao tipo, EEstadoOcupacao estado) {
    	if(numero <= 0) {
    		throw exception.Excecoes.numInv;
    	}
        this.numero = numero;
        if(ocupacaoMax <= 0){
            throw exception.Excecoes.ocupMaxInvalida;
        }
        this.ocupacaoMax = ocupacaoMax;
        this.tipo = tipo;
        this.estadoOcupacao = estado;
    }

    @Override
    public int getNumero() {
        return this.numero;
    }

    @Override
    public int getOcupacaoMaxima() {
        return this.ocupacaoMax;
    }

    @Override
    public void setEstadoOcupacao(EEstadoOcupacao e) {
        this.estadoOcupacao = e;
    }

    @Override
    public EEstadoOcupacao getEstadoOcupacao() {
        return this.estadoOcupacao;
    }

    @Override
    public String getTipo() {
        return tipo.getNome();
    }

    @Override
    public double getTarifaDiaria() {
        return tipo.getTarifaDiaria();
    }

    @Override
    public double getAdicionalAcompanhante() {
        return tipo.getAdicionalAcompanhante();
    }
    
}
