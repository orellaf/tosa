package fr.lta.tosa.vegas;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Paths;
import java.util.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class IsoContest {
	@ParameterizedTest
	@CsvSource({ "input1.txt, output1.txt", "input2.txt, output2.txt", "input3.txt, output3.txt",
		"input4.txt, output4.txt" ,"input5.txt, output5.txt","input6.txt, output6.txt" })
	void test(String inputFile, String outputFile) throws Exception {
		int line;
		Scanner sc = new Scanner(Paths.get(getClass().getResource(inputFile).toURI()));

		int nbLoop = sc.nextInt();
		int index = nbLoop - 1;

		int oldResult = sc.nextInt();

		int count = 1;
		int max = 0;

		while (index > 0) {
			line = sc.nextInt();

			if (oldResult == line) {
				count++;
			} else {
				max = Math.max(count, max);
				count = 1;
			}
			oldResult = line;

			index--;
		}
		
		if(count != 1) {
			max = Math.max(count, max);
		}

		System.out.println(max);

		sc.close();

		sc = new Scanner(Paths.get(getClass().getResource(outputFile).toURI()));

		int expected = sc.nextInt();

		sc.close();

		assertEquals(expected, max);
		/*
		 * Vous pouvez aussi effectuer votre traitement une fois que vous avez lu toutes
		 * les données.
		 */
	}
}