package Boids;

import gui.*;

/** 
 * Classe repr�sentant un simulable contenant plusieurs groupe de boids,
 * g�r� � l'aide d'un gestionnaire d'�v�nements.
 *
 *
 */

public class BoidsSimulator implements Simulable{
	
	/** Liste de groupes de Boid*/
	private Boids[] boidsGroups;
	private GUISimulator gui;
	
	/**Nombre de groupes*/
	private int nbGroups;
	
	/**Le gestionnaire d'�v�nements*/
	private EventManager boidsManager;
	
	/**
	 * Constructeur, cr�� un simulateur SANS Boid, ils peuvent �tre ajout�s � l'aide de la fonction addBoids
	 * @param nbMaxGroups : nombre maximum de groupe pouvant �tre ajout�s, pour �viter des probl�mes de temps de calcul
	 * @param gui : fen�tre
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
	
	/**M�thode � appeler OBLIGATOIREMENT une fois que les Boids ont �t� ajout�s, on remplit le boidsManager jusqu'au temps 100
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
	 * Execute tous les �v�nements qui doivent �tre ex�cut�s (met � jour certains groupes de Boids)
	 * Affiche ensuite tous les groupes
	 */
	@Override
	public void next () {
		
		if (boidsManager.isFinished()) { //Si il n'y a plus d'�v�nements � venir :
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
	 * Red�marre la simulation (pas besoin de red�marrer le gestionnaire)
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
