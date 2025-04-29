package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.TreeMap;

import model.Acomodacao;
import model.Hospedagem;
import model.Hospede;
import model.IHospede;
import model.Pagamento.ETipoPagamento;

public class HospedagemController implements Serializable { //Hospedagem e Pagamento

    private static final long serialVersionUID = -5862414463L;

    private TreeMap<String, Hospedagem> hospedagens = new TreeMap<>();
    private TreeMap<String, Hospedagem> historico = new TreeMap<>();

    private ArrayList<IHospede> acompanhantes = new ArrayList<>();

    public void addHospedagem(int numAcomodacao, long cpfHospede, ArrayList<Long> cpfs) throws IllegalArgumentException {
        
        acompanhantes.clear();
    	
    	Acomodacao acomodacao = MainController.getAcomodacaoController().procuraNumeroAcomodacao(numAcomodacao);

        Hospede hospede = MainController.getHospedeController().procuraCpf(cpfHospede);

        for (Long cpf : cpfs) {
            acompanhantes.add(MainController.getHospedeController().procuraCpf(cpf));
        }

        Hospedagem hospedagem = new Hospedagem(acomodacao, hospede, acompanhantes);
        hospedagens.put(hospedagem.getId(), hospedagem);
        
        MainController.save();
    }

    public Hospedagem procuraHospedagem(String id){
        return hospedagens.get(id);
    }
    
    public Hospedagem procuraHistorico(String id){
        return historico.get(id);
    }
    
    public void addAcompanhante(long cpfHospede){

        Hospede hospede = MainController.getHospedeController().procuraCpf(cpfHospede);

        acompanhantes.add(hospede);

        MainController.save();
    }
    
    public void fazerCheckout(String idHospedagem) throws IllegalArgumentException { 
        
        Hospedagem hospedagem = procuraHospedagem(idHospedagem);
        hospedagem.realizarCheckout();

        hospedagens.remove(idHospedagem);
        historico.put(idHospedagem, hospedagem);

        MainController.save();
    }

    public double valorFaltante(String id){
        Hospedagem hospedagem = procuraHospedagem(id);
        return hospedagem.pgmtFaltante();
    }

    public void addPagamento(String idHospedagem, ETipoPagamento tipo, double valor) throws IllegalArgumentException {
        Hospedagem hospedagem = procuraHospedagem(idHospedagem);

        hospedagem.addPagamento(tipo, valor);

        MainController.save();
    }

    public Object[][] tabelaHospedagens(){
        int tam = hospedagens.size();
        Object[][] vetor = new Object[tam][5];
        int i = 0;
        for (Hospedagem h : hospedagens.values()){
            vetor[i][0] = h.getId();
            vetor[i][1] = h.getAcomodacao().getNumero();
            vetor[i][2] = h.getHospede().getNome();
            vetor[i][3] = util.Util.dataFormat(h.getCheckin());
            vetor[i][4] = h.getTotalConta(); 
            i++;
        }
        return vetor;
    }

    public Object[][] tabelaHistorico(){
        int tam = historico.size();
        Object[][] vetor = new Object[tam][6];
        int i = 0;
        for (Hospedagem h : historico.values()){
            vetor[i][0] = h.getId();
            vetor[i][1] = h.getAcomodacao().getNumero();
            vetor[i][2] = String.format("%011d", h.getHospede().getCpf());
            vetor[i][3] = util.Util.dataFormat(h.getCheckin());
            vetor[i][4] = util.Util.dataFormat(h.getCheckout());
            vetor[i][5] = String.format("%.2f", h.getTotalHospedagem()); 
            i++;
        }
        return vetor;
    }

	public String extratoHospedagem(String id) {
		
		Hospedagem hospedagem = procuraHistorico(id);
		
		return hospedagem.listar().toString();
	}
    
}
