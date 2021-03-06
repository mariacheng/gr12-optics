//A Maria Cheng Production
//December 18, 2013
//GUI Assignment - Reflection and Refraction
//ICS 4U1 - Mr. Cadawas
//Animation panel for optics main

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.awt.geom.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.File;
import java.io.IOException;

public class Opticsanimation extends JPanel{
  //Properties
  public int intHeight = 5;
  public int intDistance = 5;
  public int int1x1 = 70;
  public int int1x2 = 80;
  public int int2y = 50;
  public int int3x = 75;
  public int int3y = 55;
  public double dblslope1;
  public double dblslope2;
  public Line2D.Double line1;
  public Line2D.Double line2;
  public Line2D.Double line3;
  public Line2D.Double line4;
  public Line2D.Double line5;
  public Line2D.Double lineint1;
  public Line2D.Double lineint2;
  public Line2D.Double lineint3;
  public Line2D.Double lineint4;
  public Point poi1;
  public Point poi2;
  BufferedImage index = null;

  //Methods
  public void paintComponent(Graphics g){
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D)g;
//    g2d.clearRect(0, 0, 800, 900);
    try{
      index = ImageIO.read(new File("index.png"));
    }catch(IOException e){
      System.out.println("Unable to open image file.");
    }
    g2d.setColor(Color.BLACK);
    g2d.setStroke(new BasicStroke(2));
    g2d.drawLine(450, 5, 450, 820);
    g2d.drawImage(index, 500, 400, null);
    int3x = darrowdimensions(int3x, 0, intDistance);
    int1x1 = darrowdimensions(int1x1, -5, intDistance);
    int1x2 = darrowdimensions(int1x2, 5, intDistance);
    g2d.drawString("height", 580, 35);
    g2d.drawString("distance", 575, 135);

    //Converging Mirror
    g2d.drawString("Converging Mirror", 165, 40);
    g2d.drawLine(50, 125, 400, 125);
    g2d.drawArc(115, 50, 100, 150, 270, 180);
    int3y = harrowdimensions(int3y, 125, 0, intHeight);
    int2y = harrowdimensions(int3y, 125, 5, intHeight);
    drawArrow(int1x1, int1x2, int2y, int3x, int3y, 125, g2d);
    lineint1 = cray1(intHeight, intDistance, int3x, int3y, g2d);
    lineint2 = cray2(intHeight, intDistance, int3x, int3y, g2d);
    double slope1 = slope(lineint1);
    double slope2 = slope(lineint2);
    poi1 = POI(slope1, lineint1.getX1(), lineint1.getY1(), slope2, lineint2.getX1(), lineint2.getY1());
//    System.out.println(poi1.getX()+" "+poi1.getY());
    if(intHeight>0 && poi1.getX()>0 && poi1.getX()<450){
      g2d.setColor(Color.BLUE);
      if(intDistance != 10){
        drawArrow((int)poi1.getX() - 5, (int)poi1.getX() + 5, (int)poi1.getY() - 5, (int)poi1.getX(), (int)poi1.getY(), 125, g2d);
      }else if(intDistance == 10 && intHeight == 1 || intDistance == 10 && intHeight == 2){
        drawArrow((int)poi1.getX() - 5, (int)poi1.getX() + 5, (int)poi1.getY() + 5, (int)poi1.getX(), (int)poi1.getY(), 125, g2d);
      }else if(intDistance == 10 && intHeight == 9 || intDistance == 10 && intHeight == 10){
        drawArrow((int)poi1.getX() - 5, (int)poi1.getX() + 5, (int)poi1.getY() - 5, (int)poi1.getX(), (int)poi1.getY(), 125, g2d);
      }
      g2d.setColor(Color.BLACK);
    }else if(poi1.getX()<0 || poi1.getX()>450){
      if(intDistance == 9 && intHeight == 1 || intDistance == 9 && intHeight == 2 || intDistance == 9 && intHeight == 3 || intDistance == 9 && intHeight == 4){
        g2d.drawString("Error - Lines are parallel", 20, 40);
      }else{
        g2d.drawString("Error - Off the screen", 20, 40);
      }
    }
//    //Diverging Mirror
//    g2d.drawLine(50, 325, 400, 325);
//    g2d.drawArc(215, 250, 100, 150, 90, 180);
//    int3y = harrowdimensions(int3y, 325, 0, intHeight);
//    int2y = harrowdimensions(int3y, 325, 5, intHeight);
//    drawArrow(int1x1, int1x2, int2y, int3x, int3y, 325, g2d);

    //Converging Lens
    g2d.drawString("Converging Lens", 165, 440);
    g2d.drawLine(50, 525, 400, 525);
    g2d.drawOval(200, 450, 25, 150);
    g2d.drawLine(212, 450, 212, 600);
    int3y = harrowdimensions(int3y, 525, 0, intHeight);
    int2y = harrowdimensions(int3y, 525, 5, intHeight);
    drawArrow(int1x1, int1x2, int2y, int3x, int3y, 525, g2d);
    lineint3 = lensray1(intHeight, intDistance, int3x, int3y, g2d);
    lineint4 = lensray2(intHeight, intDistance, int3x, int3y, g2d);
    double slope3 = slope(lineint3);
    double slope4 = slope(lineint4);
    poi2 = POI(slope3, lineint3.getX1(), lineint3.getY1(), slope4, lineint4.getX1(), lineint4.getY1());
    if(intHeight>0 && poi2.getX()>0 && poi2.getX()<450){
      g2d.setColor(Color.BLUE);
      if(intDistance != 10){
        drawArrow((int)poi2.getX() - 5, (int)poi2.getX() + 5, (int)poi2.getY() - 5, (int)poi2.getX(), (int)poi2.getY(), 525, g2d);
      }else if(intDistance == 10){
        drawArrow((int)poi2.getX() - 5, (int)poi2.getX() + 5, (int)poi2.getY() + 5, (int)poi2.getX(), (int)poi2.getY(), 525, g2d);
      }
      g2d.setColor(Color.BLACK);
    }else if(poi2.getX()<0 || poi2.getX()>450){
      if(intDistance == 9){
        g2d.drawString("Error - Lines are parallel", 20, 440);
      }else{
        g2d.drawString("Error - Off the screen", 20, 440);
      }
    }
    //Focal Point
    g2d.drawLine(165, 120, 165, 130);
//    g2d.drawLine(165, 320, 165, 330);
    g2d.drawLine(165, 520, 165, 530);

    //Virtual Focal Point
    g2d.drawLine(260, 120, 260, 130);
//    g2d.drawLine(260, 320, 260, 330);
    g2d.drawLine(260, 520, 260, 530);
  }
  public int harrowdimensions(int intz, int intlocation, int intarrow, int intHeight){//Height
    //intarrow is part of arrow you want dimensions for (0 is point, 5 is two sides)
    intz = intlocation - (intHeight*7) + intarrow;
    if(intz == intlocation + 5){
      intz = intlocation;
    }
    return intz;
  }
  public int darrowdimensions(int intz, int intarrow, int intDistance){//Distance
    intz = intDistance*10 + intarrow + 75;
    return intz;
  }
  public void drawArrow(int int1x1, int int1x2, int int2y, int int3x, int int3y, int intlocation, Graphics2D g2d){
    g2d.drawLine(int1x1, int2y, int3x, int3y);
    g2d.drawLine(int1x2, int2y, int3x, int3y);
    g2d.drawLine(int3x, intlocation, int3x, int3y);
  }
  public Line2D.Double cray1(int intHeight, int intDistance, int int3x, int int3y, Graphics2D g2d){//Converging ray 1
    g2d.setColor(Color.RED);
    double dblslope;
    line1 = new Line2D.Double(int3x, int3y, 210, int3y);
    line2 = new Line2D.Double(210, int3y, 165, 125);
    Line2D.Double extendline = new Line2D.Double(210, int3y, 165, 125);
    if(intHeight == 1){
      line1.setLine(int3x, int3y, 214, int3y);
      g2d.draw(line1);
      line2.setLine(214, int3y, 165, 125);
      dblslope = slope(line2);
      line2.setLine(214, int3y, 165 - 100, 125 - (dblslope * 100));
      if(intDistance>=7){
        line2.setLine(214, int3y, 165 - 150, 125 - (dblslope * 150));
        if(intDistance == 10){
          g2d.setColor(Color.PINK);
          extendline.setLine(214, int3y, 165 + 280, 125 + (dblslope * 280));
          g2d.draw(extendline);
          g2d.setColor(Color.RED);
        }
      }
      g2d.draw(line2);
    }else if(intHeight == 2){
      line1.setLine(int3x, int3y, 214, int3y);
      g2d.draw(line1);
      line2.setLine(214, int3y, 165, 125);
      dblslope = slope(line2);
      line2.setLine(214, int3y, 165 - 100, 125 - (dblslope * 100));
      if(intDistance>=7){
        line2.setLine(214, int3y, 165 - 150, 125 - (dblslope * 150));
        if(intDistance == 10){
          g2d.setColor(Color.PINK);
          extendline.setLine(214, int3y, 165 + 280, 125 + (dblslope * 280));
          g2d.draw(extendline);
          g2d.setColor(Color.RED);
        }
      }
      g2d.draw(line2);
    }else if(intHeight == 3){
      line1.setLine(int3x, int3y, 213, int3y);
      g2d.draw(line1);
      line2.setLine(213, int3y, 165, 125);
      dblslope = slope(line2);
      line2.setLine(213, int3y, 165 - 100, 125 - (dblslope * 100));
      if(intDistance>=7){
        line2.setLine(213, int3y, 165 - 150, 125 - (dblslope * 150));
      }
      g2d.draw(line2);
    }else if(intHeight == 4){
      line1.setLine(int3x, int3y, 212, int3y);
      g2d.draw(line1);
      line2.setLine(212, int3y, 165, 125);
      dblslope = slope(line2);
      line2.setLine(212, int3y, 165 - 100, 125 - (dblslope * 100));
      if(intDistance>=7){
        line2.setLine(212, int3y, 165 - 150, 125 - (dblslope * 150));
      }
      g2d.draw(line2);
    }else if(intHeight == 5){
      line1.setLine(int3x, int3y, 210, int3y);
      g2d.draw(line1);
      line2.setLine(210, int3y, 165, 125);
      dblslope = slope(line2);
      line2.setLine(210, int3y, 165 - 100, 125 - (dblslope * 100));
      if(intDistance>=7){
        line2.setLine(210, int3y, 165 - 150, 125 - (dblslope * 150));
      }
      g2d.draw(line2);
    }else if(intHeight == 6){
      line1.setLine(int3x, int3y, 205, int3y);
      g2d.draw(line1);
      line2.setLine(205, int3y, 165, 125);
      dblslope = slope(line2);
      line2.setLine(205, int3y, 165 - 75, 125 - (dblslope * 75));
      if(intDistance>=7){
        line2.setLine(205, int3y, 165 - 125, 125 - (dblslope * 125));
      }
      g2d.draw(line2);
    }else if(intHeight == 7){
      line1.setLine(int3x, int3y, 201, int3y);
      g2d.draw(line1);
      line2.setLine(201, int3y, 165, 125);
      dblslope = slope(line2);
      line2.setLine(201, int3y, 165 - 60, 125 - (dblslope * 60));
      if(intDistance>=8){
        line2.setLine(201, int3y, 165 - 125, 125 - (dblslope * 125));
      }
      g2d.draw(line2);
    }else if(intHeight == 8){
      line1.setLine(int3x, int3y, 198, int3y);
      g2d.draw(line1);
      line2.setLine(198, int3y, 165, 125);
      dblslope = slope(line2);
      line2.setLine(198, int3y, 165 - 50, 125 - (dblslope * 50));
      if(intDistance>=8){
        line2.setLine(198, int3y, 165 - 100, 125 - (dblslope * 100));
      }
      g2d.draw(line2);
    }else if(intHeight == 9){
      line1.setLine(int3x, int3y, 192, int3y);
      g2d.draw(line1);
      line2.setLine(192, int3y, 165, 125);
      dblslope = slope(line2);
      line2.setLine(192, int3y, 165 - 40, 125 - (dblslope * 40));
      if(intDistance>=8){
        line2.setLine(192, int3y, 165 - 120, 125 - (dblslope * 120));
      }
      g2d.draw(line2);
    }else if(intHeight == 10){
      line1.setLine(int3x, int3y, 184, int3y);
      g2d.draw(line1);
      line2.setLine(184, int3y, 165, 125);
      dblslope = slope(line2);
      line2.setLine(184, int3y, 165 - 35, 125 - (dblslope * 35));
      if(intDistance>=8){
        line2.setLine(184, int3y, 165 - 50, 125 - (dblslope * 50));
      }
      g2d.draw(line2);
    }
    g2d.setColor(Color.BLACK);
    return line2;
  }
  public Line2D.Double cray2(int intHeight, int intDistance, int int3x, int int3y, Graphics2D g2d){ //Converging ray 2
    g2d.setColor(Color.GREEN);
    double dblslope;
    line1 = new Line2D.Double(int3x, int3y, 210, int3y);
    line2 = new Line2D.Double(210, int3y, 165, 125);
    Line2D.Double extendline = new Line2D.Double(210, int3y, 165, 125);

    if(intHeight == 1){
      line1.setLine(int3x, int3y, 215, 125);
      g2d.draw(line1);
      dblslope = slope(line1);
      line2.setLine(215, 125, int3x - 50, 125 + (125 - int3y) - (-dblslope*50));
      if(intDistance>=7){
        line2.setLine(215, 125, int3x - 100, 125 + (125 - int3y) - (-dblslope*100));
        if(intDistance == 10){
          g2d.setColor(Color.PINK);
          extendline.setLine(215, 125, int3x + 250, 125 + (125 - int3y) + (-dblslope*250));
          g2d.draw(extendline);
          g2d.setColor(Color.GREEN);
        }
      }
      g2d.draw(line2);
    }else if(intHeight == 2){
      line1.setLine(int3x, int3y, 215, 125);
      g2d.draw(line1);
      dblslope = slope(line1);
      line2.setLine(215, 125, int3x - 50, 125 + (125 - int3y) - (-dblslope*50));
      if(intDistance>=7){
        line2.setLine(215, 125, int3x - 100, 125 + (125 - int3y) - (-dblslope*100));
        if(intDistance == 10){
          g2d.setColor(Color.PINK);
          extendline.setLine(215, 125, int3x + 250, 125 + (125 - int3y) + (-dblslope*250));
          g2d.draw(extendline);
          g2d.setColor(Color.GREEN);
        }
      }
      g2d.draw(line2);
    }else if(intHeight == 3){
      line1.setLine(int3x, int3y, 215, 125);
      g2d.draw(line1);
      dblslope = slope(line1);
      line2.setLine(215, 125, int3x - 50, 125 + (125 - int3y) - (-dblslope*50));
      if(intDistance>=7){
        line2.setLine(215, 125, int3x - 100, 125 + (125 - int3y) - (-dblslope*100));
      }
      g2d.draw(line2);
    }else if(intHeight == 4){
      line1.setLine(int3x, int3y, 215, 125);
      g2d.draw(line1);
      dblslope = slope(line1);
      line2.setLine(215, 125, int3x - 50, 125 + (125 - int3y) - (-dblslope*50));
      if(intDistance>=7){
        line2.setLine(215, 125, int3x - 100, 125 + (125 - int3y) - (-dblslope*100));
      }
      g2d.draw(line2);
    }else if(intHeight == 5){
      line1.setLine(int3x, int3y, 215, 125);
      g2d.draw(line1);
      dblslope = slope(line1);
      line2.setLine(215, 125, int3x - 45, 125 + (125 - int3y) - (-dblslope*45));
      if(intDistance>=7){
        line2.setLine(215, 125, int3x - 150, 125 + (125 - int3y) - (-dblslope*150));
      }
      g2d.draw(line2);
    }else if(intHeight == 6){
      line1.setLine(int3x, int3y, 215, 125);
      g2d.draw(line1);
      dblslope = slope(line1);
      line2.setLine(215, 125, int3x - 45, 125 + (125 - int3y) - (-dblslope*45));
      if(intDistance>=7){
        line2.setLine(215, 125, int3x - 150, 125 + (125 - int3y) - (-dblslope*150));
      }
      g2d.draw(line2);
    }else if(intHeight == 7){
      line1.setLine(int3x, int3y, 215, 125);
      g2d.draw(line1);
      dblslope = slope(line1);
      line2.setLine(215, 125, int3x - 45, 125 + (125 - int3y) - (-dblslope*45));
      if(intDistance>=8){
        line2.setLine(215, 125, int3x - 125, 125 + (125 - int3y) - (-dblslope*125));
      }
      g2d.draw(line2);
    }else if(intHeight == 8){
      line1.setLine(int3x, int3y, 215, 125);
      g2d.draw(line1);
      dblslope = slope(line1);
      line2.setLine(215, 125, int3x - 45, 125 + (125 - int3y) - (-dblslope*45));
      if(intDistance>=8){
        line2.setLine(215, 125, int3x - 100, 125 + (125 - int3y) - (-dblslope*100));
      }
      g2d.draw(line2);
    }else if(intHeight == 9){
      line1.setLine(int3x, int3y, 215, 125);
      g2d.draw(line1);
      dblslope = slope(line1);
      line2.setLine(215, 125, int3x - 45, 125 + (125 - int3y) - (-dblslope*45));
      if(intDistance>=8){
        line2.setLine(215, 125, int3x - 120, 125 + (125 - int3y) - (-dblslope*120));
      }
      g2d.draw(line2);
    }else if(intHeight == 10){
      line1.setLine(int3x, int3y, 215, 125);
      g2d.draw(line1);
      dblslope = slope(line1);
      line2.setLine(215, 125, int3x - 35, 125 + (125 - int3y) - (-dblslope*35));
      if(intDistance>=8){
        line2.setLine(215, 125, int3x - 60, 125 + (125 - int3y) - (-dblslope*60));
      }
      g2d.draw(line2);
    }
    g2d.setColor(Color.BLACK);
    return line2;
  }
  public double slope(Line2D line){
    double slope;
    slope = (line.getY2() - line.getY1())/(line.getX2() - line.getX1());
    return slope;
  }
  public Point POI(double dblslope1, double x1, double y1, double dblslope2, double x2, double y2){
    double b1;
    double b2;
    double x3;
    double y3;
    int intx3;
    int inty3;
    b1 = y1 - (dblslope1*x1);
    b2 = y2 - (dblslope2*x2);
    //inty1 = dblslope1*x1 + b1
    //inty2 = dblslope1*x2 + b2
    x3 = (b1 - b2)/(dblslope2-dblslope1);
    y3 = dblslope1*x3 + b1;
    intx3 = (int)x3;
    inty3 = (int)y3;
    return new Point(intx3, inty3);
  }
  public Line2D.Double lensray1(int intHeight, int intDistance, int int3x, int int3y, Graphics2D g2d){ //lens ray 1
    g2d.setColor(Color.RED);
    double dblslope;
    line3 = new Line2D.Double(int3x, int3y, 212, int3y);
    line4 = new Line2D.Double(212, int3y, 260, 525);
    Line2D.Double extendline = new Line2D.Double(212, int3y, 260, 525);

    if(intHeight>0){
      g2d.draw(line3);
      dblslope = slope(line4);
      line4.setLine(212, int3y, 260 + 100, 525 + (dblslope*100));
      if(intDistance>=7){
        line4.setLine(212, int3y, 260 + 150, 525 + (dblslope*150));
        if(intDistance == 10){
          g2d.setColor(Color.PINK);
          extendline.setLine(212, int3y, 260 - 220, 525 - (dblslope*220));
          g2d.draw(extendline);
          g2d.setColor(Color.GREEN);
        }
      }
      g2d.draw(line4);
    }
    g2d.setColor(Color.BLACK);
    return line4;
  }
  public Line2D.Double lensray2(int intHeight, int intDistance, int int3x, int int3y, Graphics2D g2d){//lens ray 2
    g2d.setColor(Color.GREEN);
    double dblslope;
    line5 = new Line2D.Double(int3x, int3y, 212, 525);
    Line2D.Double extendline = new Line2D.Double(int3x, int3y, 212, 525);

    if(intHeight>0){
      dblslope = slope(line5);
      line5.setLine(int3x, int3y, 212 + 150, 525 + (dblslope*150));
      if(intDistance>=7){
        line5.setLine(int3x, int3y, 212 + 200, 525 + (dblslope*200));
        if(intDistance == 10){
          g2d.setColor(Color.PINK);
          extendline.setLine(int3x, int3y, 212 - 200, 525 - (dblslope*200));
          g2d.draw(extendline);
          g2d.setColor(Color.GREEN);
        }
      }
      g2d.draw(line5);
    }
    g2d.setColor(Color.BLACK);
    return line5;
  }
  //Constructors
  public Opticsanimation(){
    super();
  }
}
