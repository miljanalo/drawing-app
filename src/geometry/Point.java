package geometry;

import java.awt.Color;
import java.awt.Graphics;

public class Point extends Shape{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int x;
	public int y;
	
	// konstruktori
	
	public Point() {
		
	}
	
	public Point(int x, int y) {
		this.x=x;
		this.y=y;
	}
	
	public Point(int x, int y, boolean selected) {
		this(x,y);
		this.selected = selected;
	}
	
	
	
	public double distance(Point otherPoint) {
		int distanceX = this.x-otherPoint.getX();
		int distanceY = this.y-otherPoint.getY();
		
		double distance=Math.sqrt(distanceX*distanceX+distanceY*distanceY);
		return distance;
	}
	
	// metoda equals
	
	public boolean equals(Object obj) {
		
		if(obj instanceof Point) {
			if(this.x == ((Point)obj).x && this.y == ((Point)obj).y && this.selected == ((Point)obj).selected) {
				return true;
			}
			
			else return false;
		}
		else return false;
	}
	
	// contains
	
	public boolean contains(int x, int y) {
		Point sadrziTacku = new Point(x,y);
		return this.distance(sadrziTacku) <= 2;
	}
	
		@Override
	public void draw(Graphics g) {
		g.setColor(borderColor);
		g.drawLine(x-2, y, x+2, y);
		g.drawLine(x, y-2, x, y+2);
		
		if (this.isSelected()) {

			g.setColor(Color.blue);
			g.drawRect(x-2, y-2, 4, 4);

		}
		g.setColor(Color.BLACK);
	}
		
		public void moveTo(int x, int y) {
			this.x=x;
			this.y=y;
		}

		@Override
		public void moveBy(int x, int y) {
			this.x+=x;
			this.y+=y;
		}

		@Override
		public int compareTo(Object o) {
			if(o instanceof Point) {
				Point shapeToCompare = (Point)o;
				return (int)this.distance(new Point(0,0))
						- (int)shapeToCompare
						.distance(new Point(0,0));
			}
			return 0;
		}

		
	//getteri za x i y koordinate
	
	public int getX(){
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	//setteri za postavljanje x i y koordinata
	
	public void setX(int x) {
		this.x=x;
	}
	
	public void setY(int y) {
		this.y=y;
	}
	
	// toString
	
	public String toString() {
		return "Point (" + this.x + "," + this.y + "); borderColor=" + borderColor.getRGB();
	}

	@Override
	public Point clone() {
		Point clone = new Point(this.getX(), this.getY());
		clone.setBorderColor(this.getBorderColor());
		clone.setSelected(this.isSelected());
		return clone;
	}



}