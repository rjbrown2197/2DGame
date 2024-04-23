package Entity;

import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import main.GamePanel;

public class NPC_OG extends Entity{

	public NPC_OG(GamePanel gp) {
		super(gp);
		
		direction = "standing";
		speed = 2;
		getImage();
		setDialogue();
	}

	public void getImage() {
		try {
			standing = ImageIO.read(getClass().getResourceAsStream("/Player/Astronaut-1.png.png"));
			back1 = ImageIO.read(getClass().getResourceAsStream("/Player/Astronaut Back1 (1).png"));
			back2 = ImageIO.read(getClass().getResourceAsStream("/Player/Astronaut Back2 (1).png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/Player/Astronaut Front1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/Player/Astronaut Front2.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/Astronaut Left1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/Astronaut Left2.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/Astronaut Right1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/Astronaut Right2.png"));
			rightHalf = ImageIO.read(getClass().getResourceAsStream("/player/Astronaut Right Half.png"));
			left3 = ImageIO.read(getClass().getResourceAsStream("/player/Astronaut Left Half.png"));
			left4 = ImageIO.read(getClass().getResourceAsStream("/player/Astronaut Left Half.png"));
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setDialogue() {
		
		dialogues[0] = "1";
		dialogues[1] = "There are all kinds of monsters crawling this planet./nIf you're looking to mine the [valuable shit?], /nyou'll have to work your way to the other side of the /nplanet.";
		dialogues[2] = "3";
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
	
	public void speak() {
		
		if(dialogues[dialogueIndex] == null) {
			dialogueIndex = 0;
		}
		gp.ui.currentDialogue = dialogues[dialogueIndex];
		dialogueIndex++;
		
		switch(gp.player.direction) {
		case "up":
			direction = "down";
			break;
		case "down":
			direction = "up";
			break;
		case "left":
			direction = "right";
			break;
		case "right":
			direction = "left";
			break;
		}
	}
	
}
