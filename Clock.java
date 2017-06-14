/*Clock.java
 * Author: Naymul & Mangat
 * Date: June 13,2017
 * Thanks for the code
 */

class Clock {
  long elapsedTime;
  long lastTimeCheck;
  
  public Clock() { 
    lastTimeCheck=System.nanoTime();
    elapsedTime=0;
  }
  
  public void update() {
    long currentTime = System.nanoTime();  //if the computer is fast you need more precision
    elapsedTime=currentTime - lastTimeCheck;
    lastTimeCheck=currentTime;
  }
  
  //return elapsed time in milliseconds
  public double getElapsedTime() {
    return elapsedTime/1.0E9;
  }
}