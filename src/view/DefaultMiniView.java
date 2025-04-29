package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.RenderingHints;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.Border;

public class DefaultMiniView extends JFrame{   
    
    private static final long serialVersionUID = -5913678817L;    

    protected JPanel backpanel;
    protected Color corFundo;
    protected Color corFundo2;
    protected JPanel fundoP;
    protected JPanel buttonsP;
    
    protected JLabel titulo; 
    protected JPanel formP;    
    protected Border borda;
    protected Font tituloFont;
    protected Font textFont;
    protected Font textfFont;
    protected Font buttonFont;  
    protected Color corTexto;

    public DefaultMiniView(){
        init();
    }
    
    public void init(){  
        tituloFont = new Font("Helvetica", Font.PLAIN, 50);        
        textFont = new Font("Helvetica", Font.PLAIN, 25);
        textfFont = new Font("Helvetica", Font.PLAIN, 23);
        buttonFont = new Font("Helvetica", Font.PLAIN, 35);
        corTexto = Color.WHITE;
        corFundo = new Color(70,100,210).darker().darker().darker();
        corFundo2 = new Color(200,255,150).darker().darker().darker();
        borda = BorderFactory.createLineBorder(corFundo2.brighter());           
        
        
        //-------TITULO-----------
        titulo = new JLabel ();        
        titulo.setFont(tituloFont);
        titulo.setForeground(Color.WHITE.darker().darker());
        titulo.setHorizontalAlignment(SwingConstants.CENTER); 
        
        //-------PANELS----
        backpanel = new JPanel(new BorderLayout()){
        private static final long serialVersionUID = 6116078705L;
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
        backpanel.setBorder(BorderFactory.createEmptyBorder(60,60,50,60));

        fundoP = new JPanel(new BorderLayout());
        fundoP.setOpaque(false);            
        
        
        formP = new JPanel();  
        formP.setOpaque(false);        
        

        buttonsP = new JPanel(); 
        buttonsP.setOpaque(false);               

        fundoP.add(titulo, BorderLayout.NORTH);
        fundoP.add(formP, BorderLayout.CENTER);
        fundoP.add(buttonsP, BorderLayout.SOUTH);
        

        backpanel.add(fundoP);
        

        add(backpanel);

        //-------WINDOW-------
        setTitle("");
        setSize(900, 600);
        setMinimumSize(new Dimension(900,600));
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
        
    } 


    
}