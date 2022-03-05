package telran.multithreading.threadsrace;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.CountDownLatch;

public class Runner extends Thread {
	private ThreadsRace race;
	public int runnerId;
	public int time;
	private CountDownLatch latch;
	private static final Object mutex = new Object();
	public Runner(ThreadsRace race, int runnerId, CountDownLatch latch) {
		this.race = race;
		this.runnerId = runnerId;
		this.latch = latch;
	}
	@Override
	public void run() {
		try {
			latch.await();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		/* V.R. Using CountDownLatch mechanism will not set the same 
		 * start time for all of threads. 
		 * Only main is able to provide the same start time for all
		 */
		Instant start = Instant.now();
		int sleepRange = race.getMaxSleep() - race.getMinSleep() + 1;
		int minSleep = race.getMinSleep();
		int distance = race.getDistance();
		for (int i = 0; i < distance; i++) {
			try {
				sleep((long) (minSleep + Math.random() * sleepRange));
			} catch (InterruptedException e) {
				throw new IllegalStateException();
			}
			/* V.R.
			 * This printing has to be out of the cycle.
			 */
			printingID(runnerId);
		}
		// V.R. It is the good place for critical section here. Time calculation
		// isn't atomic operation
		time = (int) ChronoUnit.MILLIS.between(start,Instant.now());
	}
	private static void printingID(int runnerId) {
		synchronized (mutex) {
			System.out.println(runnerId);
		}
	}
}
