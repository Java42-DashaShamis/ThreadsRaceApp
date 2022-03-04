package telran.printer;

public class Printer extends Thread{

String str;
int length;
volatile boolean running = true;

public void setRunning(boolean running) {
	this.running = running;
}
public Printer(String str) {
	
	this.str = str;
	length = str.length();
	setName(str);
}

@Override
public void run() {
	int index = 0;
	while(running) {
		System.out.printf("%s: %s\n", getName(), str.charAt(index));
		try {
			sleep(3000);
		} catch (InterruptedException e) {
			index++;
			if(index == str.length()) {
				index = 0;
			}
		}
	}
}
}
