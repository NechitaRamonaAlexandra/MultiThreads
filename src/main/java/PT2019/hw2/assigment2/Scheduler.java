package PT2019.hw2.assigment2;

public class Scheduler {
	private Server server;
	//protected ExecutorService executor;
	protected Thread t;
	public Scheduler()
	{
		this.server = new Server();
		this.t = new Thread(server);
		t.start();
		
	}
	
	public void assignTask(Client newClient)
	{
		server.addClient(newClient);
	}

	public Server getServer()
	{
		return this.server;
	}
	
	public void timeToStop()
	{
		try {
			t.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
