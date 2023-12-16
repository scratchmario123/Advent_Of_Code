package adventofcode2023.day16;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.List;

public class TheFloorWillBeLavaPartTwo {
    static Set<Point> energized = new HashSet<>();
    static Set<Point> repeated = new HashSet<>();

    public static void main(String[] args) {
        try {
            List<String> sl = Files.readAllLines(Path.of("src/adventofcode2023/day16/TestData.txt"));
            char[][] tiles = new char[sl.size()][sl.get(0).length()];
            for (int i = 0; i < sl.size(); i++) tiles[i] = sl.get(i).toCharArray();
            int max = 0;
            for (int i = 0; i < tiles[0].length; i++) {
                max = Math.max(attempt(i,0,tiles),max);
                max = Math.max(attempt(i,tiles.length-1,tiles),max);
            }
            for (int i = 1; i < tiles.length; i++) {
                max = Math.max(attempt(0,i,tiles),max);
                max = Math.max(attempt(tiles[0].length-1,i,tiles),max);
            }
            System.out.println(max);
        } catch (IOException e) {
            System.out.println("No such file found");
        }
    }

    static void move(int x, int y, int dir, char[][] arr) {//up,right,down,left 0 1 2 3
        if (y < 0 || y > arr.length-1 || x < 0 || x > arr[0].length-1) return;
        energized.add(new Point(x,y));
        switch (arr[y][x]) {
            case '/':
                if (dir == 1) move(x,y-1,0,arr);
                else if (dir == 3) move(x,y+1,2,arr);
                else if (dir == 2) move(x-1,y,3,arr);
                else move(x+1,y,1,arr);
                return;
            case '\\':
                if (dir == 3) move(x,y-1,0,arr);
                else if (dir == 1) move(x,y+1,2,arr);
                else if (dir == 0) move(x-1,y,3,arr);
                else move(x+1,y,1,arr);
                return;
            case '|':
                if (repeated.contains(new Point(x,y))) return;
                repeated.add(new Point(x,y));
                if (dir != 2) move(x,y-1,0,arr);
                if (dir != 0) move(x,y+1,2,arr);
                return;
            case '-':
                if (repeated.contains(new Point(x,y))) return;
                repeated.add(new Point(x,y));
                if (dir != 1) move(x-1,y,3,arr);
                if (dir != 3) move(x+1,y,1,arr);
                return;
            case '.':
                move(dir == 1 ? x+1 : dir==3 ? x-1 : x,dir == 0 ? y-1 : dir == 2 ? y+1 : y,dir,arr);
        }
    }

    static int attempt(int x, int y, char[][] arr) {
        int max = 0;
        for (int i = 0; i < 4; i++) {
            energized = new HashSet<>();
            repeated = new HashSet<>();
            move(x,y,i,arr);
            max = Math.max(energized.size(),max);
        }
        return max;
    }
}
