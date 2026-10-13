package drawing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import geometry.Rectangle;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class DlgRectangle extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtXRect;
	private JTextField txtYRect;
	private JTextField txtHeight;
	private JTextField txtWidth;
	private boolean ok;
	private Color borderColor;
	private Color innerColor;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgRectangle dialog = new DlgRectangle(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgRectangle(Rectangle rectangle) {
		
		setTitle("Miljana Lovrenov IT3/2023");
		setBounds(100, 100, 450, 300);
		setModal(true);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblLabel1 = new JLabel("Upper left point coordinates:");
			GridBagConstraints gbc_lblLabel1 = new GridBagConstraints();
			gbc_lblLabel1.anchor = GridBagConstraints.WEST;
			gbc_lblLabel1.gridwidth = 2;
			gbc_lblLabel1.insets = new Insets(0, 0, 5, 0);
			gbc_lblLabel1.gridx = 0;
			gbc_lblLabel1.gridy = 0;
			contentPanel.add(lblLabel1, gbc_lblLabel1);
		}
		{
			JLabel lblXRect = new JLabel("x:");
			GridBagConstraints gbc_lblXRect = new GridBagConstraints();
			gbc_lblXRect.anchor = GridBagConstraints.EAST;
			gbc_lblXRect.insets = new Insets(0, 0, 5, 5);
			gbc_lblXRect.gridx = 0;
			gbc_lblXRect.gridy = 1;
			contentPanel.add(lblXRect, gbc_lblXRect);
		}
		{
			txtXRect = new JTextField();
			txtXRect.setText(Integer.toString(rectangle.getUpperLeftPoint().getX()));
			GridBagConstraints gbc_txtXRect = new GridBagConstraints();
			gbc_txtXRect.anchor = GridBagConstraints.WEST;
			gbc_txtXRect.insets = new Insets(0, 0, 5, 0);
			gbc_txtXRect.gridx = 1;
			gbc_txtXRect.gridy = 1;
			contentPanel.add(txtXRect, gbc_txtXRect);
			txtXRect.setColumns(10);
		}
		{
			JLabel lblYRect = new JLabel("y:");
			GridBagConstraints gbc_lblYRect = new GridBagConstraints();
			gbc_lblYRect.anchor = GridBagConstraints.EAST;
			gbc_lblYRect.insets = new Insets(0, 0, 5, 5);
			gbc_lblYRect.gridx = 0;
			gbc_lblYRect.gridy = 2;
			contentPanel.add(lblYRect, gbc_lblYRect);
		}
		{
			txtYRect = new JTextField();
			txtYRect.setText(Integer.toString(rectangle.getUpperLeftPoint().getY()));
			GridBagConstraints gbc_txtYRect = new GridBagConstraints();
			gbc_txtYRect.anchor = GridBagConstraints.WEST;
			gbc_txtYRect.insets = new Insets(0, 0, 5, 0);
			gbc_txtYRect.gridx = 1;
			gbc_txtYRect.gridy = 2;
			contentPanel.add(txtYRect, gbc_txtYRect);
			txtYRect.setColumns(10);
		}
		{
			JLabel lblLabel2 = new JLabel("Enter required information:");
			lblLabel2.setForeground(new Color(0, 0, 0));
			lblLabel2.setFont(new Font("Artifakt Element Book", Font.BOLD | Font.ITALIC, 10));
			GridBagConstraints gbc_lblLabel2 = new GridBagConstraints();
			gbc_lblLabel2.anchor = GridBagConstraints.SOUTHWEST;
			gbc_lblLabel2.gridwidth = 2;
			gbc_lblLabel2.insets = new Insets(0, 0, 5, 0);
			gbc_lblLabel2.gridx = 0;
			gbc_lblLabel2.gridy = 3;
			contentPanel.add(lblLabel2, gbc_lblLabel2);
		}
		{
			JLabel lblHeight = new JLabel("height:");
			GridBagConstraints gbc_lblHeight = new GridBagConstraints();
			gbc_lblHeight.anchor = GridBagConstraints.WEST;
			gbc_lblHeight.insets = new Insets(0, 0, 5, 5);
			gbc_lblHeight.gridx = 0;
			gbc_lblHeight.gridy = 4;
			contentPanel.add(lblHeight, gbc_lblHeight);
		}
		{
			txtHeight = new JTextField();
			GridBagConstraints gbc_txtHeight = new GridBagConstraints();
			gbc_txtHeight.anchor = GridBagConstraints.WEST;
			gbc_txtHeight.insets = new Insets(0, 0, 5, 0);
			gbc_txtHeight.gridx = 1;
			gbc_txtHeight.gridy = 4;
			contentPanel.add(txtHeight, gbc_txtHeight);
			txtHeight.setColumns(10);
		}
		{
			JLabel lblWidth = new JLabel("width:");
			GridBagConstraints gbc_lblWidth = new GridBagConstraints();
			gbc_lblWidth.anchor = GridBagConstraints.WEST;
			gbc_lblWidth.insets = new Insets(0, 0, 5, 5);
			gbc_lblWidth.gridx = 0;
			gbc_lblWidth.gridy = 5;
			contentPanel.add(lblWidth, gbc_lblWidth);
		}
		{
			txtWidth = new JTextField();
			GridBagConstraints gbc_txtWidth = new GridBagConstraints();
			gbc_txtWidth.insets = new Insets(0, 0, 5, 0);
			gbc_txtWidth.anchor = GridBagConstraints.WEST;
			gbc_txtWidth.gridx = 1;
			gbc_txtWidth.gridy = 5;
			contentPanel.add(txtWidth, gbc_txtWidth);
			txtWidth.setColumns(10);
		}
		
		{
			JButton btnColor = new JButton("Choose color");
			btnColor.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					borderColor = JColorChooser.showDialog(null, "Choose border color", Color.black);
					innerColor = JColorChooser.showDialog(null, "Choose rectangle color", Color.white);
				}
			});
			btnColor.setBackground(new Color(64, 0, 128));
			btnColor.setForeground(new Color(255, 255, 255));
			btnColor.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 10));
			GridBagConstraints gbc_btnColor = new GridBagConstraints();
			gbc_btnColor.anchor = GridBagConstraints.WEST;
			gbc_btnColor.gridx = 1;
			gbc_btnColor.gridy = 6;
			contentPanel.add(btnColor, gbc_btnColor);
		}
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						try {
							if ((Integer.parseInt(getTxtHeight().getText()))<=0 || 
									(Integer.parseInt(getTxtWidth().getText()))<=0)
								JOptionPane.showMessageDialog(null, "Values for height and width have to be greater than zero!", "", JOptionPane.ERROR_MESSAGE);
							else {
								ok=true;
								setVisible(false);
							}
						} catch (Exception ex) {
							JOptionPane.showMessageDialog(null, "Please enter valid values!", "", JOptionPane.ERROR_MESSAGE);
						}
						
						//ok=true;
						//setVisible(false);
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						ok=false;
						rectangle.setSelected(false);
						setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
	}

	public JTextField getTxtXRect() {
		return txtXRect;
	}

	public void setTxtXRect(JTextField txtXRect) {
		this.txtXRect = txtXRect;
	}

	public JTextField getTxtYRect() {
		return txtYRect;
	}

	public void setTxtYRect(JTextField txtYRect) {
		this.txtYRect = txtYRect;
	}

	public JTextField getTxtHeight() {
		return txtHeight;
	}

	public void setTxtHeight(JTextField txtHeight) {
		this.txtHeight = txtHeight;
	}

	public JTextField getTxtWidth() {
		return txtWidth;
	}

	public void setTxtWidth(JTextField txtWidth) {
		this.txtWidth = txtWidth;
	}

	public boolean isOk() {
		return ok;
	}

	public void setOk(boolean ok) {
		this.ok = ok;
	}

	public Color getInnerColor() {
		return innerColor;
	}

	public void setInnerColor(Color innerColor) {
		this.innerColor = innerColor;
	}

	public JPanel getContentPanel() {
		return contentPanel;
	}

	public Color getBorderColor() {
		return borderColor;
	}

	public void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
	}
	
	

}
