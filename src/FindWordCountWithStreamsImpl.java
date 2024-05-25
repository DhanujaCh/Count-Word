import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FindWordCountWithStreamsImpl implements FindWordCount {

    @Override
    public void wordCount() {
        try {

            System.out.println(Files.lines(Path.of("gpl-3.0.txt"))
                    .flatMap(l -> Arrays.stream(l.trim().split(" ")).parallel())
                    .map(word -> word.replaceAll(Constants.SPECIALREGEX, "").trim().toLowerCase())
                    .filter(word -> !word.isEmpty())
                    .collect(Collectors.groupingByConcurrent(Function.identity(), ConcurrentHashMap::new, Collectors.counting()))
                    .entrySet().stream().map(e -> "'" + e.getKey() + "'" + ":" + e.getValue())
                    .collect(Collectors.joining(",", "{", "}")));

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
