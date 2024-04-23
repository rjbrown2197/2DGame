package main;

import Entity.NPC_OG;
import Object.OBJ_OG;
import monster.MON_Red;
import monster.MON_SpaceSolider;

public class AssetSetter {

	GamePanel gp;
	
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setObject() {
		
	}
	
	public void setNPC() {
		gp.npc[0] = new NPC_OG(gp);
		gp.npc[0].worldX = 26 * gp.tileSize;
		gp.npc[0].worldY = 19 * gp.tileSize;
	}
	
	public void setMonster() {
		gp.monster[0] = new MON_SpaceSolider(gp);
		gp.monster[0].worldX = gp.tileSize*25;
		gp.monster[0].worldY = gp.tileSize*19;
		
		gp.monster[1] = new MON_SpaceSolider(gp);
		gp.monster[1].worldX = gp.tileSize*27;
		gp.monster[1].worldY = gp.tileSize*19;
		
		gp.monster[2] = new MON_SpaceSolider(gp);
		gp.monster[2].worldX = gp.tileSize*20;
		gp.monster[2].worldY = gp.tileSize*19;
		
		gp.monster[3] = new MON_SpaceSolider(gp);
		gp.monster[3].worldX = gp.tileSize*15;
		gp.monster[3].worldY = gp.tileSize*26;
		
		gp.monster[4] = new MON_SpaceSolider(gp);
		gp.monster[4].worldX = gp.tileSize*20;
		gp.monster[4].worldY = gp.tileSize*26;
		
		gp.monster[5] = new MON_Red(gp);
		gp.monster[5].worldX = gp.tileSize*25;
		gp.monster[5].worldY = gp.tileSize*32;
	}
	
	
	
	
	
	
}
