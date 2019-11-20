package id.ac.its.nada.movingsprites;

import java.awt.EventQueue;
import javax.swing.JFrame;

public class AsteroidEx extends JFrame {
	
	public AsteroidEx() {
		
		initUI();
	}
	
	private void initUI() {
	        
		add(new Board());
		setResizable(false);
		pack();
	        
		setTitle("Shooting Missiles with Asteroid");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 }

	public static void main(String[] args) {

		EventQueue.invokeLater(() -> {
            AsteroidEx ex = new AsteroidEx();
            ex.setVisible(true);
        });
	}

}
