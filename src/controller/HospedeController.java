package controller;

import java.io.Serializable;
import java.util.TreeMap;

import model.Hospede;

public class HospedeController implements Serializable{
    
    private static final long serialVersionUID = -3466429788L;

    private TreeMap<Long, Hospede> hospedes = new TreeMap<>();

    public void addHospede(long cpf, String nome, long telefone, String email) throws IllegalArgumentException {

        if(procuraCpf(cpf) != null) {
        	throw exception.Excecoes.cpfJaCad;
        }

        Hospede hospede = new Hospede(cpf, nome, telefone, email);    
        hospedes.put(cpf, hospede);

        MainController.save();
    }

    public Object[] exibeAcompanhante(long cpf){

        Hospede h = procuraCpf(cpf);

        if(h == null){
            throw exception.Excecoes.cpfNaoCad;
        }

        Object[] vetor = new Object[2];

        vetor[0] = h.getNome();
        vetor[1] = h.getCpf();

        return vetor;
    }

    public Hospede procuraCpf(Long cpf){
        return hospedes.get(cpf);
    }

}
