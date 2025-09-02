package AppLogic;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public final class JSONReader {

    static String resourcePath = "/AppData/AppData.JSON";

    public static String[] getProduktNames(){
        return JSONReader("names");
    }

    public static String[] getProduktSizes(String wantedProduktData){
        return JSONReader("sizes");
    }

    // change JSON reader to own
    public static String[] JSONReader(String wantedData) {
        try (InputStream is = JSONReader.class.getResourceAsStream(resourcePath)) {
            if (is == null) {
                throw new IllegalStateException("Resource not found: " + resourcePath);
            }

            // Read entire file into a string
            byte[] bytes = is.readAllBytes();
            String json = new String(bytes, StandardCharsets.UTF_8);

            List<String> result = new ArrayList<>();

            if (wantedData.equals("names")) {
                // Extract all "name": "..."
                int idx = 0;
                while ((idx = json.indexOf("\"name\"", idx)) != -1) {
                    int start = json.indexOf("\"", idx + 6) + 1;
                    int end = json.indexOf("\"", start);
                    result.add(json.substring(start, end));
                    idx = end;
                }

            } else if (wantedData.equals("sizes")) {
                // change to extract only sizes of given name
                int idx = 0;
                while ((idx = json.indexOf("\"sizes\"", idx)) != -1) {
                    int blockStart = json.indexOf("{", idx);
                    int blockEnd = json.indexOf("}", blockStart);

                    String block = json.substring(blockStart + 1, blockEnd);

                    // Split by commas and get keys
                    for (String pair : block.split(",")) {
                        String key = pair.split(":")[0].trim();
                        key = key.replace("\"", ""); // remove quotes
                        result.add(key);
                    }

                    idx = blockEnd;
                }
            }

            return result.toArray(new String[0]);

        } catch (Exception e) {
            throw new RuntimeException("Failed to read resource: " + resourcePath, e);
        }
    }
}