package adventofcode2023.day05;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class IfYouGiveASeedAFertilizerPartOne {
    public static void main(String[] args) {
        try {
            List<String> sl = Files.readAllLines(Path.of("src/adventofcode2023/day5/TestData.txt"));
            List<Long> seeds = new ArrayList<>(Arrays.stream(sl.get(0).substring(7).split(" ")).map(Long::parseLong).toList());
            List<List<Long>> map = new ArrayList<>();
            for (String s : sl) {
                if (!s.isEmpty() && Character.isDigit(s.charAt(0))) map.add(Arrays.stream(s.split(" ")).map(Long::parseLong).toList());
                else if (!map.isEmpty()) {
                    for (int i = 0; i < seeds.size(); i++) {
                        for (List<Long> longs : map) {
                            long dest = longs.get(0), source = longs.get(1), range = longs.get(2), seed = seeds.get(i);
                            if (seed >= source && seed < source + range) {
                                seeds.set(i, seed + dest - source);
                                break;
                            }
                        }
                    }
                    map = new ArrayList<>();
                }
            }
            long ans = Collections.min(seeds);
            System.out.println(ans);
        } catch (IOException e) {
            System.out.println("No such file found");
        }
    }
}
