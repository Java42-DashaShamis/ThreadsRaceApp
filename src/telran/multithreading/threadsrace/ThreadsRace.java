package telran.multithreading.threadsrace;

public class ThreadsRace extends Thread {
	/* V.R. 
	 * The name 'symbol' isn't suitable. 'racerId' looks much better
	 */
	private int symbol;
	private int nRuns;
	private int sleepingTime;
	
	public static int winner = -1;
	
	
	public ThreadsRace(int symbol, int nRuns, int sleepingTime) {
		this.symbol = symbol;
		this.nRuns = nRuns;
		this.sleepingTime = sleepingTime;
	}


	@Override
	public void run() {
		for(int i = 0; i < nRuns; i++) {
			try {
				sleep(sleepingTime);
			} catch (InterruptedException e) {
				
			}
			System.out.println(symbol);
		}
		if(winner == -1) {
			winner = symbol;
		}
	}

}
