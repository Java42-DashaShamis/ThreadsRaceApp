package telran.multithreading.threadsrace;

public class ThreadsRace extends Thread {
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
