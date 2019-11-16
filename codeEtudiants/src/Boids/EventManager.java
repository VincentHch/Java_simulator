package Boids;

import java.util.* ;

public class EventManager {

	public long currentDate;
	private Queue<Event> events;
	private Queue<Event> events_futurs;
	
	public EventManager() {
		currentDate = 0;
		events = new PriorityQueue<Event>();
		events_futurs = new PriorityQueue<Event>();
	}
	
	public void addEvent(Event e) {
		events.add(e);
	}
	
	public void next() {
		while (!isFinished() && events.peek().date<=currentDate) {
			Event e = events.poll();
			events_futurs.add(e);
			e.execute();
		}
		currentDate+=1;
		
	}
	
	public void restart() {
		events = new PriorityQueue<Event>(events_futurs);
		events_futurs = new PriorityQueue<Event>();
		currentDate = 0;
	}
	
	public boolean isFinished() {
		return (events.size() == 0);
	}
	
	
	}
