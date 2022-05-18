package modele;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.html.WebColors;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;


public class PDFDataExport extends DataExport{

	@Override
	public FileChooser FileChooserOpen() {
		FileChooser fileChooserOpen=new FileChooser();
		fileChooserOpen.getExtensionFilters()
		.add(new ExtensionFilter("Fichiers pdf", ".pdf"));
		return fileChooserOpen;
	}


	@Override
	public void writeData(List<Personne> data, File f) throws Exception {
		Document doc = new Document();
		Class c = doc.getClass();
		try
		{
			
			if(f!=null) {
				PdfWriter.getInstance(doc, new FileOutputStream(f));
				// It opens the document and adds a title to it.
				doc.open();
				doc.add(new Paragraph("\n\n\n"));
				doc.add(new Phrase("Increase Knowledge - Classement", FontFactory.getFont("Comic Sans MS", 30, Font.UNDERLINE)));
				doc.add(new Paragraph("\n\n\n"));
	
				// It creates a table with 6 columns and sets the width of each column.
				PdfPTable table = new PdfPTable(6);
				int[] columnWidths = new int[] {10, 20, 20, 10, 10, 60};
				table.setWidths(columnWidths);
				table.setHorizontalAlignment(Element.ALIGN_CENTER);
				PdfPCell cell;
	
				// It creates a cell with the text "Pos" and adds it to the table.
				cell = new PdfPCell(new Phrase("Pos", FontFactory.getFont("Comic Sans MS", 12)));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setBackgroundColor(WebColors.getRGBColor("005797"));
				table.addCell(cell);
	
				// It creates a cell with the text "Name" and adds it to the table.
				cell = new PdfPCell(new Phrase("Name", FontFactory.getFont("Comic Sans MS", 12)));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setBackgroundColor(WebColors.getRGBColor("005797"));
				table.addCell(cell);
	
				// It creates a cell with the text "Scoore" and adds it to the table.
				cell = new PdfPCell(new Phrase("Scoore", FontFactory.getFont("Comic Sans MS", 12)));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setBackgroundColor(WebColors.getRGBColor("005797"));
				table.addCell(cell);
	
				// It adds an image to the cell.
				cell = new PdfPCell();
				Image img = Image.getInstance(c.getResource("/img/boardgame/check.png").toURI().toString());
				cell.addElement(img);
				cell.setBackgroundColor(WebColors.getRGBColor("005797"));
				table.addCell(cell);
	
				// It adds an image to the cell.
				cell = new PdfPCell();
				img = Image.getInstance(c.getResource("/img/boardgame/nocheck.png").toURI().toString());
				cell.setBackgroundColor(WebColors.getRGBColor("005797"));
				cell.addElement(img);
				table.addCell(cell);
	
				// It creates a cell with the text "Category" and adds it to the table.
				cell = new PdfPCell(new Phrase("Category", FontFactory.getFont("Comic Sans MS", 12)));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setBackgroundColor(WebColors.getRGBColor("005797"));
				table.addCell(cell);
	
				// A for loop that iterates through the list of Personne objects and adds the data of each Personne
				// object to the table.
				for(int i = 0; i<data.size();i++)
				{
					cell = new PdfPCell(new Phrase(""+data.get(i).getPosition(), FontFactory.getFont("Comic Sans MS", 12)));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					table.addCell(cell);
	
					cell = new PdfPCell(new Phrase(data.get(i).getName(), FontFactory.getFont("Comic Sans MS", 12)));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					table.addCell(cell);
	
					cell = new PdfPCell(new Phrase(""+data.get(i).getTotalScore(), FontFactory.getFont("Comic Sans MS", 12)));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					table.addCell(cell);
	
					cell = new PdfPCell(new Phrase(""+data.get(i).getNbCorrect(), FontFactory.getFont("Comic Sans MS", 12)));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					table.addCell(cell);
	
					cell = new PdfPCell(new Phrase(""+data.get(i).getNbIncorrect(), FontFactory.getFont("Comic Sans MS", 12)));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					table.addCell(cell);
	
					cell = new PdfPCell(new Phrase(data.get(i).getLstCateg().toString(), FontFactory.getFont("Comic Sans MS", 12)));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					table.addCell(cell);
				}
				// It adds the table to the document and closes the document.
				doc.add(table);
				doc.close();
			}

		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	
}
