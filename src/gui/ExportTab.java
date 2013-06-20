package gui;

import java.awt.BorderLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import resources.Resources;

public class ExportTab extends JPanel implements Observer{
	Gui  gui;
	private JToolBar jJToolBarBar1 = null;

	private JLabel exportLabel = null;
	private JButton exportButton = null;
	private JButton exportPath = null;
	public ExportTab(Gui gui){
		this.gui = gui;
		gui.getPlaner().getSettings().addObserver(this);
		setLayout(new BorderLayout());
		add(getJJToolBarBar1(), BorderLayout.NORTH);
	}
	
	
	/**
	 * This method initializes jJToolBarBar1
	 * 
	 * @return javax.swing.JToolBar
	 */
	private JToolBar getJJToolBarBar1() {
		if (jJToolBarBar1 == null) {
			exportLabel = new JLabel();
			exportLabel.setText(Resources.getInstance().getString("exportPath"));
			jJToolBarBar1 = new JToolBar();
			jJToolBarBar1.add(getExportButton());
			jJToolBarBar1.add(getExportPath());
			jJToolBarBar1.add(exportLabel);
		}
		return jJToolBarBar1;
	}
	/**
	 * This method initializes exportButton
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getExportButton() {
		if (exportButton == null) {
			exportButton = new JButton();
			exportButton.setText("Exportieren");
			exportButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
						gui.getPlaner().getOutput().exportTex(
								gui.getPlaner().getSettings().getExportPath(),
								gui.getPlaner().getSettings().getTemplate(),
								gui.getPlaner().getGenerator().generateSchedule(
										gui.getPlaner().getSettings().getBegin(),
										gui.getPlaner().getSettings().getEnd()));
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(
								gui,
								Resources.getInstance().getString("controlExportPath"),
								Resources.getInstance().getString("optionsMissing"), JOptionPane.WARNING_MESSAGE);
					}

				}
			});
		}
		return exportButton;
	}
	/**
	 * This method initializes exportPath
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getExportPath() {
		if (exportPath == null) {
			exportPath = new JButton();
			exportPath.setText("Exportpfad");
			exportPath.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					int returnVal = gui.getFileChooser().showOpenDialog(gui);
					if (returnVal == JFileChooser.APPROVE_OPTION) {
						gui.getPlaner().getSettings().setExportPath(
								gui.getFileChooser().getSelectedFile());
					}
				}
			});
		}
		return exportPath;
	}


	@Override
	public void update(Observable o, Object arg) {
		if (arg.equals("export")) {
			exportLabel.setText(gui.getPlaner().getSettings().getExportPath().getPath());
		}
		
	}
}
