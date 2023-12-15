package adventofcode2023.day15;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class LensLibraryPartTwo {
    public static void main(String[] args) {
        try {
            String input = Files.readString(Path.of("src/adventofcode2023/day15/TestData.txt"));
            List<LinkedHashMap<String,Integer>> boxes = new ArrayList<>(256);
            for (int i = 0; i < 256; i++) boxes.add(new LinkedHashMap<>());
            int sum = 0;
            String[] sl = input.split(",");
            for (String s : sl) {
                char[] cl = s.contains("=") ? s.split("=")[0].toCharArray() : s.split("-")[0].toCharArray();
                int temp = 0;
                for (char c : cl) temp = ((temp + c)*17)%256;
                if (s.contains("-")) boxes.get(temp).remove(s.split("-")[0]);
                else boxes.get(temp).put(s.split("=")[0],Integer.parseInt(s.split("=")[1]));
            }
            for (int i = 0; i < boxes.size(); i++) {
                LinkedHashMap<String,Integer> map = boxes.get(i);
                List<String> arr = new ArrayList<>(map.keySet());
                for (int j = 0; j < arr.size(); j++) sum += (i+1) * (j+1) * map.get(arr.get(j));
            }
            System.out.println(sum);
        } catch (IOException e) {
            System.out.println("No such file found");
        }
    }
}
