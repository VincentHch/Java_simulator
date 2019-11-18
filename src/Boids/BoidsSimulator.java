package Boids;

import gui.*;

/** 
 * Classe représentant un simulable contenant plusieurs groupe de boids,
 * géré à l'aide d'un gestionnaire d'évènements.
 *
 *
 */

public class BoidsSimulator implements Simulable{
	
	/** Liste de groupes de Boid*/
	private Boids[] boidsGroups;
	private GUISimulator gui;
	
	/**Nombre de groupes*/
	private int nbGroups;
	
	/**Le gestionnaire d'évènements*/
	private EventManager boidsManager;
	
	/**
	 * Constructeur, créé un simulateur SANS Boid, ils peuvent être ajoutés à l'aide de la fonction addBoids
	 * @param nbMaxGroups : nombre maximum de groupe pouvant être ajoutés, pour éviter des problèmes de temps de calcul
	 * @param gui : fenêtre
	 */
	public BoidsSimulator(int nbMaxGroups, GUISimulator gui) {
		this.boidsGroups = new Boids[nbMaxGroups];
		nbGroups = 0;
		this.gui = gui;
		this.boidsManager = new EventManager();
	}
	
	/** On ajoute un groupe de Boids */
	public void addBoids(int n, double vision, double v_max, double angle, int reactionTime, java.awt.Color color, java.lang.String icone) {
		this.boidsGroups[nbGroups] = new Boids(n, vision, v_max, angle, reactionTime, color, icone,  gui);
		this.nbGroups += 1;
	}
	
	/**Méthode à appeler OBLIGATOIREMENT une fois que les Boids ont été ajoutés, on remplit le boidsManager jusqu'au temps 100
	 * 
	 * */
	public void init_events() {
		for (int i=0; i<nbGroups; i++) {
			Boids group = boidsGroups[i];
			for (int t=0; t<100; t+=group.getReactionTime()) {
				boidsManager.addEvent(new BoidsEvent(t, group));
			}
		}
	}
	
	/**
	 * Execute tous les évènements qui doivent être exécutés (met à jour certains groupes de Boids)
	 * Affiche ensuite tous les groupes
	 */
	@Override
	public void next () {
		
		if (boidsManager.isFinished()) { //Si il n'y a plus d'évènements à venir :
			boidsManager.restart(); // pas grave, on recommence.
		}
		
		boidsManager.next();
		
		//Affichage
		gui.reset();
		for (int i=0; i<nbGroups; i++) {
			Boids group = boidsGroups[i];
			group.draw(this.gui);
		}
	}
	
	/**
	 * Redémarre la simulation (pas besoin de redémarrer le gestionnaire)
	 */
	@Override
	public void restart () {
		gui.reset();
		for (int i=0; i<nbGroups; i++) {
			Boids group = boidsGroups[i];
			group.reInit();
			group.draw(this.gui);
		}
	}
}
