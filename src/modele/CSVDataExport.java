package modele;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.List;

import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class CSVDataExport extends DataExport{

	@Override
	public FileChooser FileChooserOpen() {
		FileChooser fileChooserOpen=new FileChooser();
		fileChooserOpen.getExtensionFilters()
		.add(new ExtensionFilter("Fichiers csv", ".csv"));
		return fileChooserOpen;
	}


	@Override
	public void writeData(List<Personne> data, File f) throws Exception {
		Writer writer = null;
		// Creating a file chooser to select the file to save the data to.
		try {
			// It creates a file chooser to select the file to save the data to.
			
			if(f!=null) {
				// It creates a file and a writer to write to the file.
				File file = new File(f.toString());
				writer = new BufferedWriter(new FileWriter(file));
	
				// It writes the header of the CSV file.
				String text = "POS;Name;Scoore;NbCorrect;NbIncorrect;Category\n";
				writer.write(text);
	
				// A for loop that goes through the list of Personne and writes the data to the file.
				for (Personne person : data) {
					String text2 = person.getPosition() + ";" + person.getName() + ";" + person.getTotalScore() + ";"
							+ person.getNbCorrect() + ";" + person.getNbIncorrect() + ";" + person.getLstCateg()+  "\n";
	
					writer.write(text2);
				}
				writer.flush();
				writer.close();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}

}
