package game;


import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;


public class Player extends JButton {



	private ImageIcon ico;
	private int xAxis;
	private int yAxis;
	private String color;
	static int count = 0;
	private String name;
	
	public Player(String color, int xAxis, int yAxis){
		this.color = color;
		setImageIcon();
		setText("");
		putClientProperty("type", "player");
		setyAxis(yAxis);
		setxAxis(xAxis);
		addComponentListener(new ComponentListener() {
			
			@Override
			public void componentShown(ComponentEvent e) {
				// TODO Auto-generated method stub
				
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
		this.setName("player" + count);
		count ++;
		
		
	}
	

	private void setImageIcon(){
		ico = createImageIcon("img/pieces/" + getColor() + ".png",
                "a player with color:" + getColor());

		setIcon(ico);
	}
	
	
	
	
	private ImageIcon createImageIcon(String path, String description) {
		java.net.URL imgURL = getClass().getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL, description);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}
	
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getxAxis() {
		return this.xAxis;
	}

	public void setxAxis(int xAxis) {
		this.xAxis = xAxis;
	}

	public int getyAxis() {
		return this.yAxis;
	}

	public void setyAxis(int yAxis) {
		this.yAxis = yAxis;
	}
	
	public String getColor(){
		return this.color;
	}
	public void setColor(String color){
		this.color = color;
	}


	public ImageIcon getIco() {
		return ico;
	}


	public void setIco(ImageIcon ico) {
		this.ico = ico;
	}
	
}//End of class
