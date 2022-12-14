package dynamic_beat_custom;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class DynamicBeat extends JFrame {

	private Image screenImage;
	private Graphics screenGraphic;

	// Exit Button in MenuBar
	private ImageIcon exitButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/exitButtonEntered.png"));
	private ImageIcon exitButtonBasicImage = new ImageIcon(Main.class.getResource("../images/exitButtonBasic.png"));

	// Start Button and Quit Button in Frame
	private ImageIcon startButtonEnteredImage = new ImageIcon(new ImageIcon(Main.class.getResource("../images/startButtonEntered.png"))
			.getImage().getScaledInstance(300, 75, Image.SCALE_SMOOTH));
	private ImageIcon startButtonBasicImage = new ImageIcon(new ImageIcon(Main.class.getResource("../images/startButtonBasic.png"))
			.getImage().getScaledInstance(300, 75, Image.SCALE_SMOOTH));
	private ImageIcon quitButtonEnteredImage = new ImageIcon(new ImageIcon(Main.class.getResource("../images/quitButtonEntered.png"))
			.getImage().getScaledInstance(300, 75, Image.SCALE_SMOOTH));
	private ImageIcon quitButtonBasicImage = new ImageIcon(new ImageIcon(Main.class.getResource("../images/quitButtonBasic.png"))
			.getImage().getScaledInstance(300, 75, Image.SCALE_SMOOTH));


	// Left Button and Right Button in Choosing Music Frame
	private ImageIcon leftButtonEnteredImage = new ImageIcon(new ImageIcon(Main.class.getResource("../images/leftButtonEntered.png"))
			.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH));
	private ImageIcon leftButtonBasicImage = new ImageIcon(new ImageIcon(Main.class.getResource("../images/leftButtonBasic.png"))
			.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH));
	private ImageIcon rightButtonEnteredImage = new ImageIcon(new ImageIcon(Main.class.getResource("../images/rightButtonEntered.png"))
			.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH));
	private ImageIcon rightButtonBasicImage = new ImageIcon(new ImageIcon(Main.class.getResource("../images/rightButtonBasic.png"))
			.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH));

	// Choose Difficulty Buttons
	private ImageIcon easyButtonEnteredImage = new ImageIcon(new ImageIcon(Main.class.getResource("../images/easyButtonEntered.png"))
			.getImage().getScaledInstance(250, 70, Image.SCALE_SMOOTH));
	private ImageIcon easyButtonBasicImage = new ImageIcon(new ImageIcon(Main.class.getResource("../images/easyButtonBasic.png"))
			.getImage().getScaledInstance(250, 70, Image.SCALE_SMOOTH));
	private ImageIcon hardButtonEnteredImage = new ImageIcon(new ImageIcon(Main.class.getResource("../images/hardButtonEntered.png"))
			.getImage().getScaledInstance(250, 70, Image.SCALE_SMOOTH));
	private ImageIcon hardButtonBasicImage = new ImageIcon(new ImageIcon(Main.class.getResource("../images/hardButtonBasic.png"))
			.getImage().getScaledInstance(250, 70, Image.SCALE_SMOOTH));

	// Back Button at gaming Frame
	private ImageIcon backButtonBasicImage = new ImageIcon(Main.class.getResource("../images/backButtonBasic.png"));
	private ImageIcon backButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/backButtonEntered.png"));

	private Image background = new ImageIcon(Main.class.getResource("../images/introBackground(Title2).jpg"))
			.getImage().getScaledInstance(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT, Image.SCALE_SMOOTH);

	Image menuBarImg = new ImageIcon(Main.class.getResource("../images/menuBar.png"))
			.getImage().getScaledInstance(Main.SCREEN_WIDTH, 30, Image.SCALE_SMOOTH);
	private JLabel menuBar = new JLabel(new ImageIcon(menuBarImg));

	private JButton exitButton = new JButton(exitButtonBasicImage);
	private JButton startButton = new JButton(startButtonBasicImage);
	private JButton quitButton = new JButton(quitButtonBasicImage);
	private JButton leftButton = new JButton(leftButtonBasicImage);
	private JButton rightButton = new JButton(rightButtonBasicImage);
	private JButton easyButton = new JButton(easyButtonBasicImage);
	private JButton hardButton = new JButton(hardButtonBasicImage);
	private JButton backButton = new JButton(backButtonBasicImage);
	
	private int mouseX, mouseY;

	private boolean isMainScreen = false;
	private boolean isGameScreen = false;

	ArrayList<Track> trackList = new ArrayList<Track>();
	
	private Image titleImage;
	private Image selectedImage;
	private Music selectedMusic;
	private Music introMusic = new Music("introMusic.mp3", true);
	private int	nowSelected = 0; 
	
	public static Game game; // ?????????
	// ????????? ?????????, ?????? ???????????? ????????? ???????????? ???, ??? ????????? ????????? ?????? ??????
	// ???, ????????? ???????????? ?????????????????? ????????? ??? ??????. 
	// ???, game????????? ????????? ?????? ???????????? ????????? ???????????? ????????? ??????????????? public static ???????????????. 

	private void updateList(){
		try{
			Scanner scanner = new Scanner(new File("database/TrackList.csv"));
			for(int i=0;scanner.hasNextLine();i++){
				String[] data = scanner.nextLine().split("\",\"");
				if(i>0){
					Track track = new Track(
							data[0].replace("\"", ""), data[1],data[2],
							data[3],data[4],data[5].replace("\"", ""));
					trackList.add(track);
				}
			}scanner.close();
		} catch (Exception e){
			e.printStackTrace();
		}
	}

	public DynamicBeat() {
		// trackList??? add
//		trackList.add(new Track("Mighty Love Title Image.png",
//				"Mighty Love Start Image.png", "Mighty Love Game Image.png",
//				"Mighty Love Selected.mp3", "Joakim Karud - Mighty Love.mp3","Joakim Karud - Mighty Love"));
//		trackList.add(new Track("Energy Title Image.png",
//				"Energy Start Image.png", "Energy Game Image.png",
//				"Energy Selected.mp3", "Bensound - Energy.mp3", "Bensound - Energy"));
//		trackList.add(new Track("Wild Flower Title Image.png",
//				"Wild Flower Start Image.png", "Wild Flower Game Image.png",
//				"Wild Flower Selected.mp3", "Joakim Karud - Wild Flower.mp3","Wild Flower - Joakim Karud"));

		updateList();



		setUndecorated(true); // ???????????? ????????? ??? ???????????? 
		setTitle("Dynamic Beat");
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setResizable(false); // ??? ?????? ?????? ???????????????
		setLocationRelativeTo(null); // ?????? ??? ????????? ?????? 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ??? ?????? ??? ?????? ???????????????
		setVisible(true);
		setBackground(new Color(0, 0, 0, 0));
		setLayout(null);
		
		addKeyListener(new KeyListener());
		
		introMusic.start();
		
		// exitButton
		exitButton.setBounds(1565, 0, 30, 30);
		exitButton.setBorderPainted(false);
		exitButton.setContentAreaFilled(false);
		exitButton.setFocusPainted(false);
		exitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				exitButton.setIcon(exitButtonEnteredImage);
				exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				exitButton.setIcon(exitButtonBasicImage);
				exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
				buttonEnteredMusic.start();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				System.exit(0);
			}
		});
		add(exitButton);

		// startButton
		startButton.setBounds(1250, 650, 300, 75);
		startButton.setBorderPainted(false);
		startButton.setContentAreaFilled(false);
		startButton.setFocusPainted(false);
		startButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				startButton.setIcon(startButtonEnteredImage);
				startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				startButton.setIcon(startButtonBasicImage);
				startButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
				buttonEnteredMusic.start();
				// ???????????? ????????? 
				enterMain(); // ???????????? ?????? ??? enterMain ??????(?????????) ?????? 

			}
		});
		add(startButton);

		// quitButton
		quitButton.setBounds(1250, 750, 300, 75);
		quitButton.setBorderPainted(false);
		quitButton.setContentAreaFilled(false);
		quitButton.setFocusPainted(false);
		quitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				quitButton.setIcon(quitButtonEnteredImage);
				quitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				quitButton.setIcon(quitButtonBasicImage);
				quitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
				buttonEnteredMusic.start();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				System.exit(0);
			}
		});
		add(quitButton);

		// leftButton
		leftButton.setVisible(false);
		leftButton.setSize(60, 60);
		leftButton.setLocation(Main.SCREEN_WIDTH/6-(leftButton.getWidth()/2), (Main.SCREEN_HEIGHT-leftButton.getHeight())/2);
		leftButton.setBorderPainted(false);
		leftButton.setContentAreaFilled(false);
		leftButton.setFocusPainted(false);
		leftButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				leftButton.setIcon(leftButtonEnteredImage);
				leftButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				leftButton.setIcon(leftButtonBasicImage);
				leftButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
				buttonEnteredMusic.start();
				// ?????? ?????? ????????? 
				selectLeft();
			}
		});
		add(leftButton);
		
		// rightButton
		rightButton.setVisible(false);
		rightButton.setSize(60, 60);
		rightButton.setLocation(Main.SCREEN_WIDTH/6*5-(leftButton.getWidth()/2), (Main.SCREEN_HEIGHT-rightButton.getHeight())/2);
		rightButton.setBorderPainted(false);
		rightButton.setContentAreaFilled(false);
		rightButton.setFocusPainted(false);
		rightButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				rightButton.setIcon(rightButtonEnteredImage);
				rightButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				rightButton.setIcon(rightButtonBasicImage);
				rightButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
				buttonEnteredMusic.start();
				// ????????? ?????? ????????? 
				selectRight();
			}
		});
		add(rightButton);
		
		// easyButton
		easyButton.setVisible(false);
		easyButton.setSize(250, 67);
		easyButton.setLocation(Main.SCREEN_WIDTH/2-easyButton.getWidth()-50, Main.SCREEN_HEIGHT/6*5);
		easyButton.setBorderPainted(false);
		easyButton.setContentAreaFilled(false);
		easyButton.setFocusPainted(false);
		easyButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				easyButton.setIcon(easyButtonEnteredImage);
				easyButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				easyButton.setIcon(easyButtonBasicImage);
				easyButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
				buttonEnteredMusic.start();
				// easy ?????? ????????? 
				gameStart(nowSelected, "Easy");

			}
		});
		add(easyButton);
		
		// hardButton
		hardButton.setVisible(false);
		hardButton.setSize(250, 67);
		hardButton.setLocation(Main.SCREEN_WIDTH/2+50, Main.SCREEN_HEIGHT/6*5);
		hardButton.setBorderPainted(false);
		hardButton.setContentAreaFilled(false);
		hardButton.setFocusPainted(false);
		hardButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				hardButton.setIcon(hardButtonEnteredImage);
				hardButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				hardButton.setIcon(hardButtonBasicImage);
				hardButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
				buttonEnteredMusic.start();
				// hard ?????? ?????????
				gameStart(nowSelected, "Hard");

			}
		});
		add(hardButton);
		
		// backButton
		backButton.setVisible(false);
		backButton.setBounds(20, 50, 60 ,60);
		backButton.setBorderPainted(false);
		backButton.setContentAreaFilled(false);
		backButton.setFocusPainted(false);
		backButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				backButton.setIcon(backButtonEnteredImage);
				backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				backButton.setIcon(backButtonBasicImage);
				backButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
				buttonEnteredMusic.start();
				// ????????? ????????? 
				backMain();
			}
		});
		add(backButton);

		menuBar.setBounds(0, 0, Main.SCREEN_WIDTH, 30);
		menuBar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
			}
		});
		menuBar.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				setLocation(x - mouseX, y - mouseY);
			}
		});
		add(menuBar);


	}

	public void paint(Graphics g) {
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		screenGraphic = screenImage.getGraphics();
		screenDraw((Graphics2D) screenGraphic);  // Antialiasing ?????? - ?????? ?????? ??????. Graphics2D import ?????????.
		g.drawImage(screenImage, 0, 0, null);
	}

	public void screenDraw(Graphics2D g) {
		g.drawImage(background, 0, 0, null);
		if(isMainScreen) {
			g.drawImage(selectedImage, (Main.SCREEN_WIDTH-selectedImage.getWidth(null))/2
					, (Main.SCREEN_HEIGHT-selectedImage.getHeight(null))/2, null);
			g.drawImage(titleImage, (Main.SCREEN_WIDTH-titleImage.getWidth(null))/2,
					Main.SCREEN_HEIGHT/10, null);
		}
		if(isGameScreen) {
			game.screenDraw(g);
		}
		paintComponents(g);
		try {
			Thread.sleep(5);
		}catch (Exception e) {
			e.printStackTrace();
		}
		// ????????? ???????????? ?????? ???????????????. ==> ?????????????????? ??? ???????????? ?????????. 
		this.repaint();
	}
	
	// <??? ?????? ??????>
	public void selectTrack(int nowSelected) {
		if(selectedMusic != null) // ?????? ?????? ?????? ???????????? ???????????? ???????????? ?????? ?????? ????????????
			selectedMusic.close();
		titleImage = new ImageIcon(Main.class.getResource("../images/"+trackList.get(nowSelected).getTitleImage()))
				.getImage();
		selectedImage = new ImageIcon(Main.class.getResource("../images/"+trackList.get(nowSelected).getStartImage()))
				.getImage();
		selectedMusic = new Music(trackList.get(nowSelected).getStartMusic(),true);
		selectedMusic.start();
	}
	
	// <leftButton ????????? ?????? ??????>
	public void selectLeft() {
		// 0?????? ????????? ?????? ????????? ????????? ?????? ????????? ?????? ????????????. 
		if(nowSelected == 0)
			nowSelected = trackList.size() -1;
		else 
			nowSelected --;
		selectTrack(nowSelected);

	}
	
	// <rightButton ????????? ?????? ??????>
	public void selectRight() {
		// ?????? ????????? ?????? ??? ????????? ????????? ????????? ?????? ????????? ?????? ????????????.
		if (nowSelected == trackList.size() - 1)
			nowSelected = 0;
		else
			nowSelected++;
		selectTrack(nowSelected);
	}

	// <???????????? ??????> 
	public void gameStart(int nowSelected, String difficulty) {
		if(selectedMusic != null) // ?????? ?????? ????????? ?????????????????? ??? ?????? ???????????? 
			selectedMusic.close();
		isMainScreen=false; // ??????????????? ???????????? ????????? ==> screenDraw?????? if (isMainScreen) ???????????? ????????? ??????.  
		// ?????????????????? ??????????????? ??????????????? ?????? ????????? ??????
		leftButton.setVisible(false);
		rightButton.setVisible(false);
		easyButton.setVisible(false);
		hardButton.setVisible(false);
		// ?????????????????? ??????????????? ?????? ????????? ?????????
		background = new ImageIcon(Main.class.getResource("../images/"+trackList.get(nowSelected).getGameImage()))
				.getImage().getScaledInstance(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT, Image.SCALE_SMOOTH);
		backButton.setVisible(true);
		isGameScreen = true;
		game = new Game(trackList.get(nowSelected).getTitleName(), difficulty, trackList.get(nowSelected).getGameMusic());
		game.start(); // Game.java??? run????????? ????????? 
		setFocusable(true); //????????? (?????? ?????????)??? ????????? ???????????? ????????? ????????? 
		// ????????? ????????? ?????? focus??? ?????? ?????? ?????? ??????.
		// ?????? ?????? ?????? ?????? main??? ????????? ???????????? ????????? ????????? ???????????? ????????? ??? ????????? ???????????? ??????????????? ?????????.
	}
	
	// <???????????? ???????????? ??????>
	public void backMain(){
		isGameScreen = false;
		isMainScreen = true;
		// ?????? ?????? - ??????????????? ????????? ????????? ???????????? 
		leftButton.setVisible(true);
		rightButton.setVisible(true);
		easyButton.setVisible(true);
		hardButton.setVisible(true);
		// ?????? ?????? 
		background = new ImageIcon(Main.class.getResource("../images/mainBackground.jpg"))
				.getImage().getScaledInstance(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT, Image.SCALE_SMOOTH);
		// ?????? ?????? - ??????????????? ????????? ?????? ??????
		backButton.setVisible(false);
		selectTrack(nowSelected);
		game.close();
	}
	
	// <???????????? ????????? ?????? ??????> 
	// : intro?????? ??? ?????? ?????? 
	public void enterMain() {
		// ?????? ?????? - ????????? ????????? ?????? ????????? ?????? 
		startButton.setVisible(false);
		quitButton.setVisible(false);
		// ?????? ????????? ?????? 
		background = new ImageIcon(Main.class.getResource("../images/mainBackground.jpg"))
				.getImage().getScaledInstance(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT, Image.SCALE_SMOOTH);
		isMainScreen = true;
		// ?????? ?????? - ??????????????? ????????? ????????? ????????????
		leftButton.setVisible(true);
		rightButton.setVisible(true);
		easyButton.setVisible(true);
		hardButton.setVisible(true);
		introMusic.close();
		selectTrack(0);
	}

}


