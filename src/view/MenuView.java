package view;

import java.awt.BorderLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

public class MenuView extends DefaultView {

    private static final long serialVersionUID = 7647168939L;
    
    private JPanel logo;

    private JLabel lhotel;

    public MenuView(){
        super();
        this.initialize();
    }

    public void initialize(){

        //-------CENTER--------
        lhotel = new JLabel();        
        lhotel.setSize(220,200);        
        ImageIcon test6 = new ImageIcon("src/imagens/Sem título-3-01.png");
        Image dtest6 = test6.getImage().getScaledInstance(lhotel.getWidth(), lhotel.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon hotel= new ImageIcon(dtest6);
        lhotel.setIcon(hotel);    
        lhotel.setHorizontalAlignment(SwingConstants.CENTER);

        logo = new JPanel(new BorderLayout());
        logo.setOpaque(false);
        logo.add(lhotel, BorderLayout.CENTER);
        fundoP.add(logo, BorderLayout.CENTER);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        //-------WINDOW-------
        setTitle("Menu");

    }

}