package adventofcode2023.day11;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.List;

public class CosmicExpansionPartOne {
    public static void main(String[] args) {
        try {
            List<String> sl = Files.readAllLines(Path.of("src/adventofcode2023/day11/TestData.txt"));
            Map<Integer, Point> points = new HashMap<>();
            Set<Integer> expandX = new HashSet<>(), notExpandY = new HashSet<>();
            int cnt = 0, sum = 0, S = sl.size(), L = sl.get(0).length();
            for (int i = 0; i < S; i++) {
                String s = sl.get(i);
                if (!s.contains("#")) expandX.add(i);
                else for (int j = 0; j < L; j++) {
                    if (s.charAt(j)=='#') {
                        notExpandY.add(j);
                        points.put(cnt,new Point(i,j));
                        cnt++;
                    }
                }
            }
            for (int i = 0; i < points.size()-1; i++) {
                for (int j = i+1; j < points.size(); j++) {
                    Point o = points.get(i), d = points.get(j);
                    int x = Math.abs(o.x-d.x), y = Math.abs(o.y-d.y);
                    long a = expandX.stream().filter(e -> e > Math.min(o.x,d.x) && e < Math.max(d.x,o.x)).count();
                    long b = y == 0 ? 0 : y+1 - notExpandY.stream().filter(e -> e >= Math.min(o.y,d.y) && e <= Math.max(d.y,o.y)).count();
                    sum += (int) (x+a+y+b);
                }
            }
            System.out.println(sum);
        } catch (IOException e) {
            System.out.println("No such file found");
        }
    }
}
