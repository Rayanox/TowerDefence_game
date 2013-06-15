package level_maps;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;
import buidings.Tower;
import main.Player;
import mobs.Mob;

public class Level1 {
	private Image backgroundImage, selectImage, towerImage, arrow;
	private Image [] mobs = new Image[2];
	private static ArrayList<Mob> villains;
	private static final int MOBNUM = 4;
	private Random random;
	private Tower[] towers;
	private Player player;
	private boolean mobGone;
	
	public void init(Image backgroundImage, Image selectImage, Image towerImage, Image arrow, Image[]mobsi){
		this.backgroundImage = backgroundImage;
		this.selectImage = selectImage;
		this.towerImage = towerImage;
		this.arrow = arrow;
		this.mobs = mobsi;
		
		mobGone = false;
		player = new Player();
		random = new Random();
		towers = new Tower[5];
		villains = new ArrayList<Mob>();
		
		for(int i=0;i<MOBNUM;i++){
			int yLoc = 250 + (i*5);
			int xLoc = 30 - random.nextInt(150);
			villains.add(new Mob(mobs, xLoc, yLoc));
		}
	}
	public void paint(Graphics g, int xMouse, int yMouse) {
		g.drawImage(backgroundImage, 0, 0, null);
			
		if(xMouse>100 && xMouse<186 && yMouse>178 && yMouse<202){
			g.drawImage(selectImage, 96, 174, null);
		}else if(xMouse>381 && xMouse<467 && yMouse>146 && yMouse<169){
			g.drawImage(selectImage, 377, 142, null);
		}else if(xMouse>628 && xMouse<714 && yMouse>130 && yMouse<153){
			g.drawImage(selectImage, 624, 126, null);
		}else if(xMouse>232 && xMouse<318 && yMouse>372 && yMouse<395){
			g.drawImage(selectImage, 229, 368, null);
		}else if(xMouse>608 && xMouse<694 && yMouse>316 && yMouse<339){
			g.drawImage(selectImage, 604, 312, null);
		}
		for(Tower arch:towers){
			if(arch!=null)
				arch.paint(g);
		}
		for(Mob mob:villains){
			mob.paint(g);
		}
	}
	public void update(Graphics g) {
		for(int i=0;i<villains.size();i++){
			villains.get(i).update();
			if(villains.get(i).getX()>=750){
				mobGone = true;
			}
			if(villains.get(i).getDamage()>=30){
				player.increaseCoins(villains.get(i).getPrice());
				villains.remove(i);
			}
		}
		for(Tower arch:towers){
			if(arch!=null)
				arch.update(this);
		}		
	}
	public void mouseClicked(MouseEvent e) {
		if(e.getX()>100 && e.getX()<186 && e.getY()>178 && e.getY()<202){
			if(towers[0]==null && player.getCoins() - Tower.getTowerPrice() >= 0){
				towers[0] = new Tower(towerImage,arrow, 98, 101);
				player.decreaseCoins(Tower.getTowerPrice());
			}
		}
		if(e.getX()>381 && e.getX()<467 && e.getY()>146 && e.getY()<169){
			if(towers[1]==null && player.getCoins() - Tower.getTowerPrice() >= 0){
				towers[1] = new Tower(towerImage,arrow, 379, 69);
				player.decreaseCoins(Tower.getTowerPrice());
			}
		}
		if(e.getX()>628 && e.getX()<714 && e.getY()>130 && e.getY()<153){
			if(towers[2]==null && player.getCoins() - Tower.getTowerPrice() >= 0){
				towers[2] = new Tower(towerImage,arrow, 626, 53);
				player.decreaseCoins(Tower.getTowerPrice());
			}
		}
		if(e.getX()>232 && e.getX()<318 && e.getY()>372 && e.getY()<395){
			if(towers[3]==null && player.getCoins() - Tower.getTowerPrice() >= 0){
				towers[3] = new Tower(towerImage,arrow, 231, 295);
				player.decreaseCoins(Tower.getTowerPrice());
			}
		}
		if(e.getX()>608 && e.getX()<694 && e.getY()>316 && e.getY()<339){
			if(towers[4]==null && player.getCoins() - Tower.getTowerPrice() >= 0){
				towers[4] = new Tower(towerImage,arrow, 606, 239);
				player.decreaseCoins(Tower.getTowerPrice());
			}
		}
	}
	public ArrayList<Mob> getMobs(){
		return villains;
	}
	public boolean isMobGone(){
		return mobGone;
	}
	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}
	public void mouseReleased(MouseEvent arg0) {}
	public void mousePressed(MouseEvent e) {}
}