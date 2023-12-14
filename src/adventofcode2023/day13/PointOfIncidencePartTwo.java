package adventofcode2023.day13;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class PointOfIncidencePartTwo {
    public static void main(String[] args) {
        try {
            List<String> sl = Files.readAllLines(Path.of("src/adventofcode2023/day13/TestData.txt"));
            int sum = 0;
            List<String> ss = new ArrayList<>();
            for (int i = 0; i < sl.size(); i++) {
                String s = sl.get(i);
                if (!s.isBlank()) ss.add(sl.get(i));
                if (s.isBlank() || i+1==sl.size()) {
                    boolean failed = true, fixed = false;
                    int start = 0;
                    for (int j = 0; j < ss.size()-1; j++) {
                        int k = j, l = j+1;
                        outer:
                        if (ss.get(k).equals(ss.get(l)) || checkDiff(ss.get(k),ss.get(l))) {
                            if (checkDiff(ss.get(k),ss.get(l))) fixed = true;
                            start = l;
                            while (k>0 && l < ss.size()-1) {
                                k--; l++;
                                System.out.println(ss.get(k) + "\n" + ss.get(l) + "\n");
                                if (!ss.get(k).equals(ss.get(l)) && fixed) {
                                    fixed = false;
                                    break outer;
                                }
                                else if (!fixed && checkDiff(ss.get(k),ss.get(l))) fixed = true;
                            }
                            if (fixed) {
                                failed = false;
                                sum += start * 100;
                            }
                        }
                        if (!failed) {
                            System.out.println(start*100 + "\n");
                            break;
                        }
                    }
                    fixed = false;
                    if (failed) { //vertical
                        List<String> vss = new ArrayList<>();
                        for (int j = 0; j < ss.get(0).length(); j++) {
                            StringBuilder builder = new StringBuilder();
                            for (String string : ss) builder.append(string.charAt(j));
                            vss.add(builder.toString());
                        }
                        for (int j = 0; j < vss.size()-1; j++) {
                            int k = j, l = j+1;
                            outer:
                            if (vss.get(k).equals(vss.get(l)) || checkDiff(vss.get(k),vss.get(l))) {
                                if (checkDiff(vss.get(k),vss.get(l))) fixed = true;
                                start = l;
                                while (k>0 && l < vss.size()-1) {
                                    k--; l++;
                                    if (!vss.get(k).equals(vss.get(l)) && fixed) {
                                        fixed = false;
                                        break outer;
                                    }
                                    else if (!fixed && checkDiff(vss.get(k),vss.get(l))) fixed = true;
                                }
                                if (fixed) {
                                    failed = false;
                                    sum += start;
                                }
                            }
                            if (!failed) break;
                        }
                        System.out.println(start + "\n");
                    }
                    ss = new ArrayList<>();
                }
            }
            System.out.println(sum);
        } catch (IOException e) {
            System.out.println("No such file found");
        }
    }

    static boolean checkDiff(String o, String o2) {
        int cnt = 0;
        for (int i = 0; i < o.length(); i++) if (o.charAt(i)!=o2.charAt(i)) cnt++;
        return cnt == 1;
    }
}
