package output;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.LinkedList;

import planer.Planer;

import schedule.Sheet;
import schedule.Task;

public class Output{
	Planer planer;
	public Output(Planer planer){
		this.planer = planer;
	}
	/**
	 * This Function creates the Latex \newcommand task 
	 * @param t A task that should be printed
	 * @return The newcommand task of Latex
	 */
	public String printTask(Task t){
		String taskOutput = "";
		taskOutput = "\\task{"+t.getTask()+"}";
		//If the Time is zero there must no output be produced for Exsamble in case of free days etc
		if(t.getTime() == 0){
			taskOutput += "{}";
		} else{
			taskOutput += "{"+t.getTime()+"}";
		}
		return taskOutput;
	}
	/**
	 * This Function creates the Latex \newcommand timesum
	 * @param time A time that should be printed
	 * @return The newcommand timesum
	 */
	public String printAllTime(double time){
		return "\\timesum{"+time+"}";
	}
	/**
	 * This function exports a Template and the generated Content to a 
	 * specific location
	 * @param file The Location to the file the content should be written to
	 * @param template The Templates location from which the Latex base-structure is read
	 * @param sheets The pregenerated Sheets of Weeks 
	 * @throws Exception File not exists
	 */
	public void exportTex(File file,File template, LinkedList<Sheet> sheets) throws Exception{		
			 FileWriter fw = new FileWriter(file);
			 BufferedWriter out = new BufferedWriter(fw);
			 writeTemplateToFile(template, out);		 
			 for(Sheet s:sheets){
				 String weekBegin = s.getBegin().get(Calendar.DATE)+"."+(s.getBegin().get(Calendar.MONTH)+1)+"."+s.getBegin().get(Calendar.YEAR);
				 String weekEnd = s.getEnd().get(Calendar.DATE)+"."+(s.getBegin().get(Calendar.MONTH)+1)+"."+s.getBegin().get(Calendar.YEAR);
				 //Print Header
				 //out.write("\\header{"+planer.getSettings().getIcon()+"}{"+planer.getSettings().getName()+"}{"+planer.getSettings().getAppShipYear()+"}{Position is still Missing}{"+weekBegin+"}{"+weekEnd+"}");
				 out.write("\\header{"+planer.getSettings().getIcon().getName()+"}{"+planer.getSettings().getName()+"}{"+planer.getSettings().getAppShipYear()+"}{"+planer.getSettings().getInstitution()+"}{"+weekBegin+"}{"+weekEnd+"}\n");
				 //Get each day of the Week
				 out.write("\\week");
				 double timeSum = 0;
				 for(int i = 0; i < 7; i++){
					 out.write("{");
					 //If there are no days left we don't need to print more
					 if(i <  s.getWeek().getDays().size()){
					   //for each Task print output
					   for(Task t:s.getWeek().getDays().get(i).getTasks()){
						 out.write(printTask(t));
						 //Increment the global weektime
						 timeSum += t.getTime();
					   }
					 }
					 out.write("}");
				}
				 out.write("\n");
				 out.write(printAllTime(timeSum));
				 out.write("\\newpage");
			 }
			 out.write(terminateExport());
			 out.close();
	}
	
	/**
	 * This Functions writes a Template to a specific File
	 * @param file Template to read data from
	 * @param out Outputstream to write the Data
	 */
	public void writeTemplateToFile(File file,BufferedWriter out) throws Exception{
	    FileReader fr;
			fr = new FileReader(file);
		
	    BufferedReader br = new BufferedReader(fr);
	    String line = "";
	    while((line = br.readLine()) != null){
	    	out.write(line);
	    	out.write("\n");
	    }
	    br.close();
	    
	}
	public String terminateExport(){
		return "\\end{document}";
	}
}
