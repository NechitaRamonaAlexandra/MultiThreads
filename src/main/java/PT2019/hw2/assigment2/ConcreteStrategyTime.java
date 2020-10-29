package PT2019.hw2.assigment2;

import java.util.List;

public class ConcreteStrategyTime implements Strategy {

	public void addClient(List<Server> servers, Client newClient) {
		int min = servers.get(0).totalWaitTime();
		int index = 0;
		for(Server s : servers)
		{
			if(s.totalWaitTime() < min)
			{
				min = s.totalWaitTime();
				index = servers.indexOf(s);
			}
		}
		
		for(Server s : servers)
		{
			if(servers.indexOf(s) == index)
			{
				s.addClient(newClient);
			}
		}
		
		//servers<index>.addClient(newClient);
	}

}
