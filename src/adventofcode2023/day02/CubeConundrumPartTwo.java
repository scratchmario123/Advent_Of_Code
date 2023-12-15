package adventofcode2023.day02;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class CubeConundrumPartTwo {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(new File("src/adventofcode2023/day2/TestData.txt"));
            int sum = 0;
            while (scanner.hasNext()) {
                String s = scanner.nextLine();
                String[] info = s.split(":")[1].split("; ");
                int maxRed = 0, maxGreen = 0, maxBlue = 0;
                for (String colors : info) {
                    int red = 0, green = 0, blue = 0;
                    for (String c : colors.split(", ")) {
                        int sub = Integer.parseInt(c.trim().split("\\s+")[0]);
                        if (c.contains("red")) red += sub;
                        if (c.contains("green")) green += sub;
                        if (c.contains("blue")) blue += sub;
                    }
                    if (red > maxRed) maxRed = red;
                    if (green > maxGreen) maxGreen = green;
                    if (blue > maxBlue) maxBlue = blue;
                }
                sum += maxRed * maxGreen * maxBlue;
            }
            System.out.println(sum);
        } catch (IOException e) {
            System.out.println("No such file found");
        }
    }
}
