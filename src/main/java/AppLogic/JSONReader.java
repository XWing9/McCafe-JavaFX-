package AppLogic;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public final class JSONReader {

    private JSONReader() { /* utility class */ }

    // Reads the default menu JSON from resources: src/main/resources/AppData/AppData.JSON
    public static String readMenuJson() {
        return readResourceAsString("/AppData/AppData.JSON");
    }

    // Generic helper if you want to reuse it for other JSON files later
    public static String readResourceAsString(String resourcePath) {
        try (InputStream is = JSONReader.class.getResourceAsStream(resourcePath)) {
            if (is == null) {
                throw new IllegalStateException("Resource not found: " + resourcePath);
            }
            return new String(is.readAllBytes(), StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException("Failed to read resource: " + resourcePath, e);
        }
    }
}