package org.yuqoi.managerapp.utils.pdfgenerator;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.color.DeviceRgb;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.border.DashedBorder;
import com.itextpdf.layout.border.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import org.yuqoi.managerapp.models.Watch.Watch;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class GeneratePdf {
    private Document document;


    private final float threeCol = 190f;
    private final float twoCol = 285f;
    private final float twocol150 = twoCol + 150f;
    private final float[] twocolumnWidth = {twocol150, twoCol};
    private final float[] threeColumnWidth = {threeCol, threeCol, threeCol};
    private final float[] fullwidth = {threeCol*3};
    private final float[] oneColumnWidth = {twocol150};
    private final Paragraph spaceBetween = new Paragraph("\n");


    private String customerName;
    private String customerAddress;
    private String customerCountry;
    private String invoiceNumber;

    public GeneratePdf(List<Watch> watchList, String invoiceNumber, String customersName, String customersAddress, String customersCountry) throws FileNotFoundException, MalformedURLException {

        this.customerName = customersName;
        this.customerAddress = customersAddress;
        this.customerCountry = customersCountry;
        this.invoiceNumber = invoiceNumber;

        String fileName = customersName + invoiceNumber;
        String pdfLocation = "src/main/resources/org/yuqoi/managerapp/invoices/"+fileName+".pdf";
        String imgPath = "src/main/resources/org/yuqoi/managerapp/images/logo.png";

        createDocument(pdfLocation, imgPath);
        createHeader();
        createCenter(watchList);
        createFooter();

        document.close();
    }

    public void createDocument(String path, String imgPath) throws FileNotFoundException, MalformedURLException {
        PdfWriter pdfWriter = new PdfWriter(path);
        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        pdfDocument.setDefaultPageSize(PageSize.A4);

        document = new Document(pdfDocument);

        ImageData imgData = ImageDataFactory.create(imgPath);
        Image image = new Image(imgData);

        float x =pdfDocument.getDefaultPageSize().getWidth()/4;
        float y =pdfDocument.getDefaultPageSize().getHeight()/2;
        image.setFixedPosition(x,y);
        image.setOpacity(0.1f);
        document.add(image);
    }


    public void createHeader(){
        Table table = new Table(twocolumnWidth);

        table.setBackgroundColor(new DeviceRgb(52, 152, 219));

        table.addCell(new Cell().add("Watch store")
                .setBorder(Border.NO_BORDER)
                .setBold()
                .setFontSize(30f)
        );

        Table nestedTable = new Table(new float[]{twoCol/2, twoCol/2});

        LocalDate timeNow = LocalDate.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formatted = timeNow.format(format);
        formatted = formatted.replace("-", "/");

        nestedTable.addCell(getHeaderTextCell("No:"));
        nestedTable.addCell(getHeaderTextCellValue(invoiceNumber));
        nestedTable.addCell(getHeaderTextCell("Date:"));
        nestedTable.addCell(getHeaderTextCellValue(formatted));

        table.addCell(new Cell().add(nestedTable).setBorder(Border.NO_BORDER));

        Border gb = new SolidBorder(new DeviceRgb(50, 50 ,50), 1f);
        Table divider = new Table(fullwidth);
        divider.setBorder(gb);


        document.add(table);
        document.add(spaceBetween);
        document.add(divider);
        document.add(spaceBetween);
    }
    public void createCenter(List<Watch> watchList){
        // divider
        Border gb = new SolidBorder(new DeviceRgb(50, 50 ,50), 1f);
        Table divider = new Table(fullwidth);
        divider.setBorder(gb);



        Table twoColTable = new Table(twocolumnWidth);
        twoColTable.addCell(getBillingandShippingCell("Billing Info"));
        twoColTable.addCell(getBillingandShippingCell("Shipping info"));
        document.add(twoColTable.setMarginBottom(12f));

        Table twoColTable2 = new Table(twocolumnWidth);
        twoColTable2.addCell(getCell10fLeft("Company :", true));
        twoColTable2.addCell(getCell10fLeft("name :", true));
        twoColTable2.addCell(getCell10fLeft("Watch store", false));
        twoColTable2.addCell(getCell10fLeft(customerName, false));
        document.add(twoColTable2.setMarginBottom(12f));

        Table twoColTable3 = new Table(twocolumnWidth);
        twoColTable3.addCell(getCell10fLeft("Name :", true));
        twoColTable3.addCell(getCell10fLeft("Address :", true));
        twoColTable3.addCell(getCell10fLeft("Yuqoi", false));
        twoColTable3.addCell(getCell10fLeft(customerAddress + "\n" +customerCountry, false));
        document.add(twoColTable3.setMarginBottom(25f));


        Table oneColumnTable1 = new Table(oneColumnWidth);

        oneColumnTable1.addCell(getCell10fLeft("Address :", true));
        oneColumnTable1.addCell(getCell10fLeft("Tarnobrzeg", false));
        oneColumnTable1.addCell(getCell10fLeft("Email", true));
        oneColumnTable1.addCell(getCell10fLeft("watchstore@gmail.com", false));
        document.add(oneColumnTable1.setMarginBottom(10f));

        Table tableDivider2 = new Table(fullwidth);
        Border dgb = new DashedBorder(new DeviceRgb(50,50,50), 0.5f);


        Border oneLineBorder = new SolidBorder(new DeviceRgb(50, 50 ,50), 1f);
        Table oneLineDivider = new Table(fullwidth);
        oneLineDivider.setBorder(oneLineBorder);
        document.add(oneLineDivider);



        Paragraph productPara = new Paragraph("Watches");
        document.add(productPara.setBold());

        Table threeColTable1 = new Table(threeColumnWidth);
        threeColTable1.setBackgroundColor(new DeviceRgb(52, 152, 219), 0.7f);

        threeColTable1.addCell(new Cell().add("Watch name").setBold().setFontColor(new DeviceRgb(255,255,255)).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT));
        threeColTable1.addCell(new Cell().add("MPN").setBold().setFontColor(new DeviceRgb(255,255,255)).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER));
        threeColTable1.addCell(new Cell().add("Price").setBold().setFontColor(new DeviceRgb(255,255,255)).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
        document.add(threeColTable1);


        // create products list
        float total = 0;
        Table threeColTable2 = new Table(threeColumnWidth);
        for (Watch object : watchList){
            total += (float) object.getPrice();
            threeColTable2.addCell(new Cell().add(object.getWatchName()).setMarginLeft(10f).setBorder(Border.NO_BORDER));
            threeColTable2.addCell(new Cell().add(object.getMpn()).setBorder(Border.NO_BORDER)).setTextAlignment(TextAlignment.CENTER);
            threeColTable2.addCell(new Cell().add(String.valueOf(object.getPrice())).setTextAlignment(TextAlignment.RIGHT).setBorder(Border.NO_BORDER)).setMarginRight(15f);

        }
        document.add(threeColTable2.setMarginBottom(20f));

        float[] oneTwo = {threeCol+125f, threeCol*2};
        Table threeColTable4 = new Table(oneTwo);
        threeColTable4.addCell(new Cell().add("").setBorder(Border.NO_BORDER));
        threeColTable4.addCell(new Cell().add(tableDivider2.setBorder(dgb)).setBorder(Border.NO_BORDER));
        document.add(threeColTable4);

        Table threeColTable3 = new Table(threeColumnWidth);
        threeColTable3.addCell(new Cell().add("").setBorder(Border.NO_BORDER).setMarginLeft(10f));
        threeColTable3.addCell(new Cell().add("Total:").setTextAlignment(TextAlignment.LEFT).setBorder(Border.NO_BORDER)).setMarginLeft(100f);
        threeColTable3.addCell(new Cell().add(String.valueOf(total)).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));

        document.add(threeColTable3);
        document.add(tableDivider2);
        document.add(spaceBetween);
        document.add(divider.setBorder(new SolidBorder(new DeviceRgb(50,50,50), 1)).setMarginBottom(15f));
    }
    public void createFooter(){
        Table tb = new Table(fullwidth);
        tb.addCell(new Cell().add("TERMS AND CONDITIONS\n").setBorder(Border.NO_BORDER).setBold());

        List<String> tacList = new ArrayList<>();
        tacList.add("1. We suggest to not share the original copy of invoice");
        tacList.add("2. Given invoice is the only valid way of proving that items bought items are yours");
        for (String word : tacList){
            tb.addCell(new Cell().add(word).setBorder(Border.NO_BORDER));
        }
        document.add(tb);
    }

    private static Cell getHeaderTextCell(String textValue){
        return new Cell().add(textValue).setBold().setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER);
    }
    private static Cell getHeaderTextCellValue(String textValue){
        return new Cell().add(textValue).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT);
    }
    private static Cell getBillingandShippingCell(String textValue){
        return new Cell().add(textValue).setFontSize(12f).setBold().setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT);
    }
    private static Cell getCell10fLeft(String textValue, boolean isBold){
        Cell myCell = new Cell().add(textValue).setFontSize(10f).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT);
        return isBold ? myCell.setBold() : myCell;
    }
}
