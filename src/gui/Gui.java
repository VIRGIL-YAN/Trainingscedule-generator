package gui;

import gui.actionTab.ActionTab;
import gui.exeptionTab.ExceptionTab;
import gui.schoolTab.SchoolTab;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import planer.Planer;
import resources.Resources;

public class Gui extends JFrame implements Observer {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JTabbedPane jTabbedPane = null;
	private Planer planer;
	JFileChooser chooser;
	private JMenuBar jJMenuBar = null;
	private JMenu jMenu = null;
	private JMenuItem jMenuItem = null;
	private JMenuItem jMenuItem1 = null;

	private ExceptionTab exceptionTab = null;

	/**
	 * This is the default constructor
	 */
	public Gui(Planer planer) {
		super();
		setTitle(Resources.getInstance().getString("title"));
		this.planer = planer;
		chooser = new JFileChooser();
		initialize();
	}

	public Planer getPlaner() {
		return planer;
	}

	public JFileChooser getFileChooser() {
		return chooser;
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(527, 384);
		this.setJMenuBar(getJJMenuBar());
		this.setName("Ausbildungsplaner");
		this.setContentPane(getJContentPane());
		this.setTitle("JFrame");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(getJTabbedPane(), BorderLayout.NORTH);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jTabbedPane
	 * 
	 * @return javax.swing.JTabbedPane
	 */
	private JTabbedPane getJTabbedPane() {
		if (jTabbedPane == null) {
			exceptionTab = new ExceptionTab(this);
			jTabbedPane = new JTabbedPane();
			jTabbedPane.setPreferredSize(new Dimension(627, 338));
			jTabbedPane.addTab(Resources.getInstance().getString("person"),
					null, new PersonalTab(this), null);
			jTabbedPane.addTab(Resources.getInstance().getString("actions"),
					null, new ActionTab(this), null);
			jTabbedPane.addTab(Resources.getInstance().getString("school"),
					null, new SchoolTab(this), null);
			jTabbedPane.addTab(Resources.getInstance().getString("exceptions"),
					null, exceptionTab, null);
			jTabbedPane.addTab(Resources.getInstance().getString("generator"),
					null, new GeneratorTab(this), null);
			jTabbedPane.addTab(Resources.getInstance().getString("template"),
					null, new TemplateTab(this), null);
			jTabbedPane.addTab(Resources.getInstance().getString("export"),
					null, new ExportTab(this), null);
		}
		return jTabbedPane;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		this.invalidate();
		this.validate();
		this.repaint();
	}

	/**
	 * This method initializes jJMenuBar
	 * 
	 * @return javax.swing.JMenuBar
	 */
	private JMenuBar getJJMenuBar() {
		if (jJMenuBar == null) {
			jJMenuBar = new JMenuBar();
			jJMenuBar.add(getJMenu());
		}
		return jJMenuBar;
	}

	/**
	 * This method initializes jMenu
	 * 
	 * @return javax.swing.JMenu
	 */
	private JMenu getJMenu() {
		if (jMenu == null) {
			jMenu = new JMenu();
			jMenu.setText("Settings");
			jMenu.add(getJMenuItem());
			jMenu.add(getJMenuItem1());
		}
		return jMenu;
	}

	/**
	 * This method initializes jMenuItem
	 * 
	 * @return javax.swing.JMenuItem
	 */
	private JMenuItem getJMenuItem() {
		if (jMenuItem == null) {
			jMenuItem = new JMenuItem();
			jMenuItem.setText("Save");
			jMenuItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					planer.getSettings().saveProperties();
				}
			});
		}
		return jMenuItem;
	}

	/**
	 * This method initializes jMenuItem1
	 * 
	 * @return javax.swing.JMenuItem
	 */
	private JMenuItem getJMenuItem1() {
		if (jMenuItem1 == null) {
			jMenuItem1 = new JMenuItem();
			jMenuItem1.setText("Load");
			jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					planer.initGuiContents();
				}
			});
		}
		return jMenuItem1;
	}

} // @jve:decl-index=0:visual-constraint="10,10"
