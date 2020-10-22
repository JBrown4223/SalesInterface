import java.io.*;


public class HelperClass {


    public int countLines(String pathname) throws IOException {
        int counter = 0;

        BufferedReader reader = new BufferedReader(new FileReader(pathname));

        while(reader.readLine() != null){
            counter++;
        }
        return counter;
    }
}
