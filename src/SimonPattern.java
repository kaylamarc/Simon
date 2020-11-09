import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;

public class SimonPattern {
	
	public static final int ANIMATION_DELAY = 400; // time in ms between flashes

	private ArrayList<Integer> pattern; // holds generated pattern
	private Random r; // random integer to generate (for buttons)
	
	boolean animRunning = false;

	public SimonPattern() {
		this.pattern = new ArrayList<Integer>();
		this.r = new Random();
		
		// add a number into pattern
		addNext();
	}

	/**
	 *  adds next number to pattern
	 */
	public void addNext() {
		// 0, 1, 2, 3
		pattern.add(r.nextInt(4));
	}
	
	/**
	 * getter for pattern list
	 * @return ArrayList<Integer>
	 */
	public ArrayList<Integer> getPattern() {
		return pattern;
	}
	
	/**
	 * animates the pattern
	 */
	public void animate() {
		if (animRunning) {
			return;
		}
		animRunning = true;
		
		// don't take user input while animating
		SimonGUI.instance.getButton(0).setEnabled(false);
		SimonGUI.instance.getButton(1).setEnabled(false);
		SimonGUI.instance.getButton(2).setEnabled(false);
		SimonGUI.instance.getButton(3).setEnabled(false);
		
		// loop through pattern and animate it by darkening the button and resetting color
		for(Integer i: pattern) {
			JButton current = SimonGUI.instance.getButton(i);
			Color original = current.getBackground();
			current.setBackground(original.darker());
			try {
				Thread.sleep(ANIMATION_DELAY);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			current.setBackground(original);
			try {
				Thread.sleep(ANIMATION_DELAY);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		// re-allow user to click
		SimonGUI.instance.getButton(0).setEnabled(true);
		SimonGUI.instance.getButton(1).setEnabled(true);
		SimonGUI.instance.getButton(2).setEnabled(true);
		SimonGUI.instance.getButton(3).setEnabled(true);
		
		animRunning = false;
	}
}