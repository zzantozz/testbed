package rds.testbed;

import java.io.*;
import java.util.Random;

public class ReadingDoublesFromFileEfficiency {
    private static Random random = new Random();

    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
        String filePath = createInputFile();
        BufferedReader reader = new BufferedReader(new FileReader(filePath), 1);
        String line;
        while ((line = reader.readLine()) != null) {
            String[] details = line.split(",");
            double score = (Double.parseDouble(details[0]) * Double.parseDouble(details[1])) + Double.parseDouble(details[2]) + Double.parseDouble(details[3]) + Double.parseDouble(details[6]);
            System.out.println(score);
        }
        reader.close();
        long elapsed = System.currentTimeMillis() - start;
        System.out.println("Took " + elapsed + " ms");
    }

    private static String createInputFile() throws IOException {
        File file = File.createTempFile("testbed", null);
        PrintWriter writer = new PrintWriter(new FileWriter(file));
        for (int i = 0; i < 10000; i++) {
            writer.println(randomLine());
        }
        writer.close();
        return file.getAbsolutePath();
    }

    private static String randomLine() {
        return String.format("%f,%f,%f,%f,%s,%s,%f",
                score(), score(), score(), score(), name(), name(), score());
    }

    private static String name() {
        String name = "";
        for (int i = 0; i < 10; i++) {
            name += (char) (random.nextInt(26) + 97);
        }
        return name;
    }

    private static double score() {
        return random.nextDouble() * 100;
    }
}
