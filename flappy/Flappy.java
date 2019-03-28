
package flappy;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.Timer;


public class Flappy implements ActionListener,KeyListener{
    
    public final int delay = 10;
    public static Flappy flappy;
    public Renderer render;
    
    public int width = 800, height = 800;
    
    public int playerX = 200;
    public int playerY = height/2-10;
    public int gravity = -1;

 public Flappy(){
     
     
     JFrame frame = new JFrame("Flappy Bird");  
     Timer timer = new Timer(delay,this);
     render = new Renderer();
     frame.add(render);
     frame.setSize(width,height);
     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     frame.setResizable(false);
     frame.setLocationRelativeTo(null);
     frame.setVisible(true);
     
     
     Rectangle bird = new Rectangle(width/2 -10,height/2 - 10,20,20); 
      
     
     
      timer.start();
    }
 @Override
    public void actionPerformed(ActionEvent e) {
        
        render.repaint();
    }
     public void repaint(Graphics g) {
   //COLORS      
      Color sky = new Color(96,179,242);
      Color bushes = new Color(136,229,142);
      Color grass = new Color(136,201,0);
      Color ground = new Color(218,217,150);
      Color colum = new Color(116,191,46);
      Color sun = new Color(241,220,78);
      Color beak = new Color(231,121,51);
      Color wing = new Color(248,186,48);
      
     // System.out.println("FRAME RELOADING");
      //SKY BACKGROUND 
      g.setColor(sky);
      g.fillRect(1, 1, width, height);
      //BUSHES BACKGROUND
      g.setColor(bushes);
      g.fillRect(1, 550, width, height);
      //BUSHES BACKGROUND
      g.setColor(bushes);
      g.fillOval(1, 500, 500, 100);
      //BUSHES BACKGROUND
      g.setColor(bushes);
      g.fillOval(400, 500, 500, 100);
      //GROUND BACKGROUND
      g.setColor(ground);
      g.fillRect(1,600,width,height);
      //GROUND
      g.setColor(grass);
      g.fillRect(1, 600, width, 25);
      
      //BIRD render
      g.setColor(Color.yellow);
      g.fillOval(playerX,playerY,40, 30);
      
        g.setColor(beak);
      g.fillOval(playerX+25,playerY+12,25, 15);
      
         g.setColor(Color.white);
      g.fillOval(playerX+20,playerY-2,15, 17);
      
          g.setColor(Color.black);
      g.drawOval(playerX+20,playerY-2,15, 17);
      
          g.setColor(Color.black);
      g.fillOval(playerX+25,playerY-1,10, 10);
      
       g.setColor(wing);
      g.fillOval(playerX-8,playerY+14,25, 15);
      
           g.setColor(Color.white);
      g.drawOval(playerX-8,playerY+14,25, 15);
      
    
    //SUN  
      g.setColor(sun);
      g.fillOval(670, -50, 200, 200);
      
playerX = playerX-gravity;
 
    }
  
        
    public static void main(String[] args) {
     flappy = new Flappy();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
       
    }

   

    
 
          
    
   }
