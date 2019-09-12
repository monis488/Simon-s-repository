/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main_Package;

/**
 *
 * @author Simon Dao
 */
public class Tile {
    
    private int x;
    private int y;
    private boolean walkable = true;
    private boolean hasFruit = true;
    private int color;
    Player p = new Player();
    
    /**
     * 
     * @return 
     */
    public int getX()
    {
        return x;
    }
    
    /**
     * 
     * @return 
     */
    public int getY()
    {
        return y;
    }
    
    public boolean isWalkable()
    {
        return walkable;
    }
    
    public boolean hasFruit()
    {
        return hasFruit;
    }
    
    public int getColor()
    {
        return color;
    }
    
    public Tile(int x, int y, boolean walkable, int color)
    {
        this.x = x;
        this.y = y;
        this.walkable = walkable;
        this.color = color;
        
    }
    
}
