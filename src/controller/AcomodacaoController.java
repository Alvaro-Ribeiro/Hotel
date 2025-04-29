package controller;

import java.io.Serializable;
import java.util.TreeMap;

import model.Acomodacao;
import model.TipoAcomodacao;
import model.Acomodacao.EEstadoOcupacao;

public class AcomodacaoController implements Serializable{

    private static final long serialVersionUID = -57065014L;
    
	private TreeMap<Integer, Acomodacao> acomodacoes = new TreeMap<>();

    public void addAcomodacao(int numero, int ocupacaoMax, String nomeTipoAcomodacao, EEstadoOcupacao estado) throws IllegalArgumentException{

        if(procuraNumeroAcomodacao(numero) != null){
            throw exception.Excecoes.numJaExiste;
        }

        if(ocupacaoMax <= 0){
            throw exception.Excecoes.ocupMaxInvalida;
        }
        TipoAcomodacaoController tipoAcController = MainController.getTipoAcomodacaoController();
        TipoAcomodacao tipo = tipoAcController.procuraTipoAcomodacao(nomeTipoAcomodacao);

        if(tipo == null){
            throw exception.Excecoes.tipoAcomodInv;
        }

        Acomodacao acomodacao = new Acomodacao(numero, ocupacaoMax, tipo, estado);
        acomodacoes.put(acomodacao.getNumero(), acomodacao);
        
        MainController.save();
        
    }

    public Acomodacao procuraNumeroAcomodacao(int numero){
        return acomodacoes.get(numero);
    }

    public Object[][] tabelaAcomodacoes(){
        int tam = acomodacoes.size();
        Object[][] vetor = new Object[tam][6];
        int i = 0;
        for (Acomodacao a : acomodacoes.values()){
            vetor[i][0] = a.getNumero();
            vetor[i][1] = a.getTipo();
            vetor[i][2] = a.getTarifaDiaria();
            vetor[i][3] = a.getAdicionalAcompanhante();
            vetor[i][4] = a.getOcupacaoMaxima();
            vetor[i][5] = a.getEstadoOcupacao().getDescricao();
            i++;
        }
        return vetor;
    }

    public void removeAcomodacao(int numero){
        acomodacoes.remove(numero);
        
        MainController.save();
    }

    public void alteraAcomodacao(int num, EEstadoOcupacao estado) throws IllegalArgumentException{
        Acomodacao a = procuraNumeroAcomodacao(num);
        a.setEstadoOcupacao(estado);

        MainController.save();
    }

	public int getOcupMax(int num) {
		Acomodacao ac = procuraNumeroAcomodacao(num);
		return ac.getOcupacaoMaxima();
	}

}
