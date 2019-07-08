/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman;

import java.util.ArrayList;
import java.util.List;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author Simon Dao
 */
public class PacMan extends Application {
    
    /*
    * Foods class will just be a class that holds a x and y location for
    * every peice of food
    */
    public static class Foods{
        
        public int x;
        public int y;
        
        public Foods(int x , int y){
            this.x = x;
            this.y = y;
            
        }
    }
    
    /*
    * This enum will contain 4 directions pacman can follow
    * these enums will be used in the switch statement
    */
    public enum direction{
        up,down,left,right;
    }
    
    
    //variables 
    int cornerSize = 25;
    int width = 700;
    int height = 700;
    int pauseCount = 0;
    public int index;
    
    //coordinates for the player
    int pX = 13;
    int pY = 8;
    
    //movement
    int speed = 5;
    static direction Dir = direction.right;
    
    //food class
    static List<Foods>  food = new ArrayList<>(); 
        
    //booleans
    boolean running = true;
        
        /*
        *  @param Stage aka the frame
        *  In a nutshell this method just starts the window and keeps the game  
        *  running
        *
        *  this method will do several things
        *  start the background music 
        *  start the VBox(window)
        *  create a canvas and get the draw methods ready
        *  start the timer that will allow the game to tick
        *  set the controls
        *  and create multiple instances of the Food class  
        */
    @Override
    public void start(Stage primaryStage) {
        
        /*
        //background sound still a work in progress...
        if(running == true){
            Media megalovania = new Media("Megalovania.mp3");
            MediaPlayer mp = new MediaPlayer(megalovania);
            mp.setAutoPlay(true);
            mp.setVolume(0.1);
        }
        */
        
       //setting up stuff
        VBox root = new VBox();
            Canvas c = new Canvas(width,height);
            GraphicsContext gc = c.getGraphicsContext2D();
            root.getChildren().add(c);
        
            new AnimationTimer(){
                long lastTick = 0;
                
           //TIMER     
                public void handle(long now){
                    
                if(running == true){    
                    if(lastTick == 0 ){
                        lastTick = now;
                        tick(gc);
                        return;
                    }
                    if ( now - lastTick > 1000000000/10){
                        lastTick = now;
                        tick(gc);
                    }
                }
                }
            }.start(); 
                
            
            
            //stage
        Scene scene = new Scene(root, width,height);
        
        //controls
        scene.addEventFilter(KeyEvent.KEY_PRESSED, key->{
            if(key.getCode() == KeyCode.W){
                Dir = direction.up;
            }
             if(key.getCode() == KeyCode.A){
               Dir = direction.left;
            }
              if(key.getCode() == KeyCode.S){
               Dir = direction.down;
            }
               if(key.getCode() == KeyCode.D){
               Dir = direction.right;
            }
                if(key.getCode() == KeyCode.ESCAPE){
                primaryStage.close();
            }
                if(key.getCode() == KeyCode.P){
                    pauseCount++;
                    
                    running = false;
                    
                    setPauseScreen(gc);
                    
                if(pauseCount % 2 == 0){
                   running = true;
               }
                
                }
        });
        
        //add food
        for (int i=0;i<10;i++){
            
        food.add(new Foods(2+i,15));
        }
        
        primaryStage.setTitle("PAC-MAN!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
        
    
        /*
        *  In a nutshell this method draws everyframe and sets the properties
        *  of the all the objects in the game
        *
        *  set collison
        *  draws everything
        *  sets movement
        *  
        */
        public void tick(GraphicsContext gc){
          
           setCollision();
            
          
          
           
           
           
             
          
            //background
          gc.setFill(Color.BLACK);
           gc.fillRect(0,0,700,700);
             
            
           
           
          setFood(gc);
               //draw pac-man
               gc.setFill(Color.YELLOW);
               gc.fillOval(pX*cornerSize, pY*cornerSize, cornerSize, cornerSize);
               
             
                
               //movement
               switch(Dir){
                   
                   case up:
                       pY--;
                       
                       gc.setFill(Color.BLACK);
               gc.fillOval((pX*cornerSize)+5, (pY*cornerSize)+15, 15, 15);
                       break;
                       
                   case down:
                       pY++;
                       
                       gc.setFill(Color.BLACK);
               gc.fillOval((pX*cornerSize)+5, (pY*cornerSize)-4, 15, 15);
                       break;
                       
                   case left:
                       pX--;
                       
                       gc.setFill(Color.BLACK);
               gc.fillOval((pX*cornerSize)+15, (pY*cornerSize)+5, 15, 15);
                       break;    
                   
                   case right:
                       pX++;
                       
                       gc.setFill(Color.BLACK);
               gc.fillOval((pX*cornerSize)-6, (pY*cornerSize)+5, 15, 15);
                       break;
                     
               }
               //outer title screen border
               gc.setFill(Color.RED);
               gc.fillRoundRect(190, 10, 320, 120,25,25);
               
               //title screen border
               gc.setFill(Color.ORANGE);
               gc.fillRoundRect(200, 20,300, 100,25,25);
               
               
               //title screen
               gc.setFill(Color.WHITE);
               gc.setFont(new Font("",50));
               gc.fillText("PAC-MAN!!!", 220, 95);
               
              //eat food
            for(int i = 0;i<food.size();i++){
               if(pX == food.get(i).x && pY == food.get(i).y){
                  eatFood();
                  //find which one got touched by pac man 
                  
                  
               }
              
           }
               
        }
        
        //pretty seld explainitory
          public void setCollision(){
    //collision
            if(pX < 0){
                pX++;
            }
            if(pX > 27){
                pX-- ;
            }
            if(pY < 6){
                pY++;
            }
            if(pY > 27){
                pY--;
            }
        
                
    }
          
        /*
        *  this method goes through the food list and draws a little peice of food
        *  for every element
        */  
          public void setFood(GraphicsContext gc){
              
              for(Foods f: food){
                  
              //draw food
               gc.setFill(Color.WHITE);
               gc.fillOval((f.x*cornerSize)+16, (f.y*cornerSize)+8, (cornerSize/2)-2, (cornerSize/2)-2);
              }
          } 
          
        /*
        *  just draws a little pause screen  
        */  
           public void setPauseScreen(GraphicsContext gc){
             
               if(running == false){
                   
               gc.setFill(Color.RED);
               gc.setFont(new Font("Courier New",50));
               gc.fillText("GAME PAUSED", 190, 305);
               }
           }
        public void eatFood(){
            food.remove(index);
            
        }
    
    
   
    public static void main(String[] args) {
        launch(args);
    }
    
}
