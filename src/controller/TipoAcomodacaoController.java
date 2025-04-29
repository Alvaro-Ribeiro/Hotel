package controller;

import java.io.Serializable;
import java.util.TreeMap;

import model.TipoAcomodacao;

public class TipoAcomodacaoController implements Serializable{

    private static final long serialVersionUID = -1241633884L;
    
	private TreeMap<String, TipoAcomodacao> tipoAcomodacoes = new TreeMap<>();

    public void addTipoAcomodacao(String nome, double tarifaDiaria, double adicionalAcompanhante) throws IllegalArgumentException {

        if(procuraTipoAcomodacao(nome) != null) {
        	throw exception.Excecoes.tipoAcomodJaExiste;
        }

        TipoAcomodacao tipoAcomodacao = new TipoAcomodacao(nome, tarifaDiaria, adicionalAcompanhante);
        tipoAcomodacoes.put(tipoAcomodacao.getNome(), tipoAcomodacao);

        MainController.save();
    }

    public TipoAcomodacao procuraTipoAcomodacao(String nome){
        return tipoAcomodacoes.get(nome);
    }

    public String[] listaTipoAcomodacoes(){
        int tam = tipoAcomodacoes.size();
        String[] vetor = new String[tam];
        int i = 0;
        for (TipoAcomodacao t : tipoAcomodacoes.values()){
            vetor[i] = t.getNome();
            i++;
        }
        return vetor;
    }

    public void alteraTipoAcomodacao(String nome, double tarifa, double taxa) throws IllegalArgumentException{

        TipoAcomodacao tipo = procuraTipoAcomodacao(nome);

        tipo.setTarifaDiaria(tarifa);

        tipo.setAdicionalAcompanhate(taxa);

    }

}
