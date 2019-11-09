package tpl;

import java.awt.*;
import gui.*;

METTRE VIT INIT SUR TT LES CONSTRUCTEURS

public class Ball extends Particle {

	private Point vit;
	private Point vit_init;
	
	public Ball() {
		super();
	}
	
	public Ball(int taille) {
		super(taille);
		this.vit = new Point(1,2);
	}
	
	public Ball(int x, int y, int taille) {
		super(x, y, taille);
		this.vit = new Point(1,2);
	}
	
	public Ball(int x, int y, int vx, int vy, int taille) {
		super(x, y, taille);
		this.vit = new Point(vx, vy);
	}
	
	public Ball(Point pos, Point vit, int taille) {
		super(pos, taille);
		this.vit = new Point(vit);
	}
	
	/** Creation d'une particule au meme endroit que la particule p
	 * 
	 * @param Particle p
	 */
	public Ball(Ball p) {
		this.pos = new Point(p.pos);
		this.pos_init = new Point(this.pos);
		this.taille = p.taille;
	}
	
	/** Déplace la balle selon sa vitesses, gère les collisions avec les bords
	 * @param int width : largeur du cadre
	 * @param int height : hauteur du cadre*/
	@Override
	public Particle next(GUISimulator gui) {
		
		int width = gui.getWidth();
		int height = gui.getHeight();
		
		Point p = new Point(this.pos);
		Point v = new Point(this.vit);
		//Gestion des collisions (modifications de vitesses si nécessaire)
		if (p.x + v.x > width) {
			v.x *= -1;
			p.setLocation(width, p.y);
		}
		
		if (p.x + v.x < 0) {
			v.x *= -1;
			p.setLocation(0, p.y);
		}
		
		if (p.y + v.y > height) {
			v.y *= -1;
			p.setLocation(p.x, height);
		}
		
		if (p.y + v.y < 0) {
			v.y *= -1;
			p.setLocation(p.x, 0);
		}
		
		//translation des balles
        p.translate(v.x, v.y);
        return new Ball(p, v, this.taille);
	}
}
