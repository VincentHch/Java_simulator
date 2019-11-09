package tpl;

import java.awt.*;
import gui.*;

public class Particle {
	
	Point pos;
	Point pos_init;
	int taille;
	
	public Particle() {
		this.pos = new Point(0,0);
		this.pos_init = new Point(this.pos);
		this.taille = 5;
	}
	
	/** Creation d'une particule de coordonees nulles
	 * 
	 * @param taille : taille de la particule
	 */
	public Particle(int taille) {
		this.pos = new Point(0,0);
		this.pos_init = new Point(this.pos);
		this.taille = taille;
	}
	
	/**
	public Particle() {
		this.pos = new Point(0,0);
		this.pos_init = new Point(this.pos);
		this.taille = 5;
	}
	*/
		
	/** Creation d'une particule
	 * @param int x : coordonnée x de la particule
	 * @param int y : coordonnée y de la particule
	 * @param int taille : taille de la particule
	 **/
	public Particle(int x, int y, int taille) {
		this.pos = new Point(x, y);
		this.pos_init = new Point(this.pos);
		this.taille = taille;
	}
	
	public Particle(Point p, int taille) {
		this.pos = new Point(p);
		this.pos_init = new Point(this.pos);
		this.taille = taille;
	}
	
	/** Creation d'une particule au meme endroit que la particule p
	 * 
	 * @param Particle p
	 */
	public Particle(Particle p) {
		this.pos = new Point(p.pos);
		this.pos_init = new Point(this.pos);
		this.taille = p.taille;
	}
	
	/** Reinitialise la particule
	 * 
	 */
	public void reInit() {
		this.pos = new Point(this.pos_init);
	}
	
	/** Renvoie une particule corresondant à l'étape suivante
	 * 
	 * @param env : un environnement
	 * @return une nouvelle particule
	 */
	public Particle next(Environment env) {
		return new Particle(this);
	}
	
	public Particle next(GUISimulator gui) {
		return new Particle(this);
	}
	
	public int get_x() {
		return this.pos.x;
	}
	
	public int get_y() {
		return this.pos.y;
	}
	
	public void draw(GUISimulator gui) {
		gui.addGraphicalElement(new Oval(this.get_x(), this.get_y(), Color.RED, Color.RED, this.taille));
	}

}
