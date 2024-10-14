import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class JSONHandler {

    public static ArrayList<String[]> readJSON(File file) {
        ArrayList<String[]> jsonData = new ArrayList<>();
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(file)) {
            JSONArray array = (JSONArray) jsonParser.parse(reader);

            String[] columns = new String[]{"A", "B", "C", "D", "E", "F", "G", "H"};
            jsonData.add(columns);

            for (int i = 1; i < array.size(); i++) {
                JSONObject rowObject = (JSONObject) array.get(i);
                String[] row = new String[columns.length];

                for (int j = 0; j < columns.length; j++) {
                    row[j] = getStringOrNull(rowObject, columns[j]);
                }
                jsonData.add(row);
            }

        } catch (Exception e) {
            throw new RuntimeException("Error reading JSON file: " + e.getMessage());
        }

        return jsonData;
    }

    private static String getStringOrNull(JSONObject rowObject, String key) {
        return rowObject.containsKey(key) ? rowObject.get(key).toString() : "";
    }
}
