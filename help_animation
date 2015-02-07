//A Maria Cheng Production
//December 18, 2013
//GUI Assignment - Reflection and Refraction
//ICS 4U1 - Mr. Cadawas
//Animation panel for help panel and about panel

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.File;
import java.io.IOException;

public class Helpanimation extends JPanel{
  //Properties
  BufferedImage helpscreen = null;
  BufferedImage helpscreen2 = null;
  BufferedImage helpscreen3 = null;
  BufferedImage aboutscreen = null;
  BufferedImage reflect = null;
  int intscreen = 1;
  //Methods
  public void paintComponent(Graphics g){
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D)g;
    try{
      helpscreen = ImageIO.read(new File("help.png"));
      helpscreen2 = ImageIO.read(new File("help2.png"));
      helpscreen3 = ImageIO.read(new File("help3.png"));
      aboutscreen = ImageIO.read(new File("about.png"));
      reflect = ImageIO.read(new File("reflection.gif"));
    }catch(IOException e){
      System.out.println("Unable to open image file.");
    }
    //1-3 help panel, 4 is about panel
    if(intscreen == 1){
      g2d.drawImage(helpscreen, 0, 0, null);
      g2d.drawImage(reflect, 85, 270, null);
    }else if(intscreen == 2){
      g2d.drawImage(helpscreen2, 0, 0, null);
    }else if(intscreen == 3){
      g2d.drawImage(helpscreen3, 0, 0, null);
      intscreen = 0;
    }else if(intscreen == 4){
      g2d.drawImage(aboutscreen, 0, 0, null);
      intscreen = 4;
    }
  }
  //Constructors
  public Helpanimation(){
    super();
  }
}
