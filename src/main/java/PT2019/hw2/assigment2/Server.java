package PT2019.hw2.assigment2;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;

public class Server implements Runnable {
	public volatile BlockingQueue<Client> clients;
	public volatile AtomicInteger waitingTime;
	
	public Server()
	{
		this.clients = new LinkedBlockingDeque<Client>();
		this.waitingTime = new AtomicInteger(0);
	}
	
	public int totalWaitTime()
	{
		int sum = 0;
		for(Client c : this.clients)
		{
			sum += c.getTask();
		}
		
		return sum;
	}
	
	public void addClient(Client newClient)
	{
		clients.add(newClient);
	}
	

	public void run() {
		
		while(true)//!clients.isEmpty())
		{
			try {
				Client atServer = clients.take();
				int task = atServer.taskTime;
				System.out.println("The client who has arrived now has time: "+ task+ " arrival " + atServer.arrivalTime);
				Thread.sleep(task*1000);
				waitingTime.addAndGet((-1)*atServer.taskTime);
				System.out.println("This server is done with his client!");
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			
		}
		
	}
	
	public int getQLength()
	{
		return this.clients.size();
	}
	
	public Client[] getClients()
	{
		return (Client[]) clients.toArray();
	}

}
