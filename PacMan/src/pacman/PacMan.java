/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman;

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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author Gamer
 */
public class PacMan extends Application {
    
    public enum direction{
        up,down,left,right;
    }
    
    
    //variables 
    int cornerSize = 25;
    int width = 700;
    int height = 700;
    
    //coordinates
    int pX = 13;
    int pY = 8;
    
    //movement
    int speed = 5;
    static direction Dir = direction.right;
    
    @Override
    public void start(Stage primaryStage) {
        
       //setting up stuff
        VBox root = new VBox();
            Canvas c = new Canvas(width,height);
            GraphicsContext gc = c.getGraphicsContext2D();
            root.getChildren().add(c);
        
            new AnimationTimer(){
                long lastTick = 0;
                
           //TIMER     
                public void handle(long now){
                    if(lastTick == 0 ){
                        lastTick = now;
                        tick(gc);
                        return;
                    }
                    if ( now - lastTick > 1000000000/15){
                        lastTick = now;
                        tick(gc);
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
        });
        
        
        primaryStage.setTitle("PAC-MAN!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
        
        public void tick(GraphicsContext gc){
            
            //collision
            if(pX > 0){
                pX++;
            }
            if(pX < width){
                pX-- ;
            }
            if(pY < 0){
                pY++;
            }
            if(pY > height){
                pY++;
            }
            
            
            
            //background
          gc.setFill(Color.BLACK);
           gc.fillRect(0,0,700,700);
           
          
               //draw pac-man
               gc.setFill(Color.YELLOW);
               gc.fillOval(pX*cornerSize, pY*cornerSize, cornerSize, cornerSize);
               
             
                
               //movement
               switch(Dir){
                   
                   case up:
                       pY--;
                       
                       gc.setFill(Color.WHITE);
               gc.fillOval((pX*cornerSize)+5, (pY*cornerSize)+25, 5, 5);
                       break;
                       
                   case down:
                       pY++;
                       
                       gc.setFill(Color.WHITE);
               gc.fillOval((pX*cornerSize)+5, (pY*cornerSize)-7, 5, 5);
                       break;
                       
                   case left:
                       pX--;
                       
                       gc.setFill(Color.WHITE);
               gc.fillOval((pX*cornerSize)+25, (pY*cornerSize)+5, 5, 5);
                       break;    
                   
                   case right:
                       pX++;
                       
                       gc.setFill(Color.WHITE);
               gc.fillOval((pX*cornerSize)-3, (pY*cornerSize)+5, 5, 5);
                       break;
                     
               }
               //title screen border
               gc.setFill(Color.ORANGE);
               gc.fillRect(200, 100,300, 100);
               
               //title screen
               gc.setFill(Color.WHITE);
               gc.setFont(new Font("",50));
               gc.fillText("PAC-MAN!!!", 220, 160);
               
               //work in progress
               gc.setFill(Color.WHITE);
               gc.setFont(new Font("",20));
               gc.fillText("Still a work in progress!", 10,600);
        }

           
           
        
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
