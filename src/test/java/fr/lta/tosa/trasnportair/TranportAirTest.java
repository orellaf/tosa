package fr.lta.tosa.trasnportair;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.Scanner;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("transport air")
class TranportAirTest {

	@DisplayName("Quelle est la gare dans la quelle nous allons se retrouver avec mon ami?")
	@ParameterizedTest
	@CsvSource({ "input1.txt, output1.txt", "input2.txt, output2.txt", "input3.txt, output3.txt",
			"input4.txt, output4.txt" })
	void test(String inputFile, String outputFile) throws Exception {
		Scanner sc = new Scanner(Paths.get(getClass().getResource(inputFile).toURI()));

		String nextLine = sc.nextLine();
		int myStation = Integer.valueOf(nextLine.split(" ")[0]);
		int myLine = Integer.valueOf(nextLine.split(" ")[1]);

		nextLine = sc.nextLine();
		int yourStation = Integer.valueOf(nextLine.split(" ")[0]);
		int yourLine = Integer.valueOf(nextLine.split(" ")[1]);

		int communStation = 36;
		if (yourStation == myStation) {
			communStation = myStation;
		} if (yourStation != 36 && myStation != 36) {
			int maxStation = myStation > yourStation ? myStation :yourStation ;
			
			maxStation = maxStation == 0 ? 1 : maxStation;

			for (int i = maxStation; i < 37; i++) {
				if (i % myLine == 0 && i % yourLine == 0) {
					communStation = i;
					break;
				}
			}
		}

		sc.close();

		sc = new Scanner(Paths.get(getClass().getResource(outputFile).toURI()));

		int expected = sc.nextInt();

		assertEquals(expected, communStation);

		sc.close();
		System.out.println(communStation);

	}

}
