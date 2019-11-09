package GameOfImmigr;

import gui.*;

import java.awt.Color;

/** Une instance de Grid est une grille pré-remplie, dont les cellules peuvent évoluer */

public class Grid {
	
	public int width;
	public int height;
	public int n;
	public int[][] grid;
	private static int[][] etat_init = {{10,10,3},
			                            {12,10,1},
			                            {13,10,1},
			                            {10,11,3},
			                            {11,11,1},
			                            {12,11,1},
			                            {13,11,1},
			                            {14,11,2},
			                            {10,12,1},
			                            {11,12,1},
			                            {12,12,3},
			                            {13,12,2},
			                            {14,12,2},
			                            {10,13,0},
			                            {11,13,1},
			                            {12,13,2},
			                            {13,13,2},
			                            {14,13,2},
			                            {10,14,0},
			                            {11,14,3},
			                            {12,14,2},
			                            {13,14,2},
			                            {14,14,1}};

	
	public Grid(int width, int height, int n) {
		
		this.width = width;
		this.height = height;
		this.n = n;
		this.grid = new int[width][height];
		for (int x=0; x<width; x++) {
			for (int y=0; y<height; y++) {
				this.grid[x][y] = 0;
			}
		}
		for (int[] couple : etat_init) {
			this.grid[couple[0]][couple[1]] = couple[2];
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
			this.grid[couple[0]][couple[1]] = couple[2];
		}
	}
	
	
	public int etat_suivant(int x,int y) {
		
		int voisins[][] = { {x-1,y-1}, {x,y-1}, {x+1,y-1},
				            {x-1,y}  ,          {x+1,y},
				            {x-1,y+1}, {x,y+1}, {x+1,y+1}};
		
		int k = this.get_state(x,y);
		int nb_voisins = 0;
		
		for (int[] couple : voisins) {
			int vx = couple[0];
			int vy = couple[1];
			if (vx>=0 && vx<this.width && vy>=0 && vy<this.height) {
				if ( this.get_state(vx, vy)==(k+1)%this.n ) {
					nb_voisins+=1;
				}
			}
		}
		
		if (nb_voisins>=3) {
			/** DEBUG
			System.out.println(this.n);
			System.out.print("Point :");
			System.out.print(x);
			System.out.print(", ");
			System.out.print(y);
			System.out.print(" passe de ");
			System.out.print(k);
			System.out.print(" a ");
			System.out.println((k+1)%this.n);
			*/
			return (k+1)%(this.n);
		}
		return k;
		
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
				int k=this.get_state(x, y);
				float c = 1- 1/ (float) n * (float) k;
				couleur = new Color(c,c,c);
				gui.addGraphicalElement(new Rectangle(x*10,
				                                      y*10,
				                                      couleur,
				                                      couleur,
				                                      10));
			}
	    }
	}
}
