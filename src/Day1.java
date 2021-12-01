import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day1 {

    public static void main(String[] args) {

        List<Integer> measurements = getListOfElements("utils/day1_input.txt");

        System.out.println("Challenge 1: " + getIncreases(measurements, 1));
        System.out.println("Challenge 2: " + getIncreases(measurements, 3));
    }

    public static int getIncreases (List<Integer> measurements, int window){
        int result = 0, previousValue = 0, value;

        for (int i = 0; i < measurements.size()-window; i++){
            value = measurements.subList(i, i+window).stream().mapToInt(Integer::intValue).sum();
            if (value>previousValue)
                result++;
            previousValue = value;
        }
        return result;
    }

    public static List<Integer> getListOfElements (String file){

        List<Integer> result = new ArrayList<>();
        try (Stream<String> lines = Files.lines(Paths.get(file))) {
            result = lines.map(Integer::parseInt).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
}
