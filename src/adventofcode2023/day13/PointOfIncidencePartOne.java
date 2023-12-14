package adventofcode2023.day13;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class PointOfIncidencePartOne {
    public static void main(String[] args) {
        try {
            List<String> sl = Files.readAllLines(Path.of("src/adventofcode2023/day13/TestData.txt"));
            int sum = 0;
            List<String> ss = new ArrayList<>();
            for (int i = 0; i < sl.size(); i++) {
                String s = sl.get(i);
                if (!s.isBlank()) ss.add(sl.get(i));
                if (s.isBlank() || i+1==sl.size()) {
                    boolean failed = true;
                    int start;
                    for (int j = 0; j < ss.size()-1; j++) {
                        outer:
                        if (ss.get(j).equals(ss.get(j+1))) {
                            int k = j, l = j+1;
                            start = l;
                            while (k>0 && l < ss.size()-1) {
                                k--; l++;
                                if (!ss.get(k).equals(ss.get(l))) {
                                    break outer;
                                }
                            }
                            failed = false;
                            sum += start*100;
                        }
                        if (!failed) break;
                    }
                    if (failed) {
                        List<String> vss = new ArrayList<>();
                        for (int j = 0; j < ss.get(0).length(); j++) {
                            StringBuilder builder = new StringBuilder();
                            for (String string : ss) builder.append(string.charAt(j));
                            vss.add(builder.toString());
                        }
                        for (int j = 0; j < vss.size()-1; j++) {
                            outer:
                            if (vss.get(j).equals(vss.get(j+1))) {
                                int k = j, l = j+1;
                                start = l;
                                while (k>0 && l < vss.size()-1) {
                                    k--; l++;
                                    if (!vss.get(k).equals(vss.get(l))) {
                                        break outer;
                                    }
                                }
                                failed = false;
                                sum += start;
                            }
                            if (!failed) break;
                        }
                    }
                    ss = new ArrayList<>();
                }
            }
            System.out.println(sum);
        } catch (IOException e) {
            System.out.println("No such file found");
        }
    }
}
