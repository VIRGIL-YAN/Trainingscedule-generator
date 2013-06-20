package generator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.swing.table.DefaultTableModel;

import resources.Resources;
import schedule.SchoolLesson;

public class SchoolLessonsTableModel extends PlanerTableModel{
	public SchoolLessonsTableModel() {
		super();
		String[] columnName = {Resources.getInstance().getString("lessons")};
		
		super.setColumnIdentifiers(columnName);
	}
	@Override
	/*
     * JTable uses this method to determine the default renderer/
     * editor for each cell.  If we didn't implement this method,
     * then the last column would contain text ("true"/"false"),
     * rather than a check box.
     */
    public Class getColumnClass(int c) {
        return SchoolLesson.class;
    }
}
