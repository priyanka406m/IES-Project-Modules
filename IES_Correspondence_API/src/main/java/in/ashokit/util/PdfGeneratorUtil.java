package in.ashokit.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Component;

import in.ashokit.entity.IESEligibilityCheckEntity;

@Component
public class PdfGeneratorUtil {
	public byte[] getCitizenData(IESEligibilityCheckEntity entity, File f) {
		try {
			// Create a new PDF document
			PDDocument document = new PDDocument();
			PDPage page = new PDPage();
			document.addPage(page);
			document.save(f);
			// Create a new content stream for the page
			PDPageContentStream contentStream = new PDPageContentStream(document, page);

			// Set font and font size
			contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);

			float startX = 20;
			float startY = 700;

			// Write text
			contentStream.beginText();
			contentStream.newLineAtOffset(startX, startY);
			contentStream.showText("Dear Customer,");
			contentStream.newLineAtOffset(0, -20); // Move to the next line

			contentStream.showText("Here is your plan details.");
			contentStream.newLineAtOffset(0, -20); // Move to the next line

			contentStream.showText("Case Number: " + entity.getCaseNum());
			contentStream.newLineAtOffset(0, -20); // Move to the next line

			contentStream.showText("Plan Name: " + entity.getPlanName());
			contentStream.newLineAtOffset(0, -20); // Move to the next line

			contentStream.showText("Plan Status: " + entity.getPlanStatus());
			contentStream.newLineAtOffset(0, -20); // Move to the next line

			contentStream.showText("Created Date:" + entity.getCreatedDate());
			contentStream.newLineAtOffset(0, -20); // Move to the next line

			contentStream.showText("Benifit Amount: " + entity.getBenifitAmt());
			contentStream.newLineAtOffset(0, -20); // Move to the next line

			contentStream.showText("Denial Reason: " + entity.getDenialReason());
			contentStream.newLineAtOffset(0, -20); // Move to the next line

			contentStream.endText();

			// Close the content stream
			contentStream.close();

			// Save the document to a file
			document.save("correspondence.pdf");

			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			document.save(outputStream);

			// Close the document
			document.close();

			System.out.println("PDF created successfully.");
			return outputStream.toByteArray();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
