package simulateurBalle;

import java.awt.*;
import gui.*;

/**
 * Classe implémentant des balles, ainsi que leur vitesse.
 *
 * @version 2.0 
 * 
 * @author Vincent Hachin  
 **/

public class Balls {
	
	/**  
    nombre de balles */
	private static int n=3;
	/**  
    taille : le rayon des balles */
	private static int taille = 10;
	
	
	/** Les coordonnées initiales des balles */
	private static Point[] tab_balls_init = new Point[n];
	{
	tab_balls_init[0] = new Point(0, 0);
	tab_balls_init[1] = new Point(100, 200);
	tab_balls_init[2] = new Point(300, 0);
	}
	
	/**  
    tab_vit_init : les vitesses initiales des balles */
	private static Point[] tab_vit_init = new Point[n];
	{
	tab_vit_init[0] = new Point(3, -5);
	tab_vit_init[1] = new Point(1, 2);
	tab_vit_init[2] = new Point(3, 4);
	}
	
	/**  
    tab : les coordonnées des balles */
	Point[] tab = new Point[n];
	
	/**  
    V : les vitesses des balles */
	Point[] V = new Point[n];
	
	
	/** Constructeur, initiaise les positions/vitesses
	 *                 */
	public Balls() {
		
		this.tab = new Point[n];
		for (int i = 0; i < n; i++) {
				tab[i] = new Point(tab_balls_init[i]);
				V[i] = new Point(tab_vit_init[i]);
		}
	}
	
	/** Déplace les balles selon leur vitesses, gère les collisions avec les bords
	 * @param int width : largeur du cadre
	 * @param int height : hauteur du cadre*/
	public void translate(int width, int height) {
		for (int i = 0; i < n; i++) {
			
			Point p = this.tab[i];
			Point v = this.V[i];
			//Gestion des collisions (modifications de vitesses si nécessaire)
			if (p.x + v.x > width) {
				V[i].x *= -1;
				p.setLocation(width, p.y);
			}
			
			if (p.x + v.x < 0) {
				V[i].x *= -1;
				p.setLocation(0, p.y);
			}
			
			if (p.y + v.y > height) {
				V[i].y *= -1;
				p.setLocation(p.x, height);
			}
			
			if (p.y + v.y < 0) {
				V[i].y *= -1;
				p.setLocation(p.x, 0);
			}
			
			//translation des balles
	        p.translate(v.x, v.y);
	    }
	}
	
	/** Initialisation, les balles/vitesses retournent à leur état initial */
	public void reInit() {   //remet toutes les balles à leur position initiale
		for (int i = 0; i < n; i++) {
			this.tab[i] = new Point(tab_balls_init[i]);
			this.V[i] = new Point(tab_vit_init[i]);
	    }
	}
	
	/** Retourne l'état des balles*/
	@Override
	public String toString() {
		String res = "";
		for (int i = 0; i < n; i++) {
			res += "Point "+n+this.tab[i].toString()+"\n";
	    }
		return(res);
	}
	
	/** Dessine les balles
	 * @param GUISimulator gui : interface sur laquelle les balles sont dessinées*/
	public void draw(GUISimulator gui) {
		
		gui.reset();
		for (Point p : this.tab) {
			gui.addGraphicalElement(new Oval(p.x, p.y, Color.WHITE, Color.RED, taille));
	    }
	}
	
}
