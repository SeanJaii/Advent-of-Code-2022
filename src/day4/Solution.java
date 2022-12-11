package day4;

import static java.lang.System.out;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

public class Solution {
  String filename =
      new File("src/day4/input.txt").getAbsolutePath();
  Stream<String> lines;
  int numberOfAssignmentPairsCoveringTheOther = 0;
  int numberOfAssignmentPairsOverlapping = 0;
  // I like descriptive var/func names. So what.

  public void solve() throws IOException {

    lines = Files.lines(Paths.get(filename));
    lines.forEach(this::split);

    out.println(numberOfAssignmentPairsCoveringTheOther);
    out.println(numberOfAssignmentPairsOverlapping);
  }

  public void split(String line) {
    String[] elves = line.split(",");
    String[] elfARangeStr = elves[0].split("-");
    String[] elfBRangeStr = elves[1].split("-");
    int[] elfARange = {Integer.parseInt(elfARangeStr[0]), Integer.parseInt(elfARangeStr[1])};
    int[] elfBRange = {Integer.parseInt(elfBRangeStr[0]), Integer.parseInt(elfBRangeStr[1])};

    // Part one solution
    if (elfARange[0] <= elfBRange[0] && elfARange[1] >= elfBRange[1]) {
      numberOfAssignmentPairsCoveringTheOther += 1;
    } else if (elfBRange[0] <= elfARange[0] && elfBRange[1] >= elfARange[1]) {
      numberOfAssignmentPairsCoveringTheOther += 1;
    }

    // Part two solution
    // just check that they DON'T overlap
    // that way, you'll only have to check if the upper bounds are less than the lower bounds
    // then negate that to find the cases where they do indeed overlap
    if (!(elfARange[1] < elfBRange[0] || elfBRange[1] < elfARange[0])) {
      numberOfAssignmentPairsOverlapping += 1;
    }
  }
}
