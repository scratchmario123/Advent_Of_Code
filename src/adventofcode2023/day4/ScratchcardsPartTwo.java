package adventofcode2023.day4;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ScratchcardsPartTwo {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(new File("src/adventofcode2023/day4/TestData.txt"));
            int cards = 0;
            List<Integer> copies = new ArrayList<>();
            while (scanner.hasNext()) {
                String s = scanner.nextLine();
                cards++;
                int card = Integer.parseInt(s.split(":")[0].split("Card")[1].trim());
                if (copies.size() < card) copies.add(1);
                else copies.set(card-1,copies.get(card-1)+1);
                List<Integer> winning = Arrays.stream(s.split(": ")[1].trim().split(" \\| ")[0].split("\\s+")).map(Integer::parseInt).toList();
                int wins = winning.stream().filter(e -> Arrays.stream(s.split(": ")[1].split(" \\| ")[1].trim().split("\\s+")).map(Integer::parseInt).toList().contains(e)).toList().size();
                for (int i = card; i < card+wins; i++) {
                    if (copies.size() <= i) copies.add(copies.get(card-1));
                    else copies.set(i,copies.get(i)+copies.get(card-1));
                }
            }
            int sum = copies.subList(0,cards).stream().mapToInt(Integer::intValue).sum();
            System.out.println(sum);
        } catch (IOException e) {
            System.out.println("No such file found");
        }
    }
}
