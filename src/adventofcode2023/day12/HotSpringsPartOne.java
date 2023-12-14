package adventofcode2023.day12;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class HotSpringsPartOne {
    public static void main(String[] args) {
        try {
            List<String> sl = Files.readAllLines(Path.of("src/adventofcode2023/day12/TestData.txt"));
            int sum = 0;
            for (String s : sl) {
                int[] num = Arrays.stream(s.split(" ")[1].split(",")).mapToInt(Integer::parseInt).toArray();
                String part = s.split(" ")[0];
                sum += countArrangements(part,num);
            }
            System.out.println(sum);
        } catch (IOException e) {
            System.out.println("No such file found");
        }
    }

    static int countArrangements(String part, int[] num) {
        int res = 0;
        if (part.isEmpty()) if (num.length > 0)return 0; else return 1;
        if (num.length == 0) if (part.contains("#")) return 0; else return 1;
        if (part.startsWith(".") || part.startsWith("?")) res += countArrangements(part.substring(1),num);
        if (part.startsWith("#") || part.startsWith("?")) {
            if (num[0] <= part.length() && !part.substring(0,num[0]).contains(".") && (num[0] == part.length() || part.charAt(num[0])!='#')) {
                res += countArrangements(num[0]+1 > part.length() ? "" : part.substring(num[0]+1),Arrays.copyOfRange(num,1,num.length));
            }
        }
        return res;
    }
}
