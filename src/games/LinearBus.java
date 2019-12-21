package games;

import cardgamelib.*;

import java.util.ArrayList;
import java.util.List;

public class LinearBus extends DrinkingGame {
	CardStack stack = new CardStack();
	List<Card> cardsOnTable = new ArrayList<Card>();
	int numberOfCardsOnTable;
	int numberOfClosedCards;
	int counter = 0;

	public LinearBus(int numberOfCardsOnTable, int numberOfClosedCards) {
		this.numberOfCardsOnTable = numberOfCardsOnTable;
		this.numberOfClosedCards = numberOfClosedCards;

		for (int i = 0; i < numberOfCardsOnTable; i++)
			cardsOnTable.add(stack.draw());
		for (int j = 0; j < numberOfClosedCards; j++)
			cardsOnTable.get(j).setClosed(true);
	}

	@Override
	public String getName() {
		return "Bussen";
	}

	public int run() {
		int slokken = 0;
		while (counter != numberOfCardsOnTable) {
			Card c1 = cardsOnTable.get(counter);
			Card c2 = stack.draw();
			if (c1.isClosed()) {
				Comparison pred = CardGuesser.randomComp();
				Comparison res = c2.compare(c1);
				if (res == pred) {
					counter++;
					continue;
				} else {
					counter = 0;
					slokken++;
					continue;
				}
			} else {
				Comparison pred = CardGuesser.predictOtherCard(c1);
				Comparison res = c2.compare(c1);
				if (res == pred) {
					counter++;
					continue;
				} else {
					counter = 0;
					slokken++;
					continue;
				}
			}
		}

		counter = 0;
		return slokken;
	}


}
