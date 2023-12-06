package adventofcode2023.day4;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ScratchcardsPartOne {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(new File("src/adventofcode2023/day4/TestData.txt"));
            int sum = 0;
            while (scanner.hasNext()) {
                String s = scanner.nextLine();
                List<Integer> winning = Arrays.stream(s.split(": ")[1].trim().split(" \\| ")[0].split("\\s+")).map(Integer::parseInt).toList();
                sum += (int) Math.pow(2,winning.stream().filter(e -> Arrays.stream(s.split(": ")[1].split(" \\| ")[1].trim().split("\\s+")).map(Integer::parseInt).toList().contains(e)).toList().size()-1);
            }
            System.out.println(sum);
        } catch (IOException e) {
            System.out.println("No such file found");
        }
    }
}
