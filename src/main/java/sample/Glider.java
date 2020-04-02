package sample;

import javafx.util.Pair;

public class Glider implements InitialStructure {

	@Override
	public void addPairs(Cell[][] cellsMatrix) {
		index.add(new Pair<>(0,1));
		index.add(new Pair<>(0,2));
		index.add(new Pair<>(1,0));
		index.add(new Pair<>(1,1));
		index.add(new Pair<>(2,2));
	}
}
