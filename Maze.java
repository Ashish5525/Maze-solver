package maze;

import java.util.Scanner;
import java.util.Stack;

public class Maze {
	
	private Cell[][] maze;
	private int width, height;
	private Stack<Location> path;
	private Set<Location> visited;
	private boolean path_found;
	

	public Maze(Scanner s) {
		
		path_found  = false;
		
		path = new Stack<Location>();
		
		visited = new Set<Location>();
		
		height = s.nextInt();
		
		width = s.nextInt();
		
		maze = new Cell[height][width];
		
		for(int row = 0; row < height; row++) {
			
			String temp = s.nextLine();
			
			for(int col = 0; col < width; col++) {
				
				maze[row][col] = new Cell(temp.charAt(col));
			}
		}
		
	}
	
	public String asText() {
		
		String mazeResult = "";
		
		if(path_found != true) {
			
			mazeResult += "Failed\n";
		}
		
		for(int row = 0; row < height; row++) {
			
			for(int col = 0; col < width; col++) {
				
				if(maze[row][col].getEnd() == true) {
					
					mazeResult += 'E';
				}
				else if(maze[row][col].getStart() == true) {
					
					mazeResult += 'S'; 
				}
				else if(maze[row][col].getOnPath() == true) {
					
					mazeResult += '@'; 
				}
				else if(maze[row][col].getWalkable() == true) {
					
					mazeResult += '1'; 
				}
				else {
					
					mazeResult += '0';
				}
			}
			
			mazeResult += "\n";
		}
		
		mazeResult += "\n";
		
		return mazeResult;
	}
	
	public void findPath() {
		
		Location start = setCell();
		
		path.push(start);
		visited.enter(start);
		
		while(path.isEmpty() != true) {
			
			Location current = path.pop();
			
			if(maze[current.getRow()][current.getCol()].getEnd() != true) {
				
				Cell currentCell = maze[current.getRow()][current.getCol()];
				
				for(int dir = Cell.NORTH; dir <= Cell.WEST; dir++) {
					
					if(currentCell.getNeighbors(dir) == true) {
						
						Location newLocation = current.getLoc(dir);
						
						if(visited.isElement(newLocation) != true) {
							
							newLocation.setPrevious(current);
							visited.enter(newLocation);
							path.push(newLocation);
						}
					}
				}
			}
			
			else {
				
				path_found = true;
				
				maze[current.getRow()][current.getCol()].setOnPath(true);
				
				Location pointer = null;
				
				for(pointer = current; pointer != null; pointer = pointer.previous()) {
					
					maze[pointer.getRow()][pointer.getCol()].setOnPath(true);
				}
			}
		}
	}
	
	public Location setCell() {
		
		Location startLoc = null;
			
		for(int row = 0; row < height; row++) {
			
			for(int col = 0; col < height; col++) {
				
				if(row == 0) {
					
					maze[row][col].setNeighbors(0, false);
				}
				
				else if(maze[row - 1][col].getWalkable() == true) {
					
					maze[row][col].setNeighbors(1, true);
				}
				
				if(col == 0) {
					
					maze[row][col].setNeighbors(3, false);
				}
				
				else if(maze[row][col - 1].getWalkable() == true) {
					
					maze[row][col].setNeighbors(3, true);
				}
				
				if(col == width - 1) {
					
					maze[row][col].setNeighbors(2, false);
				}
				
				else if(maze[row][col + 1].getWalkable() == true) {
					
					maze[row][col].setNeighbors(2, true);
				}
				
				if(maze[row][col].getStart() == true) {
					
					startLoc = new Location(row, col);
				}
			}
		}
		
		return startLoc;
	}
	
	public boolean pathFound(){
		
		return path_found;
		
	}
	
	public int getH() {
		
		return height;
		
	}
	
	public int getW() {
		
		return width;
		
	}
	
	public Cell getCell(int i, int j) {
		
		return maze[i][j];
		
	}

	
	
}
