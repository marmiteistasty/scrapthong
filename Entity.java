/*Entity object
 * Author: Naymul Mohammed
 * May 25,2017
 * Parent class that is inherited by everything
 */
import java.awt.*;

class Entity{
  
  private double x;
  private double y;
  private Rectangle boundingBox;
  
  Entity(){
  }
  
  /**
   * Entity
   * This is a contructor for any object that is part of the game
   * @param 2 double values that are the objects coordinates and a Rectangle object that is owned by the object
   * @return Nothing because it is a constructor
   */
  Entity(double x, double y, Rectangle boundingBox){
    this.x=x;
    this.y=y;
    this.boundingBox=boundingBox;  
  }
  
  /**
   * getX
   * This method returns the X value of an object
   * @param Nothing because it is getting a value
   * @return A double value that is the objects X coordinate
   * */
  public double getX(){
    return this.x;
  }
  
  /**
   * getY
   * This method returns the Y value of an object
   * @param Nothing because it is getting a value
   * @return A double value that is the objects Y coordinate
   * */
  public double getY(){
    return this.y;
  }
  /**
   * setX
   * This method sets the X value of an object
   * @param A double value that sets objects X coordinate
   * @return Nothing because it is setting a value
   * */
  public void setX(double x){
    this.x=x;
  }
  
  /**
   * setY
   * This method sets the Y value of an object
   * @param A double value that sets objects Y coordinate
   * @return Nothing because it is setting a value
   * */
  public void setY(double y){
    this.y=y;
  }
  
  /**
   * getBox
   * This method returns the Rectangle of an object
   * @param Nothing because it is getting a value
   * @return the rectangle object belonging to the object that has it
   * */
  public Rectangle getBox(){
    return this.boundingBox; 
  }
  
  /**
   * setBox
   * This method sets the X and Y value of it's Rectangle object
   * @param Two double values that sets objects X and Y coordinate
   * @return Nothing because it is setting values
   * */
  public void setBox(int x, int y){
    this.boundingBox.x=x;
    this.boundingBox.y=y;
  }
}
