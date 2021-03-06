package tech.myic.tool;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.JPEGFactory;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.rendering.PDFRenderer;

public class PdfFileCompressor
        implements Compressible
{
    private float dpi;

    public void setDpi(float dpi)
    {
        this.dpi = dpi;
    }

    @Override
    public void compress(File in, File out)
            throws IOException
    {
        try (PDDocument pdDocument = new PDDocument()) {
            int numberOfPages;
            try (PDDocument doc = PDDocument.load(new FileInputStream(in), "", MemoryUsageSetting.setupTempFileOnly())) {
                numberOfPages = doc.getNumberOfPages();

                PDPage page;
                for (int i = 0; i < numberOfPages; i++){
                    System.out.println("Dealing with page: " + (i + 1) + "/" + numberOfPages);
                    page = new PDPage(PDRectangle.A4);
                    BufferedImage bim = new PDFRenderer(doc).renderImageWithDPI(i, this.dpi);
                    PDImageXObject pdImage = JPEGFactory.createFromImage(pdDocument, bim);
                    try (PDPageContentStream contentStream = new PDPageContentStream(pdDocument, page)) {
                        contentStream.drawImage(pdImage, 0, 0, PDRectangle.A4.getWidth(), PDRectangle.A4.getHeight());
                    }
                    pdDocument.addPage(page);
                }
                pdDocument.save(out);
            }
        }
    }
}
