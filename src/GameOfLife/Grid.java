package GameOfLife;

import gui.*;

import java.awt.Color;

/** Une instance de Grid est une grille pré-remplie, dont les cellules peuvent évoluer */

public class Grid {
	
	public int width;
	public int height;
	public int[][] grid;
	private static int[][] etat_init = {{11,9},
			                            {9,10},
			                            {10,11},
			                            {11,11},
			                            {11,10}};
	
	public Grid(int width, int height) {
		
		this.width = width;
		this.height = height;
		this.grid = new int[width][height];
		for (int x=0; x<width; x++) {
			for (int y=0; y<height; y++) {
				this.grid[x][y] = 0;
			}
		}
		for (int[] couple : etat_init) {
			this.grid[couple[0]][couple[1]] = 1;
		}
		
	}
	
	public void reInit(GUISimulator gui) {
		
		
		int width = this.width;
		int height = this.height;
		this.grid = new int[width][height];
		for (int x=0; x<width; x++) {
			for (int y=0; y<height; y++) {
				this.grid[x][y] = 0;
			}
		}
		for (int[] couple : etat_init) {
			this.grid[couple[0]][couple[1]] = 1;
		}
	}
	
	
	public int etat_suivant(int x,int y) {
		
		int voisins[][] = { {x-1,y-1}, {x,y-1}, {x+1,y-1},
				            {x-1,y}  ,          {x+1,y},
				            {x-1,y+1}, {x,y+1}, {x+1,y+1}};
		int nb_voisins = 0;
		
		for (int[] couple : voisins) {
			int vx = couple[0];
			int vy = couple[1];
			if (vx>=0 && vx<this.width && vy>=0 && vy<this.height) {
				nb_voisins+=this.grid[vx][vy];
				
			}
		}
		
		if (nb_voisins>0) {
		}
		
		if (this.grid[x][y] == 1) {
			if (nb_voisins==2 || nb_voisins==3) {
				return 1;
			}else {
				return 0;
			}
		}else {
			if (nb_voisins==3) {
				return 1;
			}
		}
		return 0;
	}
	
	public int get_state(int x,int y) {
		return this.grid[x][y];
	}
	
	public void etat_suivant() {
		int [][] new_grid = new int[width][height];
		
		for (int x=0; x<width; x++) {
			for (int y=0; y<height; y++) {
				new_grid[x][y] = this.etat_suivant(x,y);
			}
		}
		this.grid = new_grid;
		
	}
	
	/** Dessine la grille
	 * @param GUISimulator gui : interface sur laquelle la grille est dessinée*/
	public void draw(GUISimulator gui) {
		
		gui.reset();
		java.awt.Color couleur;
		for (int x=0; x<width; x++) {
			for (int y=0; y<height; y++) {
				if (this.get_state(x,y)==1) {
					couleur = Color.BLACK;
				}else {
					couleur = Color.WHITE;
				}
				gui.addGraphicalElement(new Rectangle(x*5,
				                                      y*5,
				                                      couleur,
				                                      couleur,
				                                      5));
			}
	    }
	}
}
