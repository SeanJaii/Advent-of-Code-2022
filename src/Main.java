import java.io.IOException;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        // change day`x` to run a day's solution

        day5.Solution daySolution = new day5.Solution();
        try {
            daySolution.solve();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}