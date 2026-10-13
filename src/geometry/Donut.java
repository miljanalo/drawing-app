package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

public class Donut extends Circle{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		// nasledjuje center, radius i selected od Circle
		private int innerRadius;

		public Donut() {
		}

		public Donut(Point center, int radius, boolean selected) {
			super(center, radius);
			this.selected = selected;
		}

		public Donut(Point center, int radius, int innerRadius) {
			super(center, radius);
			this.innerRadius = innerRadius;
		}

		public Donut(Point center, int radius, int innerRadius, boolean selected) {
			this(center, radius, selected);
			this.innerRadius = innerRadius;
		}
		
		public double area() {
			return super.area() - innerRadius * innerRadius * Math.PI;
		}

		public double circumference() {
			return super.circumference() + 2 * innerRadius * Math.PI;
		}

		public boolean equals(Object obj) {

			if (obj instanceof Donut) {
				Donut pomocna = (Donut) obj;
				if (getCenter().equals(pomocna.getCenter()) && getRadius() == pomocna.getRadius()
						&& innerRadius == pomocna.getInnerRadius())
					return true;
				else
					return false;
			} else
				return false;
		}

		public boolean contains(int x, int y) {

			return super.contains(x, y) && getCenter().distance(new Point(x, y)) >= innerRadius;
		}

		public boolean contains(Point p) {

			return this.contains(p.getX(), p.getY());
		}
		
		public void draw(Graphics g) {
			/*super.draw(g);
			g.setColor(getBorderColor());
			g.drawOval(getCenter().getX()-innerRadius, getCenter().getY()-innerRadius, innerRadius*2, innerRadius*2);
			g.setColor(new Color(255, 255, 255));
			//g.setColor(getBorderColor());
			g.fillOval(getCenter().getX() - innerRadius, getCenter().getY() - innerRadius, 2 * innerRadius,
					2 * innerRadius);*/
			
			Graphics2D g2D = (Graphics2D) g;
			
			Ellipse2D outer = new Ellipse2D.Double(
				getCenter().getX() - getRadius(),
				getCenter().getY() - getRadius(),
				getRadius() * 2,
				getRadius() * 2
			);
			
			Ellipse2D inner = new Ellipse2D.Double(
				getCenter().getX() - innerRadius,
				getCenter().getY() - innerRadius,
				innerRadius * 2,
				innerRadius * 2
			);
			
			Area donutArea = new Area(outer);
			Area innerArea = new Area(inner);
			donutArea.subtract(innerArea);
			
			g2D.setColor(getInnerColor());
			g2D.fill(donutArea);
			
			g2D.setColor(getBorderColor());
			g2D.draw(outer);
			g2D.draw(inner);
			
			if (isSelected()) {
				g.setColor(Color.BLUE);
				g.drawRect(getCenter().getX() - 2, getCenter().getY() - 2, 4, 4);
				g.drawRect(getCenter().getX() - innerRadius - 2, getCenter().getY() - 2, 4, 4);
				g.drawRect(getCenter().getX() + innerRadius - 2, getCenter().getY() - 2, 4, 4);
				g.drawRect(getCenter().getX() - 2, getCenter().getY() - innerRadius - 2, 4, 4);
				g.drawRect(getCenter().getX() - 2, getCenter().getY() + innerRadius - 2, 4, 4);
			}
			g.setColor(Color.BLACK);
		}

		// ostale get i set nasledjuje iz Circle
		public int getInnerRadius() {
			return innerRadius;
		}

		public void setInnerRadius(int innerRadius) {
			this.innerRadius = innerRadius;
		}
		
		public int compareTo(Object obj) {
			if(obj instanceof Donut) {
				Donut shapeToCompare = (Donut)obj;
				return (int)(this.area() 
						- shapeToCompare.area());
			}
			return 0;
		}

		public String toString() {
			return "Donut (" + this.getCenter().getX() + "," + this.getCenter().getY() 
					+ "); radius=" + this.getRadius() + "; innerRadius=" +  innerRadius
					+ "; borderColor=" + borderColor.getRGB() 
			        + "; innerColor=" + innerColor.getRGB();
		}
		
		@Override
		public Donut clone() {
			Donut clone = new Donut(this.getCenter().clone(), this.getRadius(), this.getInnerRadius());
			clone.setBorderColor(getBorderColor());
			clone.setInnerColor(getInnerColor());
			clone.setSelected(this.isSelected());
			return clone;
		}

	}
