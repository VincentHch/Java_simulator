package simulateurBalle;

import gui.*;

/**
 * Classe testant les balles, incompatible avec la v2.0.
 *
 * @version 1.0 
 * 
 * @author Vincent Hachin  
 **/
import java.awt.Color;

public class TestBalls {
	public void main1(String[] args) {
		//GUISimulator ig = new GUISimulator(200, 200, Color.BLACK);
		Balls balls1 = new Balls();
		System.out.println(balls1);
		System.out.println("Translation de 5,-6 :");
		balls1.translate(5, -6);
		System.out.println(balls1);
		System.out.println("reset des positions :");
		balls1.reInit();
		System.out.println(balls1);
	}
}
