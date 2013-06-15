package buidings;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.geom.Ellipse2D;

import level_maps.Level1;
import mobs.Mob;


public class Tower {
	private Image image,arrowImg;
	private double xLoc, yLoc;
	private Ellipse2D el;
	private boolean shooting = false;
	private Mob shootMob = null;
	private Arrow arrow;
	private Point coord;
	private static int price = 30;
	
	public Tower (Image image,Image arrowImg, double xLoc, double yLoc){
		this.image = image;
		this.arrowImg = arrowImg;
		this.xLoc = xLoc;
		this.yLoc = yLoc;
		el = new Ellipse2D.Double(xLoc-155, yLoc, 400, 200);
		coord = new Point();
	}
	public void update(Level1 start) {
		if(!shooting){
			for(int i=0;i<start.getMobs().size();i++){
				if(el.intersects(start.getMobs().get(i).getEllipse())){
					shootMob = start.getMobs().get(i);
					arrow = new Arrow(arrowImg, xLoc+20, yLoc+10);
					shooting = true;
				}
			}
		}else{
			if(el.intersects(shootMob.getEllipse()) && shooting){
					coord = arrow.update(start, shootMob.getX()+16.5, shootMob.getY()+22.5);
					if(coord.getX()>shootMob.getX()&&coord.getX()<shootMob.getX()+33 && coord.getY()>shootMob.getY()
							&& coord.getY()<shootMob.getY()+45 && arrow.isLanding()){
					shootMob.drain();
					arrow.restartCoord(xLoc+20,yLoc+10);
					if(shootMob.getDamage()>=30){
						shootMob = null;
						arrow = null;
						shooting = false;
					}
				}else if((((coord.getX()<shootMob.getX()-10 && coord.getX()>shootMob.getX()-30)||(coord.getX()>shootMob.getX()+55 &&
						coord.getX()<shootMob.getX()+57))&&(coord.getY()>shootMob.getY()&& coord.getY()<shootMob.getY()+45)&&
						arrow.isLanding())||(coord.getX()>750||coord.getX()<0 || coord.getY()<0 || coord.getY()> 550)){
					arrow.restartCoord(xLoc+20, yLoc+10);
				}
			}else{
				arrow = null;
			}
		}
	}
	public void paint(Graphics g){
		g.drawImage(image, (int)xLoc, (int)yLoc, null);
		if(shooting&&arrow!=null)
			arrow.paint(g);
	}
	public Ellipse2D getElipse() {
		return el;
	}
	public static int getTowerPrice(){
		return price;
	}
}