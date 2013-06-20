package schedule;

import generator.StringImporter;

import java.io.File;
import java.util.LinkedList;
import java.util.Observable;

public class SchoolLesson extends Observable {
	String lesson;
	String description;
	int taskLimit;
	File file;

	/**
	 * 
	 * @param lesson
	 *            The Lesson for example Math or Physics
	 * @param description
	 *            The topic you covered in your lesson this overrides the
	 *            filepath and the action is not random
	 * @param filePath
	 *            if no filepath is given the description is used, if a filepath
	 *            is given you can generate the content in respect to the given
	 *            File Content
	 * @throws Exception
	 *             Is thrown if filePath and Description are not given
	 */
	public SchoolLesson(String lesson, String description, File file) {
		this.lesson = lesson;
		this.description = description;
		this.file = file;

		taskLimit = 1;
	}

	void generate() throws Exception {
		if (file != null) {
			LinkedList<String> descriptions = StringImporter.getInstance()
					.importStringsFromFile(file);
			for (int i = 0; i < taskLimit; i++) {
				description = descriptions.get((int) Math.round(Math.random()
						* descriptions.size()));
				notifyObservers("description");
			}
		}
	}

	public String getLesson() {
		return lesson;
	}

	public void setLesson(String lesson) {
		this.lesson = lesson;
	}

	public String getDescription() {
		return description;
	}
	public File getPath(){
		return file;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public Task getTask() {
		return new Task(lesson + ":" + description, 0);
	}
	public void setPath(File file){
		this.file = file;
	}
}
