
package flappy;

import java.awt.Graphics;
import javax.swing.JPanel;


public class Renderer extends JPanel{
    
    public static final long serialVersionUID =1L;
    
    
    @Override
    public void paint(Graphics g){
        super.paintComponent(g);
        
        Flappy.flappy.repaint(g);
    }
}
