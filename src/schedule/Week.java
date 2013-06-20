package schedule;
import java.util.LinkedList;


public class Week {
	LinkedList<Day> days;
	public Week(){
		days = new LinkedList<Day>();
	}
	public void addDay(Day day){
		days.add(day);
	}
	public LinkedList<Day> getWeek(){
		return days;
	}
	public int getDayCount(){
		return days.size();
	}
	public LinkedList<Day> getDays() {
		return days;
	}
	public void setDays(LinkedList<Day> days) {
		this.days = days;
	}
	
}
