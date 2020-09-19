package fr.lta.tosa.car;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Paths;
import java.util.Scanner;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("grand prix de Monaco")
public class CarTest {

	@DisplayName("test l'algo avec les différents fichiers")
	@ParameterizedTest
	@CsvSource({ "input1.txt, output1.txt", "input2.txt, output2.txt", "input3.txt, output3.txt",
			"input4.txt, output4.txt", "input5.txt, output5.txt",  })
	void test(String inputFile, String outputFile) throws Exception {

		Scanner sc = new Scanner(Paths.get(getClass().getResource(inputFile).toURI()));
//				new Scanner(System.in);

		int playerNumber = Integer.valueOf(sc.nextLine());

		int newPosition = playerNumber;

		int nbLoop = Integer.valueOf(sc.nextLine());

		int index = nbLoop;

		while (index > 0) {

			String[] array = sc.nextLine().split(" ");

			int carNumber = Integer.valueOf(array[0]);
			String position = array[1];

			if (carNumber < playerNumber) {
				if ("I".equals(position)) {
					newPosition--;
				}

			} else if (playerNumber == carNumber && "I".equals(position)) {
				newPosition = -5;
				break;

			} else if (playerNumber == carNumber && "D".equals(position)) {
				newPosition--;

			} else if (playerNumber + 1 == carNumber && "D".equals(position)) {
				newPosition++;
			}

			index--;
		}

		String result = "KO";
		if (newPosition > 0) {
			result = newPosition + "";
		}
		sc.close();

		sc = new Scanner(Paths.get(getClass().getResource(outputFile).toURI()));

		String expected = sc.nextLine();

		assertEquals(expected, result);

		sc.close();
	}
}
