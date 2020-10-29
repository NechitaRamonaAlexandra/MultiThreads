package PT2019.hw2.assigment2;

import java.security.SecureRandom;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class SimulationManager implements Runnable {
	public int timeLimit; //maximum processing time/generating in UI
	public int maxProcessingTime;
	public int minProcessingTime;
	public int numberOfServers = 3;
	public int numberOfClients = 15;
	public SimulationFrame myFrame;
	public int timeWait;
	
	private Scheduler[] managers; // responsible for assigning clients to the servers
	
	private volatile Client[] generatedClients; //pool of randomly generated clients
	public Object lock;
	
	public SimulationManager()
	{
		this.myFrame = new SimulationFrame(numberOfServers);
		this.maxProcessingTime = 10;
		this.minProcessingTime = 1;
		this.timeLimit = 17;
		this.generatedClients = generateRandomClients(numberOfClients);
		this.managers = new Scheduler[numberOfServers];
		for(int k = 0; k < numberOfServers; k++)
		{
			managers[k] = new Scheduler();
		}
	}
	
	private Client[] generateRandomClients(int howMany)		//function using Random for generating the clients
	{
		Client[] temporary = new Client[howMany];
		Random rand = new SecureRandom();
		for(int i = 0 ; i < howMany; i++)
		{
			int a = rand.nextInt(timeLimit);
			int task = rand.nextInt(this.maxProcessingTime - this.minProcessingTime + 1) + this.minProcessingTime;
			Client c = new Client(a,task);
			temporary[i] = c;
		}
		for(int i = 0; i < howMany; i++)
		{
			for(int j = i; j < howMany; j++)
			{
				if(temporary[i].arrivalTime > temporary[j].arrivalTime)
				{
					Client buf;
					buf = temporary[i];
					temporary[i] = temporary[j];
					temporary[j] = buf;
				}
			}
		}
		
		return temporary;
	}

	public int leastQ()
	{
		int index = 0;
		int minQ = managers[0].getServer().totalWaitTime();
		for(int i = 0; i < numberOfServers; i++)
		{
			if(managers[i].getServer().totalWaitTime() < minQ)
			{
				minQ = managers[i].getServer().totalWaitTime();
				index = i;
			}
		}
		
		return index;
	}
	
	public void run() {
		int currentTime = 0;
		int i=0;
		while(currentTime <= this.timeLimit && i < numberOfClients)
		{
			while(generatedClients[i].arrivalTime == currentTime && i < numberOfClients-1)
			{
				int min = this.leastQ();
				System.out.println("Client sent to desk!");
				managers[min].assignTask(generatedClients[i]);
				timeWait = managers[min].getServer().waitingTime.addAndGet(generatedClients[i].taskTime);
				i++;
			}
			if(generatedClients[numberOfClients-1].arrivalTime == currentTime)
				managers[1].assignTask(generatedClients[numberOfClients-1]);
			currentTime++;
			Server[] sDisplay = new Server[numberOfServers];
			for(int k = 0 ; k < numberOfServers; k++)
				sDisplay[k] = managers[k].getServer();
			myFrame.displayData(generatedClients,sDisplay,numberOfServers);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("pe aici nu se mai intampla nimic");
	}
	
	public static void main(String[] args)
	{
		SimulationManager s = new SimulationManager();
		Thread t = new Thread(s);
		t.start();
		try {
			for(int i = 0; i < s.numberOfServers; i++)
			{
				s.managers[i].timeToStop();
			}
			t.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

}
