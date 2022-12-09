package day3;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.Character.isUpperCase;
import static java.lang.System.*;

public class Solution {
    String filename =
            new File("src/day3/input.txt").getAbsolutePath();
    Stream<String> lines;

    int prioritySum, prioritySumByPartitions, partitionCount = 0;
    List<List<String>> partitions = new ArrayList<>();

    public void solve() throws IOException {

        lines = Files.lines(Paths.get(filename));
        lines.forEach(this::perLine);
        out.println("prioritySum: " + prioritySum);

        // part 2
        perGroup();
        out.println(prioritySumByPartitions);

    }

    public void perLine(String line) {
        // partitions = for part deux
        if (partitionCount == 0) {
            partitions.add(new ArrayList<>(Arrays.asList(line)));
            partitionCount++;
        } else if (partitionCount == 3) {
            partitions.add(new ArrayList<>(Arrays.asList(line)));
            partitionCount = 1;
        } else {
            partitions.get(partitions.size()-1).add(line);
            partitionCount++;
        }

        List<String> lineArr = splitLine(line);

        prioritySum += priorityValue(getPriorityCharacter(lineArr,2));
    }

    private void perGroup() {
        for (List<String> lineList: partitions) {
            prioritySumByPartitions
                += priorityValue(getPriorityCharacter(lineList, 3));
        }
    }

    public List<String> splitLine(String line) {
        return List.of(
            line.substring(0, line.length() / 2),
            line.substring(line.length() / 2)
        );
    }

    public Character getPriorityCharacter(List<String> lineArr, int x) {
        Map<Character, Integer> occurrences;

        if (x == 2) {
            occurrences = Stream.of(
                sumOccurrences(lineArr.get(0)),
                sumOccurrences(lineArr.get(1)))
            .flatMap(map -> map.entrySet().stream())
                .collect(Collectors.toMap(
                    Map.Entry::getKey,
                    Map.Entry::getValue,
                    Integer::sum
                ));
        } else {
            occurrences = Stream.of(
                    sumOccurrences(lineArr.get(0)),
                    sumOccurrences(lineArr.get(1)),
                    sumOccurrences(lineArr.get(2)))
                .flatMap(map -> map.entrySet().stream())
                .collect(Collectors.toMap(
                    Map.Entry::getKey,
                    Map.Entry::getValue,
                    Integer::sum
                ));
        }

        return occurrences.entrySet()
            .stream()
            .filter(entry -> entry.getValue() >= x)
            .toList()
            .get(0)
            .getKey();
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
