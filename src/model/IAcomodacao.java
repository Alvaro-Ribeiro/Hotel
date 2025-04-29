package model;

import model.Acomodacao.EEstadoOcupacao;

public interface IAcomodacao {

    public int getNumero();
    public int getOcupacaoMaxima();
    public void setEstadoOcupacao(EEstadoOcupacao e);
    public EEstadoOcupacao getEstadoOcupacao();
    public String getTipo();
    public double getTarifaDiaria();
    public double getAdicionalAcompanhante();
    
}
