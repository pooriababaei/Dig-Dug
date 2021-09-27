/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Color;
import static java.awt.Color.blue;
import static java.awt.Color.red;
import java.awt.Graphics;
import java.awt.Image;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JApplet;

/**
 *
 * @author ui
 */
public class Player extends ScreenObject {
    int currentside=2;
    Pump p;
    int dx;
     int health=4;
    JApplet parent;
    ScreenManager sm;
    Image[] m = new Image[4];
     Image m1;

    public Player(int x, int y, int w, int h, ScreenManager sm) {
        super(x, y, w, h);
        dx = w;
        this.sm = sm;
        this.parent=sm.parent;
        //!!
       m[0] = parent.getImage(parent.getCodeBase(), "pUp.gif"); //up
       m[1] = parent.getImage(parent.getCodeBase(), "pRight.gif");//right
       m[2] = parent.getImage(parent.getCodeBase(), "pDown.gif");//down
       m[3] = parent.getImage(parent.getCodeBase(), "pLeft.gif");//left
    }

    public void fire() {
        switch (currentside) {
            case 1:
                p = new Pump(x, y - 3 * h, w, 3 * h, sm);
                break;
            case 2:
                p = new Pump(x + w, y, 3 * w, h, sm);
                break;
            case 3:
                p = new Pump(x, y + h, w, 3 * h, sm);
                break;
            case 4:
                p = new Pump(x - 3 * w, y, 3 * w, h, sm);

        }

        sm.addSO(p);
        Thread t =new Thread(p);
        t.start();

    }

    public void right() {
        if (x + dx < sm.parent.getWidth()) {
            sm.parent.background.bg[(x/20) +1][(y/20)]=false;
            x += dx;
            currentside=2;
            
        }
    }

    public void left() {
        if (x - dx >= 0) {
             sm.parent.background.bg[(x/20)-1][(y/20)]=false;
            x -= dx;
            currentside=4;
            
        }
    }

    public void up() {
        if (y - dx >= 0) {
            sm.parent.background.bg[(x/20)][(y/20)-1]=false;
            y -= dx;
            currentside=1;
             
        }
    }

    public void down() {
        if (y + dx <= sm.parent.getHeight() - 70) {
            sm.parent.background.bg[(x/20)][(y/20)+1]=false;
            y += dx;
            currentside=3;
             
        }
    }

    @Override
    public void draw(Graphics g) {
       g.drawImage(m[currentside -1], x, y, w, h, parent);
       g.setColor(red);
       g.drawString("health : ",20,630);
       g.drawString(health+"", 100, 630);
    }
    
     
//     public void run() {
//     try {
//     Thread.currentThread().sleep(1000);
//     } catch (InterruptedException ex) {
//     Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
//     }
//     }
     
}
