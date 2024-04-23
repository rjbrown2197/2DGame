package Entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class Entity {

	GamePanel gp;
	public BufferedImage standing, back1, back2, down1, down2, left1, left2, right1, right2, rightHalf, left3, left4;
	public BufferedImage attackUp1, attackUp2, attackDown1, attackDown2, attackLeft1, attackLeft2, attackRight1, attackRight2;
	public BufferedImage image, image2, image3;
	public Rectangle solidArea = new Rectangle(0, 0, 72, 72);
	public Rectangle attackArea = new Rectangle(0, 0, 0, 0);
	public int solidAreaDefaultX, solidAreaDefaultY;
	public boolean collision = false;
	String dialogues[] = new String[20];

	//STATE
	public int worldX, worldY;
	public String direction = "down";
	public int spriteNum = 1;
	int dialogueIndex = 0;
	public boolean collisionOn = false;
	public boolean invincible = false;
	public boolean monsterInvincible = false;
	boolean attacking = false;
	
	//COUNTER
	public int spriteCounter = 0;
	public int actionLockCounter;
	public int invincibleCounter = 0;
	public int monsterInvincibleCounter = 0;
	
	//CHARACTER ATTRIBUTES
	public int type; //0 = player, 1 = npc, 2 = monster
	public String name;
	public int speed;
	public int maxLife;
	public int life;
	public int attack;
	
	public Entity(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setAction() {}
	public void speak() {}
	
	
	public void update() {
	    setAction();
	    collisionOn = false;
	    gp.cChecker.checkTile(this);
	    gp.cChecker.checkObject(this, false);
	    gp.cChecker.checkEntity(this, gp.npc);
	    gp.cChecker.checkEntity(this, gp.monster);
	    boolean contactPlayer = gp.cChecker.checkPlayer(this);
	    
	    if(this.type == 2 && contactPlayer == true) {
	    	if(gp.player.invincible == false) {
	    		//give damage
	    		gp.player.life -= this.attack;
	    		gp.player.invincible = true;
	    	}
	    }
	    
		//IF COLLISION IS FALSE, PLAYER CAN MOVE
		if(collisionOn == false) {
			switch(direction) {
			case "up": worldY -= speed;
				break;
			case "down": worldY += speed;
				break;
			case "left": worldX -= speed;
				break;
			case "right": worldX += speed;
				break;
			}
		}
		
		spriteCounter++;
		if (spriteCounter > 16) {
			if (spriteNum == 1) {
				spriteNum = 2;
			}
			else if (spriteNum == 2) {
				spriteNum = 3;
			}
			else if (spriteNum == 3) {
				spriteNum = 4;
			}
			else if (spriteNum == 4) {
				spriteNum = 1;
			}
			spriteCounter = 0;
		}
	}
	

	
	public void draw(Graphics2D g2) {
		
		BufferedImage image = null;
		
		int screenX = worldX - gp.player.worldX + gp.player.screenX;
		int screenY = worldY - gp.player.worldY + gp.player.screenY;
				
		if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && 
			worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
			worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
			worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
			
			switch(direction) {
			case "standing":
				image = standing;
				break;
			case "up":
				if(spriteNum == 1) {image = back1;}
				if(spriteNum == 2) {image = back2;}
				if(spriteNum == 3) {image = back1;}
				if(spriteNum == 4) {image = back2;}
				break;
			case "down":
				if(spriteNum == 1) {image = down1;}
				if(spriteNum == 2) {image = down2;}
				if(spriteNum == 3) {image = down1;}
				if(spriteNum == 4) {image = down2;}
				break;
			case "left":
				if(spriteNum == 1) {image = left1;}
				if(spriteNum == 2) {image = left2;}
				if(spriteNum == 3) {image = left3;}
				if(spriteNum == 4) {image = left4;}
				break;
			case "right":
				if(spriteNum == 1) {image = right1;}
				if(spriteNum == 2) {image = rightHalf;}
				if(spriteNum == 3) {image = right2;}
				if(spriteNum == 4) {image = rightHalf;}
				break;
				
			}
					
			g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
		}
	}
	public BufferedImage setup(String imagePath, int width, int height) {
		
		UtilityTool uTool = new UtilityTool();
		BufferedImage image = null;
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
			image = uTool.scaleImage(image, width, height);
			
		} catch(IOException e) {
			e.printStackTrace();
		}
		return image;
	}
}
