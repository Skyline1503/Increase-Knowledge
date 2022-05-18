package json;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class Json {
	// It opens a file chooser window and allows the user to select a file. Then it writes the json file.
	public static void ExportJson()
	{
		// It opens a file chooser window and allows the user to select a file.
		FileChooser fileChooserOpen=new FileChooser();
		fileChooserOpen.getExtensionFilters()
		.add(new ExtensionFilter("Fichiers json", ".json"));

		// It opens a file chooser window and allows the user to select a file.
		File fileOpen=fileChooserOpen.showSaveDialog(null);

		if(fileOpen!=null) {
			// A try-catch block that is used to write the json file.
			try(Writer bw = new BufferedWriter(new FileWriter(fileOpen))){
				Write.writeJson(Link.getDeck(), fileOpen.toString());
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * It opens a file chooser window and allows the user to select a file. Then it deletes the file
	 * "questions.json" and renames the file that the user has chosen to "questions.json"
	 */
	public static void ImportJson()
	{
		// Opening a file chooser window and allowing the user to select a file.
		FileChooser fileChooserOpen= new FileChooser();
		fileChooserOpen.getExtensionFilters().add(new ExtensionFilter("Fichier json", "*.json"));
		File fileOpen=fileChooserOpen.showOpenDialog(null);

		File f = new File("questions.json");
		// Deleting the file "questions.json" and renaming the file that the user has chosen to
		// "questions.json".
		if(fileOpen != null) {
			f.delete();
			fileOpen.renameTo(new File("questions.json"));
		}
		// Calling the method jsonCall() from the class Link.
		Link.jsonCall();
	}
}