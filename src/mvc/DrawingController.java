package mvc;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import adapter.HexagonAdapter;
import command.AddShapeCmd;
import command.BringToBackCmd;
import command.BringToFrontCmd;
import command.Command;
import command.DeselectCmd;
import command.RemoveShapeCmd;
import command.SelectCmd;
import command.ToBackCmd;
import command.ToFrontCmd;
import command.UpdateCircleCmd;
import command.UpdateDonutCmd;
import command.UpdateHexagonCmd;
import command.UpdateLineCmd;
import command.UpdatePointCmd;
import command.UpdateRectangleCmd;
import drawing.DlgCircle;
import drawing.DlgDonut;
import drawing.DlgHexagon;
import drawing.DlgLine;
import drawing.DlgPoint;
import drawing.DlgRectangle;
import geometry.Circle;
import geometry.Donut;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;
import strategy.LogStrategy;
import strategy.SerializationStrategy;
import strategy.Strategy;

public class DrawingController {
	
	private DrawingModel model;
	private DrawingFrame frame;
	
	private Point startPoint = null;
	
	private Stack<Command> undoStack = new Stack<>();
	private Stack<Command> redoStack = new Stack<>();
	
	private List<String> loadedLines = new ArrayList<>();
	private int currentLineIndex = 0;
	
	public DrawingController(DrawingModel model, DrawingFrame frame) {
		this.model = model;
		this.frame = frame;	
		registerListeners();
		registerObservers();
	}
	
	private void registerListeners() {
	
		frame.getTglbtnPoint().addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseClicked(MouseEvent e) {
	    		if(model.getCurrentShape() == DrawingModel.CurrentShape.POINT) {
	    			frame.getButtonGroup().clearSelection();
	    			model.setCurrentShape(DrawingModel.CurrentShape.NONE);
	    		} else {
	    			model.setCurrentShape(DrawingModel.CurrentShape.POINT);
	    		}
	    	}
	    });
		
		frame.getTglbtnLine().addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseClicked(MouseEvent e) {
	    		if(model.getCurrentShape() == DrawingModel.CurrentShape.LINE) {
	    			frame.getButtonGroup().clearSelection();
	    			model.setCurrentShape(DrawingModel.CurrentShape.NONE);
	    		} else {
	    			model.setCurrentShape(DrawingModel.CurrentShape.LINE);
	    		}
	    	}
	    });
		
		frame.getTglbtnRectangle().addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		if(model.getCurrentShape() == DrawingModel.CurrentShape.RECTANGLE) {
	    			frame.getButtonGroup().clearSelection();
	    			model.setCurrentShape(DrawingModel.CurrentShape.NONE);
	    		} else {
	    			model.setCurrentShape(DrawingModel.CurrentShape.RECTANGLE);
	    		}
        	}
        });
		
		frame.getTglbtnCircle().addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		if(model.getCurrentShape() == DrawingModel.CurrentShape.CIRCLE) {
	    			frame.getButtonGroup().clearSelection();
	    			model.setCurrentShape(DrawingModel.CurrentShape.NONE);
	    		} else {
	    			model.setCurrentShape(DrawingModel.CurrentShape.CIRCLE);
	    		}
        	}
        });
		
		frame.getTglbtnDonut().addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		if(model.getCurrentShape() == DrawingModel.CurrentShape.DONUT) {
	    			frame.getButtonGroup().clearSelection();
	    			model.setCurrentShape(DrawingModel.CurrentShape.NONE);
	    		} else {
	    			model.setCurrentShape(DrawingModel.CurrentShape.DONUT);
	    		}
        	}
        });
		
		frame.getTglbtnHexagon().addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		if(model.getCurrentShape() == DrawingModel.CurrentShape.HEXAGON) {
	    			frame.getButtonGroup().clearSelection();
	    			model.setCurrentShape(DrawingModel.CurrentShape.NONE);
	    		} else {
	    			model.setCurrentShape(DrawingModel.CurrentShape.HEXAGON);
	    		}
        	}
        });
		
		//----------------------------------------------------------
		
		frame.getBtnDelete().addActionListener(e -> {
        		if (!model.getSelectedShapes().isEmpty()) {
        			executeCommand(new RemoveShapeCmd(model, model.getSelectedShapes()));
        			model.getSelectedShapes().clear();
        			model.notifySelectedShapeChanged(0);        				
    					
    			} else {
    					JOptionPane.showMessageDialog(null, "Please select the shape you want to delete.", "Error message", JOptionPane.OK_OPTION);
    			}
    				frame.repaint();
    				frame.getButtonGroup().clearSelection();
        	}
        );
		
		frame.getBtnModify().addActionListener(e -> {
        		if(!model.getSelectedShapes().isEmpty()) {
        			Shape selectedShape = model.getSelectedShapes().get(0);
        		
        			
        			if(selectedShape instanceof Point) {
        				Point selectedPoint = (Point) selectedShape;
        				
        				DlgPoint dlgPoint = new DlgPoint(selectedPoint);
        				dlgPoint.setBorderColor(selectedPoint.getBorderColor());
        				
            			dlgPoint.setVisible(true);
            			
            			if(dlgPoint.isOk()) {
            				Point newState = new Point(
            					Integer.parseInt(dlgPoint.getTxtX().getText()),
								Integer.parseInt(dlgPoint.getTxtY().getText())
            				);
            				newState.setBorderColor(dlgPoint.getBorderColor());
            				
            				executeCommand(new UpdatePointCmd(selectedPoint, newState));
							//newState.setSelected(true);
							selectedShape.setSelected(true);
            			}
        			}
        			
        			else if(selectedShape instanceof Line) {
        				Line selectedLine = (Line) selectedShape;
        				
        				DlgLine dlgLine = new DlgLine(selectedLine);
        				dlgLine.setBorderColor(selectedLine.getBorderColor());
    					
            			dlgLine.setVisible(true);
            			
            			if(dlgLine.isOk()) {
            				Line newState = new Line(
            					new Point(Integer.parseInt(dlgLine.getTxtXStart().getText()),
            							  Integer.parseInt(dlgLine.getTxtYStart().getText())),
            					new Point(Integer.parseInt(dlgLine.getTxtXEnd().getText()),
            							  Integer.parseInt(dlgLine.getTxtYEnd().getText()))
            				);
            				newState.setBorderColor(dlgLine.getBorderColor());
            				
							executeCommand(new UpdateLineCmd(selectedLine, newState));
							selectedShape.setSelected(true);
            			}
        			}
        			
        			else if(selectedShape instanceof Rectangle) {
        				Rectangle selectedRect = (Rectangle) selectedShape;
        				
        				DlgRectangle dlgRectangle = new DlgRectangle(selectedRect);
    					
        				dlgRectangle.getTxtWidth().setText(Integer.toString(selectedRect.getWidth()));
        				dlgRectangle.getTxtHeight().setText(Integer.toString(selectedRect.getHeight()));
        				dlgRectangle.setBorderColor(selectedRect.getBorderColor());
        				dlgRectangle.setInnerColor(selectedRect.getInnerColor());
        				
            			dlgRectangle.setVisible(true);
            			
            			if(dlgRectangle.isOk()) {
            				Rectangle newState = new Rectangle(
            						new Point(Integer.parseInt(dlgRectangle.getTxtXRect().getText()),
            								  Integer.parseInt(dlgRectangle.getTxtYRect().getText())),
            						Integer.parseInt(dlgRectangle.getTxtWidth().getText()),
            						Integer.parseInt(dlgRectangle.getTxtHeight().getText())
            						);
            				
            				newState.setBorderColor(dlgRectangle.getBorderColor());
							newState.setInnerColor(dlgRectangle.getInnerColor());
							
							executeCommand(new UpdateRectangleCmd(selectedRect, newState));
							selectedShape.setSelected(true);
            			}
        			}
        			
        			else if(selectedShape instanceof Donut) {
        				Donut selectedDonut = (Donut) selectedShape;
        				
        				DlgDonut dlgDonut = new DlgDonut(selectedDonut);
        				
        				dlgDonut.getTxtRadius().setText(Integer.toString(selectedDonut.getRadius()));
        				dlgDonut.getTxtInnerRadius().setText(Integer.toString(selectedDonut.getInnerRadius()));
        				dlgDonut.setBorderColor(selectedDonut.getBorderColor());
        				dlgDonut.setInnerColor(selectedDonut.getInnerColor());
        				
        				dlgDonut.setVisible(true);
        				
        				if(dlgDonut.isOk()) {
        					Donut newState = new Donut(
        							new Point(Integer.parseInt(dlgDonut.getTxtXDonutCenter().getText()),
        									  Integer.parseInt(dlgDonut.getTxtYDonutCenter().getText())),
        							Integer.parseInt(dlgDonut.getTxtRadius().getText()),
        							Integer.parseInt(dlgDonut.getTxtInnerRadius().getText())
        					);
        					newState.setBorderColor(dlgDonut.getBorderColor());
        					newState.setInnerColor(dlgDonut.getInnerColor());
        					
        					/*try {
								selectedDonut.setRadius();
							} catch (NumberFormatException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}  ????*/
        					
        					executeCommand(new UpdateDonutCmd(selectedDonut, newState));
							selectedShape.setSelected(true);
						}
        				
        			}
        			
        				else if(selectedShape instanceof Circle) {
        					Circle selectedCircle = (Circle) selectedShape;
        					
        					DlgCircle dlgCircle = new DlgCircle(selectedCircle);
         					dlgCircle.getTxtRadius().setText(Integer.toString(selectedCircle.getRadius()));
         					dlgCircle.setBorderColor(selectedCircle.getBorderColor());
         					dlgCircle.setInnerColor(selectedCircle.getInnerColor());
         					
         					dlgCircle.setVisible(true);
            			
         					if(dlgCircle.isOk()) {
         						
         						Circle newState = new Circle(
         								new Point(Integer.parseInt(dlgCircle.getTxtXCenter().getText()),
         										Integer.parseInt(dlgCircle.getTxtYCenter().getText())),
         								Integer.parseInt(dlgCircle.getTxtRadius().getText())
         						);
         						newState.setBorderColor(dlgCircle.getBorderColor());
         						newState.setInnerColor(dlgCircle.getInnerColor());
         						
         						/*try {
        							selectedCircle.setRadius(Integer.parseInt(dlgCircle.getTxtRadius().getText()));
        						} catch (Exception e1) {
        							// TODO Auto-generated catch block
        							e1.printStackTrace();
        						}*/
         						
         						executeCommand(new UpdateCircleCmd(selectedCircle, newState));
         						selectedShape.setSelected(true);
         					}
        				}
        			
        				else if(selectedShape instanceof HexagonAdapter) {
        					HexagonAdapter selectedHexagon = (HexagonAdapter) selectedShape;
        					
        					DlgHexagon dlgHexagon = new DlgHexagon(selectedHexagon);
        					dlgHexagon.getTxtRadius().setText(Integer.toString(selectedHexagon.getR()));
        					dlgHexagon.setBorderColor(selectedHexagon.getBorderColor());
        					dlgHexagon.setInnerColor(selectedHexagon.getInnerColor());
         					
        					dlgHexagon.setVisible(true);
            			
         					if(dlgHexagon.isOk()) {
         						
         						HexagonAdapter newState = new HexagonAdapter(
         								Integer.parseInt(dlgHexagon.getTxtXCenter().getText()),
         								Integer.parseInt(dlgHexagon.getTxtYCenter().getText()),
         								Integer.parseInt(dlgHexagon.getTxtRadius().getText())
         						);
         						newState.setBorderColor(dlgHexagon.getBorderColor());
         						newState.setInnerColor(dlgHexagon.getInnerColor());
         						
         						
         						executeCommand(new UpdateHexagonCmd(selectedHexagon, newState));
         						selectedShape.setSelected(true);
         					}
        				}
        			
        			
        			}
        		
        		else {
        				JOptionPane.showMessageDialog(null, "Please select the shape you want to modify.", "", JOptionPane.ERROR_MESSAGE);
        		}
        			
    				frame.repaint();
    				
    				model.notifySelectedShapeChanged(model.getSelectedShapes().size());
        	}
        	
        );
		
		//----------------------------------------------------------
		
		frame.getBtnUndo().addActionListener(e -> undo());
		
		frame.getBtnRedo().addActionListener(e -> redo());
		
		//----------------------------------------------------------
		
		frame.getBtnToFront().addActionListener(e -> {
        		if(!model.getSelectedShapes().isEmpty()) {
        			executeCommand(new ToFrontCmd(model, model.getSelectedShapes().get(0)));
        			model.notifySelectedShapeChanged(model.getSelectedShapes().size());
        		} else {
        			JOptionPane.showMessageDialog(null, "Please select a shape.", "Error", JOptionPane.ERROR_MESSAGE);
        		}
        	}
        );
		
		frame.getBtnBringToFront().addActionListener(e -> {
        		if(!model.getSelectedShapes().isEmpty()) {
        			executeCommand(new BringToFrontCmd(model, model.getSelectedShapes().get(0)));
        			model.notifySelectedShapeChanged(model.getSelectedShapes().size());
        		} else {
        			JOptionPane.showMessageDialog(null, "Please select a shape.", "Error", JOptionPane.ERROR_MESSAGE);
        		}
        	}
        );
		
		frame.getBtnToBack().addActionListener(e -> {
        		if(!model.getSelectedShapes().isEmpty()) {
        			executeCommand(new ToBackCmd(model, model.getSelectedShapes().get(0)));
        			model.notifySelectedShapeChanged(model.getSelectedShapes().size());
        		} else {
        			JOptionPane.showMessageDialog(null, "Please select a shape.", "Error", JOptionPane.ERROR_MESSAGE);
        		}
        	}
        );
		
		frame.getBtnBringToBack().addActionListener(e -> {
        		if(!model.getSelectedShapes().isEmpty()) {
        			executeCommand(new BringToBackCmd(model, model.getSelectedShapes().get(0)));
        			model.notifySelectedShapeChanged(model.getSelectedShapes().size());
        		} else {
        			JOptionPane.showMessageDialog(null, "Please select a shape.", "Error", JOptionPane.ERROR_MESSAGE);
        		}
        	}
        );
		
		//----------------------------------------------------------
		
		frame.getView().addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		switch(model.getCurrentShape()) {
        		
        		case POINT:
        			startPoint = null;
        			Point point = new Point(e.getX(), e.getY());
        			DlgPoint dlgPoint = new DlgPoint(point);
        			
        			dlgPoint.getTxtX().setEditable(false);
					dlgPoint.getTxtY().setEditable(false);
					
        			dlgPoint.setVisible(true);
        			if(dlgPoint.isOk()) {
        				point.setBorderColor(dlgPoint.getBorderColor()!= null ? dlgPoint.getBorderColor() : model.getCurrentBorderColor());
        				executeCommand(new AddShapeCmd(model, point));
        			}
        			
        			break;
        			
        		case LINE:
        			if(startPoint == null) {
        				startPoint = new Point(e.getX(), e.getY());
        			}
        			else {
        				Line line = new Line(startPoint, new Point(e.getX(), e.getY()));
        				DlgLine dlgLine = new DlgLine(line);
        				
        				dlgLine.getTxtXStart().setEditable(false);
    					dlgLine.getTxtYStart().setEditable(false);
    					dlgLine.getTxtXEnd().setEditable(false);
    					dlgLine.getTxtYEnd().setEditable(false);
    					
    					dlgLine.setVisible(true);
    					
    					startPoint = null;
    					
    					if(dlgLine.isOk()) {
    						line.setBorderColor(dlgLine.getBorderColor()!= null ? dlgLine.getBorderColor() : model.getCurrentBorderColor());
    						executeCommand(new AddShapeCmd(model, line));
    						//model.setCurrentShape(DrawingModel.CurrentShape.NONE);
    					}
    					//frame.getButtonGroup().clearSelection();
        			}
        			break;
        			
        		case RECTANGLE:
        			startPoint = null;
        			Point upperLeftPoint = new Point(e.getX(), e.getY());
        			Rectangle rectangle = new Rectangle(upperLeftPoint, 0, 0);
					DlgRectangle dlgRectangle = new DlgRectangle(rectangle);
					
					dlgRectangle.getTxtXRect().setEditable(false);
    				dlgRectangle.getTxtYRect().setEditable(false);
					
    				dlgRectangle.setVisible(true);
					
					if(dlgRectangle.isOk()) {
							
						int height = Integer.parseInt(dlgRectangle.getTxtHeight().getText());
						int width = Integer.parseInt(dlgRectangle.getTxtWidth().getText());
							
						Rectangle rect = new Rectangle(upperLeftPoint, width, height);
						
						rect.setBorderColor(dlgRectangle.getBorderColor()!= null ? dlgRectangle.getBorderColor() : model.getCurrentBorderColor());
						rect.setInnerColor(dlgRectangle.getInnerColor()!= null ? dlgRectangle.getInnerColor() : model.getCurrentInnerColor());
						executeCommand(new AddShapeCmd(model, rect));
						//model.setCurrentShape(DrawingModel.CurrentShape.NONE);
					}
					//frame.getButtonGroup().clearSelection();
        			break;
        			
        		case CIRCLE:
        			startPoint = null;
        			Point center = new Point(e.getX(), e.getY());
        			Circle circle = new Circle(center, 0);
        			DlgCircle dlgCircle = new DlgCircle(circle);
        			
        			dlgCircle.getTxtXCenter().setEditable(false);
    				dlgCircle.getTxtYCenter().setEditable(false);
    				
    				dlgCircle.setVisible(true);
    				
    				if(dlgCircle.isOk()) {
    		
						int radius = Integer.parseInt(dlgCircle.getTxtRadius().getText());
						
						Circle cr = new Circle(center, radius);
						
						cr.setBorderColor(dlgCircle.getBorderColor() != null ? dlgCircle.getBorderColor() : model.getCurrentBorderColor());
						cr.setInnerColor(dlgCircle.getInnerColor()!= null ? dlgCircle.getInnerColor() : model.getCurrentInnerColor());
						executeCommand(new AddShapeCmd(model, cr));
    					//model.setCurrentShape(DrawingModel.CurrentShape.NONE);
    				}
    				
    				//frame.getButtonGroup().clearSelection();
        			break;
        			
        		case DONUT:
        			startPoint = null;
        			Point donutCenter = new Point(e.getX(), e.getY());
        			Donut donut = new Donut(donutCenter, 0, 0);
        			DlgDonut dlgDonut = new DlgDonut(donut);
        			
        			dlgDonut.getTxtXDonutCenter().setEditable(false);
    				dlgDonut.getTxtYDonutCenter().setEditable(false);
    				
    				dlgDonut.setVisible(true);
    				
    				if(dlgDonut.isOk()) {
    					
						int donutRadius = Integer.parseInt(dlgDonut.getTxtRadius().getText());
						int innerRadius = Integer.parseInt(dlgDonut.getTxtInnerRadius().getText());	
							
						Donut d = new Donut(donutCenter, donutRadius, innerRadius);
						
						d.setBorderColor(dlgDonut.getBorderColor()!= null ? dlgDonut.getBorderColor() : model.getCurrentBorderColor());
						d.setInnerColor(dlgDonut.getInnerColor()!= null ? dlgDonut.getInnerColor() : model.getCurrentInnerColor());
						executeCommand(new AddShapeCmd(model, d));
    					//model.setCurrentShape(DrawingModel.CurrentShape.NONE);
    				}
    				
    				//frame.getButtonGroup().clearSelection();
        			break;
        			
        		case HEXAGON:
        			startPoint = null;
        			HexagonAdapter hex = new HexagonAdapter(e.getX(), e.getY(), 0);
        			DlgHexagon dlgHexagon = new DlgHexagon(hex);
        			
        			dlgHexagon.getTxtXCenter().setEditable(false);
        			dlgHexagon.getTxtYCenter().setEditable(false);
    				
        			dlgHexagon.setVisible(true);
    				
    				if(dlgHexagon.isOk()) {
    		
						int r = Integer.parseInt(dlgHexagon.getTxtRadius().getText());
						
						HexagonAdapter hexagon = new HexagonAdapter(e.getX(), e.getY(), r);
						
						hexagon.setBorderColor(dlgHexagon.getBorderColor() != null ? dlgHexagon.getBorderColor() : model.getCurrentBorderColor());
						hexagon.setInnerColor(dlgHexagon.getInnerColor() != null ? dlgHexagon.getInnerColor() : model.getCurrentInnerColor());
						
						executeCommand(new AddShapeCmd(model, hexagon));
    					//model.setCurrentShape(DrawingModel.CurrentShape.NONE);
    				}
    				
    				//frame.getButtonGroup().clearSelection();
        			break;
        			
        		default:
					model.setCurrentShape(DrawingModel.CurrentShape.NONE);
					break;
        		
        		}
        		frame.repaint();
        	}
        	
        	public void mousePressed(MouseEvent e) {
        		if(model.getCurrentShape() == DrawingModel.CurrentShape.NONE) {
        			Point click = new Point(e.getX(), e.getY());
        			Shape clicked = model.getSelected(click);
        		
				
        			if (clicked != null) {
        				executeCommand(new SelectCmd(clicked));
        				if (clicked.isSelected()) {
        					model.getSelectedShapes().add(clicked);
        				} else {
        					model.getSelectedShapes().remove(clicked);
        				}
        				
        			} else {
        				if (!model.getSelectedShapes().isEmpty()) {
	        				executeCommand(new DeselectCmd(model.getSelectedShapes()));
	        				model.getSelectedShapes().clear();
	        		        model.notifySelectedShapeChanged(0);
        				}
        				
        				/*for(Shape s : model.getSelectedShapes()) {
        					s.setSelected(false);
        				}
        				model.getSelectedShapes().clear();
        			}*/
        			}
				}
        		
				model.notifySelectedShapeChanged(model.getSelectedShapes().size());
				
				frame.repaint();
			}
		});
		
		//-------------------------------------------------------------------------------------
		
		frame.getBtnBorderColor().addMouseListener(new MouseAdapter() {
        	@Override
        	public void mousePressed(MouseEvent e) {
        		Color chosenColor = JColorChooser.showDialog(null, "Choose border color", Color.black);
        		if(chosenColor != null) {
        			model.setCurrentBorderColor(chosenColor);
        			frame.getBtnBorderColor().setBackground(chosenColor);
        		}
        	}
        });
		
		frame.getBtnInnerColor().addMouseListener(new MouseAdapter() {
        	@Override
        	public void mousePressed(MouseEvent e) {
        		Color chosenColor = JColorChooser.showDialog(null, "Choose inner color", Color.black);
        		if(chosenColor != null) {
        			model.setCurrentInnerColor(chosenColor);
        			frame.getBtnInnerColor().setBackground(chosenColor);
        		}
        	}
        });
		
		//------------------------------------------------------------------------------------------
		

        frame.getBtnSaveFile().addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		saveFile();
        	}
        });
        
        frame.getBtnOpenFile().addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		openFile();
        	}
        });
	}

	
	//command
	
	public void executeCommand(Command cmd) {
		cmd.execute();
		undoStack.push(cmd);
		redoStack.clear();
		addToLog(cmd.toString());
		model.notifyUndoAvailable(true);
		model.notifyRedoAvailable(false);
		frame.repaint();
	}
	
	public void undo() {
		if(!undoStack.isEmpty()) {
			Command cmd = undoStack.pop();
			cmd.unexecute();
			redoStack.push(cmd);
			
			addToLog("Undo: " + cmd.toString());
			
			model.notifyUndoAvailable(!undoStack.isEmpty());
			model.notifyRedoAvailable(true);
			
			model.getSelectedShapes().clear();
			for(Shape shape : model.getShapes()) {
				if(shape.isSelected()) {
					model.getSelectedShapes().add(shape);
				}
			}
			
			model.notifySelectedShapeChanged(model.getSelectedShapes().size());
			frame.repaint();
		} else {
			JOptionPane.showMessageDialog(null, "Nothing to undo.", "Undo", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	public void redo() {
		if(!redoStack.isEmpty()) {
			Command cmd = redoStack.pop();
			cmd.execute();
			undoStack.push(cmd);
			
			addToLog("Redo: " + cmd.toString());
			
			model.notifyUndoAvailable(true);
			model.notifyRedoAvailable(!redoStack.isEmpty());
			
			model.getSelectedShapes().clear();
			for(Shape shape : model.getShapes()) {
				if(shape.isSelected()) {
					model.getSelectedShapes().add(shape);
				}
			}
			
			model.notifySelectedShapeChanged(model.getSelectedShapes().size());
			frame.repaint();
		} else {
			JOptionPane.showMessageDialog(null, "Nothing to redo.", "Redo", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	//observer
	
	private void registerObservers() {
		
		model.addListener(new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				if(evt.getPropertyName().equals("selectedShape")) {
					int count = (int) evt.getNewValue();
					frame.getBtnDelete().setEnabled(count > 0);
				}
			}
			
		});
		
		model.addListener(new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				if(evt.getPropertyName().equals("selectedShape")) {
					int count = (int) evt.getNewValue();
					frame.getBtnModify().setEnabled(count == 1);
				}
			}
			
		});
		
		model.addListener(new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				if(evt.getPropertyName().equals("selectedShape")) {
					int count = (int) evt.getNewValue();
					
					if(count == 1) {
						Shape sel = model.getSelectedShapes().get(0);
						int index = model.getShapes().indexOf(sel);
						
						boolean isOnTop = index == model.getShapes().size()-1;
						boolean isOnBottom = index == 0;
						
						frame.getBtnToFront().setEnabled(!isOnTop);
						frame.getBtnBringToFront().setEnabled(!isOnTop);
						frame.getBtnToBack().setEnabled(!isOnBottom);
						frame.getBtnBringToBack().setEnabled(!isOnBottom);
					} else {
						frame.getBtnToFront().setEnabled(false);
						frame.getBtnBringToFront().setEnabled(false);
						frame.getBtnToBack().setEnabled(false);
						frame.getBtnBringToBack().setEnabled(false);
					}
					
				}
				
			}
			
		});
		
		model.addListener(new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				if(evt.getPropertyName().equals("undoAvailable")) {
					frame.getBtnUndo().setEnabled((boolean) evt.getNewValue());
				}
			}
		});
		
		model.addListener(new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				if(evt.getPropertyName().equals("redoAvailable")) {
					frame.getBtnRedo().setEnabled((boolean) evt.getNewValue());
				}
			}
		});
		
		frame.getBtnDelete().setEnabled(false);
		frame.getBtnModify().setEnabled(false);
		
		frame.getBtnToFront().setEnabled(false);
		frame.getBtnBringToFront().setEnabled(false);
		frame.getBtnToBack().setEnabled(false);
		frame.getBtnBringToBack().setEnabled(false);
		
		frame.getBtnUndo().setEnabled(false);
		frame.getBtnRedo().setEnabled(false);
	}
	
	//log
	
	private void addToLog(String message) {
	    frame.getTextLog().append(message + "\n");
	}
	
	// strategy
	
	public void saveFile() {
		
		JFileChooser fc = new JFileChooser();
		fc.setDialogTitle("Save file");
		
		Object[] options = {"Save as LOG (.txt)", "Save as DRAWING (.bin)"};

		int choice = JOptionPane.showOptionDialog(
	            frame,
	            "Choose file type:",
	            "Save",
	            JOptionPane.DEFAULT_OPTION,
	            JOptionPane.QUESTION_MESSAGE,
	            null,
	            options,
	            options[0]
	    );
		
		if(choice == JOptionPane.CLOSED_OPTION) {
			return;
		}
		
		if(fc.showSaveDialog(frame) == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			
			Strategy str;
			
			if(choice == 0) {
	            if(!file.getName().endsWith(".txt")) {
	                file = new File(file.getAbsolutePath() + ".txt");
	            }
	            str = new LogStrategy(frame);
	        } else {
	            if(!file.getName().endsWith(".bin")) {
	                file = new File(file.getAbsolutePath() + ".bin");
	            }
	            str = new SerializationStrategy(model);
	        }
			str.save(file);
		}
	}
	
	public void openFile() {
		JFileChooser fc = new JFileChooser();
		fc.setDialogTitle("Open file");
		
		FileNameExtensionFilter txtFilter = new FileNameExtensionFilter("Log files (*.txt)", "txt");
		FileNameExtensionFilter binFilter = new FileNameExtensionFilter("Drawing files (*.bin)", "bin");
		
		fc.addChoosableFileFilter(txtFilter);
		fc.addChoosableFileFilter(binFilter);
		fc.setFileFilter(binFilter);
		
		if (fc.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
	        File file = fc.getSelectedFile();
	        
	        Strategy str;
	        
	        if (file.getName().endsWith(".txt")) {
	            str = new LogStrategy(frame, this);
	        } else {
	            str = new SerializationStrategy(model);
	        }
	        
	        undoStack.clear();
	        redoStack.clear();
	        model.getSelectedShapes().clear();
	        model.getShapes().clear();
	        frame.getTextLog().setText("");
	        frame.getButtonGroup().clearSelection();
	        model.setCurrentShape(DrawingModel.CurrentShape.NONE);
	        model.notifyUndoAvailable(false);
	        model.notifyRedoAvailable(false);
	        model.notifySelectedShapeChanged(0);
	        
	        str.open(file);
	        
	        frame.repaint();
	    }
	}

	public void executeNextLine() {
		if (currentLineIndex < loadedLines.size()) {
	        String line = loadedLines.get(currentLineIndex);
	        
	        int choice = JOptionPane.showConfirmDialog(
	            null,
	            "Execute command:\n" + line,
	            "Log execution (" + (currentLineIndex + 1) + "/" + loadedLines.size() + ")",
	            JOptionPane.OK_CANCEL_OPTION
	        );
	        
	        if (choice == JOptionPane.OK_OPTION) {
	            parseText(line);
	            currentLineIndex++;
	            frame.repaint();
	            executeNextLine();
	        }
	        
	    } else {
	        JOptionPane.showMessageDialog(null, "All commands executed.");
	    }
		
	}

	private void parseText(String line) {
		String[] parts = line.split(": ", 2);
		String command = parts[0];
		
		if (line.startsWith("Undo:")) {
		    undo();
		    return;
		}

		if (line.startsWith("Redo:")) {
		    redo();
		    return;
		}
		
		String info = parts[1];
		
		switch(command) {
			case "Added":
				parseAdd(info);
				break;
			case "Deleted":
				parseDelete(info);
				break;
			case "Modified":
				parseModify(info);
				break;
			case "Selected":
				parseSelect(info, false);
				break;
			case "Deselected":
				parseSelect(info, true);
				break;
			case "Moved forward":
				parseZMove(info, "TO_FRONT");
				break;
			case "Moved backward":
				parseZMove(info, "TO_BACK");
				break;
			case "Brought to front":
				parseZMove(info, "BRING_TO_FRONT");
				break;
			case "Brought to back":
				parseZMove(info, "BRING_TO_BACK");
				break;
		}
		
	}
	
	private Shape parseShape(String info) {
	    if (info.startsWith("Point")) {
	        return parsePoint(info);
	    } else if (info.startsWith("Line")) {
	        return parseLine(info);
	    } else if (info.startsWith("Rectangle")) {
	        return parseRectangle(info);
	    } else if (info.startsWith("Circle")) {
	        return parseCircle(info);
	    } else if (info.startsWith("Donut")) {
	        return parseDonut(info);
	    } else if (info.startsWith("Hexagon")) {
	        return parseHexagon(info);
	    }
	    return null;
	}
	
	private Point parsePoint(String s) {
	    String[] parts = s.split("; ");
	    String[] coords = parts[0].replace("Point (", "").replace(")", "").split(",");
	    int x = Integer.parseInt(coords[0]);
	    int y = Integer.parseInt(coords[1]);
	    Color borderColor = new Color(Integer.parseInt(parts[1].split("=")[1]));
	    
	    Point p = new Point(x, y);
	    p.setBorderColor(borderColor);
	    return p;
	}

	private Line parseLine(String s) {
	    String[] parts = s.split("; ");
	    String coords = parts[0].replace("Line ", "");
	    String[] points = coords.split("\\)\\(");
	    String[] start = points[0].replace("(", "").split(",");
	    String[] end = points[1].replace(")", "").split(",");
	    
	    int x1 = Integer.parseInt(start[0]);
	    int y1 = Integer.parseInt(start[1]);
	    int x2 = Integer.parseInt(end[0]);
	    int y2 = Integer.parseInt(end[1]);
	    Color borderColor = new Color(Integer.parseInt(parts[1].split("=")[1]));
	    
	    Line l = new Line(new Point(x1, y1), new Point(x2, y2));
	    l.setBorderColor(borderColor);
	    return l;
	}

	private Rectangle parseRectangle(String s) {
	    String[] parts = s.split("; ");
	    String[] coords = parts[0].replace("Rectangle (", "").replace(")", "").split(",");
	    int x = Integer.parseInt(coords[0]);
	    int y = Integer.parseInt(coords[1]);
	    int width = Integer.parseInt(parts[1].split("=")[1]);
	    int height = Integer.parseInt(parts[2].split("=")[1]);
	    Color borderColor = new Color(Integer.parseInt(parts[3].split("=")[1]));
	    Color innerColor = new Color(Integer.parseInt(parts[4].split("=")[1]));
	    
	    Rectangle r = new Rectangle(new Point(x, y), width, height);
	    r.setBorderColor(borderColor);
	    r.setInnerColor(innerColor);
	    return r;
	}

	private Circle parseCircle(String s) {
	    String[] parts = s.split("; ");
	    String[] coords = parts[0].replace("Circle (", "").replace(")", "").split(",");
	    int x = Integer.parseInt(coords[0]);
	    int y = Integer.parseInt(coords[1]);
	    int radius = Integer.parseInt(parts[1].split("=")[1]);
	    Color borderColor = new Color(Integer.parseInt(parts[2].split("=")[1]));
	    Color innerColor = new Color(Integer.parseInt(parts[3].split("=")[1]));
	    
	    Circle c = new Circle(new Point(x, y), radius);
	    c.setBorderColor(borderColor);
	    c.setInnerColor(innerColor);
	    return c;
	}

	private Donut parseDonut(String s) {
	    String[] parts = s.split("; ");
	    String[] coords = parts[0].replace("Donut (", "").replace(")", "").split(",");
	    int x = Integer.parseInt(coords[0]);
	    int y = Integer.parseInt(coords[1]);
	    int radius = Integer.parseInt(parts[1].split("=")[1]);
	    int innerRadius = Integer.parseInt(parts[2].split("=")[1]);
	    Color borderColor = new Color(Integer.parseInt(parts[3].split("=")[1]));
	    Color innerColor = new Color(Integer.parseInt(parts[4].split("=")[1]));
	    
	    Donut d = new Donut(new Point(x, y), radius, innerRadius);
	    d.setBorderColor(borderColor);
	    d.setInnerColor(innerColor);
	    return d;
	}

	private HexagonAdapter parseHexagon(String s) {
	    String[] parts = s.split("; ");
	    String[] coords = parts[0].replace("Hexagon (", "").replace(")", "").split(",");
	    int x = Integer.parseInt(coords[0]);
	    int y = Integer.parseInt(coords[1]);
	    int radius = Integer.parseInt(parts[1].split("=")[1]);
	    Color borderColor = new Color(Integer.parseInt(parts[2].split("=")[1]));
	    Color innerColor = new Color(Integer.parseInt(parts[3].split("=")[1]));
	    
	    HexagonAdapter h = new HexagonAdapter(x, y, radius);
	    h.setBorderColor(borderColor);
	    h.setInnerColor(innerColor);
	    return h;
	}
	
	private void parseAdd(String info) {
	    Shape shape = parseShape(info);
	    if (shape != null) {
	        executeCommand(new AddShapeCmd(model, shape));
	    }
	}
	
	private void parseDelete(String info) {
	    String[] shapes = info.split(" \\| ");
	    List<Shape> toDelete = new ArrayList<>();
	    
	    for (String shapeStr : shapes) {
	        Shape found = findShape(shapeStr);
	        if (found != null) {
	            toDelete.add(found);
	        }
	    }
	    
	    if (!toDelete.isEmpty()) {
	        executeCommand(new RemoveShapeCmd(model, toDelete));
	    }
	}
	
	private Shape findShape(String shapeStr) {
	    for (Shape shape : model.getShapes()) {
	        if (shape.toString().equals(shapeStr)) {
	            return shape;
	        }
	    }
	    return null;
	}
	
	private void parseSelect(String info, boolean wasSelected) {
	    Shape shape = findShape(info);
	    if (shape != null) {
	        executeCommand(new SelectCmd(shape));
	        if (shape.isSelected()) {
	            model.getSelectedShapes().add(shape);
	        } else {
	            model.getSelectedShapes().remove(shape);
	        }
	        model.notifySelectedShapeChanged(model.getSelectedShapes().size());
	    }
	}
	
	private void parseModify(String info) {
	    String[] states = info.split(" >> ");
	    String oldStr = states[0];
	    String newStr = states[1];
	    
	    Shape oldShape = findShape(oldStr);
	    Shape newShape = parseShape(newStr);
	    
	    if (oldShape != null && newShape != null) {
	        if (oldShape instanceof Point) {
	            executeCommand(new UpdatePointCmd((Point) oldShape, (Point) newShape));
	        } else if (oldShape instanceof Line) {
	            executeCommand(new UpdateLineCmd((Line) oldShape, (Line) newShape));
	        } else if (oldShape instanceof Rectangle) {
	            executeCommand(new UpdateRectangleCmd((Rectangle) oldShape, (Rectangle) newShape));
	        } else if (oldShape instanceof Donut) {
	            executeCommand(new UpdateDonutCmd((Donut) oldShape, (Donut) newShape));
	        } else if (oldShape instanceof Circle) {
	            executeCommand(new UpdateCircleCmd((Circle) oldShape, (Circle) newShape));
	        } else if (oldShape instanceof HexagonAdapter) {
	            executeCommand(new UpdateHexagonCmd((HexagonAdapter) oldShape, (HexagonAdapter) newShape));
	        }
	    }
	}
	
	private void parseZMove(String info, String command) {
	    Shape shape = findShape(info);
	    if (shape != null) {
	        switch (command) {
	            case "TO_FRONT":
	                executeCommand(new ToFrontCmd(model, shape));
	                break;
	            case "TO_BACK":
	                executeCommand(new ToBackCmd(model, shape));
	                break;
	            case "BRING_TO_FRONT":
	                executeCommand(new BringToFrontCmd(model, shape));
	                break;
	            case "BRING_TO_BACK":
	                executeCommand(new BringToBackCmd(model, shape));
	                break;
	        }
	        model.notifySelectedShapeChanged(model.getSelectedShapes().size());
	    }
	}

	public void setLoadedLines(List<String> lines) {
		this.loadedLines = new ArrayList<>(lines);
	    this.currentLineIndex = 0;
	}
	
}
