package fr.lta.tosa.planning;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.Paths;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class IsoContest {
	
	@Test
	void testName() throws Exception {
		LocalTime min = LocalTime.parse("08:00");
		LocalTime max = LocalTime.parse("08:59");
		long between = ChronoUnit.MINUTES.between(min, max);
		
		System.out.println(between);
	}
	

	@ParameterizedTest
	@CsvSource({ "input1.txt, output1.txt", "input2.txt, output2.txt", "input3.txt, output3.txt",
			"input4.txt, output4.txt" })
	void test(String inputFile, String outputFile) throws Exception {
		String line;
		Scanner sc = new Scanner(Paths.get(getClass().getResource(inputFile).toURI()));

		line = sc.nextLine();

		Map<Integer, List<Interval>> map = new HashMap<>();

		while (sc.hasNextLine()) {
			line = sc.nextLine();
			/* Lisez les données et effectuez votre traitement */
			String[] data = line.split(" ");
			Integer nbOfDay = Integer.valueOf(data[0]);
			String[] intervalls = data[1].split("-");
			Interval interval = new Interval();
			interval.start = LocalTime.parse(intervalls[0]);
			interval.end = LocalTime.parse(intervalls[1]);
			
//			long between = ChronoUnit.MINUTES.between(interval.start, interval.end);
//			if (between >= 60) {

			if (map.get(nbOfDay) == null) {
				map.put(nbOfDay, new ArrayList<>());
			}
			map.get(nbOfDay).add(interval);
//			}

		}

		String result = "";
		LocalTime min = LocalTime.parse("08:00");
		LocalTime max = LocalTime.parse("17:59");

		main: for (Map.Entry<Integer, List<Interval>> entry : map.entrySet()) {

			List<Interval> list = new ArrayList<>();
			for (Interval inter : entry.getValue()) {
				LocalTime start = inter.getStart();
				LocalTime end = inter.getEnd();
			

				if (start.isAfter(min)) {
					long between = ChronoUnit.MINUTES.between(min, start);
					if (between >= 59) {
						Interval interval1 = new Interval();
						interval1.setStart(start.minusHours(1));


						interval1.setEnd(start.minusMinutes(1));

						add(list, interval1);
					}
				}

				if (end.isBefore(max)) {
					long between = ChronoUnit.MINUTES.between(end, max);
					if (between >= 59) {
						Interval interval1 = new Interval();
						interval1.setStart(end.plusMinutes(1));
						interval1.setEnd(end.plusHours(1));

						add(list, interval1);
					}
				}
				
				if (start.compareTo(min) == 0 && end.compareTo(max) == 0) {
					continue main;
				}

			}

			if (!list.isEmpty()) {
				result = entry.getKey() + " " + list.get(0);
				break;
			}
		}

		System.out.println(result);

		sc.close();
		sc = new Scanner(Paths.get(getClass().getResource(outputFile).toURI()));

		String expected = sc.nextLine();

		sc.close();

		System.err.println(expected);
		assertEquals(expected, result);
		/*
		 * Vous pouvez aussi effectuer votre traitement une fois que vous avez lu toutes
		 * les données.
		 */
	}

	private static void add(List<Interval> list, Interval interval) {

		boolean find = false;
		for (Interval inter : list) {
			if (interval.getStart().isAfter(inter.getEnd()) || interval.getEnd().isBefore(inter.getStart())) {
				find = true;
				;
			} else {
				find = false;
				break;
			}

		}

		if (find || list.isEmpty()) {
			list.add(interval);
		}

	}

	static class Interval {
		LocalTime start;
		LocalTime end;

		public LocalTime getStart() {
			return start;
		}

		public void setStart(LocalTime start) {
			this.start = start;
		}

		public LocalTime getEnd() {
			return end;
		}

		public void setEnd(LocalTime end) {
			this.end = end;
		}

		@Override
		public String toString() {
			return start.toString() + "-" + end.toString();
		}

	}
}