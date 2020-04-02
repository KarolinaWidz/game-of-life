package sample;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public interface InitialStructure {

	List<Pair<Integer,Integer>> index = new ArrayList<>();

	default void draw(Cell[][] cellsMatrix) {
		index.clear();
		addPairs(cellsMatrix);
		for(Pair<Integer,Integer> pair:index)
			cellsMatrix[pair.getKey()][pair.getValue()].setState(CellState.ALIVE);
	}
	void addPairs(Cell[][] cellsMatrix);
}
