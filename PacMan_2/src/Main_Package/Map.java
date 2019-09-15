/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main_Package;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author Simon Dao
 */
public class Map {
    
    private final int width = 28; 
    private final int height = 28; 
    
    private int scale = 25;
     
    private Tile[][] nodes;
    private boolean[][] fruit;
    
    /**
     * 
     * @param scale 
     */
    public void setScale(int scale)
    {
        this.scale = scale;
    }
    
    /**
     * 
     * @return 
     */
    public int getScale()
    {
        return scale;
    }
    
    public void drawBackground(GraphicsContext gc)
    {
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, 700, 700);   
    }
    
    /**
     * 
     * @param map 
     */
    public Map(int[][] map)
    {
    // copies the map array into the tile array      
        nodes = new Tile[width][height];
        
        for(int y = 0; y < height; y++)
        {
            for(int x = 0; x < width; x++)
            {
                nodes[x][y] = new Tile( x, y, map[x][y] == 0 || map[x][y] == 5, map[x][y]);
                
                
                if(map[x][y] == 5)
                {
                    nodes[x][y].changeFruit(false);
                }
                
            }
        }  
        
    }
    
    public Tile getNode(int x, int y)
    {
        return nodes[x][y];
    }
    
    public void drawMap(GraphicsContext gc)
    {
        //colors
        Color WALL_COLOR = Color.rgb(50, 10, 219);
        Color CHERRY_COLOR = Color.rgb(250, 169, 167);
        Color RED_COLOR = Color.rgb(250, 26, 46);
        Color GREEN_COLOR = Color.rgb(32, 147, 25);
        Color YELLOW_COLOR = Color.rgb(245, 190, 45);
        
        for(int y = 0; y < height; y++)
        {
            for(int x = 0; x < width; x++)
            {
                if(nodes[x][y].getColor() == 1 && !nodes[x][y].isWalkable())
                {
                    gc.setFill(WALL_COLOR);
                    gc.fillRect(x*scale, y*scale, scale, scale);
                } 
                else if(nodes[x][y].getColor() == 2 && !nodes[x][y].isWalkable())
                {
                    gc.setFill(RED_COLOR);
                    gc.fillRect(x*scale, y*scale, scale, scale);
                }
                else if(nodes[x][y].getColor() == 3 && !nodes[x][y].isWalkable())
                {
                    gc.setFill(GREEN_COLOR);
                    gc.fillRect(x*scale, y*scale, scale, scale);
                }
                 else if(nodes[x][y].getColor() == 4 && !nodes[x][y].isWalkable())
                {
                    gc.setFill(YELLOW_COLOR);
                    gc.fillRect(x*scale, y*scale, scale, scale);
                }
                else if(nodes[x][y].getColor() != 5 && nodes[x][y].hasFruit() && nodes[x][y].isWalkable())
                {
                    gc.setFill(CHERRY_COLOR);
                    gc.fillOval(x*scale+11, y*scale+11,5, 5);  
                }
                // still has fruit but you cant see it
                else if(nodes[x][y].getColor() == 5 && nodes[x][y].isWalkable())
                {
                    gc.setFill(Color.BLACK);
                    gc.fillRect(x*scale, y*scale, scale, scale);
                }
            }
        }  
    }
}


