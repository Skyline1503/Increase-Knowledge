package json;

import java.io.File;
import java.io.Reader;
import java.nio.file.Files;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import modele.Deck;

/**
 * Reads the questions.json file and adds the cards to the deck
 */
public class Read {

	public static void readFile() {		

		// A try catch block.
		try {
			File file = new File("questions.json");
			Reader reader = Files.newBufferedReader(file.toPath());
			// */
			// Convert JSON array to list of Card
			Deck d = new Gson().fromJson(reader, new TypeToken<Deck>() {}.getType());

			// Setting the deck in the Link class.
			Link.setDeck(d);

			// Close the reader
			reader.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}