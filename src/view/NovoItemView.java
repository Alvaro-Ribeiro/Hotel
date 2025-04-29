package view;


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
import javax.swing.JTextField;

import controller.CategoriaController;
import controller.MainController;

public class NovoItemView extends DefaultMiniView{

    private static final long serialVersionUID = -763340185L;

    private JLabel codigolb;
    private JTextField codigotf;
    private JLabel categorialb;  
    private JLabel descricaolb;
    private JTextField descricaotf;
    private JLabel precolb;
    private JButton plusB;
    private JTextField precotf;
    private JButton limpar;
    private JButton salvar;  

    private JComboBox <String> categoriacb;
    private ServicosView sView;

    public NovoItemView(String[] listaCat, ServicosView sView){
    	this.sView = sView;
        initialize(listaCat);
    }

    
    public void initialize(String[] listaCat){       
        
        //-------TITULO-----------        
        titulo.setText("NOVO ITEM");                          
        
        //-------FORMULARIO---  
        codigolb = new JLabel("Codigo: ");  
        codigolb.setForeground(corTexto);         
        codigolb.setFont(textFont); 

        codigotf = new JTextField(); 
        codigotf.setFont(textfFont);               
        codigotf.setColumns(12);   

        categorialb = new JLabel("Categoria: ");
        categorialb.setForeground(corTexto);         
        categorialb.setFont(textFont); 
        
        categoriacb = new JComboBox<>(listaCat);                 
        categoriacb.setFont(textfFont);        

        plusB = new JButton();        
        plusB.setOpaque(false);
        plusB.setContentAreaFilled(false);
        plusB.setBorderPainted(false);        
        ImageIcon test = new ImageIcon("src/imagens/add.png");
        Image dtest = test.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon add = new ImageIcon(dtest);
        plusB.setIcon(add); 
        plusB.addActionListener(new ActionListener() {            
            public void actionPerformed(ActionEvent e) {
                actionAddCategoria();
            }            
        }); 
        
        descricaolb = new JLabel("Descricão: ");
        descricaolb.setForeground(corTexto);
        descricaolb.setFont(textFont);

        descricaotf = new JTextField();
        descricaotf.setFont(textFont);
        descricaotf.setColumns(12);

        precolb = new JLabel("Preco: ");
        precolb.setForeground(corTexto);
        precolb.setFont(textFont);

        precotf = new JTextField();
        precotf.setColumns(8);
        precotf.setFont(textFont);
        
        limpar = new JButton("Limpar");
        limpar.setFont(buttonFont);
        limpar.setForeground(corFundo2.brighter().brighter().brighter().brighter().brighter().brighter()); 
        limpar.setBackground(corFundo2);
        limpar.setBorder(borda);

        limpar.addActionListener(new ActionListener() {            
            public void actionPerformed(ActionEvent e) {
                actionLimpar();
            }            
        });

        salvar = new JButton("Salvar");
        salvar.setFont(buttonFont);  
        salvar.setForeground(corFundo2.brighter().brighter().brighter().brighter().brighter().brighter()); 
        salvar.setBackground(corFundo2);
        salvar.setBorder(borda);      
        salvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionSalvar();
            }            
        });     

        formP.setLayout(new GridLayout(4,1,0,0));        
        formP.setBorder(BorderFactory.createEmptyBorder(40,0,10,0));      
        
     
        //-----ADDPANEL------
        JPanel form1 = new JPanel();
        form1.setOpaque(false);
        form1.add(codigolb);
        form1.add(codigotf);
        

        JPanel form2 = new JPanel();
        form2.setOpaque(false);
        form2.add(categorialb);
        form2.add(categoriacb);
        form2.add(plusB);
        

        JPanel form3 = new JPanel();
        form3.setOpaque(false);
        form3.add(descricaolb);
        form3.add(descricaotf);

        JPanel form4 = new JPanel();
        form4.setOpaque(false);
        form4.add(precolb);
        form4.add(precotf);
        

        buttonsP.setLayout(new GridLayout(1,2,90,0));
        buttonsP.setBorder(BorderFactory.createEmptyBorder(20,90,0,90)); 
        buttonsP.setOpaque(false);
        buttonsP.add(limpar);
        buttonsP.add(salvar);

        
        formP.add(form1);
        formP.add(form2);
        formP.add(form3); 
        formP.add(form4);          

        //-------WINDOW-------
        setTitle("Cadastro de Item");
        
    } 

    public void actionAddCategoria(){
        NovaCategoriaView view = new NovaCategoriaView(sView, this);
        view.setLocationRelativeTo(this);
        view.setAlwaysOnTop(true);

        view.setVisible(true);
    }

    public void actionLimpar(){
        codigotf.setText("");
        descricaotf.setText("");
        precotf.setText("");
    }

    public void actionSalvar(){

        try{
            CategoriaController controller = MainController.getCategoriaController();

            Long codigo = Long.parseLong(codigotf.getText());
            
            String descricao = new String(descricaotf.getText());
            
            if(descricao.equals("") || descricao.startsWith(" ")) {
            	throw exception.Excecoes.nomeInv;
            }
            
            Double preco = Double.parseDouble(precotf.getText());

            String nomeCat = new String(categoriacb.getSelectedItem().toString());

            controller.addItem(codigo, descricao, preco, nomeCat);
            
            this.sView.refresh(controller.tabelaServicos("Todas"), controller.listaCategorias(), "Todas");
            
            
        }
        catch(NumberFormatException e){
            JOptionPane.showMessageDialog(this, "Valor inválido.\nEntre com um valor no formato 0.00", "Erro", 0);
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

        this.dispose();

    }


	public void refresh(String[] listaCategorias) {
		
		categoriacb.removeAllItems();
		
        for (String string : listaCategorias) {
        	categoriacb.addItem(string);			
		}
		
	}

}