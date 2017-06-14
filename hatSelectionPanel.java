import javax.swing.*;
import java.awt.*;
import java.io.*; 
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

class hatSelectionPanel extends JPanel{

   BufferedImage hat1=null, hat2=null,hat3=null,hat4=null;
    
    //constructor
    public hatSelectionPanel() { 
        setPreferredSize(new Dimension(1600,1100));
     //setOpaque(false);
      setFocusable(true);
      requestFocusInWindow();
   
        try {
      hat1 = ImageIO.read(new File("hat1.png"));
      hat2 = ImageIO.read(new File("hat2.png"));
      hat3 = ImageIO.read(new File("hat3.png"));
      hat4 = ImageIO.read(new File("hat3.png"));
      
      
    } catch(Exception E) { 
      System.out.println("error loading picture!");
    }
    
  
     
    }
    
    
    public void paintComponent(Graphics g) { 
      super.paintComponent(g); //required to ensure the panel si correctly redrawn
   
   
      //update the content
      //draw the screen
   g.drawImage(hat1,this.getWidth()/2-550,this.getHeight()/2, null);
       g.drawImage(hat2,this.getWidth()/2-250,this.getHeight()/2, null);
        g.drawImage(hat3,this.getWidth()/2+50,this.getHeight()/2, null);
         g.drawImage(hat4,this.getWidth()/2+350,this.getHeight()/2, null);
      
      
      //request a repaint
     
      repaint();
    }
    
    
    
  
      
      
}

    

