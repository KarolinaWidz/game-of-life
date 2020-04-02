package sample;

import java.util.HashMap;
import java.util.Map;

public class Drawer {

	private static final Map<String,InitialStructure> structureMap = new HashMap<>();
	static{
		structureMap.put("Blinker",new Blinker());
		structureMap.put("Beehive", new Beehive());
		structureMap.put("Glider", new Glider());
		structureMap.put("Random", new RandomStructure());
	}
	public void drawStructure(String structure, Cell [][] cellsMatrix){
		structureMap.get(structure).draw(cellsMatrix);
	}
}
