import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.applet.Applet;
import java.applet.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FinalGame_Chess extends Applet implements ActionListener
{
    //points to run ai
    int aipoints = 0;

    //combo box for pieces
    JComboBox pieceOption;
    String pieceOption_s;

    //passwords
    JPasswordField p1;
    JPasswordField p2;

    //water sound
    AudioClip soundFile;

    //turns
    JLabel turnpic;
    JLabel turnpic1;
    char turn = 'b';

    //holds last move
    int last = -1;

    //used for check/checkmate
    int blackcheckpoints = 0;
    int blackcheck = 0;
    int bluecheckpoints = 0;
    int bluecheck = 0;

    Panel p_card;  //to hold all of the screens
    Panel card1, card2, card3, card4, card5, card6; //the six screens
    CardLayout cdLayout = new CardLayout ();

    //grid
    int row = 8;
    int col = 8;
    //screen three buttons
    JButton a[] = new JButton [row * col];
    //screen six buttons
    JButton b[] = new JButton [row * col];
    //array to hold piece type
    char piece[] [] = {{'f', 'd', 'l', 'o', 's', 'l', 'd', 'f'}, {'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c'},
	    {'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'}, {'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'},
	    {'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'}, {'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'},
	    {'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c'}, {'f', 'd', 'l', 'o', 's', 'l', 'd', 'f'}};
    //array to hold selected and unselected pieces
    char select[] [] = {{'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u'}, {'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u'},
	    {'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u'}, {'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u'},
	    {'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u'}, {'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u'},
	    {'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u'}, {'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u'}};
    //array to hold piece colour
    char colour[] [] = {{'l', 'l', 'l', 'l', 'l', 'l', 'l', 'l'}, {'l', 'l', 'l', 'l', 'l', 'l', 'l', 'l'},
	    {'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'}, {'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'},
	    {'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'}, {'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'},
	    {'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b'}, {'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b'}};
    //array to hold piece background
    char bg[] [] = {{'p', 'g', 'p', 'g', 'p', 'g', 'p', 'g'}, {'g', 'p', 'g', 'p', 'g', 'p', 'g', 'p'},
	    {'p', 'g', 'p', 'g', 'p', 'g', 'p', 'g'}, {'g', 'p', 'g', 'p', 'g', 'p', 'g', 'p'},
	    {'p', 'g', 'p', 'g', 'p', 'g', 'p', 'g'}, {'g', 'p', 'g', 'p', 'g', 'p', 'g', 'p'},
	    {'p', 'g', 'p', 'g', 'p', 'g', 'p', 'g'}, {'g', 'p', 'g', 'p', 'g', 'p', 'g', 'p'}};

    //identical arrays to reset
    //resets piece type
    char piece2[] [] = {{'f', 'd', 'l', 'o', 's', 'l', 'd', 'f'}, {'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c'},
	    {'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'}, {'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'},
	    {'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'}, {'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'},
	    {'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c'}, {'f', 'd', 'l', 'o', 's', 'l', 'd', 'f'}};
    //resets all pieces to be unselected
    char select2[] [] = {{'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u'}, {'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u'},
	    {'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u'}, {'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u'},
	    {'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u'}, {'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u'},
	    {'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u'}, {'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u'}};
    //resets the colour of all pieces
    char colour2[] [] = {{'l', 'l', 'l', 'l', 'l', 'l', 'l', 'l'}, {'l', 'l', 'l', 'l', 'l', 'l', 'l', 'l'},
	    {'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'}, {'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'},
	    {'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'}, {'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'},
	    {'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b'}, {'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b'}};
    //resets background of all pieces
    char bg2[] [] = {{'p', 'g', 'p', 'g', 'p', 'g', 'p', 'g'}, {'g', 'p', 'g', 'p', 'g', 'p', 'g', 'p'},
	    {'p', 'g', 'p', 'g', 'p', 'g', 'p', 'g'}, {'g', 'p', 'g', 'p', 'g', 'p', 'g', 'p'},
	    {'p', 'g', 'p', 'g', 'p', 'g', 'p', 'g'}, {'g', 'p', 'g', 'p', 'g', 'p', 'g', 'p'},
	    {'p', 'g', 'p', 'g', 'p', 'g', 'p', 'g'}, {'g', 'p', 'g', 'p', 'g', 'p', 'g', 'p'}};


    public void init ()
    {
	//adds water sound file
	soundFile = getAudioClip (getDocumentBase (),
		"Water.snd");
	//loops water sound file
	soundFile.loop ();
	//holds all the screens
	p_card = new Panel ();
	p_card.setLayout (cdLayout);
	screen1 ();
	screen2 ();
	screen3 ();
	screen4 ();
	screen5 ();
	screen6 ();
	resize (770, 690);
	setLayout (new BorderLayout ());
	add ("Center", p_card);
    }


    //opening screen
    public void screen1 ()
    { //screen 1 is set up.
	card1 = new Panel ();
	//opening screen picture is added
	JButton next = new JButton (createImageIcon ("images/opening.png"));
	next.setBorderPainted (false);
	next.setActionCommand ("s2");
	next.addActionListener (this);
	card1.add (next);
	p_card.add ("1", card1);
    }


    //instructions screen
    public void screen2 ()
    { //screen 2 is set up.
	card2 = new Panel ();
	//instructions picture is added
	JButton next = new JButton (createImageIcon ("images/instructions.png"));
	next.setActionCommand ("next");
	next.addActionListener (this);
	card2.add (next);
	p_card.add ("2", card2);
    }


    //formats screen three
    public void screen3format ()
    {
	//adds grid
	Panel p = new Panel (new GridLayout (row, col));
	int move = 0;
	for (int i = 0 ; i < row ; i++)
	{
	    for (int j = 0 ; j < col ; j++)
	    {
		a [move] = new JButton (createImageIcon ("images/" + piece [i] [j] + "" + colour [i] [j] + "" + bg [i] [j] + "" + select [i] [j] + ".png"));
		a [move].setPreferredSize (new Dimension (70, 70));
		a [move].addActionListener (this);
		a [move].setActionCommand ("" + move);
		p.add (a [move]);
		move++;
	    }
	}
	JLabel title = new JLabel ("\u00B0Underwater Chess- Two Player\u00B0        ");
	title.setFont (new Font ("Ink Free", Font.BOLD, 27));
	title.setForeground (Color.white);
	p1 = new JPasswordField (7);
	JButton done = new JButton ("Done");
	done.setBackground (new Color (243, 153, 152));
	done.setForeground (Color.white);
	done.setPreferredSize (new Dimension (80, 30));
	done.setFont (new Font ("Ink Free", Font.BOLD, 16));
	done.setActionCommand ("Done");
	done.addActionListener (this);
	card3.add (title);
	card3.add (p1);
	card3.add (done);
	card3.add (p);
    }


    //formats screen three
    public void screen3format1 ()
    {
	String[] pieceOptionStrings = {"Crab", "Fish", "Dolphin", "Lobster", "Shark", "Octopus", "Seal"};
	JComboBox pieceOption = new JComboBox (pieceOptionStrings);
	pieceOption.setSelectedIndex (0);
	pieceOption.setActionCommand ("pieceOption");
	pieceOption.addActionListener (this);
	JLabel turn = new JLabel ("Turn: ");
	turn.setForeground (Color.white);
	turn.setFont (new Font ("Ink Free", Font.BOLD, 20));
	turnpic = new JLabel (createImageIcon ("images/cbgu.png"));
	Panel b = new Panel (new GridLayout (1, 2));
	b.add (turn);
	b.add (turnpic);
	card3.add (b);
	card3.add (pieceOption);
    }


    //formats screen three
    public void screen3format2 ()
    {
	JButton reset = new JButton ("Reset");
	reset.setBackground (new Color (243, 153, 152));
	reset.setForeground (Color.white);
	reset.setPreferredSize (new Dimension (100, 30));
	reset.setFont (new Font ("Ink Free", Font.BOLD, 16));
	reset.setActionCommand ("reset");
	reset.addActionListener (this);
	JButton instructions = new JButton ("Instructions");
	instructions.setBackground (new Color (243, 153, 152));
	instructions.setForeground (Color.white);
	instructions.setPreferredSize (new Dimension (120, 30));
	instructions.setFont (new Font ("Ink Free", Font.BOLD, 14));
	instructions.setActionCommand ("Instructions");
	instructions.addActionListener (this);
	JButton surrender = new JButton ("Surrender");
	surrender.setBackground (new Color (243, 153, 152));
	surrender.setForeground (Color.white);
	surrender.setPreferredSize (new Dimension (110, 30));
	surrender.setFont (new Font ("Ink Free", Font.BOLD, 14));
	surrender.setActionCommand ("quit");
	surrender.addActionListener (this);
	JLabel space = new JLabel ("                                         ");
	Panel a = new Panel ();
	a.add (space);
	a.add (reset);
	a.add (instructions);
	a.add (surrender);
	a.add (space);
	card3.add (a);
    }


    //two player game screen
    public void screen3 ()
    { //screen 3 is set up.
	card3 = new Panel ();
	card3.setBackground (new Color (255, 210, 154));
	//screen three featurs are added
	screen3format ();
	screen3format1 ();
	screen3format2 ();
	p_card.add ("3", card3);
    }


    //black wins screen
    public void screen4 ()
    { //screen 4 is set up.
	card4 = new Panel ();
	//black wins picture is added
	JButton next = new JButton (createImageIcon ("images/blackwins.png"));
	next.setBorderPainted (false);
	next.setActionCommand ("s1");
	next.addActionListener (this);
	card4.add (next);
	p_card.add ("4", card4);
    }


    //blue wins screen
    public void screen5 ()
    { //screen 5 is set up.
	card5 = new Panel ();
	//blue wins picture is added
	JButton next = new JButton (createImageIcon ("images/bluewins.png"));
	next.setBorderPainted (false);
	next.setActionCommand ("s1");
	next.addActionListener (this);
	card5.add (next);
	p_card.add ("5", card5);
    }


    //screen six is formatted
    public void screen6format ()
    {
	JLabel title = new JLabel ("\u00B0Underwater Chess- One Player\u00B0        ");
	title.setFont (new Font ("Ink Free", Font.BOLD, 27));
	title.setForeground (Color.white);
	JLabel update = new JLabel ("Blue player: AI       Black player : You                                                   ");
	update.setFont (new Font ("Ink Free", Font.BOLD, 16));
	update.setForeground (Color.white);
	p2 = new JPasswordField (7);
	JButton done = new JButton ("Done");
	done.setBackground (new Color (243, 153, 152));
	done.setForeground (Color.white);
	done.setPreferredSize (new Dimension (80, 30));
	done.setFont (new Font ("Ink Free", Font.BOLD, 16));
	done.setActionCommand ("Done1");
	done.addActionListener (this);
	card6.add (title);
	card6.add (p2);
	card6.add (done);
	card6.add (update);
    }


    //screen six is formatted
    public void screen6format1 ()
    {
	//button grid is added
	Panel p = new Panel (new GridLayout (row, col));
	int move = 0;
	for (int i = 0 ; i < row ; i++)
	{
	    for (int j = 0 ; j < col ; j++)
	    {
		b [move] = new JButton (createImageIcon ("images/" + piece [i] [j] + "" + colour [i] [j] + "" + bg [i] [j] + "" + select [i] [j] + ".png"));
		b [move].setPreferredSize (new Dimension (70, 70));
		b [move].addActionListener (this);
		b [move].setActionCommand ("" + (move + 64));
		p.add (b [move]);
		move++;
	    }
	}
	JLabel turn = new JLabel ("Turn: ");
	turn.setForeground (Color.white);
	turn.setFont (new Font ("Ink Free", Font.BOLD, 20));
	turnpic1 = new JLabel (createImageIcon ("images/cbgu.png"));
	Panel b = new Panel (new GridLayout (1, 2));
	b.add (turn);
	b.add (turnpic1);
	card6.add (p);
	card6.add (b);
    }


    //screen six is formatted
    public void screen6format2 ()
    {
	JButton reset = new JButton ("Reset");
	reset.setBackground (new Color (243, 153, 152));
	reset.setForeground (Color.white);
	reset.setPreferredSize (new Dimension (100, 30));
	reset.setFont (new Font ("Ink Free", Font.BOLD, 16));
	reset.setActionCommand ("reset");
	reset.addActionListener (this);
	JButton instructions = new JButton ("Instructions");
	instructions.setBackground (new Color (243, 153, 152));
	instructions.setForeground (Color.white);
	instructions.setPreferredSize (new Dimension (120, 30));
	instructions.setFont (new Font ("Ink Free", Font.BOLD, 14));
	instructions.setActionCommand ("Instructions");
	instructions.addActionListener (this);
	JButton surrender = new JButton ("Surrender");
	surrender.setBackground (new Color (243, 153, 152));
	surrender.setForeground (Color.white);
	surrender.setPreferredSize (new Dimension (110, 30));
	surrender.setFont (new Font ("Ink Free", Font.BOLD, 14));
	surrender.setActionCommand ("quit");
	surrender.addActionListener (this);
	JLabel space = new JLabel ("                                         ");
	Panel a = new Panel ();
	a.add (space);
	a.add (reset);
	a.add (instructions);
	a.add (surrender);
	a.add (space);
	card6.add (a);
    }


    //AI game screen
    public void screen6 ()
    { //screen 6 is set up.
	card6 = new Panel ();
	card6.setBackground (new Color (255, 210, 154));
	//combo box is added for different pieces
	String[] pieceOptionStrings = {"Crab", "Fish", "Dolphin", "Lobster", "Shark", "Octopus", "Seal"};
	JComboBox pieceOption = new JComboBox (pieceOptionStrings);
	pieceOption.setSelectedIndex (0);
	pieceOption.setActionCommand ("pieceOption");
	pieceOption.addActionListener (this);
	//screen six is formatted
	screen6format ();
	screen6format1 ();
	card6.add (pieceOption);
	screen6format2 ();
	p_card.add ("6", card6);
    }


    protected static ImageIcon createImageIcon (String path)
    {
	java.net.URL imgURL = FinalGame_Chess.class.getResource (path);
	if (imgURL != null)
	{
	    return new ImageIcon (imgURL);
	}
	else
	{
	    System.err.println ("Couldn't find file: " + path);
	    return null;
	}
    }


    //redraws the grid on screen three
    public void redraw ()
    {
	int move = 0;
	for (int i = 0 ; i < row ; i++)
	{
	    for (int j = 0 ; j < col ; j++)
	    {
		a [move].setIcon (createImageIcon ("images/" + piece [i] [j] + "" + colour [i] [j] + "" + bg [i] [j] + "" + select [i] [j] + ".png"));
		move++;
	    }
	}
    }


    //redraws the grid on screen six
    public void redraw1 ()
    {
	int move = 0;
	for (int i = 0 ; i < row ; i++)
	{
	    for (int j = 0 ; j < col ; j++)
	    {
		b [move].setIcon (createImageIcon ("images/" + piece [i] [j] + "" + colour [i] [j] + "" + bg [i] [j] + "" + select [i] [j] + ".png"));
		move++;
	    }
	}
    }


    //if user selects crab
    public void selectCrab (int x, int y)
    {
	//moves two spaces
	if (colour [x] [y] == 'l' && x == 1 && piece [x + 1] [y] == 'x' && piece [x + 2] [y] == 'x')
	{
	    select [x + 1] [y] = 's';
	    select [x + 2] [y] = 's';
	}
	//moves to spaces
	else if (colour [x] [y] == 'b' && x == 6 && piece [x - 1] [y] == 'x' && piece [x - 2] [y] == 'x')
	{
	    select [x - 1] [y] = 's';
	    select [x - 2] [y] = 's';
	}
	//moves one space
	else if (colour [x] [y] == 'l' && piece [x + 1] [y] == 'x')
	    select [x + 1] [y] = 's';
	else if (colour [x] [y] == 'b' && piece [x - 1] [y] == 'x')
	    select [x - 1] [y] = 's';
	if (y < 7 && colour [x] [y] == 'l' && colour [x + 1] [y + 1] == 'b')
	    select [x + 1] [y + 1] = 's';
	if (y > 0 && colour [x] [y] == 'l' && colour [x + 1] [y - 1] == 'b')
	    select [x + 1] [y - 1] = 's';
	if (y < 7 && colour [x] [y] == 'b' && colour [x - 1] [y + 1] == 'l')
	    select [x - 1] [y + 1] = 's';
	if (y > 0 && colour [x] [y] == 'b' && colour [x - 1] [y - 1] == 'l')
	    select [x - 1] [y - 1] = 's';
	checkSelect ();

    }


    //if user selects octopus
    public void selectOctopus (int x, int y)
    {
	//moves one space in eight directions
	if (x - 1 >= 0 && y + 1 < col && colour [x - 1] [y + 1] != turn)
	    select [x - 1] [y + 1] = 's';

	if (x + 1 < row && y + 1 < col && colour [x + 1] [y + 1] != turn)
	    select [x + 1] [y + 1] = 's';

	if (y - 1 >= 0 && colour [x] [y - 1] != turn)
	    select [x] [y - 1] = 's';

	if (x - 1 >= 0 && y - 1 >= 0 && colour [x - 1] [y - 1] != turn)
	    select [x - 1] [y - 1] = 's';

	if (x - 1 >= 0 && colour [x - 1] [y] != turn)
	    select [x - 1] [y] = 's';

	if (y + 1 < col && colour [x] [y + 1] != turn)
	    select [x] [y + 1] = 's';

	if (x + 1 < row && colour [x + 1] [y] != turn)
	    select [x + 1] [y] = 's';

	if (x + 1 < row && y - 1 >= 0 && colour [x + 1] [y - 1] != turn)
	    select [x + 1] [y - 1] = 's';
	checkSelect ();
    }


    //if user selects dolphin
    public void selectDolphin (int x, int y)
    {
	//moves in L shape
	if (x - 2 >= 0 && y - 1 >= 0 && colour [x - 2] [y - 1] != turn)
	    select [x - 2] [y - 1] = 's';

	if (x - 1 >= 0 && y - 2 >= 0 && colour [x - 1] [y - 2] != turn)
	    select [x - 1] [y - 2] = 's';

	if (x - 2 >= 0 && y + 1 < col && colour [x - 2] [y + 1] != turn)
	    select [x - 2] [y + 1] = 's';

	if (x - 1 >= 0 && y + 2 < col && colour [x - 1] [y + 2] != turn)
	    select [x - 1] [y + 2] = 's';

	if (x + 1 < row && y - 2 >= 0 && colour [x + 1] [y - 2] != turn)
	    select [x + 1] [y - 2] = 's';

	if (x + 2 < row && y - 1 >= 0 && colour [x + 2] [y - 1] != turn)
	    select [x + 2] [y - 1] = 's';

	if (x + 2 < row && y + 1 < col && colour [x + 2] [y + 1] != turn)
	    select [x + 2] [y + 1] = 's';

	if (x + 1 < row && y + 2 < col && colour [x + 1] [y + 2] != turn)
	    select [x + 1] [y + 2] = 's';
	checkSelect ();
    }


    //if user selects fish, up and down selection
    public void selectFishUD (int x, int y)
    {
	//up
	int cx1 = x - 1;
	while (cx1 >= 0 && colour [cx1] [y] == 'x')
	{
	    select [cx1] [y] = 's';
	    cx1--;
	}
	if (cx1 >= 0 && colour [cx1] [y] != turn && colour [cx1] [y] != 'x')
	    select [cx1] [y] = 's';
	//down
	cx1 = x + 1;
	while (cx1 < row && colour [cx1] [y] == 'x')
	{
	    select [cx1] [y] = 's';
	    cx1++;
	}
	if (cx1 < row && colour [cx1] [y] != turn && colour [cx1] [y] != 'x')
	    select [cx1] [y] = 's';
    }


    //if user selects fish, right and left selection
    public void selectFishRL (int x, int y)
    {
	//left
	int cy1 = y - 1;
	while (cy1 >= 0 && colour [x] [cy1] == 'x')
	{
	    select [x] [cy1] = 's';
	    cy1--;
	}
	if (cy1 >= 0 && colour [x] [cy1] != turn && colour [x] [cy1] != 'x')
	    select [x] [cy1] = 's';
	//right
	cy1 = y + 1;
	while (cy1 < col && colour [x] [cy1] == 'x')
	{
	    select [x] [cy1] = 's';
	    cy1++;
	}
	if (cy1 < col && colour [x] [cy1] != turn && colour [x] [cy1] != 'x')
	    select [x] [cy1] = 's';
    }


    //holds all fish selections
    public void selectFish (int x, int y)
    {
	selectFishUD (x, y);
	selectFishRL (x, y);
	checkSelect ();
    }


    //if user selects lobster, upright and downright selection
    public void selectLobsterURDR (int x, int y)
    {
	//up right
	int cx1 = x - 1;
	int cy1 = y + 1;
	while (cx1 >= 0 && cy1 < col && colour [cx1] [cy1] == 'x')
	{
	    select [cx1] [cy1] = 's';
	    cx1--;
	    cy1++;
	}
	if (cx1 >= 0 && cy1 < col && colour [cx1] [cy1] != turn && colour [cx1] [cy1] != 'x')
	    select [cx1] [cy1] = 's';
	//down right
	cy1 = y + 1;
	cx1 = x + 1;
	while (cx1 < row && cy1 < col && colour [cx1] [cy1] == 'x')
	{
	    select [cx1] [cy1] = 's';
	    cx1++;
	    cy1++;
	}
	if (cx1 < row && cy1 < col && colour [cx1] [cy1] != turn && colour [cx1] [cy1] != 'x')
	    select [cx1] [cy1] = 's';
    }


    //if user selects lobster, up left and down left selection
    public void selectLobsterULDL (int x, int y)
    {
	//up left
	int cx1 = x - 1;
	int cy1 = y - 1;
	while (cx1 >= 0 && cy1 >= 0 && colour [cx1] [cy1] == 'x')
	{
	    select [cx1] [cy1] = 's';
	    cy1--;
	    cx1--;
	}
	if (cx1 >= 0 && cy1 >= 0 && colour [cx1] [cy1] != turn && colour [cx1] [cy1] != 'x')
	    select [cx1] [cy1] = 's';
	//down left
	cx1 = x + 1;
	cy1 = y - 1;
	while (cx1 < row && cy1 >= 0 && colour [cx1] [cy1] == 'x')
	{
	    select [cx1] [cy1] = 's';
	    cy1--;
	    cx1++;
	}
	if (cx1 < row && cy1 >= 0 && colour [cx1] [cy1] != turn && colour [cx1] [cy1] != 'x')
	    select [cx1] [cy1] = 's';
    }


    //holds all lobster selections
    public void selectLobster (int x, int y)
    {
	selectLobsterURDR (x, y);
	selectLobsterULDL (x, y);
	checkSelect ();
    }


    //if user selects seal
    public void selectSeal (int x, int y)
    {
	//jumps over a piece
	if ((x + 2) < row && piece [x + 1] [y] != 'x' && colour [x + 2] [y] != turn)
	    select [x + 2] [y] = 's';
	if ((x - 2) >= 0 && piece [x - 1] [y] != 'x' && colour [x - 2] [y] != turn)
	    select [x - 2] [y] = 's';
	if ((y + 2) < col && piece [x] [y + 1] != 'x' && colour [x] [y + 2] != turn)
	    select [x] [y + 2] = 's';
	if ((y - 2) >= 0 && piece [x] [y - 1] != 'x' && colour [x] [y - 2] != turn)
	    select [x] [y - 2] = 's';
	checkSelect ();
    }


    //checks to see if octopus has been selected
    public void checkSelect ()
    {
	for (int i = 0 ; i < row ; i++)
	    for (int j = 0 ; j < col ; j++)
		if (piece [i] [j] == 'o' && select [i] [j] == 's')
		    checkmate ();
    }


    //check condition for black octopus
    public void bOctopusDanger ()
    {
	if (blackcheckpoints < 1)
	{
	    JOptionPane.showMessageDialog (null, "* * * C H E C K *  * * \n \n"
		    + "The black octopus is in danger! \n"
		    + "", "Check", JOptionPane.INFORMATION_MESSAGE);
	    blackcheckpoints++;
	    blackcheck++;

	}
	else
	    checkmate ();
    }


    //check condition for blue octopus
    public void lOctopusDanger ()
    {
	if (bluecheckpoints < 1)
	{
	    JOptionPane.showMessageDialog (null, "* * * C H E C K *  * * \n \n"
		    + "The blue octopus is in danger! \n"
		    + "", "Check", JOptionPane.INFORMATION_MESSAGE);
	    bluecheckpoints++;
	    bluecheck++;
	}
	else
	    checkmate ();
    }


    //checks for crab and dolphin danger
    public void checkCDBlack ()
    {
	for (int i = 0 ; i < row ; i++)
	    for (int j = 0 ; j < col ; j++)
		//finds the black octopus
		if (piece [i] [j] == 'o' && colour [i] [j] == 'b')
		{
		    if (((i - 1) >= 0 && (j - 1) >= 0 && piece [i - 1] [j - 1] == 'c' && colour [i - 1] [j - 1] == 'l') || ((i - 1) >= 0 && (j + 1) < col && piece [i - 1] [j + 1] == 'c' && colour [i - 1] [j + 1] == 'l'))
			bOctopusDanger ();
		    else if ((i - 2) >= 0 && (j - 1) >= 0 && piece [i - 2] [j - 1] == 'd' && colour [i - 2] [j - 1] == 'l')
			bOctopusDanger ();
		    else if ((i - 2) >= 0 && (j + 1) < col && piece [i - 2] [j + 1] == 'd' && colour [i - 2] [j + 1] == 'l')
			bOctopusDanger ();
		    else if ((i - 1) >= 0 && (j - 2) >= 0 && piece [i - 1] [j - 2] == 'd' && colour [i - 1] [j - 2] == 'l')
			bOctopusDanger ();
		    else if ((i - 1) >= 0 && (j + 2) < col && piece [i - 1] [j + 2] == 'd' && colour [i - 1] [j + 2] == 'l')
			bOctopusDanger ();
		    else if ((i + 2) < row && (j - 1) >= 0 && piece [i + 2] [j - 1] == 'd' && colour [i + 2] [j - 1] == 'l')
			bOctopusDanger ();
		    else if ((i + 2) < row && (j + 1) < col && piece [i + 2] [j + 1] == 'd' && colour [i + 2] [j + 1] == 'l')
			bOctopusDanger ();
		    else if ((i + 1) < row && (j - 2) >= 0 && piece [i + 1] [j - 2] == 'd' && colour [i + 1] [j - 2] == 'l')
			bOctopusDanger ();
		    else if ((i + 1) < row && (j + 2) < col && piece [i + 1] [j + 2] == 'd' && colour [i + 1] [j + 2] == 'l')
			bOctopusDanger ();
		}
    }


    //checks for seal danger
    public void checkEBlack ()
    {
	for (int i = 0 ; i < row ; i++)
	    for (int j = 0 ; j < row ; j++)
		//finds the black octopus
		if (piece [i] [j] == 'o' && colour [i] [j] == 'b')
		{
		    if ((i + 2) < row && piece [i + 1] [j] != 'x' && piece [i + 2] [j] == 'e' && colour [i + 2] [j] == 'l')
			bOctopusDanger ();
		    else if ((i - 2) >= 0 && piece [i - 1] [j] != 'x' && piece [i - 2] [j] == 'e' && colour [i - 2] [j] == 'l')
			bOctopusDanger ();
		    if ((j + 2) < col && piece [i] [j + 1] != 'x' && piece [i] [j + 2] == 'e' && colour [i] [j + 2] == 'l')
			bOctopusDanger ();
		    if ((j - 2) >= 0 && piece [i] [j - 1] != 'x' && piece [i] [j - 2] == 'e' && colour [i] [j - 2] == 'l')
			bOctopusDanger ();
		}
    }


    //checks for seal danger
    public void checkEBlue ()
    {
	for (int i = 0 ; i < row ; i++)
	    for (int j = 0 ; j < row ; j++)
		//finds the blue octopus
		if (piece [i] [j] == 'o' && colour [i] [j] == 'l')
		{
		    if ((i + 2) < row && piece [i + 1] [j] != 'x' && piece [i + 2] [j] == 'e' && colour [i + 2] [j] == 'b')
			lOctopusDanger ();
		    else if ((i - 2) >= 0 && piece [i - 1] [j] != 'x' && piece [i - 2] [j] == 'e' && colour [i - 2] [j] == 'b')
			lOctopusDanger ();
		    if ((j + 2) < col && piece [i] [j + 1] != 'x' && piece [i] [j + 2] == 'e' && colour [i] [j + 2] == 'b')
			lOctopusDanger ();
		    if ((j - 2) >= 0 && piece [i] [j - 1] != 'x' && piece [i] [j - 2] == 'e' && colour [i] [j - 2] == 'b')
			lOctopusDanger ();
		}
    }


    //checks for crab and dolphin
    public void checkCDBlue ()
    {
	for (int i = 0 ; i < row ; i++)
	    for (int j = 0 ; j < col ; j++)
		//finds the blue octopus
		if (piece [i] [j] == 'o' && colour [i] [j] == 'l')
		{
		    if (((i + 1) < row && (j - 1) >= 0 && piece [i + 1] [j - 1] == 'c' && colour [i + 1] [j - 1] == 'b') || ((i + 1) < row && (j + 1) < col && piece [i + 1] [j + 1] == 'c' && colour [i + 1] [j + 1] == 'b'))
			lOctopusDanger ();
		    else if ((i - 2) >= 0 && (j - 1) >= 0 && piece [i - 2] [j - 1] == 'd' && colour [i - 2] [j - 1] == 'b')
			lOctopusDanger ();
		    else if ((i - 2) >= 0 && (j + 1) < col && piece [i - 2] [j + 1] == 'd' && colour [i - 2] [j + 1] == 'b')
			lOctopusDanger ();
		    else if ((i - 1) >= 0 && (j - 2) >= 0 && piece [i - 1] [j - 2] == 'd' && colour [i - 1] [j - 2] == 'b')
			lOctopusDanger ();
		    else if ((i - 1) >= 0 && (j + 2) < col && piece [i - 1] [j + 2] == 'd' && colour [i - 1] [j + 2] == 'b')
			lOctopusDanger ();
		    else if ((i + 2) < row && (j - 1) >= 0 && piece [i + 2] [j - 1] == 'd' && colour [i + 2] [j - 1] == 'b')
			lOctopusDanger ();
		    else if ((i + 2) < row && (j + 1) < col && piece [i + 2] [j + 1] == 'd' && colour [i + 2] [j + 1] == 'b')
			lOctopusDanger ();
		    else if ((i + 1) < row && (j - 2) >= 0 && piece [i + 1] [j - 2] == 'd' && colour [i + 1] [j - 2] == 'b')
			lOctopusDanger ();
		    else if ((i + 1) < row && (j + 2) < col && piece [i + 1] [j + 2] == 'd' && colour [i + 1] [j + 2] == 'b')
			lOctopusDanger ();
		}
    }


    //checks for fish and shark danger
    public void checkFSBlack ()
    {
	for (int i = 0 ; i < row ; i++)
	    for (int j = 0 ; j < col ; j++)
		//finds the black octopus
		if (piece [i] [j] == 'o' && colour [i] [j] == 'b')
		{
		    //fish/shark is above
		    int ci = i - 1;
		    while (ci >= 0 && piece [ci] [j] == 'x')
			ci--;
		    //fish/shark is below
		    int ci1 = i + 1;
		    while (ci1 < row && piece [ci1] [j] == 'x')
			ci1++;
		    //fish/shark is to the left
		    int cj = j - 1;
		    while (cj >= 0 && piece [i] [cj] == 'x')
			cj--;
		    //fish/shark is to the right
		    int cj1 = j + 1;
		    while (cj1 < col && piece [i] [cj1] == 'x')
			cj1++;
		    if (ci >= 0 && (piece [ci] [j] == 'f' || piece [ci] [j] == 's') && colour [ci] [j] == 'l')
			bOctopusDanger ();
		    else if (ci1 < row && (piece [ci1] [j] == 'f' || piece [ci1] [j] == 's') && colour [ci1] [j] == 'l')
			bOctopusDanger ();
		    else if (cj >= 0 && (piece [i] [cj] == 'f' || piece [i] [cj] == 's') && colour [i] [cj] == 'l')
			bOctopusDanger ();
		    else if (cj1 < col && (piece [i] [cj1] == 'f' || piece [i] [cj1] == 's') && colour [i] [cj1] == 'l')
			bOctopusDanger ();
		}
    }


    //checks for fish and shark danger
    public void checkFSBlue ()
    {
	for (int i = 0 ; i < row ; i++)
	    for (int j = 0 ; j < col ; j++)
		//finds the blue octopus
		if (piece [i] [j] == 'o' && colour [i] [j] == 'l')
		{
		    //fish/shark is above
		    int ci = i - 1;
		    while (ci >= 0 && piece [ci] [j] == 'x')
			ci--;
		    //fish/shark is below
		    int ci1 = i + 1;
		    while (ci1 < row && piece [ci1] [j] == 'x')
			ci1++;
		    //fish/shark is to the left
		    int cj = j - 1;
		    while (cj >= 0 && piece [i] [cj] == 'x')
			cj--;
		    //fish/shark is to the right
		    int cj1 = j + 1;
		    while (cj1 < col && piece [i] [cj1] == 'x')
			cj1++;
		    if (ci >= 0 && (piece [ci] [j] == 'f' || piece [ci] [j] == 's') && colour [ci] [j] == 'b')
			lOctopusDanger ();
		    else if (ci1 < row && (piece [ci1] [j] == 'f' || piece [ci1] [j] == 's') && colour [ci1] [j] == 'b')
			lOctopusDanger ();
		    else if (cj >= 0 && (piece [i] [cj] == 'f' || piece [i] [cj] == 's') && colour [i] [cj] == 'b')
			lOctopusDanger ();
		    else if (cj1 < col && (piece [i] [cj1] == 'f' || piece [i] [cj1] == 's') && colour [i] [cj1] == 'b')
			lOctopusDanger ();
		}
    }


    //checks for lobster and shark danger
    public void checkLSBlack1 ()
    {
	for (int i = 0 ; i < row ; i++)
	    for (int j = 0 ; j < col ; j++)
		//finds the black octopus
		if (piece [i] [j] == 'o' && colour [i] [j] == 'b')
		{
		    //up left
		    int ci = i - 1;
		    int cj = j - 1;
		    while (ci >= 0 && cj >= 0 && piece [ci] [cj] == 'x')
		    {
			ci--;
			cj--;
		    }
		    //up right
		    int ci1 = i - 1;
		    int cj1 = j + 1;
		    while (ci1 >= 0 && cj1 < col && piece [ci1] [cj1] == 'x')
		    {
			ci1--;
			cj1++;
		    }
		    if (ci >= 0 && cj >= 0 && (piece [ci] [cj] == 'l' || piece [ci] [cj] == 's') && colour [ci] [cj] == 'l')
			bOctopusDanger ();
		    else if (ci1 >= 0 && cj1 < col && (piece [ci1] [cj1] == 'l' || piece [ci1] [cj1] == 's') && colour [ci1] [cj1] == 'l')
			bOctopusDanger ();

		}
    }


    //checks for lobster and shark danger
    public void checkLSBlack2 ()
    {
	for (int i = 0 ; i < row ; i++)
	    for (int j = 0 ; j < col ; j++)
		//finds the black octopus
		if (piece [i] [j] == 'o' && colour [i] [j] == 'b')
		{
		    //down right
		    int ci2 = i + 1;
		    int cj2 = j + 1;
		    while (ci2 < row && cj2 < col && piece [ci2] [cj2] == 'x')
		    {
			ci2++;
			cj2++;
		    }
		    //down left
		    int cj3 = j - 1;
		    int ci3 = i + 1;
		    while (ci3 < row && cj3 >= 0 && piece [ci3] [cj3] == 'x')
		    {
			ci3++;
			cj3--;
		    }
		    if (ci2 < row && cj2 < col && (piece [ci2] [cj2] == 'l' || piece [ci2] [cj2] == 's') && colour [ci2] [cj2] == 'l')
			bOctopusDanger ();
		    else if (ci3 < row && cj3 >= 0 && (piece [ci3] [cj3] == 'l' || piece [ci3] [cj3] == 's') && colour [ci3] [cj3] == 'l')
			bOctopusDanger ();
		}
    }


    //holds lobster and shark danger
    public void checkLSBlack ()
    {
	checkLSBlack1 ();
	checkLSBlack2 ();
    }


    //checks for lobster and shark danger
    public void checkLSBlue1 ()
    {
	for (int i = 0 ; i < row ; i++)
	    for (int j = 0 ; j < col ; j++)
		//finds the blue octopus
		if (piece [i] [j] == 'o' && colour [i] [j] == 'l')
		{
		    //up left
		    int ci = i - 1;
		    int cj = j - 1;
		    while (ci >= 0 && cj >= 0 && piece [ci] [cj] == 'x')
		    {
			ci--;
			cj--;
		    }
		    //up right
		    int ci1 = i - 1;
		    int cj1 = j + 1;
		    while (ci1 >= 0 && cj1 < col && piece [ci1] [cj1] == 'x')
		    {
			ci1--;
			cj1++;
		    }
		    if (ci >= 0 && cj >= 0 && (piece [ci] [cj] == 'l' || piece [ci] [cj] == 's') && colour [ci] [cj] == 'b')
			lOctopusDanger ();
		    else if (ci1 >= 0 && cj1 < col && (piece [ci1] [cj1] == 'l' || piece [ci1] [cj1] == 's') && colour [ci1] [cj1] == 'b')
			lOctopusDanger ();
		}
    }


    //checks for lobster and shark danger
    public void checkLSBlue2 ()
    {
	for (int i = 0 ; i < row ; i++)
	    for (int j = 0 ; j < col ; j++)
		//finds the blue octopus
		if (piece [i] [j] == 'o' && colour [i] [j] == 'l')
		{
		    //down right
		    int ci2 = i + 1;
		    int cj2 = j + 1;
		    while (ci2 < row && cj2 < col && piece [ci2] [cj2] == 'x')
		    {
			ci2++;
			cj2++;
		    }
		    //down left
		    int cj3 = j - 1;
		    int ci3 = i + 1;
		    while (ci3 < row && cj3 >= 0 && piece [ci3] [cj3] == 'x')
		    {
			ci3++;
			cj3--;
		    }

		    if (ci2 < row && cj2 < col && (piece [ci2] [cj2] == 'l' || piece [ci2] [cj2] == 's') && colour [ci2] [cj2] == 'b')
			lOctopusDanger ();
		    else if (ci3 < row && cj3 >= 0 && (piece [ci3] [cj3] == 'l' || piece [ci3] [cj3] == 's') && colour [ci3] [cj3] == 'b')
			lOctopusDanger ();
		}
    }


    //holds lobster and shark danger
    public void checkLSBlue ()
    {
	checkLSBlue1 ();
	checkLSBlue2 ();
    }


    //checks for octopus danger
    public boolean checkOBlue ()
    {
	for (int i = 0 ; i < row ; i++)
	    for (int j = 0 ; j < col ; j++)
		if (piece [i] [j] == 'o' && colour [i] [j] == 'l' && (i + 1) < row && (j + 1) < col && piece [i + 1] [j + 1] == 'o')
		    return false;
		else if (piece [i] [j] == 'o' && colour [i] [j] == 'l' && (i + 1) < row && (j - 1) >= 0 && piece [i + 1] [j - 1] == 'o')
		    return false;
		else if (piece [i] [j] == 'o' && colour [i] [j] == 'l' && (i + 1) < row && piece [i + 1] [j] == 'o')
		    return false;
		else if (piece [i] [j] == 'o' && colour [i] [j] == 'l' && (j - 1) >= 0 && piece [i] [j - 1] == 'o')
		    return false;
		else if (piece [i] [j] == 'o' && colour [i] [j] == 'l' && (j + 1) < col && piece [i] [j + 1] == 'o')
		    return false;
		else if (piece [i] [j] == 'o' && colour [i] [j] == 'l' && (i - 1) >= 0 && piece [i - 1] [j] == 'o')
		    return false;
		else if (piece [i] [j] == 'o' && colour [i] [j] == 'l' && (i - 1) >= 0 && (j - 1) >= 0 && piece [i - 1] [j - 1] == 'o')
		    return false;
		else if (piece [i] [j] == 'o' && colour [i] [j] == 'l' && (i - 1) >= 0 && (j + 1) < col && piece [i - 1] [j + 1] == 'o')
		    return false;
	return true;
    }


    //checks for octopus danger
    public boolean checkOBlack ()
    {
	for (int i = 0 ; i < row ; i++)
	    for (int j = 0 ; j < col ; j++)
		if (piece [i] [j] == 'o' && colour [i] [j] == 'b' && (i + 1) < row && (j + 1) < col && piece [i + 1] [j + 1] == 'o')
		    return false;
		else if (piece [i] [j] == 'o' && colour [i] [j] == 'b' && (i + 1) < row && (j - 1) >= 0 && piece [i + 1] [j - 1] == 'o')
		    return false;
		else if (piece [i] [j] == 'o' && colour [i] [j] == 'b' && (i + 1) < row && piece [i + 1] [j] == 'o')
		    return false;
		else if (piece [i] [j] == 'o' && colour [i] [j] == 'b' && (j - 1) >= 0 && piece [i] [j - 1] == 'o')
		    return false;
		else if (piece [i] [j] == 'o' && colour [i] [j] == 'b' && (j + 1) < col && piece [i] [j + 1] == 'o')
		    return false;
		else if (piece [i] [j] == 'o' && colour [i] [j] == 'b' && (i - 1) >= 0 && piece [i - 1] [j] == 'o')
		    return false;
		else if (piece [i] [j] == 'o' && colour [i] [j] == 'b' && (i - 1) >= 0 && (j - 1) >= 0 && piece [i - 1] [j - 1] == 'o')
		    return false;
		else if (piece [i] [j] == 'o' && colour [i] [j] == 'b' && (i - 1) >= 0 && (j + 1) < col && piece [i - 1] [j + 1] == 'o')
		    return false;

	return true;
    }


    //blue check
    public void checkBlue ()
    {
	if (!checkOBlue ())
	    checkmate ();
	checkCDBlue ();
	checkFSBlue ();
	checkLSBlue ();
	checkEBlue ();
    }


    //black check
    public void checkBlack ()
    {
	if (!checkOBlack ())
	    checkmate ();
	checkCDBlack ();
	checkFSBlack ();
	checkLSBlack ();
	checkEBlack ();
    }


    //blue and black check
    public void checkBlackBlue ()
    {
	checkBlue ();
	checkBlack ();
    }


    //resets the board
    public void reset ()
    {
	//board is set to original
	for (int i = 0 ; i < row ; i++)
	{
	    for (int j = 0 ; j < col ; j++)
	    {
		piece [i] [j] = piece2 [i] [j];
		colour [i] [j] = colour2 [i] [j];
		bg [i] [j] = bg2 [i] [j];
		select [i] [j] = select2 [i] [j];
	    }
	}
	//board is redrawn
	redraw ();
	redraw1 ();
	//turn is set to black
	turn = 'b';
	//turn picture is set to black crab
	turnpic.setIcon (createImageIcon ("images/cbgu.png"));
	turnpic1.setIcon (createImageIcon ("images/cbgu.png"));
	//check points are set back to 0
	bluecheckpoints = 0;
	blackcheckpoints = 0;
	blackcheck = 0;
	bluecheck = 0;
    }


    //changes the turn
    public void turn ()
    {
	if (turn == 'b')
	{
	    if (bluecheckpoints == 1)
	    {
		bluecheck = 0;
		bluecheckpoints = 0;
	    }
	    turn = 'l';
	    turnpic.setIcon (createImageIcon ("images/clgu.png"));
	    turnpic1.setIcon (createImageIcon ("images/clgu.png"));
	}
	else
	{
	    if (blackcheckpoints == 1)
	    {
		blackcheck = 0;
		blackcheckpoints = 0;
	    }
	    turn = 'b';
	    turnpic.setIcon (createImageIcon ("images/cbgu.png"));
	    turnpic1.setIcon (createImageIcon ("images/cbgu.png"));
	}
    }


    //runs AI crab movement
    public void AI1 ()
    {
	for (int i = 0 ; i < row ; i++)
	    for (int j = 0 ; j < col ; j++)
		if (piece [i] [j] == 'c' && colour [i] [j] == 'l')
		{
		    //kills diagonally
		    if ((i + 1) < row && (j + 1) < col && aipoints < 1 && colour [i + 1] [j + 1] == 'b')
		    {
			piece [i + 1] [j + 1] = piece [i] [j];
			piece [i] [j] = 'x';
			colour [i + 1] [j + 1] = colour [i] [j];
			colour [i] [j] = 'x';
			aipoints++;
		    }
		    else if ((i + 1) < row && (j - 1) >= 0 && aipoints < 1 && colour [i + 1] [j - 1] == 'b')
		    {
			piece [i + 1] [j - 1] = piece [i] [j];
			piece [i] [j] = 'x';
			colour [i + 1] [j - 1] = colour [i] [j];
			colour [i] [j] = 'x';
			aipoints++;
		    }
		}
    }


    //runs AI crab movement
    public void AI2 ()
    {
	for (int i = 0 ; i < row ; i++)
	    for (int j = 0 ; j < col ; j++)
		if (piece [i] [j] == 'c' && colour [i] [j] == 'l')
		{
		    //moves two spaces forward
		    if ((i + 2) < row && j >= 0 && j < col && aipoints < 1 && i == 1 && piece [i + 1] [j] == 'x' && piece [i + 2] [j] == 'x')
		    {
			piece [i + 2] [j] = piece [i] [j];
			piece [i] [j] = 'x';
			colour [i + 2] [j] = colour [i] [j];
			colour [i] [j] = 'x';
			aipoints++;
		    }
		    //move one space forward
		    else if ((i + 1) < row && j >= 0 && j < col && aipoints < 1 && piece [i + 1] [j] == 'x')
		    {
			piece [i + 1] [j] = piece [i] [j];
			piece [i] [j] = 'x';
			colour [i + 1] [j] = colour [i] [j];
			colour [i] [j] = 'x';
			aipoints++;
		    }
		}
    }


    //runs AI dolphin movement
    public void AI3 ()
    {
	for (int i = 0 ; i < row ; i++)
	    for (int j = 0 ; j < col ; j++)
		if (piece [i] [j] == 'd' && colour [i] [j] == 'l')
		{
		    //moves in L shape
		    if ((i + 2) < row && (j + 1) < col && aipoints < 1 && colour [i + 2] [j + 1] != 'l')
		    {
			piece [i + 2] [j + 1] = piece [i] [j];
			piece [i] [j] = 'x';
			colour [i + 2] [j + 1] = colour [i] [j];
			colour [i] [j] = 'x';
			aipoints++;
		    }
		    else if ((i + 2) < row && (j - 1) >= 0 && aipoints < 1 && colour [i + 2] [j - 1] != 'l')
		    {
			piece [i + 2] [j - 1] = piece [i] [j];
			piece [i] [j] = 'x';
			colour [i + 2] [j - 1] = colour [i] [j];
			colour [i] [j] = 'x';
			aipoints++;
		    }
		    else if ((i + 1) < row && (j - 2) >= 0 && aipoints < 1 && colour [i + 1] [j - 2] != 'l')
		    {
			piece [i + 1] [j - 2] = piece [i] [j];
			piece [i] [j] = 'x';
			colour [i + 1] [j - 2] = colour [i] [j];
			colour [i] [j] = 'x';
			aipoints++;
		    }
		}
    }


    //runs AI dolphin movement
    public void AI4 ()
    {
	for (int i = 0 ; i < row ; i++)
	    for (int j = 0 ; j < col ; j++)
		if (piece [i] [j] == 'd' && colour [i] [j] == 'l')
		{
		    //moves in L shape
		    if ((i + 1) < row && (j + 2) < col && aipoints < 1 && colour [i + 1] [j + 2] != 'l')
		    {
			piece [i + 1] [j + 2] = piece [i] [j];
			piece [i] [j] = 'x';
			colour [i + 1] [j + 2] = colour [i] [j];
			colour [i] [j] = 'x';
			aipoints++;
		    }
		    else if ((i - 1) >= 0 && (j + 2) < col && aipoints < 1 && colour [i - 1] [j + 2] != 'l')
		    {
			piece [i - 1] [j + 2] = piece [i] [j];
			piece [i] [j] = 'x';
			colour [i - 1] [j + 2] = colour [i] [j];
			colour [i] [j] = 'x';
			aipoints++;
		    }
		    else if ((i - 1) >= 0 && (j - 2) >= 0 && aipoints < 1 && colour [i - 1] [j - 2] != 'l')
		    {
			piece [i - 1] [j - 2] = piece [i] [j];
			piece [i] [j] = 'x';
			colour [i - 1] [j - 2] = colour [i] [j];
			colour [i] [j] = 'x';
			aipoints++;
		    }
		}
    }


    //runs AI dolphin movement
    public void AI5 ()
    {
	for (int i = 0 ; i < row ; i++)
	    for (int j = 0 ; j < col ; j++)
		if (piece [i] [j] == 'd' && colour [i] [j] == 'l')
		{
		    //moves in L shape
		    if ((i - 2) >= 0 && (j - 1) >= 0 && aipoints < 1 && colour [i - 2] [j - 1] != 'l')
		    {
			piece [i - 2] [j - 1] = piece [i] [j];
			piece [i] [j] = 'x';
			colour [i - 2] [j - 1] = colour [i] [j];
			colour [i] [j] = 'x';
			aipoints++;
		    }
		    else if ((i - 2) >= 0 && (j + 1) >= 0 && aipoints < 1 && colour [i - 2] [j + 1] != 'l')
		    {
			piece [i - 2] [j + 1] = piece [i] [j];
			piece [i] [j] = 'x';
			colour [i - 2] [j + 1] = colour [i] [j];
			colour [i] [j] = 'x';
			aipoints++;
		    }
		}
    }


    //runs AI fish/shark movement
    public void AI6 ()
    {
	for (int i = 0 ; i < row ; i++)
	    for (int j = 0 ; j < col ; j++)
		if ((piece [i] [j] == 'f' || piece [i] [j] == 's') && colour [i] [j] == 'l')
		{
		    //moves down
		    if ((i + 1) < row && piece [i + 1] [j] == 'x' && aipoints < 1)
		    {
			while ((i + 1) < row && piece [i + 1] [j] == 'x')
			{
			    piece [i + 1] [j] = piece [i] [j];
			    piece [i] [j] = 'x';
			    colour [i + 1] [j] = colour [i] [j];
			    colour [i] [j] = 'x';
			    i++;

			}
			if ((i + 1) < row && colour [i + 1] [j] == 'b')
			{
			    piece [i + 1] [j] = piece [i] [j];
			    piece [i] [j] = 'x';
			    colour [i + 1] [j] = colour [i] [j];
			    colour [i] [j] = 'x';
			}
			aipoints++;
		    }

		}
    }


    //runs AI fish/shark movement
    public void AI7 ()
    {
	for (int i = 0 ; i < row ; i++)
	    for (int j = 0 ; j < col ; j++)
		if ((piece [i] [j] == 'f' || piece [i] [j] == 's') && colour [i] [j] == 'l')
		{
		    //moves up
		    if ((i - 1) >= 0 && piece [i - 1] [j] == 'x' && aipoints < 1)
		    {
			while ((i - 1) >= 0 && piece [i - 1] [j] == 'x')
			{
			    piece [i - 1] [j] = piece [i] [j];
			    piece [i] [j] = 'x';
			    colour [i - 1] [j] = colour [i] [j];
			    colour [i] [j] = 'x';
			    i--;

			}
			if ((i - 1) >= 0 && colour [i - 1] [j] == 'b')
			{
			    piece [i - 1] [j] = piece [i] [j];
			    piece [i] [j] = 'x';
			    colour [i - 1] [j] = colour [i] [j];
			    colour [i] [j] = 'x';
			}
			aipoints++;
		    }

		}
    }


    //runs AI fish/shark movement
    public void AI8 ()
    {
	for (int i = 0 ; i < row ; i++)
	    for (int j = 0 ; j < col ; j++)
		if ((piece [i] [j] == 'f' || piece [i] [j] == 's') && colour [i] [j] == 'l')
		{
		    //moves right
		    if ((j + 1) < col && piece [i] [j + 1] == 'x' && aipoints < 1)
		    {
			while ((j + 1) < col && piece [i] [j + 1] == 'x')
			{
			    piece [i] [j + 1] = piece [i] [j];
			    piece [i] [j] = 'x';
			    colour [i] [j + 1] = colour [i] [j];
			    colour [i] [j] = 'x';
			    j++;
			}
			if ((j + 1) < col && colour [i] [j + 1] == 'b')
			{
			    piece [i] [j + 1] = piece [i] [j];
			    piece [i] [j] = 'x';
			    colour [i] [j + 1] = colour [i] [j];
			    colour [i] [j] = 'x';
			}
			aipoints++;
		    }

		}
    }


    //runs AI fish/shark movement
    public void AI9 ()
    {
	for (int i = 0 ; i < row ; i++)
	    for (int j = 0 ; j < col ; j++)
		if ((piece [i] [j] == 'f' || piece [i] [j] == 's') && colour [i] [j] == 'l')
		{
		    //moves left
		    if ((j - 1) >= 0 && piece [i] [j - 1] == 'x' && aipoints < 1)
		    {
			while ((j - 1) >= 0 && piece [i] [j - 1] == 'x')
			{
			    piece [i] [j - 1] = piece [i] [j];
			    piece [i] [j] = 'x';
			    colour [i] [j - 1] = colour [i] [j];
			    colour [i] [j] = 'x';
			    j--;

			}
			if ((j - 1) >= 0 && colour [i] [j - 1] == 'b')
			{
			    piece [i] [j - 1] = piece [i] [j];
			    piece [i] [j] = 'x';
			    colour [i] [j - 1] = colour [i] [j];
			    colour [i] [j] = 'x';
			}
			aipoints++;
		    }
		}
    }


    //runs AI lobster/shark movement
    public void AI10 ()
    {
	for (int i = 0 ; i < row ; i++)
	    for (int j = 0 ; j < col ; j++)
		if ((piece [i] [j] == 'l' || piece [i] [j] == 's') && colour [i] [j] == 'l')
		{
		    //moves down right
		    if ((i + 1) < row && (j + 1) < col && piece [i + 1] [j + 1] == 'x' && aipoints < 1)
		    {
			while ((i + 1) < row && (j + 1) < col && piece [i + 1] [j + 1] == 'x')
			{
			    piece [i + 1] [j + 1] = piece [i] [j];
			    piece [i] [j] = 'x';
			    colour [i + 1] [j + 1] = colour [i] [j];
			    colour [i] [j] = 'x';
			    i++;
			    j++;
			}
			if ((i + 1) < row && (j + 1) < col && colour [i + 1] [j + 1] == 'b')
			{
			    piece [i + 1] [j + 1] = piece [i] [j];
			    piece [i] [j] = 'x';
			    colour [i + 1] [j + 1] = colour [i] [j];
			    colour [i] [j] = 'x';
			}
			aipoints++;
		    }
		}
    }


    //runs AI lobster/shark movement
    public void AI11 ()
    {
	for (int i = 0 ; i < row ; i++)
	    for (int j = 0 ; j < col ; j++)
		if ((piece [i] [j] == 'l' || piece [i] [j] == 's') && colour [i] [j] == 'l')
		{
		    //moves up right
		    if ((i - 1) >= 0 && (j + 1) < col && piece [i - 1] [j + 1] == 'x' && aipoints < 1)
		    {
			while ((i - 1) >= 0 && (j + 1) < col && piece [i - 1] [j] == 'x')
			{
			    piece [i - 1] [j + 1] = piece [i] [j];
			    piece [i] [j] = 'x';
			    colour [i - 1] [j + 1] = colour [i] [j];
			    colour [i] [j] = 'x';
			    i--;
			    j++;
			}
			if ((i - 1) >= 0 && (j + 1) < col && colour [i - 1] [j + 1] == 'b')
			{
			    piece [i - 1] [j + 1] = piece [i] [j];
			    piece [i] [j] = 'x';
			    colour [i - 1] [j + 1] = colour [i] [j];
			    colour [i] [j] = 'x';
			}
			aipoints++;
		    }

		}
    }


    //runs AI lobster/shark movement
    public void AI12 ()
    {
	for (int i = 0 ; i < row ; i++)
	    for (int j = 0 ; j < col ; j++)
		if ((piece [i] [j] == 'l' || piece [i] [j] == 's') && colour [i] [j] == 'l')
		{
		    //moves down left
		    if ((i + 1) < row && (j - 1) >= 0 && piece [i + 1] [j - 1] == 'x' && aipoints < 1)
		    {
			while ((i + 1) < row && (j - 1) >= 0 && piece [i + 1] [j - 1] == 'x')
			{
			    piece [i + 1] [j - 1] = piece [i] [j];
			    piece [i] [j] = 'x';
			    colour [i + 1] [j - 1] = colour [i] [j];
			    colour [i] [j] = 'x';
			    i++;
			    j--;
			}
			if ((i + 1) < row && (j - 1) >= 0 && colour [i + 1] [j - 1] == 'b')
			{
			    piece [i + 1] [j - 1] = piece [i] [j];
			    piece [i] [j] = 'x';
			    colour [i + 1] [j - 1] = colour [i] [j];
			    colour [i] [j] = 'x';
			}
			aipoints++;
		    }
		}
    }


    //runs AI lobster/shark movement
    public void AI13 ()
    {
	for (int i = 0 ; i < row ; i++)
	    for (int j = 0 ; j < col ; j++)
		if ((piece [i] [j] == 'l' || piece [i] [j] == 's') && colour [i] [j] == 'l')
		{
		    //moves up left
		    if ((i - 1) >= 0 && (j - 1) >= 0 && piece [i - 1] [j - 1] == 'x' && aipoints < 1)
		    {
			while ((i - 1) >= 0 && (j - 1) >= 0 && piece [i - 1] [j - 1] == 'x')
			{
			    piece [i - 1] [j - 1] = piece [i] [j];
			    piece [i] [j] = 'x';
			    colour [i - 1] [j - 1] = colour [i] [j];
			    colour [i] [j] = 'x';
			    i--;
			    j--;
			}
			if ((i - 1) >= 0 && (j - 1) >= 0 && colour [i - 1] [j - 1] == 'b')
			{
			    piece [i - 1] [j - 1] = piece [i] [j];
			    piece [i] [j] = 'x';
			    colour [i - 1] [j - 1] = colour [i] [j];
			    colour [i] [j] = 'x';
			}
			aipoints++;
		    }
		}
    }


    //runs AI seal movement
    public void AI14 ()
    {
	for (int i = 0 ; i < row ; i++)
	    for (int j = 0 ; j < col ; j++)
		if (piece [i] [j] == 'e' && colour [i] [j] == 'l')
		{
		    //moves down
		    if ((i + 2) < row && aipoints < 1 && piece [i + 1] [j] != 'x' && colour [i + 2] [j] != 'l')
		    {
			piece [i + 2] [j] = piece [i] [j];
			piece [i] [j] = 'x';
			colour [i + 2] [j] = colour [i] [j];
			colour [i] [j] = 'x';
			aipoints++;
		    }
		    //moves up
		    else if ((i - 2) >= 0 && aipoints < 1 && piece [i - 1] [j] != 'x' && colour [i - 2] [j] != 'l')
		    {
			piece [i - 2] [j] = piece [i] [j];
			piece [i] [j] = 'x';
			colour [i - 2] [j] = colour [i] [j];
			colour [i] [j] = 'x';
			aipoints++;
		    }

		}
    }


    //runs AI seal movement
    public void AI15 ()
    {
	for (int i = 0 ; i < row ; i++)
	    for (int j = 0 ; j < col ; j++)
		if (piece [i] [j] == 'e' && colour [i] [j] == 'l')
		{
		    //moves right
		    if ((j + 2) < col && aipoints < 1 && piece [i] [j + 1] != 'x' && colour [i] [j + 2] != 'l')
		    {
			piece [i] [j + 2] = piece [i] [j];
			piece [i] [j] = 'x';
			colour [i] [j + 2] = colour [i] [j];
			colour [i] [j] = 'x';
			aipoints++;
		    }
		    //moves left
		    else if ((j - 2) >= 0 && aipoints < 1 && piece [i] [j - 1] != 'x' && colour [i] [j - 2] != 'l')
		    {
			piece [i] [j - 2] = piece [i] [j];
			piece [i] [j] = 'x';
			colour [i] [j - 2] = colour [i] [j];
			colour [i] [j] = 'x';
			aipoints++;
		    }
		}
    }


    //holds AI crab movement
    public void AIone ()
    {

	AI1 ();
	AI2 ();
	for (int i = 0 ; i < row ; i++)
	    for (int j = 0 ; j < row ; j++)
		if (i == 7 && piece [i] [j] == 'c' && colour [i] [j] == 'l')
		    piece [i] [j] = 'e';
    }


    //holds AI dolphin movement
    public void AItwo ()
    {
	AI3 ();
	AI4 ();
	AI5 ();
    }


    //holds AI fish/shark movement
    public void AIthree ()
    {
	AI6 ();
	AI7 ();
	AI8 ();
	AI9 ();
    }


    //holds AI fish/lobster movement
    public void AIfour ()
    {
	AI10 ();
	AI11 ();
	AI12 ();
	AI13 ();
    }


    //holds AI seal movement
    public void AIfive ()
    {
	AI14 ();
	AI15 ();
    }


    //calculates AI movement
    public void AIpoints ()
    {
	if (aipoints >= 1)
	{
	    turn ();
	    redraw1 ();
	    checkBlackBlue ();
	}

	else
	    AI ();
    }


    //runs the AI
    public void AI ()
    {
	if (turn == 'l')
	{
	    int num = (int) (Math.random () * 5 + 1);
	    aipoints = 0;
	    if (num == 1)
		AIone ();
	    else if (num == 2)
		AItwo ();
	    else if (num == 3)
		AIthree ();
	    else if (num == 4)
		AIfour ();
	    else if (num == 5)
		AIfive ();
	    int o = 0;
	    //checks if there's still a black octopus
	    for (int i = 0 ; i < row ; i++)
		for (int j = 0 ; j < col ; j++)
		    if (piece [i] [j] == 'o' && colour [i] [j] == 'b')
			o++;
	    if (o == 1)
		AIpoints ();
	    else
		checkmate ();
	}
    }


    //player selects a piece
    public void playerSelect (int x, int y, int n)
    {
	if (piece [x] [y] == 'c')
	    selectCrab (x, y);
	else if (piece [x] [y] == 'o')
	    selectOctopus (x, y);
	else if (piece [x] [y] == 'd')
	    selectDolphin (x, y);
	else if (piece [x] [y] == 'f')
	    selectFish (x, y);
	else if (piece [x] [y] == 'l')
	    selectLobster (x, y);
	else if (piece [x] [y] == 's')
	{
	    selectFish (x, y);
	    selectLobster (x, y);
	}
	else if (piece [x] [y] == 'e')
	    selectSeal (x, y);
	last = n;
    }


    //unselects after player makes a move
    public void unselect ()
    {
	for (int i = 0 ; i < row ; i++)
	    for (int j = 0 ; j < col ; j++)
		select [i] [j] = 'u';
    }


    //AI
    public void blackPlayer (int n)
    {
	int x = (n / col) - 8;
	int y = n % col;
	showStatus ("(" + x + ", " + y + ")");
	if (turn != colour [x] [y] && last == -1)
	    showStatus ("It's not your turn.");
	else if (last == -1 && turn == 'b')
	    playerSelect (x, y, n);
	else
	{
	    int lastx = (last / col) - 8;
	    int lasty = last % col;
	    //move
	    if (select [x] [y] == 's' && piece [x] [y] == 'o')
		cdLayout.show (p_card, "4");
	    if (select [x] [y] == 's')
	    {
		piece [x] [y] = piece [lastx] [lasty];
		piece [lastx] [lasty] = 'x';
		colour [x] [y] = colour [lastx] [lasty];
		colour [lastx] [lasty] = 'x';
		//crab promotion
		if (!crabPromotion (x, y))
		    piece [x] [y] = 'e';
		//checks for danger
		checkBlackBlue ();
		//changes turn
		turn ();
	    }
	    unselect ();
	    last = -1;
	}
    }


    //changes crab to a seal
    public boolean crabPromotion (int x, int y)
    {
	if (x == 7 && piece [x] [y] == 'c' && colour [x] [y] == 'l')
	    return false;
	else if (x == 0 && piece [x] [y] == 'c' && colour [x] [y] == 'b')
	    return false;
	else
	    return true;
    }


    //two player version
    public void twoPlayer (int n)
    {
	int x = n / col;
	int y = n % col;
	showStatus ("(" + x + ", " + y + ")");
	if (turn != colour [x] [y] && last == -1)
	    showStatus ("It's not your turn.");
	else if (last == -1 && turn == colour [x] [y])
	    playerSelect (x, y, n);
	else
	{
	    int lastx = last / col;
	    int lasty = last % col;
	    //move
	    if (select [x] [y] == 's' && piece [x] [y] == 'o')
		cdLayout.show (p_card, "4");
	    if (select [x] [y] == 's')
	    {
		piece [x] [y] = piece [lastx] [lasty];
		piece [lastx] [lasty] = 'x';
		colour [x] [y] = colour [lastx] [lasty];
		colour [lastx] [lasty] = 'x';
		//crab promotion
		if (!crabPromotion (x, y))
		    piece [x] [y] = 'e';
		//checks for danger
		checkBlackBlue ();
		//changes turn
		turn ();
	    }
	    unselect ();
	    last = -1;
	}
	//redraws with changes
	redraw ();
    }


    //AI version
    public void AIversion (int n)
    {
	blackPlayer (n);
	redraw1 ();
	AI ();
    }


    //checkmate
    public void checkmate ()
    {
	if (blackcheckpoints >= 1)
	{
	    JOptionPane.showMessageDialog (null, createImageIcon ("images/obgu.png"), "Checkmate", JOptionPane.INFORMATION_MESSAGE);
	    reset ();
	    cdLayout.show (p_card, "5");
	}
	else
	{
	    JOptionPane.showMessageDialog (null, createImageIcon ("images/olgu.png"), "Checkmate", JOptionPane.INFORMATION_MESSAGE);
	    reset ();
	    cdLayout.show (p_card, "4");
	}
    }


    //player chooses a level
    public void chooseLevel (ActionEvent e)
    {
	String[] possibleValues = {"Two Player", "One Player"};
	String selectedValue = (String) JOptionPane.showInputDialog (null,
		"Choose an option below:", "Input", JOptionPane.INFORMATION_MESSAGE, null,
		possibleValues, possibleValues [0]);
	if (selectedValue.equals ("Two Player"))
	{
	    cdLayout.show (p_card, "3");
	    reset ();
	    JOptionPane.showMessageDialog (null, "Today's date (dd/mm/yyyy)", "Enter the password and press the done button",
		    JOptionPane.INFORMATION_MESSAGE);
	}
	else
	{
	    cdLayout.show (p_card, "6");
	    reset ();
	    JOptionPane.showMessageDialog (null, "Today's date (dd/mm/yyyy)", "Enter the password and press the done button",
		    JOptionPane.INFORMATION_MESSAGE);
	}
    }


    //password is checked for screen three
    public void checkPassword (ActionEvent e)
    {
	SimpleDateFormat formatter = new SimpleDateFormat ("dd/MM/yyyy");
	Date todaysdate = new Date ();
	if (p1.getText ().equals (formatter.format (todaysdate)))
	    JOptionPane.showMessageDialog (null, "Welcome", "Welcome", JOptionPane.INFORMATION_MESSAGE);
	else
	    JOptionPane.showMessageDialog (null, "Today's date (dd/mm/yyyy)", "Enter in password", JOptionPane.ERROR_MESSAGE);
    }


    //password is checked for screen six
    public void checkPassword1 (ActionEvent e)
    {
	SimpleDateFormat formatter = new SimpleDateFormat ("dd/MM/yyyy");
	Date todaysdate = new Date ();
	if (p2.getText ().equals (formatter.format (todaysdate)))
	    JOptionPane.showMessageDialog (null, "Welcome", "Welcome", JOptionPane.INFORMATION_MESSAGE);
	else
	    JOptionPane.showMessageDialog (null, "Today's date (dd/mm/yyyy)", "Enter in password", JOptionPane.ERROR_MESSAGE);
    }


    //piece information
    public void combobox1 (ActionEvent e)
    {
	JComboBox cb = (JComboBox) e.getSource ();
	pieceOption_s = (String) cb.getSelectedItem ();
	if (cb.getSelectedItem ().equals ("Crab"))
	{
	    //crab
	    JOptionPane.showMessageDialog (null, "* * *C R A B*  * * \n \n"
		    + " There are a total of six crabs for each player. \n"
		    + " The crab can move forward up to two spaces if \n"
		    + " it's the crab's first time moving. After the first time, \n"
		    + " the crab can move one space forward.\n "
		    + "The crab can kill the opponent's piece diagonally, as long \n "
		    + "as it is still moving in the forward direction. \n"
		    + " If the crab reaches the opposite side of the board it becomes \n"
		    + " a seal, which has its own set of moves. \n"
		    + "", "Instructions", JOptionPane.INFORMATION_MESSAGE);
	}
	else if (cb.getSelectedItem ().equals ("Fish"))
	{
	    //fish
	    JOptionPane.showMessageDialog (null, "* * *F I S H*  * * \n \n"
		    + " There are a total of two fishes for each player. \n"
		    + " The fish can move in all four directions (up, right, \n"
		    + " down, and left), as many spaces as you want it to as \n"
		    + " long as there are no pieces in the way. \n "
		    + "The fish can kill the opponent's piece in all four \n "
		    + "directions as well. \n"
		    + "", "Instructions", JOptionPane.INFORMATION_MESSAGE);
	}
    }


    //piece information
    public void combobox2 (ActionEvent e)
    {
	JComboBox cb = (JComboBox) e.getSource ();
	pieceOption_s = (String) cb.getSelectedItem ();
	if (cb.getSelectedItem ().equals ("Dolphin"))
	{
	    //dolphin
	    JOptionPane.showMessageDialog (null, "* * *D O L P H I N*  * * \n \n"
		    + " There are a total of two dolphins for each player. \n"
		    + " The dolphin moves in an \"L\"shape and can move in all directions. \n"
		    + " The dolphin can kill an opponent's piece with the \"L\" shape as well. \n"
		    + "", "Instructions", JOptionPane.INFORMATION_MESSAGE);
	}
	else if (cb.getSelectedItem ().equals ("Lobster"))
	{
	    //lobster
	    JOptionPane.showMessageDialog (null, "* * *L O B S T E R*  * * \n \n"
		    + " There are a total of two lobsters for each player. \n"
		    + " The lobster can move in all four directions diagonally (upright, downright, up left, down left). \n"
		    + " The lobster can kill an opponent's piece diagonally as well. \n"
		    + "", "Instructions", JOptionPane.INFORMATION_MESSAGE);
	}
	else if (cb.getSelectedItem ().equals ("Shark"))
	{
	    //shark
	    JOptionPane.showMessageDialog (null, "* * *S H A R K*  * * \n \n"
		    + "There is only one shark for each player. \n"
		    + "The shark is the most powerful piece because it can make the moves of \n"
		    + "both the fish and the lobster. \n"
		    + "The shark can kill an opponent's piece the same as the fish and lobster can as well. \n"
		    + "", "Instructions", JOptionPane.INFORMATION_MESSAGE);
	}
    }


    //piece information
    public void combobox3 (ActionEvent e)
    {
	JComboBox cb = (JComboBox) e.getSource ();
	pieceOption_s = (String) cb.getSelectedItem ();
	if (cb.getSelectedItem ().equals ("Octopus"))
	{
	    //octopus
	    JOptionPane.showMessageDialog (null, "* * *O C T O P U S*  * * \n \n"
		    + " There is only one octopus for each player. \n"
		    + " The octopus is the most powerful piece because if it is killed the game is over. \n"
		    + " The octopus can move in every direction around it, as long as it is only \n"
		    + " moving one space (up, right, left, down, upright, downright, up left, down left). \n"
		    + "", "Instructions", JOptionPane.INFORMATION_MESSAGE);
	}
	else if (cb.getSelectedItem ().equals ("Seal"))
	{
	    //seal
	    JOptionPane.showMessageDialog (null, "* * *S E A L*  * * \n \n"
		    + " If a crab reaches the opposite end of the board, it becomes a seal. \n"
		    + " The seal can jump over a piece in all four directions (up, right, down, left). \n"
		    + " The seal can kill an opponent 's piece by jumping over a piece as well. \n"
		    + " The seal will only be able to move if there is a piece to jump over. \n"
		    + "", "Instructions", JOptionPane.INFORMATION_MESSAGE);
	}
    }


    //pieces information
    public void combobox (ActionEvent e)
    {
	combobox1 (e);
	combobox2 (e);
	combobox3 (e);
    }


    public void actionPerformed (ActionEvent e)
    { //opening screen
	if (e.getActionCommand ().equals ("s1"))
	    cdLayout.show (p_card, "1");
	//instructions screen
	else if (e.getActionCommand ().equals ("s2") || e.getActionCommand ().equals ("Instructions"))
	    cdLayout.show (p_card, "2");
	//button to choose level
	else if (e.getActionCommand ().equals ("next"))
	    chooseLevel (e);
	//check password on screen three
	else if (e.getActionCommand ().equals ("Done"))
	    checkPassword (e);
	//checks password on screen six
	else if (e.getActionCommand ().equals ("Done1"))
	    checkPassword1 (e);
	//user chooses to quit
	else if (e.getActionCommand ().equals ("quit"))
	    System.exit (0);
	//user resets board
	else if (e.getActionCommand ().equals ("reset"))
	    reset ();
	//user selects the combo box
	else if (e.getActionCommand ().equals ("pieceOption"))
	    combobox (e);
	//user selects a button on screen three
	else if (e.getActionCommand ().equals ("0") || e.getActionCommand ().equals ("1") || e.getActionCommand ().equals ("2") || e.getActionCommand ().equals ("3") || e.getActionCommand ().equals ("4") || e.getActionCommand ().equals ("5") || e.getActionCommand ().equals ("6")
		|| e.getActionCommand ().equals ("7") || e.getActionCommand ().equals ("8") || e.getActionCommand ().equals ("9") || e.getActionCommand ().equals ("10") || e.getActionCommand ().equals ("11") || e.getActionCommand ().equals ("12") || e.getActionCommand ().equals ("13")
		|| e.getActionCommand ().equals ("14") || e.getActionCommand ().equals ("15") || e.getActionCommand ().equals ("16") || e.getActionCommand ().equals ("17") || e.getActionCommand ().equals ("18") || e.getActionCommand ().equals ("19") || e.getActionCommand ().equals ("20")
		|| e.getActionCommand ().equals ("21") || e.getActionCommand ().equals ("22") || e.getActionCommand ().equals ("23") || e.getActionCommand ().equals ("24") || e.getActionCommand ().equals ("25") || e.getActionCommand ().equals ("26") || e.getActionCommand ().equals ("27")
		|| e.getActionCommand ().equals ("28") || e.getActionCommand ().equals ("29") || e.getActionCommand ().equals ("30") || e.getActionCommand ().equals ("31") || e.getActionCommand ().equals ("32") || e.getActionCommand ().equals ("33") || e.getActionCommand ().equals ("34")
		|| e.getActionCommand ().equals ("35") || e.getActionCommand ().equals ("36") || e.getActionCommand ().equals ("37") || e.getActionCommand ().equals ("38") || e.getActionCommand ().equals ("39") || e.getActionCommand ().equals ("40") || e.getActionCommand ().equals ("41")
		|| e.getActionCommand ().equals ("42") || e.getActionCommand ().equals ("43") || e.getActionCommand ().equals ("44") || e.getActionCommand ().equals ("45") || e.getActionCommand ().equals ("46") || e.getActionCommand ().equals ("47") || e.getActionCommand ().equals ("48")
		|| e.getActionCommand ().equals ("49") || e.getActionCommand ().equals ("50") || e.getActionCommand ().equals ("51") || e.getActionCommand ().equals ("52") || e.getActionCommand ().equals ("53") || e.getActionCommand ().equals ("54") || e.getActionCommand ().equals ("55")
		|| e.getActionCommand ().equals ("56") || e.getActionCommand ().equals ("57") || e.getActionCommand ().equals ("58") || e.getActionCommand ().equals ("59") || e.getActionCommand ().equals ("60") || e.getActionCommand ().equals ("61") || e.getActionCommand ().equals ("62")
		|| e.getActionCommand ().equals ("63"))
	{
	    int n = Integer.parseInt (e.getActionCommand ());
	    twoPlayer (n);
	}
	//user selects a button on screen six
	else
	{
	    int n = Integer.parseInt (e.getActionCommand ());
	    AIversion (n);
	}
    }
}
