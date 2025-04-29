package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class ExtratoContaView extends DefaultMiniView{  

    private static final long serialVersionUID = 3877694797L;

    private JTextArea tabela;
    private JScrollPane scrollPane;
    private Font contaFont;

    public ExtratoContaView(String data){
        super();
        initialize(data);
    }

    public void initialize(String data){
    	
    	contaFont = new Font("Helvetica", Font.PLAIN, 18);
        
        //-------TITULO-----------        
        titulo.setText("EXTRATO DA CONTA");    

        tabela = new JTextArea(data);     
        tabela.setBounds(190,300,300,600); 
        tabela.setFont(contaFont);    
        tabela.setForeground(Color.WHITE);
        tabela.setOpaque(false);
        
        scrollPane = new JScrollPane(tabela);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(null);
        
        formP.setLayout(new BorderLayout());  
        formP.add(scrollPane);   
        
        //-------WINDOW-------
        setTitle("Extrato da Conta");    
    } 
}
