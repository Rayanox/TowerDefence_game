package main;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;

public class Front{
	private boolean state;
	private Image frontBG, playM, resumeM;
	
	public Front(Image frontBG, Image playM, Image resumeM){
		this.frontBG = frontBG;
		this.playM = playM;
		this.resumeM = resumeM;
		state = false;
	}
	public boolean update(){
		return state;
	}
	public void paint(Graphics g, int xMouse, int yMouse){
		g.drawImage(frontBG, 0, 0, null);
		if(xMouse>33 && xMouse<163 && yMouse>414 && yMouse<454)
			g.drawImage(playM, 33, 415, null);
		else if(xMouse>33 && xMouse<163 && yMouse>472 && yMouse<512)
			g.drawImage(resumeM, 33, 473, null);
	}
	public void mouseClicked(MouseEvent arg0, int xMouse, int yMouse) {
		if(xMouse>33 && xMouse<163 && yMouse>414 && yMouse<454)
			state = true;
		else if(xMouse>33 && xMouse<163 && yMouse>472 && yMouse<512)
			state = true;
	}
	public void pause(){
		state = false;
	}
	public void play(){
		state = true;
	}
}