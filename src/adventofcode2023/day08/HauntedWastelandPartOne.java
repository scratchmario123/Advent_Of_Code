package adventofcode2023.day08;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class HauntedWastelandPartOne {
    public static void main(String[] args) {
        try {
            List<String> sl = Files.readAllLines(Path.of("src/adventofcode2023/day8/TestData.txt"));
            String instruction = sl.get(0), pre = "AAA";
            Map<String,String> nodeToEle = new HashMap<>();
            for (int i = 2; i < sl.size(); i++) nodeToEle.put(sl.get(i).split(" = ")[0],sl.get(i).split(" = ")[1]);
            int cnt = 0;
            while (!pre.equals("ZZZ")) {
                char curIn = instruction.charAt(cnt%instruction.length());
                cnt++;
                pre = nodeToEle.get(pre).substring(curIn == 'L' ? 1 : 6, curIn == 'L' ? 4 : 9);
            }
            System.out.println(cnt);
        } catch (IOException e) {
            System.out.println("No such file found");
        }
    }
}
