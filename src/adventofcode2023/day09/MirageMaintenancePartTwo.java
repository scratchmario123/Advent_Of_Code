package adventofcode2023.day09;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class MirageMaintenancePartTwo {
    public static void main(String[] args) {
        try {
            List<String> sl = Files.readAllLines(Path.of("src/adventofcode2023/day9/TestData.txt"));
            int sum = 0;
            for (String s : sl) {
                int[] arr = Arrays.stream(s.split(" ")).mapToInt(Integer::parseInt).toArray();
                for (int i = arr.length-1; i >= 0; i--) {
                    for (int j = arr.length-1; j >= arr.length - i; j--) {
                        arr[j] = arr[j - 1] - arr[j];
                    }
                }
                sum += Arrays.stream(arr).sum();
            }
            System.out.println(sum);
        } catch (IOException e) {
            System.out.println("No such file found");
        }
    }
}
