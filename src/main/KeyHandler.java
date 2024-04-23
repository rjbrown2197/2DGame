package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{

	GamePanel gp;
	public boolean upPressed, downPressed, leftPressed, rightPressed, spacePressed;
	//DEBUG
	boolean checkDrawTime = false;
	
	public KeyHandler(GamePanel gp) {
		this.gp = gp;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		int code = e.getKeyCode();
		
		//PLAY STATE
		if(gp.gameState == gp.playState) {
			
			if(code == KeyEvent.VK_9) {
				upPressed = true;
			}
			if(code == KeyEvent.VK_O) {
				downPressed = true;
			}
			if(code == KeyEvent.VK_I) {
				leftPressed = true;
			}
			if(code == KeyEvent.VK_P) {
				rightPressed = true;
			}
            if(code == KeyEvent.VK_1) {
				gp.gameState = gp.pauseState;
		        gp.stopMusic();
			}
            if(code == KeyEvent.VK_SPACE) {
                spacePressed = true;
			}
			//DEBUG
			if(code == KeyEvent.VK_T) {
				if(checkDrawTime == false) {
					checkDrawTime = true;
				} else if(checkDrawTime == true) {
					checkDrawTime = false;
				}
			}
		}
		//PAUSE STATE
		else if(gp.gameState == gp.pauseState) {
			if(code == KeyEvent.VK_1) {
				gp.gameState = gp.playState;
				gp.playMusic(0);
			}
		}
		
		//DIALOGUE STATE
		else if(gp.gameState == gp.dialogueState) {
			if(code == KeyEvent.VK_SPACE) {
				gp.gameState = gp.playState;
			}
		}
		
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		if(code == KeyEvent.VK_9) {
			upPressed = false;
		}
		if(code == KeyEvent.VK_O) {
			downPressed = false;
		}
		if(code == KeyEvent.VK_I) {
			leftPressed = false;
		}
		if(code == KeyEvent.VK_P) {
			rightPressed = false;
		}
	}
	
}
