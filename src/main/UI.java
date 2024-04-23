package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import Object.OBJ_Health;
import Object.OBJ_OG;
import Entity.Entity;

public class UI {
//NOTE: Come back to the drawPlayerLife() method CALL, it is commented out now because we have to do the scaling (draw faster?) video first
//NOTE: The drawPlayerLife() method right now uses a loop to display the 3 'hearts' in the tutorial, i dont want to use hearts so will have to edit that portion of the method to just display 1 image and change which image it is, or just display 100 and drop every time player is hit or something
	
	Graphics2D g2;
	GamePanel gp;
	Font arial_40;
	BufferedImage health_full, health_half, health_blank;
	public boolean messageOn = false;
	public String message = "";
	int messageCounter = 0;
	public boolean gameFinished = false;
	public String currentDialogue = "";
	
	public UI(GamePanel gp) {
		this.gp = gp;
		
		arial_40 = new Font("Arial", Font.PLAIN, 40);
		
		//CREATE HUD OBJECT
		Entity health = new OBJ_Health(gp);
		health_full = health.image;
		health_half = health.image2;
		health_blank = health.image3;
	}
	
	
	public void showMessage(String text) {
		message = text;
		messageOn = true;
	}
	
	public void draw(Graphics2D g2) {
		
		this.g2 = g2;
		
		g2.setFont(arial_40);
		g2.setColor(Color.white);
		
		//PLAY STATE
		if(gp.gameState == gp.playState) {
			drawPlayerLife();
		}
		//PAUSE STATE
        if(gp.gameState == gp.pauseState) {
        	drawPlayerLife();
        	drawPauseScreen();
        }
        //DIALOGUE STATE
        if(gp.gameState == gp.dialogueState) {
        	drawPlayerLife();
        	drawDialogueScreen();
        }
	}
	public void drawPauseScreen() {
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
		String text = "PAUSED";
		int x = getXforCenteredText(text);
		int y = gp.screenHeight/2;
		
		g2.drawString(text, x, y);
	}
	
	public void drawDialogueScreen() {
		
		//WINDOW
		int x = gp.tileSize*2;
		int y = gp.tileSize/2;
		int width = gp.screenWidth - (gp.tileSize*4);
		int height = gp.tileSize*4;
		drawSubWindow(x, y, width, height);
		
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32));
		x += gp.tileSize;
		y += gp.tileSize;
		
		for(String line : currentDialogue.split("/n")) {
			g2.drawString(line, x, y);
			y += 40;
		}
	
	}
	
	public void drawSubWindow(int x, int y, int width, int height) {
		Color c = new Color(0,0,0,200);
		g2.setColor(c);
		g2.fillRoundRect(x, y, width, height, 35, 35);
		c = new Color(255,255,255);
		g2.setColor(c);
		g2.setStroke(new BasicStroke(5));
		g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);
		
	}
	
	public int getXforCenteredText(String text) {
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = gp.screenWidth/2 - length/2;
		return x;
	}
	
	public void drawPlayerLife() {

			g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 20F));
			String text = "HEALTH: " + gp.player.life + "%";
			int x = 20;
			int y = 35;
			
			g2.drawString(text, x, y);
		
	}
	
}
