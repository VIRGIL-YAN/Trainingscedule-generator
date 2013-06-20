package schedule;


public class Task{
	String task;
	double time;
	public Task(String task,double time){
		this.task = task;
		this.time = time;
	}
	public String getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;
	}
	public double getTime() {
		return time;
	}
	public void setTime(double time) {
		this.time = time;
	}

}
