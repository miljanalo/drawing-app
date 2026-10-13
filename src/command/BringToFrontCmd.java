package command;

import geometry.Shape;
import mvc.DrawingModel;

public class BringToFrontCmd implements Command{
	
	private DrawingModel model;
	private Shape shape;
	private int oldIndex;
	
	public BringToFrontCmd(DrawingModel model, Shape shape) {
		this.model = model;
		this.shape = shape;
	}

	@Override
	public void execute() {
		int index = model.getShapes().indexOf(shape);
		if(index < model.getShapes().size()-1) {
			oldIndex = index;
			model.getShapes().remove(index);
			model.getShapes().add(shape);
		}
		
	}

	@Override
	public void unexecute() {
		model.getShapes().remove(model.getShapes().indexOf(shape));
		model.getShapes().add(oldIndex, shape);
	}
	
	public String toString() {
		return "Brought to front: " + shape.toString();
	}

}
