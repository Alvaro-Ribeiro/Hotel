package view;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controller.HospedagemController;
import controller.MainController;



public class ServirHospedeView extends DefaultMiniView{

    private static final long serialVersionUID = 6775592919L;

    private JLabel quartolb;
    
    private JLabel itemlb;
    private JLabel qtdlb;
    private JSpinner qtdS;
    private JTextField itemtf;
    private JButton adicionarB;    
    private String id;
    private int num;
    
    private HospedagensView hView;

    public ServirHospedeView(String id, int num, HospedagensView hView){
        super();
        this.id = id;
        this.num = num;
        this.hView = hView;
        initialize();
    }
    
    public void initialize(){  
        
        
        //-------TITULO-----------
        titulo.setText("SERVIÇO");
        
        //-------FORMULARIO-------
        quartolb = new JLabel ();
        String quarto = new String("Quarto " + this.num);
        quartolb.setText(quarto);
        quartolb.setFont(textFont);
        quartolb.setForeground(Color.WHITE.darker());
        quartolb.setHorizontalAlignment(SwingConstants.CENTER);   
        
        

        itemlb = new JLabel ();
        itemlb.setText("Item: ");
        itemlb.setForeground(corTexto);
        itemlb.setFont(textFont);

        itemtf = new JTextField();
        itemtf.setFont(textFont);
        itemtf.setColumns(8);

        qtdlb = new JLabel();
        qtdlb.setText("Quantidade: ");
        qtdlb.setForeground(corTexto);
        qtdlb.setFont(textFont);

        qtdS = new JSpinner();
        qtdS.setModel(new javax.swing.SpinnerNumberModel(0,0,null,1 ));       
        qtdS.setPreferredSize(new Dimension(60,30));
        qtdS.setFont(textFont);   
        
        adicionarB = new JButton("Adicionar");
        adicionarB.setFont(buttonFont);  
        adicionarB.setForeground(corFundo2.brighter().brighter().brighter().brighter().brighter().brighter()); 
        adicionarB.setBackground(corFundo2);
        adicionarB.setBorder(borda);  
        adicionarB.addActionListener(new ActionListener() {            
            public void actionPerformed(ActionEvent e) {
                actionAdicionarPedido();
            }            
        });

        
        //-------PANELS----
        formP.setLayout(new GridLayout(3,1,0,0));        
        formP.setBorder(BorderFactory.createEmptyBorder(40,0,10,0));      
        formP.setOpaque(false);
     

        //-----ADDPANEL------        

        JPanel form1 = new JPanel();
        form1.setOpaque(false);
        form1.add(itemlb);
        form1.add(itemtf);  

        JPanel form2 = new JPanel();
        form2.setOpaque(false);
        form2.add(qtdlb);
        form2.add(qtdS);   

        
        
        buttonsP.setBorder(BorderFactory.createEmptyBorder(20,90,0,90)); 
        buttonsP.add(adicionarB);
        
        formP.add(quartolb);
        formP.add(form1);
        formP.add(form2);
        
        

        //-------WINDOW-------
        setTitle("Adicionar a Conta");        
        
    } 

    public void actionAdicionarPedido(){
        
        try{

            Long codigo = Long.parseLong(itemtf.getText());

            Integer qtd = Integer.parseInt(qtdS.getValue().toString());

            MainController.getContaController().addItemConta(id, codigo, qtd);
            
            HospedagemController controller = MainController.getHospedagemController();
            
            this.hView.refresh(controller.tabelaHospedagens());

            this.dispose();

        }
        catch(NumberFormatException e) {
        	JOptionPane.showMessageDialog(this, "Código inválido", "Erro", 0);
        }
        catch(IllegalArgumentException e){
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", 0);
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }

    
    
}
