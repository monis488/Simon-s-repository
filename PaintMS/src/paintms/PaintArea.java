
package paintms;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class PaintArea {
    
    JButton clearBtn,blackBtn;
    PaintMS draw = new PaintMS();
    
    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          if(e.getSource() == clearBtn){
              draw.clear();
          } else if (e.getSource()== blackBtn){
              draw.black();
          }
        }
    };
    
    
    
    
    
    
    
     public void makeDisplay(String name){
       JFrame frame = new JFrame();
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setSize(600,600);
       frame.setResizable(true);
       frame.setLocationRelativeTo(null);
       frame.setTitle(name);
       frame.setVisible(true);
       
       Container content = frame.getContentPane();
       draw = new PaintMS();
       
       content.setLayout(new BorderLayout());
       content.add(draw,BorderLayout.CENTER);
       JPanel panel = new JPanel();
       
       clearBtn = new JButton("clear");
       clearBtn.addActionListener(actionListener);
       
       blackBtn = new JButton("Black");
       blackBtn.addActionListener(actionListener);
       
       panel.add(clearBtn);
       panel.add(blackBtn);
       
      content.add(panel,BorderLayout.NORTH);
   }
}
