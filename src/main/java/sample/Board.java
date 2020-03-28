package sample;


import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

class Board {

	private GridPane cellsGrid;
	private GridPane stageGrid;
	private ComboBox<String> stateCombobox;
	private TextField gridSizeField;

	private final int BOARD_SIZE = 700;

	private static final Board INSTANCE = new Board();

	private Board() {

		this.cellsGrid = new GridPane();
		this.stageGrid = new GridPane();
		GridPane menuGrid = new GridPane();

		//labels
		Label gridSizeLabel = new Label("Grid size: ");
		Label initialStateLabel = new Label("Initial state: ");

		//input
		this.stateCombobox= new ComboBox<>();
		this.stateCombobox.getItems().addAll("Blinker", "Bee-hive", "Glider", "Random", "Own states");
		this.stateCombobox.setValue("Blinker");
		this.cellsGrid.setPadding(new Insets(10));
		menuGrid.setPadding(new Insets(10));
		menuGrid.setHgap(10);
		menuGrid.setVgap(10);
		this.gridSizeField = new TextField("20");
		this.stateCombobox.setMaxSize(100,10);
		this.gridSizeField.setMaxSize(100,10);

		//buttons
		Button startButton = new Button("START/STOP");
		Button tenStepsButton = new Button("10 STEPS");

		menuGrid.addColumn(0, gridSizeLabel, initialStateLabel, tenStepsButton);
		menuGrid.addColumn(1, gridSizeField, stateCombobox, startButton);

		for(int i = 0; i< 20; i++){
			for(int j = 0; j< 20; j++){
				cellsGrid.add(new Rectangle(this.BOARD_SIZE / 20, this.BOARD_SIZE / 20, Color.WHITE),i,j);
			}
		}
		this.cellsGrid.setGridLinesVisible(true);
		this.stageGrid.add(menuGrid, 0, 0);
		ScrollPane scrollPane = new ScrollPane(cellsGrid);
		this.stageGrid.add(scrollPane, 1, 0);
	}

	public GridPane getStageGrid() {
		return stageGrid;
	}

	public GridPane getCellsGrid() {
		return cellsGrid;
	}

	public ComboBox<String> getStateCombobox() {
		return stateCombobox;
	}

	public TextField getGridSizeField() {
		return gridSizeField;
	}

	public int getBOARD_SIZE() {
		return BOARD_SIZE;
	}

	static Board getInstance(){
		return INSTANCE;
	}
}
