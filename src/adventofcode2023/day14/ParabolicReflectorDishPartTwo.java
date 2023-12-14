package adventofcode2023.day14;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParabolicReflectorDishPartTwo {
    public static void main(String[] args) {
        Map<Integer,List<String>> cycles = new HashMap<>();
        Map<List<String>,Integer> cycleSum = new HashMap<>();
        try {
            List<String> sl = Files.readAllLines(Path.of("src/adventofcode2023/day14/TestData.txt"));
            int tSum = 0, sum, cycle = 0, L = sl.size(), C = sl.get(0).length();
            cycles.put(0,new ArrayList<>(sl));
            cycleSum.put(new ArrayList<>(sl),0);//change need add value instead 0
            while (true) {
                for (int i = 0; i < L; i++) { //north
                    for (int j = 0; j < sl.get(i).length(); j++) {
                        if (sl.get(i).charAt(j) == 'O') {
                            int cnt = i;
                            while (cnt > 0 && sl.get(cnt - 1).charAt(j) != '#' && sl.get(cnt - 1).charAt(j) != 'O') cnt--;
                            sl.set(i, sl.get(i).substring(0, j) + "." + sl.get(i).substring(j + 1));
                            sl.set(cnt, sl.get(cnt).substring(0, j) + "O" + sl.get(cnt).substring(j + 1));
                        }
                    }
                }
                //west
                for (int j = 0; j < C; j++) {
                    for (int i = 0; i < L; i++) {
                        if (sl.get(i).charAt(j) == 'O') {
                            int cnt = j;
                            while (cnt > 0 && sl.get(i).charAt(cnt - 1) != '#' && sl.get(i).charAt(cnt - 1) != 'O') cnt--;
                            sl.set(i, sl.get(i).substring(0, j) + "." + sl.get(i).substring(j + 1));
                            sl.set(i, sl.get(i).substring(0, cnt) + "O" + sl.get(i).substring(cnt + 1));
                        }
                    }
                }
                for (int i = L - 1; i >= 0; i--) { //south
                    for (int j = 0; j < sl.get(i).length(); j++) {
                        if (sl.get(i).charAt(j) == 'O') {
                            int cnt = i;
                            while (cnt < L - 1 && sl.get(cnt + 1).charAt(j) != '#' && sl.get(cnt + 1).charAt(j) != 'O') cnt++;
                            sl.set(i, sl.get(i).substring(0, j) + "." + sl.get(i).substring(j + 1));
                            sl.set(cnt, sl.get(cnt).substring(0, j) + "O" + sl.get(cnt).substring(j + 1));
                        }
                    }
                }
                //east
                for (int j = C - 1; j >= 0; j--) {
                    for (int i = 0; i < L; i++) {
                        if (sl.get(i).charAt(j) == 'O') {
                            int cnt = j;
                            while (cnt < C-1 && sl.get(i).charAt(cnt + 1) != '#' && sl.get(i).charAt(cnt + 1) != 'O') cnt++;
                            sl.set(i, sl.get(i).substring(0, j) + "." + sl.get(i).substring(j + 1));
                            sl.set(i, sl.get(i).substring(0, cnt) + "O" + sl.get(i).substring(cnt + 1));
                            tSum += L- i;
                        }
                    }
                }
                cycle++;
                if (cycles.containsValue(sl)) {
                    int skipped = 0;
                    for (int i : cycles.keySet()) if (cycles.get(i).equals(sl)) skipped = i;
                    int extra = (1000000000-cycle+1)%(cycle-skipped)-1;
                    sum = cycleSum.get(cycles.get(extra+skipped));
                    break;
                } else {
                    cycles.put(cycle, new ArrayList<>(sl));
                    cycleSum.put(new ArrayList<>(sl), tSum);
                    tSum = 0;
                }
            }
            System.out.println(sum);
        } catch (IOException e) {
            System.out.println("No such file found");
        }
    }
}
