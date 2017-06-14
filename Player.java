/*Player object
 * Author: Naymul Mohammed
 * May 25,2017
 * To have a moveable object controllable by the player
 */

import java.awt.Graphics;
import java.awt.*;
import java.io.*; 
import java.awt.image.*;
import javax.imageio.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;


class Player extends Entity{
  BufferedImage player;
  
  private double xDirection=0;
  private double yDirection=0;
  private Weapon gun;
  private Hat hat=new Hat(false);
  private int health=0;
  private int speed=400;
  public static int type=Hat.type;
  private int numHits=0;
  
  Player(){
    
  }
  /**
   * Player
   * This is a contructor for the player object that is part of the game
   * @param 2 double values that are the objects X and Y coordinates, and an integer for its health
   * @return Nothing because it is a constructor
   */
  Player(double x, double y, int health){
    super(x,y, (new Rectangle( (int)x, (int)y, 35, 35))); //Only constructer needed
    loadSprites();  
    this.xDirection=0;
    this.yDirection=0;
    this.health=health;
    
  }
  
  /**
   * loadSprites
   * This loads the sprites of the object
   * @param Nothing because thge values are already in the method
   * @return Nothing because it just loads the file in the method
   */
  public void loadSprites() { 
    try {    
      player = ImageIO.read(new File("player.png"));   
    } catch(Exception e) { System.out.println("error loading sprite");};
  }
  
  /**
   * draw
   * This draws the player object so it always looks at the mouse
   * @param Graphics object to draw, and the x and y coordinates of the mouse
   * @return Nothing because it just draws the player
   */
  public void draw(Graphics g,double mouseX,double mouseY) { 
    
    Graphics2D g2d=(Graphics2D)g;
    AffineTransform rotatePlayer=new AffineTransform();      //Transformation so player is always looking towards the mouse
    
    rotatePlayer.translate(this.getX(),this.getY());
    rotatePlayer.rotate(Math.atan2(this.getY()-mouseY,this.getX()-mouseX) );
    
    rotatePlayer.translate(-player.getWidth()/2,-player.getHeight()/2);
    g2d.drawImage(player,rotatePlayer,null);
    
    
    if(!(this.gun==null)){
      this.gun.draw(g,mouseX,mouseY,this.getX(),this.getY(),499);  //only draw gun if player has one
    } 
  }
  
  /**
   * move
   * This moves the player based on the direction set by the keyboard in the main class
   * @param double value of elapsed time and an arraylist of rectangles
   * @return Nothing because it just draws the player
   */
  public void move(double timeElapsed,ArrayList<Rectangle> walls) { 
    
    double originalX=this.getX();      
    double originalY=this.getY();
    
    setX(this.getX()+this.xDirection*speed*timeElapsed);    //player moves based on speed and time
    setY(this.getY()+this.yDirection*speed*timeElapsed);
    this.setBox((int)this.getX(),(int)this.getY());
    
    
    for (int r = 0; r < walls.size(); r++){              //If player intersects a wall it's X and Y is set to what it was before change
      if(this.getBox().intersects(walls.get(r))){
        System.out.println("hurt");
        System.out.println("X:"+this.getBox().x + " y:"+this.getBox().y);
        this.setY(originalY);
        this.setX(originalX);
        this.setBox((int)originalX,(int)originalY);
      }
    } 
  }
  
  /**
   * skill
   * 
   * @param
   * @return 
   */
  public void skill() {    //ANDREW's code to use skills
    if (hat.getHat()==1 || type==1){
      speed=(hat.speedHat(speed)); 
    }else if(hat.getHat()==2 ||type==2){   
    }else if(hat.getHat()==3 ){
    }  
  }
  
  /**
   * setSpeed
   * This method sets the speed of the player
   * @param An integer value that the speed is set to
   * @return Nothing because it is setting the speed
   * */
  public void setSpeed(int speed){
    this.speed=speed;
  }
  
  /**
   * getSpeed
   * This method gets the speed of the player
   * @param Nothing because you are getting a value
   * @return An integer value of the player's speed
   * */
  public int getSpeed(){
    return this.speed;
  }
  
  /**
   * getHealth
   * This method returns the health value of an object
   * @param Nothing because it is getting a value
   * @return A integer value that is the objects health
   * */
  public int getHealth(){ 
    return this.health;
  }
  
  /**
   * lowerHealth
   * This method lowers the health of on object
   * @param An integer value that is the amount health will increase by
   * @return Nothing because it is setting a value
   * */
  public void lowerHealth(int amount){
    if (type==2){
      numHits+=1;
      if (numHits>5){
        this.health=this.health-amount;
      }
    }else{
      this.health=this.health-amount;
    }
  }
  
  /**
   * Shoot
   * Checks to see if the player can shoot by checking if it has a gun and ammo
   * @param Nothing because it is checking a few values for us
   * @return A boolean value stating whether or not the player can shoot or not
   */
  public boolean shoot(){
    
    if(!(this.gun==null) && this.gun.getAmmo()>0){   //if player has gun and has ammo it can shoot
      this.gun.lowerAmmo(1);               //Lower ammo after shooting
      return true;
    }else{           
      return false; 
    }     
  }  
  
  /**
   * setDirectionX
   * This method sets the x direction of movement
   * @param An integer value of the x direction
   * @return Nothing because it is setting a value
   * */
  public void setDirectionX(int xDirection){
    this.xDirection=xDirection;    
  }
  
  /**
   * setDirectionY
   * This method sets the y direction of movement
   * @param An integer value of the y direction
   * @return Nothing because it is setting a value
   * */
  public void setDirectionY(int yDirection){
    this.yDirection=yDirection;      
  }
  
  /**
   * getWeapon
   * This method returns the weapon of an object
   * @param Nothing because it is getting a value
   * @return The weapon object this object has
   * */
  public Weapon getWeapon(){
    return this.gun;     
  }
  
  /**
   * setWeapon
   * This method sets the weapon that the player is equipping
   * @param The weapon object the player is equipping
   * @return Nothing because it is setting a value
   * */
  public void setWeapon(Weapon gun){
    this.gun=gun;   
  }
  
  /**
   * setEquip
   * This method sets the equip status of the weapon the object is currently holding
   * @param a boolean value that it wants to set the equip status to
   * @return Nothing because it is setting a value
   * */
  public void changeEquip(boolean equip){ 
    this.gun.setEquip(equip);
  }
}