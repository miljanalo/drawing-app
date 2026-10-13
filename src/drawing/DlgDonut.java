package drawing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import geometry.Donut;

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

public class DlgDonut extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtXDonutCenter;
	private JTextField txtYDonutCenter;
	private JTextField txtRadius;
	private JTextField txtInnerRadius;
	private boolean ok;
	private Color borderColor;
	private Color innerColor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgDonut dialog = new DlgDonut(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgDonut(Donut donut) {
		setTitle("Miljana Lovrenov IT3/2023");
		setBounds(100, 100, 450, 300);
		setModal(true);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{62, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblLabel = new JLabel("Donut center coordinates:");
			GridBagConstraints gbc_lblLabel = new GridBagConstraints();
			gbc_lblLabel.anchor = GridBagConstraints.WEST;
			gbc_lblLabel.gridwidth = 2;
			gbc_lblLabel.insets = new Insets(0, 0, 5, 0);
			gbc_lblLabel.gridx = 0;
			gbc_lblLabel.gridy = 0;
			contentPanel.add(lblLabel, gbc_lblLabel);
		}
		{
			JLabel lblXDonutCenter = new JLabel("x:");
			GridBagConstraints gbc_lblXDonutCenter = new GridBagConstraints();
			gbc_lblXDonutCenter.anchor = GridBagConstraints.EAST;
			gbc_lblXDonutCenter.insets = new Insets(0, 0, 5, 5);
			gbc_lblXDonutCenter.gridx = 0;
			gbc_lblXDonutCenter.gridy = 1;
			contentPanel.add(lblXDonutCenter, gbc_lblXDonutCenter);
		}
		{
			txtXDonutCenter = new JTextField();
			txtXDonutCenter.setText(Integer.toString(donut.getCenter().getX()));
			GridBagConstraints gbc_txtXDonutCenter = new GridBagConstraints();
			gbc_txtXDonutCenter.anchor = GridBagConstraints.WEST;
			gbc_txtXDonutCenter.insets = new Insets(0, 0, 5, 0);
			gbc_txtXDonutCenter.gridx = 1;
			gbc_txtXDonutCenter.gridy = 1;
			contentPanel.add(txtXDonutCenter, gbc_txtXDonutCenter);
			txtXDonutCenter.setColumns(10);
		}
		{
			JLabel lblYDonutCenter = new JLabel("y:");
			GridBagConstraints gbc_lblYDonutCenter = new GridBagConstraints();
			gbc_lblYDonutCenter.anchor = GridBagConstraints.EAST;
			gbc_lblYDonutCenter.insets = new Insets(0, 0, 5, 5);
			gbc_lblYDonutCenter.gridx = 0;
			gbc_lblYDonutCenter.gridy = 2;
			contentPanel.add(lblYDonutCenter, gbc_lblYDonutCenter);
		}
		{
			txtYDonutCenter = new JTextField();
			txtYDonutCenter.setText(Integer.toString(donut.getCenter().getY()));
			GridBagConstraints gbc_txtYDonutCenter = new GridBagConstraints();
			gbc_txtYDonutCenter.anchor = GridBagConstraints.WEST;
			gbc_txtYDonutCenter.insets = new Insets(0, 0, 5, 0);
			gbc_txtYDonutCenter.gridx = 1;
			gbc_txtYDonutCenter.gridy = 2;
			contentPanel.add(txtYDonutCenter, gbc_txtYDonutCenter);
			txtYDonutCenter.setColumns(10);
		}
		{
			JLabel lblNewLabel = new JLabel("Enter required information:");
			lblNewLabel.setFont(new Font("Artifakt Element Black", Font.ITALIC, 10));
			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
			gbc_lblNewLabel.gridwidth = 2;
			gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
			gbc_lblNewLabel.gridx = 0;
			gbc_lblNewLabel.gridy = 3;
			contentPanel.add(lblNewLabel, gbc_lblNewLabel);
		}
		{
			JLabel lblDonutRadius = new JLabel("radius:");
			GridBagConstraints gbc_lblDonutRadius = new GridBagConstraints();
			gbc_lblDonutRadius.anchor = GridBagConstraints.EAST;
			gbc_lblDonutRadius.insets = new Insets(0, 0, 5, 5);
			gbc_lblDonutRadius.gridx = 0;
			gbc_lblDonutRadius.gridy = 4;
			contentPanel.add(lblDonutRadius, gbc_lblDonutRadius);
		}
		{
			txtRadius = new JTextField();
			GridBagConstraints gbc_txtRadius = new GridBagConstraints();
			gbc_txtRadius.anchor = GridBagConstraints.WEST;
			gbc_txtRadius.insets = new Insets(0, 0, 5, 0);
			gbc_txtRadius.gridx = 1;
			gbc_txtRadius.gridy = 4;
			contentPanel.add(txtRadius, gbc_txtRadius);
			txtRadius.setColumns(10);
		}
		{
			JLabel lblInnerRadius = new JLabel("inner radius:");
			GridBagConstraints gbc_lblInnerRadius = new GridBagConstraints();
			gbc_lblInnerRadius.insets = new Insets(0, 0, 5, 5);
			gbc_lblInnerRadius.anchor = GridBagConstraints.WEST;
			gbc_lblInnerRadius.gridx = 0;
			gbc_lblInnerRadius.gridy = 5;
			contentPanel.add(lblInnerRadius, gbc_lblInnerRadius);
			//txtInnerRadius.setVisible(true);

		}
		{
			txtInnerRadius = new JTextField();
			GridBagConstraints gbc_txtInnerRadius = new GridBagConstraints();
			gbc_txtInnerRadius.insets = new Insets(0, 0, 5, 0);
			gbc_txtInnerRadius.anchor = GridBagConstraints.WEST;
			gbc_txtInnerRadius.gridx = 1;
			gbc_txtInnerRadius.gridy = 5;
			contentPanel.add(txtInnerRadius, gbc_txtInnerRadius);
			txtInnerRadius.setColumns(10);
		}
		{
			JButton btnColor = new JButton("Choose color");
			btnColor.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					borderColor = JColorChooser.showDialog(null, "Choose border color", Color.black);
					innerColor = JColorChooser.showDialog(null, "Choose circles color", Color.white);
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

							if ((Integer.parseInt(getTxtRadius().getText()))<0 || (Integer.parseInt(getTxtInnerRadius().getText()))<0)
								JOptionPane.showMessageDialog(null, "Values for radius and inner radius have to be greater than zero!", "", JOptionPane.ERROR_MESSAGE);
							else if ((Integer.parseInt(getTxtRadius().getText())) <= (Integer.parseInt(getTxtInnerRadius().getText())))
								JOptionPane.showMessageDialog(null, "Radius has to be greater value than inner radius!", "", JOptionPane.ERROR_MESSAGE);
							else {
								ok=true;
								setVisible(false);
							}
						} catch (Exception ex) {
							JOptionPane.showMessageDialog(null, "Please enter a valid value!", "", JOptionPane.ERROR_MESSAGE);
						}
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
						setVisible(false);
						donut.setSelected(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
	}

	public JTextField getTxtXDonutCenter() {
		return txtXDonutCenter;
	}

	public void setTxtXDonutCenter(JTextField txtXDonutCenter) {
		this.txtXDonutCenter = txtXDonutCenter;
	}

	public JTextField getTxtYDonutCenter() {
		return txtYDonutCenter;
	}

	public void setTxtYDonutCenter(JTextField txtYDonutCenter) {
		this.txtYDonutCenter = txtYDonutCenter;
	}

	public JTextField getTxtRadius() {
		return txtRadius;
	}

	public void setTxtRadius(JTextField txtRadius) {
		this.txtRadius = txtRadius;
	}

	public JTextField getTxtInnerRadius() {
		return txtInnerRadius;
	}

	public void setTxtInnerRadius(JTextField txtInnerRadius) {
		this.txtInnerRadius = txtInnerRadius;
	}

	public boolean isOk() {
		return ok;
	}

	public void setOk(boolean ok) {
		this.ok = ok;
	}

	public Color getBorderColor() {
		return borderColor;
	}

	public void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
	}

	public Color getInnerColor() {
		return innerColor;
	}

	public void setInnerColor(Color innerColor) {
		this.innerColor = innerColor;
	}	
	
}
