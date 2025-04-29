package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import controller.CategoriaController;
import controller.ItemController;
import controller.MainController;

public class ServicosView extends DefaultView {

    private static final long serialVersionUID = 57487893L;

    private JTable tabela;
    private JScrollPane scrollPane;
    private JPanel tablePanel;

    private JLabel titulo;
    private Font tituloFont;
    private Font tableFont;
    private Font menuFont;

    private JPanel rightPanel;
    private JButton addItemB;    
    private JButton ajustaItemB;
    
    private DefaultTableModel model;
    
    private JPanel catPanel;
    private JLabel categorialb;
    private JComboBox<String> categoriacb;
    

    public ServicosView(Object[][] data, String[] listaCat){
        super();
        this.initialize(data, listaCat);
    }

    public void initialize(Object[][] data, String[] listaCat){
        tituloFont = new Font("Helvetica", Font.PLAIN, 50); 
        menuFont = new Font("Helvetica", Font.PLAIN, 20);
        tableFont = new Font("Helvetica", Font.PLAIN, 14); 

        categorialb = new JLabel("Categoria: ");
        categorialb.setForeground(Color.WHITE);         
        categorialb.setFont(textFont); 
        
        categoriacb = new JComboBox<>();
        
        ActionListener cbActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String s = (String) categoriacb.getSelectedItem();
                
                if(s != null) {
                	CategoriaController controller = MainController.getCategoriaController();
                	
                	refresh(controller.tabelaServicos(s), controller.listaCategorias(), s);
                	
                }

            }
        };
        
        categoriacb.addActionListener(cbActionListener);
        
        tabela = new JTable();
        model = new DefaultTableModel();
        tabela.setModel(model);
        tabela.setDefaultEditor(Object.class, null);
        
        tabela.setPreferredScrollableViewportSize(new Dimension(600, 500));
        tabela.setFont(tableFont);
        tabela.setRowHeight(20);
        
        model.setColumnCount(0);
        model.setRowCount(0);
        model.addColumn("Código");
        model.addColumn("Categoria");
        model.addColumn("Descrição");
        model.addColumn("Preço");
        this.refresh(data, listaCat, "Todas");
        
        JTableHeader tbHeader = tabela.getTableHeader();       
        tbHeader.setBackground(new Color(100, 80,250,90));        
        tabela.getColumnModel().getColumn(0).setPreferredWidth(5);
        tabela.getColumnModel().getColumn(1).setPreferredWidth(50);
        tabela.getColumnModel().getColumn(2).setPreferredWidth(120);
        tabela.getColumnModel().getColumn(3).setPreferredWidth(50);
        tabela.getTableHeader().setReorderingAllowed(false);        
               
        tabela.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        scrollPane = new JScrollPane(tabela);

        tablePanel = new JPanel(new GridBagLayout());
        tablePanel.add(scrollPane);
        tablePanel.setOpaque(false);

        titulo = new JLabel ();
        titulo.setText("SERVIÇOS");
        titulo.setFont(tituloFont);
        titulo.setForeground(Color.WHITE.darker().darker());
        titulo.setHorizontalAlignment(SwingConstants.CENTER); 
        

        addItemB = new JButton();
        addItemB.setSize(200, 50);
        addItemB.setOpaque(false);
        addItemB.setContentAreaFilled(false);
        addItemB.setBorderPainted(false);        
        ImageIcon test2 = new ImageIcon("src/imagens/add.png");
        Image dtest2 = test2.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon add = new ImageIcon(dtest2);
        addItemB.setIcon(add);
        addItemB.setText("Item");
        addItemB.setForeground(Color.WHITE);
        addItemB.setFont(menuFont);
        addItemB.setIconTextGap(15);
        addItemB.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				actionAddItem();
			}
		});

        ajustaItemB = new JButton();
        ajustaItemB.setSize(200, 50);
        ajustaItemB.setOpaque(false);
        ajustaItemB.setContentAreaFilled(false);
        ajustaItemB.setBorderPainted(false);        
        ImageIcon test = new ImageIcon("src/imagens/config.png");
        Image dtest = test.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon ajusta = new ImageIcon(dtest);
        ajustaItemB.setIcon(ajusta);
        ajustaItemB.setText("Alterar");
        ajustaItemB.setForeground(Color.WHITE);
        ajustaItemB.setFont(menuFont);
        ajustaItemB.setIconTextGap(15);
        ajustaItemB.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				actionAjustaItem();
			}
		});
        
        
        rightPanel = new JPanel(new GridLayout(2,1));
        rightPanel.setBorder(BorderFactory.createEmptyBorder(200,0,0,0));
        rightPanel.setOpaque(false);
        rightPanel.add(addItemB);
        rightPanel.add(ajustaItemB);
        
        catPanel = new JPanel();
        catPanel.add(categorialb);
        catPanel.add(categoriacb);
        catPanel.setSize(100,100);
        catPanel.setOpaque(false);
        
        JPanel painel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints(); 

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.1;
        c.gridx = 0;
        c.gridy = 0;
        painel.add(catPanel, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 520;
        c.weightx = 0.0;
        c.gridx = 0;
        c.gridy = 1;

        painel.add(tablePanel, c);
        painel.setOpaque(false);

        centerP.add(titulo, BorderLayout.NORTH);
        centerP.add(painel,BorderLayout.CENTER);
        fundoP.add(rightPanel, BorderLayout.AFTER_LINE_ENDS);  

        //-------WINDOW-------
        setTitle("Servicos");
        
    }

    public void actionAddItem(){
        CategoriaController controller = MainController.getCategoriaController();
        NovoItemView view = new NovoItemView(controller.listaCategorias(), this);
        view.setLocationRelativeTo(this);
        view.setAlwaysOnTop(true);

        view.setVisible(true);
    }
    
    public void actionAjustaItem(){

        int index = this.tabela.getSelectedRow();
        if(index == -1){
            JOptionPane.showMessageDialog(this, "Selecione um item");
            return;
        }
        Long codigo = Long.parseLong(tabela.getValueAt(index, 0).toString());
        String categoria = new String(tabela.getValueAt(index, 1).toString());

        ItemController controller = MainController.getItemController();
        Object[] vetor = controller.exibeItem(codigo);

        String descricao = new String(vetor[1].toString());
        Double preco = Double.parseDouble(vetor[2].toString());

        AlteraItemView view = new AlteraItemView(codigo, categoria, descricao, preco, this);
        view.setLocationRelativeTo(this);
        view.setAlwaysOnTop(true);

        view.setVisible(true);
    }

    public void refresh(Object[][] data, String[] listaCat, String s) {    
        	
        model.setRowCount(0);        
        
        int tam = data.length;
        for(int i = 0; i < tam; i++) {
            String a = String.format("%.2f", data[i][3]);
            data[i][3] = a;          
        	model.addRow(data[i]);
        }
        
        categoriacb.removeAllItems();
        
        categoriacb.addItem("Todas");
        for (String string : listaCat) {
        	categoriacb.addItem(string);			
		}
        
        categoriacb.setSelectedItem(s);
        
        model.fireTableDataChanged();
    }

}
