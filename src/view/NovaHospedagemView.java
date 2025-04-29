package view;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.MainController;
import controller.HospedeController;


public class NovaHospedagemView extends DefaultMiniView{

    private static final long serialVersionUID = -6160324921L;
 
    private JLabel quartolb;
    private JLabel quartotf;
    
    private JLabel hospedelb;
    private JTextField hospedetf;
    private JButton plusB;
    
    private JButton proximoB;
    
    private AcomodacoesView acView;
    
    public NovaHospedagemView(int num, AcomodacoesView acView){
    	this.acView = acView;
        initialize(num);
    }

    
    public void initialize(int num){   
    
        //-------TITULO-----------
        
        titulo.setText("NOVA HOSPEDAGEM");
                            
        
        //-------FORMULARIO---
        quartolb = new JLabel("Quarto: ");
        quartolb.setForeground(corTexto);         
        quartolb.setFont(textFont); 

        quartotf = new JLabel();
        quartotf.setText(Integer.toString(num));      
        quartotf.setFont(textFont);
        quartotf.setForeground(corTexto);

        //--1
        hospedelb = new JLabel("CPF do Hospede: ");
        hospedelb.setForeground(corTexto);         
        hospedelb.setFont(textFont); 

        hospedetf = new JTextField();    
        hospedetf.setFont(textFont); 
        hospedetf.setColumns(10);

        plusB = new JButton();        
        plusB.setOpaque(false);
        plusB.setContentAreaFilled(false);
        plusB.setBorderPainted(false);        
        ImageIcon test = new ImageIcon("src/imagens/add.png");
        Image dtest = test.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon add = new ImageIcon(dtest);
        plusB.setIcon(add);  
        plusB.addActionListener(new ActionListener() {            
            public void actionPerformed(ActionEvent e) {
                actionAddNovoHospede();
            }            
        });

        proximoB = new JButton("Avançar");
        proximoB.setFont(buttonFont);  
        proximoB.setForeground(corFundo2.brighter().brighter().brighter().brighter().brighter().brighter()); 
        proximoB.setBackground(corFundo2);
        proximoB.setBorder(borda);      
        proximoB.addActionListener(new ActionListener() {            
            public void actionPerformed(ActionEvent e) {
                actionProximo();
            }            
        });       
        
        //-------PANELS----        
        formP.setLayout( new GridLayout(3,1,0,0));        
        formP.setBorder(BorderFactory.createEmptyBorder(60,20,10,20));      


        //-----ADDPANEL------
        JPanel form1 = new JPanel();
        form1.setOpaque(false);
        form1.add(hospedelb);
        form1.add(hospedetf);
        form1.add(plusB);

        JPanel form2 = new JPanel();
        form2.setOpaque(false);
        form2.add(quartolb);
        form2.add(quartotf);
        
        buttonsP.setBorder(BorderFactory.createEmptyBorder(20,90,0,90)); 
        buttonsP.add(proximoB);    
        
        formP.add(form2);
        formP.add(form1);
        
        //-------WINDOW-------
        setTitle("Cadastro de Hospedagem");      
        
    }  

    public void actionAddNovoHospede(){
        NovoHospedeView view = new NovoHospedeView();
        view.setLocationRelativeTo(this);
        view.setAlwaysOnTop(true);
        view.setVisible(true);
    }

    public void actionProximo(){

        try{
            HospedeController controller = MainController.getHospedeController();
            
            Long cpf = Long.parseLong(hospedetf.getText());

            if(controller.procuraCpf(cpf) == null){
                throw exception.Excecoes.cpfNaoCad;
            }
            Integer num = Integer.parseInt(quartotf.getText());

            AcompanhantesView view = new AcompanhantesView(cpf, num, this.acView);
            view.setLocationRelativeTo(this);
            view.setAlwaysOnTop(true);
            view.setVisible(true);

            this.dispose();

        }
        catch(NumberFormatException e){
            JOptionPane.showMessageDialog(this, "No campo CPF, insira apenas numeros!", "Erro", 0);
        }
        catch(IllegalArgumentException e){
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", 0);
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }
    
}
