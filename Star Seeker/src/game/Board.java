package game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Board extends JPanel {
		



		private int ROW;
		private int COL;
		private int Xsize = 700;
		private int Ysize = Xsize;
		private Player player, player2;
		JPanel[][] Cells;
		
		
		public Board(){
		
		this.ROW = Main.row;
		this.COL = this.ROW;
	    setLayout(new GridLayout(ROW, COL));
	    setSize(Xsize, Ysize);
	    
	    Cells = new JPanel[ROW][COL];


		for (int i = 0; i < Cells.length; i++) {
			for (int j = 0; j < Cells[i].length; j++) {

				 Cells[i][j] = new JPanel();
				 Cells[i][j].setLayout(new BorderLayout());
				 Cells[i][j].add(normalBlock(i, j));
			    
			     

		         add(Cells[i][j]);
				
	      
			}
		}
		
		}
		//30% star
		//10% wall
		//10% Bomb

		public void setWallsOnBoard(){
			int row = Main.row;
			int wallpercentage = Math.round(((row * row) *10) / 100);
			System.out.println("walls" + wallpercentage);

			for(int i = 1; i <= wallpercentage; i++){
				Wall wall = new Wall();
				if((wall.getxAxis() == (row - 2) && wall.getyAxis() == (0)) 
						|| (wall.getxAxis() == (row-1) && wall.getyAxis() == (1)) 
						|| (wall.getxAxis() == (row-1) && wall.getyAxis() == (row-2))
						|| (wall.getxAxis() == (row - 2) && wall.getyAxis() == (row-1)))
				{
					i--;
					}else{
					JButton btn = (JButton) Cells[wall.getxAxis()][wall.getyAxis()].getComponent(0);
					if(btn.getClientProperty("type").equals("block")){
						Cells[wall.getxAxis()][wall.getyAxis()].add(wall, 0);
						Cells[wall.getxAxis()][wall.getyAxis()].revalidate();
						Cells[wall.getxAxis()][wall.getyAxis()].repaint();
						
					}else{
						i--;
					}
				}				
			}
		}
		public void setStarsOnBoard(){
			int row = Main.row;
			int starpercentage = Math.round(((row * row) *30) / 100);
			config.StarsCount = starpercentage;
			System.out.println("stars" + starpercentage);

			for(int i = 1; i <= starpercentage; i++){
				Star star = new Star();
				JButton btn = (JButton) Cells[star.getxAxis()][star.getyAxis()].getComponent(0);
				if(btn.getClientProperty("type").equals("block")){
					Cells[star.getxAxis()][star.getyAxis()].add(star, 0);
					Cells[star.getxAxis()][star.getyAxis()].revalidate();
					Cells[star.getxAxis()][star.getyAxis()].repaint();
				}else{
					i--;
				}
			}
		}
		public void setBombsOnBoard(){
			int row = Main.row;
			int minepercentage = Math.round(((row * row) *10) / 100);
			System.out.println("mines" + minepercentage);
			for(int i = 1; i <= minepercentage; i++){
				Mine mine = new Mine();
				JButton btn = (JButton) Cells[mine.getxAxis()][mine.getyAxis()].getComponent(0);
				if(btn.getClientProperty("type").equals("block")){
					Cells[mine.getxAxis()][mine.getyAxis()].add(mine, 0);
					Cells[mine.getxAxis()][mine.getyAxis()].revalidate();
					Cells[mine.getxAxis()][mine.getyAxis()].repaint();
				}else{
					i--;
				}
				
			}
		}
		
		public ImageIcon createImageIcon(String path, String description) {
			java.net.URL imgURL = getClass().getResource(path);
			if (imgURL != null) {
				return new ImageIcon(imgURL, description);
			} else {
				System.err.println("Couldn't find file: " + path);
				return null;
			}
		}
		
		public void setPlayersOnBoard(){
			
			int row = Main.row;
		    player = new Player(config.PlayerColor, row - 1, row - 1);
		    Cells[row - 1][row - 1].add(player, 0);
		    Cells[row - 1][row - 1].revalidate();
			Cells[row - 1][row - 1].repaint();
	        	        
		    player2 = new Player(config.Player2Color, row - 1, 0);
			Cells[row - 1][0].add(player2, 0);
		    Cells[row - 1][0].revalidate();
			Cells[row - 1][0].repaint();		
			
		}
		
		public JButton normalBlock(int i, int j){
			JButton Button = new JButton();
			 Button.setBackground(Color.decode("#ffeedd"));
		     Button.putClientProperty("type", "block");
		     Button.putClientProperty("xAxis", i);

		     Button.putClientProperty("yAxis", j);
		     Button.addActionListener(new MoveListener());
		     return Button;
		     
		}		

		public int getXsize() {
			return Xsize;
		}


		public void setXsize(int xsize) {
			Xsize = xsize;
		}


		public int getYsize() {
			return Ysize;
		}


		public void setYsize(int ysize) {
			Ysize = ysize;
		}

		public Player getPlayer(){
			return this.player;
		}
		public Player getPlayer2(){
			return this.player2;
		}
				
	}
