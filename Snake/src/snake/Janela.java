package snake;

import javax.swing.*;

public class Janela extends JFrame {

    public Janela(){

        JFrame jframe = new JFrame("Snake");
        jframe.setSize(802,808);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setVisible(true);
        jframe.setLocationRelativeTo(null);
        
        jframe.setResizable(false);
        Painel painel = new Painel();
        
        jframe.addKeyListener(painel);
        
        jframe.add(painel);

    }

}
