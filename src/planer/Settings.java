package planer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Observable;
import java.util.Properties;

/**
 * This Class contains Method for some Settings such as the length of the 
 * Workday.
 * @author tokix
 *
 */
public class Settings extends Observable{
	Properties properties;
	int startNumber;

	public Settings(){
		properties = new Properties();
		try {
		    properties.load(new FileInputStream("settings.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.startNumber = 0;
	}
	public void saveProperties(){
		try {
		    properties.store(new FileOutputStream("settings.properties"), null);
		} catch (IOException e) {
		}
	}
	public double getAverageTaskTimeMin() {
		if(properties.getProperty("averageTaskTimeMin") != null){
			return Double.parseDouble(properties.getProperty("averageTaskTimeMin"));
		} 
		return 0;
	}
	public void setAverageTaskTimeMin(double averageTaskTimeMin) {
		properties.setProperty("averageTaskTimeMin", averageTaskTimeMin+"");
		setChanged();
		notifyObservers("averageTaskTimeMin");
		clearChanged();
	}
	public double getAverageTaskTimeMax() {
		if(properties.getProperty("averageTaskTimeMax") != null){
			return Double.parseDouble(properties.getProperty("averageTaskTimeMax"));
		} 
		return 0;
	}
	public void setAverageTaskTimeMax(double averageTaskTimeMax) {
		properties.setProperty("averageTaskTimeMax", averageTaskTimeMax+"");
		setChanged();
		notifyObservers("averageTaskTimeMax");
		clearChanged();
	}
	public int getWorkDays() {
		if(properties.getProperty("workDays") != null){
			return Integer.parseInt(properties.getProperty("workDays"));
		}
		return 0;
	}
	public void setWorkDays(int workDays) {
		properties.setProperty("workDays", ""+workDays);
		setChanged();
		notifyObservers("workDays");
		clearChanged();
	}
	public double getMaximalWorkDayTime() {
		if(properties.getProperty("maximalWorkDayTime") != null){
			return Double.parseDouble(properties.getProperty("maximalWorkDayTime"));
		} 
		return 0;	
	}
	public void setMaximalWorkDayTime(double maximalWorkDayTime) {
		properties.setProperty("maximalWorkDayTime", ""+maximalWorkDayTime);
		setChanged();
		notifyObservers("maximalWorkDayTime");
		clearChanged();
	}
	public String getName() {
		if(properties.getProperty("name") != null){
			return properties.getProperty("name");
		}
		return null;
	}
	public void setName(String name) {
		properties.setProperty("name", name);
		setChanged();
		notifyObservers("name");
		clearChanged();
	}
	public String getInstitution() {
		if(properties.getProperty("institution") != null){
			return properties.getProperty("institution");
		}
		return null;
	}
	public void setInstitution(String institution) {
		properties.setProperty("institution", institution);
		setChanged();
		notifyObservers("institution");
		clearChanged();
	}
	public Calendar getBegin() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date((Long.parseLong(properties.getProperty("begin")))));
		return cal;
	}
	public void setBegin(Calendar begin) {
		begin = normalizeCal(begin);
		properties.setProperty("begin", ""+begin.getTime().getTime());
		setChanged();
		notifyObservers("begin");
		clearChanged();
	}
	
	public Calendar getEnd() {

		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date((Long.parseLong(properties.getProperty("end")))));

		return cal;
	}
	public void setEnd(Calendar end) {
		properties.setProperty("end", ""+end.getTime().getTime());
		end = normalizeCal(end);
		setChanged();
		notifyObservers("end");
		clearChanged();
	}

	public int getStartNumber() {
		return startNumber;
	}
	public void setStartNumber(int startNumber) {
		this.startNumber = startNumber;
	}
	public File getIcon() {
		if(properties.getProperty("icon") != null){
			File file = new File(properties.getProperty("icon"));
			return file;
		} 
		return null;
	}
	public void setIcon(File icon) {
		properties.setProperty("icon", icon.getPath());
		setChanged();
		notifyObservers("icon");
		clearChanged();
	}
	public int getAppShipYear() {
		if(properties.getProperty("year") != null){
			return Integer.parseInt(properties.getProperty("year"));
		} else {
			return 0;
		}
	}
	public void setAppShipYear(int appShipYear) {
		properties.setProperty("year",""+appShipYear);
		setChanged();
		notifyObservers("appShipYear");
		clearChanged();
	}
	public File getTemplate() {
		if(properties.getProperty("template") != null){
			File file = new File(properties.getProperty("template"));
			return file;
		}
		return null;
	}
	public void setTemplate(File template) {
		properties.setProperty("template", template.getPath());
		setChanged();
		notifyObservers("template");
		clearChanged();
	}
	public File getExportPath() {
		if(properties.getProperty("export") != null){
		File file = new File(properties.getProperty("export"));
		return file;
		}
		return null;
	}
	public void setExportPath(File exportPath) {
		properties.setProperty("export", exportPath.getPath());
		setChanged();
		notifyObservers("export");
		clearChanged();
	}
	public Calendar normalizeCal(Calendar cal1){
		System.out.println("normal"+(cal1.getTimeInMillis()-(cal1.getTimeInMillis()%86400000)));
		return cal1;
	}
	
}
