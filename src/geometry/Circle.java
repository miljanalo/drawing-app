package geometry;

import java.awt.Color;
import java.awt.Graphics;

public class Circle extends Shape{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Point center;
	private int radius;

	public Circle() {
		
	}
	
	public Circle(Point center, int radius) {
		this.center = center;
		this.radius = radius;
	}
	
	public Circle(Point center, int radius, boolean selected) {
		this(center, radius);
		this.selected = selected;
	}
	
	// povrsna i obim
	
	public double area() {
		return radius*radius*Math.PI;
	}
	
	public double circumference() {
		return 2*radius*Math.PI;
	}
	
	// equals
	
	public boolean equals(Object obj) {
		if(obj instanceof Circle) {
			Circle pom = (Circle) obj;
			if(this.center.equals(pom.center) && this.radius == pom.radius && this.selected == pom.selected) {
				return true;
			}
			else return false;
		}
		else return false;
	}
	
	// contains
	
	public boolean contains(int x, int y) {
		Point sadrziTacku = new Point(x,y);
		return (this.center.
				distance(sadrziTacku) <= this.radius);
	}
	
	public boolean contains(Point sadrziTacku) {
		return (this.center.
				distance(sadrziTacku) <= this.radius);
	}
	
		@Override
	public void draw(Graphics g) {
			g.setColor(innerColor);
			g.fillOval(center.getX() - radius, center.getY() - radius, 2 * radius, 2 * radius);
			g.setColor(borderColor);
			g.drawOval(center.getX()-radius, center.getY()-radius, radius*2, radius*2);
			
			if (isSelected()) {
				g.setColor(Color.BLUE);
				g.drawRect(center.getX() - 2, center.getY() - 2, 4, 4);
				g.drawRect(center.getX() - radius - 2, center.getY() - 2, 4, 4);
				g.drawRect(center.getX() + radius - 2, center.getY() - 2, 4, 4);
				g.drawRect(center.getX() - 2, center.getY() - radius - 2, 4, 4);
				g.drawRect(center.getX() - 2, center.getY() + radius - 2, 4, 4);
			}
			g.setColor(Color.BLACK);
	}
		
		public void moveTo(int x, int y) {
			center.moveTo(x, y);
		}

		@Override
		public void moveBy(int x, int y) {
			center.moveBy(x, y);
		}

		@Override
		public int compareTo(Object obj) {
			if(obj instanceof Circle) {
				Circle shapeToCompare = (Circle)obj;
				return (int)(this.area() 
						- shapeToCompare.area());
			}
			return 0;
		}

		
	// getteri i setteri
	
	public Point getCenter() {
		return center;
	}
	public void setCenter(Point center) {
		this.center = center;
	}
	public int getRadius() {
		return radius;
	}
	public void setRadius(int radius) {
		this.radius = radius;
	}
	
	public boolean isSelected() {
		return selected;
	}
	
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	// toString
	
	public String toString() {
		return "Circle (" + center.getX() + "," 
				+ center.getY() + "); radius=" + radius
				+ "; borderColor=" + borderColor.getRGB() 
		        + "; innerColor=" + innerColor.getRGB();
	}

	@Override
	public Circle clone() {
		Circle clone = new Circle(this.getCenter().clone(), this.getRadius());
		clone.setBorderColor(getBorderColor());
		clone.setInnerColor(getInnerColor());
		clone.setSelected(this.isSelected());
		return clone;
	}



}
