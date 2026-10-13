package mvc;

import java.awt.EventQueue;

public class Application {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DrawingModel model = new DrawingModel();
					DrawingFrame frame = new DrawingFrame();
					DrawingController controller = new DrawingController(model, frame);
					frame.getView().setModel(model);
					frame.setController(controller);
					
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
