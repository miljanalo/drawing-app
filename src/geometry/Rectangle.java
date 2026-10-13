package geometry;

import java.awt.Color;
import java.awt.Graphics;

public class Rectangle extends Shape{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Point upperLeftPoint;
	private int width;
	private int height;
	
	// konstruktori
	
	public Rectangle() {
		
	}
	
	public Rectangle(Point upperLeftPoint, int width, int height) {
		this.upperLeftPoint = upperLeftPoint;
		this.width = width;
		this.height = height;
	}
	
	public Rectangle(Point upperLeftPoint, int width, int height, boolean selected) {
		this(upperLeftPoint, width, height);
		this.selected = selected;
	}
	
	// povrsina i obim
	
	public int area() {
		return width*height;
	}
	
	public int circumference() {
		return width*2+height*2;
	}
	
	// metoda equals
	
	public boolean equals(Object obj) {
		if(obj instanceof Rectangle) {
			Rectangle pom = (Rectangle) obj;
			if (this.upperLeftPoint.equals(pom.upperLeftPoint) 
					&& this.width == pom.width 
					&& this.height == pom.height
					&& this.selected == pom.selected) {
				return true;
			}	
			else return false;
		}
		else return false;
	}
	
	// contains
	
	public boolean contains(int x, int y) {
		return (x >= this.upperLeftPoint.getX() 
				&& x <= this.upperLeftPoint.getX() 
				+ width
				&& y >= this.upperLeftPoint.getY()
				&& y <= this.upperLeftPoint.getY()
				+ height);
	}
	
	public boolean contains(Point point1) {
		return  (point1.getX() >= this.upperLeftPoint.getX() 
				&& point1.getX() <= this.upperLeftPoint.getX() 
				+ width
				&& point1.getY() >= this.upperLeftPoint.getY()
				&& point1.getY() <= this.upperLeftPoint.getY()
				+ height);
	}
	
		@Override
	public void draw(Graphics g) {
		g.setColor(innerColor);
		g.fillRect(upperLeftPoint.getX(), upperLeftPoint.getY(), width, height);
		g.setColor(borderColor);
		g.drawRect(upperLeftPoint.getX(), upperLeftPoint.getY(), width, height);
		
		if (isSelected()) {
			g.setColor(Color.BLUE);
			g.drawRect(upperLeftPoint.getX() - 2, upperLeftPoint.getY() - 2, 4, 4);
			g.drawRect(upperLeftPoint.getX() + width - 2, upperLeftPoint.getY() - 2, 4, 4);
			g.drawRect(upperLeftPoint.getX() - 2, upperLeftPoint.getY() + height - 2, 4, 4);
			g.drawRect(upperLeftPoint.getX() + width - 2, upperLeftPoint.getY() 
					+ height - 2, 4, 4);
		}
		
		g.setColor(Color.BLACK);
	}
		
		public void moveTo(int x, int y) {
			upperLeftPoint.moveTo(x, y);
		}

		@Override
		public void moveBy(int x, int y) {
			upperLeftPoint.moveBy(x, y);
		}

		@Override
		public int compareTo(Object obj) {
			if(obj instanceof Rectangle) {
				Rectangle shapeToCompare = (Rectangle)obj;
				return this.area() 
						- shapeToCompare.area();
			}
			return 0;
		}

		
	// getteri i setteri
	
	public Point getUpperLeftPoint() {
		return upperLeftPoint;
	}
	
	public void setUpperLeftPoint(Point upperLeftPoint) {
		this.upperLeftPoint = upperLeftPoint;
	}
	
	public int getWidth() {
		return width;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	// toString
	
	public String toString() {
		return "Rectangle (" + upperLeftPoint.getX()
				+ "," + upperLeftPoint.getY()
				+ "); width=" + width 
				+ "; height=" + height
				+ "; borderColor=" + borderColor.getRGB()
				+ "; innerColor=" + innerColor.getRGB();
	}

	@Override
	public Rectangle clone() {
		Rectangle clone = new Rectangle(this.getUpperLeftPoint().clone(), this.getWidth(), this.getHeight());
		clone.setBorderColor(getBorderColor());
		clone.setInnerColor(getInnerColor());
		clone.setSelected(this.isSelected());
		return clone;
	}



}
