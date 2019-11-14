package Boids;

import java.awt.*;
import gui.*;

public class Boid {

	private Point pos;
	private Point speed;
	private Point accel;
	
	public Boid(Point pos, Point speed) {
			this.pos = pos;
			this.speed = speed;
			this.accel = new Point(0,0);
		}
	
	public Point get_steer(Boids boids) {
		
		Point steer = new Point();
		steer += this.get_force1(boids);
		steer += this.get_force2(boids);
		steer += this.get_force3(boids);
		return steer;
	}
	
	public Point get_force1(Boids boids) {
		return new Point();
	}
	
	public Point get_force2(Boids boids) {
		return new Point();
	}
	
	public Point get_force3(Boids boids) {
		return new Point();
	}
	
	public void move(Boids boids) {
		
		Point steer = this.get_steer(boids);
		this.speed.translate(steer.getX(), steer.getY());
		this.pos.translate(speed.getX(), speed.getY());
		
	}
	
	public void draw(GUISimulator gui) {
		
		
	}
}
