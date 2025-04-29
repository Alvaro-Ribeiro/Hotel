package model;

import java.io.Serializable;
import java.util.Date;

public class Pagamento implements Serializable{

    public enum ETipoPagamento{
        DEBITO("Débito"),
        CREDITO("Crédito"),
        PIX("Pix");

        private final String descricao;

        ETipoPagamento(String descricao){
            this.descricao = descricao;
        }

        public String getDescricao(){
            return this.descricao;
        }
    }

    private static final long serialVersionUID = -5429068128L;

    private final ETipoPagamento tipo;
    private final Date data = new Date();
    private final double valor;

    public Pagamento(ETipoPagamento tipo, double valor) {
        if(valor <= 0){
            throw exception.Excecoes.valorInv;
        }
        this.tipo = tipo;
        this.valor = valor;
    }

    public ETipoPagamento getTipo() {
        return this.tipo;
    }

    public Date getData() {
        return this.data;
    }

    public double getValor() {
        return this.valor;
    }

    public String listarPagamento(){
        StringBuilder listar = new StringBuilder();                                       //Formatar data!
        listar.append(this.getTipo().getDescricao() + " - R$ " + this.getValor() + " - " + this.getData());
        listar.append("\n");
        return listar.toString();
    }
    
}
