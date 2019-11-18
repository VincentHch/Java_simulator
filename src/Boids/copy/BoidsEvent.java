package Boids.copy;

/** 
 * Classe d'evenements correspondant � la mise � jour d'un groupe de Boid donn�
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
