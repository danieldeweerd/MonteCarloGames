import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Bus extends Game{
	CardStack stack = new CardStack();
	List<Card> cardsOnTable = new ArrayList<Card>();
	int length;
	int numberClosed;
	int counter = 0;
	Random r = new Random();
	
	public Bus(int length, int numberClosed) {
		this.length = length;
		this.numberClosed = numberClosed;
		
		for (int i = 0; i < length; i++) cardsOnTable.add(stack.draw());
		for (int j = 0; j < numberClosed; j++) cardsOnTable.get(j).close();
	}
	
	public int run()
	{
		int slokken = 0;
		while(counter != length)
		{
			//System.out.println(counter);
			Card c1 = cardsOnTable.get(counter);
			Card c2 = stack.draw();
			if (c1.isClosed())
			{
				Comparison pred = randomComp();
				Comparison res = c2.compare(c1);
				if (res == pred)
				{
					counter++;
					continue;
				}
				else
				{
					counter = 0;
					slokken++;
					continue;
				}
			}
			else
			{
				Comparison pred = c1.predictOtherCard();
				Comparison res = c2.compare(c1);
				if (res == pred)
				{
					counter++;
					continue;
				}
				else
				{
					counter = 0;
					slokken++;
					continue;
				}
			}
		}
		
		counter = 0;
		return slokken;
	}
	
	public Comparison randomComp()
	{
		int x = r.nextInt(2);
		if (x == 0) return Comparison.GREATER;
		else return Comparison.LESSER;
	}
}
