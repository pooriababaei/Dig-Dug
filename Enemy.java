/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JApplet;

/**
 *
 * @author ui
 */
public class Enemy extends ScreenObject implements Runnable {

    public enum direction {

        up, right, down, left
    };
    Object ld;

    int dx = 20;
    int health = 3;
    JApplet parent;
    ScreenManager sm;
    Color cl;
    int currentside = 2;
    Player player;
    Image[] m = new Image[4];
    BackGround bg;
     Boolean exist;

    int[] wayScore = new int[4];
    int maxWayScores = -1;
    int way = -1;

    public Enemy(int x, int y, int w, int h, ScreenManager sm) {
        super(x, y, w, h);
        this.sm = sm;
        this.parent = sm.parent;
        player = sm.parent.player;
        bg = sm.parent.background;
        ld = direction.right;
        Boolean exist =true;

        m[0] = parent.getImage(parent.getCodeBase(), "up.gif");
        m[1] = parent.getImage(parent.getCodeBase(), "right.gif");
        m[2] = parent.getImage(parent.getCodeBase(), "down.gif");
        m[3] = parent.getImage(parent.getCodeBase(), "left.gif");
    }

    @Override
    public void draw(Graphics g) {

        g.drawImage(m[currentside - 1], x, y, w, h, parent);

    }

    @Override
    public void run() {
        while (true) {
            I();

            try {
                Thread.currentThread().sleep(200);
            } catch (InterruptedException ex) {
                Logger.getLogger(Enemy.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public void up() {
        if (y - dx >= 0) {
            y -= dx;
        }
    }

    public void down() {
        if (y + dx <= parent.getHeight()) {
            y += dx;
        }
    }

    public void right() {
        if (x + dx <= parent.getWidth()) {
            x += dx;
        }
    }

    public void left() {
        if (x - dx >= 0) {
            x -= dx;
        }
    }

    public void ghost() {
        if (player.x - x > 0) {
            x += dx;
        }
        if (x - player.x > 0) {
            x -= dx;
        }

        if (y - player.y > 0) {
            y -= dx;
        }
        if (player.y - y > 0) {
            y += dx;
        }

    }

    public void wWay() {
        if (x < parent.getWidth() - dx) {
            if (bg.bg[(x / dx) + 1][y / dx] == false) { //right
                wayScore[1] += 3;
                if (player.x - x >= 0) {
                    wayScore[1] += 2;
                }
                if (ld == direction.right) {
                    wayScore[1] += 2;
                }
            }
        }
        if (y - dx > 0) {
            if (bg.bg[x / dx][(y / dx) - 1] == false) { //up
                wayScore[0] += 3;
                if (y - player.y >= 0) {
                    wayScore[0] += 2;
                }
                if (ld == direction.up) {
                    wayScore[0] += 2;
                }

            }
        }
        if (x - dx > 0) {
            if (bg.bg[(x / dx) - 1][y / dx] == false) { //left
                wayScore[3] += 3;
                if (x - player.x >= 0) {
                    wayScore[3] += 2;
                }
                if (ld == direction.left) {
                    wayScore[3] += 2;
                }

            }
        }
        if (y + dx < parent.getHeight() - 100) {
            if (bg.bg[x / dx][(y / dx) + 1] == false) { //down
                wayScore[2] += 3;
                if (player.y - y >= 0) {
                    wayScore[2] += 2;
                }
                if (ld == direction.down) {
                    wayScore[2] += 2;
                }

            }
        }
        for (int i = 0; i < 4; i++) {
            if (wayScore[i] > maxWayScores) {
                maxWayScores=wayScore[i];
                way = i;
            }
        }
        switch (way) {
            case 0:
                up();
                ld = direction.up;
                currentside=1;
                break;
            case 1:
                right();
                ld = direction.right;
                currentside=2;
                break;
            case 2:
                down();
                ld = direction.down;
                currentside=3;
                break;
            case 3:
                left();
                ld = direction.left;
                currentside=4;
                break;
        }
        for (int i = 0; i < 4; i++) {
            wayScore[i]=0;
        }
        maxWayScores=-1;
    }

    public void I() {
        if (bg.bg[x / dx][y / dx] == true) {
            ghost();
        } else {
            int x = (int) (Math.random() * 10);
            if (x == 5) {
                ghost();
            } else {
                wWay();
            }

        }
    }

}
