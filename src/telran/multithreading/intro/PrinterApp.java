package telran.multithreading.intro;

public class PrinterApp {
	public static void main(String[] args ) throws InterruptedException {
		Printer printer1 = new Printer("*", 100);
		Printer printer2 = new Printer("#", 100);
		printer1.start();
		printer2.start();
		printer1.join();
		printer2.join();
		System.out.println("All threads are over");
	}
		
}
