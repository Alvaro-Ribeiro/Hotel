package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.Image;

import controller.AcomodacaoController;
import controller.HospedagemController;
import controller.HospedeController;
import controller.MainController;

public class AcompanhantesView extends DefaultMiniView{

    private static final long serialVersionUID = -652794827L;    

    private JTable tabela;
    private DefaultTableModel model;

    private JTextField cpftf;
    private JButton plusB;
    private JButton finalizarB;
    private JButton addAcomp;
    
    private int numero;
    private long cpf;
    
    private AcomodacoesView acView;

     public AcompanhantesView(long cpf, int num, AcomodacoesView acView){
        this.numero = num;
        this.cpf = cpf;
        this.acView = acView;
        initialize();
    }

    public void initialize(){

        tabela = new JTable();
        model = new DefaultTableModel();
        tabela.setModel(model);
        model.setRowCount(0);
        model.setColumnCount(0);
        model.addColumn("Nome");
        model.addColumn("CPF");
        tabela.setPreferredScrollableViewportSize(new Dimension(600, 200));
        tabela.setDefaultEditor(Object.class, null);
        tabela.setFont(textFont);
        tabela.setRowHeight(25);
             
        JTableHeader tbHeader = tabela.getTableHeader();        
        tbHeader.setBackground(new Color(100, 80,250,90));  
        tabela.getTableHeader().setReorderingAllowed(false);
        
        JScrollPane scrollPane = new JScrollPane(tabela);

        JPanel tablePanel = new JPanel(new GridBagLayout());
        tablePanel.add(scrollPane);
        tablePanel.setOpaque(false);
        
        
        //-------TITULO-----------
        
        titulo.setText("ACOMPANHANTES");
                            
        
        //-------FORMULARIO---
        
        cpftf = new JTextField();
        cpftf.setFont(textfFont); 
        cpftf.setColumns(15);

        addAcomp = new JButton();        
        addAcomp.setOpaque(false);
        addAcomp.setContentAreaFilled(false);
        addAcomp.setBorderPainted(false);        
        ImageIcon test = new ImageIcon("src/imagens/arrow.png");
        Image dtest = test.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon add = new ImageIcon(dtest);
        addAcomp.setIcon(add);
        addAcomp.addActionListener(new ActionListener() {            
            public void actionPerformed(ActionEvent e) {
                actionAddAcompanhante();
            }            
        });


        plusB = new JButton();        
        plusB.setOpaque(false);
        plusB.setContentAreaFilled(false);
        plusB.setBorderPainted(false);        
        ImageIcon test2 = new ImageIcon("src/imagens/add.png");
        Image dtest2 = test2.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon add2 = new ImageIcon(dtest2);
        plusB.setIcon(add2);  
        plusB.addActionListener(new ActionListener() {            
            public void actionPerformed(ActionEvent e) {
                actionAddNovoHospede();
            }            
        });

        finalizarB = new JButton("Finalizar");
        finalizarB.setFont(buttonFont);  
        finalizarB.setForeground(corFundo2.brighter().brighter().brighter().brighter().brighter().brighter()); 
        finalizarB.setBackground(corFundo2);
        finalizarB.setBorder(borda);      
        finalizarB.addActionListener(new ActionListener() {            
            public void actionPerformed(ActionEvent e) {
                actionFinalizar();
            }            
        });
        
        //-------PANELS----        
        formP.setLayout( new BorderLayout());        
        formP.setBorder(BorderFactory.createEmptyBorder(40,20,0,20));      


        //-----ADDPANEL------     
        JPanel form1 = new JPanel();
        form1.setOpaque(false);
        form1.add(cpftf);
        form1.add(addAcomp);
        form1.add(plusB);         
        
        
        buttonsP.setBorder(BorderFactory.createEmptyBorder(20,90,0,90)); 
        buttonsP.add(finalizarB);

        formP.add(tablePanel, BorderLayout.NORTH);
        formP.add(form1, BorderLayout.SOUTH);

         
        
        
        //-------WINDOW-------
        setTitle("Cadastro de Acompanhantes");      
        
    }  

    public void actionAddNovoHospede(){
        NovoHospedeView view = new NovoHospedeView();
        view.setLocationRelativeTo(this);
        view.setAlwaysOnTop(true);
        view.setVisible(true);
    }    

    public void actionAddAcompanhante(){

        try{
            HospedeController controller = MainController.getHospedeController();
            
            AcomodacaoController acController = MainController.getAcomodacaoController();
            
            if(this.model.getRowCount() + 1 == acController.getOcupMax(numero)) {
            	throw exception.Excecoes.ocupMaxExtrapolada;
            }

            Long cpf = Long.parseLong(cpftf.getText());
            
            int tam = model.getRowCount();
            
            if(cpf.equals(this.cpf)) {
            	throw exception.Excecoes.hospNaoAcomp;
            }
            
            for(int i = 0; i < tam; i++) {
            	if(cpf.equals(model.getValueAt(i, 1))) {
            		throw exception.Excecoes.acompJaListado;
            	}
            }

            model.addRow(controller.exibeAcompanhante(cpf));
            
            model.fireTableDataChanged();

        }
        catch(NumberFormatException e) {
        	JOptionPane.showMessageDialog(this, "Cpf inválido", "Erro", 0);
        }
        catch(IllegalArgumentException e){
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", 0);
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }
    
    public void actionFinalizar(){
        
        try{

            HospedagemController controller = MainController.getHospedagemController();

            ArrayList<Long> cpfs = new ArrayList<>();
            cpfs.clear();
            
            System.out.println(cpfs.size());
            
            System.out.println(model.getRowCount());
            
            int tam = model.getRowCount();
            
            for (int i = 0; i < tam; i++) {

                Long cpfAc = Long.parseLong(model.getValueAt(i, 1).toString());

                cpfs.add(cpfAc);
            }

            controller.addHospedagem(numero, this.cpf, cpfs);
            
            AcomodacaoController acController = MainController.getAcomodacaoController();
            
            this.acView.refresh(acController.tabelaAcomodacoes());

            this.dispose();

        }

        catch(IllegalArgumentException e){
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", 0);
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }



}