package adventofcode2023.day05;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class IfYouGiveASeedAFertilizerPartTwo {
    public static void main(String[] args) {
        try {
            List<String> sl = Files.readAllLines(Path.of("src/adventofcode2023/day5/TestData.txt"));
            List<Long> seeds = new ArrayList<>(Arrays.stream(sl.get(0).substring(7).split("\\s+")).map(Long::parseLong).toList());
            for (Long seed : seeds) {
                int i = seeds.indexOf(seed);
                if (i%2==1) seeds.set(i,seed+seeds.get(i-1)-1);
            }
            List<List<Long>> map = new ArrayList<>();
            for (String s : sl) {
                if (!s.isEmpty() && Character.isDigit(s.charAt(0)))
                    map.add(Arrays.stream(s.split("\\s+")).map(Long::parseLong).toList());
                else if (!map.isEmpty()) {
                    map.sort(Comparator.comparing(e -> e.get(1)));
                    for (int i = 0; i < seeds.size(); i+=2) {
                        for (List<Long> longs : map) {
                            long dest = longs.get(0), source = longs.get(1), range = longs.get(2), seed1 = seeds.get(i), seed2 = seeds.get(i+1);
                            if (seed1 >= source && seed2 < source+range) {
                                seeds.set(i, seed1 + dest - source);
                                seeds.set(i+1, seed2 + dest - source);
                                break;
                            } else if (seed1 < source) {
                                seeds.add(i+1,source);
                                seeds.add(i+1,source-1);
                                break;
                            } else if (seed2 >= source + range && source+ range > seed1) {
                                seeds.add(i+1,source+range);
                                seeds.add(i+1,source+range-1);
                                seed1 = seeds.get(i);
                                seed2 = seeds.get(i+1);
                                seeds.set(i, seed1 + dest - source);
                                seeds.set(i+1, seed2 + dest - source);
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
