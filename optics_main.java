//A Maria Cheng Production
//December 18, 2013
//GUI Assignment - Reflection and Refraction
//ICS 4U1 - Mr. Cadawas
//Main program

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.event.*;

public class optics implements ActionListener, ChangeListener{
  //Properties
  public JFrame theframe;
  public JFrame aboutframe;
  public JFrame helpframe;
  public JFrame testframe;
  public Helpanimation aboutpanel;
  public Helpanimation helppanel;
  public JPanel testpanel;
  public Opticsanimation thepanel;
  public JMenuBar themenubar;
  public JMenu optionsmenu;
  public JMenuItem about;
  public JMenuItem help;
  public JMenuItem test;
  public JButton helpbut;
  public JButton donebut;
  public JSlider heightslide;
  public JSlider distanceslide;
  public Timer thetimer;
  public JLabel tlabel1;
  public JLabel tlabel2;
  public JLabel tlabel3;
  public JLabel tlabel4;
  public JLabel tlabel5;
  public JLabel tlabel6;
  public JLabel finallabel;
  public JTextField ttxtfield1;
  public JTextField ttxtfield2;
  public JTextField ttxtfield3;
  public JTextField ttxtfield4;
  public JTextField ttxtfield5;
  public JTextField namefield;
  public int intScore = 0;
  public String strName;
  
  //Methods
  public void stateChanged(ChangeEvent evt){
    if(evt.getSource() == heightslide){
      thepanel.intHeight = heightslide.getValue();
    }else if(evt.getSource() == distanceslide){
      thepanel.intDistance = distanceslide.getValue();
    }
  }
  public void actionPerformed(ActionEvent evt){
    if(evt.getSource() == thetimer){
      thepanel.repaint();
    }else if(evt.getSource() == help){//Help menu item
      helpframe.setSize(410, 430);
      helppanel.setLayout(null);
      helpframe.setContentPane(helppanel);
      helpframe.setVisible(true);
    }else if(evt.getSource() == helpbut){ //Next button clicked in help screen
      helppanel.repaint();
      helppanel.intscreen++;
    }else if(evt.getSource() == about){//About menu item
      aboutframe.setSize(410, 430);
      aboutpanel.setLayout(null);
      aboutframe.setContentPane(aboutpanel);
      aboutframe.setVisible(true);
      aboutpanel.intscreen = 4;
      aboutpanel.repaint();
    }else if(evt.getSource() == test){//Test menu item
      testframe.setSize(575, 430);
      testpanel.setLayout(null);
      testframe.setContentPane(testpanel);
      testframe.setVisible(true);
    }else if(evt.getSource() == donebut){//Test results
      if(ttxtfield1.getText().equalsIgnoreCase("true")){
        intScore++;
      }if(ttxtfield2.getText().equalsIgnoreCase("6")){
        intScore++;
      }if(ttxtfield3.getText().equalsIgnoreCase("concave")){
        intScore++;
      }if(ttxtfield4.getText().equalsIgnoreCase("reflection")){
        intScore++;
      }if(ttxtfield5.getText().equalsIgnoreCase("bending")){
        intScore++;
      }
      strName = namefield.getText();
      finallabel.setText(strName +", your score is: "+intScore+"/5");
      intScore = 0;
    }
  }
  //Constructors
  public optics(){
    theframe = new JFrame("Reflection and Refraction");
    theframe.setSize(800, 800);
    theframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    thepanel = new Opticsanimation();
    thepanel.setLayout(null);
    thepanel.setBackground(new Color(250, 249, 210));
    theframe.setContentPane(thepanel);
    
    //Menu
    themenubar = new JMenuBar();
    themenubar.setSize(800, 20);
    themenubar.setLocation(0, 0);
    thepanel.add(themenubar); 
    optionsmenu = new JMenu("Options");
    themenubar.add(optionsmenu);
    
    //Help - Menu
    help = new JMenuItem("Help");
    help.addActionListener(this);
    optionsmenu.add(help);
    helpframe = new JFrame("Help");
    helppanel = new Helpanimation();
    helpbut = new JButton("Next");
    helpbut.setSize(60, 30);
    helpbut.setLocation(320, 5);
    helpbut.addActionListener(this);
    helppanel.add(helpbut);
    
    //About - Menu
    optionsmenu.addSeparator();
    about = new JMenuItem("About");
    about.addActionListener(this);
    optionsmenu.add(about);
    aboutframe = new JFrame("About");
    aboutpanel = new Helpanimation();
    
    //Test - Menu
    optionsmenu.addSeparator();
    test =  new JMenuItem("Test");
    test.addActionListener(this);
    optionsmenu.add(test);
    testframe = new JFrame("Test");
    testpanel = new JPanel();
    
    tlabel1 = new JLabel("Test - How well do you know reflection and refraction?");
    tlabel1.setLocation(5, 5);
    tlabel1.setSize(400, 20);
    testpanel.add(tlabel1);
    tlabel2 = new JLabel("1. True or false? Dispersion is the splitting of white light into its various frequencies?");
    tlabel2.setLocation(5, 30);
    tlabel2.setSize(500, 20);
    testpanel.add(tlabel2);
    ttxtfield1 = new JTextField();
    ttxtfield1.setLocation(2, 60);
    ttxtfield1.setSize(490, 20);
    ttxtfield1.addActionListener(this);
    testpanel.add(ttxtfield1);
    tlabel3 = new JLabel("Calculate height of an image in a diverging mirror with focal length 6cm, 4cm away from object");
    tlabel3.setLocation(5, 90);
    tlabel3.setSize(550, 20);
    testpanel.add(tlabel3);
    ttxtfield2 = new JTextField();
    ttxtfield2.setLocation(2, 120);
    ttxtfield2.setSize(490, 20);
    ttxtfield2.addActionListener(this);
    testpanel.add(ttxtfield2);
    tlabel4 = new JLabel("A converging mirror is also known as a ______ mirror");
    tlabel4.setLocation(5, 150);
    tlabel4.setSize(500, 20);
    testpanel.add(tlabel4);
    ttxtfield3 = new JTextField();
    ttxtfield3.setLocation(2, 180);
    ttxtfield3.setSize(490, 20);
    ttxtfield3.addActionListener(this);
    testpanel.add(ttxtfield3);
    tlabel5 = new JLabel("The law of reflection states that the angle of incidence is always equal to the angle of ______");
    tlabel5.setLocation(5, 210);
    tlabel5.setSize(550, 20);
    testpanel.add(tlabel5);
    ttxtfield4 = new JTextField();
    ttxtfield4.setLocation(2, 240);
    ttxtfield4.setSize(490, 20);
    ttxtfield4.addActionListener(this);
    testpanel.add(ttxtfield4);
    tlabel6 = new JLabel("Refraction is the ______ of light");
    tlabel6.setLocation(5, 270);
    tlabel6.setSize(500, 20);
    testpanel.add(tlabel6);
    ttxtfield5 = new JTextField();
    ttxtfield5.setLocation(2, 300);
    ttxtfield5.setSize(490, 20);
    ttxtfield5.addActionListener(this);
    testpanel.add(ttxtfield5);
    namefield = new JTextField("Name");
    namefield.setLocation(150, 330);
    namefield.setSize(100, 20);
    namefield.addActionListener(this);
    testpanel.add(namefield);
    donebut = new JButton("Finish");
    donebut.setLocation(150, 360);
    donebut.setSize(100, 20);
    donebut.addActionListener(this);
    testpanel.add(donebut);
    finallabel = new JLabel("Your score is:");
    finallabel.setLocation(300, 360);
    finallabel.setSize(200, 20);
    testpanel.add(finallabel);
    
    //Sliders
    heightslide = new JSlider(0, 10, 5);
    heightslide.setBounds(500, 50, 200, 50);
    heightslide.setMajorTickSpacing(5);
    heightslide.setMinorTickSpacing(1);
    heightslide.setPaintLabels(true);
    heightslide.setPaintTicks(true);
    heightslide.addChangeListener(this);
    heightslide.setPaintTrack(true);
    thepanel.add(heightslide);
    
    distanceslide = new JSlider(0, 10, 5);
    distanceslide.setBounds(500, 150, 200, 50);
    distanceslide.setMajorTickSpacing(5);
    distanceslide.setMinorTickSpacing(1);
    distanceslide.setPaintLabels(true);
    distanceslide.setPaintTicks(true);
    distanceslide.addChangeListener(this);
    distanceslide.setPaintTrack(true);
    thepanel.add(distanceslide);
    theframe.setVisible(true);
    
    thetimer = new Timer(1000/5000, this); //1000/30 (30 times per second)
    thetimer.start();
    
  }
  //Main
  public static void main(String[]args){
    optics main = new optics();
  }
}
