package command;

import geometry.Shape;
import mvc.DrawingModel;

public class BringToBackCmd implements Command{
	
	private DrawingModel model;
	private Shape shape;
	private int oldIndex;
	
	public BringToBackCmd(DrawingModel model, Shape shape) {
		this.model = model;
		this.shape = shape;
	}

	@Override
	public void execute() {
		int index = model.getShapes().indexOf(shape);
		if(index > 0) {
			oldIndex = index;
			model.getShapes().remove(index);
			model.getShapes().add(0, shape);
		}
		
	}

	@Override
	public void unexecute() {
		model.getShapes().remove(model.getShapes().indexOf(shape));
		model.getShapes().add(oldIndex, shape);
		
	}
	
	public String toString() {
		return "Brought to back: " + shape.toString();
	}

}
