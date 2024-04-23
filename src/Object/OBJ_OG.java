package Object;

import java.io.IOException;

import javax.imageio.ImageIO;

import Entity.Entity;
import main.GamePanel;

public class OBJ_OG extends Entity{

	
	public OBJ_OG(GamePanel gp) {
		super(gp);
		
		name = "OG";
		down1 = setup("/npc/Space Solider Forward1", gp.tileSize, gp.tileSize);
	}
}
