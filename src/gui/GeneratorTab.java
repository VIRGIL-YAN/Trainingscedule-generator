package gui;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.SpinnerNumberModel;
import javax.swing.tree.DefaultMutableTreeNode;

import resources.Resources;
import schedule.Day;
import schedule.Sheet;
import schedule.Task;

public class GeneratorTab extends JPanel implements Observer {
	private JScrollPane sheetTreeScrollPane = null;

	private JLabel maxWorkDayTimeValue = null;
	private JToolBar jJToolBarBar2 = null;
	private JButton generateButton = null;
	private JLabel workDayValue = null;
	private JPanel workDayPanel = null;
	private JPanel maxWorkDayTimePanel = null;
	private JSlider workDaySlider = null;
	private JLabel jLabel5 = null;
	private JSpinner maxTaskTimeSpinner = null;
	private JLabel jLabel2 = null;
	private JSlider maxWorkDayTimeSlider = null;
	private JLabel maxWorkDayTimeLabel = null;
	private JPanel maxTaskTimePanel = null;

	private JSpinner minTaskTimeSpinner = null;
	private JLabel jLabel4 = null;
	private JPanel jPanel = null;

	private JTree sheetTree = null;
	private JPanel generateSettingsPanel = null;
	Gui gui;

	public GeneratorTab(Gui gui) {
		this.gui = gui;
		gui.getPlaner().getSettings().addObserver(this);
		gui.getPlaner().addObserver(this);
		setLayout(new BorderLayout());
		setName(Resources.getInstance().getString("generator"));
		add(getGeneratorToolBar(), BorderLayout.NORTH);
		add(getGenerateSettingsPanel(), BorderLayout.EAST);
		add(getSheetTreeScrollPane(), BorderLayout.CENTER);
	}

	/**
	 * This method initializes jJToolBarBar2
	 * 
	 * @return javax.swing.JToolBar
	 */
	private JToolBar getGeneratorToolBar() {
		if (jJToolBarBar2 == null) {
			jJToolBarBar2 = new JToolBar();
			jJToolBarBar2.add(getGenerateButton());
		}
		return jJToolBarBar2;
	}

	/**
	 * This method initializes generateSettingsPanel
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getGenerateSettingsPanel() {
		if (generateSettingsPanel == null) {
			maxWorkDayTimeLabel = new JLabel();
			maxWorkDayTimeLabel.setText("Maximale Arbeitszeit");
			GridLayout gridLayout7 = new GridLayout();
			gridLayout7.setRows(10);
			gridLayout7.setColumns(2);
			generateSettingsPanel = new JPanel();
			generateSettingsPanel.setLayout(gridLayout7);
			generateSettingsPanel
					.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
			generateSettingsPanel.add(getMaxWorkDayTimePanel(), null);
			generateSettingsPanel.add(getMaxWorkDayTimeSlider(), null);
			generateSettingsPanel.add(getWorkDayPanel(), null);
			generateSettingsPanel.add(getWorkDaySlider(), null);
			generateSettingsPanel.add(getMaxTaskTimePanel(), null);
			generateSettingsPanel.add(getJPanel(), null);
		}
		return generateSettingsPanel;
	}

	/**
	 * This method initializes sheetTreeScrollPane
	 * 
	 * @return javax.swing.JScrollPane
	 */
	private JScrollPane getSheetTreeScrollPane() {
		if (sheetTreeScrollPane == null) {
			sheetTreeScrollPane = new JScrollPane();
			sheetTreeScrollPane.setPreferredSize(new Dimension(0, 0));
			sheetTreeScrollPane.setViewportView(getSheetTree());
		}
		return sheetTreeScrollPane;
	}

	/**
	 * This method initializes generateButton
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getGenerateButton() {
		if (generateButton == null) {
			generateButton = new JButton();
			generateButton.setText("Generieren");
			generateButton
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							try {
								gui.getPlaner().setSheets(
										gui.getPlaner()
												.getGenerator()
												.generateSchedule(
														gui.getPlaner()
																.getSettings()
																.getBegin(),
														gui.getPlaner()
																.getSettings()
																.getEnd()));
							} catch (Exception e1) {
								JOptionPane.showMessageDialog(
										gui,
										Resources.getInstance().getString(
												"noActionsSelected"),
										Resources.getInstance().getString(
												"noGenPossible"),
										JOptionPane.WARNING_MESSAGE);
							}
						}
					});
		}
		return generateButton;
	}

	@Override
	public void update(Observable o, Object arg) {
		if (arg.equals("sheets")) {
			DefaultMutableTreeNode root = new DefaultMutableTreeNode(Resources.getInstance().getObject("sheets"));
			for (Sheet s : gui.getPlaner().getSheets()) {
				DefaultMutableTreeNode week = new DefaultMutableTreeNode(""
						+ s.getBegin().getTime().toString() + "-"
						+ s.getEnd().getTime().toString());
				int daycount = 0;
				for (Day d : s.getWeek().getDays()) {
					DefaultMutableTreeNode day = new DefaultMutableTreeNode(""
							+ daycount + "-" + d.getDate().getTime().toString());
					daycount++;
					for (Task t : d.getTasks()) {
						DefaultMutableTreeNode task = new DefaultMutableTreeNode(
								t.getTask() + ":" + t.getTime());
						day.add(task);
					}
					week.add(day);
				}
				root.add(week);
			}
			sheetTree.removeAll();
			sheetTree = new JTree(root);
			sheetTreeScrollPane.setViewportView(sheetTree);
		}
		if (arg.equals("maximalWorkDayTime")) {
			maxWorkDayTimeValue.setText(""
					+ gui.getPlaner().getSettings().getMaximalWorkDayTime());
		}
		if (arg.equals("workDays")) {
			workDayValue.setText(""
					+ gui.getPlaner().getSettings().getWorkDays());
		}
		if (arg.equals("averageTaskTimeMax")) {
			getMaxTaskTimeSpinner().setValue(
					gui.getPlaner().getSettings().getAverageTaskTimeMax());
		}
		if (arg.equals("averageTaskTimeMin")) {
			getMinTaskTimeSpinner().setValue(
					gui.getPlaner().getSettings().getAverageTaskTimeMin());
		}
	}

	/**
	 * This method initializes maxTaskTimeSpinner
	 * 
	 * @return javax.swing.JSpinner
	 */
	private JSpinner getMaxTaskTimeSpinner() {
		if (maxTaskTimeSpinner == null) {
			maxTaskTimeSpinner = new JSpinner();
			maxTaskTimeSpinner.setModel(new SpinnerNumberModel(gui.getPlaner()
					.getSettings().getAverageTaskTimeMax(), 0., 10, 0.5));

			final JSpinner.NumberEditor editor = new JSpinner.NumberEditor(
					maxTaskTimeSpinner, "0.#");
			maxTaskTimeSpinner.setPreferredSize(new Dimension(60, 30));
			maxTaskTimeSpinner.setEditor(editor);
			maxTaskTimeSpinner
					.addChangeListener(new javax.swing.event.ChangeListener() {
						public void stateChanged(javax.swing.event.ChangeEvent e) {
							gui.getPlaner()
									.getSettings()
									.setAverageTaskTimeMax(
											Double.parseDouble(maxTaskTimeSpinner
													.getValue().toString()));
						}
					});

		}
		return maxTaskTimeSpinner;
	}

	/**
	 * This method initializes maxWorkDayTimePanel
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getMaxWorkDayTimePanel() {
		if (maxWorkDayTimePanel == null) {
			maxWorkDayTimeValue = new JLabel();
			maxWorkDayTimeValue.setText("");
			maxWorkDayTimePanel = new JPanel();
			maxWorkDayTimePanel.setLayout(new BorderLayout());
			maxWorkDayTimePanel.add(maxWorkDayTimeValue, BorderLayout.EAST);
			maxWorkDayTimePanel.add(maxWorkDayTimeLabel, BorderLayout.CENTER);
		}
		return maxWorkDayTimePanel;
	}

	/**
	 * This method initializes maxWorkDayTimeSlider
	 * 
	 * @return javax.swing.JSlider
	 */
	private JSlider getMaxWorkDayTimeSlider() {
		if (maxWorkDayTimeSlider == null) {
			maxWorkDayTimeSlider = new JSlider();
			maxWorkDayTimeSlider.setName("Maximale Arbeitszeit");
			maxWorkDayTimeSlider.setPaintTicks(true);
			maxWorkDayTimeSlider.setMaximum(24);
			maxWorkDayTimeSlider.setValue(8);
			maxWorkDayTimeSlider.setSnapToTicks(true);
			maxWorkDayTimeSlider.setPaintLabels(true);
			maxWorkDayTimeSlider
					.addChangeListener(new javax.swing.event.ChangeListener() {
						public void stateChanged(javax.swing.event.ChangeEvent e) {
							gui.getPlaner()
									.getSettings()
									.setMaximalWorkDayTime(
											maxWorkDayTimeSlider.getValue());
						}
					});
		}
		return maxWorkDayTimeSlider;
	}

	/**
	 * This method initializes workDayPanel
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getWorkDayPanel() {
		if (workDayPanel == null) {
			jLabel2 = new JLabel();
			jLabel2.setText("Arbeitstage pro Woche");
			workDayValue = new JLabel();
			workDayValue.setText("");
			workDayPanel = new JPanel();
			workDayPanel.setLayout(new BorderLayout());
			workDayPanel.add(workDayValue, BorderLayout.EAST);
			workDayPanel.add(jLabel2, BorderLayout.CENTER);
		}
		return workDayPanel;
	}

	/**
	 * This method initializes workDaySlider
	 * 
	 * @return javax.swing.JSlider
	 */
	private JSlider getWorkDaySlider() {
		if (workDaySlider == null) {
			workDaySlider = new JSlider();
			workDaySlider.setPaintLabels(true);
			workDaySlider.setMinimum(1);
			workDaySlider.setMaximum(7);
			workDaySlider.setSnapToTicks(true);
			workDaySlider.setValue(5);
			workDaySlider.setMinorTickSpacing(1);
			workDaySlider.setPaintTicks(true);
			workDaySlider
					.addChangeListener(new javax.swing.event.ChangeListener() {
						public void stateChanged(javax.swing.event.ChangeEvent e) {
							gui.getPlaner().getSettings()
									.setWorkDays(workDaySlider.getValue());
						}
					});
		}
		return workDaySlider;
	}

	/**
	 * This method initializes maxTaskTimePanel
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getMaxTaskTimePanel() {
		if (maxTaskTimePanel == null) {
			jLabel4 = new JLabel();
			jLabel4.setText(Resources.getInstance()
					.getString("maximalTaskTime"));
			maxTaskTimePanel = new JPanel();
			maxTaskTimePanel.setLayout(new BorderLayout());
			maxTaskTimePanel.add(jLabel4, BorderLayout.CENTER);
			maxTaskTimePanel.add(getMaxTaskTimeSpinner(), BorderLayout.EAST);
		}
		return maxTaskTimePanel;
	}

	/**
	 * This method initializes jPanel
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			jLabel5 = new JLabel();
			jLabel5.setText(Resources.getInstance()
					.getString("minimalTaskTime"));
			jPanel = new JPanel();
			jPanel.setLayout(new BorderLayout());
			jPanel.add(getMinTaskTimeSpinner(), BorderLayout.EAST);
			jPanel.add(jLabel5, BorderLayout.CENTER);
		}
		return jPanel;
	}

	/**
	 * This method initializes sheetTree
	 * 
	 * @return javax.swing.JTree
	 */
	private JTree getSheetTree() {
		if (sheetTree == null) {
			sheetTree = new JTree();
			sheetTree.setBounds(new Rectangle(0, 0, 291, 360));
			sheetTree.setPreferredSize(new Dimension(0, 0));
		}
		return sheetTree;
	}

	/**
	 * This method initializes minTaskTimeSpinner
	 * 
	 * @return javax.swing.JSpinner
	 */
	private JSpinner getMinTaskTimeSpinner() {
		if (minTaskTimeSpinner == null) {
			minTaskTimeSpinner = new JSpinner();
			minTaskTimeSpinner.setModel(new SpinnerNumberModel(gui.getPlaner()
					.getSettings().getAverageTaskTimeMax(), 0., 10, 0.5));

			final JSpinner.NumberEditor editor = new JSpinner.NumberEditor(
					minTaskTimeSpinner, "0.#");
			minTaskTimeSpinner.setPreferredSize(new Dimension(60, 30));
			minTaskTimeSpinner.setEditor(editor);
			minTaskTimeSpinner.setPreferredSize(new Dimension(60, 30));
			minTaskTimeSpinner
					.addChangeListener(new javax.swing.event.ChangeListener() {
						public void stateChanged(javax.swing.event.ChangeEvent e) {
							gui.getPlaner()
									.getSettings()
									.setAverageTaskTimeMin(
											Double.parseDouble(minTaskTimeSpinner
													.getValue().toString()));
						}
					});
		}
		return minTaskTimeSpinner;
	}
}
