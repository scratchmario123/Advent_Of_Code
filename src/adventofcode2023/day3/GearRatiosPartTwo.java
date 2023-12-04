package adventofcode2023.day3;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GearRatiosPartTwo {
    public static void main(String[] args) {
        try {
            List<String> sl = Files.readAllLines(Path.of("src/adventofcode2023/day3/TestData.txt"));
            int sum = 0, L = sl.get(0).length();
            Map<Point,Integer> gearToNum = new HashMap<>();
            for (int i = 0; i < sl.size(); i++) {
                for (int j = 0; j < L; j++) {
                    if (Character.isDigit(sl.get(i).charAt(j))) {
                        int start = Math.max(0,j-1), temp = sl.get(i).charAt(j)-'0';
                        while (j+1<L) {
                            j++;
                            if (Character.isDigit(sl.get(i).charAt(j))) temp = temp*10+sl.get(i).charAt(j)-'0';
                            else break;
                        }
                        j = Math.min(j,L-1);
                        boolean added = false;
                        Point point = null;
                        if (isGear(sl.get(i).charAt(start))) point = new Point(i,start);
                        else if (isGear(sl.get(i).charAt(j))) point = new Point(i,j);
                        else {
                            for (int k = start; k <= j; k++) {
                                if (i!=0) added = isGear(sl.get(i-1).charAt(k));
                                if (added) {point = new Point(i-1,k);break;}
                                if (i!= sl.size()-1) added = isGear(sl.get(i+1).charAt(k));
                                if (added) {point = new Point(i+1,k);break;}
                            }
                        }
                        if (point != null) {
                            if (gearToNum.containsKey(point)) {
                                sum += temp * gearToNum.get(point);
                                gearToNum.remove(point);
                            } else {
                                gearToNum.put(point,temp);
                            }
                        }
                    }
                }
            }
            System.out.println(sum);
        } catch (IOException e) {
            System.out.println("No such file found");
        }
    }

    static boolean isGear(char c) {
        return c == '*';
    }
}
