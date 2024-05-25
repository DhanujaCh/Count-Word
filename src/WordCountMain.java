import java.io.File;

public class WordCountMain {
    public static void main(String[] args) throws Exception {

        String fileName = Helper.getInputFile(args);

        //Words Count based on multiThreading
        FindWordCount multiThread = new FindWordCountWithThreadsimpl();
        long totalTimeMulti = Helper.printExecutionTime(multiThread,fileName);
        System.out.println("Multiple Thread Processing::" + totalTimeMulti);

        //Words Count based on multiThreading with ExecutorService
        FindWordCount multiThreadWithExectorService = new FindWordCountWithExecutorServiceImpl();
        long totalTimeExe = Helper.printExecutionTime(multiThreadWithExectorService,fileName);
        System.out.println("Multiple Thread Processing with ExecutorService::" + totalTimeExe);


        //Words Count based on Java Streams
        FindWordCount wordCount = new FindWordCountWithStreamsImpl();
        long totalTimeStream = Helper.printExecutionTime(wordCount,fileName);
        System.out.println("Parallel Stream processing::" + totalTimeStream);

    }

}