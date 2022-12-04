package day2;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class Solution {
    Map<String, Integer> abcValues = new HashMap<>() {{
        put("A", 1);
        put("B", 2);
        put("C", 3);
    }};

    Map<String, Integer> xyzValues = new HashMap<>() {{
        put("X", 1);
        put("Y", 2);
        put("Z", 3);
    }};

    Map<String, Integer> roundResult = new HashMap<>() {{
        put("A X", 4); put("A Y", 8); put("A Z", 3);
        put("B X", 1); put("B Y", 5); put("B Z", 9);
        put("C X", 7); put("C Y", 2); put("C Z", 6);
    }};

    Map<String, Integer> roundResultsRequired = new HashMap<>() {{
        put("A X", 3); put("A Y", 4); put("A Z", 8);
        put("B X", 1); put("B Y", 5); put("B Z", 9);
        put("C X", 2); put("C Y", 6); put("C Z", 7);
    }};

    int score = 0;
    int requiredScore = 0;

    String filename =
            new File("/Users/seanjaii/Desktop/Projects/coding competitions/Advent of Code 2022/src/day2/input.txt")
                    .getAbsolutePath();
    Stream<String> lines;

    public void solve() throws IOException {
        // Elf carrying the most calories
        lines = Files.lines(Paths.get(filename));
        lines.forEach(this::roundJudge);
        System.out.println(score);
        System.out.println(requiredScore);
    }

    public void roundJudge(String line) {
        score += roundResult.get(line);
        requiredScore += roundResultsRequired.get(line);
    }
}
