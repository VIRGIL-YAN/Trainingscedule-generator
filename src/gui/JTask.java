package gui;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import planer.Planer;

public class JTask extends JPanel {

	private static final long serialVersionUID = 1L;
	private JSpinner taskTimer = null;
	private JTextField taskText = null;
	Planer planer;
	/**
	 * This is the default constructor
	 */
	public JTask(Planer planer) {
		super();
		this.planer = planer;
		this.setPreferredSize(new Dimension(this.getWidth(),30));
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(300, 200);
		this.setLayout(new BorderLayout());
		this.add(getTaskTimer(), BorderLayout.EAST);
		this.add(getTaskText(), BorderLayout.CENTER);
	}

	/**
	 * This method initializes taskTimer	
	 * 	
	 * @return javax.swing.JSpinner	
	 */
	private JSpinner getTaskTimer() {
		if (taskTimer == null) {
			taskTimer = new JSpinner(new SpinnerNumberModel(0., 0., planer.getSettings().getAverageTaskTimeMax(), 0.5));
		    final JSpinner.NumberEditor editor =
		          new JSpinner.NumberEditor(taskTimer, "0.#");
		    taskTimer.setPreferredSize(new Dimension(60, this.getHeight()));
		    taskTimer.setEditor(editor);
		}
		return taskTimer;
	}

	/**
	 * This method initializes taskText	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTaskText() {
		if (taskText == null) {
			taskText = new JTextField();
		}
		return taskText;
	}
	public String getText(){
		return getTaskText().getText();
	}
	public double getTime(){
		return Double.parseDouble((getTaskTimer().getValue()+""));
	}

}
