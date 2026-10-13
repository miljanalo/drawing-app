package command;

import geometry.Rectangle;

public class UpdateRectangleCmd implements Command{
	
	private Rectangle rect;
	private Rectangle oldState;
	private Rectangle newState;
	

	public UpdateRectangleCmd(Rectangle rect, Rectangle newState) {
		super();
		this.rect = rect;
		this.newState = newState;
	}

	@Override
	public void execute() {
		oldState = rect.clone();
		rect.getUpperLeftPoint().setX(newState.getUpperLeftPoint().getX());
		rect.getUpperLeftPoint().setY(newState.getUpperLeftPoint().getY());
		rect.setWidth(newState.getWidth());
		rect.setHeight(newState.getHeight());
		rect.setBorderColor(newState.getBorderColor());
		rect.setInnerColor(newState.getInnerColor());
		
	}

	@Override
	public void unexecute() {
		rect.getUpperLeftPoint().setX(oldState.getUpperLeftPoint().getX());
		rect.getUpperLeftPoint().setY(oldState.getUpperLeftPoint().getY());
		rect.setWidth(oldState.getWidth());
		rect.setHeight(oldState.getHeight());
		rect.setBorderColor(oldState.getBorderColor());
		rect.setInnerColor(oldState.getInnerColor());
		
	}
	
	@Override
	public String toString() {
		return "Modified: " + oldState.toString() + " >> " + rect.toString();
	}

}
