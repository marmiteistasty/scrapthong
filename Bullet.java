/*Bullets.java
 * To create bullets for the guns when shot
 * Author: Naymul Mohammed
 * Date: June 13, 2017
 */

import java.awt.image.*;
import java.awt.Graphics;
import java.awt.*;
import java.io.*; 
import javax.imageio.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

class Bullet extends Entity{
  
  private double changeX;
  private double changeY;
  private double endingX;
  private double endingY;
  private boolean alive;
  private BufferedImage bullet;
  private int damage;
  private int speed;
  
  
  /**
   * Bullet
   * This is a contructor for the bullet object part of the game
   * @param 2 double values that are the objects coordinate, 2 double values that are the ending X and Y coordinates, and the interger value representing the type of bullet
   * @return Nothing because it is a constructor
   */
  Bullet(double x, double y, double endingX, double endingY,int type){
    super(x,y,new Rectangle((int)x, (int)y, 50, 50));
    Sound clip= new Sound(1);  //Plays the sound effect for bullet when new bullet is created
    this.endingX=endingX;
    this.endingY=endingY;
    this.loadSprites();
    this.alive=true;
    if(type==1){          //Sets its damage and speed based on its type
      this.damage=500;
      this.speed=2000;
    }else if(type==2){
      this.damage=1000;
      this.speed=2500;
    }else if(type==3){
      this.damage=1000;
      this.speed=3000;
    } 
  }
  
  /**
   * loadSprites
   * This loads the sprites of the object
   * @param Nothing because thge values are already in the method
   * @return Nothing because it just loads the file in the method
   */
  public void loadSprites() { 
    try {
      bullet = ImageIO.read(new File("bullet.png"));  
    } catch(Exception e) { System.out.println("error loading sprite");};
  }
  
  /**
   * draw
   * This draws the bullet on the screen rotated towards the target 
   * @param Graphics object
   * @return Nothing because it is drawing the object
   */
  public void draw(Graphics g) { 
    if(alive){
      Graphics2D g2d=(Graphics2D)g;
      AffineTransform rotateBullet=new AffineTransform();
      rotateBullet.translate(this.getX(),this.getY()); 
      rotateBullet.rotate(Math.atan2(this.getY()-this.endingY,this.getX()-this.endingX) ); //Bullet roates towards the target
      g2d.drawImage(bullet,rotateBullet,null);  
    }
  }
  
  /**
   * update
   * This method updates the position of the bullet and draws it when done
   * @param Graphics object, double value of time elapsed and an arraylist of the object Rectangle
   * @return Nothing because it is just updating the position
   * */
  public void update(Graphics g, double timeElapsed,ArrayList<Rectangle> walls) { 
    
    changeX = (endingX - this.getX());
    changeY= (endingY - this.getY());
    double  direction = Math.atan2(changeY,changeX); // Math.atan2(deltaY, deltaX) does the same thing but checks for deltaX being zero to prevent divide-by-zero exceptions
    
    //Checks to see if the bullet is within the game borders
    if( (this.getX()>=0) && (this.getX()<=1600) && (this.getY()>=0) && (this.getY()<=900) )  {
      
      this.setX(this.getX() +(timeElapsed*speed* Math.cos(direction)));      //Move the bullet towards the target direction and then goes on until hits borders of game
      this.endingX=(this.getX() + (timeElapsed*speed* Math.cos(direction))); 
      this.setY(this.getY() + (timeElapsed*speed*Math.sin(direction)));
      this.endingY=(this.getY() + (timeElapsed*speed*Math.sin(direction)));
      
      for (int r = 0; r < walls.size(); r++){     //If bullet hits wall then it stops
        if(this.getBox().intersects(walls.get(r))){
          this.alive=false;
        }
      }
      this.draw(g);      //Draw the bullet once it has updated 
    }else{ 
      this.alive=false;  //If bullet is outside of game window its not alive anymore
    }
    this.setBox((int)this.getX(),(int)this.getY());
  }
  
  /**
   * getAlive
   * This method returns the boolean value if the object is alive
   * @param Nothing because you are getting a value
   * @return A boolean value that states whether or not the bullet is alive
   * */
  public boolean getAlive(){
    return this.alive; 
  }
  
  /**
   * setAlive
   * This method sets if the bullet is alive or not
   * @param A boolean value that is stating whether or not the bullet is alive
   * @return Nothing because it is setting a value\
   * */  
  public void setAlive(boolean alive){
    this.alive=alive; 
  }
  
  /**
   * getDamage
   * This method returns the damage of the bullet
   * @param Nothing because it is getting a value
   * @return An integer that is the value of damage of the bullet
   * */
  public int getDamage(){
    return this.damage;   
  }  
}