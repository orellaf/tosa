package fr.lta.tosa.gaz;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("trajet en voiture")
class StationTest {

	@DisplayName("est ce que le trajet est possible?")
	@ParameterizedTest
	@CsvSource({ "input1.txt, output1.txt", "input2.txt, output2.txt", "input3.txt, output3.txt",
			"input4.txt, output4.txt" })
	void test(String inputFile, String outputFile) throws Exception {
		Scanner sc = new Scanner(Paths.get(getClass().getResource(inputFile).toURI()));
		int tank = sc.nextInt();
		int consumption = sc.nextInt();

//		int stationOne = sc.nextInt();
//		int stationTwo = sc.nextInt();
//		int stationThird = sc.nextInt();
//
//		int roadDistance = sc.nextInt();

		int[] stations = { sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt() };

		sc.close();
		//

		int distanceWithoneTank = tank * 100 / consumption;
		System.err.println("distanceWithoneTank " + distanceWithoneTank);

		Arrays.sort(stations);

		boolean isOk = true;
		for (int i = stations.length - 1; i >= 0 && isOk; i--) {
			stations[i] = stations[i] - ((i - 1) >= 0 ? stations[i - 1] : 0);
			if (stations[i] > distanceWithoneTank) {
				isOk = false;
			}
			System.err.println("stations[i] " + stations[i]);
			System.err.println("index " + i);
			System.err.println("##");
		}

//		if (4 * distanceWithoneTank <= roadDistance) {
//
//			int limitOne = 0;
//			int limitTwo = distanceWithoneTank;
//
//			if (limitOne <= stationOne && limitTwo >= stationOne) {
//				isOk = true;
//			}
//			
//			if (isOk) {
//				limitOne = stationOne;
//				limitTwo = distanceWithoneTank + stationOne;
//				
//				if (limitOne <= stationTwo && limitTwo >= stationTwo) {
//					isOk = true;
//				} else {
//					isOk = false;
//				}
//				
//			}
//			
//			if (isOk) {
//				limitOne = stationTwo;
//				limitTwo = distanceWithoneTank + stationTwo;
//				
//				if (limitOne <= stationThird && limitTwo >= stationThird) {
//					isOk = true;
//				} else {
//					isOk = false;
//				}
//				
//			}
//			
//			if (isOk) {
//				if (stationThird + distanceWithoneTank < roadDistance) {
//					isOk = false;
//				}
//			}
//		}

		String actual = isOk ? "OK" : "KO";

		System.out.println(actual);

		sc = new Scanner(Paths.get(getClass().getResource(outputFile).toURI()));

		String expected = sc.nextLine();

		sc.close();

		assertEquals(expected, actual);

	}

}
