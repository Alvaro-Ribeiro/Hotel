package view;


import java.awt.Dimension;
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
import javax.swing.JSpinner;
import javax.swing.JTextField;
import controller.AcomodacaoController;
import controller.MainController;
import model.Acomodacao.EEstadoOcupacao;


public class NovaAcomodacaoView extends DefaultMiniView{

    private static final long serialVersionUID = 8146643512L;

    private JLabel numerolb;
    private JTextField numerotf;
    private JLabel tipolb;  
    private JLabel ocupMaxlb;
    private JSpinner ocupMaxS;    
    private JButton plusB;
    private JLabel statuslb;
    
    private JButton limpar;
    private JButton salvar;    

    private JComboBox <String> statuscb;
    private JComboBox <String> tipocb;
    
    private AcomodacoesView acView;

    public NovaAcomodacaoView(String[] tipos, AcomodacoesView acomodacao){
    	super();
    	this.acView = acomodacao;
        initialize(tipos);
    }

    
    public void initialize(String[] tipos){    
              
        
        //-------TITULO-----------        
        titulo.setText("NOVA ACOMODAÇÃO");
        
        //-------FORMULARIO---  
        numerolb = new JLabel("Número: ");  
        numerolb.setForeground(corTexto);         
        numerolb.setFont(textFont); 

        numerotf = new JTextField(); 
        numerotf.setFont(textfFont);               
        numerotf.setColumns(9);   

        tipolb = new JLabel("Tipo: ");
        tipolb.setForeground(corTexto);         
        tipolb.setFont(textFont); 
        
        tipocb = new JComboBox<>(tipos);                 
        tipocb.setFont(textfFont);  
        tipocb.setPreferredSize(new Dimension(200,30));      

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
                actionAddTipoAcomodacao();
            }            
        });

        
        ocupMaxlb = new JLabel("Ocupação Máxima: ");
        ocupMaxlb.setForeground(corTexto);
        ocupMaxlb.setFont(textFont);

        ocupMaxS = new JSpinner();
        ocupMaxS.setModel(new javax.swing.SpinnerNumberModel(0,0,null,1 ));       
        ocupMaxS.setPreferredSize(new Dimension(60,30));
        ocupMaxS.setFont(textFont);

        statuslb = new JLabel("Status: ");
        statuslb.setForeground(corTexto);
        statuslb.setFont(textFont);
        
        String[] quartoStatus = { "Dispon\u00EDvel","Ocupado", "Manutenção"};
        statuscb = new JComboBox<>(quartoStatus);                 
        statuscb.setFont(textfFont);       
     
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
            public void actionPerformed(ActionEvent e) {
                actionSalvar();
            }            
        });
        
        
        
        //-------PANELS----   
        formP.setLayout( new GridLayout(4,1,0,0));        
        formP.setBorder(BorderFactory.createEmptyBorder(40,0,10,0));         
     

        //-----ADDPANEL------
        JPanel form1 = new JPanel();
        form1.setOpaque(false);
        form1.add(numerolb);
        form1.add(numerotf);
        

        JPanel form2 = new JPanel();
        form2.setOpaque(false);
        form2.add(tipolb);
        form2.add(tipocb);
        form2.add(plusB);
        

        JPanel form3 = new JPanel();
        form3.setOpaque(false);
        form3.add(ocupMaxlb);
        form3.add(ocupMaxS);

        JPanel form4 = new JPanel();
        form4.setOpaque(false);
        form4.add(statuslb);
        form4.add(statuscb);
        

        buttonsP.setLayout(new GridLayout(1,2,90,0));
        buttonsP.setBorder(BorderFactory.createEmptyBorder(20,90,0,90)); 
        buttonsP.add(limpar);
        buttonsP.add(salvar);

        
        formP.add(form1);
        formP.add(form2);
        formP.add(form3); 
        formP.add(form4);          

        //-------WINDOW-------
        setTitle("Cadastro de Acomodacao");        
        
    } 

    public void actionAddTipoAcomodacao(){
        NovoTipoAcomodacaoView view = new NovoTipoAcomodacaoView(this);
        view.setLocationRelativeTo(this);
        view.setAlwaysOnTop(true);

        view.setVisible(true);
    }

    public void actionLimpar(){
        numerotf.setText("");
        ocupMaxS.setValue(0);        

    }

    public void actionSalvar(){

        try{
            AcomodacaoController controller = MainController.getAcomodacaoController();

            Integer numero = Integer.parseInt(numerotf.getText());

            String nomeTipo = new String(tipocb.getSelectedItem().toString());
            
            Integer ocupMax = Integer.parseInt(ocupMaxS.getValue().toString());

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
            
            controller.addAcomodacao(numero, ocupMax, nomeTipo, estado);

            this.acView.refresh(controller.tabelaAcomodacoes());

        }
        catch(NumberFormatException e){
            JOptionPane.showMessageDialog(this, "Numero da Acomodacao ou Ocupação máxima inválido(a)\nEntre com um número válido", "Erro", 0);
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


	public void refresh(String[] listaTipoAcomodacoes) {
		
		tipocb.removeAllItems();
		
        for (String string : listaTipoAcomodacoes) {
        	tipocb.addItem(string);			
		}
		
	}
    
    
}
