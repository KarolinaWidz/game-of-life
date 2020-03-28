package sample;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

class Controller {

	private GridPane stageGrid2;
	private GridPane cellsGrid;
	private final int border = 700;
	private static final Controller INSTANCE = new Controller();

	public Controller() {
	}
	GridPane initBoard() {

		this.cellsGrid = new GridPane();
		GridPane stageGrid = new GridPane();
		GridPane menuGrid = new GridPane();

		//labels
		Label gridSizeLabel = new Label("Grid size: ");
		Label initialStateLabel = new Label("Initial state: ");

		//input
		ComboBox<String> stateCombobox = new ComboBox<>();
		stateCombobox.getItems().addAll("Blinker", "Bee-hive", "Glider", "Random", "Own states");
		stateCombobox.setValue("Blinker");
		this.cellsGrid.setPadding(new Insets(10));
		menuGrid.setPadding(new Insets(10));
		menuGrid.setHgap(10);
		menuGrid.setVgap(10);
		TextField gridSizeField = new TextField("20");
		stateCombobox.setMaxSize(100,10);
		gridSizeField.setMaxSize(100,10);

		//buttons
		Button startButton = new Button("START/STOP");
		Button tenStepsButton = new Button("10 STEPS");

		startButton.setOnAction((event) -> simulation());
		tenStepsButton.setOnAction((event -> simulation()));
		menuGrid.addColumn(0, gridSizeLabel, initialStateLabel, tenStepsButton);
		menuGrid.addColumn(1, gridSizeField, stateCombobox, startButton);
		for(int i = 0; i< 20; i++){
			for(int j = 0; j< 20; j++){
				cellsGrid.add(new Rectangle(this.border / 20, this.border / 20, Color.ROYALBLUE),i,j);
			}
		}
		this.cellsGrid.setGridLinesVisible(true);
		stageGrid.add(menuGrid, 0, 0);
		ScrollPane scrollPane = new ScrollPane(cellsGrid);
		stageGrid.add(scrollPane, 1, 0);
		return stageGrid;

	}
	private void simulation() {
		System.out.println("start");
	}

	static Controller getInstance(){
		return INSTANCE;
	}
}


