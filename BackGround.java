
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JApplet;

public class BackGround extends ScreenObject {

    ScreenManager sm;
    Level level;
    Boolean[][] bg = new Boolean[30][30];
    JApplet parent;

    public BackGround(int x, int y, int w, int h, ScreenManager sm) {
        super(x, y, w, h);
        this.sm = sm;
        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 30; j++) {
                bg[i][j] = true;
            }
        }
        for (int i = 0; i < 30; i++) {
            bg[i][0] = false;
        }
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.ORANGE);
        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 30; j++) {
                if (bg[i][j] == true) {
                    g.fillRect(i * 20, j * 20, 20, 20);
                }
            }
        }
    }
    public void fillAll () {
        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 30; j++) {
                bg[i][j]=true;
            }
        }
    }
}
