/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JApplet;

/**
 *
 * @author ui
 */
public class Pump extends ScreenObject implements Runnable{
 ScreenManager sm;
 JApplet parent;
 Image m;
// Image m[]=new Image[4];
    public Pump(int x, int y, int w, int h, ScreenManager sm){
     super(x,y,w,h);
     this.sm =sm;
     this.parent=sm.parent;
//       m[0] = parent.getImage(parent.getCodeBase(), "pUp.gif"); //up
//       m[1] = parent.getImage(parent.getCodeBase(), "pRight.gif");//right
//       m[2] = parent.getImage(parent.getCodeBase(), "pDown.gif");//down
//       m[3] = parent.getImage(parent.getCodeBase(), "pLeft.gif");//left
 }
 
 
    @Override
    public void draw(Graphics g) {
      //  g.drawImage(m[sm.parent.player.currentside-1], x, y, w, h, parent);
        g.setColor(Color.red);
        g.fillRect(x, y,w ,h);
      
     }

    @Override
    public void run() {
        try {
            Thread.currentThread().sleep(200);
        }
        catch(InterruptedException ex){
        }
        sm.removeSO(this);
        }

    
}
