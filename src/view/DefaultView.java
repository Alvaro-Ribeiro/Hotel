package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import controller.AcomodacaoController;
import controller.CategoriaController;
import controller.HospedagemController;
import controller.MainController;


public class DefaultView extends JFrame {

    private static final long serialVersionUID = 8119789090L;
    
    protected JPanel backpanel;
    protected JPanel fundoP;
    protected JPanel centerP;
    private JPanel menuBarP;
    
    private Color corFundo;
    private Color corFundo2;

    protected Font textFont;

    private JButton lhotelary;

    private JButton bacomodacoes;
    private JButton bhospedagens;
    private JButton bservico;
    private JButton bhistorico;

    public DefaultView(){
        this.init();
    }

    public void init(){       
        
        corFundo = new Color(70,100,210).darker().darker().darker();
        corFundo2 = new Color(200,255,150).darker().darker().darker();
        textFont = new Font("Helvetica", Font.PLAIN, 25);

        //-------MENU ITENS----     
        lhotelary = new JButton();          
        lhotelary.setSize(220,70);
        lhotelary.setOpaque(false);
        lhotelary.setContentAreaFilled(false);
        lhotelary.setBorderPainted(false);
        ImageIcon test = new ImageIcon("src/imagens/hotelary-01.png");
        Image dtest = test.getImage().getScaledInstance(lhotelary.getWidth(), lhotelary.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon hotelary = new ImageIcon(dtest);
        lhotelary.setIcon(hotelary);
        lhotelary.setHorizontalAlignment(SwingConstants.CENTER); 
        lhotelary.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				actionMenu();
			}
		});
        
        
        
        bacomodacoes = new JButton();
        bacomodacoes.setHorizontalAlignment(SwingConstants.LEFT);
        bacomodacoes.setSize(220, 50);
        bacomodacoes.setOpaque(false);
        bacomodacoes.setContentAreaFilled(false);
        bacomodacoes.setBorderPainted(false);        
        ImageIcon test2 = new ImageIcon("src/imagens/house.png");
        Image dtest2 = test2.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        ImageIcon casa = new ImageIcon(dtest2);
        bacomodacoes.setIcon(casa);
        bacomodacoes.setText("Acomodações");
        bacomodacoes.setForeground(Color.WHITE);
        bacomodacoes.setFont(textFont);
        bacomodacoes.setIconTextGap(20);
        bacomodacoes.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				actionBacomodacoes();
			}
		});
        
        bhospedagens = new JButton();
        bhospedagens.setHorizontalAlignment(SwingConstants.LEFT);
        bhospedagens.setSize(220, 50);
        bhospedagens.setOpaque(false);
        bhospedagens.setContentAreaFilled(false);
        bhospedagens.setBorderPainted(false);        
        ImageIcon test3 = new ImageIcon("src/imagens/bed.png");
        Image dtest3 = test3.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        ImageIcon cama = new ImageIcon(dtest3);
        bhospedagens.setIcon(cama);
        bhospedagens.setText("Hospedagens");
        bhospedagens.setForeground(Color.WHITE);
        bhospedagens.setFont(textFont);
        bhospedagens.setIconTextGap(20);
        bhospedagens.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				actionBhospedagens();
			}
		});
        
        bservico = new JButton();
        bservico.setHorizontalAlignment(SwingConstants.LEFT);
        bservico.setSize(220, 50);
        bservico.setOpaque(false);
        bservico.setContentAreaFilled(false);
        bservico.setBorderPainted(false);        
        ImageIcon test4 = new ImageIcon("src/imagens/cartServico.png");
        Image dtest4 = test4.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        ImageIcon cart = new ImageIcon(dtest4);
        bservico.setIcon(cart);
        bservico.setText("Serviços");
        bservico.setForeground(Color.WHITE);
        bservico.setFont(textFont);
        bservico.setIconTextGap(20);
        bservico.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				actionBservicos();
			}
		});
        
        bhistorico = new JButton();
        bhistorico.setHorizontalAlignment(SwingConstants.LEFT);
        bhistorico.setSize(220, 50);
        bhistorico.setOpaque(false);
        bhistorico.setContentAreaFilled(false);
        bhistorico.setBorderPainted(false);        
        ImageIcon test5 = new ImageIcon("src/imagens/historico.png");
        Image dtest5 = test5.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        ImageIcon historico = new ImageIcon(dtest5);
        bhistorico.setIcon(historico);
        bhistorico.setText("Histórico");
        bhistorico.setForeground(Color.WHITE);
        bhistorico.setFont(textFont);
        bhistorico.setIconTextGap(20);
        bhistorico.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				actionBhistorico();
			}
		});
        
        //-------PANELS----
        backpanel = new JPanel(new BorderLayout()){
            private static final long serialVersionUID = -7874865542L;
            
            @Override
            protected void paintComponent(Graphics grphcs) {

                super.paintComponent(grphcs);
                Graphics2D g2d = (Graphics2D) grphcs;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
                GradientPaint gp = new GradientPaint(0, 0, corFundo, 0, getHeight(), corFundo2);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight()); 
            }
        };     

        fundoP = new JPanel(new BorderLayout());
        fundoP.setOpaque(false); 

        menuBarP = new JPanel(new GridLayout( 8 , 1 , 0 , 5 )){
        private static final long serialVersionUID = -94451439L;

            @Override
            protected void paintComponent(Graphics grphcs) {
                super.paintComponent(grphcs);
                Graphics2D g2d = (Graphics2D) grphcs;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);
                GradientPaint gp = new GradientPaint(-190, 0, corFundo, 0, getHeight(), corFundo2);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight()); 
            }
        }; 
        menuBarP.setBorder(BorderFactory.createEmptyBorder(30,5,10,0));

        centerP = new JPanel(new BorderLayout());
        centerP.setBorder(BorderFactory.createEmptyBorder(30, 15, 0, 0));
        centerP.setOpaque(false);

        //-------ADDPANEL-----      
        menuBarP.add(lhotelary);
        menuBarP.add(bacomodacoes);
        menuBarP.add(bhospedagens);
        menuBarP.add(bservico);
        menuBarP.add(bhistorico);       

        fundoP.add(menuBarP, BorderLayout.WEST);
        fundoP.add(centerP, BorderLayout.CENTER);
        backpanel.add(fundoP);
        add(backpanel);
        
        //------WINDOW
        setSize(1080, 720);
        setMinimumSize(new Dimension(1080,720));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionMenu() {
    	MenuView view = new MenuView();
        view.setLocation(this.getX(), this.getY());
        view.setSize(this.getWidth(), this.getHeight());
        view.setVisible(true);

        this.dispose();
    }
    
    public void actionBacomodacoes(){
        AcomodacaoController controller = MainController.getAcomodacaoController();
        AcomodacoesView acView = new AcomodacoesView(controller.tabelaAcomodacoes());
        acView.setLocation(this.getX(), this.getY());
        acView.setSize(this.getWidth(), this.getHeight());
        acView.setVisible(true);

        this.dispose();
    }
    
    public void actionBhospedagens(){
        HospedagemController controller = MainController.getHospedagemController();
        HospedagensView hospView = new HospedagensView(controller.tabelaHospedagens());
        hospView.setLocation(this.getX(), this.getY());
        hospView.setSize(this.getWidth(), this.getHeight());
        hospView.setVisible(true);

        this.dispose();
    }

    public void actionBservicos(){
        CategoriaController controller = MainController.getCategoriaController();
        ServicosView servView = new ServicosView(controller.tabelaServicos(new String("Todas")), controller.listaCategorias());
        servView.setLocation(this.getX(), this.getY());
        servView.setSize(this.getWidth(), this.getHeight());
        servView.setVisible(true);

        this.dispose();
    }

    public void actionBhistorico(){
        HospedagemController controller = MainController.getHospedagemController();
        HistoricoView histView = new HistoricoView(controller.tabelaHistorico());
        histView.setLocation(this.getX(), this.getY());
        histView.setSize(this.getWidth(), this.getHeight());
        histView.setVisible(true);

        this.dispose();
    }

}
