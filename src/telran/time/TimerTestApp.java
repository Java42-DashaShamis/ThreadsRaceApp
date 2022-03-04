package telran.time;

public class TimerTestApp {
	
	public static void main(String[] args) throws InterruptedException {
		Timer timer = new Timer();
		timer.setPattern("HH:mm:ss");
		timer.start();
		Thread.sleep(5000);
		timer.interrupt();
		Thread.sleep(10000);
		//Thread.currentThread().interrupt();
		//Thread.sleep(1000000000);
		
	}

}
