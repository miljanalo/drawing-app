package strategy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import geometry.Shape;
import mvc.DrawingModel;

public class SerializationStrategy implements Strategy {
	
	DrawingModel model;

	public SerializationStrategy(DrawingModel model) {
		this.model = model;
	}

	@Override
	public void save(File file) {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
			oos.writeObject(model.getShapes());
		} catch(IOException e){
			e.printStackTrace();
		}
		
	}

	@Override
	public void open(File file) {
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file)))
		{
			ArrayList<Shape> shapes = (ArrayList<Shape>) ois.readObject();
		    ois.close();
		    model.getShapes().clear();
		    model.getShapes().addAll(shapes);
		} catch (IOException | ClassNotFoundException e) {
		    e.printStackTrace();
		}
		
	}

}
