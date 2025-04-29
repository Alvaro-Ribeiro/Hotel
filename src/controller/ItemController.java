package controller;

import java.io.Serializable;
import java.util.TreeMap;

import model.Item;

public class ItemController implements Serializable{

    private static final long serialVersionUID = 8608849329L;
    
	private TreeMap<Long, Item> itens = new TreeMap<>();

    public Item addItem(long codigo, String descricao, double preco) throws IllegalArgumentException {
        if(procuraItem(codigo) != null) {
        	throw exception.Excecoes.itemJaCad;
        }
        Item item = new Item(codigo, descricao, preco);
        itens.put(item.getCodigo(), item);

        MainController.save();

        return item;
    }

    public Item procuraItem(Long codigo){
        return itens.get(codigo);
    }

    public Object[] exibeItem(long codigo){

        Item item = procuraItem(codigo);

        Object[] vetor = new Object[3];

        vetor[0] = item.getCodigo();
        vetor[1] = item.getDescricao();
        vetor[2] = item.getPreco();

        return vetor;

    }

    public Object[][] tabela_Servicos(){

        int tam = itens.size();

        Object[][] vetor = new Object[tam][3];
        int c = 0;

        for (Item i : itens.values()) {
            vetor[c][0] = i.getCodigo();
            vetor[c][1] = i.getDescricao();
            vetor[c][2] = i.getPreco();
            c++;
        }
        return vetor;
    }

    public void alteraItem(long codigo, double preco) throws IllegalArgumentException{
        Item item = procuraItem(codigo);

        item.setPreco(preco);

        MainController.save();
    }

    public void removeItem(long codigo){
        itens.remove(codigo);

        MainController.save();
    }

}
