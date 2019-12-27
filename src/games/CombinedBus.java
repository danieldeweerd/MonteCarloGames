package games;

/**
 * Wrapper for different games that are often combined
 */
public class CombinedBus extends DrinkingGame {

    int firstStageLength;
    int secondStageLength;
    int thirdStageLength;
    int fourthStageLength;

    private int numberOfSips = 0;
    private int numberOfCardsDrawn = 0;

    public CombinedBus(int firstStageLength, int secondStageLength, int thirdStageLength, int fourthStageLength) {
        this.firstStageLength = firstStageLength;
        this.secondStageLength = secondStageLength;
        this.thirdStageLength = thirdStageLength;
        this.fourthStageLength = fourthStageLength;
    }

    @Override
    public void run() {
        numberOfSips = 0;
        numberOfCardsDrawn = 0;

        RedOrBlack firstStage = new RedOrBlack(firstStageLength);
        firstStage.run();
        int sips_1 = firstStage.getSips();
        numberOfSips += sips_1;
        numberOfCardsDrawn += firstStage.getNumberOfCards();

        HigherLower secondStage = new HigherLower(secondStageLength, 0, true);
        secondStage.run();
        int sips_2 = secondStage.getSips();
        numberOfSips += sips_2;
        numberOfCardsDrawn += secondStage.getNumberOfCards();

        InsideOutside thirdStage = new InsideOutside(thirdStageLength);
        thirdStage.run();
        int sips_3 = thirdStage.getSips();
        numberOfSips += sips_3;
        numberOfCardsDrawn += thirdStage.getNumberOfCards();

        GuessTheType fourthStage = new GuessTheType(fourthStageLength);
        fourthStage.run();
        int sips_4 = fourthStage.getSips();
        numberOfSips += sips_4;
        numberOfCardsDrawn += fourthStage.getNumberOfCards();
    }

    @Override
    public int getSips() {
        return numberOfSips;
    }

    @Override
    public int getNumberOfCards() {
        return numberOfCardsDrawn;
    }

    @Override
    public String getName() {
        return firstStageLength + "-" + secondStageLength + "-" + thirdStageLength + "-" + fourthStageLength + " samengestelde bus";
    }


}
