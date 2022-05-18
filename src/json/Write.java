package json;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import modele.Deck;

/**
 * Write a class that writes a list of cards to a file in JSON format
 */
public class Write {

	/**
	 * This function takes a list of cards and writes them to a file in JSON format
	 * 
	 * @param q The list of cards to be written to the file.
	 */
	public static void writeJson(Deck d, String s) {	

		// Creating a new Gson object with the pretty printing and html escaping disabled.
		Gson gson=new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();

		// Converting the deck to a string and then writing it to a file.
		String json1 = gson.toJson(d);
		PrintWriter writer = null;	
		// Trying to create a new PrintWriter object. If it fails, it will print the stack trace.
		try
		{
			// Trying to create a new PrintWriter object. If it fails, it will print the stack trace.
			try {
				writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(s), "UTF-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}	
		// Writing the string json1 to the file and then closing the file.
		writer.println(json1);
		writer.close();
	}
}