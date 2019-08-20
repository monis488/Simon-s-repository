/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pathfinder;

/**
 *
 * @author Simon Dao
 */
public class Node 
{
    
    protected static final int MOVEMENT_COST = 10;
    
    private int x;
    private int y;
    
    private boolean walkable;
    
    private Node parent;
    
    private int g;
    private int h;
    
    public Node(int x, int y, boolean walkable)
    {
        this.x = x;
        this.y = y;
        
        this.walkable = walkable;
    } 
    
    public void setG(Node parent){
        
    }
    
    
    
    /**
     * 
     *  getters and setters to provide encapsulation 
     * for all of our variables
     * 
     */ 
    
    public int getX()
    {
        return x;
    }
    public void setX(int x)
    {
        this.x = x;
    }
    public int getY()
    {
        return y;
    }
    public void setY(int y)
    {
        this.y = y;
    }
    public boolean isWalkable()
    {
        return walkable;
    }
    public void setWalkable(boolean walkable)
    {
		this.walkable = walkable;
    }
    public Node getParent()
    {
		return parent;
    }
    public void setParent(Node parent)
    {
		this.parent = parent;
    }
    public int getF()
    {
		return g + h;
    }
    public int getG()
    {
		return g;
    }
    public int getH()
    {
		return h;
    }
}
