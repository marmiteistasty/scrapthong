/*Sound.java
 * Author: Hady Mana
 * Date: June 13, 2017
 * To play sound when created
 */


import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;
import javax.swing.*;


public class Sound extends JFrame {

   public Sound(int type) {
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.setTitle("Test Sound Clip");
      this.setSize(300, 200);
      this.setVisible(false);       
      try {
                  File soundFile;
        if(type==1){
         soundFile = new File("shoot.wav"); 
        }else if(type==2){
           soundFile = new File("death.wav");
        }else{
         soundFile = new File("victory.wav"); 
        }
          AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);              
         Clip clip = AudioSystem.getClip();
         clip.open(audioIn);
         clip.start();
      } catch (UnsupportedAudioFileException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      } catch (LineUnavailableException e) {
         e.printStackTrace();    
      }
   }
}