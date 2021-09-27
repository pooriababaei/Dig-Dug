/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
 *
 * @author ui
 */
public class Score extends ScreenObject{
    private int sc=0;
    
    
 public Score(int x, int y, int w, int h){
     super(x,y,w,h);
     
     
 }
 
 public void incScore(int d){
     sc+=d;
 }
 
 
    @Override
    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.setFont(new Font("Arial",Font.BOLD,h));
        g.drawString("score : ", x - 100, y + h/2);
        g.drawString(sc+"", x, y+h/2);
     
     }
    
}
