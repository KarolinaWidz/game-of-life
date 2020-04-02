package sample;

import javafx.util.Pair;

import java.util.Random;

public class RandomStructure implements InitialStructure {

	@Override
	public void addPairs(Cell[][] cellsMatrix) {
		Random generator = new Random();
		for(int y = 0; y<cellsMatrix.length ; y++){
			for(int x = 0; x< cellsMatrix.length; x++) {
				if(generator.nextInt(2)==1)
					index.add(new Pair<>(x,y));
			}
		}
	}
}
