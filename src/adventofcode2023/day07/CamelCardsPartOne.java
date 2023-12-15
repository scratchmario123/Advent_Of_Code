package adventofcode2023.day07;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CamelCardsPartOne {
    public static void main(String[] args) {
        try {
            List<String> sl = Files.readAllLines(Path.of("src/adventofcode2023/day7/TestData.txt")), mSl = new ArrayList<>();
            for (String s : sl) {
                String fCards = s.replace("T","a").replace("J","b").replace("Q","c").replace("K","d").replace("A","e");
                char[] cards = fCards.split(" ")[0].toCharArray();
                int[] att = new int[]{0,0,0,0};
                int temp = 0;
                Arrays.sort(cards);
                for (int i = 0; i < cards.length-1; i++) {
                    if (cards[i]==cards[i+1]) temp++;
                    if (temp > 0 && (cards[i]!=cards[i+1] || i==cards.length-2)) {
                        att[temp-1]++;
                        temp=0;
                    }
                }
                mSl.add(String.format("%d%s",att[3]==1 ? 6 : att[2]==1 ? 5 : att[1]==1 && att[0] == 1 ? 4 : att[1]==1 ? 3 : att[0]==2 ? 2 : att[0] == 1 ? 1 : 0,fCards));
            }
            Collections.sort(mSl);
            int sum = 0;
            for (int i = 0; i < mSl.size(); i++) sum += Integer.parseInt(mSl.get(i).split(" ")[1])*(i+1);
            System.out.println(sum);
        } catch (IOException e) {
            System.out.println("No such file found");
        }
    }
}
