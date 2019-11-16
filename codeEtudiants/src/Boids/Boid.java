package Boids;

import java.awt.geom.*;


/**
 * Classe représentant un Boid, se déplaçant au sein d'un groupe de Boids
 *
 * @version 1.0 
 * 
 * @author Vincent Hachin  
 **/

public class Boid {

	/**position du boid */
	public Point2D.Double pos;
	
	/** vitesse du boid*/
	private Point2D.Double speed;
	
	/** distance du champ de vision*/
	private double vision;
	
	/** Vitesse max */
	private double v_max;
	
	/** Angle du champ de vision */
	private double angle;
	
	
	
	/** 
	 * Constructeur, initialise les paramètres du boid
	 * */
	public Boid(Point2D.Double pos, Point2D.Double speed, double vision, double angle, double v_max) {
		this.pos = pos;
		this.speed = speed;
		this.vision = vision;
		this.v_max = v_max;
		this.angle = angle;
	}
	
	public Boid(Boid b) {
		this.pos = new Point2D.Double(b.pos.x, b.pos.y);
		this.speed = new Point2D.Double(b.speed.x, b.speed.y);
		this.vision = b.vision;
		this.v_max = b.v_max;
		this.angle = b.angle;
	}
	
	
	/** 
	 * Calcule la force à appliquer sur le boid (fait la somme des 3 forces de bases)
	 * @param Boid[] boids: la liste des boids qui sont dans le champ de vision du boid
	 * @return La force à appliquer
	 */
	public Point2D.Double get_steer(Boid[] boids) {
		
		Point2D.Double steer = new Point2D.Double(); //La force totale
		Point2D.Double force = new Point2D.Double(); //La force auxiliaire à ajouter
		
		force = this.get_force1(boids);
		steer.setLocation(steer.x + force.x, steer.y + force.y);
		
		force = this.get_force2(boids);
		steer.setLocation(steer.x + force.x, steer.y + force.y);
		
		force = this.get_force3(boids);
		steer.setLocation(steer.x + force.x, steer.y + force.y);
		
		return steer;
	}
	
	/**
	 * Calcule la force d'attraction, un boid est attiré par le centre de masse des boids à proximité
	 * @param boids : les boids à proximité
	 * @return la force d'attraction
	 */
	public Point2D.Double get_force1(Boid[] boids) {
		
		int n = boids.length;
		if (n==0) {
			return new Point2D.Double(0,0);
		}
		Point2D.Double centre = new Point2D.Double();
		for (Boid b: boids) {
			centre.setLocation(centre.x + b.pos.x, centre.y + b.pos.y);
		}
		centre.setLocation(centre.x/n - this.pos.x, centre.y/n - this.pos.y );
		centre.setLocation(centre.x/100, centre.y/100);
		return centre;
	}
	
	/**
	 * Calcule la force de séparation, un boid s'écarte s'il est trop près d'autres boids
	 * @param boids
	 * @return la force de séparation
	 */
	public Point2D.Double get_force2(Boid[] boids) {
		
		int n = boids.length;
		if (n==0) {
			return new Point2D.Double(0,0);
		}
		Point2D.Double ecart = new Point2D.Double();
		for (Boid b: boids) {
			if (this.distance_to(b) < 5) {
				ecart.setLocation(ecart.x - (b.pos.x - this.pos.x), ecart.y - (b.pos.y - this.pos.y));
			}
		}
		ecart.setLocation(ecart.x/5, ecart.y/5);
		return ecart;
	}
	
	/**
	 * Calcule la force d'alignement, un boid s'aligne avec les boids à proximité
	 * @param boids : les boids à proximité
	 * @return la force d'alignement
	 */
	public Point2D.Double get_force3(Boid[] boids) {
		int n = boids.length;
		if (n==0) {
			return new Point2D.Double(0,0);
		}
		Point2D.Double alignement = new Point2D.Double();
		for (Boid b: boids) {
			alignement.setLocation(alignement.x + b.speed.x, alignement.y + b.speed.y);
		}
		alignement.setLocation(alignement.x/n - this.speed.x, alignement.y/n - this.speed.y );
		alignement.setLocation(alignement.x/8, alignement.y/8);
		return alignement;
	}
	
	/**
	 * Distance par rapport au Boid boid 
	 * @param boid
	 * @return distance entre this et boid
	 */
	public int distance_to(Boid boid) {
		return (int) (this.pos.distance(boid.pos)+0.5);
	}
	
	/**
	 * Calcule l'angle entre this et b avec la formule du cosinus : cos(x,y) = x.y/(||x||*||y||)
	 * Il s'agit de l'angle par rapport à l'orientation de this
	 * @param b
	 * @return l'angle en radians
	 */
	public double angle_to(Boid b) {
		
		double norme = Math.sqrt(speed.x*speed.x + speed.y*speed.y) * Math.sqrt(Math.pow(b.pos.x - pos.x, 2) + Math.pow(b.pos.y - pos.y, 2));
		if (norme==0) {
			return 0.0;
		}
		double produit_vect = (speed.x * (b.pos.x - pos.x) + speed.y * (b.pos.y - pos.y));
		return Math.acos(produit_vect/norme);
	}
	
	/**
	 * Déplace le boid en calculant d'abord son voisinage (boids dans le champ de vision), puis la force qu'il faut lui appliquer,
	 * pour enfin modifier sa vitesse et sa position.
	 * @param boids : Tous les boids dont on veut vérifier la proximité avec this
	 * @param height : hauteur de la fenetre
	 * @param width : largeu de la fenêtre
	 *  */
	public void move(Boid[] boids, int height, int width) {
		
		Boid[] voisinage_aux = new Boid[boids.length];
		int taille_voisinage = 0;
		for (Boid boid: boids) {
			if (!this.pos.equals(boid.pos) && (this.distance_to(boid) < this.vision)) { //Si il est assez proche
				if (this.angle_to(boid)<this.angle) {									//Et qu'il n'est pas trop derrière
					voisinage_aux[taille_voisinage] = boid;
					taille_voisinage+=1;
				}
			}
		}
		Boid[] voisinage = new Boid[taille_voisinage];  //On calcule d'abord la taille du voisinage pour faire un tableau sur-mesure.
		for (int i=0;i<taille_voisinage;i++) {
			voisinage[i] = voisinage_aux[i];
		}
		
		Point2D.Double steer = this.get_steer(voisinage);   //On calcule la force
		this.speed.setLocation(this.speed.x + steer.x, this.speed.y + steer.y); // v(t+1) = v(t) + a(t+1)
		double v = this.speed.distance(new Point2D.Double());   //On calcule la norme de la vitesse 
		if (v>this.v_max){ //Si celle si est trop grande, on la réduit
			this.speed.setLocation(this.speed.x/v*this.v_max, this.speed.y/v*this.v_max); 
		}
		this.pos.setLocation(this.pos.x + this.speed.x, this.pos.y + this.speed.y); //x(t+1) = x(t) + v(t+1)
		this.pos.setLocation((this.pos.x+width)%width, (this.pos.y+height)%height); //Le monde est considéré comme étant un donut 
																					//(les cotés haut-bas et gauche-droit se touchent)
		
	}
}
