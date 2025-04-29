package controller;

import java.io.Serializable;
import java.util.TreeMap;

import model.Categoria;
import model.Item;
import util.Util;

public class CategoriaController implements Serializable{ //Categoria e Catálogo

    private static final long serialVersionUID = -38393666L;
    
	private TreeMap<String, Categoria> categorias = new TreeMap<>();

    public void addCategoria(String nome) throws IllegalArgumentException {
        if(procuraCategoria(nome) != null) {
        	throw exception.Excecoes.catJaExiste;
        }
        if(Util.validaNomeCat(nome) == false){
            throw exception.Excecoes.nomeInv;
        }
        Categoria categoria = new Categoria(nome);
        categorias.put(categoria.getNome(), categoria);
        MainController.save();
    }

    public Categoria procuraCategoria(String nome){
        return categorias.get(nome);
    }
    
    public String[] listaCategorias() {
        int tam = categorias.size();
    	String[] vetor = new String[tam]; 
        int i = 0;
    	
        for (Categoria c : categorias.values()) {
            vetor[i] = c.getNome();
            i++;
        }

    	return vetor;
    }


    public void addItem(long codigo, String descricao, double preco, String nomeCat)throws IllegalArgumentException{

        ItemController controller = MainController.getItemController();

        Categoria categoria = categorias.get(nomeCat);

        Item item = controller.addItem(codigo, descricao, preco);

        categoria.addItem(item);

        MainController.save();
    }
    
    public Object[][] tabelaServicos(String nome) {
    	Object[][] vetor;
    	
    	if(nome.equals("Todas")) {
    		int tam = 0;
    		for (Categoria cat : categorias.values()) {
    			tam += cat.getItens().size();
    		}
    		vetor = new Object[tam][4];
    		int l = 0;
    		for (Categoria cat : categorias.values()) {
    			for (Item i : cat.getItens()) {
    				vetor[l][0] = i.getCodigo();
    				vetor[l][1] = cat.getNome();
    				vetor[l][2] = i.getDescricao();
    				vetor[l][3] = i.getPreco();
    				l++;
    			}
    		}    		
    	}
    	else {
    		Categoria cat = procuraCategoria(nome);
    		int tam = cat.getItens().size();
    		vetor = new Object[tam][4];
    		int l = 0;
    		for (Item i : cat.getItens()) {
    			vetor[l][0] = i.getCodigo();
    			vetor[l][1] = cat.getNome();
    			vetor[l][2] = i.getDescricao();
    			vetor[l][3] = i.getPreco();
    			l++;
    		}
    	}
    	
    	return vetor;
    }

	public void removeItem(String nomeCat, long codigo) {
		
		Categoria categoria = categorias.get(nomeCat);
		
		ItemController controller = MainController.getItemController();
		
		Item item = controller.procuraItem(codigo);
		
		categoria.removeItem(item);
		
		controller.removeItem(codigo);
		
		MainController.save();
		
	}

}
