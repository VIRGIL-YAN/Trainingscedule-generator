package generator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.swing.table.DefaultTableModel;

public class PlanerTableModel extends DefaultTableModel{
	public PlanerTableModel() {
		super(0,1);
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
}
