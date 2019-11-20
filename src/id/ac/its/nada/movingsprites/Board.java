package id.ac.its.nada.movingsprites;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener {
	
	private Timer timer;
	private SpaceShip spaceShip;
	private List<Asteroid> asteroids;
	private final int ICRAFT_X = 40;
	private final int ICRAFT_Y = 60;
	private final int B_WIDTH = 400;
	private final int B_HEIGHT = 300;
	private final int DELAY = 10;
	
	private final int[][] pos = {
			{2500, 59},
	        {780, 109}, {580, 139}, {680, 239},
	        {790, 259}, {760, 50}, {790, 150},
	        {980, 209}, {560, 45}, {510, 70},
	        {930, 159}, {590, 80},
	        {990, 30}, {660, 50}, {540, 90},
	        {860, 20}, {740, 180},
	        {490, 200}, {700, 30}
	};
	
	public Board() {
		
		initBoard();
	}
	
	private void initBoard() {
		
		addKeyListener(new TAdapter());
		setBackground(Color.black);
		setFocusable(true);
		
		setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
		
		spaceShip = new SpaceShip(ICRAFT_X, ICRAFT_Y);
		
		initAsteroids();
		
		timer = new Timer(DELAY, this);
		timer.start();
	}
	
	public void initAsteroids() {
		
		asteroids = new ArrayList<>();
		
		for (int[] p : pos) {
			asteroids.add(new Asteroid(p[0], p[1]));
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		doDrawing(g);
		
		Toolkit.getDefaultToolkit().sync();
	}
	
	private void doDrawing(Graphics g) {
		
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.drawImage(spaceShip.getImage(), spaceShip.getX(),
				spaceShip.getY(), this);
		
		List<Missile> missiles = spaceShip.getMissiles();
		
		for (Missile missile : missiles) {
			
			g2d.drawImage(missile.getImage(), missile.getX(),
					missile.getY(), this);
		}
		
		for (Asteroid asteroid : asteroids) {
			
			g2d.drawImage(asteroid.getImage(), asteroid.getX(),
					asteroid.getY(), this);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		updateMissiles();
		updateSpaceShip();
		updateAsteroids();
		
		repaint();
	}

	private void updateMissiles() {
		
		List<Missile> missiles = spaceShip.getMissiles();
		
		for (int i = 0; i < missiles.size(); i++) {
			
			Missile missile = missiles.get(i);
			
			if (missile.isVisible()) {
				
				missile.move();
			}
			else {
				
				missiles.remove(i);
			}
		}
	}
	
	private void updateSpaceShip() {
		
		spaceShip.move();
	}
	
	private void updateAsteroids() {
		
		for (int i =0; i < asteroids.size(); i++) {
			
			Asteroid a = asteroids.get(i);
			
			if (a.isVisible()) {
				a.move();
			}
			else {
				asteroids.remove(i);
			}
		}
	}
	
	private class TAdapter extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {
			
			spaceShip.keyPressed(e);
		}

		@Override
		public void keyReleased(KeyEvent e) {
			spaceShip.keyReleased(e);
		}
	}
	
}
