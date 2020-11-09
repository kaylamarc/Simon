import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SimonGUI {

	private static final int WIDTH = 800;
	private static final int HEIGHT = 800;
	private static final Color BACKGROUNDCOLOR = Color.GRAY;

	// frame for game
	public static JFrame frame;
	public static JPanel currentPanel;

	// elements for starting screen
	public JPanel startPanel;
	public JButton startButton;

	// element for play screen
	public static JPanel playPanel;
	public JButton redButton;
	public JButton yellowButton;
	public JButton blueButton;
	public JButton greenButton;

	// create GUI for game
	public SimonGUI() {
		createFrame();
		createStartButton();
		createStartPanel();
		currentPanel = startPanel;
		createPlayButtons();
		createPlayPanel();
	}

	// set up the frame
	public void createFrame() {
		frame = new JFrame("Simon");
		frame.setSize(WIDTH, HEIGHT);
		frame.getContentPane().setBackground(BACKGROUNDCOLOR);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		frame.setVisible(true);
	}

	// create the start panel
	public void createStartPanel() {
		startPanel = new JPanel();
		startPanel.setSize(WIDTH, HEIGHT);
		startPanel.setBackground(BACKGROUNDCOLOR);
		startPanel.setLayout(null);
		startPanel.add(startButton);
		startPanel.setVisible(true);
		frame.add(startPanel);
	}

	// create a start button
	public void createStartButton() {
		startButton = new JButton("START");
		startButton.setBackground(Color.GREEN);
		startButton.setForeground(Color.WHITE);
		startButton.setFocusPainted(false);
		startButton.setBounds(100, 300, 600, 200);
		startButton.addActionListener(new StartButtonListener());
	}

	// creates play panel
	public void createPlayPanel() {
		playPanel = new JPanel();
		playPanel.setSize(WIDTH, HEIGHT);
		playPanel.setBackground(BACKGROUNDCOLOR);

		GridLayout playLayout = new GridLayout(2, 2);

		playPanel.setLayout(playLayout);

		// add buttons to panel
		playPanel.add(redButton);
		playPanel.add(yellowButton);
		playPanel.add(greenButton);
		playPanel.add(blueButton);

		playPanel.setVisible(false);
		
		frame.add(playPanel);

	}

	// create all play buttons
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

	}

	// set active panel to show on the main frame
	public static void setActivePanel(JPanel panel) {
		currentPanel.setVisible(false);
		panel.setVisible(true);
		currentPanel = panel;
	}

	public static class StartButtonListener implements ActionListener {

		private StartButtonListener() {
		};

		public void actionPerformed(ActionEvent e) {
			setActivePanel(playPanel);
		}
	}

}
