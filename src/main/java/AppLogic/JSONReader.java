package AppLogic;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public final class JSONReader {

    static String resourcePath = "/AppData/AppData.JSON";

    public static String[] getProduktNames(){
        return JSONReader("names",null);
    }

    // change JSON reader to own
    public static String[] JSONReader(String wantedData,String wantedProduktName) {
        //System.out.println("Json reader entered");
        //new Exception("Call trace").printStackTrace(System.out);
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

            }  else if (wantedData.equals("sizes")) {
                int idx = 0;
                String target = wantedProduktName == null ? null : wantedProduktName.trim();
                while ((idx = json.indexOf("\"name\"", idx)) != -1) {
                    int start = json.indexOf("\"", idx + 6) + 1;
                    int end = json.indexOf("\"", start);
                    if (start <= 0 || end <= start) break;

                    String name = json.substring(start, end);

                    if (target != null && target.equals(name)) {
                        int sizesIdx = json.indexOf("\"sizes\"", end);
                        if (sizesIdx == -1) break;

                        int blockStart = json.indexOf("{", sizesIdx);
                        if (blockStart == -1) break;

                        int blockEnd = json.indexOf("}", blockStart);
                        if (blockEnd == -1 || blockEnd <= blockStart) break;

                        String block = json.substring(blockStart + 1, blockEnd);

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
                        idx = end;
                    }
                }
            }
            return result.toArray(new String[0]);

        } catch (Exception e) {
            throw new RuntimeException("Failed to read resource: " + resourcePath, e);
        }
    }
}