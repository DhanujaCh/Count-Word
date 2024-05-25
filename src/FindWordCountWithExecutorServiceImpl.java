import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class FindWordCountWithExecutorServiceImpl implements FindWordCount {
    Helper helper = new Helper();

    @Override
    public void wordCount() {
        ConcurrentHashMap<String, Integer> valueMap = new ConcurrentHashMap<>();
        final Queue<String> dataQueue = new ConcurrentLinkedQueue<>();
        new Thread(() -> {
            try (CustomFileIterator customerFileIterator = new CustomFileIterator("gpl-3.0.txt")) {
                while (customerFileIterator.hasNext()) {
                    dataQueue.add(customerFileIterator.next());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
        try {
            ExecutorService executorService = Executors.newFixedThreadPool(Constants.THREADSCOUNT);
            for (int i = 0; i < Constants.THREADSCOUNT; i++) {
                executorService.execute(new WordCounterThread(helper, valueMap, dataQueue));
            }
            executorService.shutdown();
            executorService.awaitTermination(1, TimeUnit.MINUTES);
            System.out.println(valueMap.entrySet().stream().map(e -> "'" + e.getKey() + "'" + ":" + e.getValue()).
                    collect(Collectors.joining(",", "{", "}")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

