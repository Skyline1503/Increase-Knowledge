package export;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;

import modele.Challenges;

public class ReadChallenges {

	public static void  readFile() throws IOException {

		Challenges result = new Challenges();
		// Reading the file and adding the challenges to the list.
		try {
			// Creating a new file object.
			File file = new File("challenges.txt");

			Reader fr = Files.newBufferedReader(file.toPath());
			BufferedReader br = new BufferedReader(fr);
			// Reading the file line by line and adding each line to the list of challenges.
			for (String line = br.readLine(); line != null; line = br.readLine()) {
				result.addChallenge(line);
			}

			// Setting the challenges to the list of challenges.
			LinkChallenges.setChallenges(result);
			// It closes the file.
			fr.close();
			br.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}