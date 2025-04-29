package controller;

import java.io.Serializable;

import persistence.Serializer;

public class MainController implements Serializable{
    
    private static final long serialVersionUID = -8577087301L;

    private static MainController instance;

    private final AcomodacaoController acomodacaoController = new AcomodacaoController();
    private final CategoriaController categoriaController = new CategoriaController();
    private final ContaController contaController = new ContaController();
    private final HospedagemController hospedagemController = new HospedagemController();
    private final HospedeController hospedeController = new HospedeController();
    private final ItemController itemController = new ItemController();
    private final TipoAcomodacaoController tipoAcomodacaoController = new TipoAcomodacaoController();

    private MainController(){
        
    }

    public static synchronized MainController getInstance(){
        if(instance == null){
            instance = new MainController();
        }
        return instance;
    }
    
    public static AcomodacaoController getAcomodacaoController() {
        return instance.acomodacaoController;
    }
    
    public static CategoriaController getCategoriaController() {
        return instance.categoriaController;
    }

    public static ContaController getContaController() {
        return instance.contaController;
    }

    public static HospedagemController getHospedagemController() {
        return instance.hospedagemController;
    }

    public static HospedeController getHospedeController() {
        return instance.hospedeController;
    }

    public static ItemController getItemController() {
        return instance.itemController;
    }

    public static TipoAcomodacaoController getTipoAcomodacaoController() {
        return instance.tipoAcomodacaoController;
    }

    public static void load(){
        instance = Serializer.readFile();
        getInstance();
    }

    public static void save() {
		Serializer.writeFile(instance);
	}

}
