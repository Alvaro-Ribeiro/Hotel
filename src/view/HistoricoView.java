package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class HistoricoView extends DefaultView {

    private static final long serialVersionUID = 9226134556L;

    private JTable tabela;
    private JScrollPane scrollPane;
    private JPanel tablePanel;

    private JLabel titulo;
    private Font tituloFont;
    private Font tableFont;
   
    public HistoricoView(Object[][] data){
        super();
        this.initialize(data);
    }
    
    public void initialize(Object[][] data){
        tituloFont = new Font("Helvetica", Font.PLAIN, 50); 
        tableFont = new Font("Helvetica", Font.PLAIN, 14); 
       
        String[] nomes = {"ID", "Quarto", "Hóspede", "Checkin", "Checkout", "Valor Final"};
        tabela = new JTable();
        
        DefaultTableModel model = new DefaultTableModel(data, nomes);
        tabela.setModel(model);
        tabela.setDefaultEditor(Object.class, null);
        
        tabela.setPreferredScrollableViewportSize(new Dimension(600, 500));
        tabela.setFont(tableFont);
        tabela.setRowHeight(20);
        JTableHeader tbHeader = tabela.getTableHeader();       
        tbHeader.setBackground(new Color(100, 80,250,90));        
        tabela.getColumnModel().getColumn(0).setPreferredWidth(5); 
        tabela.getColumnModel().getColumn(1).setPreferredWidth(1);
        tabela.getColumnModel().getColumn(2).setPreferredWidth(50);
        tabela.getColumnModel().getColumn(3).setPreferredWidth(90);  
        tabela.getColumnModel().getColumn(4).setPreferredWidth(90); 
        tabela.getColumnModel().getColumn(5).setPreferredWidth(15);      
        tabela.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        tabela.getTableHeader().setReorderingAllowed(false);

        scrollPane = new JScrollPane(tabela);
        tablePanel = new JPanel(new GridBagLayout());
        tablePanel.add(scrollPane);
        tablePanel.setOpaque(false);
        


        titulo = new JLabel ();
        titulo.setText("HISTÓRICO");
        titulo.setFont(tituloFont);
        titulo.setForeground(Color.WHITE.darker().darker());
        titulo.setHorizontalAlignment(SwingConstants.CENTER); 
      

        centerP.add(titulo, BorderLayout.NORTH);
        centerP.add(tablePanel,BorderLayout.CENTER);               
        
        //-------WINDOW-------
        setTitle("Historico");
    }
    
}
