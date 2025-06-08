package CsvQuestion;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.Supplier;

public class StockCSVReader {

    static class StockProcessor implements Supplier<List<List<String>>> {
        private final File file;
        private final long start;
        private final long end;
        private final int amountToAdd;

        public StockProcessor(File file, long start, long end, int amountToAdd) {
            this.file = file;
            this.start = start;
            this.end = end;
            this.amountToAdd = amountToAdd;
        }

        @Override
        public List<List<String>> get() {
            List<List<String>> result = new ArrayList<>();

            try (RandomAccessFile raf = new RandomAccessFile(file, "r")) {
                raf.seek(start);

                if (start != 0) {
                    raf.readLine(); // Skip partial line
                }

                String line;
                while (raf.getFilePointer() < end && (line = raf.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length < 2) continue;

                    String code = parts[0];
                    double price = Double.parseDouble(parts[1]);
                    price += amountToAdd;

                    result.add(Arrays.asList(code, String.format("%.2f", price)));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return result;
        }
    }

    public static void main(String[] args) throws Exception {
        File file = new File("C:\\Users\\Anshu\\Downloads\\stocks.csv");

        // Read header and calculate its byte offset
        String header;
        long headerOffset;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            header = br.readLine();
            headerOffset = header.getBytes().length + System.lineSeparator().getBytes().length;
        }

        long totalSize = file.length();
        long chunkSize = (totalSize - headerOffset) / 4;

        int[] addAmounts = {10, 20, 30, 40};
        List<CompletableFuture<List<List<String>>>> futures = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            long start = headerOffset + i * chunkSize;
            long end = (i == 3) ? totalSize : headerOffset + (i + 1) * chunkSize;

            StockProcessor processor = new StockProcessor(file, start, end, addAmounts[i]);
            CompletableFuture<List<List<String>>> future = CompletableFuture.supplyAsync(processor);
            futures.add(future);
        }

        // Wait for all to complete and combine results
        CompletableFuture<Void> allDone = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
        allDone.join(); // Waits for all to complete

        List<List<String>> allStocks = new ArrayList<>();
        for (CompletableFuture<List<List<String>>> future : futures) {
            allStocks.addAll(future.get());
        }

        // Print results
        System.out.println("Total processed stocks: " + allStocks.size());
        allStocks.stream().limit(100).forEach(System.out::println);
    }
}
