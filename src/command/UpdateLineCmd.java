package command;

import geometry.Line;

public class UpdateLineCmd implements Command{
	
	private Line line;
	private Line newState;
	private Line oldState;
	
	public UpdateLineCmd(Line line, Line newState) {
		this.line = line;
		this.newState = newState;
	}

	@Override
	public void execute() {
		oldState = line.clone();
		line.getStartPoint().setX(newState.getStartPoint().getX());
		line.getStartPoint().setY(newState.getStartPoint().getY());
		line.getEndPoint().setX(newState.getEndPoint().getX());
		line.getEndPoint().setY(newState.getEndPoint().getY());
		line.setBorderColor(newState.getBorderColor());
		
	}

	@Override
	public void unexecute() {
		line.getStartPoint().setX(oldState.getStartPoint().getX());
		line.getStartPoint().setY(oldState.getStartPoint().getY());
		line.getEndPoint().setX(oldState.getEndPoint().getX());
		line.getEndPoint().setY(oldState.getEndPoint().getY());
		line.setBorderColor(oldState.getBorderColor());
	}
	
	@Override
	public String toString() {
		return "Modified: " + oldState.toString() + " >> " + line.toString();
	}

}
