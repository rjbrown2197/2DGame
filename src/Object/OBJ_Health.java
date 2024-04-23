package Object;

import java.io.IOException;

import javax.imageio.ImageIO;

import Entity.Entity;
import main.GamePanel;

public class OBJ_Health extends Entity{
//NOTE: will have to come back to the health/heart video to see how to change these images to scaled images (maybe try the rendering performance video)
	GamePanel gp;
	
	public OBJ_Health(GamePanel gp) {
		
		super(gp);
		
		name = "Health";
		image = setup("/Player/Astronaut-1.png", gp.tileSize, gp.tileSize);
		image2 = setup("/Player/Astronaut-1.png", gp.tileSize, gp.tileSize);
		image3 = setup("/Player/Astronaut-1.png", gp.tileSize, gp.tileSize);
	}
}
