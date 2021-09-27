
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JApplet;


public class Fire extends ScreenObject implements Runnable{
    JApplet parent;
    Image[] m =new Image[4];
    ScreenManager sm;
    int currentside;
    public Fire(int x, int y, int w, int h,ScreenManager sm,int currentside) {
         super(x, y, w, h);
         this.sm=sm;
        this.parent=sm.parent;
        this.currentside=currentside;
//        m[0] = parent.getImage(parent.getCodeBase(), "dUp.gif");
//        m[1] = parent.getImage(parent.getCodeBase(), "dRight.gif");
//        m[2] = parent.getImage(parent.getCodeBase(), "dDown.gif");
//        m[3] = parent.getImage(parent.getCodeBase(), "dLeft.gif");
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.blue);
        //g.drawImage(m[currentside-1], x, y, w, h,parent);
     g.fillRect(x, y, w, h);
        
       }

    @Override
    public void run() {
        
        try {
            Thread.currentThread().sleep(300);
        }
        catch(InterruptedException ex){
        }
        sm.removeSO(this);
       }
   
    
}
