import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class SimonGUI {

	// GUI info
	private static final int WIDTH = 800;
	private static final int HEIGHT = 800;
	private static final Color BACKGROUNDCOLOR = Color.GRAY;

	// score for game
	private int score;

	public static SimonGUI instance;

	// frame for game
	private JFrame frame;
	private JPanel currentPanel;

	private JPanel scorePanel;
	private JLabel scoreLabel;

	// elements for starting screen
	private JPanel startPanel;
	private JButton startButton;

	// element for play screen
	private JPanel playPanel;
	private JButton redButton;
	private JButton yellowButton;
	private JButton blueButton;
	private JButton greenButton;

	private SimonPattern pattern;

	// users answers
	private ArrayList<Integer> userPick;

	private static SoundHandler sounds;

	// create GUI for game
	public SimonGUI() {
		// set the instance of this class to this object
		instance = this;

		// create soundhandler
		sounds = new SoundHandler();

		// initial score is 0
		score = 0;

		// users list of button presses
		userPick = new ArrayList<Integer>();

		// create and initialize GUI elements
		createFrame();
		createStartButton();
		createScorePanel();
		createStartPanel();
		createPlayButtons();
		createPlayPanel();

		// initialize pattern
		this.pattern = new SimonPattern();

	}

	/**
	 * creates the frame for the game
	 */
	public void createFrame() {
		frame = new JFrame("Simon");
		frame.setSize(WIDTH, HEIGHT);
		frame.getContentPane().setBackground(BACKGROUNDCOLOR);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.setVisible(true);
	}

	/**
	 * creates the score panel and label
	 */
	public void createScorePanel() {
		scorePanel = new JPanel();
		scoreLabel = new JLabel("SCORE: " + score);
		scorePanel.setBackground(BACKGROUNDCOLOR);
		scorePanel.setForeground(Color.WHITE);
		scoreLabel.setBackground(Color.LIGHT_GRAY);
		scoreLabel.setForeground(Color.WHITE);
		scorePanel.add(scoreLabel);
		frame.add(scorePanel, BorderLayout.NORTH);
	}

	/**
	 * creates the start panel
	 */
	public void createStartPanel() {
		startPanel = new JPanel();
		startPanel.setSize(WIDTH, HEIGHT);
		startPanel.setBackground(BACKGROUNDCOLOR);
		startPanel.setLayout(null);
		startPanel.add(startButton);
		startPanel.setVisible(true);
		frame.add(startPanel, BorderLayout.CENTER);
		currentPanel = startPanel;
	}

	/**
	 * creates the start button
	 */
	public void createStartButton() {
		startButton = new JButton("START");
		startButton.setBackground(Color.GREEN);
		startButton.setForeground(Color.WHITE);
		startButton.setFocusPainted(false);
		startButton.setBounds(100, 300, 600, 200);
		startButton.addActionListener(new StartButtonListener());
	}

	/**
	 * creates the play panel
	 */
	public void createPlayPanel() {
		playPanel = new JPanel();
		playPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		playPanel.setBackground(BACKGROUNDCOLOR);

		GridLayout playLayout = new GridLayout(2, 2);

		playPanel.setLayout(playLayout);

		// add buttons to panel
		playPanel.add(redButton);
		playPanel.add(yellowButton);
		playPanel.add(greenButton);
		playPanel.add(blueButton);
	}

	/**
	 * create all play buttons and their listeners
	 */
	public void createPlayButtons() {

		// create each button
		redButton = new JButton();
		yellowButton = new JButton();
		greenButton = new JButton();
		blueButton = new JButton();

		// set play button colors
		redButton.setBackground(Color.RED);
		yellowButton.setBackground(Color.YELLOW);
		greenButton.setBackground(Color.GREEN);
		blueButton.setBackground(Color.BLUE);

		// button listener for red button and reaction to button press
		redButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onButtonPress(0);
			}
		});

		// button listener for red button and reaction to button press
		yellowButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onButtonPress(1);
			}
		});

		// button listener for red button and reaction to button press
		greenButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onButtonPress(2);
			}
		});

		// button listener for red button and reaction to button press
		blueButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onButtonPress(3);
			}
		});

	}

	/**
	 * set a panel to be the active panel on the frame
	 * 
	 * @param panel
	 */
	public void setActivePanel(JPanel panel) {
		frame.remove(currentPanel);
		frame.add(panel, BorderLayout.CENTER);
		frame.pack();
		panel.setVisible(true);
		currentPanel = panel;
	}

	/**
	 * returns the button for each corresponding number
	 * 
	 * @param num (corresponding button number)
	 * @return corresponding button
	 */
	public JButton getButton(int num) {
		switch (num) {
		case 0:
			return redButton;
		case 1:
			return yellowButton;
		case 2:
			return greenButton;
		case 3:
			return blueButton;
		default:
			return null;
		}
	}

	/**
	 *  play correct button sound for each button
	 * @param num (corresponding button number)
	 */
	public static void playButtonSound(int num) {
		switch(num) {
		case 0:
			sounds.playButton1();
			break;
		case 1:
			sounds.playButton2();
			break;
		case 2:
			sounds.playButton3();
			break;
		case 3:
			sounds.playButton4();
			break;
		default:
			break;
		}
	}

	/**
	 * add user selection to their list of picks and check if their button press was
	 * correct update score and GUI if correct and continue animation otherwise game
	 * is over reset everything
	 * 
	 * @param num (corresponding button number)
	 */
	public void onButtonPress(int num) {
		playButtonSound(num); // play button sound on click
		
		// add user choice
		userPick.add(num);
		
		// check user choice
		if (check()) {
			if (userPick.size() == pattern.getPattern().size()) {
				score += 1;
				scoreLabel.setText("SCORE: " + score);
				pattern.addNext();
				userPick.clear();
				startAnimation();
			}
		} else {
			// play gameover sound
			sounds.playGameover();

			// reset user choices and pattern
			userPick.clear();
			pattern.getPattern().clear();
			
			// ask to play again
			int result = JOptionPane.showConfirmDialog(frame,
					"GAME OVER\nSCORE: " + score + "\n Would you like to play again?");
			if (result == JOptionPane.YES_OPTION) {
				score = 0;
				scoreLabel.setText("SCORE: " + score);
				pattern.addNext();
				startAnimation();
			} else {
				System.exit(0);
			}
		}
	}

	/**
	 * check if current click is correct in the pattern
	 * 
	 * @return true if click was correct, false otherwise
	 */
	public boolean check() {
		return userPick.get(userPick.size() - 1) == pattern.getPattern().get(userPick.size() - 1);
	}

	/**
	 * Runs Simon animation
	 */
	public void startAnimation() {
		new Thread(new Runnable() {
			public void run() {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				pattern.animate();
			}
		}).start();
	}

	/**
	 * ActionListener specifically for start button switches panels and starts
	 * animation
	 * 
	 * @author kkcoo
	 *
	 */
	public class StartButtonListener implements ActionListener {

		private StartButtonListener() {
		};

		public void actionPerformed(ActionEvent e) {
			setActivePanel(playPanel);
			startAnimation();
		}
	}
}
