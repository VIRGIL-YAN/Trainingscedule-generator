package gui.exeptionTab;

import gui.Gui;
import gui.JTask;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.util.LinkedList;
import java.util.ResourceBundle;

import javax.annotation.Resource;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;

import resources.Resources;
import schedule.Day;
import schedule.Task;

import com.toedter.calendar.JDateChooser;

public class ExceptionDialog extends JDialog {
	private JPanel addExceptionContentPane = null;
	private JLabel timeSpanLabel = null;
	private JDateChooser addExceptionDateEnd = null;
	private JPanel taskPanel = null;
	private JButton addExceptionDialogButton = null;
	private JDateChooser addExceptionDateBegin = null;
	private JButton addTaskButton = null;
	private JScrollPane addTaskDialogScrollPanel = null;
	private JToolBar jJToolBarBar4 = null;
	private JToolBar jJToolBarBar5 = null;

	private JButton deleteDialogTask = null;
	Gui gui;

	public ExceptionDialog(Gui gui) {
		this.gui = gui;
		setTitle(Resources.getInstance().getString("addException"));
		setSize(new Dimension(529, 228));
		setContentPane(getAddExceptionContentPane());
	}

	/**
	 * This method initializes addExceptionContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getAddExceptionContentPane() {
		if (addExceptionContentPane == null) {
			addExceptionContentPane = new JPanel();
			addExceptionContentPane.setLayout(new BorderLayout());
			addExceptionContentPane.add(getJJToolBarBar4(), BorderLayout.NORTH);
			addExceptionContentPane.add(getJJToolBarBar5(), BorderLayout.SOUTH);
			addExceptionContentPane.add(getAddTaskDialogScrollPanel(),
					BorderLayout.CENTER);
		}
		return addExceptionContentPane;
	}

	/**
	 * This method initializes jJToolBarBar4
	 * 
	 * @return javax.swing.JToolBar
	 */
	private JToolBar getJJToolBarBar4() {
		if (jJToolBarBar4 == null) {
			timeSpanLabel = new JLabel();
			timeSpanLabel.setText(Resources.getInstance().getString("timeSpan"));
			jJToolBarBar4 = new JToolBar();
			jJToolBarBar4.add(timeSpanLabel);
			jJToolBarBar4.add(getAddExceptionDateBegin());
			jJToolBarBar4.add(getAddExceptionDateEnd());
			jJToolBarBar4.add(getAddTaskButton());
			jJToolBarBar4.add(getDeleteDialogTask());
		}
		return jJToolBarBar4;
	}

	/**
	 * This method initializes jJToolBarBar5
	 * 
	 * @return javax.swing.JToolBar
	 */
	private JToolBar getJJToolBarBar5() {
		if (jJToolBarBar5 == null) {
			jJToolBarBar5 = new JToolBar();
			jJToolBarBar5.add(getAddExceptionDialogButton());
		}
		return jJToolBarBar5;
	}

	/**
	 * This method initializes addTaskDialogScrollPanel
	 * 
	 * @return javax.swing.JScrollPane
	 */
	private JScrollPane getAddTaskDialogScrollPanel() {
		if (addTaskDialogScrollPanel == null) {
			addTaskDialogScrollPanel = new JScrollPane();
			addTaskDialogScrollPanel.setViewportView(getTaskPanel());
		}
		return addTaskDialogScrollPanel;
	}

	/**
	 * This method initializes addExceptionDateBegin
	 * 
	 * @return com.toedter.calendar.JDateChooser
	 */
	private JDateChooser getAddExceptionDateBegin() {
		if (addExceptionDateBegin == null) {
			addExceptionDateBegin = new JDateChooser();
		}
		return addExceptionDateBegin;
	}

	/**
	 * This method initializes deleteDialogTask
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getDeleteDialogTask() {
		if (deleteDialogTask == null) {
			deleteDialogTask = new JButton();
			deleteDialogTask.setText(Resources.getInstance().getString("deleteTask"));
			deleteDialogTask
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							if (taskPanel.getComponents().length > 0) {
								taskPanel.remove(taskPanel.getComponents().length - 1);
								addTaskDialogScrollPanel.invalidate();
								addTaskDialogScrollPanel.validate();
								addTaskDialogScrollPanel.repaint();
							}
						}
					});
		}
		return deleteDialogTask;
	}

	/**
	 * This method initializes addExceptionDateEnd
	 * 
	 * @return com.toedter.calendar.JDateChooser
	 */
	private JDateChooser getAddExceptionDateEnd() {
		if (addExceptionDateEnd == null) {
			addExceptionDateEnd = new JDateChooser();
		}
		return addExceptionDateEnd;
	}

	/**
	 * This method initializes addTaskButton
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getAddTaskButton() {
		if (addTaskButton == null) {
			addTaskButton = new JButton();
			addTaskButton.setText(Resources.getInstance().getString("addTask"));
			addTaskButton
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							getTaskPanel().add(new JTask(gui.getPlaner()));
							getAddTaskDialogScrollPanel().invalidate();
							getAddTaskDialogScrollPanel().validate();
							getAddTaskDialogScrollPanel().repaint();
						}
					});
		}
		return addTaskButton;
	}

	/**
	 * This method initializes addExceptionDialogButton
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getAddExceptionDialogButton() {
		if (addExceptionDialogButton == null) {
			addExceptionDialogButton = new JButton();
			addExceptionDialogButton.setText(Resources.getInstance().getString("ok"));
			addExceptionDialogButton
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							LinkedList<Task> tasks = new LinkedList<Task>();
							for (Component c : getTaskPanel().getComponents()) {
								JTask t = (JTask) c;
								tasks.add(new Task(t.getText(), t.getTime()));
							}
							if ((addExceptionDateBegin.getCalendar() == null)
									|| (addExceptionDateEnd.getCalendar() == null)
									|| (tasks.size() == 0)) {
								JOptionPane.showMessageDialog(gui,
										Resources.getInstance().getString("pleaseFillAllFields"),
										Resources.getInstance().getString("fieldsMissing"),
										JOptionPane.ERROR_MESSAGE);
							} else {
								// Get the beginning date
								long currentDate = addExceptionDateBegin
										.getCalendar().getTimeInMillis();

								while (currentDate <= addExceptionDateEnd
										.getCalendar().getTimeInMillis()) {
									gui.getPlaner()
											.getExceptions()
											.addException(
													new Day(tasks, currentDate));
									currentDate += gui.getPlaner()
											.getGenerator().addDays(1);
								}

								hide();
								getTaskPanel().removeAll();
							}
						}
					});
		}
		return addExceptionDialogButton;
	}
	/**
	 * This method initializes taskPanel
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getTaskPanel() {
		if (taskPanel == null) {
			taskPanel = new JPanel();
			taskPanel
					.setLayout(new BoxLayout(getTaskPanel(), BoxLayout.Y_AXIS));
		}
		return taskPanel;
	}
}
