/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main_Package;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author Simon Dao
 */
public class Main extends Application 
{
    //variables
    private final int width = 700;
    private final int height = 700;
    private final int velocity = 1;
    
    private final String title = "Pac Man!!!";
    
    private boolean running = true;
    private GraphicsContext gc;
    private Scene scene;
    
    private Player pacMan = new Player();
    private Control control = new Control();
    
    // red green yellow color for the map
    private int r = 2;
    private int g = 3;
    private int y = 4;
            
    private enum Direction {up,down,left,right}
    private Direction dir = Direction.right;

    private int[][] GOOGLE_MAP = 
    {               //12
                   {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                   {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                   {1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                   {1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                   {1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                   {1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                   {1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                   {1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                   {1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                   {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1},
                   {1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                   {0,0,0,0,0,0,0,0,0,0,0,0,y,0,1,1,1,1,0,g,g,g,0,r,r,r,r,0},
                   //5
                   {0,1,1,1,1,0,0,0,0,0,0,0,y,0,1,0,0,1,0,g,0,0,0,r,0,0,0,0},
                   {0,1,0,0,0,0,r,r,r,0,y,y,y,0,1,0,1,1,0,g,0,0,0,r,r,r,0,0},
                   {0,1,0,1,1,0,r,0,r,0,y,0,y,0,1,0,0,0,0,g,0,0,0,r,0,0,0,0},
                   {0,1,0,0,1,0,r,0,r,0,y,0,0,0,1,1,1,1,0,g,0,0,0,r,r,r,r,0},
                   {0,1,1,1,1,0,r,r,r,0,y,y,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                   //11   
                   {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1},
                   {1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                   {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                   {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                   {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                   {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                   {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                   {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                   {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                   {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                   {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
              //    1 2 3 4 5 1 2 3 4 5 1 2 3 4 5 1 2 3 4 5 1 2 3 4 5
                
    };
    
    private Map m;
    
    /**
     * 
     * @param primaryStage 
     */
    @Override
    public void start(Stage primaryStage) 
    {  
        Pane layout = new Pane();
        Canvas c = new Canvas(width,height);
        GraphicsContext gc = c.getGraphicsContext2D();
        layout.getChildren().add(c);
        Scene scene = new Scene(layout, width, height);
        ////////////////////////////////////////////////////////////////////////
        // start of game
        control.setSceneControls(scene, primaryStage);
        setControls(scene,primaryStage);
        rotateMap(GOOGLE_MAP);
        reflectMap(GOOGLE_MAP);
        m = new Map(GOOGLE_MAP);
        m.drawMap(gc);
        
    new AnimationTimer()
    {
        long lastTick = 0;        
    /**
     * 
     * @param now 
     */     
    public void handle(long now)
    {
                    
        if(running == true )
        {            
            primaryStage.setScene(scene);
                    
            if(lastTick == 0 )
            {
            lastTick = now;
            tick(gc);
            return;
            }
            
            if ( now - lastTick > 1000000000/10)
            {
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
    
    /**
     * 
     * @param gc 
     */
    public void tick(GraphicsContext gc)
    {
       m.drawBackground(gc); 
       m.drawMap(gc);
       
       gc.setFill(Color.YELLOW);
       gc.fillOval(pacMan.getX() * m.getScale(), pacMan.getY() * m.getScale(), m.getScale(), m.getScale());
       
       setMovement();
    }
    
    /**
     * 
     * @param mapy
     * @return 
     */
    public int[][] rotateMap(int[][] mapy)
    {
       int N = mapy.length;
        
        //turn counter clockwise 90 degrees
        for (int i =0; i < N/2 ;i++)
        {
            for (int j = i; j < N - i - 1; j++ )
            {
                int temp = mapy[i][j];
                mapy[i][j] = mapy[N - 1 - j][i]; 
                mapy[N - 1 - j][i] = mapy[N - 1 - i][N - 1 - j]; 
                mapy[N - 1 - i][N - 1 - j] = mapy[j][N - 1 - i]; 
                mapy[j][N - 1 - i] = temp; 
            
            }
        }
        return mapy;
    }     
    
    /**
     * 
     * @param mapy
     * @return 
     */
    public int[][] reflectMap(int[][] mapy)
    {
       
        for (int j = 0;j< 12 ; j++)
        {
            for(int i = 0; i < (mapy.length / 2); i++) 
            {
                int temp = mapy[j][i];
                mapy[j][i] = mapy[j][mapy.length - i - 1];
            mapy[j][mapy.length - i - 1] = temp;
            }
        }
        return mapy;
    }
    
    //controls 
    /**
     * 
     * @param scene
     * @param primaryStage 
     */
     public void setControls(Scene scene, Stage primaryStage)
    {
        scene.addEventFilter(KeyEvent.KEY_PRESSED, keys ->
        {
            if(keys.getCode() == KeyCode.W)
            {
                dir = Direction.up;
            }
            else if(keys.getCode() == KeyCode.A)
            {
                dir = Direction.left;
            }
            else if(keys.getCode() == KeyCode.S)
            {
                dir = Direction.down;
            }
            else if(keys.getCode() == KeyCode.D)
            {
                dir = Direction.right;
            }
            
        }); 
    }    
    
    public void setMovement()
    {
        //movement
        switch(dir)
        {
            case right:
                pacMan.setX(pacMan.getX() + velocity);
                break;
            case left:
                pacMan.setX(pacMan.getX() - velocity);
                break;
            case up:
                pacMan.setY(pacMan.getY() - velocity);
                break;
            case down:
                pacMan.setY(pacMan.getY() + velocity);
                break;
        }
        //checking collision
        // if pacman is going right
        if(dir == Direction.right)
        {
            // if the node to the right of pacman is not walkable 
            if(!m.getNode(pacMan.getX(), pacMan.getY()).isWalkable())
            {
                pacMan.setX(pacMan.getX()-1);
            }
        }
        // if pacman is going left
        if(dir == Direction.left)
        {   
            // if the node to the left of pacman is not walkable 
            if(!m.getNode(pacMan.getX(), pacMan.getY()).isWalkable())
            {
                pacMan.setX(pacMan.getX()+1);
            }
        }
        //if pacman is going up
        if(dir == Direction.up)
        {
            // if the node above pacman is not walkable 
            if(!m.getNode(pacMan.getY(), pacMan.getY()).isWalkable())
            {
                pacMan.setY(pacMan.getY()-1);
            }
        }
        // if pacman is going down
        if(dir == Direction.down)
        {
            // if the node below pacman is not walkable 
            if(!m.getNode(pacMan.getY(), pacMan.getY()).isWalkable())
            {
                pacMan.setY(pacMan.getY()-1);
            }
        }      
    }
      
    /**
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        launch(args);
    }
    
}

