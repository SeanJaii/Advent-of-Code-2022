package day5;

import static java.lang.System.out;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Solution {
  String filename =
      new File("src/day5/input.txt").getAbsolutePath();
  Stream<String> lines;

  public void solve() throws IOException {

    lines = Files.lines(Paths.get(filename));
    lines.forEach(this::split);

  }


}
