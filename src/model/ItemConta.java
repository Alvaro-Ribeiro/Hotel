package model;

import java.io.Serializable;
import java.time.LocalDateTime;

import util.Util;

public class ItemConta implements Serializable{

    private static final long serialVersionUID = 8363165070L;
    
    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    private final LocalDateTime dataHora = LocalDateTime.now();
    private final double preco;
    private final int qtd;

    private final Item item;

    public ItemConta(Item item, int qtd) {
        if(qtd <= 0){
            throw exception.Excecoes.qtdeInv;
        }
        this.item = item;
        this.qtd = qtd;
        this.preco = item.getPreco();
    }

    public LocalDateTime getDataHora() {
        return this.dataHora;
    }

    public double getPreco() {
        return this.preco;
    }

    public int getQtd() {
        return this.qtd;
    }   

    public Item getItem(){
        return this.item;
    } 
    
    public double getTotal() {
        return this.getPreco() * this.getQtd();
    }

    public String listarItemConta(){
        StringBuilder lista = new StringBuilder();
        lista.append(this.getItem().getDescricao() + " | R$ ");    
        lista.append(Util.valueFormat(this.getPreco()) + " | qtde: ");
        lista.append(this.getQtd() + " | Parcial: R$ ");
        lista.append(Util.valueFormat(this.getTotal()) + " | ");
        lista.append(Util.dataFormat(this.getDataHora()) + "\n");
        return lista.toString();
    }
}
