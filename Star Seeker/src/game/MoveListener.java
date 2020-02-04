package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class MoveListener implements ActionListener{


	@Override
	public void actionPerformed(ActionEvent e) {
		if(!config.isTurnFinished){
			return;
		}
		JButton btn = (JButton) e.getSource();
		int x = (int) btn.getClientProperty("xAxis");
		int y = (int) btn.getClientProperty("yAxis");
		Main.board.revalidate();
		Main.board.repaint();
		
		

		if(config.turn){
			//Player 1

			int xPlayer;
			int yPlayer;
			
			xPlayer = (int) Main.board.getPlayer().getxAxis();
			yPlayer = (int) Main.board.getPlayer().getyAxis();
			
			if(x == xPlayer){
				int moveOnY = y - yPlayer;
				if(moveOnY < 0){
					moveOnYminus(x, y, yPlayer);

				}else if(moveOnY > 0){
					moveOnYpossitive(x, y, yPlayer);
				}else{
					JOptionPane.showMessageDialog(null, "Erorr", "Ops ! :( ", JOptionPane.WARNING_MESSAGE);

				}

			}else if(y == yPlayer){
				int moveOnX = x - xPlayer;
				if(moveOnX < 0){
					moveOnXminus(x, y, xPlayer);

				}else if(moveOnX > 0){
					moveOnXpossitive(x, y, xPlayer);
				}else{
					JOptionPane.showMessageDialog(null, "Erorr", "Ops ! :( ", JOptionPane.WARNING_MESSAGE);
				}
			}else{
				JOptionPane.showMessageDialog(null, "Select a valid place !", "Error :( ", JOptionPane.WARNING_MESSAGE);

			}

				
			
		}else {
			//Player 2

			
			int xPlayer2;
			int yPlayer2;
			
			xPlayer2 = (int) Main.board.getPlayer2().getxAxis();
			yPlayer2 = (int) Main.board.getPlayer2().getyAxis();
			
			if(x == xPlayer2){
				int moveOnY = y - yPlayer2;
				if(moveOnY < 0){
					moveOnY2minus(x, y, yPlayer2);

				}else if(moveOnY > 0){
					moveOnY2possitive(x, y, yPlayer2);
				}else{
					JOptionPane.showMessageDialog(null, "Ops !", "Error :( ", JOptionPane.WARNING_MESSAGE);

				}

			}else if(y == yPlayer2){
				int moveOnX = x - xPlayer2;
				if(moveOnX < 0){
					moveOnX2minus(x, y, xPlayer2);

				}else if(moveOnX > 0){
					moveOnX2possitive(x, y, xPlayer2);
				}else{
					JOptionPane.showMessageDialog(null, "Ops !", "Error :( ", JOptionPane.WARNING_MESSAGE);
				}
			}else{
				JOptionPane.showMessageDialog(null, "Select a valid place !", "Error :( ", JOptionPane.WARNING_MESSAGE);

			}	
		}

		ScoreBoard.reNewScores();		
		ScoreBoard.checkStars();		
		
	}
	
	
	
	public void checkCell(JButton nextBtn, int playerNumber){
		if(nextBtn.getClientProperty("type").equals("bomb")){
			if(playerNumber == 1){ config.PlayerScore -= (int) nextBtn.getClientProperty("score");}
			else{ config.Player2Score -= (int) nextBtn.getClientProperty("score");}
			

		}else if(nextBtn.getClientProperty("type").equals("star")){
			
			if(playerNumber == 1){ config.PlayerScore += (int) nextBtn.getClientProperty("score");}
			else{ config.Player2Score += (int) nextBtn.getClientProperty("score");}
			
			config.StarsCount--;

		}
	}
	
	
	// Player1's stuff....
	public void moveOnYpossitive(int x, int y, int yPlayer){
		int moveOnY = y - yPlayer;
		config.isTurnFinished = false;

		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask(){
			int i = 1;
			@Override
			public void run() {
				
				if(i <= Math.abs(moveOnY)){	
					JButton nextBtn = (JButton) Main.board.Cells[x][yPlayer + i].getComponent(0);
					if(nextBtn.getClientProperty("type").equals("wall") || nextBtn.getClientProperty("type").equals("player")){
						System.out.print(nextBtn.getClientProperty("type"));
		
						JOptionPane.showMessageDialog(null, "You have stepped in a wrong cell :( ", "Ops !", JOptionPane.WARNING_MESSAGE);
						Main.board.getPlayer().setxAxis(x);
						Main.board.getPlayer().setyAxis((yPlayer + i) - 1);
						timer.cancel();
						config.turn = false;
						config.isTurnFinished = true;

						ScoreBoard.setTurn();
					}else{
						if(nextBtn.getClientProperty("type").equals("bomb")){

							playSound("sound/bomb.wav");
						}
						Main.board.Cells[x][yPlayer + i].remove(0);
						Main.board.Cells[x][yPlayer + i].add(Main.board.getPlayer());
						Main.board.Cells[x][yPlayer + i].revalidate();
						Main.board.Cells[x][yPlayer + i].repaint();
						
						Main.board.Cells[x][(yPlayer + i) - 1].add(Main.board.normalBlock(x, (yPlayer + i) - 1), 0);
						Main.board.Cells[x][(yPlayer + i) - 1].revalidate(); 
						Main.board.Cells[x][(yPlayer + i) - 1].repaint();
								
						checkCell(nextBtn, 1);
						Main.board.getPlayer().setxAxis(x);
						Main.board.getPlayer().setyAxis(y);
												
					}
					
					
					i++;
					ScoreBoard.reNewScores();		
					}else{
						timer.cancel();
						config.turn = false;
						config.isTurnFinished = true;

						ScoreBoard.setTurn();
						ScoreBoard.checkStars();

					}
			}
		}, 0, 200);
	}
	
	
	
	
		public void moveOnYminus(int x, int y, int yPlayer){
			int moveOnY = y - yPlayer;
			config.isTurnFinished = false;

			Timer timer = new Timer();	
			timer.scheduleAtFixedRate(new TimerTask(){
				int i = 1;
				@Override
				public void run() {
					if(i <= Math.abs(moveOnY)){	

						JButton nextBtn = (JButton) Main.board.Cells[x][yPlayer - i].getComponent(0);
						
						if(nextBtn.getClientProperty("type").equals("wall") || nextBtn.getClientProperty("type").equals("player")){
							System.out.print(nextBtn.getClientProperty("type"));
							
							JOptionPane.showMessageDialog(null, "You have stepped in a wrong cell :( ", "Ops !", JOptionPane.WARNING_MESSAGE);
							Main.board.getPlayer().setxAxis(x);
							Main.board.getPlayer().setyAxis((yPlayer - i) + 1);
							
							System.out.println("X is: " + Main.board.getPlayer().getxAxis() + Main.board.getPlayer().getyAxis());
							timer.cancel();
							config.turn = false;						
							config.isTurnFinished = true;

							ScoreBoard.setTurn();
						}else{
							if(nextBtn.getClientProperty("type").equals("bomb")){

								playSound("sound/bomb.wav");
							}
							Main.board.Cells[x][yPlayer - i].remove(0);
							Main.board.Cells[x][yPlayer - i].add(Main.board.getPlayer(), 0);
							Main.board.Cells[x][yPlayer - i].revalidate();
							Main.board.Cells[x][yPlayer - i].repaint();
							
							Main.board.Cells[x][(yPlayer - i) + 1].add(Main.board.normalBlock(x, (yPlayer - i) + 1), 0);
							Main.board.Cells[x][(yPlayer - i) + 1].revalidate(); 
							Main.board.Cells[x][(yPlayer - i) + 1].repaint();
											
							checkCell(nextBtn, 1);
																					
							Main.board.getPlayer().setxAxis(x);
							Main.board.getPlayer().setyAxis(y);
						}
		
												
						i++;
						ScoreBoard.reNewScores();		
						}else{
							timer.cancel();
							config.turn = false;
							config.isTurnFinished = true;

							ScoreBoard.setTurn();
							ScoreBoard.checkStars();

						}
				}			
			}, 0, 200);

									
		}
		
		
		
		
		public void moveOnXminus(int x, int y, int xPlayer){
			int moveOnY = x - xPlayer;
			config.isTurnFinished = false;

			Timer timer = new Timer();
			timer.scheduleAtFixedRate(new TimerTask(){
				int i = 1;
				
				@Override
				public void run() {
					
					if(i <= Math.abs(moveOnY)){	
				JButton nextBtn = (JButton) Main.board.Cells[xPlayer - i][y].getComponent(0);
				
				if(nextBtn.getClientProperty("type").equals("wall") || nextBtn.getClientProperty("type").equals("player")){
					System.out.print(nextBtn.getClientProperty("type"));
					
					JOptionPane.showMessageDialog(null, "You have stepped in a wrong cell :( ", "Ops !", JOptionPane.WARNING_MESSAGE);
					Main.board.getPlayer().setxAxis((xPlayer - i) + 1);
					Main.board.getPlayer().setyAxis(y);
					timer.cancel();
					config.turn = false;
					config.isTurnFinished = true;

					ScoreBoard.setTurn();
				}else{
					if(nextBtn.getClientProperty("type").equals("bomb")){

						playSound("sound/bomb.wav");
					}
					Main.board.Cells[xPlayer - i][y].remove(0);
					Main.board.Cells[xPlayer - i][y].add(Main.board.getPlayer(), 0);
					Main.board.Cells[xPlayer - i][y].revalidate();
					Main.board.Cells[xPlayer - i][y].repaint();
					
					
					Main.board.Cells[(xPlayer - i) + 1][y].add(Main.board.normalBlock((xPlayer - i) + 1, y), 0);
					Main.board.Cells[(xPlayer - i) + 1][y].revalidate(); 
					Main.board.Cells[(xPlayer - i) + 1][y].repaint();
					
					
					checkCell(nextBtn, 1);

					
					
					Main.board.getPlayer().setxAxis(x);
					Main.board.getPlayer().setyAxis(y);
				}
				i++;
				ScoreBoard.reNewScores();		
				}else{
					timer.cancel();
					config.isTurnFinished = true;

					config.turn = false;
					ScoreBoard.setTurn();
					ScoreBoard.checkStars();

				}
		}
	}, 0, 200);

		}
		
		
		
		
		public void moveOnXpossitive(int x, int y, int xPlayer){
		int moveOnY = x - xPlayer;
		config.isTurnFinished = false;

		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask(){
			int i = 1;
			@Override
			public void run() {
				if(i <= Math.abs(moveOnY)){						JButton nextBtn = (JButton) Main.board.Cells[xPlayer + i][y].getComponent(0);
					
					if(nextBtn.getClientProperty("type").equals("wall") || nextBtn.getClientProperty("type").equals("player")){
						System.out.print(nextBtn.getClientProperty("type"));
						
						JOptionPane.showMessageDialog(null, "You have stepped in a wrong cell :( ", "Ops !", JOptionPane.WARNING_MESSAGE);
						Main.board.getPlayer().setxAxis((xPlayer + i) - 1);
						Main.board.getPlayer().setyAxis(y);
						timer.cancel();
						config.turn = false;
						ScoreBoard.setTurn();
						config.isTurnFinished = true;

					}else{
						if(nextBtn.getClientProperty("type").equals("bomb")){

							playSound("sound/bomb.wav");
						}
						Main.board.Cells[xPlayer + i][y].remove(0);
						Main.board.Cells[xPlayer + i][y].add(Main.board.getPlayer(), 0);
						Main.board.Cells[xPlayer + i][y].revalidate();
						Main.board.Cells[xPlayer + i][y].repaint();
			
						
						Main.board.Cells[(xPlayer + i) - 1][y].add(Main.board.normalBlock((xPlayer + i) - 1, y), 0);
						Main.board.Cells[(xPlayer + i) - 1][y].revalidate(); 
						Main.board.Cells[(xPlayer + i) - 1][y].repaint();
						
						checkCell(nextBtn, 1);

						
						
						Main.board.getPlayer().setxAxis(x);
						Main.board.getPlayer().setyAxis(y);
					}
					i++;
					ScoreBoard.reNewScores();		
					}else{
						timer.cancel();
						config.turn = false;
						config.isTurnFinished = true;

						ScoreBoard.setTurn();
						ScoreBoard.checkStars();

					}
			}
		}, 0, 200);

		}
//End of player1's stuff
		
		
		
		
//Player2's Stuff
		
		public void moveOnY2possitive(int x, int y, int yPlayer){
			int moveOnY = y - yPlayer;
			config.isTurnFinished = false;

			Timer timer = new Timer();
			timer.scheduleAtFixedRate(new TimerTask(){
				int i = 1;
				@Override
				public void run() {
					if(i <= Math.abs(moveOnY)){					JButton nextBtn = (JButton) Main.board.Cells[x][yPlayer + i].getComponent(0);
				
				if(nextBtn.getClientProperty("type").equals("wall") || nextBtn.getClientProperty("type").equals("player")){
					System.out.print(nextBtn.getClientProperty("type"));

					JOptionPane.showMessageDialog(null, "You have stepped in a wrong cell :( ", "Ops !", JOptionPane.WARNING_MESSAGE);
					Main.board.getPlayer2().setxAxis(x);
					Main.board.getPlayer2().setyAxis((yPlayer + i) - 1);
					timer.cancel();
					config.turn = true;
					config.isTurnFinished = true;

					ScoreBoard.setTurn();
				}else{
					if(nextBtn.getClientProperty("type").equals("bomb")){

						playSound("sound/bomb.wav");
					}
					Main.board.Cells[x][yPlayer + i].remove(0);
					Main.board.Cells[x][yPlayer + i].add(Main.board.getPlayer2(), 0);
					Main.board.Cells[x][yPlayer + i].revalidate();
					Main.board.Cells[x][yPlayer + i].repaint();
					
					Main.board.Cells[x][(yPlayer + i) - 1].add(Main.board.normalBlock(x, (yPlayer + i) - 1), 0);
					Main.board.Cells[x][(yPlayer + i) - 1].revalidate(); 
					Main.board.Cells[x][(yPlayer + i) - 1].repaint();
					
					
					checkCell(nextBtn, 2);

					
					
					Main.board.getPlayer2().setxAxis(x);
					Main.board.getPlayer2().setyAxis(y);
				}
				i++;
				ScoreBoard.reNewScores();		
				}else{
					timer.cancel();
					config.turn = true;
					config.isTurnFinished = true;

					ScoreBoard.setTurn();
					ScoreBoard.checkStars();

				}
		}
	}, 0, 200);

		}
		
		
		
		
			public void moveOnY2minus(int x, int y, int yPlayer){
				int moveOnY = y - yPlayer;
				config.isTurnFinished = false;

				Timer timer = new Timer();
				timer.scheduleAtFixedRate(new TimerTask(){
					int i = 1;
					@Override
					public void run() {
						if(i <= Math.abs(moveOnY)){						JButton nextBtn = (JButton) Main.board.Cells[x][yPlayer - i].getComponent(0);
					
					if(nextBtn.getClientProperty("type").equals("wall") || nextBtn.getClientProperty("type").equals("player")){
						System.out.print(nextBtn.getClientProperty("type"));

						JOptionPane.showMessageDialog(null, "You have stepped in a wrong cell :( ", "Ops !", JOptionPane.WARNING_MESSAGE);
						Main.board.getPlayer2().setxAxis(x);
						Main.board.getPlayer2().setyAxis((yPlayer - i) + 1);
						timer.cancel();
						config.isTurnFinished = true;

						config.turn = true;
						ScoreBoard.setTurn();
					}else{
						if(nextBtn.getClientProperty("type").equals("bomb")){

							playSound("sound/bomb.wav");
						}
						Main.board.Cells[x][yPlayer - i].remove(0);
						Main.board.Cells[x][yPlayer - i].add(Main.board.getPlayer2(), 0);
						Main.board.Cells[x][yPlayer - i].revalidate();
						Main.board.Cells[x][yPlayer - i].repaint();
						
						Main.board.Cells[x][(yPlayer - i) + 1].add(Main.board.normalBlock(x, (yPlayer - i) + 1), 0);
						Main.board.Cells[x][(yPlayer - i) + 1].revalidate(); 
						Main.board.Cells[x][(yPlayer - i) + 1].repaint();
						
						
						checkCell(nextBtn, 2);

						
						
						Main.board.getPlayer2().setxAxis(x);
						Main.board.getPlayer2().setyAxis(y);
					}
					i++;
					ScoreBoard.reNewScores();		
					}else{
						timer.cancel();
						config.turn = true;
						config.isTurnFinished = true;

						ScoreBoard.setTurn();
						ScoreBoard.checkStars();

					}
			}
		}, 0, 200);

				
	}
			
			
			
			
			public void moveOnX2minus(int x, int y, int xPlayer){
				int moveOnY = x - xPlayer;
				config.isTurnFinished = false;

				Timer timer = new Timer();
				timer.scheduleAtFixedRate(new TimerTask(){
					int i = 1;
					@Override
					public void run() {
						if(i <= Math.abs(moveOnY)){						JButton nextBtn = (JButton) Main.board.Cells[xPlayer - i][y].getComponent(0);
					
					if(nextBtn.getClientProperty("type").equals("wall") || nextBtn.getClientProperty("type").equals("player")){
						System.out.print(nextBtn.getClientProperty("type"));
						
						JOptionPane.showMessageDialog(null, "You have stepped in a wrong cell :( ", "Ops !", JOptionPane.WARNING_MESSAGE);
						Main.board.getPlayer2().setxAxis((xPlayer - i));
						Main.board.getPlayer2().setyAxis(y);
						timer.cancel();
						config.turn = true;
						config.isTurnFinished = true;

						ScoreBoard.setTurn();
					}else{
						if(nextBtn.getClientProperty("type").equals("bomb")){

							playSound("sound/bomb.wav");
						}
						Main.board.Cells[xPlayer - i][y].remove(0);
						Main.board.Cells[xPlayer - i][y].add(Main.board.getPlayer2(), 0);
						Main.board.Cells[xPlayer - i][y].revalidate();
						Main.board.Cells[xPlayer - i][y].repaint();
						
						
						Main.board.Cells[(xPlayer - i) + 1][y].add(Main.board.normalBlock((xPlayer - i) + 1, y), 0);
						Main.board.Cells[(xPlayer - i) + 1][y].revalidate(); 
						Main.board.Cells[(xPlayer - i) + 1][y].repaint();
						
						
						checkCell(nextBtn, 2);

						
						
						Main.board.getPlayer2().setxAxis(x);
						Main.board.getPlayer2().setyAxis(y);
					}
					i++;
					ScoreBoard.reNewScores();

					}else{
						timer.cancel();
						config.turn = true;
						config.isTurnFinished = true;

						ScoreBoard.setTurn();
						ScoreBoard.checkStars();

					}
			}
		}, 0, 200);

	}
			
			
			
			
			public void moveOnX2possitive(int x, int y, int xPlayer){
			int moveOnY = x - xPlayer;
			config.isTurnFinished = false;

			Timer timer = new Timer();
			timer.scheduleAtFixedRate(new TimerTask(){
				int i = 1;
				@Override
				public void run() {
					if(i <= Math.abs(moveOnY)){							JButton nextBtn = (JButton) Main.board.Cells[xPlayer + i][y].getComponent(0);
						
						if(nextBtn.getClientProperty("type").equals("wall") || nextBtn.getClientProperty("type").equals("player")){
							System.out.print(nextBtn.getClientProperty("type"));
							
							JOptionPane.showMessageDialog(null, "You have stepped in a wrong cell :( ", "Ops !", JOptionPane.WARNING_MESSAGE);
							Main.board.getPlayer2().setxAxis((xPlayer + i) - 1);
							Main.board.getPlayer2().setyAxis(y);
							timer.cancel();
							config.turn = true;
							config.isTurnFinished = true;

							ScoreBoard.setTurn();
						}else{
							if(nextBtn.getClientProperty("type").equals("bomb")){

								playSound("sound/bomb.wav");
							}
							Main.board.Cells[xPlayer + i][y].remove(0);
							Main.board.Cells[xPlayer + i][y].add(Main.board.getPlayer2(), 0);
							Main.board.Cells[xPlayer + i][y].revalidate();
							Main.board.Cells[xPlayer + i][y].repaint();
				
							
							Main.board.Cells[(xPlayer + i) - 1][y].add(Main.board.normalBlock((xPlayer + i) - 1, y), 0);
							Main.board.Cells[(xPlayer + i) - 1][y].revalidate(); 
							Main.board.Cells[(xPlayer + i) - 1][y].repaint();
							
							checkCell(nextBtn, 2);

							
							Main.board.getPlayer2().setxAxis(x);
							Main.board.getPlayer2().setyAxis(y);
						}
						i++;
						ScoreBoard.reNewScores();		
						}else{
							timer.cancel();
							config.isTurnFinished = true;

							config.turn = true;
							ScoreBoard.setTurn();
							ScoreBoard.checkStars();

						}
				}
			}, 0, 200);

			}
			
			
			
			
		public static void waitTime(long millisecond){
	        long max = millisecond;
	        for(long i = 0;  i < max; i++){
	            for(long j = 0;  j < max; j++){
	                 
	            }
	        }
	    }
		
		
		
		
		void playSound(String soundFile) {
			try
			  {
			    InputStream inputStream = getClass().getResourceAsStream(soundFile);
			    AudioStream audioStream = new AudioStream(inputStream);
			    AudioPlayer.player.start(audioStream);
			  }catch (Exception e){
				  e.printStackTrace();
			  }
		}
}//End of class


