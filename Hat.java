/*Hat.java
 * Author: Andrew Lee
 */
 
import javax.swing.*;
import java.awt.*;

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

class Hat extends JFrame{
  public static int type;
  
  Hat(Boolean start){
    if (start ==true){
    setTitle("Selection");
    //setUndecorated(true);
    setSize(1600, 1100);
    setResizable(false);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // set the window up to end the program when closed
    
    MyMouseListener mouseListener = new MyMouseListener();
    this.addMouseListener(mouseListener);

    
    setFocusable(true);
      requestFocusInWindow();
  
     getContentPane().add( new hatSelectionPanel(),BorderLayout.CENTER);
     
    pack(); //makes the frame fit the contents
    setVisible(true);
    }
  }

  

  
   private class MyMouseListener implements MouseListener {
      
     public void mouseClicked(MouseEvent e) {
    int x=e.getX();
    int y=e.getY();
    
    if (x>249 && x<451 && y>549 && y<751){
      System.out.println("dog");
      GameWindow game= new GameWindow();
      type=1;
      setVisible(false);
     
    }else if (x>549 && x<751 && y>549 && y<751){
      GameWindow game= new GameWindow();
      type=2;
      setVisible(false);
    }else if (x>849 && x<1051 && y>549 && y<751){
      GameWindow game= new GameWindow();
      type=3;
      System.out.println("asd");
      setVisible(false);
    }else if (x>1149 && x<1351 && y>549 && y<751){
      GameWindow game= new GameWindow();
      type=4;
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
    
    
  
  
  Hat(double x, double y){
    
  
  }
  
  public int getHat(){
    return type;
  }
 
  
 public int speedHat(int speed) { 
 speed = 700;
    return speed;
 }
 
 public double healthHat(double health) { 
  health = health*2;
  return health;
 }
 
 
 
 

 public void dashHat() { 
   
   }
     
 

 }
   
   
   
  //public void nowallsHat() { 
  
 //}
 
  
  
 //}
 

  

 
  
  
  
