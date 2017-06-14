/*ScrapTing.java
 * Author: Naymul Mohammed & Andrew Lee
 * Date: June 13, 2017
 * To run the game functions in the java file
 */


import javax.swing.*;
import java.awt.event.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
//This class represents the game window
class GameWindow extends JFrame { 
  
  //Window constructor
  public GameWindow() { 
    setTitle("Scrapting");
    setResizable(false);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // set the window up to end the program when closed  
    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new BorderLayout());
    getContentPane().add( new GamePanel(),BorderLayout.NORTH);   
    pack(); //makes the frame fit the contents
    setVisible(true);
  }
  
// An inner class representing the panel on which the game takes place
  static class GamePanel extends JPanel implements KeyListener{
    
    FrameRate frameRate;
    Clock clock;
    Map map;  
    Player player;
    Enemy enemy;
    HUD health;
    HUD ammo;
    HUD armor;
    int level;
    int playerHealth=1;
    int playerAmmo=1;
    boolean gameOn=true;
    
    
    ArrayList<Bullet> playerBullets = new ArrayList<Bullet>();
    ArrayList<Bullet> enemyBullets= new ArrayList<Bullet>();
    ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    ArrayList<Weapon> weapons=new ArrayList<Weapon>();
    ArrayList<Rectangle> walls=new ArrayList<Rectangle>();
    
    public static int type=Hat.type;//andrew
    boolean enemiesAlive=true;
    int numHits=0;//andrew
    
    
    //constructor
    public GamePanel() { 
      setPreferredSize(new Dimension(1600,1080));
      addKeyListener(this);
      setFocusable(true);
      requestFocusInWindow();
      frameRate = new FrameRate();
      health = new HUD();
      ammo = new HUD();
      clock=new Clock();
      level=1;
      map = new Map(1600,900,this.level);  //Load assets from map
      walls=map.getWalls(); 
      enemies=map.getEnemy();
      weapons=map.getWeapons();
      player=map.getPlayer();
      
      MyMouseListener mouseListener = new MyMouseListener(); //Mouse listener for shooting and other mechanics
      this.addMouseListener(mouseListener);
    }
    
    public void paintComponent(Graphics g) { 
      super.paintComponent(g); //required to ensure the panel si correctly redrawn
      PointerInfo a = MouseInfo.getPointerInfo(); //Get mouse location
      Point b  = a.getLocation();
      double x = b.getX();
      double y =b.getY();
      
      //if game is on load hud
      if(gameOn){
        map.draw(g);
        health.draw(g,this.getWidth()/2-240,950,playerHealth, "Health"); //Draw HUD
        health.drawheart(g,this.getWidth()/2-250,900,100);
        ammo.draw(g,this.getWidth()/2-40,950,playerAmmo,"Ammo");
        ammo.drawammo(g,this.getWidth()/2-50,900);
      }else if( !(gameOn) && (level==5) ){    //if player is passed level 4 game is over
        map.victory(g); 
      }else{
        map.died(g);   
      }
      
      if(!(map.getLevel()==level) ){ //If level is different it means game is done or another level is ready to be played
        if(this.level==5){
          gameOn=false;
        }else{
          map = new Map(1600,900,this.level);
          playerBullets= new ArrayList<Bullet>();   //Load new level if it isn't final level
          enemyBullets= new ArrayList<Bullet>();
          walls=map.getWalls();
          enemies=map.getEnemy();
          weapons=map.getWeapons();
          player=map.getPlayer();
        }
      }
      
      //If game is playable load bullets
      if(gameOn){
        //Update the bullets player shot
        for (int k = 0; k < playerBullets.size(); k++){
          playerBullets.get(k).update(g,clock.getElapsedTime(),walls);   //Bullets travel on their own every second  
          
          for (int r = 0; r < enemies.size(); r++){
            if( (playerBullets.get(k).getBox().intersects(enemies.get(r).getBox())) && (playerBullets.get(k).getAlive()) ){  
              playerBullets.get(k).setAlive(false);
              enemies.get(r).lowerHealth(playerBullets.get(k).getDamage());
            }
          }
        }  
        
        //Update bullets enemies shot
        for (int h = 0; h < enemyBullets.size(); h++){
          enemyBullets.get(h).update(g,clock.getElapsedTime(),walls); //Bullets travel themselves every second    
          
          //If hits player it is no longer live bullet 
          if( (enemyBullets.get(h).getBox().intersects(player.getBox())) && (enemyBullets.get(h).getAlive()) ){
            numHits+=1;//andrew
            if (type==2){ //amdrew
              if (numHits>10){//andrew
                enemyBullets.get(h).setAlive(false);   //If bullet hits player, player loses health and bullet dies
                player.lowerHealth(enemyBullets.get(h).getDamage()); 
              }
            }else{//andrew
              enemyBullets.get(h).setAlive(false);            
              player.lowerHealth(enemyBullets.get(h).getDamage()); 
            }
          }
          //Remove bullets that are not being drawn anymore
          if( !(enemyBullets.get(h).getAlive()) ){
            enemyBullets.remove(h);
          }
        }
        
        player.move(clock.getElapsedTime(),walls); //Update the players postion and draw it
        player.draw(g,x,y);
      }
      
      //If enemies all died level is completed
      if(enemies.size()<=0){
        enemiesAlive=false; 
        level=level+1;
      }
      
      //If game is playable enemies loaded in
      if(gameOn){
        for (int m = 0; m < enemies.size(); m++){
          if( !(enemies.get(m)==null) ){
            enemies.get(m).move(player.getX(),player.getY(),clock.getElapsedTime());  //enemies move based on player location
            
            if(enemies.get(m).getBox().intersects(player.getBox())){
              player.lowerHealth(9999);
              
            }
            enemies.get(m).draw(g,player.getX(),player.getY());
            
            if( (enemies.get(m).shoot()) && (Math.random()<=0.01) ){   //If enemies can shoot add bullets 
              
              if(enemies.get(m).getWeapon().getType()==1){   //Different types of guns shoot different bullets
                enemies.get(m).getWeapon().lowerAmmo(1);
                enemyBullets.add(new Bullet((enemies.get(m).getWeapon().getX()), enemies.get(m).getWeapon().getY(),player.getX(),player.getY(),1)); 
              }  
              if(enemies.get(m).getWeapon().getType()==2){
                enemies.get(m).getWeapon().lowerAmmo(2);
                enemyBullets.add(new Bullet((enemies.get(m).getWeapon().getX()), enemies.get(m).getWeapon().getY(),player.getX(),player.getY(),2)); 
                enemyBullets.add(new Bullet((enemies.get(m).getWeapon().getX()-50), enemies.get(m).getWeapon().getY(),player.getX(),player.getY(),2)); 
              }
              if(enemies.get(m).getWeapon().getType()==3){
                enemies.get(m).getWeapon().lowerAmmo(3);
                enemyBullets.add(new Bullet((enemies.get(m).getWeapon().getX()), enemies.get(m).getWeapon().getY(),player.getX(),player.getY(),3)); 
                enemyBullets.add(new Bullet((enemies.get(m).getWeapon().getX()-50), enemies.get(m).getWeapon().getY(),player.getX(),player.getY(),3)); 
                enemyBullets.add(new Bullet((enemies.get(m).getWeapon().getX()+50), enemies.get(m).getWeapon().getY(),player.getX(),player.getY(),3)); 
              }
            } 
          }
          if(enemies.get(m).getHealth()<=0){       //When enemies dies drop gun and removed from arraylist
            Weapon old= enemies.get(m).getWeapon();
            old.setEquip(false);       
            weapons.add(old);
            enemies.remove(m);   
          }  
        }
        
        //Unequipped weapons get drawn
        for (int p = 0; p < weapons.size(); p++){
          if(!((weapons.get(p)==null))){
            weapons.get(p).draw(g,x,y,player.getX(),player.getY(),1000);
          }
        }
      }
      
      if(player.getHealth()<=0 && gameOn){
        Sound clip= new Sound(2);
        gameOn=false;
        playerBullets= new ArrayList<Bullet>();   //Load new level if it isn't final level
        enemyBullets= new ArrayList<Bullet>();
        map = new Map(1600,900,level);   //Load the same level again if died
        walls=map.getWalls();
        enemies=map.getEnemy();
        weapons=map.getWeapons();
        player=map.getPlayer();
      }
      frameRate.update();              //Update frames when repainted
      frameRate.draw(g,10,10);
      clock.update();
      repaint();     
    }
    
    public void keyTyped(KeyEvent e) {      
    }
    
    public void keyPressed(KeyEvent e) {    //WASD set movement for player
      if(e.getKeyChar() == 'a' ){    
        player.setDirectionX(-1); 
      }
      if(e.getKeyChar() == 's' ){
        player.setDirectionY(1);  
      } 
      if(e.getKeyChar() == 'd' ){    
        player.setDirectionX(1);  
      } 
      if(e.getKeyChar() == 'w' ){  
        player.setDirectionY(-1);
      }
      if(e.getKeyChar() == 'r' && !(gameOn) ){  
        this.gameOn=true;
      }
      if(e.getKeyChar() == 'q' ){
        //Andrew code
        PointerInfo a = MouseInfo.getPointerInfo();
        Point b  = a.getLocation();
        double x = b.getX();
        double y =b.getY();
        
        if (x>player.getX() && player.getX()+10<1600){    //Andrew code 
          player.setX(player.getX()+100);
        }else if (x<player.getX() && player.getX()-10>0){
          player.setX(player.getX()-100);
        }else if (y>player.getY() && player.getY()+10>0 ){
          player.setY(player.getY()+100);
        }else if (y<player.getY() && player.getY()-10<900){
          player.setY(player.getY()-100);
        }
      }
    }
    
    public void keyReleased(KeyEvent e) {      //When WASD key is released player stops moving
      if(e.getKeyChar() == 'a' ){    
        player.setDirectionX(0);
      }
      if(e.getKeyChar() == 's' ){
        player.setDirectionY(0);
      } 
      if(e.getKeyChar() == 'd' ){
        player.setDirectionX(0);
      } 
      if(e.getKeyChar() == 'w' ){
        player.setDirectionY(0);
      }  //note - would be better to make player class and pass in map, test movement in there
    }  
    
    //Inner class so mouselister can access key objects such as the player
    class MyMouseListener implements MouseListener {
      
      public void mouseClicked(MouseEvent e) {   
        //Checks to see if you equipped a gun already during the click event
        boolean equip=false;
        
        //Check to see if any unequiped weapons are near the player to equip when right click is pressed
        for (int i = 0; i < weapons.size(); i++){
          if ( (e.getButton() == MouseEvent.BUTTON3) && (player.getWeapon()==null) && player.getBox().intersects(weapons.get(i).getBox()) ){
            player.setWeapon(weapons.get(i));
            player.changeEquip(true);
            equip=true;
            weapons.remove(i);   
          }   
        }
        
        //If didn't equip a gun from click then equip a gun if you had one from before
        if( (e.getButton() == MouseEvent.BUTTON3) && !(player.getWeapon()==null) && player.getWeapon().getEquip()==true && !equip){
          player.changeEquip(false);
          weapons.add(player.getWeapon()); 
          player.setWeapon(null);
        }   
      }
      
      public void mousePressed(MouseEvent e) {
        if( (player.shoot()) && (e.getButton() == MouseEvent.BUTTON1) ){   //If player can shoot and mouse button 1 is pressed add bullets
          if(player.getWeapon().getType()==1){ //Different types of guns generate different bullet(s)
            player.getWeapon().lowerAmmo(1);
            playerBullets.add(new Bullet(player.getWeapon().getX(), player.getWeapon().getY(),e.getX(),e.getY(), 1));
          }  
          if(player.getWeapon().getType()==2){
            player.getWeapon().lowerAmmo(1);
            playerBullets.add(new Bullet(player.getWeapon().getX(), player.getWeapon().getY(),e.getX(),e.getY(), 2));
            playerBullets.add(new Bullet(player.getWeapon().getX()-50, player.getWeapon().getY(),e.getX(),e.getY(), 2));
          }  
          if(player.getWeapon().getType()==3){
            player.getWeapon().lowerAmmo(3);
            playerBullets.add(new Bullet(player.getWeapon().getX(), player.getWeapon().getY(),e.getX(),e.getY(), 3));
            playerBullets.add(new Bullet(player.getWeapon().getX()-50, player.getWeapon().getY(),e.getX(),e.getY(), 3));
            playerBullets.add(new Bullet(player.getWeapon().getX()+50, player.getWeapon().getY(),e.getX(),e.getY(),3));
          }  
        }   
      }
      public void mouseReleased(MouseEvent e) {
      }      
      public void mouseEntered(MouseEvent e) {
      }
      public void mouseExited(MouseEvent e) {
      }
    }
  }
}