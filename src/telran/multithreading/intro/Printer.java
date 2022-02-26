package telran.multithreading.intro;

public class Printer extends Thread {
	private String symbol;
	private int nRuns;
	public Printer(String string, int nRuns) {
		this.symbol = string;
		this.nRuns = nRuns;
	}
	
	@Override
	public void run() {
		for(int i = 0; i < nRuns; i++) {
			try {
				sleep(10);
			} catch (InterruptedException e) {
				
			}
			System.out.println(symbol);
		}
	}
}
