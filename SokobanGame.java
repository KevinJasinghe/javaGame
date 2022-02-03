//Name: Kevin Jasinghe
//Date: Jan 1 2022
//Purpose: Grid Game

import java.awt.*;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
public class SokobanGame extends JPanel implements ActionListener {
	Panel p_card; // to hold all of the screens
	Panel card1, card2, card22, card3, card4, card5, card6, card7;;
	CardLayout cdLayout = new CardLayout();

	// grid
	int row = 8;
	int col = 8;

	int level = 0;
	//Character coordinates
	int x = 7;
	int y = 2;
	JLabel a[] = new JLabel[row * col];

	int tile[] = new int[64];
	int tileOldX[] = new int[64];
	int tileNewX[] = new int[64];
	int tileOldY[] = new int[64];
	int tileNewY[] = new int[64];

	//Teleporter Variables
	int xt1 = 0;
	int yt1 = 0;

	int xt2 = 0;
	int yt2 = 0;

	//Variables to lose health
	int percentLost = 0;
	int spikeCheck = ' ';

	JTextArea TA;

	//Level 1 layout
	int level1Layout[][] = { { 1, 1, 1, 1, 1, 1, 1, 1 },
			{ 1, 1, 1, 1, 1, 1, 50, 1 },
			{ 2, 2, 1, 1, 1, 1, 1, 1 },
			{ 50, 2, 1, 1, 1, 1, 1, 1 },
			{ 1, 2, 1, 1, 1, 50, 1, 1 },
			{ 1, 2, 1, 1, 1, 1, 1, 1 },
			{ 1, 2, 1, 1, 1, 1, 1, 1 },
			{ 1, 2, 1, 1, 1, 1, 1, 50 } };

	//Level 2 layout
	int level2Layout[][] = { { 1, 1, 1, 1, 1, 1, 1, 1 },
			{ 1, 1, 1, 1, 1, 1, 1, 1 },
			{ 1, 1, 1, 1, 1, 1, 1, 1 },
			{ 1, 1, 1, 1, 2, 2, 2, 1 },
			{ 1, 1, 1, 1, 2, 50, 2, 1 },
			{ 1, 1, 1, 1, 2, 2, 2, 1 },
			{ 2, 2, 2, 1, 1, 1, 1, 1 },
			{ 2, 50, 2, 1, 50, 1, 1, 1 } };

	//Level 3 layout
	int level3Layout[][] = { { 1, 2, 1, 50, 1, 2, 1, 1 },
			{ 1, 2, 1, 1, 1, 2, 50, 1 },
			{ 1, 2, 1, 1, 1, 2, 1, 1 },
			{ 1, 2, 1, 1, 1, 2, 1, 1 },
			{ 1, 2, 1, 50, 1, 2, 1, 1 },
			{ 1, 2, 1, 1, 1, 2, 1, 1 },
			{ 2, 2, 2, 2, 2, 2, 1, 1 },
			{ 1, 2, 1, 50, 1, 2, 1, 1 } };

	//Level 4 layout
	int level4Layout[][] = { { 1, 1, 8, 1, 50, 8, 8, 8 },
			{ 1, 1, 1, 1, 1, 8, 50, 8 },
			{ 1, 1, 1, 1, 1, 8, 8, 8 },
			{ 1, 1, 1, 1, 1, 1, 8, 1 },
			{ 1, 1, 1, 1, 1, 8, 1, 1 },
			{ 1, 1, 1, 1, 8, 1, 1, 50 },
			{ 1, 50, 1, 8, 50, 1, 1, 1 },
			{ 1, 1, 1, 8, 1 ,1, 1, 1 } };

	//Level 5 layout
	int level5Layout[][] = { { 1, 1, 2, 1, 8, 8, 8, 1 },
			{ 1, 1, 2, 1, 8, 50, 8, 1 },
			{ 1, 1, 2, 1, 8, 8, 8, 1 },
			{ 50, 8, 2, 1, 1, 1, 1, 1 },
			{ 1, 1, 2, 1, 1, 8, 1, 1 },
			{ 1, 1, 2, 50, 1, 1, 2, 2 },
			{ 8, 8, 2, 1, 1, 8, 2, 50 },
			{ 1, 1, 1, 1, 10, 1, 2, 1 } };

	//Level 6 layout
	int level6Layout[][] = { { 1, 8, 1, 2, 1, 1, 1, 8 },
			{ 1, 8, 8, 2, 1, 1, 1, 1 },
			{ 1, 1, 8, 2, 1, 50, 1, 1 },
			{ 1, 1, 1, 2, 10, 1, 1, 1 },
			{ 8, 50, 1, 1, 2, 2, 2, 2},
			{ 1, 1, 8, 1, 1, 8, 8, 8 },
			{ 2, 2, 8, 1, 50, 1, 1, 8 },
			{ 2, 50, 8, 1, 1, 8, 8, 8 } };

	//Level 7 layout
	int level7Layout[][] = { { 1, 2, 1, 1, 1, 50, 1, 1 },
			{ 1, 1, 2, 1, 1, 1, 1, 1 },
			{ 1, 1, 2, 1, 8, 8, 8, 8 },
			{ 50, 1, 2, 1, 8, 50, 50, 50 },
			{ 1, 25, 2, 1, 8, 1, 26, 1 },
			{ 1, 1, 2, 1, 8, 8, 8, 8 },
			{ 1, 1, 1, 1, 1, 1, 1, 1 },
			{ 50, 1, 1, 1, 50, 1, 1, 50 } };

	//Level 8 layout
	int level8Layout[][] = { { 1, 1, 8, 25, 8, 1, 1, 50 },
			{ 1, 1, 1, 8, 1, 1, 1, 1 },
			{ 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 1, 50, 1, 1, 1, 1, 1, 50 },
			{ 1, 1, 1, 2, 2, 2, 1, 1 },
			{ 10, 1, 1, 2, 50, 2, 2, 2 },
			{ 1, 50, 1, 2, 26, 2, 2, 50 },
			{ 1, 1, 1, 2, 2, 2, 1, 1 } };

	//Level 9 layout
	int level9Layout[][] = { { 1, 1, 10, 1, 8, 1, 1, 1 },
			{ 1, 1, 1, 1, 8, 1, 1, 1 },
			{ 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 1, 8, 1, 1, 8, 1, 1, 1 },
			{ 50, 8, 1, 10, 8, 1, 1, 1 },
			{ 8, 8, 1, 1, 8, 1, 8, 1 },
			{ 1, 8, 1, 1, 8, 1, 8, 50 },
			{ 50, 8, 1, 1, 8, 50, 8, 1 } };

	//Level 10 layout
	int level10Layout[][] = { { 1, 8, 50, 1, 8, 8, 8, 8 },
			{ 8, 8, 1, 1, 1, 1, 1, 1 },
			{ 10, 1, 1, 1, 1, 1, 1, 8 },
			{ 8, 8, 2, 2, 1, 50, 1, 8 },
			{ 1, 1, 1, 2, 2, 1, 1, 1 },
			{ 1, 1, 1, 1, 2, 8, 8, 8 },
			{ 50, 1, 1, 1, 2, 2, 26, 50 },
			{ 1, 25, 1, 1, 50, 2, 2, 1 } };

	//This is the layout the game uses. All other layouts copy into this one
	int layout[][] = { { 1, 1, 1, 1, 1, 1, 1, 1 },
			{ 1, 1, 1, 1, 1, 1, 1, 1 },
			{ 1, 1, 1, 1, 1, 1, 1, 1 },
			{ 1, 1, 1, 1, 1, 1, 1, 1 },
			{ 1, 1, 1, 1, 1, 1, 1, 1 },
			{ 1, 1, 1, 1, 1, 1, 1, 1 },
			{ 1, 1, 1, 1, 1, 1, 1, 1 },
			{ 1, 1, 1, 1, 1, 1, 1, 1 } };

	//Global variable for rotations
	int temp = 1;

	//Variable to display character
	int sansDisplay = 0;

	//Coins variables
	int totalCoins = 0;
	JLabel coinsCount = new JLabel();
	JLabel levelDisplay = new JLabel();

	//Progress Bar
	JProgressBar progress;
	int i = 100;
	
	JButton rightR;
	JButton leftR;

	//Radio Buttons
	JRadioButton minsec;
	JRadioButton minsec2;
	JRadioButton minsec3;
	JRadioButton minsec4;
	JRadioButton minsec5;

	//Feedback variables
	int rating = 0;
	String userInput = "No errors reported";


	//Variable to show different lose messages 
	JLabel loseText;

	//Sound variable
	Clip clip;

	public SokobanGame() {
		p_card = new Panel();
		p_card.setLayout(cdLayout);
		screen1();
		screen2();
		screen22();
		screen3();
		screen4();
		screen5();
		screen6();
		screen7();
		setLayout(new BorderLayout());
		add("Center", p_card);
		levels();
	}

	public void screen1() { // screen 1 is set up.
		card1 = new Panel();
		card1.setBackground(Color.black);
		JLabel title = new JLabel(createImageIcon("titleFont2.png"));
		JLabel title2 = new JLabel(createImageIcon("titleFont.png"));
		JLabel image = new JLabel(createImageIcon("splash.png"));
		JLabel filler = new JLabel("zzzzzzzzzzzzzzzzzz");
		JLabel filler2 = new JLabel("zzzzzzz");
		JLabel filler3 = new JLabel("zzzzzzzzzzzzzzzzzzzzzzzzzzz");
		JLabel filler4 = new JLabel("zzzzzzzzzzzzzzzzzzzzzzzzzzzz");
		JLabel tag = new JLabel(createImageIcon("tag.jpeg"));
		JLabel tag2 = new JLabel(createImageIcon("tag2.png"));
		JLabel develop = new JLabel(createImageIcon("developer.jpeg"));
		JLabel tag3 = new JLabel(createImageIcon("tag3.png"));

		JButton next = new JButton("Click to Play");
		next.setPreferredSize (new Dimension (150, 32));
		next.setFont (new Font ("DPcomic", Font.BOLD, 20));

		next.setActionCommand("s2");
		next.addActionListener(this);
		next.setForeground(new Color(0, 125, 255));
		card1.add(title);
		card1.add(title2);
		card1.add(image);
		card1.add(filler);
		card1.add(tag2);
		card1.add(develop);
		card1.add(filler2);
		card1.add(tag);
		card1.add(filler3);
		card1.add(tag3);
		card1.add(filler4);
		card1.add(next);
		p_card.add("1", card1);
	}

	public void screen2() { // screen 2 is set up.
		card2 = new Panel();
		card2.setBackground(Color.black);
		JButton next = new JButton(createImageIcon ("instrucScreen.jpg"));
		next.setActionCommand("s22");
		next.addActionListener(this);
		next.setBorderPainted(false);

		card2.add(next);
		p_card.add("2", card2);
	}

	public void screen22() { //second part of instructions is set up
		card22 = new Panel();
		card22.setBackground(Color.black);
		JButton next = new JButton(createImageIcon ("instrucScreen2.jpg"));
		next.setActionCommand("s3");
		next.addActionListener(this);
		next.setBorderPainted(false);

		card22.add(next);
		p_card.add("22", card22);
	}

	public void screen3() { // screen 3 is set up.
		card3 = new Panel();
		card3.setBackground(Color.black);
		JButton instruc = new JButton("Instructions");
		instruc.setActionCommand("s2");
		instruc.setFont (new Font ("DPcomic", Font.BOLD, 15));
		instruc.addActionListener(this);
		instruc.setForeground(Color.black);levelDisplay.setText("Level X");
		levelDisplay.setForeground(Color.white);
		levelDisplay.setFont (new Font ("DPcomic", Font.BOLD, 30));
		JButton next = new JButton("Next");
		JLabel filler5 = new JLabel (" ");
		filler5.setPreferredSize(new Dimension (30, 20));
		JLabel filler6 = new JLabel (" ");
		filler6.setPreferredSize(new Dimension (50, 20));
		coinsCount = new JLabel("# Of Coins Remaining: " + totalCoins);
		coinsCount.setFont (new Font ("DPcomic", Font.BOLD, 15));
		coinsCount.setForeground(new Color (0, 125, 255));
		next.setActionCommand("s4");
		next.addActionListener(this);
		JButton resetButton = new JButton ("Reset");
		resetButton.setActionCommand("reset");
		resetButton.addActionListener(this);
		resetButton.setPreferredSize(new Dimension (45, 45));
		progress = new JProgressBar (0, 0, 100);
		progress.setValue (100);
		progress.setForeground(Color.red);
		progress.setStringPainted (false);
		progress.setPreferredSize(new Dimension (200, 100));



		// Set up grid
		Panel p = new Panel(new GridLayout(row, col));
		int move = 0;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				a[move] = new JLabel(createImageIcon("p" + layout[i][j] + ".png"));
				// change to be your size
				a[move].setPreferredSize(new Dimension(62, 62));
				p.add(a[move]);
				move++;
			}
		}

		a[x * col + y].setIcon(createImageIcon("p" + layout[x][y] + ".png"));
		a[x * col + y].setIcon(createImageIcon("p100.png"));

		JButton up = new JButton("Up");
		up.setActionCommand("up");
		up.addActionListener(this);
		up.setForeground(new Color (0, 125, 255));
		up.setFont (new Font ("DPcomic", Font.BOLD, 18));
		JButton down = new JButton("Down");
		down.setActionCommand("down");
		down.addActionListener(this);
		down.setForeground(new Color (0, 125, 255));
		down.setFont (new Font ("DPcomic", Font.BOLD, 18));
		JButton right = new JButton("Right");
		right.setActionCommand("right");
		right.addActionListener(this);
		right.setForeground(new Color (0, 125, 255));
		right.setFont (new Font ("DPcomic", Font.BOLD, 18));
		JButton left = new JButton("Left");
		left.setActionCommand("left");
		left.addActionListener(this);
		left.setForeground(new Color (0, 125, 255));
		left.setFont (new Font ("DPcomic", Font.BOLD, 18));
		rightR = new JButton("Rotate Right");
		rightR.setActionCommand("rightR");
		rightR.addActionListener(this);
		rightR.setForeground(new Color (255, 0, 0));
		rightR.setFont (new Font ("DPcomic", Font.BOLD, 18));
		leftR = new JButton("Rotate left");
		leftR.setActionCommand("leftR");
		leftR.addActionListener(this);
		leftR.setForeground(new Color (255, 0, 0));
		leftR.setFont (new Font ("DPcomic", Font.BOLD, 18));

		card3.add(instruc);
		card3.add(filler5);
		card3.add(levelDisplay);
		card3.add(filler6);
		card3.add(coinsCount);
		card3.add(p);

		Panel dir = new Panel(new GridLayout(2, 3));


		dir.add(left);
		dir.add(up);
		dir.add(right);
		dir.add(leftR);
		dir.add(down);
		dir.add(rightR);

		card3.add(dir);
		card3.add(resetButton);
		card3.add(progress);

		p_card.add("3", card3);
	}

	public void screen4() { // screen 4 is set up.
		card4 = new Panel();
		card4.setBackground(Color.black);
		JLabel title = new JLabel("Congratulations! You have completed the game");
		title.setFont(new Font ("DPcomic", Font.BOLD, 24));
		title.setForeground(new Color(6, 222, 179));
		JLabel image = new JLabel(createImageIcon("endImage.png"));
		JLabel text = new JLabel ("Sans has sucessfully escaped the dungeon.");
		text.setFont(new Font ("DPcomic", Font.BOLD, 20));
		text.setForeground(Color.white);
		text.setPreferredSize(new Dimension (400, 150));




		JLabel filler = new JLabel (" ");
		filler.setPreferredSize(new Dimension (100,100));
		JLabel filler2= new JLabel (" ");
		filler2.setPreferredSize(new Dimension (50,50));

		JButton playAgain = new JButton("Play Again?");
		playAgain.setActionCommand("s7");
		playAgain.addActionListener(this);
		playAgain.setForeground(new Color(6, 222, 179));
		playAgain.setFont(new Font ("DPcomic", Font.BOLD, 18));

		JButton feedback = new JButton("Feedback");
		feedback.setActionCommand("s6");
		feedback.addActionListener(this);
		feedback.setForeground(new Color(6, 222, 179));
		feedback.setFont(new Font ("DPcomic", Font.BOLD, 18));

		JButton quit = new JButton("Quit");
		quit.setActionCommand("q");
		quit.addActionListener(this);
		quit.setForeground(new Color(6, 222, 179));
		quit.setFont(new Font ("DPcomic", Font.BOLD, 18));

		card4.add(title);
		card4.add(image);
		card4.add(filler);
		card4.add(filler2);
		card4.add(text);
		card4.add(playAgain);
		card4.add(feedback);
		card4.add(quit);
		p_card.add("4", card4);
	}

	public void screen5() { // screen 5 is set up.
		card5 = new Panel();
		card5.setBackground(Color.black);



		JLabel title = new JLabel("Game Over");
		title.setForeground(Color.red);
		title.setFont (new Font ("DPcomic", Font.BOLD, 60));
		JLabel filler = new JLabel (" ");
		filler.setPreferredSize(new Dimension (100, 100));
		JLabel filler2 = new JLabel (" ");
		filler2.setPreferredSize(new Dimension (100, 100));
		JLabel filler3 = new JLabel (" ");
		filler3.setPreferredSize(new Dimension (100, 100));
		JLabel filler4 = new JLabel (" ");
		filler4.setPreferredSize(new Dimension (100, 100));

		loseText = new JLabel (" ");
		loseText.setForeground(new Color(6, 222, 179));
		loseText.setFont (new Font ("DPcomic", Font.BOLD, 40));


		JButton next = new JButton("Play Again?");
		next.setActionCommand("s1");
		next.addActionListener(this);
		JButton end = new JButton("Quit?");
		end.setActionCommand("q");
		end.addActionListener(this);
		card5.add(filler);
		card5.add(title);
		card5.add(filler2);
		card5.add(filler3);
		card5.add(next);
		card5.add(end);
		card5.add(filler4);
		card5.add(loseText);
		p_card.add("5", card5);
	}

	//Sets up screen 6
	public void screen6() {
		card6 = new Panel();
		card6.setBackground(Color.black);

		JLabel title = new JLabel("Feedback");
		title.setForeground(Color.red);
		title.setFont (new Font ("DPcomic", Font.BOLD, 60));

		JLabel filler = new JLabel (" ");
		filler.setPreferredSize(new Dimension (100, 100));
		JLabel filler2 = new JLabel (" ");
		filler2.setPreferredSize(new Dimension (100, 100));
		JLabel filler3 = new JLabel (" ");
		filler3.setPreferredSize(new Dimension (50, 100));


		JLabel text = new JLabel ("How would you rate the game?");
		text.setFont(new Font ("DPcomic", Font.BOLD, 24));
		text.setForeground(Color.white);


		minsec = new JRadioButton ("1 Star");
		minsec.setForeground(Color.white);
		minsec.setFont(new Font ("DPcomic", Font.BOLD, 18));
		minsec.addActionListener (this);
		minsec.setActionCommand ("minsec");
		minsec2 = new JRadioButton ("2 Star");
		minsec2.setForeground(Color.white);
		minsec2.setFont(new Font ("DPcomic", Font.BOLD, 18));
		minsec2.addActionListener (this);
		minsec2.setActionCommand ("minsec2");
		minsec3 = new JRadioButton ("3 Star");
		minsec3.setForeground(Color.white);
		minsec3.setFont(new Font ("DPcomic", Font.BOLD, 18));
		minsec3.addActionListener (this);
		minsec3.setActionCommand ("minsec3");
		minsec4 = new JRadioButton ("4 Star");
		minsec4.setForeground(Color.white);
		minsec4.setFont(new Font ("DPcomic", Font.BOLD, 18));
		minsec4.addActionListener (this);
		minsec4.setActionCommand ("minsec4");
		minsec5 = new JRadioButton ("5 Star");
		minsec5.setForeground(Color.white);
		minsec5.setFont(new Font ("DPcomic", Font.BOLD, 18));
		minsec5.addActionListener (this);
		minsec5.setActionCommand ("minsec5");
		ButtonGroup group = new ButtonGroup ();
		group.add(minsec);
		group.add (minsec2);
		group.add (minsec3);
		group.add (minsec4);
		group.add (minsec5);


		Panel dir = new Panel(new GridLayout(5, 1));
		dir.add(minsec);
		dir.add(minsec2);
		dir.add(minsec3);
		dir.add(minsec4);
		dir.add(minsec5);

		JLabel text2 = new JLabel ("Are there any errors you would like to report?");
		text2.setForeground(Color.white);
		text2.setFont(new Font ("DPcomic", Font.BOLD, 24));
		TA = new JTextArea (" ", 5, 40);;
		System.out.println("User Input");

		JButton playAgain = new JButton("Play Again?");
		playAgain.setActionCommand("s7");
		playAgain.addActionListener(this);
		playAgain.setFont(new Font ("DPcomic", Font.BOLD, 18));
		playAgain.setForeground(Color.blue);

		JButton submit = new JButton("Submit");
		submit.setActionCommand("submit");
		submit.addActionListener(this);
		submit.setFont(new Font ("DPcomic", Font.BOLD, 18));
		submit.setForeground(Color.blue);
		
		JButton quit = new JButton("Quit");
		quit.setActionCommand("q");
		quit.addActionListener(this);
		quit.setForeground(Color.blue);
		quit.setFont(new Font ("DPcomic", Font.BOLD, 18));

		card6.add(filler); 
		card6.add(title);
		card6.add(filler2);
		card6.add(text);
		card6.add(dir);
		card6.add(text2);
		card6.add (TA);
		card6.add(filler3);
		card6.add(playAgain);
		card6.add(submit);
		card6.add(quit);
		p_card.add("6", card6);
	}

	//Sets up screen 7
	public void screen7() {
		card7 = new Panel();
		card7.setBackground(Color.black);

		JLabel filler = new JLabel (" ");
		filler.setPreferredSize(new Dimension (100, 100));
		JLabel title = new JLabel("Play Again");
		title.setForeground(Color.red);
		title.setFont (new Font ("DPcomic", Font.BOLD, 60));

		JLabel filler2 = new JLabel (" ");
		filler2.setPreferredSize(new Dimension (100, 100));

		JMenuBar menuBar = new JMenuBar ();
		JMenu menu;
		JMenuItem menuItem;
		menu = new JMenu ("Choose Level");
		menuBar.add (menu);
		menuItem = new JMenuItem ("Level One");
		menuItem.addActionListener (this);
		menuItem.setActionCommand ("L1");
		menu.add (menuItem);
		menuItem = new JMenuItem ("Level Two");
		menuItem.addActionListener (this);
		menuItem.setActionCommand ("L2");
		menu.add (menuItem);
		menuItem = new JMenuItem ("Level Three");
		menuItem.addActionListener (this);
		menuItem.setActionCommand ("L3");
		menu.add (menuItem);
		menuItem = new JMenuItem ("Level Four");
		menuItem.addActionListener (this);
		menuItem.setActionCommand ("L4");
		menu.add (menuItem);
		menuItem = new JMenuItem ("Level Five");
		menuItem.addActionListener (this);
		menuItem.setActionCommand ("L5");
		menu.add (menuItem);
		menuItem = new JMenuItem ("Level Six");
		menuItem.addActionListener (this);
		menuItem.setActionCommand ("L6");
		menu.add (menuItem);
		menuItem = new JMenuItem ("Level Seven");
		menuItem.addActionListener (this);
		menuItem.setActionCommand ("L7");
		menu.add (menuItem);
		menuItem = new JMenuItem ("Level Eight");
		menuItem.addActionListener (this);
		menuItem.setActionCommand ("L8");
		menu.add (menuItem);
		menuItem = new JMenuItem ("Level Nine");
		menuItem.addActionListener (this);
		menuItem.setActionCommand ("L9");
		menu.add (menuItem); 
		menuItem = new JMenuItem ("Level Ten");
		menuItem.addActionListener (this);
		menuItem.setActionCommand ("L10");
		menu.add (menuItem);

		card7.add(filler);
		card7.add(title);
		card7.add(filler2);
		card7.add("North", menuBar);
		p_card.add("7", card7);

	}



	protected static ImageIcon createImageIcon(String path) { // change the red to your class name
		java.net.URL imgURL = SokobanGame.class.getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}

	//Method to redraw the board
	public void redraw() {

		int move = 0;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				a[move].setIcon(createImageIcon("p" + layout[i][j] + ".png"));
				move++;
			}
		}
		a[x * col + y].setIcon(createImageIcon("p10"+sansDisplay+".png"));

	}

	//Method to move up
	public void moveUp() {
		if (layout[x - 1][y] == 50) 
			addPoints("up");
		else if (layout[x - 1][y] == 10)
			addHealth("up");
		if (x - 1 < 0)
			JOptionPane.showMessageDialog(null, "You cannot go further up", "Alert Message", JOptionPane.ERROR_MESSAGE);
		else if (layout[x - 1][y] == 2)
			JOptionPane.showMessageDialog(null, "There is a wall", "Alert Message", JOptionPane.ERROR_MESSAGE);
		else if (layout[x-1][y] == 8)
			loseHealth();	
		else if (layout[x - 1][y] == 25) {
			sansDisplay = 1;
			teleport();
		}else if (layout[x - 1][y] == 26) {
			sansDisplay = 1;
			teleport2();
		}
		else {
			sansDisplay = 1;
			a[x * col + y].setIcon(createImageIcon("p1.png"));
			a[(x - 1) * col + y].setIcon(createImageIcon("p10"+sansDisplay+".png"));
			x--;
			redraw();
		}

	}

	//Method to move down
	public void moveDown() {
		//Adds points if player touches coin
		if (layout[x + 1][y] == 50) 
			addPoints("down");
		else if (layout[x + 1][y] == 10)
			addHealth("down");
		//Fall guard
		if (x + 1 >= row)
			JOptionPane.showMessageDialog(null, "You cannot go further down", "Alert Message",
					JOptionPane.ERROR_MESSAGE);
		//Prevents player from walking on wall
		else if (layout[x + 1][y] == 2)
			JOptionPane.showMessageDialog(null, "There is a wall", "Alert Message", JOptionPane.ERROR_MESSAGE);
		//Player loses health if they hit a spike
		else if (layout[x+1][y] == 8)
			loseHealth();	
		//Teleports player
		else if (layout[x + 1][y] == 25) {
			sansDisplay = 0;
			teleport();
			//Teleports player
		}else if (layout[x + 1][y] == 26) {
			sansDisplay = 0;
			teleport2();
			//Moves the player
		}else {
			sansDisplay = 0;
			a[x * col + y].setIcon(createImageIcon("p1.png"));
			a[(x + 1) * col + y].setIcon(createImageIcon("p10"+sansDisplay+".png"));
			x++;
			redraw();
		}
	}

	//Method to move left
	public void moveLeft() {
		{
			if (layout[x][y-1] == 50)
				addPoints("left");
			else if (layout[x][y-1] == 10)
				addHealth("left");
			if (y - 1 < 0)
				JOptionPane.showMessageDialog(null, "You cannot go further left", "Alert Message",
						JOptionPane.ERROR_MESSAGE);
			else if (layout[x][y - 1] == 2)
				JOptionPane.showMessageDialog(null, "There is a wall", "Alert Message", JOptionPane.ERROR_MESSAGE);
			else if (layout[x][y - 1] == 8)
				loseHealth();
			else if (layout[x][y-1] == 25) {
				sansDisplay = 3;
				teleport();
			}else if (layout[x][y-1] == 26) {
				sansDisplay = 3;
				teleport2();
			}else {
				sansDisplay = 3;
				a[x * col + y].setIcon(createImageIcon("p1.png"));
				a[x * col + (y - 1)].setIcon(createImageIcon("p10"+sansDisplay+".png"));
				y--;
				redraw();
			}
		}
	}

	//Method to mvoe right
	public void moveRight() {
		if (layout[x][y+1] == 50)
			addPoints("right");
		else if (layout[x][y+1] == 10)
			addHealth("right");
		if (y + 1 >= col)
			JOptionPane.showMessageDialog(null, "You cannot go further right", "Alert Message",
					JOptionPane.ERROR_MESSAGE);
		else if (layout[x][y + 1] == 2)
			JOptionPane.showMessageDialog(null, "There is a wall", "Alert Message", JOptionPane.ERROR_MESSAGE);
		else if (layout[x][y + 1] == 8)
			loseHealth();
		else if (layout[x][y+1] == 25) {
			sansDisplay = 2;
			teleport();
		}else if (layout[x][y+1] == 26) {
			sansDisplay = 2;
			teleport2();
		}else {
			sansDisplay = 2;
			a[x * col + y].setIcon(createImageIcon("p1.png"));
			a[x * col + (y + 1)].setIcon(createImageIcon("p10"+sansDisplay+".png"));
			y++;
			redraw();
		}
	}


	public void actionPerformed(ActionEvent e) { // moves between the screens

		//Takes player to splash screen and resets the game
		if (e.getActionCommand().equals("s1"))
			cdLayout.show(p_card, "1");
		//Takes player to instruction screen
		else if (e.getActionCommand().equals("s2"))
			cdLayout.show(p_card, "2");
		else if (e.getActionCommand().equals("s22"))
			cdLayout.show(p_card, "22");
		//Takes the player to game screen
		else if (e.getActionCommand().equals("s3")) {
			cdLayout.show(p_card, "3");
			Music("hades.wav");
			//Takes the player to win screen
		}	else if (e.getActionCommand().equals("s4"))
			cdLayout.show(p_card, "4");
		else if (e.getActionCommand().equals("s5"))
			cdLayout.show(p_card, "5");
		//Shows screen 6
		else if (e.getActionCommand().equals("s6"))
			cdLayout.show(p_card, "6");	
		//Shows screen 7
		else if (e.getActionCommand().equals("s7"))
			cdLayout.show(p_card, "7");
		//Shows a prompt when "quit" is clicked to confirm 
		else if (e.getActionCommand().equals("q"))
			quit();
		//Shows the feedback that the user submitted
		else if (e.getActionCommand().equals("submit"))
			showFeedback();
		//Changes the rating based on radio button user selected
		else if (e.getActionCommand().equals("minsec"))
			rating = 1;
		//Changes the rating based on radio button user selected
		else if (e.getActionCommand().equals("minsec2"))
			rating = 2;
		//Changes the rating based on radio button user selected
		else if (e.getActionCommand().equals("minsec3"))
			rating = 3;
		//Changes the rating based on radio button user selected
		else if (e.getActionCommand().equals("minsec4"))
			rating = 4;
		//Changes the rating based on radio button user selected
		else if (e.getActionCommand().equals("minsec5"))
			rating = 5;
		//Resets the level 
		else if (e.getActionCommand().equals("reset"))
			resetLevel();
		//Moves the player up 
		else if (e.getActionCommand().equals("up"))
			moveUp();
		//Moves player down
		else if (e.getActionCommand().equals("down"))
			moveDown();
		//Moves player left
		else if (e.getActionCommand().equals("left"))
			moveLeft();
		//Moves player right
		else if (e.getActionCommand().equals("right"))
			moveRight();
		//Rotates the grid clockwise
		else if (e.getActionCommand().equals("rightR"))
			rotateRight();
		//Rotates the grid counter-clockwise
		else if (e.getActionCommand().equals("leftR"))
			rotateLeft();
		//Changes the level based on the option that the user chose
		else if (e.getActionCommand().equals("L1"))
			playAgain(1);
		//Changes the level based on the option that the user chose
		else if (e.getActionCommand().equals("L2"))
			playAgain(2);
		//Changes the level based on the option that the user chose
		else if (e.getActionCommand().equals("L3"))
			playAgain(3);
		//Changes the level based on the option that the user chose
		else if (e.getActionCommand().equals("L4"))
			playAgain(4);
		//Changes the level based on the option that the user chose
		else if (e.getActionCommand().equals("L5"))
			playAgain(5);
		//Changes the level based on the option that the user chose
		else if (e.getActionCommand().equals("L6"))
			playAgain(6);
		//Changes the level based on the option that the user chose
		else if (e.getActionCommand().equals("L7"))
			playAgain(7);
		//Changes the level based on the option that the user chose
		else if (e.getActionCommand().equals("L8"))
			playAgain(8);
		//Changes the level based on the option that the user chose
		else if (e.getActionCommand().equals("L9"))
			playAgain(9);
		else if (e.getActionCommand().equals("L10"))
			playAgain(10);
	}


	public void rotateLeft() {
		// Runs through every number in the layout array
		// and rotates it. The layout is then updated with the new values
		int z[][] = new int [row][col];
		for (int j = 0; j < row; j++) {
			for (int k = 0; k < col; k++) {
				z[j][k] = layout[k][row-j-1]; 

			}
		}
		for (int j = 0; j < row; j++) {
			for (int k = 0; k < col; k++) {
				layout[j][k] = z[j][k];
			}
		}
		rotateLeftCheck();
	}

	public void rotateLeftCheck() {
		//If the player rotated and there is a wall that will overlap, shows an error message
		if (layout[x][y] == 2 && temp == 1) {
			rotateRight();
			JOptionPane.showMessageDialog(null, "Overlap", "Alert Message", JOptionPane.ERROR_MESSAGE);
			temp = 0;
			//Else if spikes overlap the player, they lose health
		} else if (layout[x][y] == 8 && temp == 1) {
			loseHealth();
			rotateRight();
			JOptionPane.showMessageDialog (null, createImageIcon ("sansHurt.png"), "Alert Message (Spikes Overlapped)", 
					JOptionPane.INFORMATION_MESSAGE);
			temp = 0;
		}
		//If none of the above conditions are met, the method allows the rotate
		else {
			redraw();
			temp =1;
			newObjectLocation();
		}
		//If the player lands on a teleport, they get teleported
		if (layout[x][y] == 25 || layout[x][y] == 26)
			teleport3();
		else if (layout[x][y] == 50)
			addPoints("rotate");
		else if (layout[x][y] == 10)
			addHealth("rotate");
		temp = 1;

	}

	public void rotateRight() {
		// Runs through every number in the layout array. Checks to see if if there is
		// an image that needs
		// Rotating. Calculates the new x and y cordinates and rotates.
		int z[][] = new int [row][col];


		for (int j = 0; j < row; j++) {
			for (int k = 0; k < col; k++) {

				z[j][k] = layout[col-k-1][j]; 

			}

		}

		for (int j = 0; j < row; j++) {
			for (int k = 0; k < col; k++) {
				layout[j][k] = z[j][k];
			}
		}
		rotateRightCheck();
	}

	public void rotateRightCheck() {
		//If the layout overlaps with the player it reverses the rotate and shows an error
		if (layout[x][y] == 2 && temp == 1) {
			rotateLeft();
			JOptionPane.showMessageDialog(null, "Overlap", "Alert Message", JOptionPane.ERROR_MESSAGE);
			temp = 0;
		}
		else if (layout[x][y] == 8 && temp == 1) {
			loseHealth();
			rotateLeft();
			JOptionPane.showMessageDialog (null, createImageIcon ("sansHurt.png"), "Alert Message (Spikes Overlapped)", 
					JOptionPane.INFORMATION_MESSAGE);
			temp = 0;
		}
		//If the layout does not overlap then it allows the rotate
		else {
			redraw();
			temp = 1;
			newObjectLocation();
		}
		if (layout[x][y] == 25 || layout[x][y] == 26)
			teleport3();
		else if (layout[x][y] == 50)
			addPoints("rotate");
		else if (layout[x][y] == 10)
			addHealth("rotate");
		temp = 1;
	}


	//Teleports the player
	public void teleport(){
		a[x * col + y].setIcon(createImageIcon("p1.png"));
		x = xt2;
		y = yt2;	
		a[x*col +y].setIcon(createImageIcon("p10"+sansDisplay+".png"));
	}

	//Teleports the player
	public void teleport2() {
		a[x * col + y].setIcon(createImageIcon("p1.png"));
		x = xt1;
		y = yt1;	
		a[x*col +y].setIcon(createImageIcon("p10"+sansDisplay+".png"));	
	}

	//Teleports the player when rotated
	public void teleport3() {
		if (layout[x][y] == layout[xt1][yt1]) {
			a[x * col + y].setIcon(createImageIcon("p1.png"));
			x = xt2;
			y = yt2;	
			a[x*col +y].setIcon(createImageIcon("p10"+sansDisplay+".png"));	
		} else if (layout[x][y] == layout[xt2][yt2]) {
			a[x * col + y].setIcon(createImageIcon("p1.png"));
			x = xt1;
			y = yt1;	
			a[x*col +y].setIcon(createImageIcon("p10"+sansDisplay+".png"));	
		}
		redraw();
	}

	//Add points to the game
	public void addPoints(String direction) {
		totalCoins--;
		coinsCount.setText("# Of Coins Remaining: " + totalCoins);
		//Removes the coin from the grid once collected
		if (direction == "up") 
			layout[x - 1][y] = 1;
		else if (direction == "down")
			layout[x + 1][y] = 1;
		else if (direction == "left")
			layout[x][y-1] = 1;
		else if (direction == "right")
			layout[x][y+1] = 1;
		else if (direction == "rotate")
			layout[x][y] = 1;

		if (totalCoins == 0) {
			levels();
		}
	}


	//New location for teleporters
	public void newObjectLocation(){
		for (int j =0; j < row; j++) {
			for (int k = 0; k < col; k++)
				if (layout[j][k] == 25) {
					xt1 = j;
					yt1 = k;
				}
		}
		for (int j =0; j < row; j++) {
			for (int k = 0; k < col; k++)
				if (layout[j][k] == 26) {
					xt2 = j;
					yt2 = k;
				}
		}
	}

	//Calculates the # of spikes and health that the user is going to lose
	public int calculate() {
		int j = 0;
		int k = -1;
		int totalSpikes = 0;


		while (true) {
			k++;
			if (k == col) {
				k = 0;
				j++;
			}
			if (j == row)
				break;
			if (layout[j][k]== 8)
				totalSpikes++;

		}
		spikeCheck = level;
		return totalSpikes;
	}

	//Deducts the health from the user when they hit a spike
	public void loseHealth() {

		int spike = calculate();
		percentLost = i/spike;

		i = i-percentLost-10;
		progress.setValue (i);
		System.out.println(i);

		if (i <= 0) {
			lose();
			cdLayout.show(p_card, "5");
		}

	}

	//Adds health to the user if they hit a potion
	public void addHealth(String direction) {
		//Doesn't let the user consume the potion if their health is already full
		if (i == 100)
			JOptionPane.showMessageDialog(null, "You are already at full health. Come back and collect later",
					"Alert Message", JOptionPane.ERROR_MESSAGE);
		else {
			if (direction == "up") 
				layout[x - 1][y] = 1;
			else if (direction == "down")
				layout[x + 1][y] = 1;
			else if (direction == "left")
				layout[x][y-1] = 1;
			else if (direction == "right")
				layout[x][y+1] = 1;
			else if (direction == "rotate")
				layout[x][y] = 1;
			i = i+20;
			progress.setValue (i);
		}
	}

	//Method to switch the levels
	public void levels() {
		//Level is increased
		level++;
		//Health is restored
		i = 100;
		if (level == 1)	
			createLevelOne();
		else if (level == 2)
			createLevelTwo();
		else if (level == 3)
			createLevelThree();
		else if (level == 4)
			createLevelFour();
		else if (level == 5)
			createLevelFive();
		else if (level == 6)
			createLevelSix();
		else if (level == 7)
			createLevelSeven();
		else if (level == 8)
			createLevelEight();
		else if (level == 9)
			createLevelNine();
		else if (level == 10)
			createLevelTen();
		else if (level == 11)
			cdLayout.show(p_card, "4");
	}

	//Creates the layout of level one
	public void createLevelOne() {
		for (int j =0; j < row; j++) {
			for (int k = 0; k < col; k++)
				layout[j][k] = level1Layout[j][k];	
		}
		for (int j =0; j < row; j++) {
			for (int k = 0; k < col; k++)
				if (level1Layout[j][k] == 50)
					totalCoins++;
		}
		x = 7;
		y = 2;
		levelDisplay.setText("Level One");
		coinsCount.setText("# Of Coins Remaining: " + totalCoins);
		redraw();
	}

	//Creates the layout for level two
	public void createLevelTwo() {
		for (int j =0; j < row; j++) {
			for (int k = 0; k < col; k++)
				layout[j][k] = level2Layout[j][k];	
		}
		for (int j =0; j < row; j++) {
			for (int k = 0; k < col; k++)
				if (level2Layout[j][k] == 50)
					totalCoins++;
		}
		x = 1;
		y = 6;
		levelDisplay.setText("Level Two");
		coinsCount.setText("# Of Coins Remaining: " + totalCoins);
		redraw();
	}

	//Creates the layout for level three
	public void createLevelThree() {
		for (int j =0; j < row; j++) {
			for (int k = 0; k < col; k++)
				layout[j][k] = level3Layout[j][k];	
		}
		for (int j =0; j < row; j++) {
			for (int k = 0; k < col; k++)
				if (level3Layout[j][k] == 50)
					totalCoins++;
		}
		levelDisplay.setText("Level Three");
		coinsCount.setText("# Of Coins Remaining: " + totalCoins);
		x = 0;
		y = 0;
		redraw();
	}

	//Creates the layout for level four
	public void createLevelFour() {
		for (int j =0; j < row; j++) {
			for (int k = 0; k < col; k++)
				layout[j][k] = level4Layout[j][k];	
		}
		for (int j =0; j < row; j++) {
			for (int k = 0; k < col; k++)
				if (level4Layout[j][k] == 50)
					totalCoins++;
		}
		levelDisplay.setText("Level Four");
		coinsCount.setText("# Of Coins Remaining: " + totalCoins);
		x = 0;
		y = 0;
		redraw();
	}

	//Creates the layout for level five
	public void createLevelFive() {
		for (int j =0; j < row; j++) {
			for (int k = 0; k < col; k++)
				layout[j][k] = level5Layout[j][k];	
		}
		for (int j =0; j < row; j++) {
			for (int k = 0; k < col; k++)
				if (level5Layout[j][k] == 50)
					totalCoins++;
		}
		levelDisplay.setText("Level Five");
		coinsCount.setText("# Of Coins Remaining: " + totalCoins);
		x = 0;
		y = 0;
		redraw();
	}

	//Creates the layout for level six
	public void createLevelSix() {
		for (int j =0; j < row; j++) {
			for (int k = 0; k < col; k++)
				layout[j][k] = level6Layout[j][k];	
		}
		for (int j =0; j < row; j++) {
			for (int k = 0; k < col; k++)
				if (level6Layout[j][k] == 50)
					totalCoins++;
		}
		levelDisplay.setText("Level Six");
		coinsCount.setText("# Of Coins Remaining: " + totalCoins);
		x = 1;
		y = 1;
		redraw();
	}

	//Creates the layout for level seven
	public void createLevelSeven() {
		for (int j =0; j < row; j++) {
			for (int k = 0; k < col; k++)
				layout[j][k] = level7Layout[j][k];	
		}
		for (int j =0; j < row; j++) {
			for (int k = 0; k < col; k++)
				if (level7Layout[j][k] == 50)
					totalCoins++;
				else if (level7Layout[j][k] == 25) {
					xt1 = j;
					yt1 = k;
				}
				else if (level7Layout[j][k] == 26) {
					xt2 = j;
					yt2 = k;
				}
		}
		levelDisplay.setText("Level Seven");
		coinsCount.setText("# Of Coins Remaining: " + totalCoins);
		rightR.setEnabled(false);
		leftR.setEnabled(false);
		x = 1;
		y = 1;
		redraw();
	}

	//Creates the layout for level eight
	public void createLevelEight() {
		for (int j =0; j < row; j++) {
			for (int k = 0; k < col; k++)
				layout[j][k] = level8Layout[j][k];	
		}
		for (int j =0; j < row; j++) {
			for (int k = 0; k < col; k++)
				if (level8Layout[j][k] == 50)
					totalCoins++;
				else if (level8Layout[j][k] == 25) {
					xt1 = j;
					yt1 = k;
				}
				else if (level8Layout[j][k] == 26) {
					xt2 = j;
					yt2 = k;
				}
		}
		rightR.setEnabled(true);
		leftR.setEnabled(true);
		levelDisplay.setText("Level Eight");
		coinsCount.setText("# Of Coins Remaining: " + totalCoins);
		x = 0;
		y = 5;
		redraw();
	}

	//Creates the layout of level nine
	public void createLevelNine() {
		for (int j =0; j < row; j++) {
			for (int k = 0; k < col; k++)
				layout[j][k] = level9Layout[j][k];	
		}
		for (int j =0; j < row; j++) {
			for (int k = 0; k < col; k++)
				if (level9Layout[j][k] == 50)
					totalCoins++;
				else if (level9Layout[j][k] == 25) {
					xt1 = j;
					yt1 = k;
				}
				else if (level9Layout[j][k] == 26) {
					xt2 = j;
					yt2 = k;
				}
		}
		levelDisplay.setText("Level Nine");
		coinsCount.setText("# Of Coins Remaining: " + totalCoins);
		x = 0;
		y = 0;
		redraw();
	}
	
	//Creates the layout of level ten
		public void createLevelTen() {
			for (int j =0; j < row; j++) {
				for (int k = 0; k < col; k++)
					layout[j][k] = level10Layout[j][k];	
			}
			for (int j =0; j < row; j++) {
				for (int k = 0; k < col; k++)
					if (level10Layout[j][k] == 50)
						totalCoins++;
					else if (level10Layout[j][k] == 25) {
						xt1 = j;
						yt1 = k;
					}
					else if (level10Layout[j][k] == 26) {
						xt2 = j;
						yt2 = k;
					}
			}
			levelDisplay.setText("Level Ten");
			coinsCount.setText("# Of Coins Remaining: " + totalCoins);
			x = 0;
			y = 0;
			redraw();
		}


	//Resets the level to the original display while also resetting health
	public void resetLevel() {
		totalCoins = 0;
		i = 100;
		progress.setValue (i);
		switch(level) {
		case (1):
			createLevelOne();
		break;
		case (2):
			createLevelTwo();
		break;
		case (3):
			createLevelThree();
		break;
		case (4):
			createLevelFour();
		break;
		case(5):
			createLevelFive();
		break;
		case(6):
			createLevelSix();
		break;
		case(7):
			createLevelSeven();
		break;
		case(8):
			createLevelEight();
		break;
		case(9):
			createLevelNine();
		break;
		case(10):
			createLevelTen();
		break;
		}

	}

	//Method for when the user loses the game
	public void lose () {

		int ran = (int) (Math.random () * 3) + 1;
		//Displays a random lose message
		if (ran == 1) 
			loseText.setText("Dont lose hope...");
		else if (ran == 2)
			loseText.setText("So close....");
		else 
			loseText.setText("You cannot give up just yet...");
	}

	//Method to show a confirm quit 
	public void quit() {
		Object[] options = { "No", "Yes" };
		int quitGame = JOptionPane.showOptionDialog(null, "Are you sure you want to quit?", "Alert Message",
				JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
				null, options, options[0]);
		if (quitGame == 0) {
			loseText.setText("Sans still needs your help!");
		}
		else
			System.exit(0);
	}

	//Resets the global variables
	public void playAgain(int levelNum) {
		totalCoins = 0;
		i = 100;
		level = levelNum;
		sansDisplay = 0;
		percentLost = 0;
		spikeCheck = ' ';

		//Creates the level based on what the user selected
		if (level == 1)
			createLevelOne();
		else if (level == 2)
			createLevelTwo();
		else if (level == 3)
			createLevelThree();
		else if (level == 4)
			createLevelFour();
		else if (level == 5)
			createLevelFive();
		else if (level == 6)
			createLevelSix();
		else if (level == 7)
			createLevelSeven();
		else if (level == 8)
			createLevelEight();
		else if (level == 9)
			createLevelNine();
		else if (level == 10)
			createLevelTen();

		cdLayout.show(p_card, "3");
	}

	//Shows the users feedback that they gave
	public void showFeedback() {
		userInput = TA.getText();
		JOptionPane.showMessageDialog(null, "You rated this game "+rating+ " stars out of 5. The errors you reported were: "
				+ userInput,
				"Thank you for your feedback!", JOptionPane.ERROR_MESSAGE);
	}

	//Method for music
	public void Music(String filepath) {

		String mainPath = System.getProperty("user.dir");

		try {
			//I don't know if this sound file would work for you since its accessing it from my macbook
			File filePath = new File("/Users/kev/eclipse-workspace/Sokoban/src/hades.wav");

			//This sound file might work for you since it's the same one Samyam used
			//File filePath = new File(mainPath + "\\src\\\\" + filepath);

			if (filePath.exists()) { 
				AudioInputStream audioInput = AudioSystem.getAudioInputStream(filePath);
				Clip clip = AudioSystem.getClip();
				clip.open(audioInput);
				clip.setMicrosecondPosition(0);
				clip.start(); 
				clip.loop(Clip.LOOP_CONTINUOUSLY);
			} 


		} catch (Exception ex) {
			ex.printStackTrace(); 
		}

	}

	public static void main(String[] args) {
		JFrame.setDefaultLookAndFeelDecorated(true);
		// Create and set up the window.
		JFrame frame = new JFrame("SokobanGame");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Create and set up the content pane.
		JComponent newContentPane = new SokobanGame();
		newContentPane.setOpaque(true);
		frame.setContentPane(newContentPane);
		frame.setSize(500, 700);
		frame.setVisible(true);
	}

}