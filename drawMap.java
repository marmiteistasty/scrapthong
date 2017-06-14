
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*; 
import java.util.Scanner;
import java.util.Random;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.GridBagLayout;











class drawMap { 
  
  public static void main(String[] args) { 
    GameWindow game= new GameWindow(); 

  }
  
}

//This class represents the game window
class GameWindow1 extends JFrame { 
  
  //Window constructor
  public GameWindow1() { 
    setTitle("Scrapting");
    
    this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
setLayout(new GridBagLayout());
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    getContentPane().add( new GamePanel(),SwingConstants.CENTER);
    getContentPane().add( new HUDPanel(),BorderLayout.SOUTH);
    setVisible(true);
  }
  
  
  
// An inner class representing the panel on which the game takes place
  static class GamePanel extends JPanel{
    
   
    Map map;  
    
    //constructor
    public GamePanel() { 
        setPreferredSize(new Dimension(1024,768));
     
      setFocusable(true);
      requestFocusInWindow();
   
      map = new Map();

     
    }
    
    
    public void paintComponent(Graphics g) { 
      super.paintComponent(g); //required to ensure the panel si correctly redrawn
      
      int x = this.getWidth(); 
    int y = this.getHeight();
    
      //update the content
      //draw the screen
      map.draw(g,x,y);
      //request a repaint
     
      //repaint();
    }
    
   


class Map { 
  BufferedImage image=null;
  
  
  public Map() { 
    try {
      image = ImageIO.read(new File("map.png"));
      
    } catch(Exception E) { 
      System.out.println("error loading map!");
    }
  }
  
  
  
  public void draw(Graphics g, int x, int y) { 
     
    g.drawImage(image,(x-image.getWidth())/2,(y-image.getHeight())/2,null);
    
  }
  
}
  
  }
  
 
  
  
  
  
  
  static class HUDPanel extends JPanel{
    
   
    HUD health;
    HUD ammo;
    HUD armor;
    
    //constructor
    public HUDPanel() { 
        setPreferredSize(new Dimension(1024,200));
     
      setFocusable(true);
      requestFocusInWindow();
   
      health = new HUD();
      ammo = new HUD();
      armor = new HUD();

     
    }
    
    
    public void paintComponent(Graphics g) { 
      super.paintComponent(g); //required to ensure the panel si correctly redrawn
   
   
      //update the content
      //draw the screen
      health.draw(g,this.getWidth()/2-240,160,10, "Health");
      health.drawheart(g,this.getWidth()/2-250,10,100);
      ammo.draw(g,this.getWidth()/2-40,160,10,"Ammo");
      ammo.drawammo(g,this.getWidth()/2-50,10);
      armor.draw(g,this.getWidth()/2+160,160,10,"Armor");
      armor.drawarmor(g,this.getWidth()/2+150,10,100);
      
      //request a repaint
     
      repaint();
    }
    
   


class HUD { 
  
  BufferedImage healthImg1=null, healthImg2=null,healthImg3=null,healthImg4=null,ammoImg=null, armorImg=null;
  
  
  public HUD() { 
    try {
      healthImg1 = ImageIO.read(new File("health1.png"));
      healthImg2 = ImageIO.read(new File("health2.png"));
      healthImg3 = ImageIO.read(new File("health3.png"));
      healthImg4 = ImageIO.read(new File("health3.png"));
      ammoImg = ImageIO.read(new File("ammo.png"));
      armorImg = ImageIO.read(new File("armor.png"));
      
    } catch(Exception E) { 
      System.out.println("error loading picture!");
    }
  }
  
  
  
  public void drawheart(Graphics g, int x, int y, int health) { 
    if (health==100){
    g.drawImage(healthImg1,x,y,null);
    }else if(health<100 && health>74){
      g.drawImage(healthImg2,x,y,null);
    }else if (health<75 && health>49){
      g.drawImage(healthImg3,x,y,null);
    }else if (health<50 && health >24){
      g.drawImage(healthImg4,x,y,null);
    }else if (health<25 && health!=0){
      g.drawImage(healthImg4,x,y,null);
    }
  }
  
    public void drawammo(Graphics g, int x, int y) { 
    
    g.drawImage(ammoImg,x,y,null);

    
  }
    
    
      public void drawarmor(Graphics g, int x, int y, int armor) { 
 
      g.drawImage(armorImg,x,y,null);
    }
  
  
  //public HUD() { 
    

  
  
  
  public void draw(Graphics g,int x, int y, int amount, String valueName) { 
     g.setFont(new Font("Microsoft PhagsPa", Font.PLAIN, 18));

    g.drawString(valueName+": "+amount,x,y);
    
  }
  
}
  
  }
}
  
  
  
  
  


 