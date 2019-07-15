/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package the.matrix.demo;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author Simon Dao
 */
public class TheMatrixDemo extends Application {
    
    //variables
    int width = 600;
    int height = 600;
    boolean running = true;
    GraphicsContext gc;
    Scene scene;
    String title = "template";
    
    
    @Override
    public void start(Stage primaryStage) {
      
        
            
    
        
        StackPane layout = new StackPane();
        Canvas c = new Canvas(width,height);
        GraphicsContext gc = c.getGraphicsContext2D();
        layout.getChildren().add(c);
        Scene scene = new Scene(layout, width, height);
        
        scene.addEventFilter(KeyEvent.KEY_PRESSED, keys ->{
        if(keys.getCode() == KeyCode.ESCAPE){
            primaryStage.close();
        }   
        });
        
        new AnimationTimer(){
                long lastTick = 0;
                
           //TIMER     
                public void handle(long now){
                    
                if(running == true ){ 
                    
                    primaryStage.setScene(scene);
                    
                    if(lastTick == 0 ){
                        lastTick = now;
                        tick(gc);
                        
                        return;
                    }
                    if ( now - lastTick > 1000000000/60){
                        lastTick = now;
                        tick(gc);
                      
                    }
                }
                }
            }.start(); 
        
        primaryStage.setTitle(title);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void tick(GraphicsContext gc){
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, width, width);
        
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
