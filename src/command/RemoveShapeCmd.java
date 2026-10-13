package command;

import java.util.ArrayList;
import java.util.List;

import geometry.Shape;
import mvc.DrawingModel;

public class RemoveShapeCmd implements Command{
	
	private DrawingModel model;
	private List<Shape> shapes;
	private List<Integer> indexes;
	
	public RemoveShapeCmd(DrawingModel model, List<Shape> shapes) {
		this.model = model;
		this.shapes = new ArrayList<>(shapes);
		this.indexes = new ArrayList<>();
		for(Shape shape : this.shapes) {
			indexes.add(model.getShapes().indexOf(shape));
		}
	}

	@Override
	public void execute() {
		for(Shape shape : shapes) {
			model.getShapes().remove(shape);
		}
		
	}

	@Override
	public void unexecute() {
		List<Integer> sortedIndexes = new ArrayList<>(indexes);
		List<Shape> sortedShapes = new ArrayList<>(shapes);
		
		for(int i=0; i<sortedIndexes.size()-1; i++) {
			for(int j= 0; j<sortedIndexes.size() - i - 1; j++) {
				if(sortedIndexes.get(j) > sortedIndexes.get(j+1)) {
					int temp = sortedIndexes.get(j);
					sortedIndexes.set(j, sortedIndexes.get(j+1));
					sortedIndexes.set(j+1, temp);
					
					Shape tempShape = sortedShapes.get(j);
					sortedShapes.set(j, sortedShapes.get(j+1));
					sortedShapes.set(j+1, tempShape);
				}
			}
		}
		
		
		for(int i =0; i< sortedShapes.size(); i++) {
			model.getShapes().add(sortedIndexes.get(i), sortedShapes.get(i));
		}
		
	}

	public List<Shape> getShapes() {
		return shapes;
	}
	
	public String toString() {
		String string = "Deleted: ";
		for(int i=0; i<shapes.size(); i++) {
			 string += shapes.get(i).toString();
			 
			 if(i<shapes.size() - 1) {
				 string += " | ";
			 }
		}
		
		return string;
		
	}

	

}
