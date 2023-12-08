package adventofcode2023.day8;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HauntedWastelandPartTwo {
    public static void main(String[] args) {
        try {
            List<String> sl = Files.readAllLines(Path.of("src/adventofcode2023/day8/TestData.txt"));
            String instruction = sl.get(0);
            Map<String,String> nodeToEle = new HashMap<>();
            List<Integer> tries = new ArrayList<>();
            for (int i = 2; i < sl.size(); i++) nodeToEle.put(sl.get(i).split(" = ")[0],sl.get(i).split(" = ")[1]);
            List<String> aNodes = nodeToEle.keySet().stream().filter(e -> e.endsWith("A")).toList();
            for (String aNode : aNodes) {
                int cnt = 0;
                String pre = aNode;
                while (!pre.endsWith("Z")) {
                    char curIn = instruction.charAt(cnt % instruction.length());
                    cnt++;
                    pre = nodeToEle.get(pre).substring(curIn == 'L' ? 1 : 6, curIn == 'L' ? 4 : 9);
                }
                tries.add(cnt);
            }
            BigInteger num = BigInteger.valueOf(tries.get(0));
            for (int t : tries) num = num.multiply(BigInteger.valueOf(t)).divide(num.gcd(BigInteger.valueOf(t)));
            System.out.println(num);
        } catch (IOException e) {
            System.out.println("No such file found");
        }
    }
}
