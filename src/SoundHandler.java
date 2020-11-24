import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

// Handles sounds
public class SoundHandler {

	// sounds
	private AudioInputStream gameover;
	private AudioInputStream button1;
	private AudioInputStream button2;
	private AudioInputStream button3;
	private AudioInputStream button4;
	
	public SoundHandler() {
	}
	
	/**
	 * plays gameover sound
	 */
	public void playGameover() {
		try {
			gameover = AudioSystem.getAudioInputStream(new File("sounds/gameover.wav"));
			Clip clip = AudioSystem.getClip();
			clip.open(gameover);
			clip.start();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * plays button 1 sound
	 */
	public void playButton1() {
		try {
			button1 = AudioSystem.getAudioInputStream(new File("sounds/button1.wav"));
			Clip clip = AudioSystem.getClip();
			clip.open(button1);
			clip.start();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * plays button 2 sound
	 */
	public void playButton2() {
		try {
			button2 = AudioSystem.getAudioInputStream(new File("sounds/button2.wav"));
			Clip clip = AudioSystem.getClip();
			clip.open(button2);
			clip.start();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * plays button 3 sound
	 */
	public void playButton3() {
		try {
			button3 = AudioSystem.getAudioInputStream(new File("sounds/button3.wav"));
			Clip clip = AudioSystem.getClip();
			clip.open(button3);
			clip.start();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * plays button 4 sound
	 */
	public void playButton4() {
		try {
			button4 = AudioSystem.getAudioInputStream(new File("sounds/button4.wav"));
			Clip clip = AudioSystem.getClip();
			clip.open(button4);
			clip.start();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		}
	}
}
