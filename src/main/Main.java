package main;

import java.awt.EventQueue;

import controller.MainController;
import view.MenuView;

public class Main {

    public static void main(String argv[]){

        EventQueue.invokeLater(new Runnable(){

            public void run(){

                try{
                    MainController.load();
                    MenuView menu = new MenuView();
                    menu.setVisible(true);

                }
                catch(Exception e){
                    e.printStackTrace();
                }

            }

        });

    }
    
}
