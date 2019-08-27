
package paintms;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

 
 
public class PaintMS extends JComponent{

private Image image;
private Graphics2D g2;
private Graphics g;
    
/*
 *  coordinates for the drawings                                
 */

private int currentX,currentY,oldX,oldY; 

    public PaintMS(){
        
        setDoubleBuffered(false);
        
        /*
         *  adds a mouse listener that will contain a mousePressed method     
         */
        
        addMouseListener(new MouseAdapter() {
            
    public void mousePressed(MouseEvent e){
                              
               /*
                * saves coordinates entered with the mouse listener
                */      
        oldX = e.getX();
        oldY = e.getY();
        
            /*
             * this if statement will draw a line if the specific spot is blank 
             */
         
   
                
            
        }
        
    });
        addMouseMotionListener(new MouseMotionAdapter(){
            public void mouseDragged(MouseEvent e){
                currentX = e.getX();
                currentY = e.getY();
                
             if (g2 != null){
                g2.drawRect(oldX, oldY, 10, 10);
                
                repaint();
                
                oldX = currentX;
                oldY = currentY;
            }   
            }
            
        });
        
        
        
        
    }
    

    /*
     *  draw method
     */
    public void paint(Graphics g){
        if(image == null){
          image = createImage(getSize().width,getSize().height);
          g2 = (Graphics2D) image.getGraphics();
          g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
          
          clear();
        }
       g.drawImage(image, 0, 0, null);
    }
    public void clear(){
    g2.setColor(Color.WHITE);
    // draw white on entire draw area to clear
    g2.fillRect(0, 0, getSize().width, getSize().height);
    g2.setColor(Color.black);
    repaint();  
      
    }
    public void black(){
        g2.setPaint(Color.BLACK);
    }
}