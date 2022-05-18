package modele;

import java.io.File;
import java.util.List;

import javafx.stage.FileChooser;

public abstract class DataExport {
	
	
	public void extractData(List<Personne> l) throws Exception
	{
		FileChooserOpen();
		openFile(FileChooserOpen());
		writeData(l, openFile(FileChooserOpen()));
	}
	
	public abstract FileChooser FileChooserOpen();
	
	public File openFile(FileChooser p) {
		File fileOpen=p.showSaveDialog(null);
		return fileOpen;
	}
	
	public abstract void writeData(List<Personne> l, File f) throws Exception;
	
}
