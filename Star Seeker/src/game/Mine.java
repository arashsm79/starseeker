package game;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Mine extends JButton {

	private ImageIcon ico;
	private int xAxis;
	private int yAxis;
	private int score = 0;
	static int count = 1;
	
	public Mine(){
		
		setRandomPosition();
		setImageIcon();
		putClientProperty("type", "bomb");
		putClientProperty("score", getScore());
		addActionListener(new MoveListener());
		setText("");
		addComponentListener(new ComponentListener() {
					
					@Override
					public void componentShown(ComponentEvent e) {
						
					}
					
					@Override
					public void componentResized(ComponentEvent e) {
						setIcon(new ImageIcon(getIco().getImage().getScaledInstance(e.getComponent().getWidth(), e.getComponent().getHeight(), Image.SCALE_SMOOTH)));
						
					}
					
					@Override
					public void componentMoved(ComponentEvent e) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void componentHidden(ComponentEvent e) {
						// TODO Auto-generated method stub
						
					}
				});
		this.setName("Mine" + count);
		count ++;
		
		
	}
	
	
	private void setRandomPosition(){
		Random rand = new Random();
		int row = Main.row;
	    
	    setxAxis(0 + rand.nextInt(((row - 1) - 0) + 1));
	    setyAxis(0 + rand.nextInt(((row - 1) - 0) + 1));
	    
	    putClientProperty("xAxis", getxAxis());
	    putClientProperty("yAxis", getyAxis());
	    
	    setBackground(Color.decode("#ffeedd"));

	}
	
	
	
	private void setImageIcon(){
		Random rand = new Random();
	    int randomNum;
	    
		randomNum = 1 + rand.nextInt((3 - 1) + 1);
		if(randomNum == 1){
			setScore(1);
		}else if(randomNum == 2){
			setScore(5);
		}else if(randomNum == 3){
			setScore(10);
		}
		
		ico = createImageIcon("img/bombs/bomb"+ randomNum + ".png",
			"a mine");
		
		setIcon(ico);
	}
	
	
	

	protected ImageIcon createImageIcon(String path, String description) {
		java.net.URL imgURL = getClass().getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL, description);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}
	
	
	
	
	public int getxAxis() {
		return xAxis;
	}
	
	public void setxAxis(int xAxis) {
		this.xAxis = xAxis;
	}
	
	public int getyAxis() {
		return yAxis;
	}
	
	public void setyAxis(int yAxis) {
		this.yAxis = yAxis;
	}
	
	
	public int getScore() {
		return score;
	}
	
	
	public void setScore(int score) {
		this.score = score;
	}
	
	
	public ImageIcon getIco() {
		return ico;
	}
	
	
	public void setIco(ImageIcon ico) {
		this.ico = ico;
	}

}//End of class
