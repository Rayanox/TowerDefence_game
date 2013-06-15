package main;
import java.awt.Color;
import java.awt.Graphics;

public class Player {
	private static int coins = 87;
	
	
	public static void drawCoins(Graphics g){
		g.setColor(Color.YELLOW);
		g.drawString(Integer.toString(coins), 50, 470);
	}
	public void increaseCoins(int amount){
		coins += amount;
	}
	public void decreaseCoins(int amount){
		coins -= amount;
	}
	public int getCoins(){
		return coins;
	}
}