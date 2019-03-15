package Pong;

import javax.swing.JFrame;


public class Main {
    
    public static void main(String[] args){
      
        Pong pong = new Pong();
    //JFrame
        JFrame frame = new JFrame("Pong");
        frame.setBounds(10,10,700,600);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(pong);
        
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
   
        
        
    }
}
