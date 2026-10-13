package command;

import geometry.Shape;
import mvc.DrawingModel;

public class ToBackCmd implements Command {
	
	private DrawingModel model;
	private Shape shape;
	
	public ToBackCmd(DrawingModel model, Shape shape) {
		this.model = model;
		this.shape = shape;
	}

	@Override
	public void execute() {
		int index = model.getShapes().indexOf(shape);
		if(index > 0) {
			model.getShapes().remove(index);
			model.getShapes().add(index - 1, shape);
		}
	}

	@Override
	public void unexecute() {
		int index = model.getShapes().indexOf(shape);
			model.getShapes().remove(index);
			model.getShapes().add(index + 1, shape);
	}
	
	public String toString() {
		return "Moved backward: " + shape.toString();
	}

}
