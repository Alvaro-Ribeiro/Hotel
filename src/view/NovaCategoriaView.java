package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


import controller.CategoriaController;
import controller.MainController;

public class NovaCategoriaView extends DefaultMiniView{

    private static final long serialVersionUID = 7938014953L;

    JLabel nomelb;
    JTextField nometf;
    JButton limpar;
    JButton salvar; 
    
    private ServicosView sView;
    
    private NovoItemView iView;

    public NovaCategoriaView(ServicosView sView, NovoItemView iView){
    	this.sView = sView;
    	this.iView = iView;
        initialize();
    }

    
    public void initialize(){        
        
        
        //-------TITULO-----------
        titulo.setText("NOVA CATEGORIA");
                          
        
        //-------FORMULARIO---
        nomelb = new JLabel("Nome: ");  
        nomelb.setForeground(corTexto);         
        nomelb.setFont(textFont); 

        
        nometf = new JTextField(); 
        nometf.setFont(textfFont);               
        nometf.setColumns(24);        
                
        limpar = new JButton("Limpar");
        limpar.setFont(buttonFont);
        limpar.setForeground(corFundo2.brighter().brighter().brighter().brighter().brighter().brighter()); 
        limpar.setBackground(corFundo2);
        limpar.setBorder(borda);

        limpar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionLimpar();               
            }            
        });

        salvar = new JButton("Salvar");
        salvar.setFont(buttonFont);  
        salvar.setForeground(corFundo2.brighter().brighter().brighter().brighter().brighter().brighter()); 
        salvar.setBackground(corFundo2);
        salvar.setBorder(borda);      
        salvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionSalvar();               
            }            
        });
              
        formP.setLayout(new GridLayout(3,1,0,0));        
        formP.setBorder(BorderFactory.createEmptyBorder(60,20,10,20));      
        
   
        //-----ADDPANEL------
        JPanel form1 = new JPanel();
        form1.setOpaque(false);
        form1.add(nomelb);
        form1.add(nometf);        

        buttonsP.setLayout(new GridLayout(1,2,90,0));
        buttonsP.setBorder(BorderFactory.createEmptyBorder(20,90,0,90)); 
        buttonsP.add(limpar);
        buttonsP.add(salvar);

        formP.add(form1);  

        //-------WINDOW-------
        setTitle("Cadastro de Categoria");        
        
    }    
    public void actionLimpar(){
        nometf.setText("");                

    }

    public void actionSalvar(){

        try{
            CategoriaController controller = MainController.getCategoriaController();

            String nomeCat = new String(nometf.getText());
            
            if(nomeCat.equals("") || nomeCat.startsWith(" ")) {
            	throw exception.Excecoes.nomeInv;
            }

            controller.addCategoria(nomeCat);
            
            this.sView.refresh(controller.tabelaServicos("Todas"), controller.listaCategorias(), "Todas");
            
            this.iView.refresh(controller.listaCategorias());

        }
        catch(IllegalArgumentException e){
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", 0);
            return;
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", 0);
            return;
        }

        this.dispose();

    }
    
}
