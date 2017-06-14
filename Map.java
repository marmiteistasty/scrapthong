/*Map.java
 * Author:Hady Mana
 * Date: June 13,2017
 *To create a map for the objects to interact in
 */

import java.awt.*;
import java.io.*; 
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.util.ArrayList;

class Map {
  
  private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
  private ArrayList<Weapon> weapons=new ArrayList<Weapon>();

  private Player player;
  private BufferedImage floor;
  private BufferedImage died;
  private BufferedImage victory;
  private ArrayList<Rectangle> walls = new ArrayList<Rectangle>();
  
  private int level;
 
  public Map(int xResolution,int yResolution, int level) { 

    this.level=level;   
    try {
      
      died=ImageIO.read(new File("died.gif"));//plays an animated gif for when the player dies
      victory=ImageIO.read(new File("victory.png"));  //load victory image with credits
      
      
      if(this.level==1){
        System.out.println("it works");
      floor = ImageIO.read(new File("map.png"));
      }else if(this.level==2){
      floor = ImageIO.read(new File("mapTwo.png"));
      }else if(this.level==3){
      floor = ImageIO.read(new File("mapThree.png"));
      //scrapTing will give a value for 
      }else{
      floor = ImageIO.read(new File("map.png"));
      }
    
      
 
    } catch(Exception E) { 
      System.out.println("There was an error loading the map");
    }
 

    if(level==1){
     
     enemies=new ArrayList<Enemy>();
     weapons=new ArrayList<Weapon>();
     player=new Player();
    walls.add(new Rectangle(0,0,20,950));
    walls.add(new Rectangle(0,0,1600,20));
    walls.add(new Rectangle(0,880,1600,20));
    walls.add(new Rectangle(1599,0,1,900));
    walls.add(new Rectangle(1599,0,1,900));
    walls.add(new Rectangle(653,0,25,105));
    walls.add(new Rectangle(653,0,25,105));
    walls.add(new Rectangle(653,233,25,150));
    walls.add(new Rectangle(233,365,431,30));
    walls.add(new Rectangle(284,380,25,117));
    walls.add(new Rectangle(0,372,130,25));
    walls.add(new Rectangle(284,675,10,73));
    walls.add(new Rectangle(0,752,290,174));
    walls.add(new Rectangle(928,0,25,100));
    walls.add(new Rectangle(928,234,25,142));
    walls.add(new Rectangle(933,372,152,15));
    walls.add(new Rectangle(1082,376,25,48));
    walls.add(new Rectangle(1081,536,25,400));
    walls.add(new Rectangle(1086,596,58,15));
    walls.add(new Rectangle(1232,595,147,19));
    walls.add(new Rectangle(1376,542,25,119));
    walls.add(new Rectangle(1376,761,25,59));
    walls.add(new Rectangle(1352,819,1000,1000));
    walls.add(new Rectangle(1378,154,215,250));
    walls.add(new Rectangle(1298,2,81,190));
  
    weapons.add(new Weapon(50,50,1,100,false));
    enemies.add(new Enemy(735,645,100,1,(new Weapon(1000,534,1,100,true))));  
    player=new Player(23,30,100);
   
    }
    
    
    if(level==2){
      enemies=new ArrayList<Enemy>();
     weapons=new ArrayList<Weapon>();
     player=new Player();
    walls.add(new Rectangle(0,0,20,950));
    walls.add(new Rectangle(0,0,1600,20));
    walls.add(new Rectangle(0,880,1600,20));
    walls.add(new Rectangle(1599,0,1,900));
    walls.add(new Rectangle(1599,0,1,900));
    walls.add(new Rectangle(653,0,25,105));
    walls.add(new Rectangle(653,0,25,105));
    walls.add(new Rectangle(653,233,25,150));
    walls.add(new Rectangle(233,365,431,30));
    walls.add(new Rectangle(284,380,25,117));
    walls.add(new Rectangle(0,372,130,25));
    walls.add(new Rectangle(284,675,10,73));
    walls.add(new Rectangle(0,752,290,174));
    walls.add(new Rectangle(928,0,25,100));
    walls.add(new Rectangle(928,234,25,142));
    walls.add(new Rectangle(933,372,152,15));
    walls.add(new Rectangle(1082,376,25,48));
    walls.add(new Rectangle(1081,536,25,400));
    walls.add(new Rectangle(1086,596,58,15));
    walls.add(new Rectangle(1232,595,147,19));
    walls.add(new Rectangle(1376,542,25,119));
    walls.add(new Rectangle(1376,761,25,59));
    walls.add(new Rectangle(1352,819,1000,1000));
    walls.add(new Rectangle(1378,154,215,250));
    walls.add(new Rectangle(1298,2,81,190));
  
    weapons.add(new Weapon(50,50,1,100,false));
    enemies.add(new Enemy(735,645,100,1,(new Weapon(1000,534,3,200,true))));  
    enemies.add(new Enemy(788,160,100,1,(new Weapon(1000,534,2,200,true))));  
    enemies.add(new Enemy(1492,483,100,1,(new Weapon(1000,534,2,200,true))));  
    player=new Player(23,30,100);
      
      
    } 
    
    if(level==3){
  enemies=new ArrayList<Enemy>();
     weapons=new ArrayList<Weapon>();
     player=new Player();
    walls.add(new Rectangle(0,0,20,950));
    walls.add(new Rectangle(0,0,1600,20));
    walls.add(new Rectangle(0,880,1600,20));
    walls.add(new Rectangle(1599,0,1,900));
    walls.add(new Rectangle(1599,0,1,900));
    walls.add(new Rectangle(653,0,25,105));
    walls.add(new Rectangle(653,0,25,105));
    walls.add(new Rectangle(653,233,25,150));
    walls.add(new Rectangle(233,365,431,30));
    walls.add(new Rectangle(284,380,25,117));
    walls.add(new Rectangle(0,372,130,25));
    walls.add(new Rectangle(284,675,10,73));
    walls.add(new Rectangle(0,752,290,174));
    walls.add(new Rectangle(928,0,25,100));
    walls.add(new Rectangle(928,234,25,142));
    walls.add(new Rectangle(933,372,152,15));
    walls.add(new Rectangle(1082,376,25,48));
    walls.add(new Rectangle(1081,536,25,400));
    walls.add(new Rectangle(1086,596,58,15));
    walls.add(new Rectangle(1232,595,147,19));
    walls.add(new Rectangle(1376,542,25,119));
    walls.add(new Rectangle(1376,761,25,59));
    walls.add(new Rectangle(1352,819,1000,1000));
    walls.add(new Rectangle(1378,154,215,250));
    walls.add(new Rectangle(1298,2,81,190));
  
    weapons.add(new Weapon(50,50,1,100,false));
    enemies.add(new Enemy(735,645,100,1,(new Weapon(1000,534,3,200,true))));  
    enemies.add(new Enemy(788,160,100,1,(new Weapon(1000,534,2,200,true))));  
    enemies.add(new Enemy(1492,483,100,1,(new Weapon(1000,534,2,200,true))));  
    player=new Player(23,30,100);
      
    }
    if(level==4){
      enemies=new ArrayList<Enemy>();
     weapons=new ArrayList<Weapon>();
     player=new Player(23,30,100);
    walls.add(new Rectangle(0,0,20,950));
    walls.add(new Rectangle(0,0,1600,20));
    walls.add(new Rectangle(0,880,1600,20));
    walls.add(new Rectangle(1599,0,1,900));
    walls.add(new Rectangle(1599,0,1,900));
    walls.add(new Rectangle(653,0,25,105));
    walls.add(new Rectangle(653,0,25,105));
    walls.add(new Rectangle(653,233,25,150));
    walls.add(new Rectangle(233,365,431,30));
    walls.add(new Rectangle(284,380,25,117));
    walls.add(new Rectangle(0,372,130,25));
    walls.add(new Rectangle(284,675,10,73));
    walls.add(new Rectangle(0,752,290,174));
    walls.add(new Rectangle(928,0,25,100));
    walls.add(new Rectangle(928,234,25,142));
    walls.add(new Rectangle(933,372,152,15));
    walls.add(new Rectangle(1082,376,25,48));
    walls.add(new Rectangle(1081,536,25,400));
    walls.add(new Rectangle(1086,596,58,15));
    walls.add(new Rectangle(1232,595,147,19));
    walls.add(new Rectangle(1376,542,25,119));
    walls.add(new Rectangle(1376,761,25,59));
    walls.add(new Rectangle(1352,819,1000,1000));
    walls.add(new Rectangle(1378,154,215,250));
    walls.add(new Rectangle(1298,2,81,190));
  
    weapons.add(new Weapon(50,50,1,100,false));
    enemies.add(new Enemy(735,645,100,1,(new Weapon(1000,534,3,200,true))));  
    enemies.add(new Enemy(788,160,100,1,(new Weapon(1000,534,2,200,true))));  
    enemies.add(new Enemy(1492,483,100,1,(new Weapon(1000,534,2,200,true))));  

      
    }
  }
  
  public void draw(Graphics g) {  
    g.drawImage(floor,0,0,null);
  } 
  public void died(Graphics g) {  
    g.drawImage(died,800,450,null);
  }
  public void victory(Graphics g) {  
    g.drawImage(victory,800,450,null);
  } 
 public ArrayList<Rectangle> getWalls() {
  return walls; 
 }
  public ArrayList<Enemy> getEnemy() {
  return enemies; 
  }

  public ArrayList<Weapon> getWeapons() {
  return weapons; 
  }
  public Player getPlayer() {
  return player; 
  }
  
  public void setLevel(int level) {
  this.level=level;
  }
  public int getLevel() {
 return this.level;
  }
   
}