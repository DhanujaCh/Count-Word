import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

public class CustomFileIterator implements Iterator, AutoCloseable {
    private final BufferedReader br;
    private String nextLine;

    public CustomFileIterator(String fileName) throws IOException {
        br = new BufferedReader(new FileReader(fileName));
        nextLine = br.readLine();
    }

    @Override
    public boolean hasNext() {
        return nextLine != null;
    }

    @Override
    public String next() {
        String lineToReturn = nextLine;
        try {
            nextLine = br.readLine();
        } catch (IOException e) {
            nextLine = null;
        }
        return lineToReturn;
    }

    @Override
    public void close() throws IOException {
        br.close();
    }
}