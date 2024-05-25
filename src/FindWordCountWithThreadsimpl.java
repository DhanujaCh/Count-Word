import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import static java.lang.Math.ceil;

public class FindWordCountWithThreadsimpl implements FindWordCount {

    @Override
    public void wordCount(String fileName) {

        List<String> wordsArray = Helper.readFile(fileName);
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
        ConcurrentHashMap<String, Integer> outputMap = threadCreation(Constants.THREADSCOUNT, wordsArray, map);
        System.out.println(outputMap.entrySet().stream().map(e -> "'" + e.getKey() + "'" + ":" + e.getValue()).
                collect(Collectors.joining(",", "{", "}")));
    }

    //Split the no.of lines to share to multiple threads to put/store the words and it's count in map

    public ConcurrentHashMap<String, Integer> threadCreation(int N, List<String> fileReads, ConcurrentHashMap<String, Integer> map) {
        int start = 0, size = fileReads.size(), n = (int) ceil(size / N), remaining = size % N;

        for (int x = 0; x < N; x++) {
            ArrayList<String> splitReads = new ArrayList<String>();
            int end = start + n + remaining;
            for (int j = start; j < end; j++) {
                splitReads.add(fileReads.get(j));
            }
            start = end;
            remaining = 0;
            Thread t = getThread(map, splitReads);
            try {
                t.join();
            } catch (InterruptedException e) {
                System.out.println("Multiple threads interrupted:" + e);
            }
        }
        return map;

    }

    private static Thread getThread(ConcurrentHashMap<String, Integer> map, ArrayList<String> splitReads) {
        Thread thread = new Thread(new Runnable() {
            public synchronized void run() {
                Iterator a = splitReads.iterator();
                while (a.hasNext()) {
                    String word = (String) a.next();
                    String nextWord = word.replaceAll(Constants.SPECIALREGEX, "").trim().toLowerCase();
                    if (!nextWord.isEmpty()) {
                        if (map.containsKey(nextWord)) {
                            map.put(nextWord, map.get(nextWord) + 1);
                        } else {
                            map.put(nextWord, 1);
                        }
                    }
                }

            }
        });
        thread.start();
        return thread;
    }

}
