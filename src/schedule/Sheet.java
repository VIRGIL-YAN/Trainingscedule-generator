package schedule;

import java.util.Calendar;

import planer.Planer;


public class Sheet {
	Week week;
	int number;
	Planer planer;
	public Sheet(Week week,int number, Planer planer){
		this.week = week;
		this.number = number;
		this.planer = planer;
	}


	public Week getWeek() {
		return week;
	}


	public void setWeek(Week week) {
		this.week = week;
	}


	public Calendar getBegin() {
		if(getWeek().getDayCount() > 0){
			return planer.getGenerator().generateCalendar(getWeek().getDays().get(0).getDateValue());
		} else{
			return null;
		}
		
	}

	public Calendar getEnd() {
		if(getWeek().getDayCount() > 0){
			return planer.getGenerator().generateCalendar(getWeek().getDays().get(getWeek().getDayCount()-1).getDateValue());
		} else{
			return null;
		}
	}


	
}
