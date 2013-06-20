package gui.schoolTab.lessonsTable;

import gui.Gui;
import gui.schoolTab.SchoolLessonsDialog;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

import schedule.SchoolLesson;

public class LessonsTableEditor extends AbstractCellEditor implements
		TableCellEditor, ActionListener {
	public SchoolLesson schoolLesson;
	SchoolLessonsDialog schoolLessonDialog;
	JButton button;
	JDialog dialog;
	Gui gui;
	protected static final String EDIT = "edit";

	public LessonsTableEditor(Gui gui) {
		this.gui = gui;

		button = new JButton();
		button.setActionCommand(EDIT);
		button.addActionListener(this);
		button.setBorderPainted(false);

	}

	public void actionPerformed(ActionEvent e) {
		if (EDIT.equals(e.getActionCommand())) {
			// The user has clicked the cell, so
			// bring up the dialog.
			schoolLessonDialog = new SchoolLessonsDialog(gui);
			schoolLessonDialog.setVisible(true);
			schoolLessonDialog.loadData(this);

			fireEditingStopped(); // Make the renderer reappear.

		} else { // User pressed dialog's "OK" button.
			if (schoolLessonDialog.getLessonText().getText() != "") {
				if (schoolLesson == null) {
					schoolLesson = new SchoolLesson("", "", null);
				}
				schoolLesson.setLesson(schoolLessonDialog.getLessonText()
						.getText());
				schoolLesson.setPath(schoolLessonDialog.getFile());
				schoolLessonDialog.setVisible(false);
				schoolLessonDialog = null;
			}
		}
	}

	// Implement the one CellEditor method that AbstractCellEditor doesn't.
	public Object getCellEditorValue() {
		return schoolLesson;
	}

	// Implement the one method defined by TableCellEditor.
	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {
		schoolLesson = (SchoolLesson) value;
		return button;
	}
}