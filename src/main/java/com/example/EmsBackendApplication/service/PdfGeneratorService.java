package com.example.EmsBackendApplication.service;

import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;

@Service
public class PdfGeneratorService {

    public String generateSimplePdf(String customerName, double amount) {
        try {
            String filePath = "Invoice_" + customerName + ".pdf";
            File file = new File(filePath);
            FileOutputStream fos = new FileOutputStream(file);

            PdfWriter writer = new PdfWriter(fos);
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc);

            // Content
            document.add(new Paragraph("Invoice"));
            document.add(new Paragraph("Customer Name: " + customerName));
            document.add(new Paragraph("Amount: ₹" + amount));
            document.add(new Paragraph("Thank you for your business!"));

            document.close();
            return file.getAbsolutePath();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

