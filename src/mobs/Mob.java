package mobs;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class Mob implements Runnable{
	private Image[] mobs = new Image[2];
	private double x = 0;
	private double y = 0;
	private double dx = .5;
	private double dy = .2;
	private int i = 0;
	private Color col = new Color(26,75,7);
	private Color dmg = new Color(208, 1, 1);
	private double damage = 0;
	private Ellipse2D ellipse;
	private double rate = 5;
	private int price = 40;
	private Point coord;
	
	public Mob(Image[] imgs, int xLoc, int yLoc){
		mobs[0] = imgs[0];
		mobs[1] = imgs[1];
		x = xLoc - 16.5;
		y = yLoc - 22.5;
		Thread time = new Thread(this);
		time.start();
	}
	public void update() {
		if(x > 290 && x<510){
			x +=dx*.2;
			y -=dy;
		}
		if(x + dx < 751)
			x += dx;
		else
			x = 750;
	}
	public void paint(Graphics g) {
		g.setColor(Color.BLACK);
		ellipse =  new Ellipse2D.Double((int)x, (int)y+33, 33, 10);
		g.drawImage(mobs[i], (int)x, (int)y, null);
		g.drawString("xPos: "+x+"; yPos: "+y, 30, 40);
		g.setColor(col);
		g.fillRect((int)x, (int)y-5, (int) (30- damage), 3);
		if(damage>0){
			g.setColor(dmg);
			g.fillRect((int)(x+30 - damage), (int)y-5, (int)damage , 3);
		}
	}
	public void run() {
		while(true){
			switch(i){
				case 0:
					i=1;
					break;
				case 1:
					i=0;
					break;
			}
			try{
				Thread.sleep(300);
			}catch(InterruptedException inte){
				inte.printStackTrace();
			}
		}
	}
	public void drain(){
		damage += rate;
	}
	public Rectangle2D getEllipse(){
		return ellipse.getBounds2D();
	}
	public double getDamage(){
		return damage;
	}
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	public int getPrice(){
		return price;
	}
	public Point getCoord(){
		coord.setLocation(x, y);
		return coord;
	}
}