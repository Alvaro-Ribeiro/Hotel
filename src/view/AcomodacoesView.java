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
import javax.swing.table.DefaultTableModel;
import javax.swing.SwingConstants;
import javax.swing.table.JTableHeader;

import controller.MainController;
import controller.TipoAcomodacaoController;

public class AcomodacoesView extends DefaultView {

    private static final long serialVersionUID = -8008990915L;
    
    private JLabel titulo;
    private Font tituloFont;
    private Font menuFont;
    private Font tableFont;
    private JTable tabela;
    private JScrollPane scrollPane;
    private JPanel tablePanel;
    private JPanel rightPanel;
    private JButton checkInB;
    private JButton addAcomodacao;
    private JButton gerenciarQuartoB;
    private JButton gerenciarTipoB;
    
    private DefaultTableModel model;

    public AcomodacoesView(Object[][] data){
        super();
        this.initialize(data);
    }

    public void initialize(Object[][] data){
        tituloFont = new Font("Helvetica", Font.PLAIN, 50); 
        menuFont = new Font("Helvetica", Font.PLAIN, 20); 
        tableFont = new Font("Helvetica", Font.PLAIN, 14); 

        tabela = new JTable();
        model = new DefaultTableModel();
        tabela.setModel(model);
        
        model.setColumnCount(0);
        model.setRowCount(0);
        model.addColumn("Número");
        model.addColumn("Tipo");
        model.addColumn("Tarifa/Dia");
        model.addColumn("Taxa/Ac.");
        model.addColumn("Ocup.Max");
        model.addColumn("Status");
        this.refresh(data);
        
        tabela.setPreferredScrollableViewportSize(new Dimension(600, 500));
        tabela.setDefaultEditor(Object.class, null);
        tabela.setFont(tableFont);        
        tabela.setRowHeight(20);
             
        JTableHeader tbHeader = tabela.getTableHeader();        
        tbHeader.setBackground(new Color(100, 80,250,90));        
        tabela.getColumnModel().getColumn(0).setPreferredWidth(5); 
        tabela.getColumnModel().getColumn(1).setPreferredWidth(50);
        tabela.getColumnModel().getColumn(2).setPreferredWidth(29);
        tabela.getColumnModel().getColumn(3).setPreferredWidth(40);
        tabela.getColumnModel().getColumn(4).setPreferredWidth(40);
        tabela.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        tabela.getTableHeader().setReorderingAllowed(false);

        scrollPane = new JScrollPane(tabela);
         
        tablePanel = new JPanel(new GridBagLayout());
        tablePanel.add(scrollPane);
        tablePanel.setOpaque(false);
        
        titulo = new JLabel ();
        titulo.setText("ACOMODAÇÕES");
        titulo.setFont(tituloFont);
        titulo.setForeground(Color.WHITE.darker().darker());
        titulo.setHorizontalAlignment(SwingConstants.CENTER);   
        
        checkInB = new JButton();
        checkInB.setSize(200, 50);
        checkInB.setOpaque(false);
        checkInB.setContentAreaFilled(false);
        checkInB.setBorderPainted(false);        
        ImageIcon test = new ImageIcon("src/imagens/malas.png");
        Image dtest = test.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon mala = new ImageIcon(dtest);
        checkInB.setIcon(mala);
        checkInB.setText("CheckIn");
        checkInB.setForeground(Color.WHITE);
        checkInB.setFont(menuFont);
        checkInB.setIconTextGap(15);        
        checkInB.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				actionAddHospedagem();
			}
		});

        addAcomodacao = new JButton();
        addAcomodacao.setSize(200, 50);
        addAcomodacao.setOpaque(false);
        addAcomodacao.setContentAreaFilled(false);
        addAcomodacao.setBorderPainted(false);        
        ImageIcon test2 = new ImageIcon("src/imagens/add.png");
        Image dtest2 = test2.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        ImageIcon add = new ImageIcon(dtest2);
        addAcomodacao.setIcon(add);
        addAcomodacao.setText("Quarto");
        addAcomodacao.setForeground(Color.WHITE);
        addAcomodacao.setFont(menuFont);
        addAcomodacao.setIconTextGap(15);
        addAcomodacao.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				actionAddAcomodacao();
			}
		});

        gerenciarQuartoB = new JButton();
        gerenciarQuartoB.setSize(200, 50);
        gerenciarQuartoB.setOpaque(false);
        gerenciarQuartoB.setContentAreaFilled(false);
        gerenciarQuartoB.setBorderPainted(false);        
        ImageIcon test4 = new ImageIcon("src/imagens/config.png");
        Image dtest4 = test4.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        ImageIcon gear = new ImageIcon(dtest4);
        gerenciarQuartoB.setIcon(gear);
        gerenciarQuartoB.setText("Quarto");
        gerenciarQuartoB.setForeground(Color.WHITE);
        gerenciarQuartoB.setFont(menuFont);
        gerenciarQuartoB.setIconTextGap(15);
        gerenciarQuartoB.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				actionGerenciarQuarto();
			}
		});

        gerenciarTipoB = new JButton();
        gerenciarTipoB.setSize(200, 50);
        gerenciarTipoB.setOpaque(false);
        gerenciarTipoB.setContentAreaFilled(false);
        gerenciarTipoB.setBorderPainted(false);        
        ImageIcon test3 = new ImageIcon("src/imagens/config.png");
        Image dtest3 = test3.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        ImageIcon gear2 = new ImageIcon(dtest3);
        gerenciarTipoB.setIcon(gear2);
        gerenciarTipoB.setText("Tipo Ac.");
        gerenciarTipoB.setForeground(Color.WHITE);
        gerenciarTipoB.setFont(menuFont);
        gerenciarTipoB.setIconTextGap(15);
        gerenciarTipoB.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				actionGerenciarTipo();
			}
		});
        
        rightPanel = new JPanel(new GridLayout(4,1));
        rightPanel.setBorder(BorderFactory.createEmptyBorder(100,0,0,0));
        rightPanel.setOpaque(false);
        rightPanel.add(checkInB);
        rightPanel.add(addAcomodacao);
        rightPanel.add(gerenciarQuartoB);
        rightPanel.add(gerenciarTipoB);

        JPanel center = new JPanel(new BorderLayout());
        center.setBorder(BorderFactory.createEmptyBorder(30, 15, 0, 0));
        center.setOpaque(false);
        center.add(titulo, BorderLayout.NORTH);
        center.add(tablePanel,BorderLayout.CENTER);
        fundoP.add(center, BorderLayout.CENTER);       
        
        fundoP.add(rightPanel, BorderLayout.AFTER_LINE_ENDS);
        fundoP.setOpaque(false);

        //-------WINDOW-------
        setTitle("Acomodacoes");

    }
    public void actionAddHospedagem(){

        int index = this.tabela.getSelectedRow();

        if(index == -1){
            JOptionPane.showMessageDialog(this, "Selecione uma acomodação");
            return;
        }

        Integer num = Integer.parseInt(tabela.getValueAt(index, 0).toString());
        
        String status = (String) tabela.getValueAt(index, 5);
        
        if(status.equals("Disponível") == false) {
        	JOptionPane.showMessageDialog(this, "Acomodação Ocupada");
        	return;
        }
        
        NovaHospedagemView view = new NovaHospedagemView(num, this);
        view.setLocationRelativeTo(this);
        view.setAlwaysOnTop(true);

        view.setVisible(true);
    }

    public void actionGerenciarQuarto(){
        
        int index = this.tabela.getSelectedRow();
        
        if(index == -1){
            JOptionPane.showMessageDialog(this, "Selecione uma acomodação");
            return;
        }
        
        String status = (String) tabela.getValueAt(index, 5);
        
        if(status.equals("Ocupado")) {
        	JOptionPane.showMessageDialog(this, "Acomodação Ocupada\nEspere o checkout para mudar o status.");
        	return;
        }
        
        Integer num = Integer.parseInt(tabela.getValueAt(index, 0).toString());
        AlteraQuartoView view = new AlteraQuartoView(num, this);
        view.setLocationRelativeTo(this);
        view.setAlwaysOnTop(true);

        view.setVisible(true);

    }

    public void actionGerenciarTipo(){
         
        TipoAcomodacaoController controller = MainController.getTipoAcomodacaoController();
        AlteraTipoAcomodacaoView view = new AlteraTipoAcomodacaoView(controller.listaTipoAcomodacoes(), this);
        view.setLocationRelativeTo(this);
        view.setAlwaysOnTop(true);
        view.setVisible(true);

    }

    public void actionAddAcomodacao(){
        TipoAcomodacaoController controller = MainController.getTipoAcomodacaoController();
        NovaAcomodacaoView view = new NovaAcomodacaoView(controller.listaTipoAcomodacoes(), this);
        view.setLocationRelativeTo(this);
        view.setAlwaysOnTop(true);

        view.setVisible(true);
    }

    public void refresh(Object[][] data) {
    	
        model.setRowCount(0);
        
        int tam = data.length;
        for(int i = 0; i < tam; i++) {
            String a = String.format("%.2f", data[i][2]);
            data[i][2] = a; 
            a = String.format("%.2f", data[i][3]);
            data[i][3] = a;   
        	model.addRow(data[i]);
        }
        model.fireTableDataChanged();
    	this.model.fireTableDataChanged();
    }

}
