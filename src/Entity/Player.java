package Entity;


import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;
import monster.MON_SpaceSolider;
import monster.MON_Red;

public class Player extends Entity{

	KeyHandler keyH;
	
	public final int screenX;
	public final int screenY;
	
	public Player(GamePanel gp, KeyHandler keyH) {
		
		super(gp);
		this.keyH = keyH;
		
		screenX = gp.screenWidth/2 - (gp.tileSize/2);
		screenY = gp.screenHeight/2 - (gp.tileSize/2);
		
		solidArea = new Rectangle(20,40,32,18);
		//x,20,24,h 24s
		//(17(20), 50, 32, 14);
		//The dimensions of the collision rectangle might have to be changed when we incorporate monsters
		//Not sure how it will play yet but the rectangle is on his legs with these coordinates
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		//changing these dimensions to alter the range of your attack
		attackArea.width = 36;
		attackArea.height = 36;
		
		setDefaultValues();
		getPlayerImage();
		getPlayerAttackImage();
	}
	
	public void setDefaultValues() {
		worldX = gp.tileSize * 23;
		worldY = gp.tileSize * 30;
		speed = 4;
		direction = "standing";
		
		//PLAYER STATUS
		maxLife = 100;
		life = maxLife;
	}
	public void getPlayerImage() {
		
		standing = setup("/Player/Astronaut-1.png", gp.tileSize, gp.tileSize);
		back1 = setup("/Player/Astronaut Back1 (1)", gp.tileSize, gp.tileSize);
		back2 = setup("/Player/Astronaut Back2 (1)", gp.tileSize, gp.tileSize);
		down1 = setup("/Player/Astronaut Front1", gp.tileSize, gp.tileSize);
		down2 = setup("/Player/Astronaut Front2", gp.tileSize, gp.tileSize);
		left1 = setup("/Player/Astronaut Left1", gp.tileSize, gp.tileSize);
		left2 = setup("/Player/Astronaut Left Half", gp.tileSize, gp.tileSize);
		right1 = setup("/Player/Astronaut Right1", gp.tileSize, gp.tileSize);
		right2 = setup("/Player/Astronaut Right2", gp.tileSize, gp.tileSize);
		rightHalf = setup("/Player/Astronaut Right Half", gp.tileSize, gp.tileSize);
		left3 = setup("/Player/Astronaut Left2", gp.tileSize, gp.tileSize);
		left4 = setup("/Player/Astronaut Left Half", gp.tileSize, gp.tileSize);
		
	}
	
    public void getPlayerAttackImage() {
    	//the *2 portion of these arguments might have to be altered based on what your attack tile size is - 7:20 of Hit Detection Video
    	attackUp1 = setup("/Player/Astronaut-1.png", gp.tileSize, gp.tileSize*2);
    	attackUp2 = setup("/Player/Astronaut-1.png", gp.tileSize, gp.tileSize*2);
    	attackDown1 = setup("/Player/Astronaut Forward Attack01", gp.tileSize, gp.tileSize*2);
    	attackDown2 = setup("/Player/Astronaut Forward Attack02", gp.tileSize, gp.tileSize*2);
    	attackLeft1 = setup("/Player/Astronaut Left Attack01", gp.tileSize*2, gp.tileSize);
    	attackLeft2 = setup("/Player/Astronaut Left Attack02", gp.tileSize*2, gp.tileSize);
    	attackRight1 = setup("/Player/Astronaut Right Attack01", gp.tileSize*2, gp.tileSize);
    	attackRight2 = setup("/Player/Astronaut Right Attack02 (1)", gp.tileSize*2, gp.tileSize);
    	
    }
	
//	public BufferedImage setup(String imageName) {
//		
//		UtilityTool uTool = new UtilityTool();
//		BufferedImage image = null;
//		
//		try {
//			image = ImageIO.read(getClass().getResourceAsStream("/player/" + imageName + ".png"));
//			image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
//			
//		} catch(IOException e) {
//			e.printStackTrace();
//		}
//		return image;
//	}
//	
	public void update() {
		
		if(attacking == true) {
			attacking();
		}
		else if(keyH.upPressed == true ||keyH.downPressed == true ||keyH.leftPressed == true ||keyH.rightPressed == true ||keyH.spacePressed == true) {
			
			if(keyH.upPressed == true) {
				direction = "up";
			}
			else if(keyH.downPressed == true) {
				direction = "down";
			}
			else if(keyH.leftPressed == true) {
				direction = "left";
			}
			else if(keyH.rightPressed == true) {
				direction = "right";
			}
			
			//CHECK TILE COLLISION
			collisionOn = false;
			gp.cChecker.checkTile(this);
			
			//CHECK OBJECT COLLISION
			int objIndex = gp.cChecker.checkObject(this, true);
			pickUpObject(objIndex);
			
			//CHECK NPC COLLISION 
			int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
			interactNPC(npcIndex);
			
			//CHECK MONSTER COLLISION
			int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
			contactMonster(monsterIndex);
			
			//IF COLLISION IS FALSE, PLAYER CAN MOVE
			if(collisionOn == false && keyH.spacePressed == false) {
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
			
			gp.keyH.spacePressed = false;
			
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
        if(invincible == true) {
        	invincibleCounter++;
        	if(invincibleCounter > 60) {
        		invincible = false;
        		invincibleCounter = 0;
        	}
        }
	}
	
	public void attacking() {
		//spriteCounter (frames) might have to be adjusted to fit my attack images. spriteCounter/frame stuff is detail in line 85 of Entity class. our max is 16 i think, rysnow use 5 and 25
		spriteCounter++;
		
		if(spriteCounter <= 8) {
			spriteNum = 1;
		}
		if(spriteCounter > 8 && spriteCounter <= 16) {
			spriteNum = 2;
			
			//Save the current worldX, worldY, solidArea
			int currentWorldX = worldX;
			int currentWorldY = worldY;
			int solidAreaWidth = solidArea.width;
			int solidAreaHeight = solidArea.height;
			
			//Adjust player's worldX/Y for attackArea
			switch(direction) {
			case "up": worldY -= attackArea.height; break;
			case "down": worldY += attackArea.height; break;
			case "left": worldX -= attackArea.width; break;
			case "right": worldX += attackArea.width; break;
			}
			//attackArea becomes solidArea
			solidArea.width = attackArea.width;
			solidArea.height = attackArea.height;
			//Check monster collision with the updated wordlX/Y and solidArea
			int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
			damageMonster(monsterIndex);
			
			worldX = currentWorldX;
			worldY = currentWorldY;
			solidArea.width = solidAreaWidth;
			solidArea.height = solidAreaHeight;
			
		}
		if(spriteCounter > 16) {
			spriteNum = 1;
			spriteCounter = 0;
			attacking = false;
		}
	}
	
	public void pickUpObject(int i) {
		if (i != 999) {

		}
	}
	
	public void interactNPC(int i) {
		if(gp.keyH.spacePressed == true) {
			if(i != 999) {
				 gp.gameState = gp.dialogueState;
				 gp.npc[i].speak();
			} else {
				attacking = true;
			}
			gp.keyH.spacePressed = false;
		}
	
	}
	
	public void contactMonster(int i) {
		
		if(i != 999) {
			if(invincible == false) {
				int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
				life -= gp.monster[monsterIndex].attack;
				System.out.println("X");
				invincible = true;
			}
		}
	}
	
	public void damageMonster(int i) {
		
		
		if(i != 999) {
			
	        if(monsterInvincible == true) {
	        	monsterInvincibleCounter++;
	        	if(monsterInvincibleCounter > 6) {
	        		monsterInvincible = false;
	        		monsterInvincibleCounter = 0;
	        	}
	        }
	        else{
			    gp.monster[i].life -= 1;
			    monsterInvincible = true;
			    System.out.println("hit");
		        if(gp.monster[i].life <= 0) {
	    	        gp.monster[i] = null;
			    }
		    }
	    }
	}
	
	public void draw(Graphics2D g2) {
//		g2.setColor(Color.red);
//		g2.fillRect(x, y, gp.tileSize, gp.tileSize);
		
		BufferedImage image = null;
		int tempScreenX = screenX;
		int tempScreenY = screenY;
		
		switch(direction) {
		case "standing":
			image = standing;
			break;
		case "up":
			if(attacking == false) {
				if(spriteNum == 1) {image = back1;}
				if(spriteNum == 2) {image = back2;}
				if(spriteNum == 3) {image = back1;}
				if(spriteNum == 4) {image = back2;}
			}
			if(attacking == true) {
				tempScreenY = screenY - gp.tileSize;
				if(spriteNum == 1) {image = attackUp1;}
				if(spriteNum == 2) {image = attackUp2;}
			}
			break;
		case "down":
	        if(attacking == false) {
			    if(spriteNum == 1) {image = down1;}
		    	if(spriteNum == 2) {image = down2;}
		    	if(spriteNum == 3) {image = down1;}
			    if(spriteNum == 4) {image = down2;}
			}
			if(attacking == true) {
				if(spriteNum == 1) {image = attackDown1;}
				if(spriteNum == 2) {image = attackDown2;}
			}
			break;
		case "left":
			if(attacking == false) {
		     	if(spriteNum == 1) {image = left1;}
		    	if(spriteNum == 2) {image = left2;}
		    	if(spriteNum == 3) {image = left3;}
			    if(spriteNum == 4) {image = left4;}
			}
			if(attacking == true) {
				tempScreenX = screenX - gp.tileSize;
				if(spriteNum == 1) {image = attackLeft1;}
				if(spriteNum == 2) {image = attackLeft2;}
			}
			break;
		case "right":
			if(attacking == false) {
		    	if(spriteNum == 1) {image = right1;}
			    if(spriteNum == 2) {image = rightHalf;}
			    if(spriteNum == 3) {image = right2;}
		    	if(spriteNum == 4) {image = rightHalf;}
			}
			if(attacking == true) {
				if(spriteNum == 1) {image = attackRight1;}
				if(spriteNum == 2) {image = attackRight2;}
			}
			break;
	
		}
		
		if(invincible == true) {
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
		}
		g2.drawImage(image, tempScreenX, tempScreenY, null);
		
		//Reset Alpha
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
		
		//DEBUG
//		g2.setFont(new Font("Arial", Font.PLAIN, 26));
//		g2.setColor(Color.white);
//		g2.drawString("Invincible:" + invincibleCounter, 10, 400);
	}

 }
