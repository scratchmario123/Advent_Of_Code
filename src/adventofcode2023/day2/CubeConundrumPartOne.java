package adventofcode2023.day2;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class CubeConundrumPartOne {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(new File("src/adventofcode2023/day2/TestData.txt"));
            int sum = 0;
            while (scanner.hasNext()) {
                String s = scanner.nextLine();
                int gid = Integer.parseInt(s.split(":")[0].split("\\s+")[1]);
                String[] info = s.split(":")[1].split("; ");
                boolean rule = false;
                for (String colors : info) {
                    int red = 0, green = 0, blue = 0;
                    for (String c : colors.split(", ")) {
                        int sub = Integer.parseInt(c.trim().split("\\s+")[0]);
                        if (c.contains("red")) red += sub;
                        if (c.contains("green")) green += sub;
                        if (c.contains("blue")) blue += sub;
                        if (red <= 12 && green <= 13 && blue <= 14) continue;
                        rule = true;
                    }
                }
                if (!rule) sum += gid;
            }
            System.out.println(sum);
        } catch (IOException e) {
            System.out.println("No such file found");
        }
    }
}
