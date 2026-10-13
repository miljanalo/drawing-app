package geometry;

import java.awt.Color;
import java.awt.Graphics;

public class Line extends Shape{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Point startPoint;
	private Point endPoint;
	
	public Line() {}
	
	public Line(Point startPoint, Point endPoint) {
		this.startPoint = startPoint;
		this.endPoint = endPoint;
	}
	
	public Line(Point startPoint, Point endPoint, boolean selected) {
		this(startPoint, endPoint);
		this.selected = selected;
	}
	
	public double length() {
		double length = startPoint.distance(endPoint);
		return length;
	}
	
	public boolean equals(Object obj) {
		
		if(obj instanceof Line) {
			Line pom = (Line) obj;
			if(this.startPoint.equals(pom.startPoint) && this.endPoint.equals(pom.endPoint) && this.selected == pom.selected) {
				return true;
			}
			else return false;
		}
		else return false;
	}
	
	// contains
	
	public boolean contains(int x, int y) {
		Point sadrziTacku = new Point(x,y);
		return this.startPoint.distance(sadrziTacku)
				+ this.endPoint.distance(sadrziTacku) 
				- length() <= 2;
	}
	
		@Override
	public void draw(Graphics g) {
		g.setColor(borderColor);
		g.drawLine(startPoint.getX(), startPoint.getY(), endPoint.getX(), endPoint.getY());
		
		if(isSelected()) {
			g.setColor(Color.BLUE);
			g.drawRect(startPoint.getX()-2, startPoint.getY()-2, 4, 4);
			g.drawRect(endPoint.getX()-2, endPoint.getY()-2, 4, 4);
			}
		g.setColor(Color.black);
		
	}
		
		public void moveTo(int x, int y) {
			//ako bismo je implementirali 
			//od linije bismo dobili tacku
		}

		@Override
		public void moveBy(int x, int y) {
			startPoint.moveBy(x, y);
			endPoint.moveBy(x, y);
		}

		@Override
		public int compareTo(Object obj) {
			if(obj instanceof Line) {
				Line shapeToCompare = (Line)obj;
				return (int)(this.length() 
						- shapeToCompare.length());
			}
			return 0;
		}


	
	//getteri
	
	public Point getStartPoint() {
		return startPoint;
	}
	
	public Point getEndPoint() {
		return endPoint;
	}
	
	//setter
	
	public void setStartPoint(Point startPoint) {
		this.startPoint=startPoint;
	}
	
	public void setEndPoint(Point endPoint) {
		this.endPoint=endPoint;
	}
	
	// toString
	
	public String toString() {
		return "Line (" + startPoint.getX()  +  "," + startPoint.getY()  
		+ ")(" + endPoint.getX()  + "," + endPoint.getY()
		+ "); borderColor=" + borderColor.getRGB();
	}

	@Override
	public Line clone() {
		Line clone = new Line(this.getStartPoint().clone(), this.getEndPoint().clone());
		clone.setBorderColor(getBorderColor());
		clone.setSelected(this.isSelected());
		return clone;
	}

}
