package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import controller.ContaController;
import controller.HospedagemController;
import controller.MainController;

public class HospedagensView extends DefaultView {

    private static final long serialVersionUID = -561679455L;
    
    private JLabel titulo;
    private Font tituloFont;
    private Font tableFont;
    
    private JTable tabela;
    private JScrollPane scrollPane;
    private JPanel tablePanel;

    private JPanel rightPanel;
    private JButton servicosB;
    private JButton extratoB;
    private JButton pagamentosB;
    private JButton checkOutB;
    private Font menuFont;
    
    private DefaultTableModel model;

    public HospedagensView(Object[][] data){
        super();
        this.initialize(data);
    }

    public void initialize(Object[][] data){
        tituloFont = new Font("Helvetica", Font.PLAIN, 50); 
        menuFont = new Font("Helvetica", Font.PLAIN, 20);
        tableFont = new Font("Helvetica", Font.PLAIN, 14); 

        titulo = new JLabel ();
        titulo.setText("HOSPEDAGENS");
        titulo.setFont(tituloFont);
        titulo.setForeground(Color.WHITE.darker().darker());
        titulo.setHorizontalAlignment(SwingConstants.CENTER);

        tabela = new JTable();
        model = new DefaultTableModel();
        tabela.setModel(model);

        model.setColumnCount(0);
        model.setRowCount(0);
        model.addColumn("ID");
        model.addColumn("N° Quarto");
        model.addColumn("Hóspede");
        model.addColumn("Checkin");
        model.addColumn("Conta");
        this.refresh(data);
        
        tabela.setPreferredScrollableViewportSize(new Dimension(600, 500));
        tabela.setDefaultEditor(Object.class, null);
        tabela.setFont(tableFont);
        tabela.setRowHeight(20);
        JTableHeader tbHeader = tabela.getTableHeader();        
        tbHeader.setBackground(new Color(100, 80,250,90));        
        tabela.getColumnModel().getColumn(0).setPreferredWidth(5); 
        tabela.getColumnModel().getColumn(1).setPreferredWidth(14);
        tabela.getColumnModel().getColumn(2).setPreferredWidth(130);
        tabela.getColumnModel().getColumn(3).setPreferredWidth(100);  
        tabela.getColumnModel().getColumn(4).setPreferredWidth(24); 
        tabela.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        tabela.getTableHeader().setReorderingAllowed(false);
        
        scrollPane = new JScrollPane(tabela);

        tablePanel = new JPanel(new GridBagLayout());
        tablePanel.add(scrollPane);
        tablePanel.setOpaque(false);

        servicosB = new JButton();
        servicosB.setSize(200, 50);
        servicosB.setOpaque(false);
        servicosB.setContentAreaFilled(false);
        servicosB.setBorderPainted(false);        
        ImageIcon test3 = new ImageIcon("src/imagens/bell.png");
        Image dtest3 = test3.getImage().getScaledInstance(30, 25, Image.SCALE_SMOOTH);
        ImageIcon bell = new ImageIcon(dtest3);
        servicosB.setIcon(bell);
        servicosB.setText("Pedir");
        servicosB.setForeground(Color.WHITE);
        servicosB.setFont(menuFont);
        servicosB.setIconTextGap(15);
        servicosB.addActionListener(new ActionListener() {            
            public void actionPerformed(ActionEvent e) {
                actionServirHospede();
            }            
        });

        extratoB = new JButton();
        extratoB.setSize(200, 50);
        extratoB.setOpaque(false);
        extratoB.setContentAreaFilled(false);
        extratoB.setBorderPainted(false);        
        ImageIcon test4 = new ImageIcon("src/imagens/extrato.png");
        Image dtest4 = test4.getImage().getScaledInstance(30, 25, Image.SCALE_SMOOTH);
        ImageIcon extrato = new ImageIcon(dtest4);
        extratoB.setIcon(extrato);
        extratoB.setText("Conta");
        extratoB.setForeground(Color.WHITE);
        extratoB.setFont(menuFont);
        extratoB.setIconTextGap(15);
        extratoB.addActionListener(new ActionListener() {            
            public void actionPerformed(ActionEvent e) {
                actionExtratoConta();
            }            
        });

        
        pagamentosB = new JButton();
        pagamentosB.setSize(200, 50);
        pagamentosB.setOpaque(false);
        pagamentosB.setContentAreaFilled(false);
        pagamentosB.setBorderPainted(false);        
        ImageIcon test2 = new ImageIcon("src/imagens/moneyhand.png");
        Image dtest2 = test2.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon add = new ImageIcon(dtest2);
        pagamentosB.setIcon(add);
        pagamentosB.setText("Pagar");
        pagamentosB.setForeground(Color.WHITE);
        pagamentosB.setFont(menuFont);
        pagamentosB.setIconTextGap(15);
        pagamentosB.addActionListener(new ActionListener() {            
            public void actionPerformed(ActionEvent e) {
                actionPagar();
            }            
        });

        checkOutB = new JButton();
        checkOutB.setSize(200, 50);
        checkOutB.setOpaque(false);
        checkOutB.setContentAreaFilled(false);
        checkOutB.setBorderPainted(false);        
        ImageIcon test = new ImageIcon("src/imagens/checkout.png");
        Image dtest = test.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon pay = new ImageIcon(dtest);
        checkOutB.setIcon(pay);
        checkOutB.setText("CheckOut");
        checkOutB.setForeground(Color.WHITE);
        checkOutB.setFont(menuFont);
        checkOutB.setIconTextGap(15);
        checkOutB.addActionListener(new ActionListener() {            
            public void actionPerformed(ActionEvent e) {
                actionCheckOut();
            }            
        });

        rightPanel = new JPanel(new GridLayout(4,1));
        rightPanel.setBorder(BorderFactory.createEmptyBorder(100,0,0,0));
        rightPanel.setOpaque(false);
        rightPanel.add(servicosB);
        rightPanel.add(extratoB);
        rightPanel.add(pagamentosB);
        rightPanel.add(checkOutB);
        

        centerP.add(titulo, BorderLayout.NORTH);
        centerP.add(tablePanel,BorderLayout.CENTER);  
        fundoP.add(rightPanel, BorderLayout.AFTER_LINE_ENDS);        
        
        //-------WINDOW-------
        setTitle("Hospedagens");
    }

    public void actionPagar(){
        int index = tabela.getSelectedRow();
        
        if(index == -1){
            JOptionPane.showMessageDialog(this, "Selecione uma Hospedagem");
            return;
        }
        
        String id = new String(tabela.getValueAt(index, 0).toString());
        NovoPagamentoView view = new NovoPagamentoView(id);
        view.setLocationRelativeTo(this);
        view.setAlwaysOnTop(true);
        view.setVisible(true);

    }

    public void actionServirHospede(){

        int index = tabela.getSelectedRow();
        
        if(index == -1){
            JOptionPane.showMessageDialog(this, "Selecione uma Hospedagem");
            return;
        }

        String id = new String(tabela.getValueAt(index, 0).toString());

        Integer numQuarto = Integer.parseInt(tabela.getValueAt(index, 1).toString());

        ServirHospedeView view = new ServirHospedeView(id, numQuarto, this);
        view.setLocationRelativeTo(this);
        view.setAlwaysOnTop(true);

        view.setVisible(true);
    }

    public void actionExtratoConta(){

        int index = tabela.getSelectedRow();
        
        if(index == -1){
            JOptionPane.showMessageDialog(this, "Selecione uma Hospedagem");
            return;
        }
        
        ContaController controller = MainController.getContaController();

        String id = new String(tabela.getValueAt(index, 0).toString());

        ExtratoContaView view = new ExtratoContaView(controller.tabelaExtrato(id));
        view.setLocationRelativeTo(this);
        view.setAlwaysOnTop(true);

        view.setVisible(true);
    }

    public void actionCheckOut(){

        double valor = -1;
        int index = tabela.getSelectedRow();
        
        if(index == -1){
            JOptionPane.showMessageDialog(this, "Selecione uma Hospedagem");
            return;
        }
        
        String id = new String(tabela.getValueAt(index, 0).toString());
        try{

        	HospedagemController controller = MainController.getHospedagemController();
        	
        	controller.fazerCheckout(id);        	
        	
        	JOptionPane.showMessageDialog(this, controller.extratoHospedagem(id));
        		
        	this.refresh(controller.tabelaHospedagens());
        	
            
        }
        catch(IllegalArgumentException e){
            valor = MainController.getHospedagemController().valorFaltante(id);
            String erro = new String(e.getMessage() + "\nValor Faltante: " + valor);
            JOptionPane.showMessageDialog(this, erro, "Erro", 0);
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }
    
    public void refresh(Object[][] data) {
    
        model.setRowCount(0);
        
        int tam = data.length;
        for(int i = 0; i < tam; i++) {
            String valor = String.format("%.2f", data[i][4]);
            data[i][4] = valor;  
        	model.addRow(data[i]);
        }
        model.fireTableDataChanged();
    	this.model.fireTableDataChanged();
    }

}
