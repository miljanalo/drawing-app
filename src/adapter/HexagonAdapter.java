package adapter;

import java.awt.Color;
import java.awt.Graphics;
import geometry.Shape;
import hexagon.Hexagon;

public class HexagonAdapter extends Shape{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Hexagon hexagon;
	

	public HexagonAdapter(int x, int y, int radius) {
		this.hexagon = new Hexagon(x, y, radius);
	}

	@Override
	public void moveTo(int x, int y) {
		hexagon.setX(x);
		hexagon.setY(y);
	}

	@Override
	public void moveBy(int x, int y) {
		hexagon.setX(x);
		hexagon.setY(y);
	}

	@Override
	public int compareTo(Object o) {
		if(o instanceof HexagonAdapter) {
			HexagonAdapter shapeToCompare = (HexagonAdapter) o;
			return this.hexagon.getR() - shapeToCompare.hexagon.getR();
		}
		return 0;
	}

	@Override
	public boolean contains(int x, int y) {
		return hexagon.doesContain(x, y);
	}

	@Override
	public String toString() {
		return "Hexagon (" + hexagon.getX() + "," + hexagon.getY() + "); radius=" + hexagon.getR()
		+ "; borderColor=" + hexagon.getBorderColor().getRGB() 
        + "; innerColor=" + hexagon.getAreaColor().getRGB();
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof HexagonAdapter) {
			HexagonAdapter pom = (HexagonAdapter) obj;
			if(hexagon.getX() == pom.hexagon.getX()
				&& hexagon.getY() == pom.hexagon.getY()
				&& hexagon.getR() == pom.hexagon.getR()
				&& hexagon.isSelected() == pom.hexagon.isSelected()) {
				return true;
			}
			else return false;
		}
		else return false;
	}

	@Override
	public void draw(Graphics g) {
		hexagon.paint(g);
		
		if(this.isSelected()) {
			g.setColor(Color.BLUE);
			g.drawRect(hexagon.getX() - 2, hexagon.getY() - 2, 4, 4);
			
			for(int i=0; i< 6; i++) {
				double angle = Math.toRadians(60 * i);
		          int x = (int) (hexagon.getX() + hexagon.getR() * Math.cos(angle));
		          int y = (int) (hexagon.getY() + hexagon.getR() * Math.sin(angle));

		          g.drawRect(x - 2, y - 2, 4, 4);
			}
		}
		
		g.setColor(Color.BLACK);
		
	}

	@Override
	public HexagonAdapter clone() {
		HexagonAdapter clone = new HexagonAdapter(this.getX(), this.getY(), this.getR());
		clone.setBorderColor(this.getBorderColor());
		clone.setInnerColor(this.getInnerColor());
		clone.setSelected(this.isSelected());
		return clone;
		
	}

	public int getX() {
		return hexagon.getX(); 
	}
    
	public void setX(int x) {
		hexagon.setX(x); 
	}

    public int getY() {
    	return hexagon.getY(); 
    }
    
    public void setY(int y) {
    	hexagon.setY(y); 
    }

    public int getR() {
    	return hexagon.getR(); 
    }
    
    public void setR(int r) {
    	hexagon.setR(r); 
    }
    

    public boolean isSelected() {
    	return hexagon.isSelected(); 
    }

    public void setSelected(boolean selected) { 
        this.selected = selected;
        hexagon.setSelected(selected); 
    }
    
    public Color getBorderColor() {
    	return hexagon.getBorderColor();
    }
    
    public void setBorderColor(Color borderColor) {
    	hexagon.setBorderColor(borderColor);
    }
    
    public Color getInnerColor() {
    	return hexagon.getAreaColor();
    }
    
    public void setInnerColor(Color innerColor) {
    	hexagon.setAreaColor(innerColor);
    }

}
