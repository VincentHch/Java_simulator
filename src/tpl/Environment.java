package tpl;

import java.awt.*;
import gui.*;

public class Environment {
	
	Particle[] tab;
	
	/** 
	 * 
	 */
	public Environment(int n, int taille) {
		this.tab = new Particle[n];
		for (int i=0; i<n; i++) {
			this.tab[i] = new Particle(taille);
		}
	}
	
	public Environment(Particle[] tab) {
		this.tab = new Particle[tab.length];
		for (int i=0; i<tab.length; i++) {
			this.tab[i] = new Particle(tab[i]);
		}
	}
	
	public Environment(Environment env) {
		this.tab = new Particle[env.tab.length];
		for (int i=0; i<env.tab.length; i++) {
			this.tab[i] = new Particle(env.tab[i]);
		}
	}
	
	public void reInit() {
		for (Particle p:  this.tab) {
			p.reInit();
		}
	}

	public void next() {
		
		Environment nouv_env = new Environment(this);
		for (int i=0; i<this.tab.length; i++) {
			nouv_env.tab[i] = this.tab[i].next(this);
		}
		this.tab = nouv_env.tab;
		
	}

	public void draw(GUISimulator gui) {
		for (Particle p:  this.tab) {
			p.draw(gui);
		}
	}
}
