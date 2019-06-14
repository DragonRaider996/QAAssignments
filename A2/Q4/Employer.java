import java.util.ArrayList;

public class Employer
{
	private ArrayList<IWorker> allWorkers;

	public Employer()
	{
		allWorkers = new ArrayList<IWorker>();
		for (int i = 0; i < 5; i++)
		{
			allWorkers.add(new HourlyWorker());
		}
		for (int i = 0; i < 5; i++)
		{
			allWorkers.add(new SalaryWorker());
		}
	}

	public void outputWageCostsForAllStaff(int hours)
	{
		float cost = 0.0f;
		for (int i = 0; i < allWorkers.size(); i++)
		{
			IWorker worker = allWorkers.get(i);
			cost += worker.calculatePay(hours);
		}
		System.out.println("Total wage cost for all staff = $" + cost);
	}
}