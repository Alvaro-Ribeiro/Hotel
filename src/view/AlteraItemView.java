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
import javax.swing.SwingConstants;

import controller.CategoriaController;
import controller.ItemController;
import controller.MainController;

public class AlteraItemView extends DefaultMiniView{

    private static final long serialVersionUID = -42819778L;

    private JLabel codigolb;
    private JLabel descricaolb;
    private JLabel precolb;
    private JLabel codigotf;
    private JTextField descricaotf;
    private JTextField precotf;

    private JButton removerB;
    private JButton alterarB;
    private String cod;    
    
    private ServicosView sView;
    
    private String cat;

    public AlteraItemView(long codigo, String cat, String descricao, double preco, ServicosView sView){
        super();
        this.sView = sView;
        this.cat = cat;
        initialize(codigo, descricao, preco);
    }

    
    public void initialize(long codigo, String descricao, Double preco){
        
        //-------TITULO-----------
        titulo.setText("ALTERAR ITEM");
        
        //-------FORMULARIO-------
        codigolb = new JLabel ();
        codigolb.setText("Código: ");
        codigolb.setFont(textFont);
        codigolb.setForeground(corTexto);
        codigolb.setHorizontalAlignment(SwingConstants.CENTER);  
        
        codigotf = new JLabel();
        cod = new String(Long.toString(codigo));
        codigotf.setText(cod);
        codigotf.setForeground(corTexto);
        codigotf.setFont(textfFont);

        descricaolb = new JLabel ();
        descricaolb.setText("Descrição: ");
        descricaolb.setFont(textFont);
        descricaolb.setForeground(corTexto);
        descricaolb.setHorizontalAlignment(SwingConstants.CENTER);

        descricaotf = new JTextField(); 
        descricaotf.setText(descricao);
        descricaotf.setFont(textfFont);               
        descricaotf.setColumns(12);  
        descricaotf.setEditable(false);

        precolb = new JLabel ();
        precolb.setText("Preço: ");
        precolb.setFont(textFont);
        precolb.setForeground(corTexto);
        precolb.setHorizontalAlignment(SwingConstants.CENTER);

        precotf = new JTextField(); 
        precotf.setText(preco.toString());
        precotf.setFont(textfFont);               
        precotf.setColumns(5);  

        removerB = new JButton("Excluir");
        removerB.setFont(buttonFont);  
        removerB.setForeground(corFundo2.brighter().brighter().brighter().brighter().brighter().brighter()); 
        removerB.setBackground(corFundo2);
        removerB.setBorder(borda);      
        removerB.addActionListener(new ActionListener() {            
            public void actionPerformed(ActionEvent e) {
                actionRemoveItem();
            }            
        });

        alterarB = new JButton("Alterar");
        alterarB.setFont(buttonFont);
        alterarB.setForeground(corFundo2.brighter().brighter().brighter().brighter().brighter().brighter()); 
        alterarB.setBackground(corFundo2);
        alterarB.setBorder(borda);
        alterarB.addActionListener(new ActionListener() {            
            public void actionPerformed(ActionEvent e) {
                actionAlteraItem();
            }            
        });
        

        //-------PANELS------

        JPanel form1 = new JPanel();
        form1.setOpaque(false);
        form1.add(descricaolb);
        form1.add(descricaotf);

        JPanel form2 = new JPanel();
        form2.setOpaque(false);
        form2.add(precolb);
        form2.add(precotf);   

        JPanel form3 = new JPanel();
        form3.setOpaque(false);
        form3.add(codigolb);
        form3.add(codigotf);    

        formP.setLayout(new GridLayout(4,1,0,0));        
        formP.setBorder(BorderFactory.createEmptyBorder(40,0,10,0));      

        //-----ADDPANEL------        
        
        buttonsP.setLayout(new GridLayout(1,2,90,0));        
        buttonsP.setBorder(BorderFactory.createEmptyBorder(20,90,0,90));  
        buttonsP.add(removerB);
        buttonsP.add(alterarB);


        formP.add(form3);
        formP.add(form1);
        formP.add(form2);
        formP.add(buttonsP);

        //-------WINDOW-------
        setTitle("Alterar Item");        
        
    } 

    public void actionAlteraItem(){
        
        try{

            ItemController controller = MainController.getItemController();
            
            CategoriaController catController = MainController.getCategoriaController();

            Long codigo = Long.parseLong(codigotf.getText());

            Double preco = Double.parseDouble(precotf.getText());

            controller.alteraItem(codigo, preco);
            
            this.sView.refresh(catController.tabelaServicos("Todas"), catController.listaCategorias(), "Todas");

            this.dispose();
        }
        catch(NumberFormatException e) {
        	JOptionPane.showMessageDialog(this, "Número inválido", "Erro", 0);
        }
        catch(IllegalArgumentException e){
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", 0);
            return;
        }
        catch(Exception e){
            e.printStackTrace();
            return;
        }


    }

    public void actionRemoveItem(){

        try{
            
            CategoriaController catController = MainController.getCategoriaController();

            Long codigo = Long.parseLong(codigotf.getText());
            
            catController.removeItem(this.cat, codigo);
            
            this.sView.refresh(catController.tabelaServicos("Todas"), catController.listaCategorias(), "Todas");

            this.dispose();
        }
        catch(Exception e){
            e.printStackTrace();
            return;
        }


    }
    
}
