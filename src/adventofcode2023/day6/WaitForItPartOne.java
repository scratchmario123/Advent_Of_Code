package adventofcode2023.day6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class WaitForItPartOne {
    public static void main(String[] args) {
        try {
            List<String> sl = Files.readAllLines(Path.of("src/adventofcode2023/day6/TestData.txt"));
            int[] times = Arrays.stream(sl.get(0).substring(5).trim().split("\\s+")).mapToInt(Integer::parseInt).toArray();
            int[] distances = Arrays.stream(sl.get(1).substring(9).trim().split("\\s+")).mapToInt(Integer::parseInt).toArray();
            int product = 1;
            for (int i = 0; i < times.length; i++) {
                double delta = Math.sqrt(Math.pow(times[i],2)-4*distances[i]);
                product *= (int)Math.ceil((times[i]+delta)/2.0) - (int)Math.ceil((times[i]-delta)/2.0);
            }
            System.out.println(product);
        } catch (IOException e) {
            System.out.println("No such file found");
        }
    }
}
