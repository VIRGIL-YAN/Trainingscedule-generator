package gui.schoolTab;

import generator.ActionsTableModel;
import generator.SchoolLessonsTableModel;
import generator.SchoolTableModel;
import gui.Gui;
import gui.schoolTab.lessonsTable.LessonsTableEditor;
import gui.schoolTab.lessonsTable.LessonsTableRenderer;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.DefaultCellEditor;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.JToolBar;

import resources.Resources;
import schedule.SchoolLesson;

import javax.swing.JButton;
import javax.swing.table.TableColumn;

import com.toedter.calendar.JDateChooser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.LinkedList;

import javax.swing.JMenuBar;
import javax.swing.JMenu;

public class SchoolTab extends JPanel {
	private JTable schoolTable;
	Gui gui;
	private SchoolTableModel schoolTableModel;
	JScrollPane timeTableScrollPane;
	private JButton addLessonsButton;
	private JDateChooser beginDateChooser = null;
	private JLabel beginLabel = null;
	private JButton deleteLessonsButton;
	private JButton addToPlanerButton;
	private LinkedList<SchoolLesson> schoolLessons;
	private JScrollPane lessonsScrollPane;
	private SchoolLessonsTableModel schoolLessonsTableModel;
	private JTable lessonsTable;
	private JPanel panel;
	private JToolBar toolBar_1;

	/**
	 * Create the panel.
	 * 
	 * @param gui
	 */
	public SchoolTab(Gui gui) {
		this.gui = gui;
		schoolLessons = new LinkedList<SchoolLesson>();
		initComponents();

	}

	private void initComponents() {

		this.setLayout(new BorderLayout());
		JTree tree = new JTree();
		add(tree, BorderLayout.WEST);
		JToolBar toolBar = new JToolBar();
		beginDateChooser = new JDateChooser();
		beginLabel = new JLabel(Resources.getInstance().getString("begin"));
		add(toolBar, BorderLayout.NORTH);
		toolBar.add(beginLabel);
		toolBar.add(beginDateChooser);

		addToPlanerButton = new JButton("addToPlaner");
		addToPlanerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		toolBar.add(addToPlanerButton);
		schoolTableModel = gui.getPlaner().getSchool();
		schoolTable = new JTable(schoolTableModel);

		schoolTable.setDefaultRenderer(SchoolLesson.class, new
		LessonsTableRenderer());
		schoolTable.setDefaultEditor(SchoolLesson.class,
				new ScheduleTableEditor(gui));

		timeTableScrollPane = new JScrollPane(schoolTable);
		this.add(timeTableScrollPane, BorderLayout.CENTER);
		schoolLessonsTableModel = gui.getPlaner().getSchoolLessons();

		panel = new JPanel();
		add(panel, BorderLayout.EAST);
		lessonsTable = new JTable(schoolLessonsTableModel);
		lessonsTable.setDefaultRenderer(SchoolLesson.class,
				new LessonsTableRenderer());
		lessonsTable.setDefaultEditor(SchoolLesson.class,
				new LessonsTableEditor(gui));
		panel.setLayout(new BorderLayout(0, 0));

		lessonsScrollPane = new JScrollPane(lessonsTable);
		panel.add(lessonsScrollPane);
		lessonsScrollPane.setPreferredSize(new Dimension(110, 402));

		toolBar_1 = new JToolBar();
		panel.add(toolBar_1, BorderLayout.NORTH);

		addLessonsButton = new JButton("Add Lessons");
		toolBar_1.add(addLessonsButton);

		deleteLessonsButton = new JButton("Delete Lesson");
		toolBar_1.add(deleteLessonsButton);
		deleteLessonsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (Integer i : lessonsTable.getSelectedRows()) {
					schoolLessonsTableModel.removeRow(lessonsTable
							.getSelectedRow());
				}
			}
		});
		addLessonsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SchoolLessonsDialog schoolLessonsCreatorDialog = new SchoolLessonsDialog(
						gui);
				schoolLessonsCreatorDialog.setVisible(true);
			}
		});

	}
}
