package monster;

import java.util.Random;

import Entity.Entity;
import main.GamePanel;

public class MON_SpaceSolider extends Entity{
	
	GamePanel gp;
	
	public MON_SpaceSolider(GamePanel gp) {
		super(gp);
		
		this.gp = gp;
		
		type = 2;
		name = "Space Solider";
		speed = 2;
		maxLife = 5;
		life = maxLife;
		attack = 5;
		
		//solidArea.x = 3;
		//solidArea.y = 18;
		//solidArea.width = 42;
		//solidArea.height = 30;
		solidArea.x = 12;
		solidArea.y = 29;
		solidArea.width = 42;
		solidArea.height = 30;
		
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;	
		getImage();
	}
	public void getImage() {
		standing = setup("/monster/Space Solider Forward1", gp.tileSize, gp.tileSize);
		back1 = setup("/monster/Space Solider Forward1", gp.tileSize, gp.tileSize);
		back2 = setup("/monster/Space Solider Forward2", gp.tileSize, gp.tileSize);
		down1 = setup("/monster/Space Solider Forward1", gp.tileSize, gp.tileSize);
		down2 = setup("/monster/Space Solider Forward2", gp.tileSize, gp.tileSize);
		left1 = setup("/monster/Space Solider Left1", gp.tileSize, gp.tileSize);
		left2 = setup("/monster/Space Solider Left2", gp.tileSize, gp.tileSize);
		left3 = setup("/monster/Space Solider Left3", gp.tileSize, gp.tileSize);
		left4 = setup("/monster/Space Solider Left4", gp.tileSize, gp.tileSize);
		right1 = setup("/monster/Space Solider Forward1", gp.tileSize, gp.tileSize);
		right2 = setup("/monster/Space Solider Forward2", gp.tileSize, gp.tileSize);
		rightHalf = setup("/monster/Space Solider Forward1", gp.tileSize, gp.tileSize);
	}
	
	public void setAction() {
		actionLockCounter++;
		
		if(actionLockCounter == 120) {
		    Random random = new Random();
		    int i = random.nextInt(100)+1;
		
		    if(i <= 25) {
		    	direction = "up";
		    }
		    if(i > 25 && i <= 50) {
			direction = "down";
		    }
		    if(i > 50 && i <= 75) {
			direction = "left";
		    }
		    if(i > 75 && i <= 100) {
		    	direction = "right";
		    }
		    
		    actionLockCounter = 0;
		}
	}

}
