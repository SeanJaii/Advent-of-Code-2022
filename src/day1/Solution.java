package day1;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Stream;

public class Solution {
    ArrayList totals = new ArrayList<Integer>();
    int currentTotal = 0;
    String filename =
            new File("/Users/seanjaii/Desktop/Projects/coding competitions/Advent of Code 2022/src/day1/input.txt")
            .getAbsolutePath();
    Stream<String> lines;

    public void solve() throws IOException {
        // Elf carrying the most calories
        lines = Files.lines(Paths.get(filename));
        lines.forEach(this::sumLinesToDelimiter);
        Collections.sort(totals);
        System.out.println(totals.get(totals.size() - 1));

        // Top three elves carrying the most calories, totalled.
        int top3Total = (int) totals.get(totals.size() - 1);
        top3Total += (int) totals.get(totals.size() - 2);
        top3Total += (int) totals.get(totals.size() - 3);
        System.out.println(top3Total);
    }

    public void sumLinesToDelimiter(String line) {
        if (line.isEmpty()) {
            totals.add(currentTotal);
            currentTotal = 0;
            return ;
        }
        currentTotal += Integer.parseInt(line);
    }

}
