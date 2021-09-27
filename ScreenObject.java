/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author ui
 */
public abstract class ScreenObject {
    int x;
    int y;
    int w;
    int h;
    public ScreenObject(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }
    public abstract void draw(Graphics g);
}
