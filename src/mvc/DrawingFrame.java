package mvc;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
public class DrawingFrame extends JFrame{
		
		private static final long serialVersionUID = 1L;
		
		private JPanel contentPane;
		
		DrawingView view = new DrawingView();
		DrawingController controller;
		
		private final ButtonGroup buttonGroup = new ButtonGroup();
	    private JToggleButton tglbtnPoint;
	    private JToggleButton tglbtnLine;
	    private JToggleButton tglbtnRectangle;
	    private JToggleButton tglbtnCircle;
	    private JToggleButton tglbtnDonut;
	    private JButton btnUndo;
	    private JButton btnRedo;
	    private JButton btnToFront;
	    private JButton btnBringToFront;
	    private JButton btnToBack;
	    private JButton btnBringToBack;
	    private JButton btnModify;
	    private JButton btnDelete;
	    private JToggleButton tglbtnHexagon;
	    private JButton btnBorderColor;
	    private JButton btnInnerColor;
	    private JLabel lblNewLabel_1;
	    private JTextArea textLog;
	    private JScrollPane scrollPane;
	    private JButton btnSaveFile;
	    private JButton btnOpenFile;

		public DrawingFrame() {
			setTitle("Miljana Lovrenov IT3/2023");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 928, 518);
			contentPane = new JPanel();
			contentPane.setBackground(new Color(128, 128, 192));
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			

			setContentPane(contentPane);
			GridBagLayout gbl_contentPane = new GridBagLayout();
			gbl_contentPane.columnWidths = new int[]{97, 85, 90, 176, 0};
			gbl_contentPane.rowHeights = new int[]{27, 0, 0, 33, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 64, 0, 0};
			gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
			contentPane.setLayout(gbl_contentPane);
	        setVisible(true);
	        
	        btnDelete = new JButton("Delete");
	        btnDelete.setFont(new Font("Yu Gothic", Font.BOLD, 10));
	        GridBagConstraints gbc_btnDelete = new GridBagConstraints();
	        gbc_btnDelete.insets = new Insets(0, 0, 5, 5);
	        gbc_btnDelete.gridx = 0;
	        gbc_btnDelete.gridy = 1;
	        contentPane.add(btnDelete, gbc_btnDelete);
	        
	        btnModify = new JButton("Modify");
	        btnModify.setFont(new Font("Yu Gothic", Font.BOLD, 10));
	        GridBagConstraints gbc_btnModify = new GridBagConstraints();
	        gbc_btnModify.insets = new Insets(0, 0, 5, 5);
	        gbc_btnModify.gridx = 1;
	        gbc_btnModify.gridy = 1;
	        contentPane.add(btnModify, gbc_btnModify);
	        
	        btnUndo = new JButton(" Undo ");
	        btnUndo.setFont(new Font("Yu Gothic", Font.BOLD, 10));
	        GridBagConstraints gbc_btnUndo = new GridBagConstraints();
	        gbc_btnUndo.insets = new Insets(0, 0, 5, 5);
	        gbc_btnUndo.gridx = 0;
	        gbc_btnUndo.gridy = 2;
	        contentPane.add(btnUndo, gbc_btnUndo);
	        
	        btnRedo = new JButton(" Redo ");
	        btnRedo.setFont(new Font("Yu Gothic", Font.BOLD, 10));
	        GridBagConstraints gbc_btnRedo = new GridBagConstraints();
	        gbc_btnRedo.insets = new Insets(0, 0, 5, 5);
	        gbc_btnRedo.gridx = 1;
	        gbc_btnRedo.gridy = 2;
	        contentPane.add(btnRedo, gbc_btnRedo);
	        view.setBorder(new LineBorder(new Color(64, 0, 128)));
	        
	        view.setBackground(new Color(255, 255, 255));
	        GridBagConstraints gbc_pnlDrawing = new GridBagConstraints();
	        gbc_pnlDrawing.anchor = GridBagConstraints.NORTH;
	        gbc_pnlDrawing.insets = new Insets(0, 0, 5, 5);
	        gbc_pnlDrawing.gridheight = 15;
	        gbc_pnlDrawing.gridx = 2;
	        gbc_pnlDrawing.gridy = 1;
	        getContentPane().add(view, gbc_pnlDrawing);
	        view.setLayout(new FlowLayout(FlowLayout.CENTER, 250, 200));
	        
	        scrollPane = new JScrollPane();
	        GridBagConstraints gbc_scrollPane = new GridBagConstraints();
	        gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
	        gbc_scrollPane.fill = GridBagConstraints.BOTH;
	        gbc_scrollPane.gridheight = 15;
	        gbc_scrollPane.gridx = 3;
	        gbc_scrollPane.gridy = 1;
	        contentPane.add(scrollPane, gbc_scrollPane);
	        
	        textLog = new JTextArea();
	        scrollPane.setViewportView(textLog);
	        textLog.setEditable(false);
	        
	        btnToFront = new JButton(" To Front");
	        btnToFront.setFont(new Font("Yu Gothic", Font.BOLD, 10));
	        GridBagConstraints gbc_btnToFront = new GridBagConstraints();
	        gbc_btnToFront.insets = new Insets(0, 0, 5, 5);
	        gbc_btnToFront.gridx = 0;
	        gbc_btnToFront.gridy = 4;
	        contentPane.add(btnToFront, gbc_btnToFront);
	        
	        btnBringToFront = new JButton("Bring to front");
	        btnBringToFront.setFont(new Font("Yu Gothic", Font.BOLD, 10));
	        GridBagConstraints gbc_btnBringToFront = new GridBagConstraints();
	        gbc_btnBringToFront.insets = new Insets(0, 0, 5, 5);
	        gbc_btnBringToFront.gridx = 1;
	        gbc_btnBringToFront.gridy = 4;
	        contentPane.add(btnBringToFront, gbc_btnBringToFront);
	        
	        btnToBack = new JButton(" To back ");
	        btnToBack.setFont(new Font("Yu Gothic", Font.BOLD, 10));
	        GridBagConstraints gbc_btnToBack = new GridBagConstraints();
	        gbc_btnToBack.insets = new Insets(0, 0, 5, 5);
	        gbc_btnToBack.gridx = 0;
	        gbc_btnToBack.gridy = 5;
	        contentPane.add(btnToBack, gbc_btnToBack);
	        
	        btnBringToBack = new JButton("Bring to back");
	        btnBringToBack.setFont(new Font("Yu Gothic", Font.BOLD, 10));
	        GridBagConstraints gbc_btnBringToBack = new GridBagConstraints();
	        gbc_btnBringToBack.insets = new Insets(0, 0, 5, 5);
	        gbc_btnBringToBack.gridx = 1;
	        gbc_btnBringToBack.gridy = 5;
	        contentPane.add(btnBringToBack, gbc_btnBringToBack);
	        
	        JLabel lblNewLabel = new JLabel("Draw:");
	        lblNewLabel.setFont(new Font("Yu Gothic", Font.BOLD, 10));
	        lblNewLabel.setBackground(new Color(255, 255, 255));
	        lblNewLabel.setForeground(new Color(0, 0, 0));
	        GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
	        gbc_lblNewLabel.gridwidth = 2;
	        gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
	        gbc_lblNewLabel.gridx = 0;
	        gbc_lblNewLabel.gridy = 7;
	        contentPane.add(lblNewLabel, gbc_lblNewLabel);
	        
	        tglbtnPoint = new JToggleButton("     POINT    ");
	        tglbtnPoint.setFont(new Font("Tahoma", Font.PLAIN, 8));
	        tglbtnPoint.setForeground(new Color(64, 0, 128));
	        buttonGroup.add(tglbtnPoint);
	        GridBagConstraints gbc_tglbtnPoint = new GridBagConstraints();
	        gbc_tglbtnPoint.insets = new Insets(0, 0, 5, 5);
	        gbc_tglbtnPoint.gridx = 0;
	        gbc_tglbtnPoint.gridy = 8;
	        contentPane.add(tglbtnPoint, gbc_tglbtnPoint);
	        
	        tglbtnLine = new JToggleButton("      LINE      ");
	        tglbtnLine.setFont(new Font("Tahoma", Font.PLAIN, 8));
	        tglbtnLine.setForeground(new Color(64, 0, 128));
	        buttonGroup.add(tglbtnLine);
	        GridBagConstraints gbc_tglbtnLine = new GridBagConstraints();
	        gbc_tglbtnLine.insets = new Insets(0, 0, 5, 5);
	        gbc_tglbtnLine.gridx = 1;
	        gbc_tglbtnLine.gridy = 8;
	        contentPane.add(tglbtnLine, gbc_tglbtnLine);
	        
	        tglbtnCircle = new JToggleButton("    CIRCLE    ");
	        tglbtnCircle.setFont(new Font("Tahoma", Font.PLAIN, 8));
	        tglbtnCircle.setForeground(new Color(64, 0, 128));
	        buttonGroup.add(tglbtnCircle);
	        GridBagConstraints gbc_tglbtnCircle = new GridBagConstraints();
	        gbc_tglbtnCircle.insets = new Insets(0, 0, 5, 5);
	        gbc_tglbtnCircle.gridx = 0;
	        gbc_tglbtnCircle.gridy = 9;
	        contentPane.add(tglbtnCircle, gbc_tglbtnCircle);
	        
	        tglbtnRectangle = new JToggleButton(" RECTANGLE");
	        tglbtnRectangle.setFont(new Font("Tahoma", Font.PLAIN, 8));
	        tglbtnRectangle.setForeground(new Color(64, 0, 128));
	        buttonGroup.add(tglbtnRectangle);
	        GridBagConstraints gbc_tglbtnRectangle = new GridBagConstraints();
	        gbc_tglbtnRectangle.insets = new Insets(0, 0, 5, 5);
	        gbc_tglbtnRectangle.gridx = 1;
	        gbc_tglbtnRectangle.gridy = 9;
	        contentPane.add(tglbtnRectangle, gbc_tglbtnRectangle);
	        
	        tglbtnDonut = new JToggleButton("    DONUT    ");
	        tglbtnDonut.setFont(new Font("Tahoma", Font.PLAIN, 8));
	        tglbtnDonut.setForeground(new Color(64, 0, 128));
	        buttonGroup.add(tglbtnDonut);
	        GridBagConstraints gbc_tglbtnDonut = new GridBagConstraints();
	        gbc_tglbtnDonut.insets = new Insets(0, 0, 5, 5);
	        gbc_tglbtnDonut.gridx = 0;
	        gbc_tglbtnDonut.gridy = 10;
	        contentPane.add(tglbtnDonut, gbc_tglbtnDonut);
	        
	        tglbtnHexagon = new JToggleButton("  HEXAGON  ");
	        tglbtnHexagon.setForeground(new Color(64, 0, 128));
	        tglbtnHexagon.setFont(new Font("Tahoma", Font.PLAIN, 8));
	        buttonGroup.add(tglbtnHexagon);
	        GridBagConstraints gbc_tglbtnHexagon = new GridBagConstraints();
	        gbc_tglbtnHexagon.insets = new Insets(0, 0, 5, 5);
	        gbc_tglbtnHexagon.gridx = 1;
	        gbc_tglbtnHexagon.gridy = 10;
	        contentPane.add(tglbtnHexagon, gbc_tglbtnHexagon);
	        
	        lblNewLabel_1 = new JLabel("Choose shape colors:");
	        lblNewLabel_1.setFont(new Font("Yu Gothic", Font.BOLD, 10));
	        GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
	        gbc_lblNewLabel_1.anchor = GridBagConstraints.SOUTH;
	        gbc_lblNewLabel_1.gridwidth = 2;
	        gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
	        gbc_lblNewLabel_1.gridx = 0;
	        gbc_lblNewLabel_1.gridy = 12;
	        contentPane.add(lblNewLabel_1, gbc_lblNewLabel_1);
	        
	        btnBorderColor = new JButton("Border");
	        btnBorderColor.setForeground(new Color(255, 255, 255));
	        btnBorderColor.setBackground(new Color(64, 0, 128));
	        btnBorderColor.setFont(new Font("Yu Gothic", Font.BOLD, 10));
	        GridBagConstraints gbc_btnBorderColor = new GridBagConstraints();
	        gbc_btnBorderColor.anchor = GridBagConstraints.SOUTH;
	        gbc_btnBorderColor.insets = new Insets(0, 0, 5, 5);
	        gbc_btnBorderColor.gridx = 0;
	        gbc_btnBorderColor.gridy = 13;
	        contentPane.add(btnBorderColor, gbc_btnBorderColor);
	        
	        btnInnerColor = new JButton("  Fill  ");
	        btnInnerColor.setBackground(new Color(204, 204, 255));
	        btnInnerColor.setFont(new Font("Yu Gothic", Font.BOLD, 10));
	        GridBagConstraints gbc_btnInnerColor = new GridBagConstraints();
	        gbc_btnInnerColor.anchor = GridBagConstraints.SOUTH;
	        gbc_btnInnerColor.insets = new Insets(0, 0, 5, 5);
	        gbc_btnInnerColor.gridx = 1;
	        gbc_btnInnerColor.gridy = 13;
	        contentPane.add(btnInnerColor, gbc_btnInnerColor);
	        
	        btnSaveFile = new JButton("Save file");
	        GridBagConstraints gbc_btnSaveFile = new GridBagConstraints();
	        gbc_btnSaveFile.anchor = GridBagConstraints.SOUTH;
	        gbc_btnSaveFile.insets = new Insets(0, 0, 5, 5);
	        gbc_btnSaveFile.gridx = 0;
	        gbc_btnSaveFile.gridy = 15;
	        contentPane.add(btnSaveFile, gbc_btnSaveFile);
	        
	        btnOpenFile = new JButton("Open file");
	        GridBagConstraints gbc_btnOpenFile = new GridBagConstraints();
	        gbc_btnOpenFile.anchor = GridBagConstraints.SOUTH;
	        gbc_btnOpenFile.insets = new Insets(0, 0, 5, 5);
	        gbc_btnOpenFile.gridx = 1;
	        gbc_btnOpenFile.gridy = 15;
	        contentPane.add(btnOpenFile, gbc_btnOpenFile);

		}

		public DrawingView getView() {
			return view;
		}

		public void setController(DrawingController controller) {
			this.controller = controller;
		}

		public ButtonGroup getButtonGroup() {
			return buttonGroup;
		}

		public JToggleButton getTglbtnPoint() {
			return tglbtnPoint;
		}

		public JToggleButton getTglbtnLine() {
			return tglbtnLine;
		}

		public JToggleButton getTglbtnRectangle() {
			return tglbtnRectangle;
		}

		public JToggleButton getTglbtnCircle() {
			return tglbtnCircle;
		}

		public JToggleButton getTglbtnDonut() {
			return tglbtnDonut;
		}

		public JToggleButton getTglbtnHexagon() {
			return tglbtnHexagon;
		}

		public JButton getBtnDelete() {
			return btnDelete;
		}

		public JButton getBtnModify() {
			return btnModify;
		}

		public JButton getBtnUndo() {
			return btnUndo;
		}

		public JButton getBtnRedo() {
			return btnRedo;
		}

		public JButton getBtnToFront() {
			return btnToFront;
		}

		public JButton getBtnBringToFront() {
			return btnBringToFront;
		}

		public JButton getBtnToBack() {
			return btnToBack;
		}

		public JButton getBtnBringToBack() {
			return btnBringToBack;
		}

		public JButton getBtnBorderColor() {
			return btnBorderColor;
		}

		public JButton getBtnInnerColor() {
			return btnInnerColor;
		}

		public JTextArea getTextLog() {
			return textLog;
		}

		public JButton getBtnSaveFile() {
			return btnSaveFile;
		}

		public JButton getBtnOpenFile() {
			return btnOpenFile;
		}
		
}
