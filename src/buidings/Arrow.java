package buidings;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.geom.AffineTransform;

import level_maps.Level1;

public class Arrow {
	private Image arrow;
	private double x,y,towerX, towerY,dx,dy,ax,ay;
	private static final double V = 4;
	private static final int XADD = 23;
	private static final int YADD = 35;
	private Point coord;
	private AffineTransform at; 
	private double angle = 60;
	private double angleDown = 150;
	private boolean isLanding;
	private static final double M = 8;
	
	public Arrow(Image arrow, double towerX, double towerY){
		this.arrow = arrow;
		this.towerX = towerX + XADD;
		this.towerY = towerY + YADD;
		x = towerX + XADD;
		y = towerY + YADD;
		coord = new Point();
		at = new AffineTransform();
		isLanding = false;
	}
	public Point update(Level1 start,double xMob, double yMob) {
		ax = towerX - xMob;
		ay = towerY - yMob;
		if(ax>0){
			dx = -V*Math.cos(Math.toRadians(angle));
			if(towerX-ax/2<x){
				isLanding = false;
				checkHeight(ay);
			}else{
				isLanding = true;
				dy = V*Math.sin(Math.toRadians(angle))+M;
			}
		}
		else{
			dx = V*Math.cos(Math.toRadians(angle));
			if(towerX-ax/2>x){
				isLanding = false;
				checkHeight(ay);
			}else{
				isLanding = true;
				dy = V*Math.sin(Math.toRadians(angle))+M;
			}
		}
		
		x += dx;
		y += dy;
		coord.setLocation(x, y);
		return coord;
	}
	public void paint(Graphics g){
		Graphics2D g2d = (Graphics2D)g;
		at.setToTranslation(x, y);
		if(ax>0 && !isLanding)
			at.rotate(Math.toRadians(-angle), 3.5, 8);
		else if(ax>0 && isLanding)
			at.rotate(Math.toRadians(-angleDown), 3.5, 8);
		else if(ax<0 && !isLanding)
			at.rotate(Math.toRadians(angle), 3.5, 8);
		else
			at.rotate(Math.toRadians(angleDown), 3.5, 8);
		g2d.drawImage(arrow, at, null);
	}
	public void restartCoord(double x,double y){
		this.x = x + XADD;
		this.y = y + YADD;
	}
	private void checkHeight(double ay){
		if(ay>0){
			dy = -V*Math.sin(Math.toRadians(angle))-4;
		}else{
			dy = -V*Math.sin(Math.toRadians(angle))-1;
		}
	}
	public boolean isLanding(){
		return isLanding;
	}
}