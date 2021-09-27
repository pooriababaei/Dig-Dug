
public class Level {

    ScreenManager sm;
    BackGround background;
    FRocks fr;
    Dragon dr;
    Enemy e;

    public Level(ScreenManager sm) {
        this.sm = sm;
        background = sm.parent.background;
    }

    public void levelProperty(int level) {
        switch (level) {
            case 1:
                rowNuller(0, 0, 20);
                columnNuller(15, 1, 10);
                rowNuller(13,11,5);

                rowNuller(20,10, 4);
                rowNuller(10,22, 4);
                columnNuller(22, 18, 4);
                columnNuller(6, 6, 4);

                setEnemy(20, 10);
                setEnemy(22, 18);
                setEnemy(6, 6);
                setDragon(10, 22);

                setFRocks(22, 15);
                setFRocks(15, 22);
                setFRocks(8,9);

                break;

            case 2:
                rowNuller(0, 0, 20);
                columnNuller(15, 1, 10);
                rowNuller(13,11,5);
                
                rowNuller (20,10,4 );
                rowNuller (17,20,4);
                columnNuller (25,5,4);
                break;
        }

    }

    public void setEnemy(int i, int j) {
        e = new Enemy(i * 20, j * 20, 20, 20, sm);
        sm.addSO(e);
        Thread t=new Thread(e);
        t.start();
    }

    public void setDragon(int i, int j) {
        dr = new Dragon(i * 20, j * 20, 20, 20, sm);
        sm.addSO(dr);
        Thread t= new Thread(dr);
        t.start();
    }

    public void rowNuller(int i, int j, int l) {
        for (int k = 0; k < l; k++) {
            background.bg[i + k][j] = false;
        }
    }

    public void columnNuller(int i, int j, int l) {
        for (int k = 0; k < l; k++) {
            background.bg[i][j + k] = false;

        }
    }

    public void setFRocks(int i, int j) {
        background.bg[i][j] = false;
        fr = new FRocks(i * 20, j * 20, 20, 20, sm);
        sm.addSO(fr);
        Thread t=new Thread(fr);
        t.start();
    }
}
