package Boids.copy;

/** 
 * Classe d'evenements correspondant à la mise à jour d'un groupe de Boid donné
 * */
public class BoidsEvent extends Event {
	
	private Boids boids ;
	
	public BoidsEvent (int date , Boids boids ) {
		super(date) ;
		this.boids = boids ;
	}
	
	public void execute () {
		boids.etat_suivant();
	}
}
