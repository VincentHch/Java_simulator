package simulateurBalle;

import gui.*;

/**
 * Classe implémentant le simulateur.
 *
 * @version 2.0 
 * 
 * @author Vincent Hachin  
 **/

public class BallsSimulator implements Simulable {
	
	private Balls balls;
	private GUISimulator gui;
	
	public BallsSimulator(GUISimulator gui) {
		this.balls = new Balls();
		this.gui = gui;
	}
	
	@Override
	public void next () {
		this.balls.translate(this.gui.getWidth(), this.gui.getHeight());
		//System.out.println(this.balls);
		this.balls.draw(this.gui);
	}
	
	
	@Override
	public void restart () {
		this.balls.reInit();
	}
}
