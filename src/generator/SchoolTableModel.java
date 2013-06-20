package generator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.ListModel;
import javax.swing.event.ListDataListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import resources.Resources;
import schedule.SchoolLesson;

public class SchoolTableModel extends DefaultTableModel {
	
	public SchoolTableModel() {
		super(0,0);
		super.setColumnIdentifiers(getDescription());
		for(int i = 0; i < 9; i++){
			this.addRow(new Vector<SchoolLesson>());
		}
	}
	private String[] getDescription() {
		String[] columnNames = { Resources.getInstance().getString("monday"),
				Resources.getInstance().getString("tuesday"),
				Resources.getInstance().getString("wednesday"),
				Resources.getInstance().getString("thursday"),
				Resources.getInstance().getString("friday"),
				Resources.getInstance().getString("saturday"),
				Resources.getInstance().getString("sunday") };
		return columnNames;
	}
	
	public void importActionsFromFile(File file) throws Exception{
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line = "";
		while((line = br.readLine()) != null){
			String[] lineObject = { line };
			addRow(lineObject);
			fireTableDataChanged();
		}
	}
	public String getRandomAction() throws Exception{
		int randomInt;
		if(getRowCount()>0){
		randomInt = (int) (Math.random()*(getRowCount()-1));
		} else{
			throw new Exception();
		}
		return (String) getValueAt((randomInt), 0);
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
