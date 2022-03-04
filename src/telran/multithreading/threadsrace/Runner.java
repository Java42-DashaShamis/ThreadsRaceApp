package telran.multithreading.threadsrace;

public class Runner extends Thread {
	private ThreadsRace race;
	private int runnerId;
	public Runner(ThreadsRace race, int runnerId) {
		this.race = race;
		this.runnerId = runnerId;
	}
	@Override
	public void run() {
		int sleepRange = race.getMaxSleep() - race.getMinSleep() + 1;
		int minSleep = race.getMinSleep();
		int distance = race.getDistance();
		for (int i = 0; i < distance; i++) {
			try {
				sleep((long) (minSleep + Math.random() * sleepRange));
			} catch (InterruptedException e) {
				throw new IllegalStateException();
			}
			System.out.println(runnerId);
		}
		race.setWinner(runnerId);
	}
}
