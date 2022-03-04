package telran.time;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Timer extends Thread {
	private static final String DEFAULT_TIME_PATTERN = "hh:mm ss a";
	private static final long DEFAULT_TIMEOUT = 1000;
	String pattern = DEFAULT_TIME_PATTERN;
	long timeout = DEFAULT_TIMEOUT;
	public Timer() {
		setDaemon(true);
	}
	
	public void setTimeout(long timeout) {
		this.timeout = timeout;
	}

	DateTimeFormatter dtf = DateTimeFormatter.ofPattern(pattern);
	public void setPattern(String pattern) {
		this.pattern = pattern;
		dtf = DateTimeFormatter.ofPattern(pattern);
	}
	
	@Override
	public void run() {
		while(true) {
			System.out.println(LocalTime.now().format(dtf));
			try {
				sleep(timeout);
			} catch (InterruptedException e) {
				break;
			}
		}
	}
}
