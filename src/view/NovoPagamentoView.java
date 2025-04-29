package view;


import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.HospedagemController;
import controller.MainController;
import model.Pagamento.ETipoPagamento;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class NovoPagamentoView extends DefaultMiniView{

    private static final long serialVersionUID = -5276773478L;
    
    private JLabel pagamentolb;
    private JLabel formaPagamentolb;
    private JTextField pagamentotf;
    private JComboBox <String> formaPagCB;
    private JButton pagarB;
    private String id;

    public NovoPagamentoView(String id){
        this.id = id;
        initialize();
    }

    
    public void initialize(){       
        
        //-------TITULO-----------        
        titulo.setText("NOVO PAGAMENTO");                          
        
        //-------FORMULARIO--- 
        

        pagamentolb = new JLabel("Pagamento: ");
        pagamentolb.setForeground(corTexto);         
        pagamentolb.setFont(textFont); 

        pagamentotf = new JTextField();
        pagamentotf.setFont(textFont);
        pagamentotf.setColumns(5);

        formaPagamentolb = new JLabel("Forma de pagamento: ");  
        formaPagamentolb.setForeground(corTexto);         
        formaPagamentolb.setFont(textFont); 

        String[] pagamentosT = { "Débito", "Crédito", "Pix"};
        formaPagCB = new JComboBox<>(pagamentosT);                 
        formaPagCB.setFont(textfFont); 

        pagarB = new JButton("Pagar");
        pagarB.setFont(buttonFont);  
        pagarB.setForeground(corFundo2.brighter().brighter().brighter().brighter().brighter().brighter()); 
        pagarB.setBackground(corFundo2);
        pagarB.setBorder(borda);      
        pagarB.addActionListener(new ActionListener() {            
            public void actionPerformed(ActionEvent e) {
                actionPagar();
            }            
        });    

        formP.setLayout(new GridLayout(3,1,0,0));        
        formP.setBorder(BorderFactory.createEmptyBorder(40,0,10,0));      
        
     
        //-----ADDPANEL------
        

        JPanel form1 = new JPanel();
        form1.setOpaque(false);
        form1.add(pagamentolb);
        form1.add(pagamentotf);

        JPanel form2 = new JPanel();
        form2.setOpaque(false);
        form2.add(formaPagamentolb);
        form2.add(formaPagCB);
        

        buttonsP.setLayout(new GridLayout(1,2,90,0));
        buttonsP.setBorder(BorderFactory.createEmptyBorder(20,90,0,90)); 
        buttonsP.add(pagarB);

        formP.add(form1);
        formP.add(form2);
        
        
        


        //-------WINDOW-------
        setTitle("Cadastro de Pagamento");
        
    } 

    public void actionPagar(){
        
        try{
            HospedagemController controller = MainController.getHospedagemController();

            Double valor = Double.parseDouble(pagamentotf.getText());

            String status = new String(formaPagCB.getSelectedItem().toString());
            
            ETipoPagamento estado = ETipoPagamento.DEBITO;
            if(status.equals("Débito")){
                estado = ETipoPagamento.DEBITO;
            }
            else if(status.equals("Crédito")){
                estado = ETipoPagamento.CREDITO;
            }
            else if(status.equals("Pix")){
                estado = ETipoPagamento.PIX;
            }

            controller.addPagamento(this.id, estado, valor);

            this.dispose();

        }
        catch(NumberFormatException e){
            JOptionPane.showMessageDialog(this, "Valor inválido", "Erro", 0);
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

    }

    

}