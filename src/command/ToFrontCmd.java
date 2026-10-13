package command;

import geometry.Shape;
import mvc.DrawingModel;

public class ToFrontCmd implements Command{
	
	private DrawingModel model;
	private Shape shape;
	
	public ToFrontCmd(DrawingModel model, Shape shape){
		this.model = model;
		this.shape = shape;
	}

	@Override
	public void execute() {
		int index = model.getShapes().indexOf(shape);
		if(index < model.getShapes().size()-1) {
			model.getShapes().remove(index);
			model.getShapes().add(index + 1, shape);
		}
		
	}

	@Override
	public void unexecute() {
		int index = model.getShapes().indexOf(shape);
			model.getShapes().remove(index);
			model.getShapes().add(index - 1, shape);
	}
	
	public String toString() {
		return "Moved forward: " + shape.toString();
	}
}
