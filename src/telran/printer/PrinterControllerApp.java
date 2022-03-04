package telran.printer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class PrinterControllerApp {

	public static void main(String[] args) {
		String names[] = {"Vasya", "Moshe", "Sara"};
		ArrayList <Printer> printers = Arrays.stream(names).map(s -> new Printer(s)).collect(Collectors.toCollection(ArrayList::new));
		printers.forEach(Printer::start);
		
		Scanner scanner = new Scanner(System.in);
		while(isAnyPrinterAlive(printers)) {
			String line = scanner.nextLine();
			Integer number = getNumber(line);
			if(number != null && number >= 0 && number < names.length) {
				printers.get(number).interrupt();
			}else if(line.endsWith("q")){
				number = getNumber(line.substring(0, line.length()-1));
				if(number != null && number >= 0 && number < names.length) {
					printers.get(number).setRunning(false);
				}
			}
		}
	}

	private static boolean isAnyPrinterAlive(ArrayList<Printer> printers) {
		return printers.stream().anyMatch(p -> p.isAlive());
	}

	private static Integer getNumber(String line) {
		Integer res = null;
		try {
			res = Integer.parseInt(line);
		} catch (NumberFormatException e) {
			
		}
		return res;
	}

}
