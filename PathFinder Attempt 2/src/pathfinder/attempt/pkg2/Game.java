/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pathfinder.attempt.pkg2;

import java.util.List;
import java.util.Random;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author Gamer
 */


public class Game 
{
    
    Map map;
    //Main m = new Main();
    
    /**
     * 
     * 0 = walkable
     * 
     */
    int[][] map1 = {
        
        {0,1,0,1,0,0,0,1,1,1,0,1},
        {0,0,0,0,0,0,0,0,1,1,0,0},
        {0,0,0,1,1,0,0,1,0,0,1,0},
        {1,0,0,0,1,0,1,0,1,0,0,1},
        {0,0,1,0,0,0,1,0,0,1,1,1},
        {0,0,0,1,1,0,1,0,0,1,0,0},
        {0,0,0,1,0,0,0,0,0,0,0,0},
        {0,1,1,0,0,0,0,0,0,0,1,0},
        {0,0,0,1,0,1,1,0,0,0,0,1},
        {0,1,1,1,0,1,0,0,1,1,1,0},
        {0,0,0,0,0,0,0,0,0,1,0,0},
        {0,0,0,1,1,1,0,0,0,0,0,0},
    
    };
  
    List<Node> path;
  
    int x = 10;
    int y = 10;
    
    public Game()
    {
        rotateMap(map1);
        reflectMap(map1);
        map = new Map(map1);
        
    }
    
    
    // move the player 
    public void update(GraphicsContext gc)
    {      
        render(gc);
    }
    
    public void render(GraphicsContext gc){
        
        //background
        gc.setFill(Color.BLACK);
        gc.fillRect(0,0,601,601);
        
        map.drawMap(gc, path);
    }
    public void print()
    {   
        
        map.printMap(map1);
        
       
    }
    public void translateMap()
    {
        
    }
    public int[][] rotateMap(int[][] mapy)
    {
       int N = 12;
        
        //turn counter clockwise 90 degrees
        for (int i =0; i < N/2 ;i++)
        {
            for (int j = i; j < N - i - 1; j++ )
            {
                int temp = map1[i][j];
                map1[i][j] = map1[N - 1 - j][i]; 
            map1[N - 1 - j][i] = map1[N - 1 - i][N - 1 - j]; 
            map1[N - 1 - i][N - 1 - j] = map1[j][N - 1 - i]; 
            map1[j][N - 1 - i] = temp; 
            
            }
        }
         return mapy;
    }     
    public int[][] reflectMap(int[][] mapy)
    {
       
    for (int j = 0;j< 12 ; j++)
    {
        for(int i = 0; i < (mapy.length / 2); i++) 
        {
        int temp = mapy[j][i];
        mapy[j][i] = mapy[j][mapy.length - i - 1];
        map1[j][map1.length - i - 1] = temp;
        }
    }
        return mapy;
    }
}