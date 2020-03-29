package sample;

import javafx.scene.control.Alert;
import javafx.scene.layout.GridPane;
import lombok.Getter;

import java.util.EnumMap;
import java.util.Map;
import java.util.regex.Pattern;

@Getter
class Controller {

	private GridPane stageGrid;
	private int gridSize;
	private static Board board;
	private Cell [][] cellsMatrix;

	Controller() {
		board = Board.getInstance();
		this.stageGrid = board.getStageGrid();
		setSize();
		board.getOneStepButton().setOnAction(event -> simulation());
		board.getTenStepsButton().setOnAction(event -> {
			for(int i=0;i<10;i++) simulation();
		});
		board.getSetSizeButton().setOnAction(event -> setSize());
	}

	private void setSize() {
		setCellSize(board.getGridSizeField().getText());
		this.cellsMatrix = new Cell[this.gridSize][this.gridSize];
		board.getCellsGrid().getChildren().clear();
		for(int y = 0; y<this.gridSize ; y++){
			for(int x = 0; x< this.gridSize; x++){
				this.cellsMatrix[y][x]= new Cell(CellState.DEAD,x,y,board.getBOARD_SIZE()/this.gridSize);
				board.getCellsGrid().add(this.cellsMatrix[y][x].getRectangle(),x,y);
			}
		}
		board.getCellsGrid().setGridLinesVisible(false);
		board.getCellsGrid().setGridLinesVisible(true);
	}

	private void setCellSize(String size){
		if(Pattern.matches("^\\d*$",size) && (Integer.parseInt(size))>=4){
			this.gridSize = (Integer.parseInt(size));
		}
		else {
			try{
				throw new IllegalArgumentException("INVALID ARGUMENT");
			}catch(IllegalArgumentException e ){
				Alert alert = new Alert(Alert.AlertType.ERROR,e.getMessage());
				alert.show();
			}
		}
	}
	private void simulation() {
		Cell [][] newCellsMatrix = new Cell[this.gridSize][this.gridSize];

		for(int i=0; i<this.gridSize; i++)
			for(int j=0; j<this.gridSize; j++)
				newCellsMatrix[i][j]=new Cell(this.cellsMatrix[i][j]);

		for(int y=1;y<gridSize-1;y++){
			for(int x=1;x<gridSize-1;x++){

				Map<Side,CellState> neighbourCells = new EnumMap<>(Side.class);
				neighbourCells.put(Side.TOP_LEFT,this.cellsMatrix[y-1][x-1].getState());
				neighbourCells.put(Side.LEFT,this.cellsMatrix[y][x-1].getState());
				neighbourCells.put(Side.DOWN_LEFT,this.cellsMatrix[y+1][x-1].getState());
				neighbourCells.put(Side.DOWN,this.cellsMatrix[y+1][x].getState());
				neighbourCells.put(Side.DOWN_RIGHT,this.cellsMatrix[y+1][x+1].getState());
				neighbourCells.put(Side.RIGHT,this.cellsMatrix[y][x+1].getState());
				neighbourCells.put(Side.TOP_RIGHT,this.cellsMatrix[y-1][x+1].getState());
				neighbourCells.put(Side.TOP,this.cellsMatrix[y-1][x].getState());

				ActiveCells activeCells = new ActiveCells(this.cellsMatrix[y][x],neighbourCells);

				for(Map.Entry<Side,CellState> cellState: neighbourCells.entrySet()){
					if(cellState.getValue().getFlag()) activeCells.incrementCounter();
				}
				if(activeCells.getCenter().getState().getFlag()){
					switch (activeCells.getCounter()){
						case 2:
						case 3: break;
						default: newCellsMatrix[y][x].setState(CellState.DEAD);break;
					}
				}
				else
					if (activeCells.getCounter() == 3) newCellsMatrix[y][x].setState(CellState.ALIVE);
			}
		}
		for(int i=0; i<this.gridSize; i++)
			for(int j=0; j<this.gridSize; j++)
				this.cellsMatrix[i][j].setState(newCellsMatrix[i][j].getState());
	}
}


