import java.io.File;

public class WordCountMain {
    public static void main(String[] args) throws Exception {


        //Words Count based on Java Streams
        FindWordCount wordCount = new FindWordCountWithStreamsImpl();
        long totalTimeStream = Helper.printExecutionTime(wordCount);
        System.out.println("Stream processing::" + totalTimeStream);

        //Words Count based on multiThreading
        FindWordCount multiThread = new FindWordCountWithThreadsimpl();
        long totalTimeMulti = Helper.printExecutionTime(multiThread);
        System.out.println("Multiple Thread Processing::" + totalTimeMulti);


        //Words Count based on multiThreading with ExecutorService
        FindWordCount multiThreadWithExectorService = new FindWordCountWithExecutorServiceImpl();
        long totalTimeExe = Helper.printExecutionTime(multiThreadWithExectorService);
        System.out.println("Multiple Thread Processing with ExecutorService::" + totalTimeExe);
    }

}