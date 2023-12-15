package adventofcode2023.day06;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class WaitForItPartTwo {
    public static void main(String[] args) {
        try {
            List<String> sl = Files.readAllLines(Path.of("src/adventofcode2023/day6/TestData.txt"));
            long time = Long.parseLong(sl.get(0).substring(5).trim().replaceAll("\\s+","")), distance = Long.parseLong(sl.get(1).substring(9).trim().replaceAll("\\s+",""));
            double delta = Math.sqrt(Math.pow(time,2)-4*distance);
            long product = (long)Math.ceil((time+delta)/2.0) - (long)Math.ceil((time-delta)/2.0);
            System.out.println(product);
        } catch (IOException e) {
            System.out.println("No such file found");
        }
    }
}
