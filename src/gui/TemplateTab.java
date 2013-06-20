package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import resources.Resources;

public class TemplateTab extends JPanel implements Observer{

	private JLabel templateLabel = null;
	private JLabel iconLabel = null;
	private JToolBar jJToolBarBar = null;
	private JButton iconButton = null;

	private JButton templateButton = null;
	Gui gui;

	public TemplateTab(Gui gui) {
		this.gui = gui;
		gui.getPlaner().getSettings().addObserver(this);
		GridLayout gridLayout6 = new GridLayout();
		gridLayout6.setRows(2);
		GridLayout gridLayout5 = new GridLayout();
		gridLayout5.setRows(2);
		GridLayout gridLayout4 = new GridLayout();
		gridLayout4.setRows(2);
		GridLayout gridLayout2 = new GridLayout();
		gridLayout2.setRows(2);
		GridLayout gridLayout3 = new GridLayout();
		gridLayout3.setRows(2);
		iconLabel = new JLabel();
		iconLabel.setText("");
		iconLabel.setPreferredSize(new Dimension(50, 50));
		GridLayout gridLayout1 = new GridLayout();
		gridLayout1.setRows(4);
		gridLayout1.setColumns(2);

		setLayout(new BorderLayout());
		add(iconLabel, BorderLayout.CENTER);
		add(getJJToolBarBar(), BorderLayout.NORTH);
	}

	/**
	 * This method initializes jJToolBarBar
	 * 
	 * @return javax.swing.JToolBar
	 */
	private JToolBar getJJToolBarBar() {
		if (jJToolBarBar == null) {
			templateLabel = new JLabel();
			templateLabel.setText(Resources.getInstance().getString("templatePath"));
			jJToolBarBar = new JToolBar();
			jJToolBarBar.add(getIconButton());
			jJToolBarBar.add(getTemplateButton());
			jJToolBarBar.add(templateLabel);
		}
		return jJToolBarBar;
	}

	/**
	 * This method initializes iconButton
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getIconButton() {
		if (iconButton == null) {
			iconButton = new JButton();
			iconButton.setText(Resources.getInstance().getString("chooseIcon"));
			iconButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					int returnVal = gui.getFileChooser().showOpenDialog(gui);
					if (returnVal == JFileChooser.APPROVE_OPTION) {
						gui.getPlaner().getSettings().setIcon( gui.getFileChooser().getSelectedFile());
					}
				}

			});
		}
		return iconButton;
	}

	/**
	 * This method initializes templateButton
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getTemplateButton() {
		if (templateButton == null) {
			templateButton = new JButton();
			templateButton.setText(Resources.getInstance().getString("chooseTemplate"));
			templateButton
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							int returnVal = gui.getFileChooser()
									.showOpenDialog(gui);
							if (returnVal == JFileChooser.APPROVE_OPTION) {
								gui.getPlaner().getSettings().setTemplate(
										gui.getFileChooser().getSelectedFile());
							}
						}
					});
		}
		return templateButton;
	}

	@Override
	public void update(Observable o, Object arg) {
		if (arg.equals("template")) {
			templateLabel.setText(gui.getPlaner().getSettings().getTemplate().getPath());
		}
		if (arg.equals("icon")) {
			ImageIcon icon = new ImageIcon(gui.getPlaner().getSettings().getIcon()
					.getPath());
			iconLabel.setIcon(icon);
		}		
	}

}
