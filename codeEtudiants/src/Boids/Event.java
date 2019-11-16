package Boids;

public abstract class Event implements Comparable<Event> {
	
	public long date;
	
	
	public Event(long date) {
		this.date = date;
	}
	
	public abstract void execute();
	
	public long getDate() {
		return this.date;
	}
	
	// On impl�mente la m�thode compareTo qui prend en param�tre
	// un autre objet Event � comparer avec this
	@Override
	public int compareTo(Event e) {
		// On retourne un nombre positif
		// si la date de this est sup�rieur � celle de e
		if(this.date > e.date) {
			return 1;
		}
		if(this.date < e.date) {
			return -1;
		}
		return 0;
	}
}
