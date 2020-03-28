package sample;

import javafx.scene.layout.GridPane;

class Controller {

	private GridPane stageGrid;

	Controller() {
		Board board = Board.getInstance();
		this.stageGrid = board.getStageGrid();
	}

	private void simulation() {
		System.out.println("start simulation");
	}

	public GridPane getStageGrid() {
		return stageGrid;
	}

}


