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

import geometry.Line;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

public class DlgLine extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtXStart;
	private JTextField txtYStart;
	private JTextField txtXEnd;
	private JTextField txtYEnd;
	private boolean ok;
	private Color borderColor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgLine dialog = new DlgLine(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgLine(Line line) {
		setTitle("Miljana Lovrenov IT3/2023");
		setBounds(100, 100, 450, 300);
		setModal(true);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{40, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblLabel1 = new JLabel("Starting point coordinates:");
			GridBagConstraints gbc_lblLabel1 = new GridBagConstraints();
			gbc_lblLabel1.anchor = GridBagConstraints.WEST;
			gbc_lblLabel1.gridwidth = 2;
			gbc_lblLabel1.insets = new Insets(0, 0, 5, 0);
			gbc_lblLabel1.gridx = 0;
			gbc_lblLabel1.gridy = 0;
			contentPanel.add(lblLabel1, gbc_lblLabel1);
		}
		{
			JLabel lblXStart = new JLabel("x:");
			GridBagConstraints gbc_lblXStart = new GridBagConstraints();
			gbc_lblXStart.anchor = GridBagConstraints.EAST;
			gbc_lblXStart.insets = new Insets(0, 0, 5, 5);
			gbc_lblXStart.gridx = 0;
			gbc_lblXStart.gridy = 1;
			contentPanel.add(lblXStart, gbc_lblXStart);
		}
		{
			txtXStart = new JTextField();
			txtXStart.setText(Integer.toString(line.getStartPoint().getX()));
			GridBagConstraints gbc_txtXStart = new GridBagConstraints();
			gbc_txtXStart.anchor = GridBagConstraints.WEST;
			gbc_txtXStart.insets = new Insets(0, 0, 5, 0);
			gbc_txtXStart.gridx = 1;
			gbc_txtXStart.gridy = 1;
			contentPanel.add(txtXStart, gbc_txtXStart);
			txtXStart.setColumns(10);
		}
		{
			JLabel lblYStart = new JLabel("y:");
			GridBagConstraints gbc_lblYStart = new GridBagConstraints();
			gbc_lblYStart.anchor = GridBagConstraints.EAST;
			gbc_lblYStart.insets = new Insets(0, 0, 5, 5);
			gbc_lblYStart.gridx = 0;
			gbc_lblYStart.gridy = 2;
			contentPanel.add(lblYStart, gbc_lblYStart);
		}
		{
			txtYStart = new JTextField();
			txtYStart.setText(Integer.toString(line.getStartPoint().getY()));
			GridBagConstraints gbc_txtYStart = new GridBagConstraints();
			gbc_txtYStart.anchor = GridBagConstraints.WEST;
			gbc_txtYStart.insets = new Insets(0, 0, 5, 0);
			gbc_txtYStart.gridx = 1;
			gbc_txtYStart.gridy = 2;
			contentPanel.add(txtYStart, gbc_txtYStart);
			txtYStart.setColumns(10);
		}
		{
			JLabel lblNewLabel = new JLabel("Ending point coordinates:");
			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.gridwidth = 2;
			gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
			gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
			gbc_lblNewLabel.gridx = 0;
			gbc_lblNewLabel.gridy = 3;
			contentPanel.add(lblNewLabel, gbc_lblNewLabel);
		}
		{
			JLabel lblXEnd = new JLabel("x:");
			GridBagConstraints gbc_lblXEnd = new GridBagConstraints();
			gbc_lblXEnd.anchor = GridBagConstraints.EAST;
			gbc_lblXEnd.insets = new Insets(0, 0, 5, 5);
			gbc_lblXEnd.gridx = 0;
			gbc_lblXEnd.gridy = 4;
			contentPanel.add(lblXEnd, gbc_lblXEnd);
		}
		{
			txtXEnd = new JTextField();
			txtXEnd.setText(Integer.toString(line.getEndPoint().getX()));
			GridBagConstraints gbc_txtXEnd = new GridBagConstraints();
			gbc_txtXEnd.anchor = GridBagConstraints.WEST;
			gbc_txtXEnd.insets = new Insets(0, 0, 5, 0);
			gbc_txtXEnd.gridx = 1;
			gbc_txtXEnd.gridy = 4;
			contentPanel.add(txtXEnd, gbc_txtXEnd);
			txtXEnd.setColumns(10);
		}
		{
			JLabel lblYEnd = new JLabel("y:");
			GridBagConstraints gbc_lblYEnd = new GridBagConstraints();
			gbc_lblYEnd.anchor = GridBagConstraints.EAST;
			gbc_lblYEnd.insets = new Insets(0, 0, 5, 5);
			gbc_lblYEnd.gridx = 0;
			gbc_lblYEnd.gridy = 5;
			contentPanel.add(lblYEnd, gbc_lblYEnd);
		}
		{
			txtYEnd = new JTextField();
			txtYEnd.setText(Integer.toString(line.getEndPoint().getY()));
			GridBagConstraints gbc_txtYEnd = new GridBagConstraints();
			gbc_txtYEnd.insets = new Insets(0, 0, 5, 0);
			gbc_txtYEnd.anchor = GridBagConstraints.WEST;
			gbc_txtYEnd.gridx = 1;
			gbc_txtYEnd.gridy = 5;
			contentPanel.add(txtYEnd, gbc_txtYEnd);
			txtYEnd.setColumns(10);
		}
		{
			JButton btnColor = new JButton("Choose color");
			btnColor.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					borderColor = JColorChooser.showDialog(null, "Choose line color", Color.black);
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
						ok=true;
						setVisible(false);
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						ok=false;
						setVisible(false);
						line.setSelected(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
	}

	public JTextField getTxtXStart() {
		return txtXStart;
	}

	public void setTxtXStart(JTextField txtXStart) {
		this.txtXStart = txtXStart;
	}

	public JTextField getTxtYStart() {
		return txtYStart;
	}

	public void setTxtYStart(JTextField txtYStart) {
		this.txtYStart = txtYStart;
	}

	public JTextField getTxtXEnd() {
		return txtXEnd;
	}

	public void setTxtXEnd(JTextField txtXEnd) {
		this.txtXEnd = txtXEnd;
	}

	public JTextField getTxtYEnd() {
		return txtYEnd;
	}

	public void setTxtYEnd(JTextField txtYEnd) {
		this.txtYEnd = txtYEnd;
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
	
	
	
	

}
