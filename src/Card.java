
public class Card {
	
	private final Color color;
	private final int number;
	private boolean closed = false;
	
	public Card(Color color, int number) {
		if (number > 14)
		{
			throw new IllegalArgumentException();
		}
		this.color = color;
		this.number = number;
	}
	
	public String toString()
	{
		return color + " " + number;
	}
	
	public int getNumber()
	{
		return number;
	}
	/**
	 * Check whether this card is greater than, lesser than or the same
	 * as the other card
	 * @param other
	 * @return
	 */
	public Comparison compare(Card other)
	{
		if (this.number > other.getNumber()) return Comparison.GREATER;
		if (this.number < other.getNumber()) return Comparison.LESSER;
		else return Comparison.SAME;
	}
	
	public Comparison predictOtherCard()
	{
		if (this.number > 8) return Comparison.LESSER;
		else return Comparison.GREATER;
	}
	
	public void close()
	{
		closed = true;
	}
	
	public boolean isClosed()
	{
		return closed;
	}
	

}
