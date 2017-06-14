/*Enemy.java
 * Author: Naymul Mohammed
 * Date: June 13, 2017
 * To be a moveable object that fights the player
 */

import java.awt.*;
import java.io.*; 
import java.awt.image.*;
import javax.imageio.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

class Enemy extends Entity{
  
  private  BufferedImage enemySprite;   
  private Weapon enemyGun;
  private int health;
  private double xChange=0;
  private double yChange=0;
  private double distance;
  private int type;
  
  /**
   * Enemy
   * This is a contructor for the enemy object that is part of the game
   * @param 2 double values that are the objects coordinate, an integer value of its health, an integer value of its type, and a Weapon object it has
   * @return Nothing because it is a constructor
   */
  Enemy(double x, double y, int health, int type, Weapon enemyGun){ 
    super(x,y,new Rectangle((int)x, (int)y, 35, 35));
    loadSprites();
    this.health=health;
    this.enemyGun=enemyGun; 
    this.type=type;
  }
  
  /**
   * loadSprites
   * This loads the sprites of the object
   * @param Nothing because thge values are already in the method
   * @return Nothing because it just loads the file in the method
   */
  public void loadSprites() { 
    try {
      if(type==1){
        enemySprite = ImageIO.read(new File("enemySprite.png"));
      }else if(type==2){
        
        enemySprite = ImageIO.read(new File("enemySpriteTwo.png"));
      }else if(type==3){
        enemySprite = ImageIO.read(new File("enemySpriteThree.png"));
      }else{
        enemySprite = ImageIO.read(new File("enemySprite.png"));
      }
    } catch(Exception e) { System.out.println("error loading sprite");};
  }
  /**
   * draw
   * This draws the enemy on the screen
   * @param Graphics object to draw it and the two double values of it's X and Y coordinates
   * @return Nothing because it is drawing the object
   */
  public void draw(Graphics g, double x, double y) {   
    
    Graphics2D g2d=(Graphics2D)g;
    AffineTransform rotateEnemy=new AffineTransform();
    rotateEnemy.translate(this.getX(),this.getY());
    
    
    if(distance<=500){
      rotateEnemy.rotate(Math.atan2(this.getY()-y,this.getX()-x));  //If less than 500 units than enemys look towards player
    }
    
    rotateEnemy.translate(-enemySprite.getWidth()/2,-enemySprite.getHeight()/2);
    g2d.drawImage(enemySprite,rotateEnemy,null);
    
    if(!(this.enemyGun==null)){
      this.enemyGun.draw(g,x,y,this.getX(),this.getY(),(int)this.distance); //If gun is equipped then draw the gun
    }  
  }
  /**
   * Move
   * This moves the enemy object based on the player's postion and time elapsed
   * @param 2 integer values that are the player's X and Y, and the time elapsed
   * @return Nothing because it is just moving the object
   */
  public void move(double playerX, double playerY, double timeElapsed) { 
    
    this.distance =Math.abs( Math.sqrt(Math.pow((playerX-this.getX()), 2) + Math.pow((playerY-this.getY()), 2))); //Calculates distance between enemy and player
    if(distance<=500){  //If distance between enemy and player is less than 500 it will travel towards it
      
      if(playerX==this.getX()){ //Only moves if player coordinates are different from enemy coordinates
        
        xChange=0; 
      }else{ 
        xChange=-1;   
      }
      
      if(playerY==this.getY()){      
        yChange=0; 
      }else{   
        yChange=-1;  
      }
      
      //Use trig to find path for enemy to travel towards the player
      this.setY(this.getY()+this.yChange*((250*timeElapsed)*Math.sin(Math.atan2(this.getY()-playerY,this.getX()-playerX))));
      this.setX(this.getX()+ this.xChange*((250*timeElapsed)*Math.cos(Math.atan2(this.getY()-playerY,this.getX()-playerX)))); 
      this.setBox((int)this.getX(),(int)this.getY());
      this.distance =Math.abs( Math.sqrt(Math.pow((playerX-this.getX()), 2) + Math.pow((playerY-this.getY()), 2)));
      xChange=0;
      yChange=0;
    }
  }
  
  /**
   * Shoot
   * Checks to see if the enemy can shoot by checking ammo and distance from player
   * @param Nothing because it is checking a few values for us
   * @return A boolean value stating whether or not the enemy can shoot or not
   */
  public boolean shoot() { 
    if(this.enemyGun.getAmmo()>0 && this.distance<=500){ //Checks to see if player is less than 500 units for it to shoot
      return true; 
    }else{
      return false; 
    }  
  }
  
  /**
   * getWeapon
   * This method returns the weapon of an object
   * @param Nothing because it is getting a value
   * @return The weapon object this object has
   * */
  public Weapon getWeapon(){
    return this.enemyGun; 
  }
  
  /**
   * lowerHealth
   * This method lowers the health of on object
   * @param An integer value that is the amount health will increase by
   * @return Nothing because it is setting a value
   * */
  public void lowerHealth(int amount){ 
    this.health=this.health-amount; 
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
}