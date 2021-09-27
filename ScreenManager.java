/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Color;
import static java.awt.Color.blue;
import static java.awt.Color.red;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ui
 */
public class ScreenManager extends Thread {

    int level = 1;
    ArrayList<ScreenObject> ol;
    DigDug parent;
    boolean isRunning = true;

    public ScreenManager(DigDug parent) {
        this.parent = parent;
        ol = new ArrayList<>();

    }

    public void addSO(ScreenObject so) {
        ol.add(so);
    }

    public void removeSO(ScreenObject so) {
        ol.remove(so);
    }

    private void checkConflict() {

        ArrayList<Enemy> El = new ArrayList<>();
        ArrayList<Dragon> Dl = new ArrayList<>();
        ArrayList<FRocks> Fl = new ArrayList<>();
        ArrayList<Fire> firel = new ArrayList<>();
        Score score = null;
        Player player = null;
        Pump pump = null;
        ScreenObject so = null;
        
        for (int i = 0; i < ol.size(); i++) {
            so = ol.get(i);
            if (so instanceof Enemy) {
                El.add((Enemy) so);
            }

            if (so instanceof Dragon) {
                Dl.add((Dragon) so);
            }
            if (so instanceof Fire)
                firel.add((Fire) so);

            if (so instanceof Score) {
                score = (Score) so;
            }
            if (so instanceof Pump) {
                pump = (Pump) so;
            }
            if (so instanceof Player) {
                player = (Player) so;
            }
            if (so instanceof FRocks) {
                Fl.add((FRocks) so);
            }

        }

        for (int i = 0; i < El.size(); i++) {
            Enemy e = El.get(i);
            if (e.x == player.x && e.y == player.y) {
                parent.player.health--;
                try {
                    Thread.currentThread().sleep(100);
                } catch (InterruptedException ex) {

                }
            }

        }
        for (int i = 0; i <firel.size(); i++) {
           Fire f=firel.get(i);
           int ex = player.x;
                int ey = player.y;

                if (f.currentside == 1 || f.currentside == 3) {
                    if (f.y <= ey && ey <= f.y + 60 && f.x == ex) {
                       removeSO(player);
                       
                    }
                } else if (f.currentside == 2 || f.currentside == 4) {
                    if (f.x <= ex && ex <= f.x + 60 && f.y == ey) {
                        removeSO(player);
                    }
           
            
                }
           
           
        }
        for (int i = 0; i < Fl.size(); i++) {
            for (int j = 0; j < El.size(); j++) {
                if (Fl.get(i).y == El.get(j).y && El.get(j).x == Fl.get(i).x && parent.background.bg[El.get(j).x / 20][El.get(j).y / 20] == false) {
                    removeSO(Fl.get(i));
                    removeSO(El.get(i));
                    El.get(i).exist=false;
                    try {
                        Thread.currentThread().sleep(400);
                    } catch (Exception e) {
                    }

                    score.incScore(200);

                }
            }
        }
        if (pump != null) {
            for (int i = 0; i < El.size(); i++) {
                int ex = El.get(i).x;
                int ey = El.get(i).y;

                if (player.currentside == 1 || player.currentside == 3) {
                    if (pump.y <= ey && ey <= pump.y + 60 && pump.x == ex) {
                        removeSO(El.get(i));
                        El.get(i).exist=false;
                        score.incScore(100);
                    }
                } else if (player.currentside == 2 || player.currentside == 4) {
                    if (pump.x <= ex && ex <= pump.x + 60 && pump.y == ey) {
                        removeSO(El.get(i));
                        El.get(i).exist=false;
                    }

                    score.incScore(100);

                }
                try {
                    Thread.currentThread().sleep(400);
                } catch (Exception e) {

                }
            }

        }
    }

    public void draw(Graphics g) {
        Image offImg;
        Graphics offG;
        offImg = parent.createImage(parent.getWidth(), parent.getHeight());
        offG = offImg.getGraphics();
        offG.setColor(Color.white);
        offG.fillRect(0, 0, parent.getWidth(), parent.getHeight());

        for (int i = 0; i < ol.size(); i++) {
            ol.get(i).draw(offG);
        }
        g.drawImage(offImg, 0, 0, parent);
    }

    public void run() {

        while (isRunning) {
            checkConflict();
            parent.repaint();
            try {
                sleep(20);
            } catch (InterruptedException ex) {
                Logger.getLogger(ScreenManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void stopManager() {
        isRunning = false;
    }
}
