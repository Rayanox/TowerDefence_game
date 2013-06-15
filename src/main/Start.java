package main;
import java.applet.Applet;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.net.URL;

import level_maps.Level1;

public class Start extends Applet implements Runnable, MouseListener, MouseMotionListener, KeyListener{
	private static final long serialVersionUID = 1L;
	private static boolean play = true;
	private static boolean drawLightning = false;
	private static final Dimension APPLETSIZE = new Dimension(750,550);
	private static URL url;
	private Image backgroundImage, selectImage, towerImage, arrow, frontBG, playM, resumeM, bufferImg, menu, specButtonA, lightning;
	private Image [] mobs = new Image[2];
	private Graphics bufferGraphics;
	private Front gameStart;
	private int xMouse;
	private int yMouse;
	private Level1 lvlObj1 = new Level1();
	private static int state = 0;
	private static int currentLvl = 1;
	private static final String GAMEOVER = "GAME OVER";
	private Font overFont = new Font("SansSerif", Font.BOLD, 20);
	
	public void start(){
		Thread thread = new Thread(this);
		thread.start();
	}
	public void init() {
		setSize(APPLETSIZE);
		addMouseListener(this);
		addMouseMotionListener(this);
		addKeyListener(this);
		try{
			url = getDocumentBase();
		}catch(Exception e){
			e.printStackTrace();
		}
		frontBG = getImage(url,  "res/gameFront.png");
		menu = getImage(url, "res/menu.png");
		lightning = getImage(url, "res/lightning.png");
		specButtonA = getImage(url, "res/specButtonA.png");
		playM = getImage(url, "res/playButton_red.png");
		resumeM = getImage(url, "res/resume_red.png");
		backgroundImage = getImage(url, "res/level1.png");
		selectImage = getImage(url, "res/selection.png");
		towerImage = getImage(url, "res/archers.png");
		mobs[0] = getImage(url, "res/mobLow.png");
		mobs[1] = getImage(url, "res/mobHigh.png");
		arrow = getImage(url, "res/arrows_left.png");
		lvlObj1.init(backgroundImage , selectImage, towerImage, arrow, mobs);
		gameStart = new Front(frontBG, playM, resumeM);
	}
	public void run(){
		while(play){
			repaint();
			try{
				Thread.sleep(17);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}
	public void update(Graphics g){
		switch(state){
			case 0:
				if(gameStart.update())
					state = currentLvl;
				break;
			case 1:
				lvlObj1.update(g);
				break;
		}
		bufferImg = createImage(APPLETSIZE.width, APPLETSIZE.height);
		bufferGraphics = bufferImg.getGraphics();
		paint(bufferGraphics);
		bufferGraphics.dispose();
		g.drawImage(bufferImg, 0, 0, this);
	}
	public void paint(Graphics g){
		switch(state){
			case 0:
				gameStart.paint(g, xMouse, yMouse);
				break;
			case 1:
				lvlObj1.paint(g, xMouse, yMouse);
				g.drawImage(menu, 0, 445, null);
				Player.drawCoins(g);
				if(xMouse>12 && xMouse<175 && yMouse>488 && yMouse<538){
					g.drawImage(specButtonA, 12, 488, this);
				}
				if(drawLightning){
					g.drawImage(lightning, 200, 50, this);
				}
				if(lvlObj1.isMobGone()){
					g.setFont(overFont);
					g.setColor(Color.RED);
					g.drawString(GAMEOVER, APPLETSIZE.width/2-100, APPLETSIZE.height/2);
					g.setFont(getFont());
				}
				break;
		}
		g.setColor(Color.BLACK);
		g.drawString("("+xMouse+","+yMouse+")",xMouse,yMouse);
	}
	public void mouseMoved(MouseEvent e) {
		xMouse = e.getX();
		yMouse = e.getY();
	}
	public void keyPressed(KeyEvent evt) {
		int keyCode = evt.getKeyCode();
		switch (state) {
		case 0:
			if(keyCode == KeyEvent.VK_P){
				gameStart.play();
				state = currentLvl;
			}else if(keyCode == KeyEvent.VK_R){
				gameStart.play();
				state = currentLvl;
			}
			break;
		default:
			if(keyCode == KeyEvent.VK_ESCAPE){
				state = 0;
				gameStart.pause();
			}else if(keyCode == KeyEvent.VK_R){
				lvlObj1.init(backgroundImage, selectImage, towerImage, arrow, mobs);
				state = currentLvl;
			}
			break;
		}
	}
	public void mouseEntered(MouseEvent e) {}
	public void mouseClicked(MouseEvent e) {
		switch(state){
			case 0:
				gameStart.mouseClicked(e, xMouse, yMouse);
				break;
			case 1:
				lvlObj1.mouseClicked(e);if(xMouse>12 && xMouse<175 && yMouse>488 && yMouse<538){
					drawLightning = true;
				}
				break;
		}	
	}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void keyReleased(KeyEvent arg0) {}
	public void keyTyped(KeyEvent evt) {}
	public void mouseDragged(MouseEvent e) {}
}