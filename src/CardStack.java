import java.util.Collections;
import java.util.Stack;

public class CardStack {

	private Stack<Card> stack = new Stack<Card>();
	
	public CardStack()
	{
		init();
	}
	
	public Card draw()
	{
		Card ret = stack.pop();
		if (stack.size() == 0) init();
		return ret;
		
	}
	
	private void init()
	{
		for (int i = 2; i < 15; i++)
		{
			stack.add(new Card(Color.HARTEN, i));
			stack.add(new Card(Color.RUITEN, i));
			stack.add(new Card(Color.SCHOPPEN, i));
			stack.add(new Card(Color.KLAVER, i));
		}
		Collections.shuffle(stack);
	}

}
