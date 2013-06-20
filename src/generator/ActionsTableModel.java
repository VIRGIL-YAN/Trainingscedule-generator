package generator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.DefaultListModel;
import javax.swing.ListModel;
import javax.swing.event.ListDataListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import resources.Resources;

public class ActionsTableModel extends PlanerTableModel {
	public ActionsTableModel() {
		super();
		String[] columnName = {Resources.getInstance().getString("actions")};
		super.setColumnIdentifiers(columnName);
	}


}
