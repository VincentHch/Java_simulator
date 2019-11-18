package Boids;

import gui.*;

import java.awt.Color;
//import java.awt.geom.Point2D;

/**
 * Classe principale, instancie et teste un simulateur de balle.
 *
 * @version 2.0 
 * 
 * @author Vincent Hachin  
 **/
public class TestSimulator {

	public static void main(String[] args) {
		
		//Boid b1 = new Boid(new Point2D.Double(1,1), new Point2D.Double(1,-0.1), 3, 5);
		//Boid b2 = new Boid(new Point2D.Double(0,1), new Point2D.Double(1,1), 3, 5);
		//System.out.println(b1.angle_to(b2));
		int height = 500;
		int width = 500;
		GUISimulator gui = new GUISimulator(width, height , Color.BLACK);
		
		int n = 5;
		BoidsSimulator simulator = new BoidsSimulator(n, gui);
		for (int i=1; i<=0; i++) {
			simulator.addBoids(100, 20, 5, 2.62, i, Color.RED, "o");
		}
		simulator.addBoids(500, 20, 1, 2.62, 1, Color.RED, "o"); //Les élèves
		simulator.addBoids(100, 30, 5, 2, 3, Color.GREEN, "{}"); //Les profs
		simulator.addBoids(20, 50, 20, 2, 1, Color.WHITE, "CRS Méchant");
		
		simulator.init_events();
		
		gui.setSimulable(simulator);
	}

}