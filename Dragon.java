
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JApplet;

public class Dragon extends Enemy {

    Fire f;
    int hrealth;
    Image[] m = new Image[4];
   

    public Dragon(int x, int y, int w, int h, ScreenManager sm) {
        super(x, y, w, h, sm);
        exist=true;
        m[0] = parent.getImage(parent.getCodeBase(), "dUp.gif");
        m[1] = parent.getImage(parent.getCodeBase(), "dRight.gif");
        m[2] = parent.getImage(parent.getCodeBase(), "dDown.gif");
        m[3] = parent.getImage(parent.getCodeBase(), "dLeft.gif");
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(m[currentside - 1], x, y, w, h, parent);
    }

    public void fire() {
     
        switch (currentside) {
            case 1:
                f = new Fire(x, y - 3 * h, w, 3 * h, sm,currentside);
                break;
            case 2:
                f = new Fire(x + w, y, 3 * w, h, sm,currentside);
                break;
            case 3:
                f = new Fire(x, y + h, w, 3 * h, sm,currentside);
                break;
            case 4:
                f = new Fire(x - 3 * w, y, 3 * w, h, sm,currentside);

        }

        sm.addSO(f);
        Thread t = new Thread(f);
        t.start();
        }

    

    @Override
    public void run() {

       while (exist) {
            I();
            try {
                Thread.currentThread().sleep(200);
            } catch (InterruptedException ex) {
                Logger.getLogger(Enemy.class.getName()).log(Level.SEVERE, null, ex);
            }
            int x = (int) (Math.random() * 20);
            if (x == 10) {
                fire();
            }
        }

    }

}
