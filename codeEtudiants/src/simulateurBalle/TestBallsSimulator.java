package simulateurBalle;

import gui.*;

import java.awt.Color;

/**
 * Classe principale, instancie et teste un simulateur de balle.
 *
 * @version 2.0 
 * 
 * @author Vincent Hachin  
 **/
public class TestBallsSimulator {

	public static void main(String[] args) {
		
		int height = 500;
		int width = 500;
		GUISimulator gui = new GUISimulator(width, height , Color.BLACK );
		gui.setSimulable(new BallsSimulator(gui));
	}

}
