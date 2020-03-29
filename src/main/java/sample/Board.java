package sample;


import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import lombok.Getter;

@Getter
class Board {
	private GridPane cellsGrid;
	private GridPane stageGrid;
	private ComboBox<String> stateCombobox;
	private TextField gridSizeField;
	private Button oneStepButton;
	private Button setSizeButton;
	private Button tenStepsButton;
	private final int BOARD_SIZE = 700;

	private static Board instance = null;

	private Board() {

		this.cellsGrid = new GridPane();
		this.stageGrid = new GridPane();
		GridPane menuGrid = new GridPane();

		//labels
		Label gridSizeLabel = new Label("Grid size: ");
		Label initialStateLabel = new Label("Initial state: ");

		//buttons
		this.oneStepButton = new Button("1 STEP");
		this.tenStepsButton = new Button("10 STEPS");
		this.setSizeButton = new Button("SET SIZE");

		//input
		this.stateCombobox= new ComboBox<>();
		this.stateCombobox.getItems().addAll("Blinker", "Bee-hive", "Glider", "Random", "Own states");
		this.stateCombobox.setValue("Blinker");
		this.gridSizeField = new TextField("20");

		//settings
		this.cellsGrid.setPadding(new Insets(10));
		menuGrid.setPadding(new Insets(10));
		menuGrid.setHgap(10);
		menuGrid.setVgap(10);
		this.stateCombobox.setMaxSize(100,10);
		this.gridSizeField.setMaxSize(100,10);

		menuGrid.addColumn(0, gridSizeLabel, initialStateLabel, oneStepButton,setSizeButton);
		menuGrid.addColumn(1, gridSizeField, stateCombobox, tenStepsButton);

		this.stageGrid.add(menuGrid, 0, 0);
		ScrollPane scrollPane = new ScrollPane(cellsGrid);
		this.stageGrid.add(scrollPane, 1, 0);
	}

	static Board getInstance(){
		if(instance == null) instance = new Board();
		return instance;
	}
}
