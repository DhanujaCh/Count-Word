import java.util.Map;
import java.util.Queue;

public class WordCounterThread implements Runnable {
    private Helper helper;
    private Queue<String> dataQueue;
    private Map<String, Integer> valueMap;


    public WordCounterThread(Helper helper, Map<String, Integer> valueMap, Queue<String> dataQueue) {
        this.helper = helper;
        this.dataQueue = dataQueue;
        this.valueMap = valueMap;
    }


    @Override
    public void run() {
        while (!dataQueue.isEmpty()) {
            String line = dataQueue.poll();
            if (line != null) {
                String[] words = helper.convertTokens(line);
                String[] legalWords = helper.filterTokens(words);
                for (String word : legalWords) {
                    helper.addToMap(valueMap, word);
                }
            }
        }
    }
}
