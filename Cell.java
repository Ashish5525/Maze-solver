package maze;

public class Cell {

	public static final int NORTH = 0;
	public static final int SOUTH = 1;
	public static final int EAST = 2;
	public static final int WEST = 3;

	private boolean neighbors[];

	private boolean onPath;

	private boolean start;

	private boolean end;

	private boolean walkable;

	public Cell(char value) {

		neighbors[0] = false;
		neighbors[1] = false;
		neighbors[2] = false;
		neighbors[3] = false;

		if (value == 'S') {

			start = true;
			end = false;
			walkable = true;

		}

		if (value == 'E') {

			start = false;
			end = true;
			walkable = true;

		}

		if (value == '1') {

			start = false;
			end = false;
			walkable = true;

		}

		if (value == '0') {

			start = false;
			end = false;
			walkable = false;

		}

		onPath = false;
	}

	public boolean getStart() {

		return start;

	}

	public boolean getEnd() {

		return end;

	}

	public boolean getWalkable() {

		return walkable;

	}

	public boolean getOnPath() {

		return onPath;

	}

	public void setNeighbors(int number, boolean answer) {

		neighbors[number] = answer;

	}

	public boolean getNeighbors(int number) {

		return neighbors[number];

	}

	public void setOnPath(boolean value) {

		onPath = value;

	}

}
