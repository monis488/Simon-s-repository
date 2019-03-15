package Pong;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Pong extends JPanel implements KeyListener, ActionListener{
    
    private boolean play = false;
    private double score = 0;
    private double score2 = 0;
    
    private int totalBricks = 21;
    private Timer timer;
    private int delay = 0;
    
    private int playerX;
    private int playerY=255;
    
    //AI
    private int AIplayerX = 642 ;
    private int AIplayerY = 255;
    
    //BALL 1
    private int ballposY = 290;
    private int ballposX = 350;
    private int ballXdir = -1;
    private int ballYdir = -2;
  
    public Pong(){
      
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start();
    }
    

    
    
    
    
    //CREATING GAME COMPONENTS
    public void paint(Graphics g){
    //BACKGROUND
        g.setColor(Color.green);
        g.fillRect(1, 1, 692, 592);
        
    //BORDERS 
        g.setColor(Color.white);
        g.fillRect(0,0,3,592);
        g.fillRect(0,0,692,3);
        g.fillRect(691,0,3,592);
        g.fillRect(0, 567, 691, 5);
        
        g.setColor(Color.white);
        g.fillRect(355, 55, 10, 540);
        
        
        g.setColor(Color.white);
        g.drawOval(260, 200, 200, 200);
        
      
       
        
    //PADDLE 
        g.setColor(Color.blue);
        g.fillRect(50,playerY,8,100);
        
        //AI
         g.setColor(Color.red);
        g.fillRect(AIplayerX,AIplayerY,8,100); 
     //SCORE  
    g.setColor(Color.white);
    g.setFont(new Font("Arial",1,30));
    g.drawString(""+score, 280, 80);
    
    g.setColor(Color.white);
    g.setFont(new Font("Arial",1,30));
    g.drawString(""+score2, 401, 80);
    
     g.setColor(Color.white);
    g.setFont(new Font("TimesRoman",1,50));
    g.drawString("PONG", 291, 50);
    
    //BALL
        g.setColor(Color.black);              
        g.fillOval(ballposX ,ballposY ,20,20);
        
        g.dispose();
        
        
    }
     
    
    //USELESS METHOD
    @Override
    public void keyTyped(KeyEvent e) {
      
    }
    //USELESS METHODS
    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    
    //CONTROLS  
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_S){
                 if(playerY >= 467){
                     playerY =467;
                 } else {
                     this.moveUp();
                 }        
         }
         if(e.getKeyCode()==KeyEvent.VK_W){
               if(playerY < 10){
                     playerY =3;
                 } else {
                     this.moveDown();  
                 }                
       
         }  
          if(e.getKeyCode()==KeyEvent.VK_UP){
                 if(AIplayerY < 10){
                     AIplayerY =3;
                 } else {
                     this.AImoveUp();
                 }        
         }
         if(e.getKeyCode()==KeyEvent.VK_DOWN){
               if(AIplayerY >= 467){
                     AIplayerY =467;
                 } else {
                     this.AImoveDown();
                 }                
  }
    }  

    private void AImoveUp() {
       play = true;
       AIplayerY-=20;
    }

    private void AImoveDown() {
        play = true;
       AIplayerY+=20;
        }
    

    private void moveUp() {
       play = true;    
       playerY +=20;
    }
    private void moveDown() {
       play = true;
       playerY -=20;
    }
      @Override
    public void actionPerformed(ActionEvent e) {
         timer.start();
         //BALL
         if(play){
          
             }
             if(new Rectangle(ballposY,ballposX,20,20).intersects(new Rectangle(playerY,0,100,60))){
                 ballYdir = -ballYdir;
             } 
             
             ballposY += ballXdir;
             ballposX += ballYdir;
             
             if(ballposX < 0) { 
                 ballYdir = -ballYdir;
             }
             if(ballposY < 0) {
                 ballXdir = -ballXdir;
             }
             if(ballposX > 670) {
                 ballYdir = -ballYdir;
             }
             if(ballposY > 550){
                 ballXdir = -ballXdir;
             }
             if(ballposX ==0 ){
                 score = score +.5;    
             }
             if(ballposX == 672 ){
                 score2 = score +.5 -.5;
             }
             //AI 
            
             if(new Rectangle(ballposY,ballposX,20,20).intersects(new Rectangle(AIplayerY,640,100,60))){
             
               ballYdir = -ballYdir;
             
               
            }
            if (ballposX > 200){ 
             
              if(AIplayerY +50> ballposY){
                 AImoveUp();
             }
              if(AIplayerY+50 < ballposY){
                 AImoveDown();
             }
             //AI FOR BLUE PLAYER
            }
            if (ballposX < 400){ 
             
              if(playerY +50< ballposY){
                 moveUp();
             }
              if(playerY+50 > ballposY){
                 moveDown();
             }
             if(playerY <1){
                playerY =playerY+20;
             }
             if(playerY >470){
                playerY =playerY-20;
            } 
    }
           
         repaint();

   
        }
    }


