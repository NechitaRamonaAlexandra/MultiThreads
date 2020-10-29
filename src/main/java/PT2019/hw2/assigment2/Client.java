package PT2019.hw2.assigment2;

public class Client {
	int arrivalTime;
	int taskTime;
	
	public Client()
	{
		this.arrivalTime = 0;
		this.taskTime = 0;
	}
	
	public Client(int a, int t)
	{
		this.arrivalTime = a;
		this.taskTime = t;
	}
	
	public void setArrival(int a)
	{
		this.arrivalTime = a;
	}
	
	public void setTask(int t)
	{
		this.taskTime = t;
	}
	
	public int getArrival()
	{
		return this.arrivalTime;
	}
	
	public int getTask()
	{
		return this.taskTime;
	}

}
