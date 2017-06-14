
import java.awt.*;
import java.io.*; 
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;


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
  
  
