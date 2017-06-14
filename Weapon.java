/* Weapon.java
 * Author: Naymul Mohammed
 * To be able to interact with players and shoot 
 */

import java.awt.Graphics;

import java.awt.*;
import java.io.*; 
import java.awt.image.*;
import javax.imageio.*;
import java.awt.geom.AffineTransform;

class Weapon extends Entity{
  
  private int ammo;
  private int type;
  private boolean equip;
  private BufferedImage weaponSprite;
  
  /**
   * Weapon
   * This is a contructor for the weapon object
   * @param 2 double values that are the objects coordinate, an integer value of its type and another for its ammo, and a boolean value for its equip status
   * @return Nothing because it is a constructor
   */
  Weapon(double x, double y,int type, int ammo,boolean equip) {
    super(x,y,new Rectangle((int)x, (int)y, 150, 150));
    this.type=type;
    this.ammo=ammo;
    this.equip=equip;
    loadSprites();
  }
  
  /**
   * loadSprites
   * This loads the sprites of the object
   * @param Nothing because thge values are already in the method
   * @return Nothing because it just loads the file in the method
   */
  public void loadSprites() { 
    try {
      if(this.type==1){
        weaponSprite = ImageIO.read(new File("weaponSpriteOne.png"));   //Different types of guns have different sprites
      }else if(type==2){
        weaponSprite = ImageIO.read(new File("weaponSpriteTwo.png")); 
      }else if(type==3){
        weaponSprite = ImageIO.read(new File("weaponSpriteThree.png")); 
      }else if(type==4){  
        weaponSprite = ImageIO.read(new File("weaponSpriteFour.png"));  
      }else{
        weaponSprite = ImageIO.read(new File("weaponSpriteOne.png"));  
      }
    } catch(Exception e) { System.out.println("error loading sprite");};
  }
  
  /**
   * draw
   * This draws weapon screen 
   * @param Graphics object, 4 integer values for the entity equipping it X and Y and the target X and Y, and an integer for the distance it is away from an entity
   * @return Nothing because it is drawing the object
   */
  public void draw(Graphics g,double targetX,double targetY,double entityX,double entityY,int distance) { 
    
    Graphics2D g2d=(Graphics2D)g;
    AffineTransform rotateWeapon=new AffineTransform();
    
    if( this.getEquip() ){   //If equiped its X and Y is based on the entity that has it equipped 
      this.setX(entityX);
      this.setY(entityY);
      
      rotateWeapon.translate(this.getX(),this.getY());
      
      if(distance<=500){
        rotateWeapon.rotate(Math.atan2(this.getY()-targetY,this.getX()-targetX) );  //If the entity is a enemy it will only look at the player when it is less than 500 units away
      }   
      rotateWeapon.translate(-weaponSprite.getWidth()/2,-weaponSprite.getHeight()/2);
      g2d.drawImage(weaponSprite,rotateWeapon,null);
      this.setBox((int)this.getX(),(int)this.getY());
    }else{
      rotateWeapon.translate(this.getX(),this.getY()); //If equipped by enemy and not close to player then gun won't rotate towards player
      g2d.drawImage(weaponSprite,rotateWeapon,null);
      this.setBox((int)this.getX(),(int)this.getY());
    }  
  }
  
  /**
   * setEquip
   * This method sets the equip status of the weapon the object is currently holding
   * @param a boolean value that it wants to set the equip status to
   * @return Nothing because it is setting a value
   * */
  public void setEquip(boolean equip){
    this.equip=equip;   
  }
  
  /**
   * getEquip
   * This method returns the equip status of the weapon the object is currently holding
   * @param Nothing because it is getting a value
   * @return A boolean value representing the equip status of the weapon
   * */
  public boolean getEquip(){
    return this.equip;  
  }
  /**
   * lowerAmmo
   * This method lowers the ammo by the entered amount
   * @param Integer value representing how much the ammo is being decreased by
   * @return Nothing because it is setting a value
   * */
  public void lowerAmmo(int ammo){
    this.ammo=this.ammo-ammo;  
  }
  /**
   * getAmmo
   * This method returns the ammo of an object
   * @param Nothing because it is getting a value
   * @return A integer value that is the objects ammo
   * */
  public int getAmmo(){
    return this.ammo; 
  }
  
  /**
   * getType
   * This method returns the integer value of the type of the weapon
   * @param Nothing because it is getting a value
   * @return A integer value that is the type of the weapon
   * */
  public int getType(){
    return this.type; 
  }  
}