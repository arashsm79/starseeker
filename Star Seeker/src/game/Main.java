package game;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;

public class Main extends JFrame {
	public static Board board;
	public static JFrame frame;
	public static int row;
	public static Main mainFrame;
	public static final String[] gameDifficulty = {"Easy(8x8)", "Medium(14x14)", "Hard(20x20)"};
	public static final String[] color = {"Red", "Blue", "Green", "Yellow"};
	
	
	
	
	
	
	
	public static void main(String[] args) {
		if(config.isFirstTimeOpened){
	        long max = 100000;
	        for(long i = 0;  i < max; i++){
	            for(long j = 0;  j < max; j++){
	                 
	            }
	        }
		}
	    
	
		mainFrame = new Main();
	}
	
	public Main(){
		

		
		if(config.isNewGame){
		String getGameDifficulty = getGameDifficulty();
		String getTheNxNofGameDifficulty = getGameDifficulty.substring(getGameDifficulty.indexOf('(') + 1, getGameDifficulty.lastIndexOf(')'));
		row = Integer.valueOf(getTheNxNofGameDifficulty.split("x")[0]);
		config.Difficulty = row;
		
		getColors();
		config.isNewGame = false;
		}else{
			row = config.Difficulty;
		}
	
		setBounds(100, 100, 900, 750);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		board = new Board();
		board.setSize(board.getXsize(), board.getYsize());
		board.setPlayersOnBoard();
		board.setStarsOnBoard();
		board.setWallsOnBoard();
		board.setBombsOnBoard();
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setRightComponent(board);
		splitPane.setLeftComponent(new ScoreBoard());
		getContentPane().add(splitPane, BorderLayout.CENTER);
		
		setJMenuBar(createMenuBar());
		
		setTitle("Star Seeker");
		
		ImageIcon icon = board.createImageIcon("img/ico.png",
	            "Main frame icon");
		setIconImage(icon.getImage());
		setVisible(true);
	

	}

	
	 public static JMenuBar createMenuBar() {

	        JMenuBar menubar = new JMenuBar();

	        
	        //Game Menu
	        JMenu game = new JMenu("Game");
	        game.setMnemonic(KeyEvent.VK_F1);

	        JMenuItem exitItem = new JMenuItem("Exit");
	        exitItem.setMnemonic(KeyEvent.VK_E);
	        exitItem.setToolTipText("Exit application");
	        exitItem.addActionListener((ActionEvent event) -> {
	        	System.exit(0);
	        });
	        JMenuItem newItem = new JMenuItem("New Game");
	        newItem.setMnemonic(KeyEvent.VK_N);
	        newItem.setToolTipText("Start a fresh game");
	        newItem.addActionListener((ActionEvent event) -> {
	        	config.isNewGame = true;
	        	reNewGame();

	        });
	        
	        JMenu difficulty = new JMenu("Skill Level");
	        JMenuItem easy;
	        JMenuItem medium;
	        JMenuItem hard;
	        //Easy
	        if(config.Difficulty==8){
	        	easy = new JMenuItem("<html><p><i>Easy 8x8 «</i></p></html>");

	        }else {
	        	easy = new JMenuItem("<html><p>Easy 8x8</p></html>");
	        }
	        easy.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					config.Difficulty = 8;
					reNewGame();
				}
			});
	        //Medium
	        if(config.Difficulty==14){

	        	medium = new JMenuItem("<html><p><i>Medium 14x14 «</i></p></html>");
	        }else{
		        medium = new JMenuItem("<html><p>Medium 14x14</p></html>");

	        }
	        
	        medium.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					config.Difficulty = 14;
					reNewGame();					
				}
			});
	        //Hard
	        if(config.Difficulty==20){

	        	hard = new JMenuItem("<html><p><i>Hard 20x20 «</i></p></html>");
	        }else{
	        	hard = new JMenuItem("<html><p>Hard 20x20</p></html>");

	        }
	        
	        hard.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					config.Difficulty = 20;
					reNewGame();
					
				}
			});

	        difficulty.add(easy);
	        difficulty.add(medium);
	        difficulty.add(hard);
	        
	        
	        game.add(newItem);
	        game.add(difficulty);
	        game.add(exitItem);

	        //Help Menu
	        JMenu help = new JMenu("Help");
	        JMenuItem how = new JMenuItem("How to play ?");
	        how.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					JFrame how = new JFrame("How to play ?");
					how.setBounds(100, 100, 600, 400);

					ImageIcon icon = board.createImageIcon("img/ico.png",
				            "Main frame icon");
					how.setIconImage(icon.getImage());
					how.getContentPane().setLayout(new BoxLayout(how.getContentPane(), BoxLayout.X_AXIS) );
					JLabel helptext2 = new JLabel("<html><div style=\"font-size:116%;\"><b>How to play ?</b></div></html>");
					JLabel helptext = new JLabel(
							"<html>"
							
							+ "<p>Each player starts the game on the very corner of the board and with the score of 30<br> Players can only move on both vertical and horizontal lines"
							+ "<p>Players should go along the path and gather as many as stars they can, but heads up for the explosive bombs ! </p>"
							+ "<p>Each star has a random score between 1 to 5 and each bomb has a random -score from 5 to 20 </p>"
							+ "<p><b>Terms of winning:</b></p>"
							+ "<p>1-After all the stars have been picked, winner would be the one with higher score</p>"
							+ "<p>2-If a player reachs the score of 0 or under it, the other player would win the game</p>"
							
							+ "</html>");
					
					helptext.setFont(new Font("helptext", 0, 18));
					how.getContentPane().add(helptext2);
					how.getContentPane().add(helptext);
					how.setVisible(true);
				}
	        });
	        JMenuItem about = new JMenuItem("About Star Seeker");
	        about.addActionListener(new ActionListener(){
	        	@Override
	        	public void actionPerformed(ActionEvent e){
	        		JFrame about = new JFrame("About Star Seeker");
	        		about.setBounds(100, 100, 600, 200);

					ImageIcon icon = board.createImageIcon("img/ico.png",
				            "Main frame icon");
					about.setIconImage(icon.getImage());
					about.getContentPane().setLayout(new BoxLayout(about.getContentPane(), BoxLayout.Y_AXIS) );
					JLabel abouttext2 = new JLabel("<html><br><div style=\"font-size:116%;\"><b>&nbsp About Star Seeker: </b></div></html>");
	        		JLabel aboutText = new JLabel("<html><br><p>© 2016 ArashSM79 All rights reserved</p></html>");
	        		about.getContentPane().add(abouttext2);
	        		about.getContentPane().add(new JLabel("<html> "
	        				+ "<p>&nbsp This program has been made for non-marketing intentions</p>"
	        				+ "<p>&nbsp Contact me with: </p>"
	        				+ "<p>&nbsp Tel: +989337037698"
	        				+ "<p>&nbsp Email: arashsm79@yahoo.com"
	        				+ "</html>"));

	        		about.getContentPane().add(aboutText);
					about.setVisible(true);

	        	}
	        });
	            	        
	        help.add(how);
	        help.add(about);
	        
	        
	        menubar.add(game);
	        menubar.add(help);

	        return menubar;
	    }
	
	public static void reNewGame() {
    	mainFrame.dispose();
		GameState.reset();
		mainFrame = new Main();		
	}

	public static String getGameDifficulty(){
		String getGameDifficulty = null;
		while(true){
			if(getGameDifficulty==null){
				getGameDifficulty = (String) JOptionPane.showInputDialog(null,"Choose game's difficulty ", "Game Setting", JOptionPane.INFORMATION_MESSAGE,
						null,
						gameDifficulty,
						gameDifficulty[0]);
			}else{
				break;
			}	
		}
		return getGameDifficulty;
	}
	
	
	public static void getColors(){
		String[] newColors = new String[2];
		
		while(true){
			if(newColors[0]==null){
				newColors[0] = (String) JOptionPane.showInputDialog(null,"Choose Player One's Color :", "Game Setting", JOptionPane.INFORMATION_MESSAGE,
						null,
						color,
						color[0]);
			}else{
				break;
			}	
		}
		
		while(true){
			if(newColors[1]==null){
				newColors[1] = (String) JOptionPane.showInputDialog(null,"Choose Player Two's Color :", "Game Setting", JOptionPane.INFORMATION_MESSAGE,
						null,
						color,
						color[0]);
				if(newColors[1]!=null){

					if(newColors[1].equals(newColors[0])){
						JOptionPane.showMessageDialog(null, "Choose a Diffrent color !", "Ops :( ", JOptionPane.WARNING_MESSAGE);
						newColors[1] = null;
					}
				}
			}else{
				break;
			}	
		}
		
		config.PlayerColor = newColors[0].toLowerCase();
		config.Player2Color = newColors[1].toLowerCase();
		
	}
	

}
//frame.setLocationRelativeTo(null);
//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
