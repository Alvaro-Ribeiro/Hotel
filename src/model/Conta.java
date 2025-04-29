package model;

import java.io.Serializable;
import java.util.ArrayList;

import util.Util;

public class Conta implements IConta, Serializable {

    private static final long serialVersionUID = -9731631926L;
    
    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    private ArrayList<ItemConta> itens = new ArrayList<>();
    
    private ArrayList<ItemConta> getItens() {
    	return this.itens;
    }
    
    @Override
    public void addItem(Item item, int qtde) throws IllegalArgumentException {
        ItemConta i = new ItemConta(item, qtde);
        itens.add(i);
    }

    @Override
    public void removeItem(int index) {
        itens.remove(index);
    }

    @Override
    public double getTotal() {
        double total = 0;
        for (ItemConta i : itens) {
            total += i.getTotal();            
        }
        return total;
    }

    @Override
    public StringBuilder listar() {
        StringBuilder lista = new StringBuilder();
        int cont = 1;
        for ( ItemConta i : itens) {
            lista.append(cont + " - " + i.listarItemConta());
            cont++;
        }
        if(this.getItens().size() == 0) {
        	lista.append("Nenhum item consumido.\n");
        }
        lista.append("Total Conta: " + Util.valueFormat(this.getTotal()));

        return lista;
    }

    
}
