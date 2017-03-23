package logic;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import javafx.scene.canvas.Canvas;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import presentation.FrontPage;
import presentation.FrontPageMediator;

public class PdfExporter {
	FrontPageMediator fpm;
	Document document;

	public PdfExporter() {
		fpm = FrontPage.getFrontPageMediator();
	}

	public void exportToPdf() throws DocumentException, MalformedURLException, IOException {
		String placement = "C:\\PTE-Projekt pdf document";
		List<TextField> TFList = new ListMaker().getTextFields();
		Canvas triangle = fpm.getTriangle().getCanvas();
		TextArea mellemRegninger = fpm.getMellemRegninger();

		try {

			document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream("Udregning.pdf"));

			document.open();

			addTextFields();
			addMellemRegninger(mellemRegninger);
			addTriangle();
			document.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//		System.out.println("i did it");
	}

	public void addTextFields() {
		try {
			document.add(new Paragraph("Fdim 	= " + fpm.getFdimTextField().getText() + "\n"));
			document.add(new Paragraph("Ft 		= " + fpm.getFtTextField().getText() + "\n"));
			document.add(new Paragraph("Fn 		= " + fpm.getFnTextField().getText() + "\n"));
			document.add(new Paragraph("Mb 		= " + fpm.getMBTextField().getText() + "\n"));
			document.add(new Paragraph("Tau		= " + fpm.getTauTextField().getText() + "\n"));
			document.add(new Paragraph("SigmaN 	= " + fpm.getSigmaNTextField().getText() + "\n"));
			document.add(new Paragraph("SigmaB	= " + fpm.getSigmaBTextField().getText() + "\n"));
			document.add(new Paragraph("SigmaRef= " + fpm.getSigmaRefTextField().getText() + "\n"));
			document.add(new Paragraph("SF		= " + fpm.getSikkerhedsFaktorTextField().getText() + "\n"));
		} catch (DocumentException d) {
			d.printStackTrace();
		}
	}

	public void addMellemRegninger(TextArea mellemRegninger) {

		try {
			document.add(new Paragraph("\n\n"));
			document.add(new Paragraph(mellemRegninger.getText()));

		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}

	public void addTriangle() {

		try {
			new ImageMaker().canvasToImage(fpm.getTriangle().getCanvas());
			document.add(Image.getInstance("test.png"));
		} catch (DocumentException | IOException e) {
			e.printStackTrace();
		}

	}

}
