package org.yuqoi.managerapp.models.Invoice;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class InvoiceSerialGenerator {
    // create a serial number for invoice
    public static String createSerial(){
        String prefix = "INV";

        // get the current date
        LocalDate ld = LocalDate.now();
        String datePart = ld.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        datePart = datePart.replace("-", "/");
        String uniqueId = UUID.randomUUID().toString().replace("-", "").substring(0, 8);

        return String.format("%s:%s-%s", prefix, datePart, uniqueId);
    }
}
