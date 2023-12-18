package adventofcode2023.day18;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class LavaductLagoonPartOne {
    public static void main(String[] args) {
        try {
            List<String> sl = Files.readAllLines(Path.of("src/adventofcode2023/day18/TestData.txt"));
            List<Point> corners = new ArrayList<>();
            int x = 0, y = 0, totalSteps = 0;
            for (String s : sl) {
                String move = s.split(" ")[0];
                int steps = Integer.parseInt(s.split(" ")[1]);
                switch (move) {
                    case "R" -> x += steps;
                    case "L" -> x -= steps;
                    case "U" -> y += steps;
                    case "D" -> y -= steps;
                }
                totalSteps += steps;
                corners.add(new Point(x,y));
            }
            int cL = corners.size(), a = 0;
            for (int i = 0; i < cL-1; i++) { //shoelace formula
                a += corners.get(i).x * corners.get(i+1).y - corners.get(i+1).x * corners.get(i).y;
            }
            a += corners.get(cL-1).x * corners.get(0).y - corners.get(0).x * corners.get(cL-1).y;
            a = a/2;
            a = Math.abs(a);
            int in = a+1-totalSteps/2;
            System.out.println(in+totalSteps);
        } catch (IOException e) {
            System.out.println("No such file found");
        }
    }
}
