package adventofcode2023.day15;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class LensLibraryPartOne {
    public static void main(String[] args) {
        try {
            String input = Files.readString(Path.of("src/adventofcode2023/day15/TestData.txt"));
            int sum = 0;
            String[] sl = input.split(",");
            for (String s : sl) {
                char[] cl = s.toCharArray();
                int temp = 0;
                for (char c : cl) temp = ((temp + c)*17)%256;
                sum += temp;
            }
            System.out.println(sum);
        } catch (IOException e) {
            System.out.println("No such file found");
        }
    }
}
