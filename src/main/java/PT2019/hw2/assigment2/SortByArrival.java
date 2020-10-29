package PT2019.hw2.assigment2;

import java.util.Comparator;

public class SortByArrival implements Comparator<Client> {

	public int compare(Client o1, Client o2) {
		//if(o1.getArrival() )
		return o1.arrivalTime - o2.arrivalTime;
	}

}
