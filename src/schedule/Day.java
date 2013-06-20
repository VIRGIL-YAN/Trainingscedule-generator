package schedule;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.Observable;


public class Day extends Observable{
	LinkedList<Task> tasks;
	//the day in milliseconds to create a date out of it
	long calendarDate;
	public Day(LinkedList<Task> tasks, Long calendarDate){
		this.tasks = tasks;
		this.calendarDate = calendarDate;
	}
	public void addTask(Task task){
		tasks.add(task);
		notifyObservers();
	}
	public LinkedList<Task> getTasks(){
		return tasks;
	}
	public void setTasks(LinkedList<Task> tasks) {
		this.tasks = tasks;
	}
	public long getDateValue() {
		return calendarDate;
	}
	public Calendar getDate(){
		Calendar cal1 = Calendar.getInstance();
		cal1.setTimeInMillis(getDateValue());
		return cal1;
	}
}
