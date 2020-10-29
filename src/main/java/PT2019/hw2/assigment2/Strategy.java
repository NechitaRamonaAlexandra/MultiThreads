package PT2019.hw2.assigment2;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public interface Strategy {
	
	public void addClient(List<Server> servers, Client newClient);

}
