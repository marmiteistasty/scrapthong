import javax.swing.*;
import java.awt.*;

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

class SelectionWindow extends JFrame { 
  private Hat hat;
  

  
  //Window constructor
  public SelectionWindow() { 
    setTitle("Selection");
    //setUndecorated(true);
    setSize(1600, 1100);
    setResizable(false);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // set the window up to end the program when closed
    
  
  

    
    setFocusable(true);
      requestFocusInWindow();
  
     getContentPane().add( new hatSelectionPanel(),BorderLayout.CENTER);
     
    pack(); //makes the frame fit the contents
    setVisible(true);
  }

     private class MyMoseListener implements MouseListener {
      
     public void mouseClicked(MouseEvent e) {
    int x=e.getX();
    int y=e.getY();
    
    if (x>249 && x<451 && y>549 && y<751){
      GameWindow game= new GameWindow();
    //  Hat hat = new Hat(0,0,1);
      setVisible(false);
     
    }else if (x>549 && x<751 && y>549 && y<751){
      GameWindow game= new GameWindow();
   //   Hat hat = new Hat(0,0,2);
      setVisible(false);
    }else if (x>849 && x<1051 && y>549 && y<751){
      GameWindow game= new GameWindow();
   //   Hat hat = new Hat(0,0,3);
      setVisible(false);
    }else if (x>1149 && x<1351 && y>549 && y<751){
      GameWindow game= new GameWindow();
    //  Hat hat = new Hat(0,0,4);
      setVisible(false);
    }
    
}
      
      public void mousePressed(MouseEvent e) {
      }

      public void mouseReleased(MouseEvent e) {
      }

      public void mouseEntered(MouseEvent e) {
      }

      public void mouseExited(MouseEvent e) {
      }

 
}
  

  }
   


   
