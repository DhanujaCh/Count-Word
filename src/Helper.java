import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Helper {
    public String[] convertTokens(String input) {
        return input.split(" ");
    }


    public String[] filterTokens(String[] words) {
        List<String> filteredList = new ArrayList<>();
        for (String word : words) {
            String nextWord = word.replaceAll(Constants.SPECIALREGEX, "").trim().toLowerCase();
            if (!nextWord.isEmpty())
                filteredList.add(nextWord);
        }
        return filteredList.toArray(new String[filteredList.size()]);
    }

    public synchronized void addToMap(Map<String, Integer> valueMap, String word) {
        if (valueMap.containsKey(word)) {
            valueMap.put(word, valueMap.get(word) + 1);
        } else {
            valueMap.put(word, 1);
        }
    }

    public static long printExecutionTime(FindWordCount cw, String fileName) {
        long startTime = System.currentTimeMillis();
        cw.wordCount(fileName);
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }

    public static String getInputFile(String[] args) throws Exception {
        File fileName = new File(args.length != 1 ? "gpl-3.0.txt" : args[0]);
        if (!fileName.getName().endsWith(".txt")) {
            throw new Exception("Please try again with file extension as .txt");
        }
        return fileName.getName();
    }

    public static List<String> readFile(String fileName) {
        List<String> wordsArray = null;
        try {
            CustomFileIterator customerFileIterator = new CustomFileIterator(fileName);
            StringBuilder content = new StringBuilder();
            while (customerFileIterator.hasNext()) {
                content.append(customerFileIterator.next()).append(" ").append("\n");
            }
            wordsArray = Arrays.stream(content.toString().toLowerCase().split("\\s+")).collect(Collectors.toList());
        } catch (NoSuchFileException e) {
            System.out.println("File doesn't exists");
        } catch (Exception e) {
            System.out.println("CommonFiles:: Exception" + e);
        }
        return wordsArray;
    }
}
