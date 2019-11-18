package Boids;


import java.awt.geom.*;
import gui.*;

import java.lang.Math;

/**
 *	Classe représentant un groupe de Boids
 *
 * @version 1.0 
 * 
 * @author Vincent Hachin  
 */

public class Boids {
	
	/** Nombre de Boid */
	private int n;
	/** Tableau des Boid */
	private Boid[] tab_boids;
	
	/** Tableau des Boid au temps 0*/
	private Boid[] tab_boids_init;
	
	/** Couleur des Boid*/
	private java.awt.Color color;
	
	/** Environnement*/
	private GUISimulator gui; 

	/** distance du champ de vision*/
	private double vision;
	
	/** Vitesse max */
	private double v_max;
	
	/** Angle du champ de vision */
	private double angle;
	
	/** Texte utilisé pour afficher les Boid */
	private java.lang.String icone;
	
	/** Temps de réaction du groupe*/
	private int reactionTime;
	
	/** 
	 * Constructeur, créé n Boid placés aléatoirements, initialise les deux tableaux
	 * */
	public Boids(int n, double vision, double v_max, double angle, int reactionTime, java.awt.Color color, java.lang.String icone, GUISimulator gui) {
		this.n = n;
		this.gui = gui;
		/*this.color = new Color((int) (Math.random() * 255),
							   (int) (Math.random() * 255),
							   (int) (Math.random() * 255));
	    */
		this.color = color;
		this.vision = vision;
		this.v_max = v_max;
		this.angle = angle;
		this.tab_boids = new Boid[n];
		this.tab_boids_init = new Boid[n];
		this.icone = icone;
		this.reactionTime = reactionTime;
		int height = gui.getHeight();
		int width = gui.getWidth();
		for (int k=0; k<n; k++) {
			double x = (Math.random() * width);
			double y = (Math.random() * height);
			double vx = (Math.random()*6)-3;
			double vy = (Math.random()*6)-3;
			this.tab_boids[k] = new Boid(new Point2D.Double(x,y), new Point2D.Double(vx, vy), this.vision, this.angle, this.v_max);
			this.tab_boids_init[k] = new Boid(tab_boids[k]);
		}
	}
	
	/** 
	 * Réinitialise la position de tous les Boid
	 * */
	public void reInit() {
		for (int k=0; k<n; k++) {
			this.tab_boids[k] = new Boid(this.tab_boids_init[k]);
		}
	}
		
	/** 
	 * Met à jour tous les Boid avec l'appel de la fonction Boid.move()
	 * 
	 * */
	
	public int getReactionTime() {
		return reactionTime;
	}
	
	public void etat_suivant() {
		
		int height = gui.getHeight();
		int width = gui.getWidth();
		Boid[] tab_aux = new Boid[n]; //On travaille avec une copie de l'instant t, pour n'appliquer la modification qu'à la fin
		for (int k=0; k<n; k++) {
			tab_aux[k] = this.tab_boids[k];
		}
		
		for (Boid boid: tab_aux) {
			boid.move(this.tab_boids, height, width);
		}
		
		this.tab_boids = tab_aux;
	}
	
	/** 
	 * Affiche tous les Boid à l'écran, sous forme de texte préalablement défini
	 * */
	public void draw(GUISimulator gui) {
		for (Boid boid : this.tab_boids) {
			//gui.addGraphicalElement(new Oval((int) boid.pos.x, (int) boid.pos.y, this.color, this.color, 3));
			Text image = new Text( boid.getX(), boid.getY(), this.color, icone);
			gui.addGraphicalElement(image);
		}
	}
	
	
		
}
