package strategy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import mvc.DrawingController;
import mvc.DrawingFrame;

public class LogStrategy implements Strategy {
	
	DrawingFrame frame;
	DrawingController controller;
	
	
	public LogStrategy(DrawingFrame frame, DrawingController controller) {
		super();
		this.frame = frame;
		this.controller = controller;
	}

	public LogStrategy(DrawingFrame frame) {
		this.frame = frame;
	}

	@Override
	public void save(File file) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
			writer.write(frame.getTextLog().getText());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void open(File file) {
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			List<String> lines = new ArrayList<>();
			String line;
			
			while ((line = reader.readLine()) != null) {
				
				lines.add(line);
			}
			
			controller.setLoadedLines(lines);
			JOptionPane.showMessageDialog(null, "File loaded. Click OK to start executing commands.");
			controller.executeNextLine();
			
			        
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	

}
