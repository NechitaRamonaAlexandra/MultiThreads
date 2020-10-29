package PT2019.hw2.assigment2;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class SimulationFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8336117800947664657L;
	//private static final long serialVersionUID = -8613491258160802313L;
	private int height = 700, width = 700;
	private JScrollPane[] qs;
	private JList<String> clientPool;
	public JPanel panel;

	public SimulationFrame(int numberOfServers) {
		JLabel simTimeLabel = new JLabel("Simulation time: ");
		JLabel minProcLabel = new JLabel("Minimum processing time: ");
		JLabel maxProcLabel = new JLabel("Maximum processing time: ");
		JTextField simTf = new JTextField();
		JTextField minProcTf = new JTextField();
		JTextField maxProcTf = new JTextField();
		JTextField averageTf = new JTextField();
		averageTf.setEditable(false);
		
		this.qs = new JScrollPane[numberOfServers];
		for(int i = 0 ; i < numberOfServers; i++)
		{
			qs[i] = new JScrollPane();
		}
		this.setSize(height, width);
		this.setVisible(true);
		panel = new JPanel();
		this.add(panel);
		/*this.add(averageTf);
		this.add(maxProcTf);
		this.add(minProcTf);
		this.add(simTf);
		this.add(maxProcLabel);
		this.add(minProcLabel);
		this.add(simTimeLabel);
		panel.setLayout(new GridLayout(2,5));*/
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
	}

	public void clear()
	{
		String[] s = new String[3];
		for(int i = 0; i < 3; i ++)
		{
			s[i] = "";
		}
		JList<String> j = new JList<String>(s);
		for(int i = 0; i < 3; i ++)
		{
			qs[i] = new JScrollPane(j);
		}
	}
	
	public void displayData(Client[] generated, Server[] queues,int numberOfServers) {
		String[] clientsData = new String[generated.length];
		panel.removeAll();
		panel.revalidate();
		for(int i = 0 ; i < generated.length; i++)
		{
			clientsData[i] = "Client with task time: " + generated[i].taskTime + " and arrival: " + generated[i].arrivalTime;
		}
		clientPool = new JList<String>(clientsData);
		panel.add(clientPool);
		for(int i = 0; i < queues.length; i++)
		{
			String[] serv = new String[queues[i].clients.size()];
			int a = 0;
			for(Client c : queues[i].clients)
			{
				serv[a++] = "Client["+c.arrivalTime+";"+c.taskTime+"]";
			}
			JList<String> q = new JList<String>(serv);
			qs[i] = new JScrollPane(q);
		}
		for(int i = 0 ; i < numberOfServers; i++)
		{
			panel.add(qs[i]);
		}
		panel.repaint();
		panel.revalidate();
	}

}
