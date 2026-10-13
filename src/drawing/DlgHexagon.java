package drawing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import adapter.HexagonAdapter;

public class DlgHexagon extends JDialog{


		private static final long serialVersionUID = 1L;
		private final JPanel contentPanel = new JPanel();
		private JTextField txtXCenter;
		private JTextField txtYCenter;
		private JTextField txtRadius;
		private boolean ok;
		private Color borderColor;
		private Color innerColor;


		public static void main(String[] args) {
			try {
				DlgHexagon dialog = new DlgHexagon(null);
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}


		public DlgHexagon(HexagonAdapter hexagon) {
			setTitle("Miljana Lovrenov IT3/2023");
			setBounds(100, 100, 450, 300);
			setModal(true);
			getContentPane().setLayout(new BorderLayout());
			contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
			getContentPane().add(contentPanel, BorderLayout.CENTER);
			GridBagLayout gbl_contentPanel = new GridBagLayout();
			gbl_contentPanel.columnWidths = new int[]{0, 0, 0};
			gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
			gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
			gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
			contentPanel.setLayout(gbl_contentPanel);
			{
				JLabel lblNewLabel = new JLabel("Hexagon center coordinates:");
				GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
				gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
				gbc_lblNewLabel.gridwidth = 2;
				gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
				gbc_lblNewLabel.gridx = 0;
				gbc_lblNewLabel.gridy = 0;
				contentPanel.add(lblNewLabel, gbc_lblNewLabel);
			}
			{
				JLabel lblXCenter = new JLabel("x:");
				GridBagConstraints gbc_lblXCenter = new GridBagConstraints();
				gbc_lblXCenter.anchor = GridBagConstraints.EAST;
				gbc_lblXCenter.insets = new Insets(0, 0, 5, 5);
				gbc_lblXCenter.gridx = 0;
				gbc_lblXCenter.gridy = 1;
				contentPanel.add(lblXCenter, gbc_lblXCenter);
			}
			{
				txtXCenter = new JTextField();
				txtXCenter.setText(Integer.toString(hexagon.getX()));
				GridBagConstraints gbc_txtXCenter = new GridBagConstraints();
				gbc_txtXCenter.anchor = GridBagConstraints.WEST;
				gbc_txtXCenter.insets = new Insets(0, 0, 5, 0);
				gbc_txtXCenter.gridx = 1;
				gbc_txtXCenter.gridy = 1;
				contentPanel.add(txtXCenter, gbc_txtXCenter);
				txtXCenter.setColumns(10);
			}
			{
				JLabel lblYCenter = new JLabel("y:");
				GridBagConstraints gbc_lblYCenter = new GridBagConstraints();
				gbc_lblYCenter.anchor = GridBagConstraints.EAST;
				gbc_lblYCenter.insets = new Insets(0, 0, 5, 5);
				gbc_lblYCenter.gridx = 0;
				gbc_lblYCenter.gridy = 2;
				contentPanel.add(lblYCenter, gbc_lblYCenter);
			}
			{
				txtYCenter = new JTextField();
				txtYCenter.setText(Integer.toString(hexagon.getY()));
				GridBagConstraints gbc_txtYCenter = new GridBagConstraints();
				gbc_txtYCenter.anchor = GridBagConstraints.WEST;
				gbc_txtYCenter.insets = new Insets(0, 0, 5, 0);
				gbc_txtYCenter.gridx = 1;
				gbc_txtYCenter.gridy = 2;
				contentPanel.add(txtYCenter, gbc_txtYCenter);
				txtYCenter.setColumns(10);
			}
			{
				JLabel lblNewLabel_1 = new JLabel("Enter required information:");
				lblNewLabel_1.setFont(new Font("Artifakt Element Black", Font.ITALIC, 10));
				GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
				gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
				gbc_lblNewLabel_1.gridwidth = 2;
				gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 0);
				gbc_lblNewLabel_1.gridx = 0;
				gbc_lblNewLabel_1.gridy = 3;
				contentPanel.add(lblNewLabel_1, gbc_lblNewLabel_1);
			}
			{
				JLabel lblRadius = new JLabel("radius:");
				GridBagConstraints gbc_lblRadius = new GridBagConstraints();
				gbc_lblRadius.anchor = GridBagConstraints.WEST;
				gbc_lblRadius.insets = new Insets(0, 0, 5, 5);
				gbc_lblRadius.gridx = 0;
				gbc_lblRadius.gridy = 4;
				contentPanel.add(lblRadius, gbc_lblRadius);
			}
			{
				txtRadius = new JTextField();
				GridBagConstraints gbc_txtRadius = new GridBagConstraints();
				gbc_txtRadius.insets = new Insets(0, 0, 5, 0);
				gbc_txtRadius.anchor = GridBagConstraints.WEST;
				gbc_txtRadius.gridx = 1;
				gbc_txtRadius.gridy = 4;
				contentPanel.add(txtRadius, gbc_txtRadius);
				txtRadius.setColumns(10);
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
				gbc_btnColor.gridy = 5;
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
								if ((Integer.parseInt(getTxtRadius().getText()))<=0)
									JOptionPane.showMessageDialog(null, "Value for radius has to be greater than zero!", "", JOptionPane.ERROR_MESSAGE);
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
							ok = false;
							setVisible(false);
							hexagon.setSelected(false);
						}
					});
					cancelButton.setActionCommand("Cancel");
					buttonPane.add(cancelButton);
				}
			}

		}

		public JTextField getTxtXCenter() {
			return txtXCenter;
		}

		public void setTxtXCenter(JTextField txtXCenter) {
			this.txtXCenter = txtXCenter;
		}

		public JTextField getTxtYCenter() {
			return txtYCenter;
		}

		public void setTxtYCenter(JTextField txtYCenter) {
			this.txtYCenter = txtYCenter;
		}

		public JTextField getTxtRadius() {
			return txtRadius;
		}

		public void setTxtRadius(JTextField txtRadius) {
			this.txtRadius = txtRadius;
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
