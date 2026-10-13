package command;

import geometry.Circle;

public class UpdateCircleCmd implements Command{
	
	private Circle circle;
	private Circle oldState;
	private Circle newState;
	
	
	public UpdateCircleCmd(Circle circle, Circle newState) {
		this.circle = circle;
		this.newState = newState;
	}

	@Override
	public void execute() {
		oldState = circle.clone();
		circle.getCenter().setX(newState.getCenter().getX());
		circle.getCenter().setY(newState.getCenter().getY());
		circle.setRadius(newState.getRadius());
		circle.setBorderColor(newState.getBorderColor());
		circle.setInnerColor(newState.getInnerColor());
		
	}

	@Override
	public void unexecute() {
		circle.getCenter().setX(oldState.getCenter().getX());
		circle.getCenter().setY(oldState.getCenter().getY());
		circle.setRadius(oldState.getRadius());
		circle.setBorderColor(oldState.getBorderColor());
		circle.setInnerColor(oldState.getInnerColor());
		
	}
	
	@Override
	public String toString() {
		return "Modified: " + oldState.toString() + " >> " + circle.toString();
	}

}
