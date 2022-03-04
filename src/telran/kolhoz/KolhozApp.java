package telran.kolhoz;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class KolhozApp {

	private static final int N_LOADS = 100000;
	private static final long N_TRUCKS = 10000;

	public static void main(String[] args) {
		List<Truck> trucks = Stream.generate(() -> new Truck(1, N_LOADS)).limit(N_TRUCKS).collect(Collectors.toList());
		Instant start = Instant.now();
		trucks.forEach(Truck::start);
		trucks.forEach(t -> {
			try {
				t.join();
			} catch (InterruptedException e) {
				
			}
		});
		System.out.printf("elevaator1 = %d, elevator2 = %d, running time = %d\n", Truck.getElevator1(), Truck.getElevator2(), ChronoUnit.MILLIS.between(start, Instant.now()));
	}

}
