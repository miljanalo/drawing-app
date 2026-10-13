package geometry;

import java.util.Arrays;

public class Test {
	
	public static void main(String[] args) {
	
	
	
	//Vezbe 4.
			Point novaTacka = new Point(10,15, true);
			System.out.println(novaTacka.getX());
			Point novaTacka2 = new Point(10,15, true);
			
			Line novaLinija = new Line(novaTacka, novaTacka2);
			//ovde sad nece biti null pointer exception
			System.out.println(novaLinija.getStartPoint().getX());
			
			//pre redefinianja toString() metode izbacuje referencu
			//nakon redefinisanja vraca (10,15)
			System.out.println(novaTacka.toString());
			
			Rectangle novirec = new Rectangle(novaTacka, 20, 15, true);
			System.out.println(novirec.toString());
			
			System.out.println(novaTacka.equals(novaTacka2));
			
			// APSTRAKCIJA U PROGRAMIRANJU
			//Shape shape1 = new Shape(); - nije moguce
			Shape shape1 = new Point(15,25);
			System.out.println(shape1.isSelected());
			
			Point p3 = new Point(50, 50);
			Rectangle r10 = new Rectangle(p3, 10, 10);
			Rectangle r20 = new Rectangle(p3, 20, 20);
			Rectangle r30 = new Rectangle(p3, 30, 30);
			Rectangle[] rectangles = { r30, r10, r20 };
			System.out.println("\nNesortiran niz: ");
			for (int i = 0; i < rectangles.length; i++) {
				System.out.println(rectangles[i]);
			}
			// u pozadini sortira po compareTo
			Arrays.sort(rectangles);
			System.out.println("Sortiran niz: ");
			for (int i = 0; i < rectangles.length; i++) {
				System.out.println(rectangles[i]);
			}

	}
}
