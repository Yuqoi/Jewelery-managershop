import com.codingerror.model.AddressDetails;
import com.codingerror.model.HeaderDetails;
import com.codingerror.model.Product;
import com.codingerror.model.ProductTableHeader;
import com.codingerror.service.CodingErrorPdfInvoiceCreator;
import models.Gender;
import models.MechanismType;
import models.Watch;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        Watch rolex = new Watch(1, "rolex sraka", "rolex", Gender.FEMALE, "abc123d", MechanismType.Automatic, 189.5);
        Watch citizen = new Watch(2, "citizen", "Citizen", Gender.MALE, "cde5312", MechanismType.Quartz, 209.70);

        String generator = InvoiceNumberGenerator.generateInvoiceNumber();
        List<Watch> watchList = new ArrayList<>();
        watchList.add(rolex);
        watchList.add(citizen);
        InvoiceGenerator ig = new InvoiceGenerator(watchList, generator, "Aleksander", "Kopernika 8/46", "Poland");
    }
}
