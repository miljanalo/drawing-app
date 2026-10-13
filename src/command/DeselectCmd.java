package command;

import java.util.ArrayList;
import java.util.List;

import geometry.Shape;

public class DeselectCmd implements Command{
	
	private List<Shape> shapes;
	
	public DeselectCmd(List<Shape> shapes) {
		this.shapes = new ArrayList<>(shapes);
	}

	@Override
	public void execute() {
		for (Shape s: shapes) {
			s.setSelected(false);
		}
		
	}

	@Override
	public void unexecute() {
		for (Shape s: shapes) {
			s.setSelected(true);
		}
		
	}
	
	public String toString() {
		String string = "Deselected: ";
		for(int i=0; i<shapes.size(); i++) {
			 string += shapes.get(i).toString();
			 
			 if(i<shapes.size() - 1) {
				 string += " | ";
			 }
		}
		
		return string;
	}

}
