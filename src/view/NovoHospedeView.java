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

import controller.HospedeController;
import controller.MainController;


public class NovoHospedeView extends DefaultMiniView{

    private static final long serialVersionUID = -6030220715L;
 
    private JLabel nomelb;
    private JTextField nometf;
    private JLabel cpflb;
    private JTextField cpftf;
    private JLabel tellb;
    private JTextField teltf;
    private JLabel emaillb;
    private JTextField emailtf;
    private JButton limpar;
    private JButton salvar;
    
 
    public NovoHospedeView(){
        initialize();
    }

    
    public void initialize(){          
    
        //-------TITULO-----------
        
        titulo.setText("NOVO HÓSPEDE");
                            
        
        //-------FORMULARIO---
        nomelb = new JLabel("Nome: ");  
        nomelb.setForeground(corTexto);         
        nomelb.setFont(textFont); 
        
        nometf = new JTextField(); 
        nometf.setFont(textfFont);               
        nometf.setColumns(24);        
        
        cpflb = new JLabel("CPF: ");  
        cpflb.setForeground(corTexto); 
        cpflb.setFont(textFont);

        cpftf = new JTextField();
        cpftf.setFont(textfFont); 
        cpftf.setColumns(10);

        tellb = new JLabel(" Telefone: ");  
        tellb.setForeground(corTexto);   
        tellb.setFont(textFont); 

        teltf = new JTextField();
        teltf.setFont(textfFont); 
        teltf.setColumns(9);

        emaillb = new JLabel("Email: "); 
        emaillb.setForeground(corTexto);  
        emaillb.setFont(textFont);  
            
        emailtf = new JTextField(); 
        emailtf.setFont(textfFont); 
        emailtf.setColumns(24); 
        
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
            public void actionPerformed(ActionEvent e) {
                actionSalvar();
            }            
        });
        
        
        
        //-------PANELS----
        
        formP.setLayout( new GridLayout(3,1,0,0));        
        formP.setBorder(BorderFactory.createEmptyBorder(60,20,10,20));      


        //-----ADDPANEL------
        JPanel form1 = new JPanel();
        form1.setOpaque(false);
        form1.add(nomelb);
        form1.add(nometf);

        JPanel form2 = new JPanel();
        form2.setOpaque(false);
        form2.add(cpflb);
        form2.add(cpftf);
        form2.add(tellb);
        form2.add(teltf);

        JPanel form3 = new JPanel();
        form3.setOpaque(false);
        form3.add(emaillb);
        form3.add(emailtf);

        buttonsP.setLayout(new GridLayout(1,2,90,0));
        buttonsP.setBorder(BorderFactory.createEmptyBorder(20,90,0,90));         
        buttonsP.add(limpar);
        buttonsP.add(salvar);


        formP.add(form1);
        formP.add(form2);
        formP.add(form3);      

        //-------WINDOW-------
        setTitle("Cadastro de Hospede");      
        
    } 

    public void actionLimpar(){
        nometf.setText("");
        cpftf.setText("");
        teltf.setText("");
        emailtf.setText("");
    }   

    public void actionSalvar(){
        
        try{
            String Cpf = (String) cpftf.getText();
            if(util.Util.validaCPF(Cpf) == false) {
            	throw exception.Excecoes.cpfInv;
            }
            
            String tel = (String) teltf.getText();
            if(util.Util.validaTelefone(tel) == false) {
            	throw exception.Excecoes.telInv;
            }
            
            String nome = new String(nometf.getText());
            
            Long cpf = Long.parseLong(Cpf);            	
    
            Long telefone = Long.parseLong(tel);  
            
            String email = new String(emailtf.getText());
    
            HospedeController controller = MainController.getHospedeController();

            controller.addHospede(cpf, nome, telefone, email);

        }
        catch(IllegalArgumentException e){
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", 0);
            return;
        }
        catch(Exception e){
            e.printStackTrace();
            return;
        }

        this.dispose();

    }
    
}
