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

import controller.AcomodacaoController;
import controller.MainController;
import model.Acomodacao.EEstadoOcupacao;




public class AlteraQuartoView extends DefaultMiniView{

    private static final long serialVersionUID = 9073733925L;

    private JLabel quartolb;
    private JLabel quartotf;
    
    private JLabel statuslb;
    private JComboBox <String> statuscb;
    
    private JButton alterarB;    
    
    private AcomodacoesView acView;

    public AlteraQuartoView(int num, AcomodacoesView acView){
        super();
        this.acView = acView;
        initialize(num);
    }

    
    public void initialize(int num){  
        
        
        //-------TITULO-----------
        titulo.setText("ALTERAR QUARTO");
        
        //-------FORMULARIO-------
        quartolb = new JLabel("Quarto: ");  
        quartolb.setForeground(corTexto);         
        quartolb.setFont(textFont);         

        quartotf = new JLabel();
        quartotf.setFont(textFont);
        quartotf.setText(Integer.toString(num));
        quartotf.setForeground(corTexto);

        statuslb = new JLabel("Quarto: ");  
        statuslb.setForeground(corTexto);         
        statuslb.setFont(textFont);   

        
        String[] quartoStatus = { "Disponível", "Manutenção"};
        statuscb = new JComboBox<>(quartoStatus);                 
        statuscb.setFont(textfFont); 
        

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
        form1.add(quartolb);
        form1.add(quartotf); 

        JPanel form2 = new JPanel();
        form2.setOpaque(false);
        form2.add(statuslb);
        form2.add(statuscb);       

        formP.setLayout(new GridLayout(4,1,0,0));        
        formP.setBorder(BorderFactory.createEmptyBorder(40,0,10,0));   
         
        //-----ADDPANEL------        
        
        buttonsP.setLayout(new GridLayout(1,2,90,0));        
        buttonsP.setBorder(BorderFactory.createEmptyBorder(20,90,0,90)); 
        buttonsP.add(alterarB); 

        
        formP.add(form1);
        formP.add(form2);
            

        //-------WINDOW-------
        setTitle("Alterar Quarto");        
        
    } 

    public void actionAlterar(){

        try{
            Integer num = Integer.parseInt(quartotf.getText());

            String status = new String(statuscb.getSelectedItem().toString());

            EEstadoOcupacao estado = EEstadoOcupacao.MANUTENCAO;
            if(status.equals("Disponível")){
                estado = EEstadoOcupacao.DISPONIVEL;
            }
            else if(status.equals("Ocupado")){
                estado = EEstadoOcupacao.OCUPADO;
            }
            else if(status.equals("Manutenção")){
                estado = EEstadoOcupacao.MANUTENCAO;
            }

            AcomodacaoController controller = MainController.getAcomodacaoController();
            controller.alteraAcomodacao(num, estado);
            
            this.acView.refresh(controller.tabelaAcomodacoes());

            this.dispose();
        }
        catch(IllegalArgumentException e) {
        	JOptionPane.showMessageDialog(this, e.getMessage());
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }    
    
}
