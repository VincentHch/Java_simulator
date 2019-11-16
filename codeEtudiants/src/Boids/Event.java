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
	
	// On implémente la méthode compareTo qui prend en paramètre
	// un autre objet Event à comparer avec this
	@Override
	public int compareTo(Event e) {
		// On retourne un nombre positif
		// si la date de this est supérieur à celle de e
		if(this.date > e.date) {
			return 1;
		}
		if(this.date < e.date) {
			return -1;
		}
		return 0;
	}
}
