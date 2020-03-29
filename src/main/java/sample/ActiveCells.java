package sample;

import lombok.Getter;

import java.util.Map;

@Getter
class ActiveCells {
	private Cell center;
	private Map<Side, CellState> neighbourCells;
	private int counter;

	ActiveCells(Cell center, Map<Side, CellState> neighbourCells) {
		this.center = center;
		this.neighbourCells = neighbourCells;
		this.counter=0;
	}
	void incrementCounter(){
		this.counter++;
	}
}
