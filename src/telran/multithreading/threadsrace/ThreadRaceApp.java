package telran.multithreading.threadsrace;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import telran.view.*;

public class ThreadRaceApp {
	public static int MIN_NUMBER_THREADS = 3;
	public static int MAX_NUMBER_THREADS = 10;
	public static int MIN_NUMBER_DISTANCE = 100;
	public static int MAX_NUMBER_DISTANCE = 3500;
	public static int [] sleepingTimeRange = {2,5};
	
	public static void main(String[] args ) throws InterruptedException {
		InputOutput io = new ConsoleInputOutput();
		int numOfThreads = io.readInt(String.format("Enter number of threads (from %d to %d)", MIN_NUMBER_THREADS, MAX_NUMBER_THREADS), MIN_NUMBER_THREADS, MAX_NUMBER_THREADS);
		int distance = io.readInt(String.format("Enter a distance (from %d to %d)", MIN_NUMBER_DISTANCE, MAX_NUMBER_DISTANCE), MIN_NUMBER_DISTANCE, MAX_NUMBER_DISTANCE);
		
		List<ThreadsRace> listOfThreads = new ArrayList<ThreadsRace>(getThreads(numOfThreads,distance,sleepingTimeRange));
		
		listOfThreads.forEach(t -> {
			t.start();
		});
		
		listOfThreads.forEach(t -> {
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		
		io.writeObjectLine("Congretulations to Thread-" + ThreadsRace.winner + " (winner)!");
		
	}

	private static List<ThreadsRace> getThreads(int numOfThreads, int distance, int[] sleepingTimeRange) {
		int min = sleepingTimeRange[0];
		int max = sleepingTimeRange[1];
		List<ThreadsRace> list = new ArrayList<ThreadsRace>();
		for(int i = 0; i < numOfThreads; i++) {
			int sleepingTime = (int) ((Math.random() * (max - min)) + min);
			/* V.R. It seems to me that racerId begins from 1. However it is a matter
			 * of taste.
			 */
			list.add(new ThreadsRace(i, distance, sleepingTime));
		}
		return list;
	}
}
