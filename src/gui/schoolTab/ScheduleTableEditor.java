package gui.schoolTab;

import gui.Gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

import schedule.SchoolLesson;

public class ScheduleTableEditor extends AbstractCellEditor implements
		TableCellEditor, ActionListener {
	SchoolLesson schoolLesson;
	JComboBox comboBox;

	JButton button;
	Gui gui;
	protected static final String EDIT = "edit";

	public ScheduleTableEditor(Gui gui) {
		this.gui = gui;
		comboBox = new JComboBox(gui.getPlaner().getSchoolLessons()
				.getDataVector());
		button = new JButton();
		button.setActionCommand(EDIT);
		button.addActionListener(this);
		button.setBorderPainted(false);
	}

	public void actionPerformed(ActionEvent e) {
		fireEditingStopped();
	}

	// Implement the one CellEditor method that AbstractCellEditor doesn't.
	public Object getCellEditorValue() {
		return schoolLesson;
	}

	// Implement the one method defined by TableCellEditor.
	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {
		schoolLesson = (SchoolLesson) value;
		return comboBox;
	}
}