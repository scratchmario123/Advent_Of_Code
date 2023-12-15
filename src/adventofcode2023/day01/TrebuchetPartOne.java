package adventofcode2023.day01;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class TrebuchetPartOne {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(new File("src/adventofcode2023/day1/TestData.txt"));
            int sum = 0;
            while (scanner.hasNext()) {
                String s = scanner.nextLine().replaceAll("[a-zA-Z\\s+]","");
                sum += (s.charAt(0)-'0')*10+s.charAt(s.length()-1)-'0';
            }
            System.out.println(sum);
        } catch (IOException e) {
            System.out.println("No such file found");
        }
    }
}
