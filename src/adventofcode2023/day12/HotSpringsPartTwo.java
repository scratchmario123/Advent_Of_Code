package adventofcode2023.day12;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HotSpringsPartTwo {
    public static void main(String[] args) {
        Map<String,Long> cache = new HashMap<>();
        try {
            List<String> sl = Files.readAllLines(Path.of("src/adventofcode2023/day12/TestData.txt"));
            long sum = 0;
            for (String s : sl) {
                String bNum = s.split(" ")[1];
                int[] num = Arrays.stream(String.join(",", bNum,bNum,bNum,bNum,bNum).split(",")).mapToInt(Integer::parseInt).toArray();
                String part = s.split(" ")[0];
                part = String.join("?",part,part,part,part,part);
                sum += countArrangements(part,num,cache);
            }
            System.out.println(sum);
        } catch (IOException e) {
            System.out.println("No such file found");
        }
    }

    static long countArrangements(String part, int[] num, Map<String,Long> cache) {
        long res = 0;
        if (part.isEmpty()) if (num.length > 0) return 0; else return 1;
        if (num.length == 0) if (part.contains("#")) return 0; else return 1;
        String key = part+Arrays.toString(num);
        if (cache.containsKey(key)) return cache.get(key);
        if (part.startsWith(".") || part.startsWith("?")) {
                res += countArrangements(part.substring(1), num, cache);
        }
        if (part.startsWith("#") || part.startsWith("?")) {
            if (num[0] <= part.length() && !part.substring(0,num[0]).contains(".") && (num[0] == part.length() || part.charAt(num[0])!='#')) {
                res += countArrangements(num[0] + 1 > part.length() ? "" : part.substring(num[0] + 1), Arrays.copyOfRange(num, 1, num.length), cache);
            }
        }
        cache.put(key,res);
        return res;
    }
}

