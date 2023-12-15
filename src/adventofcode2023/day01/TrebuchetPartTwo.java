package adventofcode2023.day01;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class TrebuchetPartTwo {
    public static void main(String[] args) {
        Map<String,Integer> wordToNum = Map.ofEntries(
                Map.entry("one",1),
                Map.entry("two",2),
                Map.entry("three",3),
                Map.entry("four",4),
                Map.entry("five",5),
                Map.entry("six",6),
                Map.entry("seven",7),
                Map.entry("eight",8),
                Map.entry("nine",9)
        );
        try {
            Scanner scanner = new Scanner(new File("src/adventofcode2023/day1/TestData.txt"));
            int sum = 0;
            while (scanner.hasNext()) {
                String s = scanner.nextLine();
                int min = s.length(), minNum = 0, max = -1, maxNum = 0;
                for (String check : wordToNum.keySet()) {
                    int i = s.length(), j = -1;
                    if (s.contains(check)) {
                        i = s.indexOf(check);
                        j = s.lastIndexOf(check);
                    }
                    if (s.contains(wordToNum.get(check).toString())) {
                        i = Math.min(s.indexOf(wordToNum.get(check).toString()), i);
                        j = Math.max(s.lastIndexOf(wordToNum.get(check).toString()), j);
                    }
                    if (i<min) {
                        min=i;
                        minNum = wordToNum.get(check);
                    }
                    if (j>max) {
                        max=j;
                        maxNum = wordToNum.get(check);
                    }
                }
                sum += minNum*10+maxNum;
            }
            System.out.println(sum);
        } catch (IOException e) {
            System.out.println("No such file found");
        }
    }
}
