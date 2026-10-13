package command;

import geometry.Donut;

public class UpdateDonutCmd implements Command{
	
	private Donut donut;
	private Donut oldState;
	private Donut newState;
	

	public UpdateDonutCmd(Donut donut, Donut newState) {
		super();
		this.donut = donut;
		this.newState = newState;
	}

	@Override
	public void execute() {
		oldState = donut.clone();
		donut.getCenter().setX(newState.getCenter().getX());
		donut.getCenter().setY(newState.getCenter().getY());
		donut.setRadius(newState.getRadius());
		donut.setInnerRadius(newState.getInnerRadius());
		donut.setBorderColor(newState.getBorderColor());
		donut.setInnerColor(newState.getInnerColor());
		
	}

	@Override
	public void unexecute() {
		donut.getCenter().setX(oldState.getCenter().getX());
		donut.getCenter().setY(oldState.getCenter().getY());
		donut.setRadius(oldState.getRadius());
		donut.setInnerRadius(oldState.getInnerRadius());
		donut.setBorderColor(oldState.getBorderColor());
		donut.setInnerColor(oldState.getInnerColor());
		
	}
	
	@Override
	public String toString() {
		return "Modified: " + oldState.toString() + " >> " + donut.toString();
	}

}
