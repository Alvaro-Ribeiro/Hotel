package model;

import java.io.Serializable;
import java.util.ArrayList;

import util.Util;

public class Categoria implements Serializable{
    
    private static final long serialVersionUID = 1186999588L;

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    private final String nome;

    private ArrayList<Item> itens = new ArrayList<>();

    public Categoria(String nome){
        if(Util.validaNomeCat(nome) == false){
            throw exception.Excecoes.nomeInv;
        }
        this.nome = nome;
    }

    public String getNome(){
        return this.nome;
    }

    public ArrayList<Item> getItens(){
        return this.itens;
    }

    public void addItem(Item item){
        itens.add(item);
    }
    
    public void removeItem(Item item) {
    	itens.remove(item);
    }

    public String listarCategoria(){
        StringBuilder lista = new StringBuilder();
        for (Item item : itens) {
            lista.append(item.listarItem());
        }
        return lista.toString();
    }

}
