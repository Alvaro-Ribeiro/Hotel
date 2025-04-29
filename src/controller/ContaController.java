package controller;

import java.io.Serializable;

import model.Hospedagem;
import model.IConta;
import model.Item;

public class ContaController implements Serializable { //Conta e ItemConta

    private static final long serialVersionUID = -1791958983L;
    
    public String listarConta(String idHospedagem){
        Hospedagem hospedagem = MainController.getHospedagemController().procuraHospedagem(idHospedagem);

        IConta conta = hospedagem.getConta();

        return conta.listar().toString();
    }

    public void addItemConta(String idHospedagem, long codigo, int qtd) throws IllegalArgumentException{
        Hospedagem hospedagem = MainController.getHospedagemController().procuraHospedagem(idHospedagem);
        
        IConta conta = hospedagem.getConta();

        Item item = MainController.getItemController().procuraItem(codigo);

        if(item == null){
            throw exception.Excecoes.itemNaoCad;
        }

        conta.addItem(item, qtd);

        MainController.save();
    }
    
    public void removeItemConta(String idHospedagem, int index){
        Hospedagem hospedagem = MainController.getHospedagemController().procuraHospedagem(idHospedagem);
        
        IConta conta = hospedagem.getConta();

        conta.removeItem(index);

        MainController.save();
    }

    public String tabelaExtrato(String id) {

        Hospedagem h = MainController.getHospedagemController().procuraHospedagem(id);
        IConta c = h.getConta();

        return c.listar().toString();
    }

}
