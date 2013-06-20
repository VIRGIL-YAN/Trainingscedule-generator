package exceptions;

import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Observable;

import planer.Planer;
import schedule.Day;

public class Exceptions extends Observable{
	HashMap<Long, Day> exceptions;
	Planer planer;

	public Exceptions(Planer planer) {
		exceptions = new HashMap<Long, Day>();
		this.planer = planer;
	}

	public HashMap<Long, Day> getExceptions() {
		return exceptions;
	}

	public void addException(Day day){
		exceptions.put(day.getDateValue(),day);
		setChanged();
		notifyObservers("exception");
		clearChanged();
	}
	public void removeExceptionByDate(Calendar cal){
		for(int i = 0; i < getExceptions().size();i++){
			if(getExceptions().get(i).getDateValue() == cal.getTimeInMillis()){
				getExceptions().remove(i);
				setChanged();
				notifyObservers("exception");
				clearChanged();
			}
		}
	}

	/**
	 * This Method gets the first Day as a Calender of an Exception
	 * 
	 * @return first Day of the Exception
	 */
	public Calendar getBegin() {
		if (getExceptions().size() > 0) {
			return planer.getGenerator().generateCalendar(
					getExceptions().get(0).getDateValue());
		} else {
			return null;
		}
	}

	public Calendar getEnd() {
		if (getExceptions().size() > 0) {
			return planer.getGenerator().generateCalendar(
					getExceptions().get(getExceptions().size() - 1)
							.getDateValue());
		} else {
			return null;
		}
	}

	public Day isException(Day day) {
		for (Map.Entry<Long,Day> entry : getExceptions().entrySet()) {
			if (entry.getKey() == day.getDateValue()) {
				return entry.getValue();
			}
		}
		return day;
	}


}
