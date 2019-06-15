 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Snake_Code;



import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javafx.animation.AnimationTimer;



import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author Gamer
 */
public class Main extends Application {
    
    //variables
    static int speed = 5;
    static int foodColor = 0;
    public static int width = 20;
    public static int height = 20;
    public static int foodX = 0;
    public static int foodY = 0;
    static int cornerSize =25;
    static Random rand = new Random();
    
    //List class takes in class as a param
    static List<Corner> snake = new ArrayList<>();
    static Dir direction = Dir.left;
    private boolean gameOver = false;
    
    public enum Dir{
        left,right,up,down;
    }
    
    
    
    
    
    public static class Corner{
    int x;
    int y;
    
    public Corner(int x , int y){
        this.x = x;
        this.y = y;
        
    }
}
    
    
    /*
    * @param Stage primaryStage
    *
    *  border pane layout 
    *  gets css class called "application.css"
    *  then the scene is set and then showed
    *  
    *
    */
    
   
    public void start(Stage primaryStage) throws Exception {
        
        try{
            newFood();
            
            VBox root = new VBox();
            Canvas c = new Canvas(width*cornerSize, height*cornerSize);
            GraphicsContext gc = c.getGraphicsContext2D();
            root.getChildren().add(c);
            
            new AnimationTimer(){
                long lastTick = 0;
                
                public void handle(long now){
                    if(lastTick == 0 ){
                        lastTick = now;
                        tick(gc);
                        return;
                    }
                    if ( now - lastTick > 1000000000/speed){
                        lastTick = now;
                        tick(gc);
                    }
                }
            }.start(); 
                
                
            
                    
            
            
            Scene scene = new Scene(root,width*cornerSize,height*cornerSize);
            
            //controls
            scene.addEventFilter(KeyEvent.KEY_PRESSED, key->{
                if(key.getCode() == KeyCode.W){
                    direction = Dir.up;
                }
                if(key.getCode() == KeyCode.A){
                    direction = Dir.left;
                }
                if(key.getCode() == KeyCode.S){
                    direction = Dir.down;
                }
                if(key.getCode() == KeyCode.D){
                    direction = Dir.right;
                }
            });
            
            //add snake parts
            
            snake.add(new Corner(width/2,height/2));
            snake.add(new Corner(width/2,height/2));
            snake.add(new Corner(width/2,height/2));
            
            
          
            primaryStage.setScene(scene);
            primaryStage.setTitle("snake!");
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    //GAME LOOP
    public void tick(GraphicsContext gc ){
        if(gameOver){
            gc.setFill(Color.RED);
            gc.setFont(new Font("",50));
            gc.fillText("GAMEOVER", 100, 250);
            return;
        }
        for(int i = snake.size()-1 ;i>=1 ; i--){
            snake.get(i).x = snake.get(i-1).x;
            snake.get(i).y = snake.get(i-1).y;
        }
        
        switch(direction){
            case up:
                snake.get(0).y--;
                if(snake.get(0).y < 0){
                gameOver = true;
            }
                break;
            case down:
                snake.get(0).y++;
                if(snake.get(0).y > height){
                gameOver = true;
                }
                break;
            case left:
                snake.get(0).x--;
                if(snake.get(0).x < 0){
                gameOver = true;
                }
                break;
            case right:
                snake.get(0).x++;
                if(snake.get(0).x > width){
                gameOver = true;    
                }
                break;  
        }
        //eat food
        if(foodX == snake.get(0).x && foodY == snake.get(0).y){
            snake.add(new Corner(-1,-1));
            newFood();
        }
        //suicide
           for(int i =1;i<snake.size();i++){
    if (snake.get(0).x == snake.get(i).x && snake.get(0).y == snake.get(i).y){
        gameOver = true;
    }
}
           //fill
           //background
           gc.setFill(Color.BLACK);
           gc.fillRect(0,0,width*cornerSize,height*cornerSize);
           
           //score board
           gc.setFill(Color.WHITE);
           gc.setFont(new Font("",30));
           gc.fillText("Score:",+(speed-6),10,30);
    
        //random foor color
        Color cc = Color.WHITE;
        
        switch(foodColor){
            case 0: cc = Color.RED;
            break;
            case 1: cc = Color.ORANGE;
            break;
            case 2: cc = Color.YELLOW;
            break;
            case 3: cc = Color.GREEN;
            break;
            case 4: cc = Color.BLUE;
            break;
            case 5: cc = Color.PURPLE;
            break;
    }    
       gc.setFill(cc);
       gc.fillOval(foodX*cornerSize, foodY*cornerSize, cornerSize, cornerSize);

       //snake 
       for(Corner c:snake){
           gc.setFill(Color.LIMEGREEN);
           gc.fillRect(c.x*cornerSize, c.y*cornerSize, cornerSize-1, cornerSize-1);
           gc.setFill(Color.LIMEGREEN);
           gc.fillRect(c.x*cornerSize, c.y*cornerSize, cornerSize-2, cornerSize-2);
       }
}
    
    /*
    * makes the food re appear after the snake's head has 
    * touched it in a random location
    *
    */
    public static void newFood(){
        start: while(true){
            foodX = rand.nextInt(width);
            foodY = rand.nextInt(height);
            
            for(Corner c : snake){
                if (c.x == foodX && c.y == foodY){
                    continue start;
            }
        }
            foodColor = rand.nextInt(5);
            speed++;
            break;        
    }
    }
    /*
    *  the main method will take in all the code as the param "args" and run it
    */
    public static void main(String[] args){
        launch(args);
    }
}
