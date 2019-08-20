/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pathfinder.attempt.pkg2;

import java.util.List;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author Simon Dao
 * 
 * TODO 
 * : add randomly generated maps
 * :add start and end node
 */
public class Main extends Application
{   
    private int width = 601;
    private int height = 601;
 
    private boolean running = true;
    
    GraphicsContext gc;
    Game game = new Game();
    
    //temp
    List<Node> path;
    
    public int getWidth()
    {
        return width;
    }
    public int getHeight()
    {
        return height;
    }
    
    @Override
    public void start(Stage stage) throws Exception 
    {
        //set up scene 
        StackPane layout = new StackPane();
        Canvas c = new Canvas(width,height);
        gc = c.getGraphicsContext2D();
        layout.getChildren().add(c);
        Scene scene = new Scene(layout,width,height);
        
        // START
        Game game = new Game();
        game.print();
        game.map.drawMap(gc, path);
        
        
        new AnimationTimer(){
            long lastTick = 0;
    
           //TIMER     
            public void handle(long now){
                    
                if(running == true ){ 
                    
                    stage.setScene(scene);
                    
                    if(lastTick == 0 ){
                        lastTick = now;
                        game.update(gc);
                        return;
                    }
              
                    if ( now - lastTick > 1000000000/60){
                        lastTick = now;
                        game.update(gc);
                    }
                }
            }
        }.start(); 
     
        stage.setTitle("A star demo");
        stage.setScene(scene);
        stage.show();
        
    }
  
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        launch(args);
    
    }
    
}
