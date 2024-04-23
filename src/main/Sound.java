package main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {

	//NOTES: TO ADD SOUND EFFECT, CALL GP.PLAYSE (THE ONE IN GAMEPANEL CLASS) WITHIN WHATEVER CLASS-METOHOD YOU ARE TRYING TO ADD SOUND ON
	
	Clip clip;
	URL soundURL[] = new URL[30];
	
	public Sound() {
		
		soundURL[0] = getClass().getResource("/sound/1b.wav");
		//soundURL[1] = getClass().getResource("/sound/1a.wav"); add more sound effects and songs to the array 
		
	}
	
	public void setFile(int i) {
		
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
			clip = AudioSystem.getClip();
			clip.open(ais);
		} catch(Exception e) {
			
		}
	}
	
	public void play() {
		
		clip.start();
	}
	
	public void loop() {
		
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	public void stop() {
		
		clip.stop();
	}
}
