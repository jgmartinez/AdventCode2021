import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day1 {

    public static void main(String[] args) throws Exception{

        List<Integer> measurements = getListOfElements("resources/test.txt");

        System.out.println("Challenge 1: " + getIncreases(measurements, 1));
        System.out.println("Challenge 2: " + getIncreases(measurements, 3));
    }

    public static int getIncreases (List<Integer> measurements, int window){
        int result = 0, previousValue=0, value;

        for (int i = 0; i < measurements.size()-window+1; i++){
            value = measurements.subList(i, i + window).stream().mapToInt(Integer::intValue).sum();
            if (value > previousValue && i!=0) //First occurrence must not be evaluated
                result++;
            previousValue = value;
        }
        return result;
    }

    public static List<Integer> getListOfElements (String file) throws Exception{

        List<Integer> result;
        try (Stream<String> lines = Files.lines(Paths.get(file))) {
            result = lines.map(Integer::parseInt).collect(Collectors.toList());
        }
        return result;
    }
}