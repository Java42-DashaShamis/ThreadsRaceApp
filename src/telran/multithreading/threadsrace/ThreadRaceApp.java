package telran.multithreading.threadsrace;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;

import telran.view.*;

public class ThreadRaceApp {

	private static final int MAX_THREADS = 20;
	private static final int MIN_DISTANCE = 10;
	private static final int MAX_DISTANCE = 1000;
	private static final int MIN_SLEEP = 2;
	private static final int MAX_SLEEP = 5;
	static CountDownLatch latch = new CountDownLatch(1);
	
	public static void main(String[] args) {
		InputOutput io = new ConsoleInputOutput();
		Item[] items = getItems();
		Menu menu = new Menu("Race Game", items);
		menu.perform(io);

	}

	private static Item[] getItems() {
		Item[] res = {
				Item.of("Start new game", ThreadRaceApp::startGame),
				Item.exit()
		};
		return res;
	}
	static void startGame(InputOutput io) {
		/* V.R.
		 * This input doesn't explain the range of values. For example,
		 * the range for distance is from MIN_DISTANCE to MAX_DISTANCE
		 */
		int nThreads = io.readInt("Enter number of the runners", 2, MAX_THREADS);
		int distance = io.readInt("Enter distance", MIN_DISTANCE, MAX_DISTANCE);
		ThreadsRace race = new ThreadsRace(distance, MIN_SLEEP, MAX_SLEEP);
		Runner[] runners = new Runner[nThreads];
		startRunners(runners, race, nThreads);
		latch.countDown();
		joinRunners(runners);
		displayResults(runners);
	}

	private static void displayResults(Runner[] runners) {
		System.out.println("Results of the game\n" + 
	"place" + " ".repeat(5) + "racer number" + " ".repeat(5) + "time");
		/* V.R. This implementation isn't effective. 
		 *  The list of runners is created using mutex mechanism. After the
		 *  creation this list is sorted.
		 *  The alternative idea is to add the runner after it's finish in the
		 *  natural order and dont't sort it at all.
		 */
		ArrayList<Runner>list = getList(runners);
		for(int i = 0; i < list.size(); i++) {
			System.out.println((i+1) + " ".repeat(14) + list.get(i).runnerId + " ".repeat(12) + list.get(i).time);
		}
	}

	private static ArrayList<Runner> getList(Runner[] runners) {
		ArrayList<Runner>list = new ArrayList<Runner>(Arrays.asList(runners));
		list.sort((r1,r2) -> r1.time - r2.time);
		return list;
	}

	private static void joinRunners(Runner[] runners) {
		IntStream.range(0, runners.length).forEach(i -> {
			try {
				runners[i].join();
			} catch (InterruptedException e) {
				throw new IllegalStateException();
			}
		});
		
	}

	private static void startRunners(Runner[] runners, ThreadsRace race, int nThreads) {
		IntStream.range(0, runners.length).forEach(i -> {
			runners[i] = new Runner(race, i + 1, latch);
			runners[i].start();
		});
		
	}

}