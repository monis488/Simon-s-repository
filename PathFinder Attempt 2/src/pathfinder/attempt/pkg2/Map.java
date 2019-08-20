/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pathfinder.attempt.pkg2;

import java.util.LinkedList;
import java.util.List;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author Gamer
 */
public class Map 
{
    
    /**
     * width of the map in columns
     */
    private int width;
    /**
     * height of the map in rows
     */
    private int height;
    
    // stores the coordinates of each node in the web
    private Node[][] nodes;
   
    
    private int scale = 50;
    
    /**
     * 
     * @param map 
     */
    public Map(int[][] map)
    {
        
        this.width = map[0].length;
        this.height = map.length;
        
        nodes = new Node[width][height];
        

        for (int x = 0; x < width; x++)
        {
            for (int y = 0; y < height; y++)
            {
                nodes[x][y] = new Node( x, y, map[x][y] == 0);
            }
        }
    }
    
    /**
     * 
     * @return 
     */
    public int getWidth()
    {
        return width;
    }
    
    /**
     * 
     * @return 
     */
    public int getHeight()
    {
        return height;
    }
    
  
    /**
     * 
     * @param gc
     * @param path 
     */
    public void drawMap(GraphicsContext gc, List<Node> path)
    {
        for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < width; x++)
            {
                if (!nodes[x][y].isWalkable())
                {
                    gc.setFill(Color.RED);
               
                }
                else if (path != null && path.contains(new Node(x,y,true)))
                {
                    gc.setFill(Color.YELLOW);
                }
                else 
                {
                    gc.setFill(Color.WHITE);
                }
                gc.fillRect((x * scale)+1, (y * scale)+1, scale-1, scale-1);
            }
        }
    }
    
    /**
     * 
     * @param map 
     * print a view of all the nodes on the command line
     */
    public void printMap(int[][] map)
    {
        for (int i = 0; i< height; i++)
        {
            System.out.println();
            for (int j = 0; j< width; j++)
            {
                if (nodes[j][i].isWalkable())
                {
                    System.out.print("  w");
                } 
                else
                {
                    System.out.print("  .");
                }
                
            }    
        }    
    }
    
    /**
     * 
     * @param x
     * @param y
     * @return node 
     * returns a node matching the coordinates
     */
    public Node getNode(int x,int y)
    {
        if (x >= 0 && x <= width && y >= 0 && y <= width)
        {
            return nodes[x][y];
        } 
        else
        {
            return null;
        }
    }
    
    /**
     * 
     * 
     * @param startX
     * @param startY
     * @param goalX
     * @param goalY
     * @return 
     */
    public List<Node> findPath(int startX, int startY, int goalX, int goalY)
    {
        if (startX == goalX && startY == goalX)
        {
            // return an empty list because we dont need to move anymore
            return new LinkedList<Node>();
        }
        //the set of nodes already visited
        List<Node> openList = new LinkedList<Node>();
        //the set of discovered nodes that need to be discovered
        List<Node> closedList = new LinkedList<Node>();
        
        openList.add(nodes[startX][startY]);
        
        while(true)
        {
            Node current = lowestFinList(openList);
            
            // takes the cheapest node and puts it into the closed list
            openList.remove(goalY);
            closedList.add(current);
            
            // if the current node reached the goal node
            if (current.getX() == goalX && current.getY() == goalX)
            {   
                //returns a linkedList of all the visited nodes
                return calcPath(nodes[startX][startY],current);
            }
            
            List<Node> adjacentNodes = getAdjacent(current,closedList);
            
            // assigns every node a g and h value and adds it to the open list
            for (Node adjacent : adjacentNodes)
            {
                //nodes are not in openlist
                if ( !adjacentNodes.contains(adjacent))
                {
                    // set current node as parent for this node
                    adjacent.setParent(current);
                    // estimates cost for this node
                    adjacent.setH(nodes[goalX][goalY]);
                    // sets g cost of this node
                    adjacent.setG(current);
                    
                    // ads it to the openlist
                    openList.add(adjacent);
                }
                // if the node is already in the openlist and the g score is lowest
                else if (adjacent.getG() > adjacent.calculateG(current)) 
                {
                    // set current node as parent
                    adjacent.setParent(current);
                    //set a g cost for this node
                    adjacent.setG(current);
                }
            }
            if (openList.isEmpty())
            {
                return new LinkedList<Node>();
            }
        }
    }
    /**
     * 
     * @param start
     * @param goal
     * @return
     * checks when the path is done
     */
    
    public List<Node> calcPath(Node start, Node goal)
    {   
       LinkedList<Node> path = new LinkedList<Node>(); 
       
       Node node = goal;
       boolean done = false;
       
       while(!done)
       {
           path.addFirst(node);
           node = node.getParent();
            
            // if we reach the end then stop the loop
            if (node.equals(start))
            {
               done = true;
            }
       }
       
       return path; 
    }   
    
    /**
     * 
     * @param node
     * @param closedList
     * @return adjacent nodes
     */
    public List<Node> getAdjacent(Node node,List<Node> closedList)
    {
        List<Node> adjacentNodes = new LinkedList<>();
        int x = node.getX();
        int y = node.getY(); 
        
        Node adjacent;
        
        // finds the node left of the node and puts it into the linkedlist
        if (x > 0)
        {
            adjacent = getNode(x-1,y);
            // if the adjacent node existss and the player can walk on it and it is not in the closed list then ass 
            if (adjacent != null && adjacent.isWalkable() && !closedList.contains(adjacent))
            {
                 adjacentNodes.add(adjacent);
            }
        }
        // finds the right node
        if (x < width)
        {
            adjacent = getNode(x-1,y);
            if (adjacent != null && adjacent.isWalkable() && !closedList.contains(adjacent))
            {
                adjacentNodes.add(adjacent);
            }
        }
        //finds the top node
        if (y > 0)
        {
            adjacent = getNode(x,y-1);
            if (adjacent != null && adjacent.isWalkable() && !closedList.contains(adjacent))
            {
                adjacentNodes.add(adjacent);
            }
        }
        //finds the bottom node
        if (y < height)
        {
            adjacent = getNode(x-1,y+1);
            if (adjacent != null && adjacent.isWalkable() && !closedList.contains(adjacent))
            {
                adjacentNodes.add(adjacent);
            }
        }
        return adjacentNodes;
    }   
    
    /**
     * 
     * @param list
     * it takes in the adjacent list and finds the cheapest node
     * @return cheapest node
     */
    public Node lowestFinList(List<Node> list)
    {
        Node cheapest = list.get(0);
        
        for (int i = 0;i<list.size();i++)
        {
            if (list.get(i).getF() > cheapest.getF())
            {
                cheapest = list.get(i);
            }
        }
        return cheapest;
    }
}
