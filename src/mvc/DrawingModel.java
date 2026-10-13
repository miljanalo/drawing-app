package mvc;

import java.awt.Color;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;

import geometry.Point;
import geometry.Shape;

public class DrawingModel {
	
	private PropertyChangeSupport propertyChangeSupport;
	
	public enum CurrentShape {
		POINT, LINE, RECTANGLE, CIRCLE, DONUT, HEXAGON, NONE
	}
	
	public CurrentShape currentShape = CurrentShape.NONE;      //izabrani oblik za crtanje
	
	private ArrayList<Shape> shapes = new ArrayList<Shape>();  //lista svih oblika
	
	private List<Shape> selectedShapes = new ArrayList<>();    //lista selektovanih oblika
	
	private Color currentBorderColor = new Color(64, 0, 128);
	private Color currentInnerColor = new Color(204, 204, 255);
	
	public DrawingModel() {
		propertyChangeSupport = new PropertyChangeSupport(this);
	}
	
	
	public void addShape(Shape shape){
		this.shapes.add(shape);
	}
	
    public void removeShape(Shape shape) {
        shapes.remove(shape);
    }
    
	public ArrayList<Shape> getShapes() {
		return shapes;
	}
	
	public void setShapes(ArrayList<Shape> shapes) {
		this.shapes = shapes;
	}
	
	public CurrentShape getCurrentShape() {
		return currentShape;
	}

	public void setCurrentShape(CurrentShape currentShape) {
		this.currentShape = currentShape;
	}

	public Shape getSelected(Point point) {
		Shape selected = null;
		Shape temp;
		Iterator<Shape> it = shapes.iterator();
		while (it.hasNext()) {
			temp = it.next();
			if (temp.contains(point.getX(), point.getY())) {
				selected = temp;
			}
			//temp.setSelected(false);
		}
		return selected;
	}
	
	public void delete() {
		Shape temp;
		Iterator<Shape> it = shapes.iterator();
		while (it.hasNext()) {
			temp = it.next();
			if (temp.isSelected()) {
				if (JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this shape?", "Delete object",
						JOptionPane.YES_NO_OPTION) == 0) {
					shapes.remove(temp);
					break;
				} else {
					temp.setSelected(false);
					break;
				}
			}
		}
	}

	public void updateShape(Shape oldShape, Shape newShape) {
		int index = shapes.indexOf(oldShape);
		if(index != -1) {
			shapes.set(index, newShape);
		}	
	}
	
	public List<Shape> getSelectedShapes() {
		return selectedShapes;
	}
	
	//observer
	
	public void addListener(PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(listener);
	}
	
	public void removeListener(PropertyChangeListener listener) {
		propertyChangeSupport.removePropertyChangeListener(listener);
	}
	
	public void notifySelectedShapeChanged(int selectedCount){
		propertyChangeSupport.firePropertyChange("selectedShape", null, selectedCount);
	}
	
	public void notifyUndoAvailable(boolean available) {
		propertyChangeSupport.firePropertyChange("undoAvailable", null, available);
	}
	
	public void notifyRedoAvailable(boolean available) {
		propertyChangeSupport.firePropertyChange("redoAvailable", null, available);
	}

	public Color getCurrentBorderColor() {
		return currentBorderColor;
	}


	public void setCurrentBorderColor(Color currentBorderColor) {
		this.currentBorderColor = currentBorderColor;
	}


	public Color getCurrentInnerColor() {
		return currentInnerColor;
	}


	public void setCurrentInnerColor(Color currentInnerColor) {
		this.currentInnerColor = currentInnerColor;
	}
	
}
