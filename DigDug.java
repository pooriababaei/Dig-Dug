/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JApplet;

/**
 *
 * @author ui
 */
public class DigDug extends JApplet implements KeyListener {

    Score sc;
    ScreenManager sm;
    Player player;
    Level level;
    BackGround background;
    int currentlevel=1;
   

    public void init() {
        
        setSize(600, 650);
        sm = new ScreenManager(this);
        Score sc = new Score(getWidth() - 60, getHeight() - 30,60, 20);
        sm.addSO(sc);
        background = new BackGround(1,2,3,4,sm);
        sm.addSO(background);            
        player = new Player(15 *20, 10 * 20, 20,20, sm);
        sm.addSO(player);
        level = new Level(sm);
        level.levelProperty(currentlevel);
        this.addKeyListener(this);
        setFocusable(true);
        requestFocus();

    }

    public void start() {
        sm.start();
    }

    public void paint(Graphics g) {
        sm.draw(g);

    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                player.left();
                
                break;
            case KeyEvent.VK_RIGHT:
                player.right();
             
                break;
            case KeyEvent.VK_UP:
                player.up();
               
                break;
            case KeyEvent.VK_DOWN:
                player.down();
               
                break;
            case KeyEvent.VK_SPACE:
                player.fire();
                break;
            case KeyEvent.VK_F :
                
                
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //Thread t = new Thread(player);
        //if (e.getKeyCode() == KeyEvent.VK_SPACE) {
        //  t.start();
        //}

    }
}
