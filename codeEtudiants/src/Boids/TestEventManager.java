package Boids;

public class TestEventManager {

	public static void main1(String[] args) {
		
		EventManager pingpong = new EventManager();
		
		for (int i=0; i<20; i+=2) {
			pingpong.addEvent(new MessageEvent(i, "wesh"));
		}
		
		for (int i=0; i<20; i+=3) {
			pingpong.addEvent(new MessageEvent(i, "la zone"));
		}
		
		for (int i=0; i<3; i+=1) { 
			while (!pingpong.isFinished()) {
				pingpong.next();
			}
			pingpong.restart();
		}

	}

}
