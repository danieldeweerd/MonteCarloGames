
public class Tester {

	private static final int TEST_FREQ = 20000;

	public static int test(Game game, boolean displayProgress) {
		int sum = 0;

		if (displayProgress) {

			int numStripes = TEST_FREQ / 500;
			for (int i = 0; i < numStripes; i++) {
				System.out.print('-');
			}
		}

		int j = 0;
		for (int i = 0; i < TEST_FREQ; i++) {
			int slokken = game.run();
			sum = sum + slokken;

			j++;
			if (j == 500 && displayProgress) {
				System.out.print('-');
				j = 0;
			}
		}
		return sum / TEST_FREQ;
	}

	public static void main(String[] args) {
		for (int k = 1; k <= 8; k++)
		{
			for (int i = 0; i <= 8; i++) {
				int slokkenGemiddeld = test(new Bus(k, Math.min(k, i)), false);
				System.out.println(k + " KAARTEN WAARVAN " + Math.min(k, i) + " DICHT: " + slokkenGemiddeld);
			}
		}

	}

}
