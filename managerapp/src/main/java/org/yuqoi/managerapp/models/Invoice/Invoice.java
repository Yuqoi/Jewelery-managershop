package org.yuqoi.managerapp.models.Invoice;


import org.yuqoi.managerapp.models.Watch.Watch;
import org.yuqoi.managerapp.utils.pdfgenerator.GeneratePdf;
import org.yuqoi.managerapp.utils.pdfgenerator.PdfSerialGenerator;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Invoice {


    public Invoice(List<Watch> selectedWatches,String customerName, String customerAddress, String customerCountry) throws MalformedURLException, FileNotFoundException {
        GeneratePdf gpdf = new GeneratePdf(selectedWatches, PdfSerialGenerator.generateInvoiceNumber(), customerName, customerAddress, customerCountry);
    }
}
