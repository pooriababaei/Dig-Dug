
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JApplet;

public class FRocks extends ScreenObject implements Runnable {

    Image m;
    ScreenManager sm;

    public FRocks(int x, int y, int w, int h, ScreenManager sm) {
        super(x, y, w, h);
        this.sm = sm;

        m = sm.parent.getImage(sm.parent.getCodeBase(), "FRock.png");
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(m, x, y, w, h, sm.parent);
    }

    @Override
    public void run() {
        while (true) {
            Boolean isRunning = false;
            try {
            if (sm.parent.background.bg[x / 20][(y / 20) + 1] == false) {
                y += 20;
                isRunning = true;
            }
            
            if (sm.parent.background.bg[x / 20][(y / 20) + 1] == true && isRunning) {
                sm.removeSO(this);
                break;
            }
            }
            catch(Exception e) {
                    sm.removeSO(this);
                    }
             try {
                Thread.currentThread().sleep(200);
            } catch (InterruptedException ex) {

            }
            }
           
        }
    public void fall () {
        while (y<sm.parent.getHeight()-70) {
            y+=20;
        }
    } 
    }


