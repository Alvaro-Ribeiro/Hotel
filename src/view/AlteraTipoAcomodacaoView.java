package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.AcomodacaoController;
import controller.MainController;
import controller.TipoAcomodacaoController;




public class AlteraTipoAcomodacaoView extends DefaultMiniView{

    private static final long serialVersionUID = -3457656160L;

    private JComboBox <String> tipocb;
    private JLabel tarifalb;
    private JTextField tarifatf;
    private JLabel tarAcomlb;
    private JTextField tarAcomtf;
    private JButton alterarB;
    
    private AcomodacoesView acView;

    public AlteraTipoAcomodacaoView(String[] data, AcomodacoesView acView){
        super();
        this.acView = acView;
        initialize(data);
    }

    
    public void initialize(String[] data){  
        
        
        //-------TITULO-----------
        titulo.setText("ALTERAR TIPO-ACOMODAÇÃO");
        
        //-------FORMULARIO-------
        tipocb = new JComboBox<>(data);                 
        tipocb.setFont(textfFont);     
        
        tarifalb = new JLabel("Tarifa Diária: ");  
        tarifalb.setForeground(corTexto); 
        tarifalb.setFont(textFont);

        tarifatf = new JTextField();
        tarifatf.setFont(textFont);
        tarifatf.setColumns(5);

        tarAcomlb = new JLabel("Adicional/Acompanhante: ");  
        tarAcomlb.setForeground(corTexto);   
        tarAcomlb.setFont(textFont); 

        tarAcomtf = new JTextField();
        tarAcomtf.setFont(textFont);
        tarAcomtf.setColumns(5);

        alterarB = new JButton("Alterar");
        alterarB.setFont(buttonFont);  
        alterarB.setForeground(corFundo2.brighter().brighter().brighter().brighter().brighter().brighter()); 
        alterarB.setBackground(corFundo2);
        alterarB.setBorder(borda);      
        alterarB.addActionListener(new ActionListener() {            
            public void actionPerformed(ActionEvent e) {
                actionAlterar();                
            }            
        });



        //-------PANELS------

         JPanel form1 = new JPanel();
        form1.setOpaque(false);
        form1.add(tipocb);

        JPanel form2 = new JPanel();
        form2.setOpaque(false);
        form2.add(tarifalb);
        form2.add(tarifatf);
        

        JPanel form3 = new JPanel();
        form3.setOpaque(false);
        form3.add(tarAcomlb);
        form3.add(tarAcomtf);
               

        formP.setLayout(new GridLayout(3,1,0,0));        
        formP.setBorder(BorderFactory.createEmptyBorder(40,0,10,0));   

     
         
        //-----ADDPANEL------        
        
        buttonsP.setLayout(new GridLayout(1,2,90,0));        
        buttonsP.setBorder(BorderFactory.createEmptyBorder(20,90,0,90)); 
        buttonsP.add(alterarB); 

        
        formP.add(form1);
        formP.add(form2);
        formP.add(form3);        
        

        //-------WINDOW-------
        setTitle("Alterar Tipo de Acomodacao");        
        
    } 

    public void actionAlterar(){

        try{

            String nome = new String(tipocb.getSelectedItem().toString());

            Double tarifa = Double.parseDouble(tarifatf.getText());

            Double taxa = Double.parseDouble(tarAcomtf.getText());

            TipoAcomodacaoController controller = MainController.getTipoAcomodacaoController();

            controller.alteraTipoAcomodacao(nome, tarifa, taxa);
            
            AcomodacaoController acController = MainController.getAcomodacaoController();
            
            this.acView.refresh(acController.tabelaAcomodacoes());

            this.dispose();

        }
        catch(NumberFormatException e){
            JOptionPane.showMessageDialog(this, "Valor inválido.\nDigite um valor no formato 0.00");
        }
        catch(IllegalArgumentException e){
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", 0);
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }

    
    
}
