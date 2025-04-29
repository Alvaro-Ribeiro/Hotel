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

import controller.MainController;
import controller.TipoAcomodacaoController;

public class NovoTipoAcomodacaoView extends DefaultMiniView{

    private static final long serialVersionUID = 4013324270L;

    private JLabel nomelb;
    private JTextField nometf;
    private JLabel tarifalb;
    private JTextField tarifatf;
    private JLabel addAcomlb;
    private JTextField addAcomtf;
    
    private JButton limpar;
    private JButton salvar;
    
    private NovaAcomodacaoView aView;

    public NovoTipoAcomodacaoView(NovaAcomodacaoView aView){
    	this.aView = aView;
        initialize();
    }

    
    public void initialize(){  
  
        
        //-------TITULO-----------
   
        titulo.setText("NOVO TIPO ACOMODAÇÃO");                    
        
        //-------FORMULARIO---
        nomelb = new JLabel("Nome: ");  
        nomelb.setForeground(corTexto);         
        nomelb.setFont(textFont); 

        
        nometf = new JTextField(); 
        nometf.setFont(textfFont);               
        nometf.setColumns(15);      
        
        tarifalb = new JLabel("Tarifa Diária: ");  
        tarifalb.setForeground(corTexto); 
        tarifalb.setFont(textFont);

        tarifatf = new JTextField();
        tarifatf.setFont(textFont);
        tarifatf.setColumns(5);

        addAcomlb = new JLabel("Adicional/Acompanhante: ");  
        addAcomlb.setForeground(corTexto);   
        addAcomlb.setFont(textFont); 

        addAcomtf = new JTextField();
        addAcomtf.setFont(textFont);
        addAcomtf.setColumns(5);
        
        limpar = new JButton("Limpar");
        limpar.setFont(buttonFont);
        limpar.setForeground(corFundo2.brighter().brighter().brighter().brighter().brighter().brighter()); 
        limpar.setBackground(corFundo2);
        limpar.setBorder(borda);

        limpar.addActionListener(new ActionListener() {            
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
        
        
        
        //-------PANELS----       
        
        formP.setLayout(new GridLayout(3,1,0,0));        
        formP.setBorder(BorderFactory.createEmptyBorder(60,20,10,20));      
      

        //-----ADDPANEL------
        JPanel form1 = new JPanel();
        form1.setOpaque(false);
        form1.add(nomelb);
        form1.add(nometf);

        JPanel form2 = new JPanel();
        form2.setOpaque(false);
        form2.add(tarifalb);
        form2.add(tarifatf);
        

        JPanel form3 = new JPanel();
        form3.setOpaque(false);
        form3.add(addAcomlb);
        form3.add(addAcomtf);

        buttonsP.setLayout(new GridLayout(1,2,90,0));
        buttonsP.setBorder(BorderFactory.createEmptyBorder(20,90,0,90)); 
        buttonsP.add(limpar);
        buttonsP.add(salvar);


        formP.add(form1);
        formP.add(form2);
        formP.add(form3);   
        //-------WINDOW-------
        setTitle("Cadastro de Tipo de Acomodacao");        
        
    }   

    public void actionLimpar(){
        nometf.setText("");
        tarifatf.setText("");
        addAcomlb.setText("");
    }
    
    public void actionSalvar(){

        try{
            TipoAcomodacaoController controller = MainController.getTipoAcomodacaoController();

            String nome = new String(nometf.getText());
            
            if(nome.equals("") || nome.startsWith(" ")) {
            	throw exception.Excecoes.nomeInv;
            }
            
            Double tarifa = Double.parseDouble(tarifatf.getText());
            
            Double taxaAc = Double.parseDouble(addAcomtf.getText());

            controller.addTipoAcomodacao(nome, tarifa, taxaAc);
            
            this.aView.refresh(controller.listaTipoAcomodacoes());

        }
        catch(NumberFormatException e){
            JOptionPane.showMessageDialog(this, "Valor inválido.\nEntre com um valor no formato 0.00", "Erro", 0);
            return;
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
