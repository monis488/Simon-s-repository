/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pathfinder;

import java.util.Random;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author Simon Dao
 */
public class Main extends Application{
    
    Stage stage;
    Scene scene;
    Canvas c;
    GraphicsContext gc;
    Random r = new Random();
    Grid g = new Grid();
    
    int width =605;
    int height = 605;
    int scale = 50;
    
    boolean running = true;
    
  
    static Grid[][] tilex = new Grid[12][12];
    

//GRID CLASS
public class Grid {
    
    int x =0;
    int y =0;
    
    int f =0;
    int g =0;
    int h =0;
    
    //draws grid
    public void drawGrid(GraphicsContext gc,int x,int y){
         
        this.x = x;
        this.y = y;
        
            if(r.nextDouble() < .3 && x != 5 ){
                
                gc.setFill(Color.RED);
                
            } else {
                gc.setFill(Color.WHITE);
            }
            if (x == 555 && y == 555) {
                
                gc.setFill(Color.GREEN);
            }    
            if (x == 5 && y == 5) {
                
                gc.setFill(Color.BLUE);
            }  
             
             gc.fillRect(x, y, scale-5, scale-5);
        }      
    }

    
    //METHOD WHERE EVERYTHING HAPPENS
    @Override
    public void start(Stage stage) throws Exception {
        
        StackPane layout = new StackPane();
        c = new Canvas(width,height);
        gc = c.getGraphicsContext2D();
        layout.getChildren().add(c);
        scene = new Scene(layout,width,height);
        
        setBackground(gc);
        storeCoords();
        
        new AnimationTimer(){
                long lastTick = 0;
    
           //TIMER     
                public void handle(long now){
                    
                if(running == true ){ 
                    
                    stage.setScene(scene);
                    
                    if(lastTick == 0 ){
                        lastTick = now;
                        tick(gc);
                        
                        return;
                    }
              
                    if ( now - lastTick > 1000000000/1){
                        lastTick = now;
                        tick(gc);
                      
                    }
                }
                }
            }.start(); 
     
        stage.setTitle("A star demo");
        stage.setScene(scene);
        stage.show();
       
        
    }  
    //METHOD WHERE EVERYTHING IS DRAWN
    public void tick(GraphicsContext gc){
        //implement a star algorithm
    }
    public void setBackground(GraphicsContext gc){
        // draws black background
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, width, height);
        
        //draw grid
        
        for (int y = 0;y<12;y++){
            
            for (int x = 0;x<12;x++){
                
                g.drawGrid(gc, (x*scale)+5,(y*scale)+5);
 
            }
        }
    }
    public void storeCoords(){
        for (int y = 0;y<12;y++){
            
            for (int x = 0;x<12;x++){
                g.x = x;
                g.y = y;
                
                 tilex[y][x] = new Grid();
            }
        }    
    }
    public static void main(String[] args){
        launch(args);
        int count =0;
        
        for (int i = 0; i< 12 ;i++){ 
            for (int j = 0;j<12; j++){
                System.out.println(tilex[i][i].x);
                count++;
        }
    }    
     System.out.println(count);
    }
}
