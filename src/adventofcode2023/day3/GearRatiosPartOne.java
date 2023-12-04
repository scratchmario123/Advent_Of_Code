package adventofcode2023.day3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class GearRatiosPartOne {
    public static void main(String[] args) {
        try {
            List<String> sl = Files.readAllLines(Path.of("src/adventofcode2023/day3/TestData.txt"));
            int sum = 0, L = sl.get(0).length();
            for (int i = 0; i < sl.size(); i++) {
                for (int j = 0; j < L; j++) {
                    if (Character.isDigit(sl.get(i).charAt(j))) {
                        int start = Math.max(0,j-1), temp = sl.get(i).charAt(j)-'0';
                        while (j+1<L) {
                            j++;
                            if (Character.isDigit(sl.get(i).charAt(j))) temp = temp*10+sl.get(i).charAt(j)-'0';
                            else break;
                        }
                        j = Math.min(j,L-1);
                        if (isSymbol(sl.get(i).charAt(start)) || isSymbol(sl.get(i).charAt(j))) sum += temp;
                        else {
                            boolean added = false;
                            for (int k = start; k <= j; k++) {
                                if (i!=0) added = isSymbol(sl.get(i-1).charAt(k));
                                if (i!= sl.size()-1) added = added || isSymbol(sl.get(i+1).charAt(k));
                                if (added) {
                                    sum += temp; break;
                                }
                            }
                        }
                    }
                }
            }
            System.out.println(sum);
        } catch (IOException e) {
            System.out.println("No such file found");
        }
    }

    static boolean isSymbol(char c) {
        return (c < '0' || c > '9') && c != '.';
    }
}
