import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day2 {

    public static void main(String[] args) throws Exception{
        List<String> directions = getListFromFile("resources/day2_input.txt");
        System.out.println("Challenge 1: " + compute(directions,1));
        System.out.println("Challenge 2: " + compute(directions,2));
    }

    public static int compute (List<String> measurements, int chall){
        int hPos=0, depth=0, aim=0;
        String[] lineArray;
        for(String line : measurements){
            lineArray = line.split(" ");
            switch (lineArray[0]){
                case "forward":
                    if (chall==2)
                        depth += aim*Integer.parseInt(lineArray[1]);
                    hPos += Integer.parseInt(lineArray[1]);
                    break;
                case "up":
                    if (chall==2)
                        aim -= Integer.parseInt(lineArray[1]);
                    else
                        depth -= Integer.parseInt(lineArray[1]);
                    break;
                case "down":
                    if (chall==2)
                        aim += Integer.parseInt(lineArray[1]);
                    else
                        depth += Integer.parseInt(lineArray[1]);
                    break;
            }
        }
        return depth*hPos;
    }

    public static List<String> getListFromFile (String file) throws IOException {
        List<String> result;
        try (Stream<String> lines = Files.lines(Paths.get(file))) {
            result = lines.collect(Collectors.toList());
        }
        return result;
    }

    /*
    public static Map<String, Integer> firstProblemMagic (String file) throws IOException{
        Map<String, Integer> result;
        try (Stream<String> lines = Files.lines(Paths.get(file))) {
            result = lines.map(l -> Arrays.asList(l.split(" "))).collect(Collectors.toMap(a->a.get(0), b->Integer.parseInt(b.get(1)), Integer::sum));
        }
        return result;
    }
    */
}
