/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pathfinder.attempt.pkg2;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author Gamer
 */


public class Game 
{
    
    public int startX;
    public int startY;
    public int goalX;
    public int goalY;
 
   int[] input = new int[4];
    
    Map map;
    //Main m = new Main();
    
    /**
     * 
     * 0 = walkable
     * 
     */
    private final int MAP_1_SCALE = 50;
    
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
    
    private final int MAP_2_SCALE = 25;
    
    int[][] map2 = new int[24][24];
    
    private final int MAP_3_SCALE = 6;
    
    int[][] map3 = new int[99][99];
    
    List<Node> path;
    
  
    int x = 10;
    int y = 10;
    
    public Game()
    {   
        generateMap(map3);
        rotateMap(map3);
        reflectMap(map3);
        map = new Map(map3);
        map.setScale(this.MAP_3_SCALE);
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
        
        gc.setFill(Color.GREEN);
        gc.fillRect(600, 0, 3, 601);
        ///////////////////////////////////////
        path = map.findPath(input[0], input[1], input[2], input[3]);
        
        
        ///////////////////////////////////////
        map.drawMap(gc, path);
         
    }
    
    public void print()
    {   
        map.printMap(map3); 
    }
    
     public int[] userInput()
    {
        print();
        System.out.println("\n");
        System.out.println("values cannot be higher than "+map2.length+"\n");
        Scanner s = new Scanner(System.in);
        System.out.println("please enter starting node x");
        input[0] = s.nextInt();
        System.out.println("please enter starting node y");
        input[1] = s.nextInt();
        System.out.println("please enter goal node x");
        input[2] = s.nextInt();
        System.out.println("please enter goal node x");
        input[3] = s.nextInt();
        
        for (int i = 0; i < input.length;i++)
        {
            if (input[i] > map.getWidth())
            {
                System.out.println("out of map bounds");
            }
        }
        
        return input;
    }
     
    public int[][] generateMap(int[][] rmap)
    {   
        for (int i = 0; i < rmap.length; i++)
        {
            for (int j = 0; j < rmap.length; j++)
            {
                int walkable;
                Random r = new Random();
                double rand = r.nextDouble();
                
                if (rand < .2)
                {
                    walkable = 1;
                } 
                else 
                {
                    walkable = 0;
                }
                rmap[i][j] = walkable;
            }
        }
        return rmap;
    }
    
    public int[][] rotateMap(int[][] mapy)
    {
       int N = 12;
        
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
}
