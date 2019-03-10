package atm.db;

import java.io.*;

public class Util {

    public static PrintWriter openFileW(String fileName) throws IOException {
        return new PrintWriter(new File(Util.class.getResource("/" + fileName).getPath()));
    }

    public static InputStreamReader openFile(String fileName) {
        return new InputStreamReader(Util.class.getResourceAsStream("/" + fileName));
    }

    public static void loadCSV(String fileName, CSVRowConsumer consumer) throws RuntimeException {
        try {
            BufferedReader reader = new BufferedReader(Util.openFile(fileName));
            reader.readLine(); // Skip the column name row.
            String row;
            while ((row = reader.readLine()) != null) {
                consumer.consumeRow(row);
            }
            reader.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Failed to load CSV: " + fileName + ".");
        }
    }

    public interface CSVRowConsumer {
        void consumeRow(String row) throws IOException;
    }
}
