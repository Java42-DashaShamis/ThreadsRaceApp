package telran.multithreading.threadsrace;


public class ThreadsRace {
	private int distance;
	private int minSleep;
	private int maxSleep;
	public ThreadsRace(int distance, int minSleep, int maxSleep) {
		this.distance = distance;
		this.minSleep = minSleep;
		this.maxSleep = maxSleep;
	}

	public int getDistance() {
		return distance;
	}
	public int getMinSleep() {
		return minSleep;
	}
	public int getMaxSleep() {
		return maxSleep;
	}
	
}