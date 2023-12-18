package adventofcode2023.day18;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class LavaductLagoonPartTwo {
    public static void main(String[] args) {
        try {
            List<String> sl = Files.readAllLines(Path.of("src/adventofcode2023/day18/TestData.txt"));
            List<Point> corners = new ArrayList<>();
            int x = 0, y = 0, totalSteps = 0;
            for (String s : sl) {
                String code = s.split(" ")[2].substring(2,8);
                String move = code.substring(5);
                int steps = Integer.parseInt(code.substring(0,5),16);
                switch (move) {
                    case "0" -> x += steps;
                    case "2" -> x -= steps;
                    case "3" -> y += steps;
                    case "1" -> y -= steps;
                }
                totalSteps += steps;
                corners.add(new Point(x,y));
            }
            int cL = corners.size();
            long a = 0;
            for (int i = 0; i < cL-1; i++) { //shoelace formula
                a += (long) corners.get(i).x * corners.get(i+1).y - (long) corners.get(i + 1).x * corners.get(i).y;
            }
            a += (long) corners.get(cL - 1).x * corners.get(0).y - (long) corners.get(0).x * corners.get(cL-1).y;
            a = a/2;
            a = Math.abs(a);
            long in = a+1-totalSteps/2;
            System.out.println(in+totalSteps);
        } catch (IOException e) {
            System.out.println("No such file found");
        }
    }
}
