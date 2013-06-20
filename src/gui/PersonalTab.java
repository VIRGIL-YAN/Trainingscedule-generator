package gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

public class PersonalTab extends JPanel implements Observer {

	private JLabel jLabel1 = null;

	private JLabel yearLabel = null;
	private JLabel jLabel = null;
	private JLabel nameLabel = null;
	private JTextField nameField = null;
	private JTextField institutionField = null;
	private JTextField yearField = null;
	Gui gui;
	private JDateChooser fromCalendar = null;
	private JDateChooser toCalendar = null;
	private JLabel Ausbildungsort = null;

	public PersonalTab(Gui gui) {
		this.gui = gui;
		gui.getPlaner().getSettings().addObserver(this);
		jLabel1 = new JLabel();
		jLabel1.setText("Bis:");
		jLabel = new JLabel();
		jLabel.setText("Von:");
		yearLabel = new JLabel();
		yearLabel.setText("Ausbildungsjahr:");
		Ausbildungsort = new JLabel();
		Ausbildungsort.setText("Ausbildungsort:");
		GridLayout gridLayout = new GridLayout();
		gridLayout.setRows(8);
		gridLayout.setColumns(2);
		nameLabel = new JLabel();
		nameLabel.setText("Name:");
		setPreferredSize(new Dimension(0, 0));
		setLayout(gridLayout);
		add(nameLabel, null);
		add(getNameField(), null);
		add(Ausbildungsort, null);
		add(getInstitutionField(), null);
		add(yearLabel, null);
		add(getYearField(), null);
		add(jLabel, null);
		add(getFromCalendar(), null);
		add(jLabel1, null);
		add(getToCalendar(), null);
	}

	/**
	 * This method initializes nameField
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getNameField() {
		if (nameField == null) {
			nameField = new JTextField();
			nameField.addFocusListener(new java.awt.event.FocusAdapter() {
				public void focusLost(java.awt.event.FocusEvent e) {
					gui.getPlaner().getSettings().setName(nameField.getText());
				}
			});
		}
		return nameField;
	}

	/**
	 * This method initializes institutionField
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getInstitutionField() {
		if (institutionField == null) {
			institutionField = new JTextField();
			institutionField
					.addFocusListener(new java.awt.event.FocusAdapter() {
						public void focusLost(java.awt.event.FocusEvent e) {
							gui.getPlaner().getSettings()
									.setInstitution(institutionField.getText());
						}
					});
		}
		return institutionField;
	}

	/**
	 * This method initializes yearField
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getYearField() {
		if (yearField == null) {
			yearField = new JTextField();
			yearField.addFocusListener(new java.awt.event.FocusAdapter() {
				public void focusLost(java.awt.event.FocusEvent e) {
					if (!yearField.getText().equals("")) {
						gui.getPlaner()
								.getSettings()
								.setAppShipYear(
										Integer.parseInt(yearField.getText()));
					}
				}
			});
		}
		return yearField;
	}

	/**
	 * This method initializes fromCalendar
	 * 
	 * @return com.toedter.calendar.JDateChooser
	 */
	private JDateChooser getFromCalendar() {
		if (fromCalendar == null) {
			fromCalendar = new JDateChooser();
			fromCalendar.addPropertyChangeListener("date",
					new java.beans.PropertyChangeListener() {
						public void propertyChange(
								java.beans.PropertyChangeEvent e) {
							gui.getPlaner().getSettings()
									.setBegin(fromCalendar.getCalendar());
						}
					});
		}
		return fromCalendar;
	}

	/**
	 * This method initializes toCalendar
	 * 
	 * @return com.toedter.calendar.JDateChooser
	 */
	private JDateChooser getToCalendar() {
		if (toCalendar == null) {
			toCalendar = new JDateChooser();
			toCalendar.addPropertyChangeListener("date",
					new java.beans.PropertyChangeListener() {
						public void propertyChange(
								java.beans.PropertyChangeEvent e) {
							gui.getPlaner().getSettings()
									.setEnd(toCalendar.getCalendar());
						}
					});
		}
		return toCalendar;
	}

	@Override
	public void update(Observable o, Object arg) {
		if (arg.equals("institution")) {
			getInstitutionField().setText(
					gui.getPlaner().getSettings().getInstitution());
		}
		if (arg.equals("name")) {
			getNameField().setText(gui.getPlaner().getSettings().getName());
		}
		if (arg.equals("begin")) {
			getFromCalendar().setCalendar(
					gui.getPlaner().getSettings().getBegin());
		}
		if (arg.equals("appShipYear")) {
			getYearField().setText(
					"" + gui.getPlaner().getSettings().getAppShipYear());
		}
		if (arg.equals("end")) {
			getToCalendar().setCalendar(gui.getPlaner().getSettings().getEnd());
		}

	}

}
