package sample;


import java.util.Map;

class ActiveCells {
	private Cell center;
	private Map<Side,Cell> neighbourCells;

	public ActiveCells(Cell center, Map<Side, Cell> neighbourCells) {
		this.center = center;
		this.neighbourCells = neighbourCells;
	}

	public Cell getCenter() {
		return center;
	}

	public Map<Side, Cell> getNeighbourCells() {
		return neighbourCells;
	}
}
