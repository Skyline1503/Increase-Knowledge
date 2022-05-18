package export;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.List;

/**
 * This class writes the challenges to a text file
 */
public class WriteChallenges {
	public static void write(List<String> challenge) throws Exception {	
		// Declaring a variable called writer of type Writer.
		Writer writer = null;
		// Writing the challenges to a text file.
		try {
			File file = new File("challenges.txt");
			writer = new BufferedWriter(new FileWriter(file));
			// This is a for loop that is iterating through the list of challenges and writing each challenge to
			// the text file.
			for (int i = 0;i<challenge.size();i++) {
				String text = challenge.get(i).toString()+  "\n";
				writer.write(text);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		finally {
			// Flushing the writer and closing it.
			writer.flush();
			writer.close();
		} 
	}
}