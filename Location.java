package maze;

public class Location {
	
	private int row, col;
	
	private Location previous;

	public Location(int row, int col) {
		
		this.row = row;
		
		this.col = col;
		
		previous = null;
		
	}
	
	public void setPrevious(Location loc) {
		
		previous = loc;
		
	}
	
	public Location previous() {
		
		return previous;
		
	}
	
	public Location getLoc(int dir) {
		
		Location temp = null;
		
		if(dir == Cell.NORTH) {
			
			temp = new Location(row - 1, col);
			
		}
		
		else if (dir == Cell.EAST) {

			temp = new Location(row, col + 1);

		} 
		
		else if (dir == Cell.WEST) {

			temp = new Location(row, col - 1);

		} 
		
		else if (dir == Cell.SOUTH) {

			temp = new Location(row + 1, col);

		}
		
		return temp;
		
	}
	
	public boolean equals(Object item) {
		
		if(item instanceof Location) {
			
			Location temp = (Location) item;
			
			if(temp.row == this.row && temp.col == this.col) {
				
				return true;
			}
			
			return false;
		}
		
		return false;
	}
	
	public int getRow() {
		
		return row;
		
	}
	
	public int getCol() {
		
		return col;
		
	}

}
