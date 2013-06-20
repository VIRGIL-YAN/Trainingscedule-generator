package generator;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.Observable;
import java.util.concurrent.ArrayBlockingQueue;

import planer.Planer;


import schedule.Day;
import schedule.Sheet;
import schedule.Task;
import schedule.Week;

public class Generator{
	Planer planer;
	public Generator(Planer planer){
		this.planer = planer;
	}
	/**
	 * Generate a complete Schedule from the given Date to another
	 * @param cal1 Date to begin the Schedule this should be a 
	 * normalized Calender to take care that there are no disturbing hours inside the calendar
	 * @param cal2 End of the Schedule-Timethis should be a normalized 
	 * Calender to take care that there are no disturbing hours inside the calendar
	 * @return
	 * @throws Exception 
	 */
	public LinkedList<Sheet> generateSchedule(Calendar cal1,Calendar cal2) throws Exception{
		long currentDate = cal1.getTime().getTime();
		LinkedList<Sheet> sheets = new LinkedList<Sheet>();
		//The Number of the current Sheet, created
 		int startNumber = planer.getSettings().getStartNumber();
 		while(currentDate < cal2.getTimeInMillis()){
 			Calendar tmpCal = Calendar.getInstance();
 			tmpCal.setTimeInMillis(currentDate);
 			if(tmpCal.DAY_OF_WEEK == (planer.getSettings().getWorkDays()+1)){
 				currentDate+=addDays(getWeekend());
 			}
			sheets.add(new Sheet(generateWeek(currentDate),startNumber, planer));
			currentDate+=addDays(7);
			startNumber++;		
		}
		return sheets;
	}
	public int getWeekend(){
		return 7-planer.getSettings().getWorkDays();
	}
	public long addDays(int days){
		return days*86400000;
	}
	/**
	 * Generates a random time between the two borders of the Settings-Class
	 * @return A random Time
	 */
	private double getRandTaskTime(){
		//Random number related to the average Task time rounded to x.5
		double randomNumber = Math.random()*planer.getSettings().getAverageTaskTimeMax();
		randomNumber = ( (int) (randomNumber * 2  + 0.5) ) / 2f;
		if(randomNumber < planer.getSettings().getAverageTaskTimeMin()){
			randomNumber = planer.getSettings().getAverageTaskTimeMin();
		}
		return randomNumber;
	}
	/**
	 * This Method generates a Week
	 * @param currentDate Start of the week-Date in Milliseconds
	 * @return generated Week
	 * @throws Exception 
	 */
	public Week generateWeek(long currentDate) throws Exception{
		Week week = new Week();
		for(int i = 0;i < planer.getSettings().getWorkDays();i++){
			//Adds a day to a week and if  it is an exceptionDay this is added instead
			week.addDay(planer.getExceptions().isException(new Day(generateTasks(),currentDate+addDays(i))));
		}
		return week;
	}
	public LinkedList<Task> generateTasks() throws Exception{
		//Generate a Task
		double workingDayHours = 0;
		
		//Counter
		int counter = 0;
		//Value that indicates when a given Task can reapear to avoid double tasks in generation
		//TODO Should be placed in Settingsdata
		int minRepeatValue = 5;
		LinkedList<Task> tasks = new LinkedList<Task>();
		Task[] taskBuffer;
		while(workingDayHours <= planer.getSettings().getMaximalWorkDayTime()){
			
			double randomNumber = getRandTaskTime();
			workingDayHours += randomNumber;
			//If our work per day is higher than the maximum allowed hours
			if(workingDayHours > planer.getSettings().getMaximalWorkDayTime()){
				double restTime = workingDayHours-randomNumber;
				randomNumber = planer.getSettings().getMaximalWorkDayTime()-restTime;				
			}
			//Check for Infinite Loop if Actionscount is smaller than minRepeatValue
			if(planer.getActions().getRowCount() <= minRepeatValue){
				minRepeatValue = planer.getActions().getRowCount()-1;
				//Buffer to Compare if the generated Task is under the last minRepeatValue Tasks
				//Could be an infinite Loop if the Number of Actions is smaller than minRepeatValue
				taskBuffer = new Task[minRepeatValue];				
			} else{
				taskBuffer = new Task[minRepeatValue];
			}
			
			
			//Test if the randomnumber is greater than zero, for in this case it is not 
			//useful to add a task with the time-Amount of zero
			if(randomNumber > 0){
				if(minRepeatValue > 0){
					Task t = new Task(planer.getActions().getRandomAction(),randomNumber);
					while(bufferConflict(taskBuffer, t)){
						t = new Task(planer.getActions().getRandomAction(),randomNumber);
					}
					taskBuffer[counter%minRepeatValue] = t;
					tasks.add(t);
				} else{
					tasks.add(new Task(planer.getActions().getRandomAction(),randomNumber));
				}
			}
			counter++;
		}		
		return 	tasks;	
	}
	
	public boolean bufferConflict(Task[] tasks, Task task){
		for(Task t: tasks){
			if(t == null) continue; 
			if(t.getTask().equals(task.getTask())){
				return true;
			}
		}
		return false;
	}
	public Calendar generateCalendar(long time){
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(time);
		return cal;
	}
}
