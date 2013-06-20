package gui.exeptionTab;

import gui.Gui;

import java.awt.BorderLayout;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import javax.annotation.Resource;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeModel;

import resources.Resources;
import schedule.Day;
import schedule.Task;

public class ExceptionTab extends JPanel implements Observer {

	private JToolBar exceptionTabToolbar = null;
	private JButton addExceptionButton = null;
	private JTree exceptionTree = null;

	private JScrollPane exceptionTreeScrollPane = null;
	private JDialog addExceptionDialog = null; // @jve:decl-index=0:visual-constraint="622,9"

	private JScrollPane jScrollPane1 = null;
	JTextField jTextField;

	Gui gui;

	public ExceptionTab(Gui gui) {
		this.gui = gui;
		gui.getPlaner().getExceptions().addObserver(this);
		setLayout(new BorderLayout());
		this.add(getExceptionToolbar(), BorderLayout.NORTH);
		this.add(getExceptionTreeScrollPane(), BorderLayout.CENTER);

	}

	/**
	 * This method initializes jJToolBarBar3
	 * 
	 * @return javax.swing.JToolBar
	 */
	private JToolBar getExceptionToolbar() {
		if (exceptionTabToolbar == null) {
			exceptionTabToolbar = new JToolBar();
			exceptionTabToolbar.add(getAddExceptionButton());
		}
		return exceptionTabToolbar;
	}

	/**
	 * This method initializes addExceptionButton
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getAddExceptionButton() {
		if (addExceptionButton == null) {
			addExceptionButton = new JButton();
			addExceptionButton.setText(Resources.getInstance().getString(
					"addException"));
			addExceptionButton
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							getAddExceptionDialog().setVisible(true);
						}

					});
		}
		return addExceptionButton;
	}

	/**
	 * This method initializes exceptionTree
	 * 
	 * @return javax.swing.JTree
	 */
	public JTree getExceptionTree() {
		if (exceptionTree == null) {
			exceptionTree = new JTree();
			exceptionTree.setEditable(true);
			exceptionTree
					.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
						public void valueChanged(
								javax.swing.event.TreeSelectionEvent e) {
							System.out.println("valueChanged()"); // TODO
																	// Auto-generated
																	// Event
																	// stub
																	// valueChanged()
						}
					});
			exceptionTree.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mousePressed(java.awt.event.MouseEvent e) {
					System.out.println("mousePressed()"); // TODO Auto-generated
															// Event stub
															// mousePressed()
				}
			});
		}
		return exceptionTree;
	}

	/**
	 * This method initializes exceptionTreeScrollPane
	 * 
	 * @return javax.swing.JScrollPane
	 */
	private JScrollPane getExceptionTreeScrollPane() {
		if (exceptionTreeScrollPane == null) {
			exceptionTreeScrollPane = new JScrollPane();
			exceptionTreeScrollPane.setViewportView(getExceptionTree());
		}
		return exceptionTreeScrollPane;
	}

	/**
	 * This method initializes addExceptionDialog
	 * 
	 * @return javax.swing.JDialog
	 */
	private JDialog getAddExceptionDialog() {
		if (addExceptionDialog == null) {
			addExceptionDialog = new ExceptionDialog(gui);
		}
		return addExceptionDialog;
	}

	/**
	 * This method initializes jScrollPane1
	 * 
	 * @return javax.swing.JScrollPane
	 */
	private JScrollPane getJScrollPane1() {
		if (jScrollPane1 == null) {
			jScrollPane1 = new JScrollPane();
			jScrollPane1.setLayout(new BoxLayout(getJScrollPane1(),
					BoxLayout.Y_AXIS));
			jScrollPane1.setViewportView(getJTextField());
		}
		return jScrollPane1;
	}

	/**
	 * This method initializes jTextField
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextField() {

		if (jTextField == null) {
			jTextField = new JTextField();
		}
		return jTextField;
	}

	@Override
	public void update(Observable o, Object arg) {

		DefaultMutableTreeNode root = new DefaultMutableTreeNode(Resources.getInstance().getString("exceptions"));
		for (Map.Entry<Long,Day> d : gui.getPlaner().getExceptions().getExceptions().entrySet()) {
			DefaultMutableTreeNode day = new DefaultMutableTreeNode(""
					+ d.getValue().getDate().getTime().toString());
			for (Task t : d.getValue().getTasks()) {
				DefaultMutableTreeNode task = new DefaultMutableTreeNode(
						t.getTask() + ":" + t.getTime());
				day.add(task);
			}
			root.add(day);
		}
		getExceptionTree().removeAll();
		exceptionTree = new JTree(root);
		getExceptionTreeScrollPane().setViewportView(exceptionTree);
	}
}
