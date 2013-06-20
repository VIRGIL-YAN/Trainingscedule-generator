package gui.schoolTab.lessonsTable;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

import resources.Resources;
import schedule.SchoolLesson;

public class LessonsTableRenderer extends JLabel  implements TableCellRenderer{
	/**
	 * Create the panel.
	 */
	public LessonsTableRenderer() {
		super();
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object schoolLessonObject,
			boolean isSelected, boolean hasFocus, int row, int column) {
		
		if(schoolLessonObject == null){		
			return null;
		}
		SchoolLesson schoolLesson = (SchoolLesson) schoolLessonObject;
		String labelText = schoolLesson.getLesson();
		this.setText(labelText);
		return this;
	}

}
