package command;

import geometry.Shape;

public class SelectCmd implements Command{
	
	private Shape shape;
	private boolean wasSelected;
	

	public SelectCmd(Shape shape) {
		this.shape = shape;
		this.wasSelected = shape.isSelected();
	}

	@Override
	public void execute() {
		shape.setSelected(!wasSelected);
		
	}

	@Override
	public void unexecute() {
		shape.setSelected(wasSelected);
		
	}
	
	@Override
	public String toString() {
		return !wasSelected ? "Selected: " + shape.toString()
							: "Deselected: " + shape.toString();
	}

}
