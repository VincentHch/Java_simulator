package GameOfImmigr;

import gui.*;
import java.awt.Color;

/**
 * Classe implémentant le simulateur.
 *
 * @version 2.0 
 * 
 * @author Vincent Hachin  
 **/

public class GridSimulator implements Simulable {
	
	private Grid grid;
	private GUISimulator gui;
	
	public GridSimulator(GUISimulator gui) {
		this.grid = new Grid(gui.getWidth()/10, gui.getHeight()/10, 4);
		this.gui = gui;
	}
	
	@Override
	public void next () {
		this.grid.etat_suivant();
		//System.out.println(this.balls);
		this.grid.draw(this.gui);
	}
	
	
	@Override
	public void restart () {
		this.grid.reInit(gui);
		this.grid.draw(this.gui);
	}
}
