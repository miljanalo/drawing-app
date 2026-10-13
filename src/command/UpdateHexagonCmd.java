package command;

import adapter.HexagonAdapter;

public class UpdateHexagonCmd implements Command{
	
	private HexagonAdapter hexagon;
	private HexagonAdapter oldState;
	private HexagonAdapter newState;

	public UpdateHexagonCmd(HexagonAdapter hexagon, HexagonAdapter newState) {
		this.hexagon = hexagon;
		this.newState = newState;
	}

	@Override
	public void execute() {
		oldState = hexagon.clone();
		hexagon.setX(newState.getX());
		hexagon.setY(newState.getY());
		hexagon.setR(newState.getR());
		hexagon.setBorderColor(newState.getBorderColor());
		hexagon.setInnerColor(newState.getInnerColor());
		
	}

	@Override
	public void unexecute() {
		hexagon.setX(oldState.getX());
		hexagon.setY(oldState.getY());
		hexagon.setR(oldState.getR());
		hexagon.setBorderColor(oldState.getBorderColor());
		hexagon.setInnerColor(oldState.getInnerColor());
		
	}
	
	@Override
	public String toString() {
		return "Modified: " + oldState.toString() + " >> " + hexagon.toString();
	}

}
