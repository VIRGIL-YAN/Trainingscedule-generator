package gui.actionTab;

import generator.ActionsTableModel;
import gui.Gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.table.DefaultTableModel;

import resources.Resources;

public class ActionTab extends JPanel {
	private DefaultTableModel actionsTableModel;
	private JTable actionsTable;
	private JScrollPane jScrollPane;
	private JButton jButton;
	private JButton clearActionsButton = null;
	private JToolBar actionToolBar = null;
	private Gui gui;
	private JButton addActionButton;

	public ActionTab(Gui gui) {
		this.gui = gui;
		initComponents();
	}

	private void initComponents() {
		this.setLayout(new BorderLayout());
		actionsTableModel = gui.getPlaner().getActions();
		actionsTable = new JTable(actionsTableModel);
		jScrollPane = new JScrollPane(actionsTable);

		this.add(getActionToolBar(), BorderLayout.NORTH);
		this.add(jScrollPane, BorderLayout.CENTER);
	}

	/**
	 * This method initializes actionToolBar
	 * 
	 * @return javax.swing.JToolBar
	 */
	private JToolBar getActionToolBar() {
		if (actionToolBar == null) {
			actionToolBar = new JToolBar();
			actionToolBar.add(getJButton());
			actionToolBar.add(getClearActionsButton());
			actionToolBar.add(getAddActionButton());
		}
		return actionToolBar;
	}

	/**
	 * This method initializes jButton
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setText("Aktionen importieren");
			jButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					int returnVal = gui.getFileChooser().showOpenDialog(gui);
					if (returnVal == JFileChooser.APPROVE_OPTION) {
						try {
							gui.getPlaner()
									.getActions()
									.importActionsFromFile(
											gui.getFileChooser()
													.getSelectedFile());

						} catch (Exception e1) {
							JOptionPane.showMessageDialog(gui,
									"Bitte fÃ¼gen Sie Aktionen hinzu!",
									"Aktionen fehlen",
									JOptionPane.ERROR_MESSAGE);
						}

					}
				}
			});
		}
		return jButton;
	}

	/**
	 * This method initializes clearActionsButton
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getClearActionsButton() {
		if (clearActionsButton == null) {
			clearActionsButton = new JButton();
			clearActionsButton.setText("Aktionen löschen");
			clearActionsButton
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							for (Integer i : actionsTable.getSelectedRows()) {
								actionsTableModel.removeRow(actionsTable.getSelectedRow());
							}

						}
					});
		}
		return clearActionsButton;
	}

	private JButton getAddActionButton() {
		if (addActionButton == null) {
			addActionButton = new JButton(Resources.getInstance().getString(
					"addAction"));
			addActionButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String[] line = { "" };
					actionsTableModel.addRow(line);
				}
			});
		}
		return addActionButton;
	}
}
