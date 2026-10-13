package command;

import geometry.Point;

public class UpdatePointCmd implements Command{
	
	private Point point;
	private Point newState;
	private Point oldState;
	
	public UpdatePointCmd(Point point, Point newState) {
		this.point = point;
		this.newState = newState;
	}

	@Override
	public void execute() {
		oldState = point.clone();
		point.setX(newState.getX());
		point.setY(newState.getY());
		point.setBorderColor(newState.getBorderColor());
	}

	@Override
	public void unexecute() {
		point.setX(oldState.getX());
		point.setY(oldState.getY());
		point.setBorderColor(oldState.getBorderColor());
		
	}

	@Override
	public String toString() {
		return "Modified: " + oldState.toString() + " >> " + point.toString();
	}
	
}
