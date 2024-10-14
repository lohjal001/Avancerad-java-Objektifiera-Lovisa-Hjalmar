import com.opencsv.CSVReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.io.IOException;

public class CSVHandler {

    public static ArrayList<String[]> readCSV(File file) throws IOException {
        ArrayList<String[]> csvData = new ArrayList<>();

        try (CSVReader csvReader = new CSVReader(new FileReader(file))) {
            for (String[] row : csvReader) {
                csvData.add(row);
            }
        }

        return csvData;
    }
}