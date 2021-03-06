package sample;

import javafx.scene.control.Alert;
import javafx.scene.layout.GridPane;
import lombok.Getter;

import java.util.*;
import java.util.regex.Pattern;

@Getter
class Controller {

	private GridPane stageGrid;
	private int gridSize;
	private static Board board;
	private Cell [][] cellsMatrix;
	private String initialState;

	Controller() {
		board = Board.getInstance();
		this.stageGrid = board.getStageGrid();
		setSize();
		board.getOneStepButton().setOnAction(event -> simulation());
		board.getTenStepsButton().setOnAction(event -> {
			for(int i=0;i<10;i++) simulation();
		});
		board.getStateCombobox().setOnAction(event -> {
			setInitialState(board.getStateCombobox().getValue());
			setInitialCells(this.initialState);

		});
		board.getSetSizeButton().setOnAction(event -> setSize());
	}

	private void setSize() {
		setInitialState(board.getStateCombobox().getValue());
		setCellSize(board.getGridSizeField().getText());
		this.cellsMatrix = new Cell[this.gridSize][this.gridSize];
		board.getCellsGrid().getChildren().clear();
		for(int y = 0; y<this.gridSize ; y++){
			for(int x = 0; x< this.gridSize; x++){
				this.cellsMatrix[y][x]= new Cell(CellState.DEAD,x,y,board.getBOARD_SIZE()/this.gridSize);
				board.getCellsGrid().add(this.cellsMatrix[y][x].getRectangle(),x,y);
			}
		}
		setInitialCells(this.initialState);
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
	private void setInitialCells(String state){

		for(int y = 0; y<this.gridSize ; y++){
			for(int x = 0; x< this.gridSize; x++){
				this.cellsMatrix[y][x].setState(CellState.DEAD);
			}
		}
		if(!state.equals("Own states")){
		Drawer drawer = new Drawer();
		drawer.drawStructure(state,this.cellsMatrix);
		}
	}
	private void setInitialState(String initialState){
		this.initialState = initialState;
	}

	private void simulation() {
		Cell [][] newCellsMatrix = new Cell[this.gridSize][this.gridSize];

		for(int i=0; i<this.gridSize; i++)
			for(int j=0; j<this.gridSize; j++)
				newCellsMatrix[i][j]=new Cell(this.cellsMatrix[i][j]);

		for(int y=0;y<this.gridSize;y++){
			for(int x=0;x<this.gridSize;x++){

				int prevY = converter(y-1);
				int prevX = converter(x-1);
				int nextX = converter(x+1);
				int nextY = converter(y+1);
				Map<Side,CellState> neighbourCells = new EnumMap<>(Side.class);
				neighbourCells.put(Side.TOP_LEFT,this.cellsMatrix[prevY][prevX].getState());
				neighbourCells.put(Side.LEFT,this.cellsMatrix[y][prevX].getState());
				neighbourCells.put(Side.DOWN_LEFT,this.cellsMatrix[nextY][prevX].getState());
				neighbourCells.put(Side.DOWN,this.cellsMatrix[nextY][x].getState());
				neighbourCells.put(Side.DOWN_RIGHT,this.cellsMatrix[nextY][nextX].getState());
				neighbourCells.put(Side.RIGHT,this.cellsMatrix[y][nextX].getState());
				neighbourCells.put(Side.TOP_RIGHT,this.cellsMatrix[prevY][nextX].getState());
				neighbourCells.put(Side.TOP,this.cellsMatrix[prevY][x].getState());

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

	private int converter(int x){
		return x<0?this.gridSize-1 : x%this.gridSize;
	}

}


