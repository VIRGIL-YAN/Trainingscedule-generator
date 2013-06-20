package gui.schoolTab;

import generator.SchoolLessonsTableModel;
import gui.Gui;
import gui.schoolTab.lessonsTable.LessonsTableEditor;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.FileChooserUI;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import schedule.SchoolLesson;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

public class SchoolLessonsDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField lessonText;
	private File file;
	JLabel path;
	JFileChooser chooser;
	SchoolLessonsTableModel schoolLessonsTableModel;
	Gui gui;
	boolean newEntry = true;

	SchoolLesson schoolLesson;

	/**
	 * Create the dialog.
	 * @param gui 
	 * @param tableChooserEditor 
	 * @param button 
	 * @param tableChooserEditor 
	 */
	public SchoolLessonsDialog(Gui gui) {
		this.gui = gui;
		chooser = gui.getFileChooser();
		this.schoolLessonsTableModel = gui.getPlaner().getSchoolLessons();
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lessonLabel = new JLabel("Lesson:");
			GridBagConstraints gbc_lessonLabel = new GridBagConstraints();
			gbc_lessonLabel.anchor = GridBagConstraints.EAST;
			gbc_lessonLabel.insets = new Insets(0, 0, 5, 5);
			gbc_lessonLabel.gridx = 0;
			gbc_lessonLabel.gridy = 0;
			contentPanel.add(lessonLabel, gbc_lessonLabel);
		}
		{
			lessonText = new JTextField();
			GridBagConstraints gbc_lessonText = new GridBagConstraints();
			gbc_lessonText.insets = new Insets(0, 0, 5, 0);
			gbc_lessonText.fill = GridBagConstraints.HORIZONTAL;
			gbc_lessonText.gridx = 1;
			gbc_lessonText.gridy = 0;
			contentPanel.add(lessonText, gbc_lessonText);
			lessonText.setColumns(10);
		}
		{
			JLabel filePath = new JLabel("filePath");
			GridBagConstraints gbc_filePath = new GridBagConstraints();
			gbc_filePath.insets = new Insets(0, 0, 5, 5);
			gbc_filePath.gridx = 0;
			gbc_filePath.gridy = 1;
			contentPanel.add(filePath, gbc_filePath);
		}
		{
			path = new JLabel("Path");
			GridBagConstraints gbc_path = new GridBagConstraints();
			gbc_path.insets = new Insets(0, 0, 5, 0);
			gbc_path.gridx = 1;
			gbc_path.gridy = 1;
			contentPanel.add(path, gbc_path);
		}
		{
			JLabel lblGeneratorfile = new JLabel("Generatorfile");
			GridBagConstraints gbc_lblGeneratorfile = new GridBagConstraints();
			gbc_lblGeneratorfile.insets = new Insets(0, 0, 0, 5);
			gbc_lblGeneratorfile.gridx = 0;
			gbc_lblGeneratorfile.gridy = 2;
			contentPanel.add(lblGeneratorfile, gbc_lblGeneratorfile);
		}
		{
			JButton fileChooserButton = new JButton("choosefile");
			fileChooserButton.addActionListener(new ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					int returnVal = getGui().getFileChooser().showOpenDialog(getGui());
					if (returnVal == JFileChooser.APPROVE_OPTION) {
						file = chooser.getSelectedFile();
						try {
							path.setText(file.getCanonicalPath().toString());
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			});
			GridBagConstraints gbc_fileChooserButton = new GridBagConstraints();
			gbc_fileChooserButton.gridx = 1;
			gbc_fileChooserButton.gridy = 2;
			contentPanel.add(fileChooserButton, gbc_fileChooserButton);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(file != null && lessonText.getText() != ""){
							if(newEntry){
								SchoolLesson schoolLesson = new SchoolLesson(lessonText.getText(), "", file);
								addSchoolLesson(schoolLesson);	
							} else{
								schoolLesson.setLesson(lessonText.getText());
								schoolLesson.setPath(file);
							}
							setVisible(false);
						} else{
							
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);				
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	private void addSchoolLesson(SchoolLesson schoolLesson){
		SchoolLesson[] schoolLessons = {schoolLesson};
		schoolLessonsTableModel.addRow(schoolLessons);
	}
	public JTextField getLessonText() {
		return lessonText;
	}
	private Gui getGui(){
		return gui;
	}
	public File getFile(){
		return file;
	}
	public void setLessonText(JTextField lessonText) {
		this.lessonText = lessonText;
	}
	
	public void loadData(LessonsTableEditor tableChooserEditor){
		newEntry = false;
		schoolLesson = tableChooserEditor.schoolLesson;
		this.lessonText.setText(schoolLesson.getLesson());
		this.file = schoolLesson.getPath();
		try {
			path.setText(schoolLesson.getPath().getCanonicalFile().toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
}
