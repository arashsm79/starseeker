package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class ScoreBoard extends JPanel{


	public static JPanel[] components = new JPanel[6];
	public static ImageIcon icon;
	public static ImageIcon icon2;
	public ScoreBoard(){
		super(new GridLayout(6, 1));
		
		for(int i = 0; i < components.length;i++){
			components[i] = new JPanel();
			components[i].setLayout(new GridBagLayout());
			add(components[i]);
		}
		
		
		//Player 1's Score
		
		icon = createImageIcon("img/pieces/" + config.PlayerColor + ".png");

		JLabel j1 = new JLabel(icon);
		
		components[0].add(j1);
		
		components[1].add(new JLabel(String.valueOf(config.PlayerScore)),0);
		components[1].getComponent(0).setFont(new Font("big", 1, 75));
		
		switch(config.PlayerColor){
		case "red": 
			components[0].setBackground(Color.decode(config.red));
			components[1].setBackground(Color.decode(config.red));
			break;
		case "blue": 
			components[0].setBackground(Color.decode(config.blue));
			components[1].setBackground(Color.decode(config.blue));
			break;
		case "green": 
			components[0].setBackground(Color.decode(config.green));
			components[1].setBackground(Color.decode(config.green));
			break;
		case "yellow": 
			components[0].setBackground(Color.decode(config.yellow));
			components[1].setBackground(Color.decode(config.yellow));
			break;

		}
		

		// Player 2 icon
		
		icon2 = createImageIcon("img/pieces/" + config.Player2Color + ".png");

		JLabel j2 = new JLabel(icon2);
		components[2].add(j2);		
		components[3].add(new JLabel(String.valueOf(config.Player2Score)), 0);
		components[3].getComponent(0).setFont(new Font("big", 1, 75));
		
		switch(config.Player2Color){
		case "red": 
			components[2].setBackground(Color.decode(config.red));
			components[3].setBackground(Color.decode(config.red));
			break;
		case "blue": 
			components[2].setBackground(Color.decode(config.blue));
			components[3].setBackground(Color.decode(config.blue));
			break;
		case "green": 
			components[2].setBackground(Color.decode(config.green));
			components[3].setBackground(Color.decode(config.green));
			break;
		case "yellow": 
			components[2].setBackground(Color.decode(config.yellow));
			components[3].setBackground(Color.decode(config.yellow));
			break;

		}
		
		// Turn 
		components[4].add(new JLabel());
		String PlayerName = config.PlayerColor.toUpperCase().charAt(0) + config.PlayerColor.substring(1);
		
		((JLabel) components[4].getComponent(0)).setText("<html><p style=\"color:"+config.PlayerColor+";font-size:102%;\"><b><i>"+PlayerName+"'s</i></b></p><div style=\"font-size:102%;text-align:center;\"><i>turn</i></div></html>");		
		components[4].getComponent(0).setFont(new Font("turns font", 0, 30));
		components[4].setBackground(Color.decode("#d0d3d4"));
		
		components[5].add(new JLabel(icon));
		components[5].setBackground(Color.decode("#d0d3d4"));

		reNewScores();
		
		
		




	}
	
	
	
	
	public static void reNewScores(){
		
		((JLabel) components[1].getComponent(0)).setText(String.valueOf(config.PlayerScore));	
		
		((JLabel) components[3].getComponent(0)).setText(String.valueOf(config.Player2Score));
	
	}
	
	
	
	
	public static void checkStars(){
		if(config.StarsCount <= 0){
			
			if(config.PlayerScore > config.Player2Score){
				JOptionPane.showMessageDialog(null, config.PlayerColor + " Won !", "Horay :)", JOptionPane.INFORMATION_MESSAGE, icon);

			}else if(config.Player2Score > config.PlayerScore){
				JOptionPane.showMessageDialog(null, config.Player2Color + " Won !", "Horay :)", JOptionPane.INFORMATION_MESSAGE, icon2);

			}else{
				JOptionPane.showMessageDialog(null, "Draw ~!~", "Horay :)", JOptionPane.INFORMATION_MESSAGE);

			}
			
			Main.reNewGame();
		}
		if(config.PlayerScore <= 0){
			JOptionPane.showMessageDialog(null, config.Player2Color + " Won !", "Horay :)", JOptionPane.INFORMATION_MESSAGE, icon2);
			Main.reNewGame();

		}else if(config.Player2Score <= 0){
			JOptionPane.showMessageDialog(null, config.PlayerColor + " Won !", "Horay :)", JOptionPane.INFORMATION_MESSAGE, icon);
			Main.reNewGame();

		}
	}
	
	
	
	
	public static void setTurn(){
		
		if(config.turn){
			String PlayerName = config.PlayerColor.toUpperCase().charAt(0) + config.PlayerColor.substring(1);
			((JLabel) components[4].getComponent(0)).setText("<html><p style=\"color:"+config.PlayerColor+";\"><b><i>"+PlayerName+"'s</i></b></p><div style=\"font-size:102%;text-align:center;\"><i>turn</i></div></html>");
			
			((JLabel) components[5].getComponent(0)).setIcon(icon);

		}else{
			String Player2Name = config.Player2Color.toUpperCase().charAt(0) + config.Player2Color.substring(1);
			((JLabel) components[4].getComponent(0)).setText("<html><p style=\"color:"+config.Player2Color+";\"><b><i>"+Player2Name+"'s</i></b></p><div style=\"font-size:102%;text-align:center;\"><i>turn</i></div></html>");

			
			
			((JLabel) components[5].getComponent(0)).setIcon(icon2);
	
		}
	}
	
	
	
	
	private ImageIcon createImageIcon(String path) {
		java.net.URL imgURL = getClass().getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}

}
