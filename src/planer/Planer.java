package planer;

import exceptions.Exceptions;
import exceptions.Exceptions;
import generator.ActionsTableModel;
import generator.Generator;
import generator.SchoolLessonsTableModel;
import generator.SchoolTableModel;
import gui.Gui;

import java.io.File;
import java.security.acl.NotOwnerException;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.Observable;

import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;

import output.Output;

import schedule.Day;
import schedule.Sheet;
import schedule.Task;

public class Planer extends Observable{
	public ActionsTableModel actions;
	public Generator generator;
	public Exceptions exceptions;
	public Settings settings;
	public Output output;
	public Gui gui;
	LinkedList<Sheet> sheets;
	private SchoolTableModel school;
	private SchoolLessonsTableModel schoolLessons;
	Planer() {
		actions = new ActionsTableModel();
		schoolLessons = new SchoolLessonsTableModel();
		school = new SchoolTableModel();
		generator = new Generator(this);
		settings = new Settings();
		output = new Output(this);
		sheets = new LinkedList<Sheet>();
		exceptions = new Exceptions(this);
		

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createGui();
			}
		});
	}
	public Exceptions getExceptions() {
		return exceptions;
	}
	private void createGui() {
		gui = new Gui(this);
		getSettings().addObserver(gui);
		addObserver(gui);
		gui.setVisible(true);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// New Instance of the Main Programm
		Planer planer = new Planer();
	}
	public ActionsTableModel getActions() {
		return actions;
	}
	public Output getOutput() {
		return output;
	}
	public Generator getGenerator() {
		return generator;
	}
	public Settings getSettings() {
		return settings;
	}
	public SchoolTableModel getSchool() {
		return school;
	}
	public SchoolLessonsTableModel getSchoolLessons() {
		return schoolLessons;
	}
	public LinkedList<Sheet> getSheets() {
		return sheets;
	}
	public void setSheets(LinkedList<Sheet> sheets) {
		this.sheets = sheets;
		setChanged();
		notifyObservers("sheets");
		clearChanged();
	}
	public Gui getGui() {
		return gui;
	}
	public void setGui(Gui gui) {
		this.gui = gui;
	}
	public void initGuiContents() {
			if (getSettings().getTemplate().getPath() != null) {
				getSettings().setTemplate(new File(getSettings().getTemplate().getPath()));
			}
			if (getSettings().getExportPath().getPath() != null) {
				getSettings().setExportPath(new File(getSettings().getExportPath().getPath()));
			}
			if (getSettings().getIcon() != null) {
				getSettings().setIcon(getSettings().getIcon());
			}
			getSettings().setMaximalWorkDayTime(getSettings().getMaximalWorkDayTime());
			getSettings().setWorkDays(getSettings().getWorkDays());

			getSettings().setAverageTaskTimeMax(getSettings().getAverageTaskTimeMax());
			getSettings().setAverageTaskTimeMin(getSettings().getAverageTaskTimeMin());
			
			if(getSettings().getInstitution() != null)
			getSettings().setInstitution(getSettings().getInstitution());
			
			if(getSettings().getName() != null)
			getSettings().setName(getSettings().getName());
			
			if(getSettings().getBegin() != null)
			getSettings().setBegin(getSettings().getBegin());
			
			if(getSettings().getEnd() != null)
				getSettings().setEnd(getSettings().getEnd());
			
			getSettings().setAppShipYear(getSettings().getAppShipYear());		
		}		
}
