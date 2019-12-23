package games;

/**
 * Wrapper for different games that are often combined
 */
public class PyramidBus extends DrinkingGame {

    int firstStageLength = 4;
    int secondStageLength = 3;
    private int sips = 0;
    boolean checkPointsEnabled;

    public PyramidBus(boolean checkpointsEnabled) {
        this.checkPointsEnabled = checkpointsEnabled;
    }

    @Override
    public void run() {
        sips = 0;
        RedOrBlack firstStage = new RedOrBlack(firstStageLength);
        firstStage.run();
        sips += firstStage.getSips();

        HigherLower secondStage = new HigherLower(secondStageLength, 0);
        secondStage.run();
        sips += secondStage.getSips();
    }

    @Override
    public int getSips() {
        return sips;
    }

    @Override
    public String getName() {
        return "";
    }


}
