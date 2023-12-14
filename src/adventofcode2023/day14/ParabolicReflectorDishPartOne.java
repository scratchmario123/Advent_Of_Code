package adventofcode2023.day14;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class ParabolicReflectorDishPartOne {
    public static void main(String[] args) {
        try {
            List<String> sl = Files.readAllLines(Path.of("src/adventofcode2023/day14/TestData.txt"));
            int sum = 0;
            for (int i = 0; i < sl.size(); i++) {
                for (int j = 0; j < sl.get(i).length(); j++) {
                    if (sl.get(i).charAt(j)=='O') {
                        int cnt = i;
                        while (cnt > 0 && sl.get(cnt-1).charAt(j)!= '#' && sl.get(cnt-1).charAt(j)!= 'O') cnt--;
                        sl.set(i,sl.get(i).substring(0,j) + "." + sl.get(i).substring(j+1));
                        sl.set(cnt,sl.get(cnt).substring(0,j) + "O" + sl.get(cnt).substring(j+1));
                        sum += sl.size() - cnt;
                    }
                }
            }
            System.out.println(sum);
        } catch (IOException e) {
            System.out.println("No such file found");
        }
    }
}
