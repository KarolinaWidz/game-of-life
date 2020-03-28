package sample;

import javafx.scene.paint.Color;

public enum Cell {
	DEAD(false,Color.WHITE),
	ALIVE(true,Color.ROYALBLUE);

	private final Color color;
	private final Boolean state;

	Cell(Boolean state, Color color){
		this.state = state;
		this.color = color;
	}

	public Color getColor() {
		return color;
	}

	public Boolean getState() {
		return state;
	}
}
