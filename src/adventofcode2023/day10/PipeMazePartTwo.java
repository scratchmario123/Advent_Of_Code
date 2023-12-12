package adventofcode2023.day10;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class PipeMazePartTwo {
    public static void main(String[] args) {
        try {
            List<String> sl = Files.readAllLines(Path.of("src/adventofcode2023/day10/TestData.txt"));
            char cur = '+';
            int L = sl.get(0).length(), S = sl.size(), x = 0, y = 0, pSpace = -1;//pSpace: 0=above,1=right,2=below,3=left
            outer: for (int i = 0; i < S; i++) {
                for (int j = 0; j < L; j++) {
                    if (sl.get(i).charAt(j)=='S') {
                        x=j;
                        y=i;
                        break outer;
                    }
                }
            }
            List<Point> corners = new ArrayList<>();
            corners.add(new Point(x,y));
            int step = 0, a = 0, in = 0;
            while (cur != 'S') {
                if (cur == 'L' || cur == 'J' || cur == '7' || cur == 'F') corners.add(new Point(x,y));
                if ((cur == 'L' || cur == 'J' || cur == '|' || cur == '+') && pSpace != 0 && y!=0 && (getChar(sl,y-1,x) == '|' || getChar(sl,y-1,x) == 'F' || getChar(sl,y-1,x) == '7' || getChar(sl,y-1,x) == 'S')) {
                    y=y-1;
                    pSpace = 2;
                } else if ((cur == '7' || cur == 'F' || cur == '|' || cur == '+') && pSpace != 2 && y!= S-1 && (getChar(sl,y+1,x) == '|' || getChar(sl,y+1,x) == 'L' || getChar(sl,y+1,x) == 'J' || getChar(sl,y+1,x) == 'S')) {
                    y=y+1;
                    pSpace = 0;
                } else if ((cur == 'L' || cur == 'F' || cur == '-' || cur == '+') && pSpace != 1 && x!= L-1 && (getChar(sl,y,x+1) == '-' || getChar(sl,y,x+1) == '7' || getChar(sl,y,x+1) == 'J' || getChar(sl,y,x+1) == 'S')) {
                    x=x+1;
                    pSpace = 3;
                } else {
                    x=x-1;
                    pSpace = 1;
                }
                cur = getChar(sl,y,x);
                step++;
            }
            int cL = corners.size();
            for (int i = 0; i < cL-1; i++) { //shoelace formula & S is not corner
                a += corners.get(i).x * corners.get(i+1).y - corners.get(i+1).x * corners.get(i).y;
            }
            a += corners.get(cL-1).x * corners.get(0).y - corners.get(0).x * corners.get(cL-1).y;
            a/=2;
            a = Math.abs(a);
            in = a+1-step/2;
            System.out.println(in);
        } catch (IOException e) {
            System.out.println("No such file found");
        }
    }

    static char getChar(List<String> arr, int i, int j) {
        return arr.get(i).charAt(j);
    }
}
