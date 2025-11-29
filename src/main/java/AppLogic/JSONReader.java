package AppLogic;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public final class JSONReader {

    static String resourcePath = "/AppData/AppData.JSON";

    static String JSON_Content = null;

    static List<String> result = new ArrayList<>();

    static int JSON_Index = 0;
    static int start = 0;
    static int end = 0;

    public static void loadJSON(){
        if (JSON_Content != null) {
            return; // Already loaded
        }
        try (InputStream is = JSONReader.class.getResourceAsStream(resourcePath)){
            if (is == null) {
                throw new IllegalStateException("Resource not found: " + resourcePath);
            }

            // Read entire file into a string
            byte[] bytes = is.readAllBytes();
            JSON_Content = new String(bytes, StandardCharsets.UTF_8);

        } catch (Exception e) {
            throw new RuntimeException("Failed to read resource: " + resourcePath, e);
        }
    }

    public static float PriceJSONReader(String produktName, String sizeChoosen) {
        float tempnum = 0.0F;
        String priceString = "";
        String sizesText;
        start = 0;
        end = 0;
        int sizesIndex;
        int blockStart;
        int blockEnd;
        int sizesStart;
        int colonIndex;
        int commaIndex;

        result.clear();
        JSON_Index = 0;

        while ((JSON_Index = JSON_Content.indexOf("\"name\"", JSON_Index)) != -1) {
            start = JSON_Content.indexOf("\"", JSON_Index + 6) + 1;
            end = JSON_Content.indexOf("\"", start);
            result.add(JSON_Content.substring(start, end));
            JSON_Index = end;

            String name = JSON_Content.substring(start, end);
            if (name.equals(produktName)){
                sizesIndex = JSON_Content.indexOf("\"sizes\"", end);
                blockStart = JSON_Content.indexOf("{", sizesIndex);
                blockEnd = JSON_Content.indexOf("}", blockStart);

                sizesText = JSON_Content.substring(blockStart + 1, blockEnd);
                sizesStart = sizesText.indexOf( "\"" + sizeChoosen + "\"");

                if (sizesStart != -1) {
                    // 2. Jump to the colon ':' after the found name
                    colonIndex = sizesText.indexOf(":", sizesStart);
                    // 3. Check if there is a comma after the value
                    commaIndex = sizesText.indexOf(",", colonIndex);

                    if (commaIndex == -1) {
                        // No comma found -> Take everything from the colon to the end
                        priceString = sizesText.substring(colonIndex + 1);
                    } else {
                        // Comma found -> Take everything between colon and comma
                        priceString = sizesText.substring(colonIndex + 1, commaIndex);
                    }
                }
            }
        }
        tempnum = Float.parseFloat(priceString);
        return tempnum;
    }

    public static String[] NamesJSONReader(){
        loadJSON();
        result.clear();
        JSON_Index = 0;
        start = 0;
        end = 0;
        while ((JSON_Index = JSON_Content.indexOf("\"name\"", JSON_Index)) != -1) {
            start = JSON_Content.indexOf("\"", JSON_Index + 6) + 1;
            end = JSON_Content.indexOf("\"", start);
            result.add(JSON_Content.substring(start, end));
            JSON_Index = end;
        }
        return result.toArray(new String[0]);
    }

    public static String[] sizesJSONReader(String produktName) {
        result.clear();
        JSON_Index = 0;
        String target = produktName == null ? null : produktName.trim();
        while ((JSON_Index = JSON_Content.indexOf("\"name\"", JSON_Index)) != -1) {
            int start = JSON_Content.indexOf("\"", JSON_Index + 6) + 1;
            int end = JSON_Content.indexOf("\"", start);
            if (start <= 0 || end <= start) break;

            String name = JSON_Content.substring(start, end);

            if (target != null && target.equals(name)) {
                int sizesIdx = JSON_Content.indexOf("\"sizes\"", end);
                if (sizesIdx == -1) break;

                int blockStart = JSON_Content.indexOf("{", sizesIdx);
                if (blockStart == -1) break;

                int blockEnd = JSON_Content.indexOf("}", blockStart);
                if (blockEnd == -1 || blockEnd <= blockStart) break;

                String block = JSON_Content.substring(blockStart + 1, blockEnd);

                // Split the sizes object into pairs and collect keys (left side of :)
                for (String pair : block.split(",")) {
                    String[] parts = pair.split(":", 2);
                    if (parts.length == 0) continue;
                    String key = parts[0].trim().replace("\"", "");
                    if (!key.isEmpty()) {
                        result.add(key);
                    }
                }
                break; // done after matched product
            } else {
                // IMPORTANT: advance the scan when the name doesn't match to avoid infinite loop
                JSON_Index = end;
            }
        }
        return result.toArray(new String[0]);
    }
}