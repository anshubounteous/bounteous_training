import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        // file name to be used in the program 
        String inputFilePath = "input.txt";
        String outputFilePath = "vowel_count.txt";

        Map<Character, Integer> vowelCounts = new HashMap<>();
        vowelCounts.put('a', 0);
        vowelCounts.put('e', 0);
        vowelCounts.put('i', 0);
        vowelCounts.put('o', 0);
        vowelCounts.put('u', 0);

        // try and catch block so that it will first check whether the input file is there or not
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {
            int character;
            while ((character = reader.read()) != -1) {
                char ch = (char) character;
                ch = Character.toLowerCase(ch);
                if (vowelCounts.containsKey(ch)) {
                    vowelCounts.put(ch, vowelCounts.get(ch) + 1);
                }
            }
            
        }
        // catch block 
        catch (IOException e) {
            System.err.println("Error reading the input file: " + e.getMessage());
            return;
        }


        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {
            for (Map.Entry<Character, Integer> entry : vowelCounts.entrySet()) {
                writer.write(entry.getKey() + ": " + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to the output file: " + e.getMessage());
        }
    }
}