package model;

import java.io.Serializable;

public class Item implements Serializable{

    private static final long serialVersionUID = 3858913582L;
        
    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    private final long codigo;
    private final String descricao;
    private double preco;

    public Item(long codigo, String descricao, double preco){
    	if(codigo < 0) {
    		throw exception.Excecoes.numInv;
    	}
        if(preco <= 0){
            throw exception.Excecoes.valorInv;
        }
        this.codigo = codigo;
        this.descricao = descricao;
        this.preco = preco;
    }

    public long getCodigo() {
        return this.codigo;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public double getPreco() {
        return this.preco;
    }

    public void setPreco(double preco) {
        if(preco <= 0){
            throw exception.Excecoes.valorInv;
        }
        this.preco = preco;
    }

    public String listarItem(){
        StringBuilder lista = new StringBuilder();
        lista.append("Cód.: " + this.getCodigo() + " | Desc.: " + this.getDescricao() + "| Preço: " + this.getPreco());
        lista.append("\n");
        return lista.toString();
    }
    
}
