
import java.awt.BorderLayout;//enable users to borderLayout
import java.awt.Color;//enable access to borderLayout
import java.awt.Container;//enable access awt components
import java.awt.GridBagConstraints;//enable access to display component
import java.awt.GridBagLayout;//enable access to GridBagLayout
import java.awt.event.ActionEvent;//enable access to perform event
import java.awt.event.ActionListener;//enable access to event listener
import java.io.File;//

import javax.swing.Timer;//enable access to timer
import javax.swing.BorderFactory;//enable access to borderFactory
import javax.swing.ImageIcon;//enable access to image
import javax.swing.JButton;//enable access to button
import javax.swing.JFrame;//enable access to JFrame
import javax.swing.JLabel;//enable access to JLabel
import javax.swing.JMenu;//enable access to JMenu
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;//enable access to JMenuItem
import javax.swing.JOptionPane;
import javax.swing.JPanel;//enable access to JPanel
import javax.swing.JSlider;
import javax.swing.JTextField;//enable access to JTextField
import javax.swing.border.Border;//enable access to border
import javax.sound.sampled.AudioInputStream;//read data
import javax.sound.sampled.AudioSystem;//enable access to audioSystem
import javax.sound.sampled.Clip;//

public class Panel extends JFrame implements ActionListener {
	private JPanel jPPanel1; //panel1
	private JPanel jPPanel2;//panel2
	private JPanel jPPanel3;//panel3
	private static JMenuBar jMMenuBar;//adding menuBar
	private static JMenuItem scenarios,quit,about;// adding menuItem in menuBar
	private JSlider  jSSlider;
	private JButton jBAct, jBRun, jBReset,jBPause, jBOption1, jBOption2, jBOption3, jBExit;//JButton for buttons
	private JButton  jBSpace, jBSpace1, jBSpace2, jBSpace3, jBSpace4;//JButton for buttons
	private JLabel jLOption, jLSquare, jLDirection, jLTimer, jLSpeed;//JLabel for labels
	private JLabel jLLb, jLLb1, jLLb2, jLLb3;//JLabel for labels
	private JTextField jTText1, jTText2, jTText; //JTextField for text
	private JTextField jTHours, jTMinutes, jTSeconds, jTDirectionTxt;//JTextField for text
	private JButton jBForward, jBBackward, jBDown, jBUp, jBLb4;//JButton for buttons
	private JLabel jLDot1, jLDot2, jLTile;//JLabel for labels
	private ImageIcon img1, img2, img3, img4, sand, ball, white, greenfoot;//imageicon for images

	private JLabel jLGoldenBall;//JLabel for goldenball
	private Timer timetimer,timer1, time;
	private int ticks = 0;
	Clip drop;
	int set = 0;
	private boolean bInactive = false, basic= true, advance = false;

	//position checker for the ball to move it
	int posBallx, posBally;

	//setting the imageicon
	ImageIcon mazeIcon = new ImageIcon("images/sand.jpg");
	ImageIcon sandIcon = new ImageIcon("images/sandstone.jpg");
	ImageIcon ballimg = new ImageIcon("images/sand60x60.png");
	ImageIcon whiteimg = new ImageIcon("images/white32x32.jpg");
	ImageIcon gold_ball = new ImageIcon("images/gold-ball.png");
	GridBagConstraints gb = new GridBagConstraints();
	JLabel[][] jLMazeArrs = new JLabel[16][13];

	//main method
	public static void main(String[] args) {
		Panel frame = new Panel();
		frame.setSize(775, 650);//setting frame size
		frame.setTitle(" CBallMaze - Ball Maze Application");//frame title
		ImageIcon greenfoot = new ImageIcon("images/greenfoot.jpg");//frame image
		frame.setIconImage(greenfoot.getImage());
		frame.createGUI();//graphical user interface
		frame.setJMenuBar(jMMenuBar);//adding menu bar
		frame.setVisible(true);

	}

	//sets graphical user interface
	private void createGUI() {

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		Container c = getContentPane();
		c.setLayout(null);

		// adding menu bar
		jMMenuBar = new JMenuBar();
		JMenu scenario = new JMenu("Scenario");
		jMMenuBar.add(scenario);
		quit = new JMenuItem("Exit");//adding menu item
		scenario.add(quit);
		quit.addActionListener(this); 

		JMenu edit = new JMenu("Edit");//adding menubar
		jMMenuBar.add(edit);
		JMenu controls = new JMenu("Controls");
		jMMenuBar.add(controls);

		JMenu help = new JMenu("Help");
		jMMenuBar.add(help);
		about = new JMenuItem("About ");//adding menuitem
		about.addActionListener(this);
		help.add(about);

		// creating borders
		Border raisedbevel = BorderFactory.createRaisedBevelBorder();
		Border loweredbevel = BorderFactory.createRaisedBevelBorder();
		Border compound = BorderFactory.createCompoundBorder(raisedbevel, loweredbevel);

		// panel1
		jPPanel1 = new JPanel(new GridBagLayout());

		jPPanel1.setBounds(0, 0, 575, 550);
		jPPanel1.setBorder(compound);
		c.add(jPPanel1, BorderLayout.LINE_START);

		// panel2
		jPPanel2 = new JPanel();
		jPPanel2.setBounds(575, 0, 200, 550);
		jPPanel2.setLayout(null);
		jPPanel2.setBorder(compound);
		c.add(jPPanel2, BorderLayout.LINE_END);

		// panel3
		jPPanel3 = new JPanel();
		jPPanel3.setBorder(compound);
		jPPanel3.setBounds(0, 550, 775, 100);//setting the size of panel
		c.add(jPPanel3, BorderLayout.PAGE_END);
		jPPanel3.setLayout(null);
		c.add(jPPanel3);
		setVisible(true);

		// adding imageicon
		jBAct = new JButton("Act");
		jBAct.setIcon(new ImageIcon("images/step.png"));
		jBAct.setBounds(30, 5, 75, 30);
		jPPanel3.add(jBAct);//adding button in panel
		jBAct.addActionListener(this);//adding action listener

		//run button
		jBRun = new JButton("Run");
		jBRun.setIcon(new ImageIcon("images/run.png"));//setting imageicon
		jBRun.setBounds(120, 5, 75, 30);//setting the size
		jPPanel3.add(jBRun);
		jBRun.addActionListener(this);

		//pause button
		jBPause = new JButton("Pause");
		jBPause.setIcon(new ImageIcon("images/pause.png"));//imageicon
		jBPause.setBounds(120, 5, 75, 30);
		jPPanel3.add(jBPause);
		jBPause.addActionListener(this);

		//reset button
		jBReset = new JButton("Reset");
		jBReset.setIcon(new ImageIcon("images/reset.png"));
		jBReset.setBounds(230, 5, 95, 30);
		jBReset.addActionListener(this);//adding action listener
		jPPanel3.add(jBReset);

		jLSpeed = new JLabel("Speed: ");
		jLSpeed.setBounds(510, 5, 70, 35);
		jPPanel3.add(jLSpeed);

		// slider
		jSSlider = new JSlider(JSlider.HORIZONTAL, 0, 50, 25);
		jSSlider.setBounds(560, 5, 200, 30);
		jSSlider.setMajorTickSpacing(10);
		jSSlider.setPaintTicks(true);
		jPPanel3.add(jSSlider);

		//label for option
		jLLb = new JLabel("Option:");
		jLLb.setBounds(10, 5, 70, 20);
		jPPanel2.add(jLLb);
		c.add(jPPanel1);

		//textfield for option
		jTText = new JTextField("  1    ");
		jTText.setBounds(90, 5, 70, 20);
		jTText.setEditable(false);
		jPPanel2.add(jTText);

		//label for square
		jLLb1 = new JLabel("Square: ");
		jLLb1.setBounds(10, 35, 70, 20);
		jPPanel2.add(jLLb1);
		c.add(jPPanel1);

		//textfield for square
		jTText1 = new JTextField();
		jTText1.setEditable(false);
		jTText1.setBounds(90, 35, 70, 20);
		jPPanel2.add(jTText1);

		//label for direction
		jLLb2 = new JLabel("Direction:");
		jLLb2.setBounds(10, 65, 70, 20);
		jPPanel2.add(jLLb2);
		c.add(jPPanel2);

		//textfield for direction
		jTText2 = new JTextField("    N    ");
		jTText2.setEditable(false);
		jTText2.setBounds(90, 65, 70, 20);
		jPPanel2.add(jTText2);

		//label for timer
		jLLb3 = new JLabel("DIGITAL TIMER");
		jLLb3.setBounds(40, 100, 100, 20);
		jPPanel2.add(jLLb3);

		//setting timer

		timetimer = new Timer(500, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {//action event
				jTHours.setText(Integer.toString((ticks/(60*60))));//time for hour
				jTMinutes.setText(Integer.toString(ticks/60));//time for minute
				jTSeconds.setText(Integer.toString(ticks%60));//time for second
				ticks = ticks +1;//increases the timer
			}

		});	
		timetimer.stop();//it stops the timer


		//textfield
		jTHours = new JTextField("00");
		jTHours.setBounds(10, 130, 30, 20);
		jTHours.setBackground(Color.BLACK);//setting background color
		jTHours.setForeground(Color.white);
		jPPanel2.add(jTHours);

		jLDot1 = new JLabel(":");
		jLDot1.setBounds(50, 130, 30, 20);
		jPPanel2.add(jLDot1);

		jTMinutes = new JTextField("00");//textfield for minute
		jTMinutes.setBounds(70, 130, 30, 20);
		jTMinutes.setBackground(Color.BLACK);//setting background color
		jTMinutes.setForeground(Color.white);//setting foreground color
		jPPanel2.add(jTMinutes);


		jLDot2 = new JLabel(":");//label for dot
		jLDot2.setBounds(110, 130, 30, 20);
		jPPanel2.add(jLDot2);

		jTSeconds = new JTextField("00");// textfield for second
		jTSeconds.setBounds(130, 130, 30, 20);
		jTSeconds.setBackground(Color.BLACK);//setting background color
		jTSeconds.setForeground(Color.white);//seeting forehead color
		jPPanel2.add(jTSeconds);

		//timer for dropdown method
		timer1 = new Timer(500, new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				if(basic==false || advance==true) {//checking condition
					if(jLMazeArrs[posBallx][posBally+1].getIcon().equals(mazeIcon)){
						jLMazeArrs[posBallx][posBally].setIcon(mazeIcon);
						posBally= posBally+1;
						jLMazeArrs[posBallx][posBally].setIcon(ballimg);
						jBLb4.setIcon(img1);
						jTText2.setText("  South ");//setting direction
						sound("blip.wav");//adding sound
					}
				}
			}
		});
		fall();//dropdown method

		//setting the button for forward/right direction
		jBForward = new JButton(">");
		jBForward.setBounds(120, 210, 50, 25);
		jBForward.addActionListener(this);
		jPPanel2.add(jBForward);

		//setting the button for backward/left direction
		jBBackward = new JButton("<");
		jBBackward.setBounds(20, 210, 50, 25);
		jBBackward.addActionListener(this);
		jPPanel2.add(jBBackward);

        //setting button for space
		jBSpace4 = new JButton();
		jBSpace4.setBounds(75, 210, 40, 25);
		jBSpace4.setBackground(Color.white);
		jPPanel2.add(jBSpace4);

		//setting the button for down direction
		jBDown = new JButton("v");
		jBDown.setBounds(70, 240, 50, 25);
		jBDown.addActionListener(this);
		jPPanel2.add(jBDown);

		  //setting button for space
		jBSpace2 = new JButton();
		jBSpace2.setBounds(20, 240, 45, 25);
		jBSpace2.setBackground(Color.white);
		jPPanel2.add(jBSpace2);

		  //setting button for space
		jBSpace3 = new JButton();
		jBSpace3.setBounds(125, 240, 45, 25);
		jBSpace3.setBackground(Color.white);
		jPPanel2.add(jBSpace3);

		//setting the button for up direction
		jBUp = new JButton("^");
		jBUp.setBounds(70, 180, 50, 25);
		jBUp.addActionListener(this);
		jPPanel2.add(jBUp);

		  //setting button for space
		jBSpace = new JButton();
		jBSpace.setBounds(20, 180, 45, 25);
		jBSpace.setBackground(Color.white);
		jPPanel2.add(jBSpace);

		  //setting button for space
		jBSpace1 = new JButton();
		jBSpace1.setBounds(125, 180, 45, 25);
		jBSpace1.setBackground(Color.white);
		jPPanel2.add(jBSpace1);

		//button for option1
		jBOption1 = new JButton("Option1");
		jBOption1.setBounds(8, 300, 78, 30);
		jBOption1.addActionListener(this);
		jPPanel2.add(jBOption1);

		//button for option2
		jBOption2 = new JButton("Option2");
		jBOption2.setBounds(90, 300, 78, 30);
		jBOption2.addActionListener(this);
		jPPanel2.add(jBOption2);

		//button for option3
		jBOption3 = new JButton("Option3");
		jBOption3.setBounds(8, 335, 78, 30);
		jBOption3.addActionListener(this);
		jPPanel2.add(jBOption3);

		//button for exit
		jBExit = new JButton("Exit");
		jBExit.setBounds(90, 335, 78, 30);
		jBExit.addActionListener(this);
		jPPanel2.add(jBExit);


		//setting imageicon for compass
		img1 = new ImageIcon("images/south.jpg");
		img2 = new ImageIcon("images/west.jpg");
		img3 = new ImageIcon("images/east.jpg");
		img4 = new ImageIcon("images/north.jpg");
		jBLb4 = new JButton(img1);
		jBLb4.setBounds(30, 400, 120, 100);
		jPPanel2.add(jBLb4);


		//mazeScenario
		jPPanel1.setLayout(new GridBagLayout());

		//adding the whole maze scenario
		int x, y;
		for (x = 0; x < 16; x++) {
			for (y = 0; y < 13; y++) {
				gb.gridx = x;
				gb.gridy = y;
				jLMazeArrs[x][y] = new JLabel(mazeIcon);

				//setting whiteimage
				if (x != 1 && x != 5 && x != 10 && y == 1)
				{
					jLMazeArrs[x][y] = new JLabel(whiteimg);

				}
				//setting ballImage
				else if(x==15 && y==0)
				{
					jLMazeArrs[x][y] = new JLabel(ballimg);
					posBallx =x;
					posBally=y;
				}
				//setting whiteimage
				else if (x != 1 && x != 5 && x != 10 && y == 2)
				{
					jLMazeArrs[x][y] = new JLabel(whiteimg);
				}
				else if (x != 2 && x != 6 && x != 12 && y == 4)
				{
					jLMazeArrs[x][y] = new JLabel(whiteimg);
				}

				else if (x != 2 && x != 6 && x != 12 && y == 5) 
				{
					jLMazeArrs[x][y] = new JLabel(whiteimg);
				}
				else if (x != 1 && x != 5 && x != 13 && y == 7) 
				{
					jLMazeArrs[x][y] = new JLabel(whiteimg);
				}

				else if (x != 1 && x != 5 && x != 13 && y == 8)
				{
					jLMazeArrs[x][y] = new JLabel(whiteimg);
				}
				else if (x != 2 && x != 6 && y == 10)
				{
					jLMazeArrs[x][y] = new JLabel(whiteimg);
				}

				else if (x != 2 && x != 6 && y == 11) 
				{
					jLMazeArrs[x][y] = new JLabel(whiteimg);
				}
				//setting sandIcon
				else if (x == 0 && y == 12)
				{
					jLMazeArrs[x][y] = new JLabel(sandIcon);
				}
				jPPanel1.add(jLMazeArrs[x][y], gb);

			}

		}

	}

	//checking position of left white image
	private boolean leftWhiteChecker() {
		if(posBallx!=0 && jLMazeArrs[posBallx-1][posBally].getIcon()==mazeIcon || jLMazeArrs[posBallx-1][posBally].getIcon()==sandIcon)
		{
			return true;
		}
		else 
			return false;
	}

	//checking position of right white image
	private boolean rightWhiteChecker() {
		if(posBallx!=15 && jLMazeArrs[posBallx+1][posBally].getIcon()==mazeIcon)
		{
			return true;
		}
		else 
			return false;
	}

	//checking position of up white image
	private boolean upWhiteChecker() {
		if(posBallx!=0 && jLMazeArrs[posBallx][posBally-1].getIcon()==mazeIcon)
		{
			return true;
		}
		else
			return false;
	}

	//checking position of down white image
	private boolean downWhiteChecker() {
		if(posBallx!=14 && jLMazeArrs[posBallx][posBally+1].getIcon()==mazeIcon)
		{
			return true;
		}
		else
			return false;
	}

	//dropdown method
	public void fall() 
	{
		try {	
			timer1.start();
		}

		catch(Exception e) {
		}
	}

	//position checker for goal
	private boolean  sand() {
		if(posBallx==0 && posBally==12) 
		{
			jLMazeArrs[0][12].setIcon(gold_ball);//setting goal icon
			timetimer.stop();//stops the timer when reached goal
			if(advance == false)
			JOptionPane.showMessageDialog(null, "Congratulations");//displaying message

			return true;			
		}
		else return false;
	}


//position checker for goal 
	private boolean  sandStone() {
		if(posBallx==0 && posBally==12) 
		{
			jLMazeArrs[0][12].setIcon(gold_ball);
			timetimer.stop();
			popMessage();//method calling to display image

			return true;
		}
		else return false;
	}


//method for ball to move right direction
	private void moveRight() 
	{
		jBLb4.setIcon(img3);//setting imageicon
		jTText2.setText(" East ");//shows the direction
		if(rightWhiteChecker()) {
			jLMazeArrs[posBallx +1][posBally].setIcon(ballimg);//setting ball imageicon
			jLMazeArrs[posBallx][posBally].setIcon(mazeIcon);//setting ball mazeicon
			posBallx=posBallx+1;
			jTText1.setText(posBallx + " ," + posBally);//setting the direction for square button
		}
	}

	//method for ball to move left direction
	private void moveLeft()
	{
		jBLb4.setIcon(img2);
		jTText2.setText("  West ");
		if(leftWhiteChecker()) 
		{
			jLMazeArrs[posBallx -1][posBally].setIcon(ballimg);
			jLMazeArrs[posBallx][posBally].setIcon(mazeIcon);
			posBallx=posBallx-1;
			jTText1.setText(posBallx + " ," + posBally);
			sand();

		}
	}

	//method for ball to move upward direction
	private void moveUp() 
	{
		jBLb4.setIcon(img4);
		jTText2.setText(" North ");
		if(upWhiteChecker()) {
			jLMazeArrs[posBallx][posBally-1].setIcon(ballimg);
			jLMazeArrs[posBallx][posBally].setIcon(mazeIcon);
			posBally=posBally-1;
			jTText1.setText(posBallx + " ," + posBally);

		}
	}

	//method for ball to move downward direction
	private void moveDown() {
		jBLb4.setIcon(img1);
		jTText2.setText("  South ");
		if(downWhiteChecker()) {
			jLMazeArrs[posBallx][posBally+1].setIcon(ballimg);
			jLMazeArrs[posBallx][posBally].setIcon(mazeIcon);
			posBally=posBally+1;;
			jTText1.setText(posBallx + " ," + posBally);
		
		}

	}
	//method for running a ball automatically
	private void autoRun() {
		time = new Timer(500,new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if((posBallx == 10 && posBally<3)//checking the postion

						|| (posBallx == 6 && posBally<6)
						|| (posBallx == 5 && posBally<9)
						|| (posBallx == 2 && posBally<12))
					moveDown();//method calling

				else
					moveLeft();

			}

		});
		time.start();//method calling for timer to start
	}

	public void actionPerformed(ActionEvent event) {

        //action performing for exit button
		if (event.getActionCommand().equals("Exit"))
		{
			System.exit(0);
		}
        
		//action performed for displaying message
		if(event.getActionCommand().equals("About ")) {
			JOptionPane.showMessageDialog(null,"Program: \tAssignment 2:Application - Ball Maze\nFilename: \tCBallMaze.java\n@author:\tSwastika Adhikari(18406481)\nCourse: \tBSc.(Hons)Computing Year 1\nModule: \tCSY1020 Problem Solving and Programming\nTutor: \tKumar Lamichanne\n@version: \t1.0\nDate: \t15/07/2018");
		}
		
        //action performed for directions
		if (event.getSource() == jBForward)
		{
			moveRight();

		}

		if (event.getSource() == jBBackward)
		{
			moveLeft();
		}

		if (event.getSource() == jBUp) 
		{
			moveUp();

		}

		if (event.getSource() == jBDown)
		{
			moveDown();

		}
		//action performed for option1
		if (event.getSource() == jBOption1) 
		{
			basic= true;//method calling to check basic 
			advance = false;
			timetimer.start();//start the timer
			sand();
			jTText.setText("   1   ");

		}
		//action performed for option2
		if (event.getSource() == jBOption2)
		{
			basic = false;
			timetimer.start();
			sand();
			jTText.setText("   2   ");
		}
		//action performed for option3
		if (event.getSource() == jBOption3) 
		{
			jTText.setText("   3   ");
			advance=true;
			timetimer.start();
			sandStone();
		    autoRun();//method calling
		}

        //action performed for run button
		if(event.getSource()==jBRun ) 
		{
			bInactive=true;
			jBPause.setIcon(new ImageIcon("images/pause.png"));
			jBPause.setBounds(120, 5, 95, 30);
			jBRun.setVisible(false);
			jBPause.setVisible(true);
			timetimer.start();//starting timer
			set =1 ;
			jTText1.setText(posBallx + " ," + posBally);//condition for showing x-axis and y-axis
			autoRun();//method calling


		}
		//action performed for pause button
		if(event.getSource()==jBPause) 
		{
			jBRun.setIcon(new ImageIcon("images/run.png"));
			jBRun.setBounds(120, 5, 75, 30);
			jBPause.setVisible(false);
			jBRun.setVisible(true);
			timetimer.stop();
		}
		//action performed for act button
		if(event.getSource()==jBAct)
		{

			if((posBallx == 10 && posBally<3)//checking the postion

					|| (posBallx == 6 && posBally<6)
					|| (posBallx == 5 && posBally<9)
					|| (posBallx == 2 && posBally<12))
				moveDown();

			else
				moveLeft();

		
		}
		//action performed for reset button
		if(event.getSource()==jBReset && set == 1 )
		{
			jLMazeArrs[posBallx][posBally].setIcon(mazeIcon);
			jLMazeArrs[15][0].setIcon(ballimg);
			jLMazeArrs[0][12].setIcon(sandIcon);
			posBallx=15;
			posBally=0;
			ticks = 0;
			Panel.main(null);//resetting the application
		}

	}
	//method called for setting sound
	public void sound(String sound) 
	{
		try {
			stop();
			AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File("sound/" + sound ));//showing path
			drop = AudioSystem.getClip();
			drop.open(inputStream);
			drop.start();
		}catch(Exception e) {//catches the file
			e.printStackTrace();
			stop();
		}

	}
	//stopping the sound
	public void stop() 
	{
		if(drop != null)
		{
			drop.stop();
			drop.close();
			drop = null;
		}
	}

    //method to set the image
	private void popMessage() 
	{
		if(advance == true) 
		{
			if(posBallx==0 && posBally==12) //setting the position
			{
				ImageIcon goalImage = new ImageIcon("images/goal.jpg");
				jLMazeArrs[8][8].setIcon(goalImage);
				sound("clap.wav");//code for sound
					}
		}	
	}
}











