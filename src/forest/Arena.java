/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package forest;

import java.awt.*;
import javax.swing.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.*;

/**
 *
 * @author abc
 */
public class Arena extends javax.swing.JPanel {

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        super.paintComponent(g2);

        this.setBackground(new Color(153, 217, 234));
        g2.setColor(Color.ORANGE);
        g2.fillOval(50, 50, 50, 50);
        //System.out.println(Forest.selected!=null);
        /*
         * if(Forest.selected!=null){ for(Tree t: Forest.arrayOfAliveTrees){
         * if(t!=null){ Forest.selectedtree=t; t.color=Constants.selectedcolor;
         * Forest.selected=null; } }
         }
         */

        g2.setStroke(new BasicStroke(1.2f));
        
        for (Tree t : Forest.arrayOfAliveTreesCopy) {
            if (t != null && t.alive) {
                g2.setColor(Color.black);
                g2.draw(new Line2D.Double(t.x, 550, t.x, t.y));

                g2.setColor(t.color);
                if (t.age == 1) {
                    g2.setColor(Constants.TreeBornColor);
                }
                if (t.age >= t.dna.maxage - 1) {
                    g2.setColor(Constants.TreeDeadColor);
                }
                if (t.isSelected == true) {
                    g2.setColor(Constants.selectedcolor);
                }


                g2.fill(new Ellipse2D.Double((t.x - t.leafarea), (t.y - 2 * t.leafarea), (2 * t.leafarea), (2 * t.leafarea)));
            }
        }
        for (LightBeam l : Forest.arrayOfLightBeams) {
            if (l != null && l.alive && Constants.ArenaShowLight) {
                g2.setColor(Color.yellow);
                g2.fillOval((int) l.x, (int) l.y, 2, 2);
            }
        }

        g2.setStroke(new BasicStroke(2.0f));
        for (Giraffe f : Forest.arrayOfAliveGiraffesCopy) {

            if (f != null && f.alive) {
                if (f.gender == true) {
                    g2.setColor(Constants.malegiraffe);
                }
                if (f.gender == false) {
                    g2.setColor(Constants.femalegiraffe);
                }
                if (f.isSelected == true) {
                    g2.setColor(Constants.selectedgiraffe);
                }

                
                //front legs
                g2.draw(new Line2D.Double(f.x, Constants.ArenaMaxY, f.x, Constants.ArenaMaxY - f.bodylength));
                g2.draw(new Line2D.Double(f.x - f.bodylength / 6, Constants.ArenaMaxY, f.x, Constants.ArenaMaxY - f.bodylength));

                //back legs
                g2.draw(new Line2D.Double(f.x - f.currentheight / 3, Constants.ArenaMaxY, f.x - f.currentheight / 3, Constants.ArenaMaxY - f.bodylength));
                g2.draw(new Line2D.Double(f.x - f.currentheight / 3 - f.bodylength / 6, Constants.ArenaMaxY, f.x - f.currentheight / 3, Constants.ArenaMaxY - f.bodylength));



                //neck
                g2.draw(new Line2D.Double(f.x, Constants.ArenaMaxY - f.bodylength, f.x + f.necklength * Math.cos(f.angle * 3.14159 / 180), (Constants.ArenaMaxY - f.bodylength) - f.necklength * Math.sin(f.angle * 3.14159 / 180)));
                g2.draw(new Line2D.Double(f.x - f.currentheight / 6 + f.currentheight * Math.cos(45 * 3.14159 / 180) / 6, Constants.ArenaMaxY - f.bodylength - f.bodylength * Math.sin(45 * 3.14159 / 180) / 6, f.x + f.necklength * Math.cos(f.angle * 3.14159 / 180), Constants.ArenaMaxY - f.bodylength - f.necklength * Math.sin(f.angle * 3.14159 / 180)));

                //face
                g2.draw(new Ellipse2D.Double(f.x + f.necklength * Math.cos(f.angle * 3.14159 / 180) - f.necklength / 100, (Constants.ArenaMaxY - f.bodylength) - f.necklength * Math.sin(f.angle * 3.14159 / 180) - f.necklength / 10, f.necklength / 4, f.necklength / 6));
                //eyes
                g2.fill(new Ellipse2D.Double(f.x + f.necklength * Math.cos(f.angle * 3.14159 / 180) + f.necklength / 18, (Constants.ArenaMaxY - f.bodylength) - f.necklength * Math.sin(f.angle * 3.14159 / 180) - f.necklength / 18, f.necklength / 18, f.necklength / 24));
                //tail

                //body
                if (f.dna.camouflage.fromFemaleParent && f.dna.camouflage.fromMaleParent) {

                    g2.fill(new Ellipse2D.Double(f.x - f.currentheight / 3, Constants.ArenaMaxY - (f.bodylength + f.bodylength / 6), f.currentheight / 3, f.bodylength / 3));
                    g2.setColor(Constants.camouflage1);
                    g2.fill(new Ellipse2D.Double(f.x - 5 * f.currentheight / 18, Constants.ArenaMaxY - (10 * f.bodylength / 9), 2 * f.currentheight / 9, 2 * f.bodylength / 9));
                    g2.setColor(Constants.camouflage2);
                    if (f.isSelected) {
                        g2.setColor(Constants.selectedgiraffe);
                    }
                    g2.fill(new Ellipse2D.Double(f.x - 2 * f.currentheight / 9, Constants.ArenaMaxY - (19 * f.bodylength / 18), f.currentheight / 9, f.bodylength / 9));

                } else {
                    g2.fill(new Ellipse2D.Double(f.x - f.currentheight / 3, Constants.ArenaMaxY - (f.bodylength + f.bodylength / 6), f.currentheight / 3, f.bodylength / 3));
                }
            }
        }

        g2.setStroke(new BasicStroke(5.0f,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
        for (Lion l : Forest.arrayOfAliveLionsCopy) {
            if (l != null && l.alive) {
                
                if (l.gender == true) {
                    g2.setColor(Constants.malelion);
                }
                if (l.gender == false) {
                    g2.setColor(Constants.femalelion);
                }
                if(l.isSelected)
                    g2.setColor(Constants.LionSelectedColor);
                QuadCurve2D c=new QuadCurve2D.Double();
		
                if(l.flyingstate==true){
                /*
                 * Flying State 
                 */
		 
                //front leg
		g2.draw(new Line2D.Double(l.x,Constants.ArenaMaxY-l.y,l.x,Constants.ArenaMaxY-l.y-3*l.currentsize/10));
		g2.draw(new Line2D.Double(l.x+l.currentsize/20,Constants.ArenaMaxY-l.y,l.x+l.currentsize/10,Constants.ArenaMaxY-l.y-l.currentsize/4));
		g2.draw(new Line2D.Double(l.x+l.currentsize/20,Constants.ArenaMaxY-l.y,l.x,Constants.ArenaMaxY-l.y));
		c.setCurve(l.x+l.currentsize/10,Constants.ArenaMaxY-l.y-l.currentsize/4, l.x+3*l.currentsize/20, Constants.ArenaMaxY-l.y-3*l.currentsize/10, l.x+l.currentsize/8, Constants.ArenaMaxY-l.y-2*l.currentsize/5);
		g2.draw(c);
		//back leg
		g2.draw(new Line2D.Double(l.x+l.currentsize,Constants.ArenaMaxY-l.y,l.x+l.currentsize,Constants.ArenaMaxY-l.y-2*l.currentsize/5));
		g2.draw(new Line2D.Double(l.x+19*l.currentsize/20,Constants.ArenaMaxY-l.y,l.x+9*l.currentsize/10, Constants.ArenaMaxY-l.y-13*l.currentsize/40));
		g2.draw(new Line2D.Double(l.x+l.currentsize,Constants.ArenaMaxY-l.y,l.x+19*l.currentsize/20,Constants.ArenaMaxY-l.y));
		//wing line
		g2.draw(new Line2D.Double(l.x+17*l.currentsize/20, Constants.ArenaMaxY-l.y-11*l.currentsize/10,l.x+27*l.currentsize/20,Constants.ArenaMaxY-l.y-26*l.currentsize/20));
		
		//stomach
		c.setCurve(l.x-l.currentsize/8, Constants.ArenaMaxY-l.y-9*l.currentsize/20, l.x, Constants.ArenaMaxY-l.y-l.currentsize/20, l.x+9*l.currentsize/10, Constants.ArenaMaxY-l.y-13*l.currentsize/40);
		g2.draw(c);
		//hip
		c.setCurve(l.x+l.currentsize,Constants.ArenaMaxY-l.y-2*l.currentsize/5, l.x+19*l.currentsize/20, Constants.ArenaMaxY-l.y-23*l.currentsize/40, l.x+7*l.currentsize/10, Constants.ArenaMaxY-l.y-3*l.currentsize/5);
		g2.draw(c);
		//back
		c.setCurve(l.x+7*l.currentsize/10, Constants.ArenaMaxY-l.y-3*l.currentsize/5, l.x+9*l.currentsize/20, Constants.ArenaMaxY-l.y-23*l.currentsize/40, l.x+7*l.currentsize/20, Constants.ArenaMaxY-l.y-27*l.currentsize/40);
		g2.draw(c);
		//wing down
		c.setCurve(l.x+7*l.currentsize/20, Constants.ArenaMaxY-l.y-27*l.currentsize/40, l.x+3*l.currentsize/5, Constants.ArenaMaxY-l.y-13*l.currentsize/20, l.x+17*l.currentsize/20, Constants.ArenaMaxY-l.y-11*l.currentsize/10);
		g2.draw(c);
		//wing front
		c.setCurve(l.x+3*l.currentsize/20, Constants.ArenaMaxY-l.y-5*l.currentsize/8, l.x+7*l.currentsize/20, Constants.ArenaMaxY-l.y-7*l.currentsize/8, l.x, Constants.ArenaMaxY-l.y-39*l.currentsize/40);
		g2.draw(c);
		//wing top
		c.setCurve( l.x, Constants.ArenaMaxY-l.y-39*l.currentsize/40, l.x+3*l.currentsize/10, Constants.ArenaMaxY-l.y-5*l.currentsize/4, l.x+27*l.currentsize/20, Constants.ArenaMaxY-l.y-26*l.currentsize/20);
		g2.draw(c);
		//head
		c.setCurve( l.x+l.currentsize/5, Constants.ArenaMaxY-l.y-11*l.currentsize/20, l.x-l.currentsize/10, Constants.ArenaMaxY-l.y-39*l.currentsize/40, l.x-l.currentsize/4, Constants.ArenaMaxY-l.y-11*l.currentsize/20);
		g2.draw(c);
		//mouth
		c.setCurve( l.x-l.currentsize/4, Constants.ArenaMaxY-l.y-11*l.currentsize/20, l.x-3*l.currentsize/10, Constants.ArenaMaxY-l.y-l.currentsize/2, l.x-7*l.currentsize/40, Constants.ArenaMaxY-l.y-17*l.currentsize/40);
		g2.draw(c);
		c.setCurve( l.x-7*l.currentsize/40, Constants.ArenaMaxY-l.y-17*l.currentsize/40, l.x-l.currentsize/10, Constants.ArenaMaxY-l.y-9*l.currentsize/20,l.x-3*l.currentsize/40,Constants.ArenaMaxY-l.y-l.currentsize/2);
		g2.draw(c);
		//tail
		c.setCurve(l.x+37*l.currentsize/40,Constants.ArenaMaxY-l.y-l.currentsize/2, l.x+23*l.currentsize/20, Constants.ArenaMaxY-l.y-5*l.currentsize/40,l.x+5*l.currentsize/4,Constants.ArenaMaxY-l.y-3*l.currentsize/10);
		g2.draw(c);
		//eye
		g2.fill(new Ellipse2D.Double(l.x-l.currentsize/8,Constants.ArenaMaxY-l.y-7*l.currentsize/10,3*l.currentsize/40,l.currentsize/20));
                
                }
                if(l.flyingstate==false){
                /*
                 * Flying State False
                 */
		 
                //front leg
		g2.draw(new Line2D.Double(l.x,Constants.ArenaMaxY-l.y,l.x,Constants.ArenaMaxY-l.y-3*l.currentsize/10));
		g2.draw(new Line2D.Double(l.x+l.currentsize/20,Constants.ArenaMaxY-l.y,l.x+l.currentsize/10,Constants.ArenaMaxY-l.y-l.currentsize/4));
		g2.draw(new Line2D.Double(l.x+l.currentsize/20,Constants.ArenaMaxY-l.y,l.x,Constants.ArenaMaxY-l.y));
		c.setCurve(l.x+l.currentsize/10,Constants.ArenaMaxY-l.y-l.currentsize/4, l.x+3*l.currentsize/20, Constants.ArenaMaxY-l.y-3*l.currentsize/10, l.x+l.currentsize/8, Constants.ArenaMaxY-l.y-2*l.currentsize/5);
		g2.draw(c);
		//back leg
		g2.draw(new Line2D.Double(l.x+l.currentsize,Constants.ArenaMaxY-l.y,l.x+l.currentsize,Constants.ArenaMaxY-l.y-2*l.currentsize/5));
		g2.draw(new Line2D.Double(l.x+19*l.currentsize/20,Constants.ArenaMaxY-l.y,l.x+9*l.currentsize/10, Constants.ArenaMaxY-l.y-13*l.currentsize/40));
		g2.draw(new Line2D.Double(l.x+l.currentsize,Constants.ArenaMaxY-l.y,l.x+19*l.currentsize/20,Constants.ArenaMaxY-l.y));
		
                //stomach
		c.setCurve(l.x-l.currentsize/8, Constants.ArenaMaxY-l.y-9*l.currentsize/20, l.x, Constants.ArenaMaxY-l.y-l.currentsize/20, l.x+9*l.currentsize/10, Constants.ArenaMaxY-l.y-13*l.currentsize/40);
		g2.draw(c);
		//hip
		c.setCurve(l.x+l.currentsize,Constants.ArenaMaxY-l.y-2*l.currentsize/5, l.x+19*l.currentsize/20, Constants.ArenaMaxY-l.y-23*l.currentsize/40, l.x+7*l.currentsize/10, Constants.ArenaMaxY-l.y-3*l.currentsize/5);
		g2.draw(c);
		//back
		c.setCurve(l.x+7*l.currentsize/10, Constants.ArenaMaxY-l.y-3*l.currentsize/5, l.x+9*l.currentsize/20, Constants.ArenaMaxY-l.y-23*l.currentsize/40, l.x+7*l.currentsize/20, Constants.ArenaMaxY-l.y-27*l.currentsize/40);
		g2.draw(c);
                
		//wing down
		c.setCurve(l.x+7*l.currentsize/20, Constants.ArenaMaxY-l.y-27*l.currentsize/40, l.x+3*l.currentsize/5, Constants.ArenaMaxY-l.y-17*l.currentsize/20, l.x+3*l.currentsize/4, Constants.ArenaMaxY-l.y-3*l.currentsize/4);
		g2.draw(c);
		//wing front
		c.setCurve(l.x+3*l.currentsize/20, Constants.ArenaMaxY-l.y-5*l.currentsize/8, l.x+3*l.currentsize/10, Constants.ArenaMaxY-l.y-29*l.currentsize/40, l.x+7*l.currentsize/40, Constants.ArenaMaxY-l.y-9*l.currentsize/10);
		g2.draw(c);
		//wing top1
		c.setCurve( l.x+7*l.currentsize/40, Constants.ArenaMaxY-l.y-9*l.currentsize/10, l.x+3*l.currentsize/5, Constants.ArenaMaxY-l.y-9*l.currentsize/8,l.x+7*l.currentsize/5,Constants.ArenaMaxY-l.y-17*l.currentsize/20);
		g2.draw(c);
		//wing top4
		c.setCurve( l.x+7*l.currentsize/40, Constants.ArenaMaxY-l.y-9*l.currentsize/10, l.x+3*l.currentsize/5, Constants.ArenaMaxY-l.y-19*l.currentsize/20,l.x+6*l.currentsize/5,Constants.ArenaMaxY-l.y-9*l.currentsize/20);
		g2.draw(c);
		//wing top2
		c.setCurve( l.x+7*l.currentsize/40, Constants.ArenaMaxY-l.y-9*l.currentsize/10, l.x+13*l.currentsize/20, Constants.ArenaMaxY-l.y-21*l.currentsize/20,l.x+27*l.currentsize/20,Constants.ArenaMaxY-l.y-7*l.currentsize/10);
		g2.draw(c);
		//wing top3
		c.setCurve( l.x+7*l.currentsize/40, Constants.ArenaMaxY-l.y-9*l.currentsize/10, l.x+7*l.currentsize/10, Constants.ArenaMaxY-l.y-l.currentsize,l.x+5*l.currentsize/4,Constants.ArenaMaxY-l.y-23*l.currentsize/40);
		g2.draw(c);
		//wing nails
		c.setCurve(l.x+7*l.currentsize/5,Constants.ArenaMaxY-l.y-17*l.currentsize/20, l.x+5*l.currentsize/4, Constants.ArenaMaxY-l.y-31*l.currentsize/40,l.x+27*l.currentsize/20,Constants.ArenaMaxY-l.y-7*l.currentsize/10);
		g2.draw(c);
		c.setCurve(l.x+27*l.currentsize/20,Constants.ArenaMaxY-l.y-7*l.currentsize/10, l.x+23*l.currentsize/20, Constants.ArenaMaxY-l.y-7*l.currentsize/10,l.x+5*l.currentsize/4,Constants.ArenaMaxY-l.y-23*l.currentsize/40);
		g2.draw(c);
		c.setCurve(l.x+5*l.currentsize/4,Constants.ArenaMaxY-l.y-23*l.currentsize/40, l.x+11*l.currentsize/10, Constants.ArenaMaxY-l.y-11*l.currentsize/20,l.x+6*l.currentsize/5,Constants.ArenaMaxY-l.y-9*l.currentsize/20);
		g2.draw(c);
		
		//head
		c.setCurve( l.x+l.currentsize/5, Constants.ArenaMaxY-l.y-11*l.currentsize/20, l.x-l.currentsize/10, Constants.ArenaMaxY-l.y-39*l.currentsize/40, l.x-l.currentsize/4, Constants.ArenaMaxY-l.y-11*l.currentsize/20);
		g2.draw(c);
		//mouth
		c.setCurve( l.x-l.currentsize/4, Constants.ArenaMaxY-l.y-11*l.currentsize/20, l.x-3*l.currentsize/10, Constants.ArenaMaxY-l.y-l.currentsize/2, l.x-7*l.currentsize/40, Constants.ArenaMaxY-l.y-17*l.currentsize/40);
		g2.draw(c);
		c.setCurve( l.x-7*l.currentsize/40, Constants.ArenaMaxY-l.y-17*l.currentsize/40, l.x-l.currentsize/10, Constants.ArenaMaxY-l.y-9*l.currentsize/20,l.x-3*l.currentsize/40,Constants.ArenaMaxY-l.y-l.currentsize/2);
		g2.draw(c);
		//tail
		c.setCurve(l.x+37*l.currentsize/40,Constants.ArenaMaxY-l.y-l.currentsize/2, l.x+23*l.currentsize/20, Constants.ArenaMaxY-l.y-5*l.currentsize/40,l.x+5*l.currentsize/4,Constants.ArenaMaxY-l.y-3*l.currentsize/10);
		g2.draw(c);
		//eye
		g2.fill(new Ellipse2D.Double(l.x-l.currentsize/8,Constants.ArenaMaxY-l.y-7*l.currentsize/10,3*l.currentsize/40,l.currentsize/20));
                }
            }
        }
    }
}
