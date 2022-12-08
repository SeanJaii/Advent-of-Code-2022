package day3;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.Character.isUpperCase;

public class Solution {
    String filename =
            new File("/Users/seanjaii/Desktop/Projects/coding competitions/Advent of Code 2022/src/day3/input.txt")
                    .getAbsolutePath();
    Stream<String> lines;

    int prioritySum = 0;

    public void solve() throws IOException {
        lines = Files.lines(Paths.get(filename));
        lines.forEach(this::perLine);
        System.out.println(prioritySum);
    }

    public void perLine(String line) {
        String[] lineArr = splitLine(line);

        Map<Character, Integer> occurrences = Stream.of(
                        sumOccurrences(lineArr[0]),
                        sumOccurrences(lineArr[1])
                ) // Oh my goodness Java makes everything so long. (╯°□°)╯︵ ┻━┻
                .flatMap(map -> map.entrySet().stream())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        Integer::sum
                ));

        Character priorityCharacter = occurrences.entrySet() // Here we go again...
                .stream()
                .filter(entry -> entry.getValue() > 1)
                .toList()
                .get(0)
                .getKey();

        prioritySum += priorityValue(priorityCharacter);
    }

    public String[] splitLine(String line) {
        return new String[]{
                line.substring(0, line.length() / 2),
                line.substring(line.length() / 2)
        };
    }

    public Map<Character, Integer> sumOccurrences(String line) {
        Map<Character, Integer> occurrences = new HashMap<>();

        for (char letter : line.toCharArray()) {
            occurrences.put(letter, 1);
        }

        return occurrences;
    }

    public int priorityValue(Character letter) {
        return isUpperCase(letter) ? letter - 64 + 26 : letter - 96;
    }
}
