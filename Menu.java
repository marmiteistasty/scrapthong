/*Menu class
 * starting menu screen
 * andrew lee
 */


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;


import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;




import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;





class Menu extends JFrame{
  
  
    
  //class variables - needed outside main method
 
  
  
  //Constructor
  Menu() { 
  
    //Set up the Frame
     setTitle("Scrapting");
     setPreferredSize(new Dimension(1600,1100));
    setResizable(false);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // set the window up to end the program when closed
  

    //Set up the Panels
    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS)); // <-- my frame is set up as a grid layout. 0 means unlimited rows
   
    setFocusable(true);
      requestFocusInWindow();
      
    JPanel centerPanel = new JPanel();
    centerPanel.setLayout(new FlowLayout());
    centerPanel.setPreferredSize(new Dimension(100, 190));

JPanel buttonPanel = new JPanel();

    // Set up the Components
    
   
    JButton newgameButton = new JButton("New Game");
    newgameButton.addActionListener(new okButtonListener());
    
  JButton loadgameButton = new JButton("Load Game");
    loadgameButton.addActionListener(new okButtonListener());
    
    JButton instructionButton = new JButton("Instructions");
    instructionButton.addActionListener(new okButtonListener());
    
    JButton quitButton = new JButton("Quit Game");
    quitButton.addActionListener(new QuitListener());
    
    
    //Build Window
    
 
                             
    centerPanel.add(newgameButton);                     
                       centerPanel.add(loadgameButton);                 
    centerPanel.add(instructionButton);                 
        centerPanel.add(quitButton);                 
    
        buttonPanel.add(centerPanel,BorderLayout.CENTER);
        
        mainPanel.add(buttonPanel,BorderLayout.CENTER);
        
        this.add(mainPanel);
    
    
    //Start the app
    pack(); //makes the frame fit the contents
    setVisible(true);
  }
  
  
  //Inner class - to listen for button click
  
 class okButtonListener implements ActionListener {  //this is the required class definition
    public void actionPerformed(ActionEvent event)  {  
  
    
  
     Hat select=new Hat(true);
     setVisible(false);
     
     
    }
    
    
 }
 
 
 //quit button
 class QuitListener implements ActionListener{
    public void actionPerformed(ActionEvent e) {
        System.exit(0);
    }
}
    
      
      
    }

  
  
