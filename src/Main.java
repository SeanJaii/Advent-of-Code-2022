import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        // Day 1
        day1.Solution day1Solution = new day1.Solution();
        try {
            day1Solution.solve();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Day 2
        day2.Solution day2Solution = new day2.Solution();
        try {
            day2Solution.solve();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}