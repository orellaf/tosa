package fr.lta.tosa.pokemon;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.Paths;
import java.util.Scanner;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("Est-ce que je gagne ce match pokemon?")
class PokemonTest {

	@ParameterizedTest
	@CsvSource({ "input1.txt, output1.txt", "input2.txt, output2.txt", "input3.txt, output3.txt",
			"input4.txt, output4.txt", "input5.txt, output5.txt", "input6.txt, output6.txt" })
	@DisplayName("go match")
	void test(String inputFile, String outputFile) throws Exception {
		Scanner sc = new Scanner(Paths.get(getClass().getResource(inputFile).toURI()));

		int nbCard = Integer.valueOf(sc.nextLine());
		String[] sachaDeck = sc.nextLine().split(" ");
		String[] myDeck = sc.nextLine().split(" ");

		sc.close();

		int count = 0;

		for (int i = 0; i < nbCard; i++) {

			count += Integer.valueOf(myDeck[i]).compareTo(Integer.valueOf(sachaDeck[i]));
			System.err.println("count " + count);

		}
		String result = "P";
		if (count > 0) {
			result = "G";
		} else if (count == 0) {
			result = "N";
		}
		System.out.println(result);

		sc = new Scanner(Paths.get(getClass().getResource(outputFile).toURI()));

		String expected = sc.nextLine();
		sc.close();
		
		assertEquals(expected, result);

	}

}
